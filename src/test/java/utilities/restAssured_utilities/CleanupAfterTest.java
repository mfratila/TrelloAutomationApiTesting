package utilities.restAssured_utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static utilities.restAssured_utilities.BuildRequest.*;

public class CleanupAfterTest {
    public static void cleanupAfterTest(RequestSpecification requestSpec, String boardId) {
        Response response = SendRequest.sendDELETERequest(buildRequestWithPathParam(requestSpec, ID_PATH_PARAM, boardId));

        response.then().statusCode(200);
    }
}
