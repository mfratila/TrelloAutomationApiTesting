package TrelloApiRetrofitTests;

import Utility.Constants;
import Utility.RetrofitBaseTest;
import com.aventstack.extentreports.ExtentTest;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import retrofit2.Response;

import static RetrofitTestUtilities.BuildRequest.buildDELETERequest;
import static RetrofitTestUtilities.BuildRequest.buildPOSTRequest;
import static TestUtilities.CheckResponseIsValid.assertResponseValidity;
import static TestUtilities.TestDataProvider.createTrelloPojo;
import static TestUtilities.TestDataProvider.getBoardIdFromRetrofitResponse;

public class DeleteTrelloBoardTests extends RetrofitBaseTest {
    @Test
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
