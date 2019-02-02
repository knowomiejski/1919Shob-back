import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class WebshopConfig extends Configuration {
    @NotEmpty
    private String productTemplate;

    @NotEmpty
    private String defaultProduct = "";

    @NotEmpty
    private String nameUser;

    @NotEmpty
    private String defaultName = "Mah Dude";

    @NotEmpty
    private String passwd;

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty
    private String email;

    @JsonProperty
    public String getProductTemplate() {
        return productTemplate;
    }

    @JsonProperty
    public void setProductTemplate(String productTemplate) {
        this.productTemplate = productTemplate;
    }

    @JsonProperty
    public String getDefaultProduct() {
        return defaultProduct;
    }

    @JsonProperty
    public void setDefaultProduct(String defaultProduct) {
        this.defaultProduct = defaultProduct;
    }

    @JsonProperty
    public String getNameUser() {
        return nameUser;
    }

    @JsonProperty
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
