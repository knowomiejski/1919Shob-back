package Model;

import org.hibernate.validator.constraints.Length;

public class User {

    @Length(max=10)
    private String username;

    @Length(max = 15)
    private String passwd;

    private String email;

    private String province;

    private String city;

    private int housenr;

}
