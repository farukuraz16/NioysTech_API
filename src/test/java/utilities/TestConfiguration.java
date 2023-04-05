package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestConfiguration {

    protected RequestSpecification specification;
    static String username = ConfigReader.getProperty("username");
    static String password = ConfigReader.getProperty("password");

    protected ExtentReports extentReports;

    protected ExtentSparkReporter extentSparkReporter;
    protected ExtentTest extentTest;
    public static String access_token;


    @BeforeClass
    public static void setDriver() {
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions().setHeadless(true);
        driver.get("https://qa-gm3.quaspareparts.com/oauth2/authorization/a3m-client");
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.tagName("button")).click();
        driver.navigate().to("https://qa-gm3.quaspareparts.com/auth/userinfo");
        JsonPath path = new JsonPath(driver.findElement(By.tagName("body")).getText());
        access_token = path.getString("access_token");
        System.out.println("Token: " + access_token);
        driver.quit();
    }

    @Before
    public void setup() {
        extentReports = new ExtentReports();


        String currentDate = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
        String filePath = System.getProperty("user.dir") + "\\test-output\\Reports\\testReport_" + currentDate + ".html";//Raporlarımızın kaydedileceği yeri oluşturduk


        extentSparkReporter = new ExtentSparkReporter(filePath);


        extentReports.attachReporter(extentSparkReporter);


        extentReports.setSystemInfo("Enviroment", "QA");
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Test Site", "RxDrugsHub");
        extentReports.setSystemInfo("Test Type", "REST API Testing");
        extentReports.setSystemInfo("Request Type", "POST");

        extentSparkReporter.config().setDocumentTitle("Team06 Report");
        extentSparkReporter.config().setReportName("Team06 Report");

        specification = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseURL")).build();

    }

    @After
    public void closeReports() {
        extentReports.flush();
    }

    @AfterClass
    public static void aa() {

    }

}
