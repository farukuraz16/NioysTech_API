package allRequest;

import io.restassured.response.Response;
import org.junit.Test;
import utilities.TestConfiguration;

import static io.restassured.RestAssured.given;

public class UserService extends TestConfiguration {

    @Test
    public void GET_TC001_GetAllUsers() {
        extentTest = extentReports.createTest("My Test","Description");

        System.out.println("access_token = " + access_token);
        extentTest.info("URL set edildi");
        specification.pathParam("userPath", "user");

        extentTest.info("GET metodu ile request atıldı");
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{userPath}");

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();

        extentTest.info("Assertion işlemleri yapıldı");
        response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
    }

    @Test
    public void GET_TC001_GetAllUsers2() {
        extentTest = extentReports.createTest("My Test2","Description2");
        System.out.println("access_token = " + access_token);
        String token = access_token;
        extentTest.info("URL set edildi");
        specification.pathParam("userPath", "user");

        extentTest.info("GET metodu ile request atıldı");
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{userPath}");

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();

        extentTest.info("Assertion işlemleri yapıldı");
        response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //Pass mesaji
        extentTest.pass("Testimiz gecerli");

        //Fail mesaji
        extentTest.fail("Testimiz gecersiz");

        //Skip mesaji
        extentTest.skip("Testimiz skip edildi");
    }
}
