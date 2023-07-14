package API.POJO.steps;

import API.POJO.BodyArgs;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static utils.EnvProperties.API_URL;

public class BaseApiSteps {
    public Response postRequest(String username, String token, BodyArgs bodyArgs){
        return RestAssured.given()
                .auth().basic(username, token)
                .body(bodyArgs)
                .when()
                .post(API_URL);
    }
}
