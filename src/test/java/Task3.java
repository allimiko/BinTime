import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static  io.restassured.RestAssured.*;
import static  io.restassured.matcher.RestAssuredMatchers.*;
import static  org.hamcrest.Matchers.*;

/**
 * Created by superova on 14.03.2018.
 */
public class Task3 {

    @BeforeClass
    public static void setupURL()
    {
        // here we setup the default URL and API base path to use throughout the tests
        RestAssured.baseURI = "http://restcountries.eu/rest/v1";

    }

    @Test
    public void testStatusCode(){
        when().
                request("CONNECT", "/").
                then().
                statusCode(200);
    }

    @Test
    public void testType(){
        when().
                get("/").
                then().
                contentType(ContentType.JSON);
    }


}
