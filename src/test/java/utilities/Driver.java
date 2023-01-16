package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    public static WebDriver driver;

    public static WebDriver getDriver(){


        if (driver==null){//Bu if sayesinde kod çalışırken 1 kere new keyword ile driver oluşturulacak
                            //diğer kullanımlarda new devreye girmeyecek.
          switch (ConfigReader.getProperty("browser")){

              case "chrome":
                  WebDriverManager.chromedriver().setup();
                  driver=new ChromeDriver();
                  break;
              case "firefox":
                  WebDriverManager.firefoxdriver().setup();
                  driver=new FirefoxDriver();
                  break;
              case "edgedriver":
                  WebDriverManager.edgedriver().setup();
                  driver=new EdgeDriver();
                  break;


          }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        return driver;
    }
    public static void closeDriver(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }

    }
}
