package Model;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "AddressID")
    int AddressID;
    @Column(name = "Province")
    String province;
    @Column(name = "City")
    String city;
    @Column(name = "Street")
    String street;
    @Column(name = "HouseNr")
    String housenr;

    public Address(String province, String city, String street, String housenr) {
        this.province = province;
        this.city = city;
        this.street = street;
        this.housenr = housenr;
    }

    public Address() {
    }

}
