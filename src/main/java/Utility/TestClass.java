package Utility;

import org.json.simple.JSONObject;


public class TestClass extends FrameworkUtilities {
    public static void main(String[] args) {
        JSONObject testObj = returnPayloadObject(Constants.POSTRequest_PAYLOAD_PATH);
        System.out.println(testObj.toString());
    }
}
