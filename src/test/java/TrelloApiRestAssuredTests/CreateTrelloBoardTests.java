package TrelloApiRestAssuredTests;

import Utility.BaseTest;
import Utility.Constants;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static TestUtilities.BuildRequest.*;
import static TestUtilities.SendRequest.*;
import static TestUtilities.TestDataProvider.*;
import static TestUtilities.CheckResponseIsValid.*;
import static TestUtilities.CleanupAfterTest.*;

public class CreateTrelloBoardTests extends BaseTest {
    @Test
    public void createNewBoard(ITestContext context) {

        ExtentTest testLogger = createTestLogger("Create New Board",
                "Test that verifies the functionality of the POST method");
        TrelloBoard trelloBoard = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response actualResponse = sendPOSTRequest(buildRequestWithBody(requestSpec, trelloBoard));

        assertResponseValidity(actualResponse);
        logTestResult(context, testLogger);

        String boardId = getBoardIdFromResponse(actualResponse);
        cleanupAfterTest(requestSpec, boardId);
    }
}
