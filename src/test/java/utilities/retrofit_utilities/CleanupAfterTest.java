package utilities.retrofit_utilities;

import tests.base_tests.RetrofitBaseTest;
import retrofit2.Response;

import java.io.IOException;

import static utilities.retrofit_utilities.BuildRequest.buildDELETERequest;
import static org.junit.Assert.assertTrue;

public class CleanupAfterTest extends RetrofitBaseTest {
    public static void cleanupAfterTest(String boardId) throws IOException {

        Response<Void> response = buildDELETERequest(boardId).execute();

        assertTrue(response.isSuccessful());
    }
}
