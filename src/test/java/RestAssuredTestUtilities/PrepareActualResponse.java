package RestAssuredTestUtilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import models.TrelloBoard;

import java.util.HashMap;
import java.util.Map;


public class PrepareActualResponse {

    public static Map<String,String> getActualResponseData(Response actualResponse) throws JsonProcessingException {

        Map<String, String> actualResponseData = new HashMap<>();
        String responseBody = actualResponse.body().asString();

        TrelloBoard actualBoardData = getTrelloBoardPojoFromJSON(responseBody);
        actualResponseData.put("name", actualBoardData.getName());
        actualResponseData.put("desc", actualBoardData.getDesc());

        return actualResponseData;
    }

    public static TrelloBoard getTrelloBoardPojoFromJSON(String responseBody) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TrelloBoard trelloBoard = objectMapper.readValue(responseBody, TrelloBoard.class);

        return  trelloBoard;
    }
}
