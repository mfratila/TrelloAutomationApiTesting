package utilities.common_utilities;

import models.TrelloBoard;

import java.util.HashMap;
import java.util.Map;

public class PrepareExpectedResponse {

    public static Map<String, String> getExpectedResponseData(String boardDataPath) {
        Map<String, String> expectedResponseData = new HashMap<>();
        TrelloBoard expectedBoardData = TestDataProvider.createTrelloPojo(boardDataPath);

        expectedResponseData.put("name", expectedBoardData.getName());
        expectedResponseData.put("desc", expectedBoardData.getDesc());

        return expectedResponseData;
    }

}
