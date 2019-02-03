import Model.Address;
import Model.Basket;
import Model.Product;
import Model.User;
import Persistance.AddressDao;
import Persistance.BasketDao;
import Persistance.ProductDao;
import Persistance.UserDao;
import Resource.*;
import Security.AuthenticationService;
import Security.AuthorizerService;
import Security.UnAuthorizedHandler;
import io.dropwizard.Application;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class WebshopApplication extends Application<WebshopConfig> {

    public static void main(String[] args) throws Exception {
        new WebshopApplication().run(args);
    }

    private final HibernateBundle<WebshopConfig> hibernate = new HibernateBundle<WebshopConfig>(User.class, Address.class, Product.class, Basket.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(WebshopConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<WebshopConfig> bootstrap){

        bootstrap.addBundle(hibernate);
    }


    public void setupAuth(Environment environment,HibernateBundle hibernate, UserDao userDao){

        AuthenticationService auth = new UnitOfWorkAwareProxyFactory(hibernate)
                .create(AuthenticationService.class, UserDao.class, userDao);

        environment.jersey().register(
                new AuthDynamicFeature(
                        new BasicCredentialAuthFilter.Builder<User>()
                                .setAuthenticator(auth)
                                .setAuthorizer(new AuthorizerService())
                                .setRealm("1919Shop Privilages")
                                .setUnauthorizedHandler(new UnAuthorizedHandler())
                                .buildAuthFilter()
                )
        );

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }

    @Override
    public void run(WebshopConfig webshopConfig, Environment environment) throws Exception {

        final UserDao userDao = new UserDao(hibernate.getSessionFactory());
        final AddressDao addressDao = new AddressDao(hibernate.getSessionFactory());
        final ProductDao productDao = new ProductDao(hibernate.getSessionFactory());
        final BasketDao basketDao = new BasketDao(hibernate.getSessionFactory());

        final UserResource userResource = new UserResource(userDao);
        final AddressResource addressResource = new AddressResource(addressDao);
        final RegisterResource registerResource = new RegisterResource(userDao, addressDao);
        final ProductResource productResource = new ProductResource(productDao);
        final BasketResource basketResource = new BasketResource(basketDao, productDao);

        setupAuth(environment,hibernate, userDao);

        environment.jersey().register(userResource);
        environment.jersey().register(addressResource);
        environment.jersey().register(registerResource);
        environment.jersey().register(productResource);
        environment.jersey().register(basketResource);

    }
}
