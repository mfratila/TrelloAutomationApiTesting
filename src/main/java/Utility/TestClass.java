package Utility;


import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class TestClass extends FrameworkUtilities {
    public static void main(String[] args) {

        Response response = given()
                .baseUri("https://api.trello.com/1/")
                .pathParam("id", "MLJoJ1U8")
                .queryParam("key", "bede52f2bd2294db6438aa654bc8065f")
                .queryParam("token", "ATTAe0488b24db196d641dda9d08d99210ebcb608f9bba907c0da1c85a0443ed1e5d8AD54640")
                .contentType("application/json")
                .when()
                            .get("boards/{id}");

        response.then().log().body();
    }
}
