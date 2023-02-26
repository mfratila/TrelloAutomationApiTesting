package TrelloApiTests;

import Utility.BaseTest;

import Utility.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static TestUtilities.BuildRequest.*;
import static TestUtilities.SendRequest.*;
import static TestUtilities.TestDataProvider.*;
import static TestUtilities.CheckResponseIsValid.*;
import static TestUtilities.PrepareActualResponse.*;
import static TestUtilities.PrepareExpectedResponse.*;
import static TestUtilities.TestAssertions.*;


public class GetTrelloBoardTests extends BaseTest {


    @Test
    public void getAllBoards() {

        Response actualResponse = sendGETRequest(buildRequest(requestSpec));

        assertResponseValidity(actualResponse);

    }

    @Test
    public void getBoardById() throws JsonProcessingException {

        String boardId = getBoardIdFromFirstBoard();
        Response actualResponse = sendGETByIdRequest(buildRequestWithPathParam(requestSpec, ID_PATH_PARAM, boardId));

        Map<String, String> actualResponseData = getActualResponseData(actualResponse);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);

    }

}
