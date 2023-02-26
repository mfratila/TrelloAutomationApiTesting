package TrelloApiTests;

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


public class DeleteTrelloBoardTests extends BaseTest {
    @Test
    public void deleteBoardById(ITestContext context) {

        ExtentTest testLogger = createTestLogger("Delete Board by ID",
                "Test that verifies the functionality of the DELETE method");
        TrelloBoard boardData = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response newBoard = sendPOSTRequest(buildRequestWithBody(requestSpec, boardData));
        String boardId = getBoardIdFromResponse(newBoard);

        Response actualResponse = sendDELETERequest(buildRequestWithPathParam(requestSpec,
                ID_PATH_PARAM, boardId));

        assertResponseValidity(actualResponse);
        logTestResult(context, testLogger);
    }
}
