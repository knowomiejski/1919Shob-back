package Resource;

import Model.Basket;
import Model.Product;
import Model.User;
import Persistance.BasketDao;
import Persistance.ProductDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/cart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BasketResource {
    private final BasketDao basketDao;
    private final ProductDao productDao;

    public BasketResource(BasketDao basketDao, ProductDao productDao) {
        this.basketDao = basketDao;
        this.productDao = productDao;
    }

    @POST
    @UnitOfWork
    public Basket add(@Auth User user, Basket basket) {
        return basketDao.insert(basket);
    }

    @GET
    @UnitOfWork
    public List<Product> get(@Auth User user) {
        List<Basket> productIDs = basketDao.getProducts(user);
        return productDao.getListByIDs(productIDs);
    }
}
