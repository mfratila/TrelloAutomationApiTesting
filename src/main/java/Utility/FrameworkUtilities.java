package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    public Map<String, String> getApiKeyAndTokenToRequestSpecification() {
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

    public void logTestResult(ITestContext context, ExtentTest test) {
        if (context.getFailedTests().size() > 0) {
            test.log(Status.FAIL, "Request failed: " + context.getFailedTests().getAllResults());
        } else if (context.getSkippedTests().size() > 0) {
            test.log(Status.SKIP, "Test skipped: " + context.getSkippedTests().getAllResults());
        } else {
            test.log(Status.PASS, "Request successful");
        }
    }

}
