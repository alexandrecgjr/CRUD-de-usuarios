package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserRequest;

import static io.restassured.RestAssured.given;

public class UserClient {
    
    private static final String BASE_URL = "https://reqres.in";
    private static final String API_KEY = "reqres-free-v1";
    
    public Response createUser(UserRequest userRequest) {
        return given()
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .post(BASE_URL + "/api/users")
                .then()
                .extract()
                .response();
    }
    
    public Response getUserById(int userId) {
        return given()
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + "/api/users/" + userId)
                .then()
                .extract()
                .response();
    }
    
    public Response updateUser(int userId, UserRequest userRequest) {
        return given()
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .put(BASE_URL + "/api/users/" + userId)
                .then()
                .extract()
                .response();
    }
    
    public Response deleteUser(int userId) {
        return given()
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .when()
                .delete(BASE_URL + "/api/users/" + userId)
                .then()
                .extract()
                .response();
    }
    
    public Response getAllUsers() {
        return given()
                .header("x-api-key", API_KEY)
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + "/api/users")
                .then()
                .extract()
                .response();
    }
} 