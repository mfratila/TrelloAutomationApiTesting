package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class OkHttpBaseTest extends FrameworkUtilities{
    protected static HttpUrl uri;
   protected static OkHttpClient client;

    @BeforeSuite
    public void setBaseURI() {
        client = new OkHttpClient.Builder().followRedirects(false).build();
        uri = HttpUrl.parse(readConfigurationFile("Base_URI"));

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/OkHttpReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}
