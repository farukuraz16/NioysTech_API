package allRequest;

import datasPojo.UserServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.TestInfo;
import utilities.ConfigReader;
import utilities.JsonToJava;
import utilities.TestConfiguration;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class UserService extends TestConfiguration {
    HashMap<String, Object> responseBody;
    String newUserID = "106";
    ///TestInfo ???
    //Dependency Injection

    @Test
    public void GET_TC001_GetAllUsers() {
        extentTest = extentReports.createTest("My Test","Description");
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

    @Test
    public void GET_TC002_GetAllUsersofOrganization() {
        extentTest = extentReports.createTest("My Test2","Description2");
        extentTest.info("URL set edildi");

        //String URL = "https://a3m-qa-gm3.quaspareparts.com/auth/api/user?organizationId=1";
        specification.pathParam("userPath", "user").
                queryParam("organizationId", 1);

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

    @Test
    public void POST_TC003_AddNewUser() {
        extentTest = extentReports.createTest("My Test3","Description3");
        extentTest.info("URL set edildi");

        String URL = ConfigReader.getProperty("baseURL") + "/organization/1/application/2/role/5/user";

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

        responseBody = JsonToJava.convertJsonToJavaObject(response.asString(), HashMap.class);

        System.out.println("expectedDataMap = " + requestBody);
        System.out.println("actualDataMap = " + responseBody.toString());


        extentTest.info("Assertion işlemleri yapıldı");
        response.then().assertThat().statusCode(201);
        assertEquals(requestBody.getEmail(), responseBody.get("username"));
        assertEquals(requestBody.getEmail(), responseBody.get("email"));

        System.out.println("response.getStatusCode() = " + response.getStatusCode());


        //Pass mesaji
        extentTest.pass("Testimiz gecerli");

        //Fail mesaji
        extentTest.fail("Testimiz gecersiz");

        //Skip mesaji
        extentTest.skip("Testimiz skip edildi");
    }

    @Test
    public void GET_TC004_GetUserbyId() {
        extentTest = extentReports.createTest("My Test3","Description3");
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

        extentTest.info("Assertion işlemleri yapıldı");
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
    public void POST_TC005_SendEmailVerification() {
        extentTest = extentReports.createTest("My Test3","Description3");

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

        extentTest.info("Assertion işlemleri yapıldı");
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
    public void PUT_TC007_UpdateExistingUser() {
        extentTest = extentReports.createTest("My Test3","Description3");
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

        extentTest.info("Assertion işlemleri yapıldı");
        response.then().assertThat().statusCode(200);
        assertEquals(requestBody.getLastname(), responseBody.get("lastname"));
        assertEquals(requestBody.getName(), responseBody.get("name"));
    }

    @Test
    public void DELETE_TC008_DeleteUser() {
        extentTest = extentReports.createTest("My Test3","Description3");

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

        extentTest.info("Assertion işlemleri yapıldı");
        response.then().assertThat().statusCode(200);
        assertEquals("", response.asString());
    }
}
