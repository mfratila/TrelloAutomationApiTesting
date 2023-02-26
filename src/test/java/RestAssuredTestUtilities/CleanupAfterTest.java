package RestAssuredTestUtilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static RestAssuredTestUtilities.SendRequest.*;
import static RestAssuredTestUtilities.BuildRequest.*;

public class CleanupAfterTest {
    public static void cleanupAfterTest(RequestSpecification requestSpec, String boardId) {
        Response response = sendDELETERequest(buildRequestWithPathParam(requestSpec, ID_PATH_PARAM, boardId));

        response.then().statusCode(200);
    }
}
