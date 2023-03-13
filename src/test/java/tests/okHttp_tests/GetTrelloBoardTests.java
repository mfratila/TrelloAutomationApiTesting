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

import static utilities.okHttp_utilities.BuildRequest.*;
import static utilities.okHttp_utilities.CleanupAfterTest.cleanupAfterTest;
import static utilities.okHttp_utilities.PrepareActualResponse.getActualResponseData;
import static utilities.okHttp_utilities.SendRequest.sendRequest;
import static utilities.common_utilities.CheckResponseIsValid.assertResponseValidity;
import static utilities.common_utilities.PrepareExpectedResponse.getExpectedResponseData;
import static utilities.common_utilities.TestAssertions.assertBoardData;
import static utilities.common_utilities.TestDataProvider.createBodyFromBoardPojo;
import static utilities.common_utilities.TestDataProvider.getBoardIdFromOkHttpResponse;

public class GetTrelloBoardTests extends OkHttpBaseTest {

    String boardId;
    String responseBodyAsString;

    @Test
    @Description("Test that verifies the functionality of the GET method")
    public void getAllBoards(ITestContext context) throws IOException {

        ExtentTest testLogger = createTestLogger("Get all Boards",
                "Test that verifies the functionality of the GET method");

        RequestBody postBodyPayload = createBodyFromBoardPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response createNewBoard = sendRequest(buildPOSTRequest(postBodyPayload));
        responseBodyAsString = createNewBoard.body().string();
        boardId = getBoardIdFromOkHttpResponse(responseBodyAsString);

        Response actualResponse = sendRequest(buildGETRequest());

        assertResponseValidity(actualResponse);
        logResults(testLogger, context, actualResponse);

    }

    @Test
    @Description("Test that verifies the functionality of the GET method when provided a specific ID as a Path Parameter")
    public void getBoardById(ITestContext context) throws IOException {

        ExtentTest testLogger = createTestLogger("Get Board by ID",
                "Test that verifies the functionality of the GET method when provided a specific ID as a Path Parameter");
        Response actualResponse = sendRequest(buildGETByIdRequest(boardId));

        Map<String, String> actualResponseData = getActualResponseData(responseBodyAsString);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);
        logResults(testLogger, context, actualResponse);
        cleanupAfterTest(responseBodyAsString);

    }
}
