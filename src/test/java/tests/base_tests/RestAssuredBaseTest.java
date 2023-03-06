package tests.base_tests;

import utilities.FrameworkUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class RestAssuredBaseTest extends FrameworkUtilities {
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;


    @BeforeSuite
    public void setBaseURI() {
        requestSpec= new RequestSpecBuilder()
                .setBaseUri(readConfigurationFile("Base_URI"))
                .addQueryParams(getApiKeyAndTokenFromConfigFile())
                .build();

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/RestAssuredReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

//    @BeforeMethod
//    public void beforeMethod() {
//        responseSpec = new ResponseSpecBuilder()
//                .expectStatusCode(400)
//                .build();
//    }


    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}
