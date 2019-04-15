package Model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    @JsonProperty
    private int productID;

    @Column(name = "Name")
    @JsonProperty
    private String name;

    @Column(name = "Description")
    @JsonProperty
    private String description;

    @Column(name = "Price")
    @JsonProperty
    private double price;

    @Column(name = "Picture")
    @JsonProperty
    private String picture;

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }

}
