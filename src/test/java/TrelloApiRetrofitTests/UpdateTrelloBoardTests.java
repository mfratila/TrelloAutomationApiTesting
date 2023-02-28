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
import static RetrofitTestUtilities.BuildRequest.buildPUTRequest;
import static RetrofitTestUtilities.CleanupAfterTest.cleanupAfterTest;
import static RetrofitTestUtilities.PrepareActualResponse.getActualResponseData;
import static TestUtilities.CheckResponseIsValid.assertResponseValidity;
import static TestUtilities.PrepareExpectedResponse.getExpectedResponseData;
import static TestUtilities.TestAssertions.assertBoardData;
import static TestUtilities.TestDataProvider.createTrelloPojo;
import static TestUtilities.TestDataProvider.getBoardIdFromRetrofitResponse;

public class UpdateTrelloBoardTests extends RetrofitBaseTest {

    @Test
    public void testUpdateBoard(ITestContext context) throws Exception {

        ExtentTest testLogger = createTestLogger("Update Board by ID",
                "Test that verifies the functionality of the PUT method");
        TrelloBoard postPayload = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response<TrelloBoard> createNewBoard = buildPOSTRequest(postPayload).execute();
        String boardId = getBoardIdFromRetrofitResponse(createNewBoard.body());

        TrelloBoard updatedBoardPayload = createTrelloPojo(Constants.PUTRequest_PAYLOAD_PATH);
        Response<TrelloBoard> actualResponse = buildPUTRequest(boardId, updatedBoardPayload).execute();

        Map<String, String> actualBoardData = getActualResponseData(actualResponse);
        Map<String, String> expectedBoardData = getExpectedResponseData(Constants.PUTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualBoardData, expectedBoardData);
        logResults(testLogger, context, actualResponse);
        cleanupAfterTest(boardId);

    }
}
