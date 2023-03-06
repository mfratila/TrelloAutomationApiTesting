package utilities.restAssured_utilities;

import utilities.Constants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SendRequest {



    public static Response sendPOSTRequest(RequestSpecification requestSpec) {

        return requestSpec.when().post(Constants.POST_ENDPOINT);
    }

    public static Response sendGETRequest(RequestSpecification requestSpec) {

        return requestSpec.when().get(utilities.Constants.GET_ALL_ENDPOINT);
    }

    public static Response sendGETByIdRequest(RequestSpecification requestSpec) {

        return requestSpec.when().get(utilities.Constants.GET_BY_ID_ENDPOINT);
    }

    public static Response sendDELETERequest(RequestSpecification requestSpec) {

        return requestSpec.when().delete(utilities.Constants.DELETE_ENDPOINT);
    }

    public static Response sendPUTRequest(RequestSpecification requestSpec) {

        return requestSpec.when().put(utilities.Constants.PUT_ENDPOINT);
    }
}
