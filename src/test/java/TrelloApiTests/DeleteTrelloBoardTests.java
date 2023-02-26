package TrelloApiTests;

import Utility.BaseTest;
import Utility.Constants;
import io.restassured.response.Response;
import models.TrelloBoard;
import org.testng.annotations.Test;

import static TestUtilities.BuildRequest.*;
import static TestUtilities.SendRequest.*;
import static TestUtilities.TestDataProvider.*;
import static TestUtilities.CheckResponseIsValid.*;


public class DeleteTrelloBoardTests extends BaseTest {
    @Test
    public void deleteBoardById() {

        TrelloBoard boardData = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Response newBoard = sendPOSTRequest(buildRequestWithBody(requestSpec, boardData));
        String boardId = getBoardIdFromResponse(newBoard);

        Response actualResponse = sendDELETERequest(buildRequestWithPathParam(requestSpec,
                ID_PATH_PARAM, boardId));

        assertResponseValidity(actualResponse);
    }
}
