package Utility;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.testng.annotations.BeforeSuite;


public class OkHttpBaseTest extends FrameworkUtilities{
    protected static HttpUrl uri;
   protected static OkHttpClient client;

    @BeforeSuite
    public void setBaseURI() {
        client = new OkHttpClient.Builder().followRedirects(false).build();
        uri = HttpUrl.parse(readConfigurationFile("Base_URI"));

    }
}
