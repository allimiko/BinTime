import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by superova on 14.03.2018.
 */
public class Task2 extends TestBase {
 String monitoren = "/monitoren/";
    String priceRangeUrl = "?priceRange=";
 String priceRangeLow = "#priceRangeLow";
 String priceRangehigh = "#priceRangehigh";

 String zoekenButton = ".filter.active .filterFooter > button";
 String titleOfPage = ".title > h1";

 String price = ".price.priceExcl";

Set<String> prices = new HashSet<String>();

 @BeforeClass
    public void precondition() throws IOException {
     open(BASE + monitoren);
     $$(".mobileSwitchFiltersOff .filterHead").first().click();
     $$(priceRangeLow).last().val("1000");
     $$(priceRangehigh).last().val("5000");
     $(zoekenButton).click();
     $(titleOfPage).waitUntil(not(text("\n" +
             "                                    Monitoren\n" +
             "            \n" +
             "                        (2652 resultaten)\n" +
             "          ")),10000);
     String resultOfItems =  $(titleOfPage).getText()
             .replace("Monitoren (","")
             .replace(" resultaten)","").trim();
     int resultOfItemsInt = Integer.parseInt(resultOfItems);
     System.out.println("resultOfItemsInt = "+ resultOfItemsInt);
     int  allItems = 0;
     int count = resultOfItemsInt/72;
     int countOfPages = count +1;
     for(int i = 1; i<= countOfPages; i++){
         Document document = Jsoup.connect(BASE + monitoren + priceRangeUrl +"1000-5000&shift=" + i).timeout(35000).get();
         System.out.println(BASE + priceRangeUrl +"1000-5000&shift=" + i);
         Elements items = document.select(price);
         for(Element element : items){
         prices.add(element.text().replace(",-","").replace("excl. btw","").trim());
         }
         allItems += items.size();
     }
     System.out.println("items.size() "+allItems);
 }

    @DataProvider(name = "data")
    public Object[][] createData() throws IOException {
        String[] arr = prices.toArray(new String[prices.size()]);
        int a = prices.size();
        String[][] locators = new String[a][1];
        for (int i = 0; i < a; i++) {
            locators[i][0] = arr[i];
        }
        return locators;
    }

    @Test(dataProvider = "data")
    public void test(String price){
        Assert.assertTrue(Integer.valueOf(price)>=1000);
    }

 }



