package OkHttpTestUtilities;

import okhttp3.Response;

import java.io.IOException;

import static OkHttpTestUtilities.BuildRequest.buildDELETERequest;
import static OkHttpTestUtilities.SendRequest.sendRequest;
import static TestUtilities.TestDataProvider.*;
import static org.testng.Assert.assertEquals;


public class CleanupAfterTest {
    public static void cleanupAfterTest(String responseBody) throws IOException {

        String boardId = getBoardIdFromOkHttpResponse(responseBody);

        Response deleteRequest = sendRequest(buildDELETERequest(boardId));

        assertEquals(200, deleteRequest.code());
    }
}
