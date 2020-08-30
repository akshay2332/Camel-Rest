package registration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("user")
public class RegistrationReq {

    private String mobileNo;
    private String emailId;
    private String password;
    private String userId;
    private String firstName;
    private String lastName;


    @JsonCreator
    public RegistrationReq(@JsonProperty("number") String mobileNo, @JsonProperty("email") String emailId,
                   @JsonProperty("password") String password, @JsonProperty("id") String userId,
                   @JsonProperty("name") Map<String, String> name) {
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.password = password;
        this.userId = userId;
        if (name != null) {
            this.firstName = name.get("first");
            this.lastName = name.get("last");
        }
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean checkMandatoryParams() {
        return this.emailId == null || "".equalsIgnoreCase(this.emailId)
                || this.firstName == null || "".equalsIgnoreCase(this.firstName)
                || this.lastName == null || "".equalsIgnoreCase(this.lastName)
                || this.mobileNo == null || "".equalsIgnoreCase(this.mobileNo)
                || this.userId == null || "".equalsIgnoreCase(this.userId);
    }
}
