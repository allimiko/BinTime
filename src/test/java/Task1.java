/**
 * Created by superova on 13.03.2018.
 */

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Task1 extends TestBase {
    Random random = new Random();
    String BASE = "https://www.centralpoint.nl";
    String itemsString = ".card.landscape.wide";
    String titleOfPage = ".title > h1";
    String zoekenButton = ".filter.active .filterFooter > button";

    public String setRandomNumber(){
        String priceRangeLowString = $$("#priceRangeLow").last().attr("value");
        String priceRangeHighString = $$("#priceRangehigh").last().attr("value");
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
    public void test2(){
        String resultOfItems = $(titleOfPage).getText()
                .replace("Notebooks/laptops (","")
                .replace(" resultaten)","").trim();
        int resultOfItemsInt = Integer.parseInt(resultOfItems);

    }

}
