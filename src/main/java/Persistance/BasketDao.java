package Persistance;

import Model.Basket;
import Model.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BasketDao extends AbstractDAO<Basket> {

    private String success = "Checkout completed! Have fun with your new guitar!";
    private String fail = "Checkout failed! Try again later.";

    public BasketDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void delete(Basket basket) {
        currentSession().delete(basket);
    }

    public String bulkdelete(Basket[] baskets) {
        try {
            for (Basket basket : baskets) {
                delete(basket);
            }
            return success;
        }
        catch (HibernateException e) {
            return fail;
        }
    }

    public String deleteUserBasket(User user) {
        try {
            Query<Basket> query = currentSession().createQuery("delete Basket b where b.userBasketID = :userID");
            query.setParameter("userID", user.getUserID());
            query.executeUpdate();
            return success;
        }
        catch (HibernateException e) {
            return fail;
        }
    }


    public void update(Basket basket) {
        currentSession().saveOrUpdate(basket);
    }

    public Basket insert(Basket basket) {
        System.out.println("persisting address: " + basket.toString());
        return persist(basket);
    }

    public Basket addToBasket(Basket basket) {
        Basket newbasket = addToSpecificBasket(basket);
        insert(newbasket);
        return newbasket;
    }

    public Basket addToSpecificBasket(Basket basket) {
        Query<Basket> query = currentSession().createQuery("from Basket b where b.userBasketID = :userID and b.productBasketID = :productID");
        query.setParameter("userID", basket.getUserBasketID());
        query.setParameter("productID", basket.getProductBasketID());
        System.err.println(query.list().toString());
        if (query.list().isEmpty()) {
            Basket nolistbasket = new Basket();
            nolistbasket.setUserBasketID(basket.getUserBasketID());
            nolistbasket.setProductBasketID(basket.getProductBasketID());
            nolistbasket.setAmount(1);
            return nolistbasket;
        } else {
            Basket listbasket = query.list().get(0);
            listbasket.setAmount(listbasket.getAmount() + 1);
            return listbasket;
        }

    }

    public List<Basket> getProducts(int userID) {
        Query<Basket> query = currentSession().createQuery("from Basket b where b.userBasketID = :userID");
        query.setParameter("userID", userID);

        return query.list();
    }
}
