package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Basket")
public class Basket {

    @Id
    @Column(name = "UserBasketID")
    @JsonProperty
    private int userBasketID;

    @Column(name = "ProductBasketID")
    @JsonProperty
    private int productBasketID;

    @Override
    public String toString() {
        return "Basket{" +
                "userBasketID=" + userBasketID +
                ", productBasketID=" + productBasketID +
                '}';
    }

    public int getUserBasketID() {
        return userBasketID;
    }

    public void setUserBasketID(int userBasketID) {
        this.userBasketID = userBasketID;
    }

    public int getProductBasketID() {
        return productBasketID;
    }

    public void setProductBasketID(int productBasketID) {
        this.productBasketID = productBasketID;
    }
}
