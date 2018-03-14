import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;

import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.util.List;

/**
 * Created by superova on 13.03.2018.
 */
public class TestBase {
  protected   String BASE = "https://www.centralpoint.nl";

    @BeforeSuite(alwaysRun = true)
    public void browser() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = "chrome";
    }

    protected void readFileSetDataToList(String pathToFile, List<String> list){
        try {
            File file = new File(pathToFile);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            list.add(line);
            while (line != null) {
                // System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
                list.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
