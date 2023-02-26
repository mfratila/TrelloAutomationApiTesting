package TrelloApiTests;

import Utility.AllureLogger;
import Utility.BaseTest;
import Utility.Constants;
import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.annotations.Test;

import static TestUtilities.BuildRequest.*;
import static TestUtilities.SendRequest.*;
import static TestUtilities.TestDataProvider.*;
import static TestUtilities.CheckResponseIsValid.*;


public class CreateTrelloBoardTests extends BaseTest {
    @Test
    public void createNewBoard() {
        TrelloBoard trelloBoard = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response actualResponse = sendPOSTRequest(buildRequestWithBody(requestSpec, trelloBoard));

        //Verify the response code
        AllureLogger.logToAllure("Asserting the response if the status code returned is 200");
        assertResponseValidity(actualResponse);

    }
}
