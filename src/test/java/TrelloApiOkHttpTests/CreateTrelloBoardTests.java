package TrelloApiOkHttpTests;

import Utility.Constants;
import Utility.OkHttpBaseTest;
import models.TrelloBoard;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.testng.annotations.Test;

import java.io.IOException;

import static OkHttpTestUtilities.BuildRequest.*;
import static TestUtilities.Constants.*;
import static TestUtilities.TestDataProvider.*;
import static org.testng.Assert.assertEquals;

public class CreateTrelloBoardTests extends OkHttpBaseTest {

    @Test
    public void createNewBoard() throws IOException {
        TrelloBoard trelloBoard = createTrelloPojo(Constants.POSTRequest_PAYLOAD_PATH);
        String POSTPayload = convertBoardToJsonAsString(trelloBoard);

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8(POSTPayload);
            }
        };

        HttpUrl endpoint = HttpUrl.parse(uri + POST_ENDPOINT)
                .newBuilder()
                .addQueryParameter("key", readConfigurationFile("ApiKey"))
                .addQueryParameter("token", readConfigurationFile("ApiToken"))
                .build();

        System.out.println(endpoint);

        Request request = new Request.Builder()
                .url(endpoint)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());

    }

    @Test
    public void getAllBoards() throws IOException {

        Response response = client.newCall(buildGETRequest()).execute();

        assertEquals(200, response.code());

    }

    @Test
    public void getBoardById() throws IOException {

        String boardId = "ePYXVkOl";

        Response response = client.newCall(buildGETByIdRequest(boardId)).execute();

        assertEquals(200, response.code());

        if (response.body() != null) {
            System.out.println(response.body().string());
        }

    }

    @Test
    public void updateBoardById() throws IOException {
        TrelloBoard trelloBoard = createTrelloPojo(Constants.PUTRequest_PAYLOAD_PATH);
        String POSTPayload = convertBoardToJsonAsString(trelloBoard);

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8(POSTPayload);
            }
        };

        HttpUrl endpoint = HttpUrl.parse(uri + PUT_ENDPOINT.replace("{id}", "huS3zZaL"))
                .newBuilder()
                .addQueryParameter("key", readConfigurationFile("ApiKey"))
                .addQueryParameter("token", readConfigurationFile("ApiToken"))
                .build();


        Request request = new Request.Builder()
                .url(endpoint)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
    }

    @Test
    public void deleteBoardById() throws IOException {

        HttpUrl endpoint = HttpUrl.parse(uri + DELETE_ENDPOINT.replace("{id}", "huS3zZaL"))
                .newBuilder()
                .addQueryParameter("key", readConfigurationFile("ApiKey"))
                .addQueryParameter("token", readConfigurationFile("ApiToken"))
                .build();


        Request request = new Request.Builder()
                .url(endpoint)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        assertEquals(200, response.code());
    }
}
