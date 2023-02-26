package TestUtilities;

import io.restassured.response.Response;

public class CheckResponseIsValid {

    public static void assertResponseValidity(Response response) {
        response.then().assertThat().statusCode(200);
    }
}
