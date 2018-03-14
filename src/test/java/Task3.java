import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;

/**
 * Created by superova on 14.03.2018.
 */
public class Task3 {
    public static Response response;

    @BeforeClass
    public static void setupURL() {
        RestAssured.baseURI = "http://restcountries.eu/rest/v1";
    }

    @Test
    public void testStatusCode() {
        when().
                request("CONNECT", "/").
                then().
                statusCode(200);
    }

    @Test
    public void testType() {
        response =
                when().
                        get("/").
                        then().
                        contentType(ContentType.JSON).
                        extract().response();
    }

    @Test
    public void borderTest() {
        when().
                get("/").
                then().
                body("find {it.name == 'Latvia'}.borders", contains("BLR", "EST", "LTU", "RUS"));
    }
}
