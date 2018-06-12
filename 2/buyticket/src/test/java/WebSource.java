import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static io.github.bonigarcia.wdm.DriverManagerType.IEXPLORER;

public class WebSource {
    org.openqa.selenium.WebDriver driver;
    JsonReader SourceData = new JsonReader("./src/test/java/config.json");

    private WebSource()
    {
        driver = setDriver();
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    private static class WebDriverHolderInstance {
        private final static WebSource instance = new WebSource();
    }

    public static WebSource getInstance(){
        return WebDriverHolderInstance.instance;
    }

    private org.openqa.selenium.WebDriver setDriver(){
        //return createFirefoxDriver();//createCromeDriver(); //createIEDriver();//

        String value =  System.getProperty("browser");
        if (value == null) value = "chrome";
        switch (value){
            case "chrome": return createCromeDriver();
            case "firefox": return createFirefoxDriver();
            //case "opera": return createOperaDriver();
            //case "edge": return createEdgeDriver();
            default: return createCromeDriver();
        }
    }

    public org.openqa.selenium.WebDriver createCromeDriver(){
        //System.setProperty("webdriver.chrome.driver", SourceData.get("ChromeLocation"));
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        return driver;
    }

    public org.openqa.selenium.WebDriver createFirefoxDriver(){
        //System.setProperty("webdriver.firefox.driver", SourceData.get("FirefoxLocation"));
        WebDriverManager.getInstance(FIREFOX).setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public org.openqa.selenium.WebDriver createIEDriver(){
        //System.setProperty("webdriver.ie.driver", SourceData.get("IELocation"));
        WebDriverManager.getInstance(IEXPLORER).setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public void FinishTest(){
        driver.quit();
        System.out.print("Test finished\n");
    }
}
