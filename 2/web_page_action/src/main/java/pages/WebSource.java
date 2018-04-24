package pages;

import get_data.json.JsonReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static io.github.bonigarcia.wdm.DriverManagerType.IEXPLORER;

public class WebSource {
    private org.openqa.selenium.WebDriver driver;
    private JsonReader SourceData;

    private WebSource()
    {
        driver = setDriver();
        try {
            SourceData = new JsonReader(System.getProperty("configpath"));
        }catch (Exception e){
            Assert.fail("Can not read config file.");
        }
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    private static class WebDriverHolderInstance{
        private final static WebSource instance = new WebSource();
    }

    public static WebSource getInstance(){
        return WebDriverHolderInstance.instance;
    }

    private WebDriver setDriver(){
        return createCromeDriver();

/*        String value;
        try {
            value =  System.getProperty("browser");
        }catch (Exception e){
            value = "chrome";
        }
        switch (value){
            case "chrome": return createCromeDriver();
            case "firefox": return createFirefoxDriver();
            //case "opera": return createOperaDriver();
            //case "edge": return createEdgeDriver();
            default: return createCromeDriver();
        }*/
    }

    public org.openqa.selenium.WebDriver createCromeDriver(){
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        return driver;
    }

    public org.openqa.selenium.WebDriver createFirefoxDriver(){
        WebDriverManager.getInstance(FIREFOX).setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public org.openqa.selenium.WebDriver createIEDriver(){
        WebDriverManager.getInstance(IEXPLORER).setup();
        driver = new FirefoxDriver();
        return driver;
    }
}
