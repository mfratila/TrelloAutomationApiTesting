package tests.okHttp_tests;

import io.qameta.allure.Description;
import utilities.Constants;
import tests.base_tests.OkHttpBaseTest;
import com.aventstack.extentreports.ExtentTest;
import okhttp3.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;

import static utilities.okHttp_utilities.BuildRequest.*;
import static utilities.okHttp_utilities.CleanupAfterTest.cleanupAfterTest;
import static utilities.okHttp_utilities.PrepareActualResponse.getActualResponseData;
import static utilities.okHttp_utilities.SendRequest.*;
import static utilities.common_utilities.CheckResponseIsValid.assertResponseValidity;
import static utilities.common_utilities.PrepareExpectedResponse.getExpectedResponseData;
import static utilities.common_utilities.TestAssertions.assertBoardData;
import static utilities.common_utilities.TestDataProvider.*;

public class CreateTrelloBoardTests extends OkHttpBaseTest {

    @Test
    @Description("Test that verifies the functionality of the POST method.")
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
