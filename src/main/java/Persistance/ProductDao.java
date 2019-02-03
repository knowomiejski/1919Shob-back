package Persistance;

import Model.Basket;
import Model.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDao extends AbstractDAO<Product> {

    public ProductDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Product findById(int id) {
        return currentSession().get(Product.class, id);
    }

    public boolean delete(Product product) {
        try {
            currentSession().delete(product);
            return true;
        } catch (Exception deleteException) {
            return false;
        }
    }

    public void update(Product product) {
        currentSession().saveOrUpdate(product);
    }

    public Product insert(Product product) {
        System.out.println("persisting user: " + product.toString());
        return persist(product);
    }

    public List<Product> getAllProducts() {
        Query<Product> query = currentSession().createQuery("from Product product", Product.class);
        return query.list();
    }

    public List<Product> getListByIDs(List<Basket> list) {
        List<Product> productsInBasket = new ArrayList<>();
        for (Basket basket : list) {
            int id = basket.getProductBasketID();
            Query<Product> query = currentSession().createQuery("from Product p where p.productID = :productID");
            query.setParameter("productID", id);
            productsInBasket.add(query.list().get(0));
        }
        return productsInBasket;
    }
}
