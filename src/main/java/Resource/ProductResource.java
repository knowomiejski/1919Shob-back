package Resource;

import Model.Product;
import Persistance.ProductDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private final ProductDao productDao;

    public ProductResource(ProductDao productDao) {
        this.productDao = productDao;
    }

    @POST
    @UnitOfWork
    @RolesAllowed("Admin")
    public Product add(Product product) {
        System.out.println("got good request");
        return productDao.insert(product);
    }

    @DELETE
    @UnitOfWork
    @RolesAllowed("Admin")
    public boolean delete(Product product){
        return productDao.delete(product);
    }

    @GET
    @UnitOfWork
    public List<Product> getAll() {
        return productDao.getAllProducts();
    }

}
