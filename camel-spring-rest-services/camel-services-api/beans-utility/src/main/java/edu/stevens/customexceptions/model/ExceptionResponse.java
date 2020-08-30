package edu.stevens.customexceptions.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import edu.stevens.response.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("exception")
public class ExceptionResponse {
    private Response response;

    @JsonCreator
    public ExceptionResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
