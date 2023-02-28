package TestUtilities;

import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.Assert;

public class CheckResponseIsValid {

    public static final int OK_STATUS_CODE = 200;

    public static void assertResponseValidity(Response response) {
        response.then().assertThat().statusCode(OK_STATUS_CODE);
    }

    public static void assertResponseValidity(okhttp3.Response response) {
        Assert.assertEquals(OK_STATUS_CODE, response.code());
    }

    public static void assertResponseValidity(retrofit2.Response<TrelloBoard> response) {
        Assert.assertEquals(OK_STATUS_CODE, response.code());
    }

}
