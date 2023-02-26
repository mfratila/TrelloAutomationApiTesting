package Utility;

import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.restassured.response.Response;
import org.json.simple.parser.ParseException;

public class FrameworkUtilities {
    protected static Properties properties;


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
            AllureLogger.logToAllure("Error in JSON object parson with the exception: " + e);
        }

        return (JSONObject) obj;
    }

    public void logResponseAsString(Response response) {
        AllureLogger.logToAllure(response.asString());
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
}
