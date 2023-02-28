package TrelloApiRetrofitTests;

import Utility.Constants;
import Utility.RetrofitBaseTest;
import com.aventstack.extentreports.ExtentTest;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.Map;

import static RetrofitTestUtilities.BuildRequest.buildPOSTRequest;
import static RetrofitTestUtilities.CleanupAfterTest.cleanupAfterTest;
import static RetrofitTestUtilities.PrepareActualResponse.getActualResponseData;
import static TestUtilities.PrepareExpectedResponse.getExpectedResponseData;
import static TestUtilities.TestAssertions.assertBoardData;
import static TestUtilities.TestDataProvider.createTrelloPojo;
import static TestUtilities.TestDataProvider.getBoardIdFromRetrofitResponse;
import static org.testng.Assert.assertTrue;

public class CreateTrelloBoardTests extends RetrofitBaseTest {
    @Test
    public void testCreateBoard(ITestContext context) throws Exception {

        ExtentTest testLogger = createTestLogger("Create New Board",
                "Test that verifies the functionality of the POST method");
        TrelloBoard postPayload = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response<TrelloBoard> actualResponse = buildPOSTRequest(postPayload).execute();
        String boardId = getBoardIdFromRetrofitResponse(actualResponse.body());

        Map<String, String> actualResponseData = getActualResponseData(actualResponse);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertTrue(actualResponse.isSuccessful());
        assertBoardData(actualResponseData, expectedResponseData);
        logResults(testLogger, context, actualResponse);
        cleanupAfterTest(boardId);
    }
}
