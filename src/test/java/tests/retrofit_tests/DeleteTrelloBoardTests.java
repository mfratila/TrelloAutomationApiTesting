package tests.retrofit_tests;

import io.qameta.allure.Description;
import utilities.Constants;
import tests.base_tests.RetrofitBaseTest;
import com.aventstack.extentreports.ExtentTest;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import retrofit2.Response;

import static utilities.retrofit_utilities.BuildRequest.buildDELETERequest;
import static utilities.retrofit_utilities.BuildRequest.buildPOSTRequest;
import static utilities.common_utilities.CheckResponseIsValid.assertResponseValidity;
import static utilities.common_utilities.TestDataProvider.createTrelloPojo;
import static utilities.common_utilities.TestDataProvider.getBoardIdFromRetrofitResponse;

public class DeleteTrelloBoardTests extends RetrofitBaseTest {
    @Test
    @Description("Test that verifies the functionality of the DELETE method")
    public void testDeleteBoard(ITestContext context) throws Exception {

        ExtentTest testLogger = createTestLogger("Delete Board by ID",
                "Test that verifies the functionality of the DELETE method");
        TrelloBoard postPayload = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response<TrelloBoard> createNewBoard = buildPOSTRequest(postPayload).execute();
        String boardId = getBoardIdFromRetrofitResponse(createNewBoard.body());

        Response<Void> actualResponse = buildDELETERequest(boardId).execute();

        assertResponseValidity(actualResponse.raw());
        logResultsForVoidResponse(testLogger, context, actualResponse);

    }
}
