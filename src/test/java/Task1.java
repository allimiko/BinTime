/**
 * Created by superova on 13.03.2018.
 */

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Task1  {

    @Test
    public void test1(){
        open("https://www.centralpoint.nl/notebooks-laptops/");
        $$(".mobileSwitchFiltersOff .filterHead").first().click();
        $$(".mobileSwitchFiltersOff .filterHead").shouldHaveSize(18);
        //
    }

}
