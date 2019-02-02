package Model;

import javax.persistence.*;

@Entity
@Table(name = "1919ShopUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserID")
    int userID;
    @Column(name = "Email")
    String email;
    @Column(name = "Password")
    String passwd;
//    @OneToOne
//    Address address;

    public User(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public User() {
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
}
