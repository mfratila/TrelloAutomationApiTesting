package TrelloApiOkHttpTests;

import Utility.Constants;
import Utility.OkHttpBaseTest;
import com.aventstack.extentreports.ExtentTest;
import okhttp3.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

import static OkHttpTestUtilities.BuildRequest.*;
import static OkHttpTestUtilities.CleanupAfterTest.cleanupAfterTest;
import static OkHttpTestUtilities.PrepareActualResponse.getActualResponseData;
import static OkHttpTestUtilities.SendRequest.*;
import static TestUtilities.CheckResponseIsValid.assertResponseValidity;
import static TestUtilities.PrepareExpectedResponse.getExpectedResponseData;
import static TestUtilities.TestAssertions.assertBoardData;
import static TestUtilities.TestDataProvider.*;

public class CreateTrelloBoardTests extends OkHttpBaseTest {

    @Test
    public void createNewBoard(ITestContext context) throws IOException {

        ExtentTest testLogger = createTestLogger("Create New Board",
                "Test that verifies the functionality of the POST method");
        RequestBody bodyPayload = createBodyFromBoardPojo(Constants.POSTRequest_PAYLOAD_PATH);

        Response actualResponse = sendRequest(buildPOSTRequest(bodyPayload));
        Response bufferedResponse = getBufferedResponse(actualResponse);
        String actualResponseBodyAsString = bufferedResponse.body().string();

        Map<String, String> actualResponseData = getActualResponseData(actualResponseBodyAsString);
        Map<String, String> expectedResponseData = getExpectedResponseData(Constants.POSTRequest_PAYLOAD_PATH);

        assertResponseValidity(actualResponse);
        assertBoardData(actualResponseData, expectedResponseData);
        logResults(testLogger, context, bufferedResponse);
        cleanupAfterTest(actualResponseBodyAsString);

    }





}
