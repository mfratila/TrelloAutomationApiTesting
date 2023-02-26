package TestUtilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SendRequest {

    public final static String POST_ENDPOINT = "/boards/";
    public final static String GET_ALL_ENDPOINT = "/members/me/boards";
    public final static String GET_BY_ID_ENDPOINT = "/boards/{id}";
    public final static String DELETE_ENDPOINT = "/boards/{id}";
    public final static String PUT_ENDPOINT = "/boards/{id}";

    public static Response sendPOSTRequest(RequestSpecification requestSpec) {

        return requestSpec.when().post(POST_ENDPOINT);
    }

    public static Response sendGETRequest(RequestSpecification requestSpec) {

        return requestSpec.when().get(GET_ALL_ENDPOINT);
    }

    public static Response sendGETByIdRequest(RequestSpecification requestSpec) {

        return requestSpec.when().get(GET_BY_ID_ENDPOINT);
    }

    public static Response sendDELETERequest(RequestSpecification requestSpec) {

        return requestSpec.when().delete(DELETE_ENDPOINT);
    }

    public static Response sendPUTRequest(RequestSpecification requestSpec) {

        return requestSpec.when().put(PUT_ENDPOINT);
    }
}
