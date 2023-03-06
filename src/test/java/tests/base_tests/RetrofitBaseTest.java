package tests.base_tests;

import utilities.retrofit_utilities.ApiService;
import utilities.FrameworkUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBaseTest extends FrameworkUtilities {

    protected static ApiService apiService;

    @BeforeSuite
    public void beforeSuite() {

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/RetrofitReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }

    @BeforeTest
    public void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.trello.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}