package utilities.restAssured_utilities;

import io.restassured.specification.RequestSpecification;
import models.TrelloBoard;

import static io.restassured.RestAssured.given;

public class BuildRequest {

    public final static String ID_PATH_PARAM = "id";

    public static RequestSpecification buildRequest(RequestSpecification requestSpec) {
        RequestSpecification request = given()
                .spec(requestSpec)
                .contentType("application/json");

        return request;
    }

    public static RequestSpecification buildRequestWithBody(RequestSpecification requestSpec, TrelloBoard body) {

        return buildRequest(requestSpec).body(body);
    }

    public static RequestSpecification buildRequestWithPathParam(RequestSpecification requestSpec,
                                                          String key, String value) {

        return buildRequest(requestSpec).pathParam(key, value);
    }

    public static RequestSpecification buildRequestWithBodyAndPathParam(RequestSpecification requestSpec,
                                                                 TrelloBoard body, String key, String value) {

        return buildRequest(requestSpec).pathParam(key, value).body(body);
    }

}
