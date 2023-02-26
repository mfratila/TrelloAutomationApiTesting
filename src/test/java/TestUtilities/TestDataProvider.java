package TestUtilities;

import Utility.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.TrelloBoard;

import static TestUtilities.BuildRequest.*;
import static TestUtilities.SendRequest.*;

public class TestDataProvider extends BaseTest {

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

    public static TrelloBoard createTrelloPojo(String path) {
        TrelloBoard trelloBoard = new TrelloBoard();
        trelloBoard.setName(convertJsonValueToStringFromTestData(path, "name"));
        trelloBoard.setDesc(convertJsonValueToStringFromTestData(path, "desc"));
        trelloBoard.setIdOrganization(convertJsonValueToStringFromTestData(path, "idOrganization"));

        return trelloBoard;
    }

}