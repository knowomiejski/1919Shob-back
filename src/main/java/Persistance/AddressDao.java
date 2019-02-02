package Persistance;

import Model.Address;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class AddressDao extends AbstractDAO<Address> {

    public AddressDao(SessionFactory factory) {
        super(factory);
    }

    public Address findById(int id) {
        return currentSession().get(Address.class, id);
    }

    public void delete(Address address) {
        currentSession().delete(address);
    }

    public void update(Address address) {
        currentSession().saveOrUpdate(address);
    }

    public Address insert(Address address) {
        System.out.println("persisting address: " + address.toString());
        return persist(address);
    }
}
