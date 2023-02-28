package TrelloApiRestAssuredTests;

import Utility.RestAssuredBaseTest;
import Utility.Constants;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.Map;

import static RestAssuredTestUtilities.BuildRequest.*;
import static RestAssuredTestUtilities.SendRequest.*;
import static TestUtilities.TestDataProvider.*;
import static TestUtilities.CheckResponseIsValid.*;
import static RestAssuredTestUtilities.PrepareActualResponse.*;
import static TestUtilities.PrepareExpectedResponse.*;
import static TestUtilities.TestAssertions.*;
import static RestAssuredTestUtilities.CleanupAfterTest.*;

public class CreateTrelloBoardTests extends RestAssuredBaseTest {
    @Test
    public void createNewBoard(ITestContext context) throws JsonProcessingException {

        ExtentTest testLogger = createTestLogger("Create New Board",
                "Test that verifies the functionality of the POST method");

        TrelloBoard trelloBoard = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response actualResponse = sendPOSTRequest(buildRequestWithBody(requestSpec, trelloBoard));
        String boardId = getBoardIdFromResponse(actualResponse);

        Map<String, String> actualResponseData = getActualResponseData(actualResponse);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);
        logTestResult(context, testLogger);
        cleanupAfterTest(requestSpec, boardId);
    }
}
