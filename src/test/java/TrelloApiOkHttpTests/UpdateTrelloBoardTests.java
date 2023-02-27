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

import static OkHttpTestUtilities.BuildRequest.buildPOSTRequest;
import static OkHttpTestUtilities.BuildRequest.buildPUTRequest;
import static OkHttpTestUtilities.CleanupAfterTest.cleanupAfterTest;
import static OkHttpTestUtilities.PrepareActualResponse.getActualResponseData;
import static OkHttpTestUtilities.SendRequest.sendRequest;
import static TestUtilities.CheckResponseIsValid.assertResponseValidity;
import static TestUtilities.PrepareExpectedResponse.getExpectedResponseData;
import static TestUtilities.TestAssertions.assertBoardData;
import static TestUtilities.TestDataProvider.*;

public class UpdateTrelloBoardTests extends OkHttpBaseTest {

    @Test
    public void updateBoardById(ITestContext context) throws IOException {

        ExtentTest testLogger = createTestLogger("Update Board by ID",
                "Test that verifies the functionality of the PUT method");

        RequestBody postBodyPayload = createBodyFromBoardPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response createNewBoard = sendRequest(buildPOSTRequest(postBodyPayload));
        String boardId = getBoardIdFromOkHttpResponse(createNewBoard.body().string());

        RequestBody updatedPayload = createBodyFromBoardPojo(Constants.PUTRequest_PAYLOAD_PATH);
        Response actualResponse = sendRequest(buildPUTRequest(boardId, updatedPayload));
        Response bufferedResponse = getBufferedResponse(actualResponse);
        String actualResponseBodyAsString = bufferedResponse.body().string();

        Map<String, String> actualResponseData = getActualResponseData(actualResponseBodyAsString);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.PUTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);
        logResults(testLogger, context, bufferedResponse);
        cleanupAfterTest(actualResponseBodyAsString);
    }
}
