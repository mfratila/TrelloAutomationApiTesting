package tests.restAssured_tests;

import tests.base_tests.RestAssuredBaseTest;

import utilities.Constants;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.Map;

import static utilities.restAssured_utilities.BuildRequest.*;
import static utilities.restAssured_utilities.PrepareActualResponse.*;
import static utilities.common_utilities.PrepareExpectedResponse.*;
import static utilities.restAssured_utilities.SendRequest.*;
import static utilities.common_utilities.TestAssertions.*;
import static utilities.common_utilities.TestDataProvider.*;
import static utilities.common_utilities.CheckResponseIsValid.*;
import static utilities.restAssured_utilities.CleanupAfterTest.*;


public class UpdateTrelloBoardTests extends RestAssuredBaseTest {

    @Test
    public void updateBoardById(ITestContext context) throws JsonProcessingException {

        ExtentTest testLogger = createTestLogger("Update Board by ID",
                        "Test that verifies the functionality of the PUT method");

        TrelloBoard boardData = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response createNewBoard = sendPOSTRequest(buildRequestWithBody(requestSpec, boardData));
        String boardId = getBoardIdFromResponse(createNewBoard);

        TrelloBoard updatedBoardData = createTrelloPojo(Constants.PUTRequest_PAYLOAD_PATH);

        Response actualResponse = sendPUTRequest(buildRequestWithBodyAndPathParam(requestSpec,
                updatedBoardData, ID_PATH_PARAM, boardId));

        Map<String, String> actualResponseData = getActualResponseData(actualResponse);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.PUTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);

        logTestResult(context, testLogger);

        cleanupAfterTest(requestSpec, boardId);
    }
}
