package Persistance;

import Model.Basket;
import Model.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BasketDao extends AbstractDAO<Basket> {

    public BasketDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void delete(Basket basket) {
        currentSession().delete(basket);
    }

    public void update(Basket basket) {
        currentSession().saveOrUpdate(basket);
    }

    public Basket insert(Basket basket) {
        System.out.println("persisting address: " + basket.toString());
        return persist(basket);
    }

    public List<Basket> getProducts(User user) {
        Query<Basket> query = currentSession().createQuery("from Basket b where b.userBasketID = :userID");
        query.setParameter("userID", user.getUserID());
        return query.list();
    }
}
