package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.security.Principal;

@Entity
public class User implements Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    @JsonProperty
    private int userID;

    @Column(name = "Email")
    @JsonProperty
    private String email;

    @Column(name = "Password")
    @JsonProperty
    @JsonIgnore
    private String password;

    @JsonIgnore
    @Transient
    private String[] roles;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    @JsonIgnore
    public String getName() {
        return email;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String email) {
        this.email = email;
    }

    @JsonIgnore

    public String getPassword() {
        return password;
    }
    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public boolean hasRole(String roleName) {
        if (roles != null) {
            for (String role : roles) {
                if (roleName.equals(role)) {
                    return true;
                }
            }
        }

        return false;
    }

}
