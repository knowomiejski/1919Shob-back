import Model.Address;
import Model.Item;
import Model.User;
import Persistance.UserDao;
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

    private final HibernateBundle<WebshopConfig> hibernate = new HibernateBundle<WebshopConfig>(User.class, Item.class, Address.class) {
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

        final UserResource userResource = new UserResource(userDao);

        environment.jersey().register(userResource);
    }
}
