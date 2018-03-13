import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.testng.annotations.BeforeSuite;
/**
 * Created by superova on 13.03.2018.
 */
public class TestBase {
    @BeforeSuite(alwaysRun = true)
    public void browser() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = "chrome";
    }
}
