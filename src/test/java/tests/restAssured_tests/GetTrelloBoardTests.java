package tests.restAssured_tests;

import tests.base_tests.RestAssuredBaseTest;

import utilities.Constants;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
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


public class GetTrelloBoardTests extends RestAssuredBaseTest {


    @Test
    public void getAllBoards(ITestContext context) {
        ExtentTest testLogger = createTestLogger("Get all Boards",
                "Test that verifies the functionality of the GET method");

        Response actualResponse = sendGETRequest(buildRequest(requestSpec));

        assertResponseValidity(actualResponse);
        logTestResult(context, testLogger);
    }

    @Test
    public void getBoardById(ITestContext context) throws JsonProcessingException {

        ExtentTest testLogger = createTestLogger("Get Board by ID",
                "Test that verifies the functionality of the GET method when provided a specific ID as a Path Parameter");
        String boardId = getBoardIdFromFirstBoard();
        Response actualResponse = sendGETByIdRequest(buildRequestWithPathParam(requestSpec,
                                                        ID_PATH_PARAM, boardId));

        Map<String, String> actualResponseData = getActualResponseData(actualResponse);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);
        logTestResult(context, testLogger);
    }

}
