import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;

/**
 * Created by superova on 14.03.2018.
 */
public class Task3 extends TestBaseRest {

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
