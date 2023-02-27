package TrelloApiOkHttpTests;

import Utility.Constants;
import Utility.OkHttpBaseTest;
import com.aventstack.extentreports.ExtentTest;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static OkHttpTestUtilities.BuildRequest.*;
import static OkHttpTestUtilities.CleanupAfterTest.cleanupAfterTest;
import static OkHttpTestUtilities.PrepareActualResponse.getActualResponseData;
import static OkHttpTestUtilities.SendRequest.sendRequest;
import static TestUtilities.CheckResponseIsValid.assertResponseValidity;
import static TestUtilities.PrepareExpectedResponse.getExpectedResponseData;
import static TestUtilities.TestAssertions.assertBoardData;
import static TestUtilities.TestDataProvider.createBodyFromBoardPojo;
import static TestUtilities.TestDataProvider.getBoardIdFromOkHttpResponse;

public class GetTrelloBoardTests extends OkHttpBaseTest {

    String boardId;
    String responseBodyAsString;

    @Test
    public void getAllBoards(ITestContext context) throws IOException {

        ExtentTest testLogger = createTestLogger("Get all Boards",
                "Test that verifies the functionality of the GET method");

        RequestBody postBodyPayload = createBodyFromBoardPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response createNewBoard = sendRequest(buildPOSTRequest(postBodyPayload));
        responseBodyAsString = createNewBoard.body().string();
        boardId = getBoardIdFromOkHttpResponse(responseBodyAsString);

        Response actualResponse = sendRequest(buildGETRequest());

        assertResponseValidity(actualResponse);
        logResults(testLogger, context, actualResponse);

    }

    @Test
    public void getBoardById(ITestContext context) throws IOException {

        ExtentTest testLogger = createTestLogger("Get Board by ID",
                "Test that verifies the functionality of the GET method when provided a specific ID as a Path Parameter");
        Response actualResponse = sendRequest(buildGETByIdRequest(boardId));

        Map<String, String> actualResponseData = getActualResponseData(responseBodyAsString);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);
        logResults(testLogger, context, actualResponse);
        cleanupAfterTest(responseBodyAsString);

    }
}
