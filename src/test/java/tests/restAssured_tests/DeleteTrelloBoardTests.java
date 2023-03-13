package tests.restAssured_tests;

import io.qameta.allure.Description;
import tests.base_tests.RestAssuredBaseTest;
import utilities.Constants;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static utilities.restAssured_utilities.BuildRequest.*;
import static utilities.restAssured_utilities.SendRequest.*;
import static utilities.common_utilities.TestDataProvider.*;
import static utilities.common_utilities.CheckResponseIsValid.*;


public class DeleteTrelloBoardTests extends RestAssuredBaseTest {
    @Test
    @Description("Test that verifies the functionality of the DELETE method")
    public void deleteBoardById(ITestContext context) {

        ExtentTest testLogger = createTestLogger("Delete Board by ID",
                "Test that verifies the functionality of the DELETE method");
        TrelloBoard boardData = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response newBoard = sendPOSTRequest(buildRequestWithBody(requestSpec, boardData));
        String boardId = getBoardIdFromResponse(newBoard);

        Response actualResponse = sendDELETERequest(buildRequestWithPathParam(requestSpec,
                ID_PATH_PARAM, boardId));

        assertResponseValidity(actualResponse);
        logTestResult(context, testLogger);
    }
}
