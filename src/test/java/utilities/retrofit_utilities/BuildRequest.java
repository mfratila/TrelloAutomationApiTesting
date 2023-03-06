package utilities.retrofit_utilities;

import tests.base_tests.RetrofitBaseTest;
import models.TrelloBoard;
import retrofit2.Call;

import java.util.List;

public class BuildRequest extends RetrofitBaseTest {

    private static final String apiKey = readConfigurationFile("ApiKey");
    private static final String apiToken = readConfigurationFile("ApiToken");

    public static Call<List<TrelloBoard>> buildGETRequest() {
        return apiService.getAllBoards(apiKey, apiToken);
    }

    public static Call<TrelloBoard> buildGETByIdRequest(String boardId) {
        return apiService.getBoardById(boardId, apiKey, apiToken);
    }

    public static Call<TrelloBoard> buildPOSTRequest(TrelloBoard body) {
        return apiService.createBoard(apiKey, apiToken, body);
    }

    public static Call<TrelloBoard> buildPUTRequest(String boardId, TrelloBoard body) {
        return apiService.updateBoard(boardId, apiKey, apiToken, body);
    }

    public static Call<Void> buildDELETERequest(String boardId) {
        return apiService.deleteBoard(boardId, apiKey, apiToken);
    }
}
