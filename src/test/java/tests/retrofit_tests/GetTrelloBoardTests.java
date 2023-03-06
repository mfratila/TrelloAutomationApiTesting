package tests.retrofit_tests;

import utilities.Constants;
import tests.base_tests.RetrofitBaseTest;
import com.aventstack.extentreports.ExtentTest;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

import static utilities.retrofit_utilities.BuildRequest.*;
import static utilities.retrofit_utilities.CleanupAfterTest.cleanupAfterTest;
import static utilities.retrofit_utilities.PrepareActualResponse.getActualResponseData;
import static utilities.common_utilities.CheckResponseIsValid.assertResponseValidity;
import static utilities.common_utilities.TestAssertions.assertBoardData;
import static utilities.common_utilities.TestDataProvider.*;

public class GetTrelloBoardTests extends RetrofitBaseTest {
    @Test
    public void testGetAllBoards(ITestContext context) throws Exception {

        ExtentTest testLogger = createTestLogger("Get all Boards",
                "Test that verifies the functionality of the GET method");

        Response<List<TrelloBoard>> actualResponse = buildGETRequest().execute();

        assertResponseValidity(actualResponse.raw());
        logResultsForListResponse(testLogger, context, actualResponse);

    }

    @Test
    public void testGetBoardById(ITestContext context) throws Exception {

        ExtentTest testLogger = createTestLogger("Get Board by ID",
                "Test that verifies the functionality of the GET method when provided a specific ID as a Path Parameter");
        TrelloBoard postPayload = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response<TrelloBoard> createNewBoard = buildPOSTRequest(postPayload).execute();
        String boardId = getBoardIdFromRetrofitResponse(createNewBoard.body());

        Response<TrelloBoard> actualResponse = buildGETByIdRequest(boardId).execute();

        Map<String, String> actualResultsData = getActualResponseData(actualResponse);
        Map<String, String> expectedResultsData = getActualResponseData(actualResponse);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResultsData, expectedResultsData);
        cleanupAfterTest(boardId);
        logResults(testLogger, context, actualResponse);
    }
}
