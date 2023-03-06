package utilities.okHttp_utilities;

import okhttp3.Response;

import java.io.IOException;

import static utilities.common_utilities.TestDataProvider.*;
import static org.testng.Assert.assertEquals;


public class CleanupAfterTest {
    public static void cleanupAfterTest(String responseBody) throws IOException {

        String boardId = getBoardIdFromOkHttpResponse(responseBody);

        Response deleteRequest = SendRequest.sendRequest(BuildRequest.buildDELETERequest(boardId));

        assertEquals(200, deleteRequest.code());
    }
}
