package tests.retrofit_tests;

import io.qameta.allure.Description;
import utilities.Constants;
import tests.base_tests.RetrofitBaseTest;
import com.aventstack.extentreports.ExtentTest;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.Map;

import static utilities.retrofit_utilities.BuildRequest.buildPOSTRequest;
import static utilities.retrofit_utilities.BuildRequest.buildPUTRequest;
import static utilities.retrofit_utilities.CleanupAfterTest.cleanupAfterTest;
import static utilities.retrofit_utilities.PrepareActualResponse.getActualResponseData;
import static utilities.common_utilities.CheckResponseIsValid.assertResponseValidity;
import static utilities.common_utilities.PrepareExpectedResponse.getExpectedResponseData;
import static utilities.common_utilities.TestAssertions.assertBoardData;
import static utilities.common_utilities.TestDataProvider.createTrelloPojo;
import static utilities.common_utilities.TestDataProvider.getBoardIdFromRetrofitResponse;

public class UpdateTrelloBoardTests extends RetrofitBaseTest {

    @Test
    @Description("Test that verifies the functionality of the PUT method")
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
