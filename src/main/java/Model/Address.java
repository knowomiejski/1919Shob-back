package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @Column(name = "AddressID")
    private int addressID;

    @Column(name = "Province")
    @JsonProperty
    private String province;

    @Column(name = "City")
    @JsonProperty
    private String city;

    @Column(name = "Street")
    @JsonProperty
    private String street;

    @Column(name = "HouseNr")
    @JsonProperty
    private String housenr;

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", housenr='" + housenr + '\'' +
                '}';
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHousenr() {
        return housenr;
    }

    public void setHousenr(String housenr) {
        this.housenr = housenr;
    }
}
