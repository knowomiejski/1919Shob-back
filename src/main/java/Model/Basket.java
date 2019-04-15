package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Basket")
public class Basket implements Serializable {

    @Id
    @Column(name = "UserBasketID")
    @JsonProperty
    private int userBasketID;

    @Id
    @Column(name = "ProductBasketID")
    @JsonProperty
    private int productBasketID;


    @Column(name = "Amount")
    @JsonProperty
    private int amount = -1;

    @Override
    public String toString() {
        return "Basket{" +
                "userBasketID=" + userBasketID +
                ", productBasketID=" + productBasketID +
                ", amount=" + amount +
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
