package tests.okHttp_tests;

import io.qameta.allure.Description;
import utilities.Constants;
import tests.base_tests.OkHttpBaseTest;
import com.aventstack.extentreports.ExtentTest;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static utilities.okHttp_utilities.BuildRequest.buildPOSTRequest;
import static utilities.okHttp_utilities.BuildRequest.buildPUTRequest;
import static utilities.okHttp_utilities.CleanupAfterTest.cleanupAfterTest;
import static utilities.okHttp_utilities.PrepareActualResponse.getActualResponseData;
import static utilities.okHttp_utilities.SendRequest.sendRequest;
import static utilities.common_utilities.CheckResponseIsValid.assertResponseValidity;
import static utilities.common_utilities.PrepareExpectedResponse.getExpectedResponseData;
import static utilities.common_utilities.TestAssertions.assertBoardData;
import static utilities.common_utilities.TestDataProvider.*;

public class UpdateTrelloBoardTests extends OkHttpBaseTest {

    @Test
    @Description("Test that verifies the functionality of the PUT method")
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
