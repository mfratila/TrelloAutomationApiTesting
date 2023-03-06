package utilities.retrofit_utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.TrelloBoard;
import retrofit2.Response;
import java.util.HashMap;
import java.util.Map;

public class PrepareActualResponse {
    public static Map<String,String> getActualResponseData(Response<TrelloBoard> actualResponse) throws JsonProcessingException {

        Map<String, String> actualResponseData = new HashMap<>();
        TrelloBoard actualBoardData = actualResponse.body();

        actualResponseData.put("name", actualBoardData.getName());
        actualResponseData.put("desc", actualBoardData.getDesc());

        return actualResponseData;
    }
}
