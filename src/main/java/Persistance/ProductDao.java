package Persistance;

import Model.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class ProductDao extends AbstractDAO<Product> {

    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Product findById(int id) {
        return currentSession().get(Product.class, id);
    }

    public void delete(Product product) {
        currentSession().delete(product);
    }

    public void update(Product product) {
        currentSession().saveOrUpdate(product);
    }

    public Product insert(Product product) {
        System.out.println("persisting user: " + product.toString());
        return persist(product);
    }
}
