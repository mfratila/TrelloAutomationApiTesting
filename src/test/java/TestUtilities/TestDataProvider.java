package TestUtilities;

import Utility.RestAssuredBaseTest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.TrelloBoard;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;

import static RestAssuredTestUtilities.BuildRequest.*;
import static RestAssuredTestUtilities.SendRequest.*;

public class TestDataProvider extends RestAssuredBaseTest {

    public static String getBoardIdFromFirstBoard() {

        Response response = sendGETRequest(buildRequest(requestSpec));

        JsonPath jsonPath = response.jsonPath();
        String shortUrl = jsonPath.get("shortUrl[0]");

        return shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
    }

    public static String getBoardIdFromResponse(Response response) {

        String shortUrl = response.body().path("shortUrl");

        return shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
    }

    public static String getBoardIdFromOkHttpResponse(String responseBody) {
        JsonObject jsonResponseBody = parseResponseBodyIntoJson(responseBody);
        String shortUrl = jsonResponseBody.get("shortUrl").getAsString();
        return shortUrl.substring(shortUrl.lastIndexOf("/") + 1);
    }

    public static String getBoardIdFromRetrofitResponse(TrelloBoard response) throws NullPointerException {
        String shortUrl = response.getShortUrl();
        return Objects.requireNonNull(shortUrl.substring(shortUrl.lastIndexOf("/") + 1));
    }

    public static TrelloBoard createTrelloPojo(String path) {
        TrelloBoard trelloBoard = new TrelloBoard();
        trelloBoard.setName(convertJsonValueToStringFromTestData(path, "name"));
        trelloBoard.setDesc(convertJsonValueToStringFromTestData(path, "desc"));
        trelloBoard.setIdOrganization(convertJsonValueToStringFromTestData(path, "idOrganization"));

        return trelloBoard;
    }

    public static String convertBoardToJsonAsString(TrelloBoard board) {
        Gson gson = new Gson();
        return gson.toJson(board);
    }

    public static RequestBody createBodyFromBoardPojo(String path) {

        TrelloBoard newBoardData = createTrelloPojo(path);
        String PostPayload = convertBoardToJsonAsString(newBoardData);
        MediaType mediaType = MediaType.parse("application/json");

        return new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return mediaType;
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8(PostPayload);
            }
        };

    }

    public static JsonObject parseResponseBodyIntoJson(String responseBody) {

        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(responseBody, JsonElement.class);

        return jsonElement.getAsJsonObject();
    }


}
