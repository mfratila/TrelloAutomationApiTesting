package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import models.TrelloBoard;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;


public class FrameworkUtilities {
    protected static Properties properties;
    protected static ExtentReports extent;

    public static String readConfigurationFile(String key) {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(Constants.CONFIG_FILE_PATH));

        } catch (Exception e) {
            System.out.println("Cannot find key: " + key + "in Config file due to exception: " + e);
        }

        return properties.getProperty(key).trim();
    }

    public static JSONObject returnPayloadObject(String filePath) {
        JSONParser parser = new JSONParser();
        Object obj = null;

        try  {
            obj = parser.parse(new FileReader(filePath));
        } catch (NullPointerException | IOException | ParseException e) {
           e.printStackTrace();
        }

        return (JSONObject) obj;
    }

    public void logResponseAsString(Response response) {
        System.out.println(response.asString());
    }

    public void printOutputLog(Response response) {
        response.then().log().all();
    }

    public static Map<String, String> getApiKeyAndTokenFromConfigFile() {
        Map<String, String> apiInfo = new HashMap<>();
        apiInfo.put("key", properties.getProperty("ApiKey"));
        apiInfo.put("token", properties.getProperty("ApiToken"));

        return apiInfo;
    }

    public static String convertJsonValueToStringFromTestData(String path, String key) {
        JSONObject postRequestPayload = returnPayloadObject(path);

        return (String) postRequestPayload.get(key);
    }

    public ExtentTest createTestLogger(String testTitle, String testDescription) {
        ExtentTest test = extent.createTest(testTitle, testDescription);
        test.log(Status.INFO, "Sending Request");

        return test;
    }

    public void logResponseBody(ExtentTest test, Response response) {
        String responseBody = response.then().log().body().toString();
        test.log(Status.INFO, "Response Body: " + responseBody);
    }

    public static void logTestResult(ITestContext context, ExtentTest test) {
        if (context.getFailedTests().size() > 0) {
            test.log(Status.FAIL, "Request failed: " + context.getFailedTests().getAllResults());
        } else if (context.getSkippedTests().size() > 0) {
            test.log(Status.SKIP, "Test skipped: " + context.getSkippedTests().getAllResults());
        } else {
            test.log(Status.PASS, "Request successful");
        }
    }

    public static void logResults(ExtentTest test, ITestContext context, okhttp3.Response response) throws IOException {

        Request request = response.request();
        String responseBody = Objects.requireNonNull(response.body()).string();

        logTestResult(context, test);

        test.info(responseBody);
        test.info("Status Code: " + response.code());
        test.info("Request URL: " + request.url());
        test.info("Request Method: " + request.method());
        test.info("Request Headers " + request.headers());
        test.info("Response Headers: " + response.headers());
        test.info("Response time: " + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + "ms");
        // Log response body as HTML
        test.info(MarkupHelper.createCodeBlock(responseBody, CodeLanguage.JSON));
    }

    public static void logResultsForVoidResponse(ExtentTest test, ITestContext context, retrofit2.Response<Void> response) {

        Request request = response.raw().request();
        logTestResult(context, test);

        test.info("Status Code: " + response.code());
        test.info("Request URL: " + request.url());
        test.info("Request Method: " + request.method());
        test.info("Request Headers " + request.headers());
        test.info("Response Headers: " + response.headers());

    }

    public static void logResultsForListResponse(ExtentTest test, ITestContext context, retrofit2.Response<List<TrelloBoard>> response) {

        Request request = response.raw().request();
        logTestResult(context, test);

        test.info("Status Code: " + response.code());
        test.info("Request URL: " + request.url());
        test.info("Request Method: " + request.method());
        test.info("Request Headers " + request.headers());
        test.info("Response Headers: " + response.headers());

    }

    public static void logResults(ExtentTest test, ITestContext context, retrofit2.Response<TrelloBoard> response) {

        Request request = response.raw().request();
        logTestResult(context, test);

        test.info("Status Code: " + response.code());
        test.info("Request URL: " + request.url());
        test.info("Request Method: " + request.method());
        test.info("Request Headers " + request.headers());
        test.info("Response Headers: " + response.headers());
    }

    public static Buffer bufferResponse(okhttp3.Response response) throws IOException {
        return response.peekBody(Long.MAX_VALUE).source().buffer().clone();
    }

    public static okhttp3.Response getBufferedResponse(okhttp3.Response response) throws IOException {

        Buffer responseBuffer = bufferResponse(response);

        return response
                .newBuilder()
                .body(ResponseBody
                        .create(response.body().contentType(), responseBuffer.size(), responseBuffer))
                .build();
    }
}
