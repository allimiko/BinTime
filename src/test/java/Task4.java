import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

/**
 * Created by superova on 14.03.2018.
 */
public class Task4 {

    @BeforeClass
    public static void setupURL() {
        RestAssured.baseURI = "http://restcountries.eu/rest/v1";
    }

    @Test
    public void testStatusCode(){
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void codeTest(){

    }
}
