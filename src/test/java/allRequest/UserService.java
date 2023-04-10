package allRequest;

import datasPojo.UserServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import utilities.ConfigReader;
import utilities.JsonToJava;
import utilities.TestConfiguration;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class UserService extends TestConfiguration {
    HashMap<String, Object> responseBody;
    public static String newUserID="391";

@Test
    public void TC001() {
        extentTest = extentReports.createTest("User Service TC001", "TC001_GET_GetAllUsers");

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

        int statusCode = response.getStatusCode();
        extentTest.info("Assertion işlemleri yapıldı."+ " StatusCode=" + statusCode);
        response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //Pass mesaji
        extentTest.pass("Testimiz gecerli");

        //Fail mesaji
        extentTest.fail("Testimiz gecersiz");

        //Skip mesaji
        extentTest.skip("Testimiz skip edildi");
    }
    @Test
    public void TC002() {
        extentTest = extentReports.createTest("User Service TC002", "TC002_GET_GetAllUsersofOrganization");
        extentTest.info("URL set edildi");

        //String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user?organizationId=1";
        specification.pathParam("userPath", "user").
                queryParam("organizationId", 186);

        extentTest.info("GET metodu ile request atıldı");
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{userPath}");

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();
        int statusCode = response.getStatusCode();

        extentTest.info("Assertion işlemleri yapıldı."+ " StatusCode=" + statusCode);

        response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //Pass mesaji
        extentTest.pass("Testimiz gecerli");

        //Fail mesaji
        extentTest.fail("Testimiz gecersiz");

        //Skip mesaji
        extentTest.skip("Testimiz skip edildi");
    }

    @Test
    public void TC003() {
        extentTest = extentReports.createTest("User Service TC003", "TC003_POST_AddNewUser");
        extentTest.info("URL set edildi");

        String URL = ConfigReader.getProperty("baseURL") + "/organization/186/application/2/role/5/user";

        UserServicePojo requestBody = new UserServicePojo(
                ConfigReader.getProperty("USid"),
                ConfigReader.getProperty("USname"),
                ConfigReader.getProperty("USlastname"),
                ConfigReader.getProperty("USusername"),
                ConfigReader.getProperty("USemail"),
                ConfigReader.getProperty("USphone"),
                ConfigReader.getProperty("USaddress"),
                ConfigReader.getProperty("UScountry"));

        extentTest.info("POST metodu ile request atıldı");
        Response response = given().
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + access_token).
                body(requestBody).
                when().
                post(URL);

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();


        // JsonPath responseBody = response.jsonPath();

        //responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        //newUserID = responseBody.get("id").toString();
        //System.out.println("newUserID = " + newUserID);

        //System.out.println("expectedDataMap = " + requestBody);
       // System.out.println("actualDataMap = " + responseBody.toString());

        int statusCode = response.getStatusCode();

        extentTest.info("Assertion işlemleri yapıldı."+ " StatusCode=" + statusCode);

        response.then().assertThat().statusCode(500);
        //assertEquals(requestBody.getEmail(), responseBody.get("username"));
        //assertEquals(requestBody.getEmail(), responseBody.get("email"));

        System.out.println("response.getStatusCode() = " + response.getStatusCode());


        //Pass mesaji
        extentTest.pass("Testimiz gecerli");

        //Fail mesaji
        extentTest.fail("Testimiz gecersiz");

        //Skip mesaji
        extentTest.skip("Testimiz skip edildi");
    }

    @Test
    public void TC004() {
        extentTest = extentReports.createTest("User Service TC004", "TC004_GET_GetUserbyId");
        extentTest.info("URL set edildi");
//        String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user/336";
        specification.pathParams("userPath", "user", "idPath", newUserID);

        extentTest.info("GET metodu ile request atıldı");
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{userPath}/{idPath}");
        extentTest.info("Response yazdırıldı");
        response.prettyPrint();
        int statusCode = response.getStatusCode();

        extentTest.info("Assertion işlemleri yapıldı."+ " StatusCode=" + statusCode);

        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(newUserID, responseBody.get("id"));

        //Pass mesaji
        extentTest.pass("Testimiz gecerli");

        //Fail mesaji
        extentTest.fail("Testimiz gecersiz");

        //Skip mesaji
        extentTest.skip("Testimiz skip edildi");

    }



    @Test
    public void TC005() {
        extentTest = extentReports.createTest("User Service TC005", "TC005_POST_SendEmailVerification");

        extentTest.info("URL set edildi");
        String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user/send-verification-request?organizationId=1&appId=2";

        UserServicePojo requestBody = new UserServicePojo(newUserID, ConfigReader.getProperty("email"));

        extentTest.info("POST metodu ile request atıldı");
        Response response = given().
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + access_token).
                body(requestBody).
                when().
                post(URL);

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();
        int statusCode = response.getStatusCode();

        extentTest.info("Assertion işlemleri yapıldı."+ " StatusCode=" + statusCode);

        response.then().assertThat().statusCode(200);
        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);
        assertEquals("Email Verification request sent successfully", responseBody.get("message"));
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //Pass mesaji
        extentTest.pass("Testimiz gecerli");

        //Fail mesaji
        extentTest.fail("Testimiz gecersiz");

        //Skip mesaji
        extentTest.skip("Testimiz skip edildi");
    }


    @Test
    public void TC006() {
        extentTest = extentReports.createTest("User Service TC006", "TC006_PUT_UpdateExistingUser");
        UserServicePojo requestBody = new UserServicePojo(
                newUserID,
                "IronMan",
                "Çelikoğlu",
                "ironman@example.com",
                "ironman@example.com",
                "123456789",
                "Atlanta,GA",
                "US");

        extentTest.info("URL set edildi");
        specification.pathParam("userPath", "user");

        extentTest.info("PUT metodu ile request atıldı");
        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                header("Authorization", "Bearer " + access_token).
                body(requestBody).
                when().
                put("/{userPath}");

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();

        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        int statusCode = response.getStatusCode();

        extentTest.info("Assertion işlemleri yapıldı."+ " StatusCode=" + statusCode);

        response.then().assertThat().statusCode(200);
        assertEquals(requestBody.getLastname(), responseBody.get("lastname"));
        assertEquals(requestBody.getName(), responseBody.get("name"));
    }


    @Test
    public void TC007() {
        extentTest = extentReports.createTest("User Service TC007", "TC007_DELETE_DeleteUser");

        extentTest.info("URL set edildi");
        specification.pathParams("userPath", "user", "idPath", newUserID);

        extentTest.info("DELETE metodu ile request atıldı");
        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                delete("/{userPath}/{idPath}");

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        int statusCode = response.getStatusCode();

        extentTest.info("Assertion işlemleri yapıldı."+ " StatusCode=" + statusCode);
        response.then().assertThat().statusCode(200);
        assertEquals("", response.asString());
    }
}
