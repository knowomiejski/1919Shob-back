import Resource.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebshopApplication extends Application<WebshopConfig> {

    public static void main(String[] args) throws Exception {
        new WebshopApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<WebshopConfig> bootstrap){

    }

    @Override
    public void run(WebshopConfig webshopConfig, Environment environment) throws Exception {
        final UserResource userResource = new UserResource(webshopConfig.getNameUser(), webshopConfig.getDefaultName(),webshopConfig.getPasswd(), webshopConfig.getEmail());
        environment.jersey().register(userResource);
    }
}
