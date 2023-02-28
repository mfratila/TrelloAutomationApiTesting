package TrelloApiRetrofitTests;

import Utility.Constants;
import Utility.RetrofitBaseTest;
import models.TrelloBoard;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;


import java.util.List;

import static TestUtilities.TestDataProvider.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Test
public class RetrofitTests extends RetrofitBaseTest {
    private final String boardId = "39uKwF8J";
    private final String apiKey = "bede52f2bd2294db6438aa654bc8065f";
    private final String apiToken = "ATTAe0488b24db196d641dda9d08d99210ebcb608f9bba907c0da1c85a0443ed1e5d8AD54640";



    @Test
    public void testGetBoardById() throws Exception {
        Call<TrelloBoard> call = apiService.getBoardById(boardId, apiKey, apiToken);
        Response<TrelloBoard> response = call.execute();

        assertTrue(response.isSuccessful());
        assertEquals(response.body().getName(), "Testing Automation Board");
    }

    @Test
    public void testGetAllBoards() throws Exception {
        Call<List<TrelloBoard>> call = apiService.getAllBoards(apiKey, apiToken);

        Response<List<TrelloBoard>> response = call.execute();
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testCreateBoard() throws Exception {
        TrelloBoard board = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        Call<TrelloBoard> call = apiService.createBoard(apiKey, apiToken, board);
        Response<TrelloBoard> response = call.execute();

        System.out.println(response.raw().request().url());
        System.out.println(response.code());

        assertTrue(response.isSuccessful());
        assertEquals(response.body().getName(), "Testing Automation Board");
    }

    @Test
    public void testUpdateBoard() throws Exception {
        TrelloBoard board = createTrelloPojo(Constants.PUTRequest_PAYLOAD_PATH);
        Call<TrelloBoard> call = apiService.updateBoard(boardId, apiKey, apiToken, board);
        Response<TrelloBoard> response = call.execute();

        assertTrue(response.isSuccessful());
        assertEquals(response.body().getName(), "Updated Testing Automation Board");
    }

    @Test
    public void testDeleteBoard() throws Exception {
        Call<Void> call = apiService.deleteBoard(boardId, apiKey, apiToken);
        Response<Void> response = call.execute();

        assertTrue(response.isSuccessful());
    }
}