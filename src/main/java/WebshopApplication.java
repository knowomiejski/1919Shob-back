import Model.Address;
import Model.Product;
import Model.User;
import Persistance.AddressDao;
import Persistance.ProductDao;
import Persistance.UserDao;
import Resource.AddressResource;
import Resource.ProductResource;
import Resource.RegisterResource;
import Resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebshopApplication extends Application<WebshopConfig> {

    public static void main(String[] args) throws Exception {
        new WebshopApplication().run(args);
    }

    private final HibernateBundle<WebshopConfig> hibernate = new HibernateBundle<WebshopConfig>(User.class, Address.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(WebshopConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };


    @Override
    public void initialize(Bootstrap<WebshopConfig> bootstrap){
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(WebshopConfig webshopConfig, Environment environment) throws Exception {

        final UserDao userDao = new UserDao(hibernate.getSessionFactory());
        final AddressDao addressDao = new AddressDao(hibernate.getSessionFactory());
        final ProductDao productDao = new ProductDao(hibernate.getSessionFactory());

        final UserResource userResource = new UserResource(userDao);
        final AddressResource addressResource = new AddressResource(addressDao);
        final RegisterResource registerResource = new RegisterResource(userDao, addressDao);
        final ProductResource productResource = new ProductResource(productDao);
        environment.jersey().register(userResource);
        environment.jersey().register(addressResource);
        environment.jersey().register(registerResource);
    }
}
