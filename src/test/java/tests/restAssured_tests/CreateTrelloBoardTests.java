package tests.restAssured_tests;

import io.qameta.allure.Description;
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
import static utilities.restAssured_utilities.SendRequest.*;
import static utilities.common_utilities.TestDataProvider.*;
import static utilities.common_utilities.CheckResponseIsValid.*;
import static utilities.restAssured_utilities.PrepareActualResponse.*;
import static utilities.common_utilities.PrepareExpectedResponse.*;
import static utilities.common_utilities.TestAssertions.*;
import static utilities.restAssured_utilities.CleanupAfterTest.*;

public class CreateTrelloBoardTests extends RestAssuredBaseTest {
    @Test
    @Description("Test that verifies the functionality of the POST method")
    public void createNewBoard(ITestContext context) throws JsonProcessingException {

        ExtentTest testLogger = createTestLogger("Create New Board",
                "Test that verifies the functionality of the POST method");

        TrelloBoard trelloBoard = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response actualResponse = sendPOSTRequest(buildRequestWithBody(requestSpec, trelloBoard));
        assertResponseValidity(actualResponse);
        String boardId = getBoardIdFromResponse(actualResponse);

        Map<String, String> actualResponseData = getActualResponseData(actualResponse);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertBoardData(actualResponseData, expectedResponseData);
        logTestResult(context, testLogger);
        cleanupAfterTest(requestSpec, boardId);
    }
}
