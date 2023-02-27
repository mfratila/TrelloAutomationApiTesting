package TrelloApiOkHttpTests;

import Utility.Constants;
import Utility.OkHttpBaseTest;
import okhttp3.*;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

import static OkHttpTestUtilities.BuildRequest.*;
import static OkHttpTestUtilities.CleanupAfterTest.cleanupAfterTest;
import static OkHttpTestUtilities.PrepareActualResponse.getActualResponseData;
import static OkHttpTestUtilities.SendRequest.*;
import static TestUtilities.PrepareExpectedResponse.getExpectedResponseData;
import static TestUtilities.TestAssertions.assertBoardData;
import static TestUtilities.TestDataProvider.*;
import static org.testng.Assert.assertEquals;

public class CreateTrelloBoardTests extends OkHttpBaseTest {

    @Test
    public void createNewBoard() throws IOException {

        RequestBody bodyPayload = createBodyFromBoardPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response actualResponse = sendRequest(buildPOSTRequest(bodyPayload));
        String actualResponseBodyAsString = actualResponse.body().string();

        Map<String, String> actualResponseData = getActualResponseData(actualResponseBodyAsString);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertEquals(200, actualResponse.code());
        assertBoardData(actualResponseData, expectedResponseData);

        cleanupAfterTest(actualResponseBodyAsString);

    }

    @Test
    public void getAllBoards() throws IOException {

        Response actualResponse = sendRequest(buildGETRequest());

        assertEquals(200, actualResponse.code());

    }

    @Test
    public void getBoardById() throws IOException {

        String boardId = "oeuasuqL";

        Response actualResponse = sendRequest(buildGETByIdRequest(boardId));

        assertEquals(200, actualResponse.code());

        if (actualResponse.body() != null) {
            System.out.println(actualResponse.body().string());
        }

    }

    @Test
    public void updateBoardById() throws IOException {

        String boardId = "oeuasuqL";
        RequestBody updatedPayload = createBodyFromBoardPojo(Constants.PUTRequest_PAYLOAD_PATH);

        Response actualResponse = sendRequest(buildPUTRequest(boardId, updatedPayload));

        assertEquals(200, actualResponse.code());
    }

    @Test
    public void deleteBoardById() throws IOException {

        String boardId = "oeuasuqL";

        Response actualResponse = sendRequest(buildDELETERequest(boardId));

        assertEquals(200, actualResponse.code());
    }
}
