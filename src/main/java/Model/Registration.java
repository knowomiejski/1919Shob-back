package Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Registration {
    String email;
    String passwd;
    String province;
    String city;
    String street;
    String housenr;

    @JsonCreator
    public Registration(@JsonProperty("email") String email,
                        @JsonProperty("passwd") String passwd,
                        @JsonProperty("province") String province,
                        @JsonProperty("city") String city,
                        @JsonProperty("street") String street,
                        @JsonProperty("housenr") String housenr) {
        this.email = email;
        this.passwd = passwd;
        this.province = province;
        this.city = city;
        this.street = street;
        this.housenr = housenr;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHousenr() {
        return housenr;
    }
}
