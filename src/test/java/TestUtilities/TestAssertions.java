package TestUtilities;

import org.testng.Assert;

import java.util.Map;

public class TestAssertions {

    public static void assertBoardData(Map<String, String> actualResponseData, Map<String, String> expectedResponseData) {
        Assert.assertEquals(actualResponseData.get("name"), expectedResponseData.get("name"));
        Assert.assertEquals(actualResponseData.get("desc"), expectedResponseData.get("desc"));
        Assert.assertEquals(actualResponseData.get("idOrganization"), expectedResponseData.get("idOrganization"));
    }
}
