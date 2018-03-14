import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by superova on 14.03.2018.
 */
public class Task5 extends TestBase {
    String file = "./src/main/resources/prodIdList.txt";
    String productCode = ".container .productCode";
    String searchField = "input[type=\"search\"]";
    String BASE_Url = "https://www.centralpoint.nl/";
    String zoekButton = ".search.orange";
    String productNumber = ".productNumber";

    List<String> idProducts = new ArrayList<String>();

    @BeforeClass
    public void before(){
        readFileSetDataToList(file, idProducts);
    }

    @DataProvider(name = "data")
    public Object[][] createData() throws IOException {
        String[] arr = idProducts.toArray(new String[idProducts.size()]);
        int a = idProducts.size();
        String[][] locators = new String[a][1];
        for (int i = 0; i < a; i++) {
            locators[i][0] = arr[i];
        }
        return locators;
    }

    @Test(dataProvider = "data")
    public void test(String productId){
        if(productId == null){
        }else {
            if($(productCode).is(not(visible))){
                open(BASE_Url);
             List<SelenideElement> productNumbers = $$(productNumber);
             for(SelenideElement element : productNumbers){
                 Assert.assertTrue(element.getText().contains(productId));
             }
            }else {
                open(BASE_Url);
                $(searchField).val(productId);
                $(zoekButton).click();
                $(productCode).waitUntil(visible,10000);
                String codeOnThePage = $(productCode).getText();
                Assert.assertTrue(codeOnThePage.contains(productId));
            }
        }
    }
}
