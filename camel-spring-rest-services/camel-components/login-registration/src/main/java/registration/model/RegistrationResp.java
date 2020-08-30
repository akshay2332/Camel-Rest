package registration.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import edu.stevens.response.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("user")
public class RegistrationResp {
    private Response response;

    @JsonCreator
    public RegistrationResp(Response response) {
        this.response = response;
    }


    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
