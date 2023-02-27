package TrelloApiOkHttpTests;

import Utility.Constants;
import Utility.OkHttpBaseTest;
import com.aventstack.extentreports.ExtentTest;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;

import static OkHttpTestUtilities.BuildRequest.buildDELETERequest;
import static OkHttpTestUtilities.BuildRequest.buildPOSTRequest;
import static OkHttpTestUtilities.SendRequest.sendRequest;
import static TestUtilities.CheckResponseIsValid.assertResponseValidity;
import static TestUtilities.TestDataProvider.createBodyFromBoardPojo;
import static TestUtilities.TestDataProvider.getBoardIdFromOkHttpResponse;

public class DeleteTrelloBoardTests extends OkHttpBaseTest {
    @Test
    public void deleteBoardById(ITestContext context) throws IOException {

        ExtentTest testLogger = createTestLogger("Delete Board by ID",
                "Test that verifies the functionality of the DELETE method");

        RequestBody postBodyPayload = createBodyFromBoardPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response createNewBoard = sendRequest(buildPOSTRequest(postBodyPayload));

        String boardId = getBoardIdFromOkHttpResponse(createNewBoard.body().string());

        Response actualResponse = sendRequest(buildDELETERequest(boardId));
        Response bufferedResponse = getBufferedResponse(actualResponse);

        assertResponseValidity(bufferedResponse);
        logResults(testLogger, context, actualResponse);

    }
}