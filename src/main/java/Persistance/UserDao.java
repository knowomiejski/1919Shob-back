package Persistance;

import Model.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class UserDao extends AbstractDAO<User> {

    public UserDao(SessionFactory factory) {
        super(factory);
    }

    /* Probably don't need this, if you do change it so no plagiaat

    public List<User> getAll() {
        CriteriaBuilder builder = currentSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        return currentSession().createQuery(criteria).getResultList();
    }
    */

    public User findById(int id) {
        return currentSession().get(User.class, id);
    }

    public void delete(User user) {
        currentSession().delete(user);
    }

    public void update(User user) {
        currentSession().saveOrUpdate(user);
    }

    public User insert(User user) {
        System.out.println("persisting user: " + user.toString());
        return persist(user);
    }
}
