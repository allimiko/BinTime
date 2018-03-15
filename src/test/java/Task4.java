import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

/**
 * Created by superova on 14.03.2018.
 */
public class Task4 extends TestBaseRest {
    private float area;

    @BeforeClass
    public static void setupURL() {
        RestAssured.baseURI = "http://restcountries.eu/rest/v1";
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
    public void testStatusCode() {
        given().when().get("/").then().statusCode(200);
    }

    @Test(dependsOnMethods = "outputValues")
    public void testUkraine() {
        Assert.assertTrue(area > 500000.0);
    }

    @Test
    public void outputValues() {
        String name = get("").path("find {it.name == 'Ukraine'}.name");
        System.out.println(name);
        String capital = get("").path("find {it.name == 'Ukraine'}.capital");
        System.out.println(capital);
        String region = get("").path("find {it.name == 'Ukraine'}.region");
        System.out.println(region);
        int population = get("").path("find {it.name == 'Ukraine'}.population");
        System.out.println(population);
        List<String> borders = get("").path("find {it.name == 'Ukraine'}.borders");
        // borders.forEach(n -> System.out.println(n));  или так
        for (String s : borders) {
            System.out.print(s + " ");
        }
        area = get("").path("find {it.name == 'Ukraine'}.area");

    }
}
