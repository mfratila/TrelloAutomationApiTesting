package TrelloApiTests;

import Utility.BaseTest;

import Utility.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.annotations.Test;

import java.util.Map;

import static TestUtilities.BuildRequest.*;
import static TestUtilities.PrepareActualResponse.*;
import static TestUtilities.PrepareExpectedResponse.*;
import static TestUtilities.SendRequest.*;
import static TestUtilities.TestAssertions.*;
import static TestUtilities.TestDataProvider.*;
import static TestUtilities.CheckResponseIsValid.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public class UpdateTrelloBoardTests extends BaseTest {

    @Test
    public void updateBoardById() throws JsonProcessingException {

        TrelloBoard boardData = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response newBoard = sendPOSTRequest(buildRequestWithBody(requestSpec, boardData));
        String boardId = getBoardIdFromResponse(newBoard);

        TrelloBoard updatedBoardData = createTrelloPojo(Constants.PUTRequest_PAYLOAD_PATH);
        //Response actualResponse = sendPUTRequest(buildRequestWithBodyAndPathParam(requestSpec,
                                                   // updatedBoardData, ID_PATH_PARAM, boardId));

        Response actualResponse = given()
                .spec(requestSpec)
                .pathParam(ID_PATH_PARAM, boardId)
                        .body(updatedBoardData)
                .when()
                        .put(PUT_ENDPOINT);

        actualResponse.then().log().body();
        Map<String, String> actualResponseData = getActualResponseData(actualResponse);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.PUTRequest_PAYLOAD_PATH);

        assertResponseValidity(newBoard);
        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);
;
    }
}
