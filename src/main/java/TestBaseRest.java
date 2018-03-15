import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

/**
 * Created by superova on 15.03.2018.
 */
public class TestBaseRest {
    protected  Response response;
    @BeforeClass
    public static void setupURL() {
        RestAssured.baseURI = "http://restcountries.eu/rest/v1";
    }

}
