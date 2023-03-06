package tests.retrofit_tests;

import utilities.Constants;
import tests.base_tests.RetrofitBaseTest;
import com.aventstack.extentreports.ExtentTest;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.Map;

import static utilities.retrofit_utilities.BuildRequest.buildPOSTRequest;
import static utilities.retrofit_utilities.CleanupAfterTest.cleanupAfterTest;
import static utilities.retrofit_utilities.PrepareActualResponse.getActualResponseData;
import static utilities.common_utilities.PrepareExpectedResponse.getExpectedResponseData;
import static utilities.common_utilities.TestAssertions.assertBoardData;
import static utilities.common_utilities.TestDataProvider.createTrelloPojo;
import static utilities.common_utilities.TestDataProvider.getBoardIdFromRetrofitResponse;
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
