package utilities.okHttp_utilities;

import com.google.gson.JsonObject;

import static utilities.common_utilities.TestDataProvider.*;

import java.util.HashMap;
import java.util.Map;

public class PrepareActualResponse {
    public static Map<String, String> getActualResponseData(String responseBody) {
        JsonObject jsonResponseBody = parseResponseBodyIntoJson(responseBody);
        Map<String, String> actualResponseData = new HashMap<>();

        actualResponseData.put("name", jsonResponseBody.get("name").getAsString());
        actualResponseData.put("desc", jsonResponseBody.get("desc").getAsString());

        return actualResponseData;
    }
}
