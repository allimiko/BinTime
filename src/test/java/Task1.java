/**
 * Created by superova on 13.03.2018.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Task1 extends TestBase {
    String BASE = "https://www.centralpoint.nl";
    String itemsString = ".card.landscape.wide";
    String titleOfPage = ".title > h1";
    String zoekenButton = ".filter.active .filterFooter > button";
    String endUrl = "/notebooks-laptops/?priceRange=";
    String priceRangeLowString;
    String priceRangeHighString;
    int itemsOnThePage;

    public String setRandomNumber(){
         priceRangeLowString = $$("#priceRangeLow").last().attr("value");
         priceRangeHighString = $$("#priceRangehigh").last().attr("value");
        int priceRangeLowInt = Integer.parseInt(priceRangeLowString);
        int priceRangeHighInt = Integer.parseInt(priceRangeHighString);
        System.out.println(priceRangeLowInt);
        System.out.println(priceRangeHighInt);
        int randomNumber = priceRangeLowInt + (int) (Math.random() * priceRangeHighInt);
        String randomString = String.valueOf(randomNumber);
        return randomString;
    }

    @Test
    public void test1(){
        open(BASE+"/notebooks-laptops/");
        $$(".mobileSwitchFiltersOff .filterHead").first().click();
        $$(".mobileSwitchFiltersOff .filterHead").shouldHaveSize(18);
        $$("#priceRangeLow").last().val(setRandomNumber());
        System.out.println(setRandomNumber());
        $(zoekenButton).click();
        $(titleOfPage).waitUntil(not(text("\n" +
                "                                    Notebooks/laptops\n" +
                "            \n" +
                "                        (2060 resultaten)\n" +
                "          ")),10000);
    }

    @Test
    public void test2() throws IOException {
        if($(titleOfPage).text().contains("MSI laptop: Gaming GT83VR 7RF(Titan SLI)-216NE - Zwart")){
            itemsOnThePage = 1;
        }else {
            String resultOfItems = $(titleOfPage).getText()
                    .replace("Notebooks/laptops (","")
                    .replace(" resultaten)","").trim();
            int resultOfItemsInt = Integer.parseInt(resultOfItems);
            System.out.println("resultOfItemsInt = "+ resultOfItemsInt);


            if(resultOfItemsInt > 72){
                int  allItems = 0;
                int count = resultOfItemsInt/72;
                int countOfPages = count +1;
                for(int i = 1; i<= countOfPages; i++){
                    Document document = Jsoup.connect(BASE + endUrl +
                            priceRangeLowString +"-"+priceRangeHighString + "&shift=" + i).timeout(35000).get();
                    Elements items = document.select(itemsString);
                    allItems += items.size();
                }
                System.out.println("allItems = " + allItems);
                itemsOnThePage = allItems;
            }else {
                itemsOnThePage = $$(itemsString).size();
            }

            System.out.println("itemsOnThePage = " + itemsOnThePage);
            Assert.assertEquals(resultOfItemsInt, itemsOnThePage);
        }

    }

}
