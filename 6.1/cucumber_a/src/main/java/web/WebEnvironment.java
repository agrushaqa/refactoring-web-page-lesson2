package web;

import get_data.JsonReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class WebEnvironment {

    private JsonReader SourceData;
    private String BrowserName = "chrome";
    private static Logger log = Logger.getLogger(WebEnvironment.class.getName());
    private org.openqa.selenium.WebDriver driver;
    private static WebEnvironment instance;

    private WebEnvironment() {
        try {
            SourceData = new JsonReader(System.getProperty("configpath"));
        } catch (Exception e) {
            Assert.fail("Can not read config file.");
        }
        try {
            log.info("Browser in CLI is "+System.getProperty("browser"));
            if (System.getProperty("browser").isEmpty())
                BrowserName = "chrome";
            else
                BrowserName = System.getProperty("browser");
            log.info("Browser name is:" + BrowserName);
        } catch (Exception e) {
            BrowserName = "chrome";
        }
        log.info("driver was initialize here");
        driver = setDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public static synchronized WebEnvironment getInstance(){
        if(instance == null) {
            instance = new WebEnvironment();
            log.info("driver was initialize");
        }
        return instance;
    }

    private WebDriver setDriver() {
       // return createRemoteChromeDriver();
        log.info("Browser name is "+BrowserName);
        switch (BrowserName) {
            case "chrome":
                log.info("Select Chrome browser");
                return createChromeDriver();
            case "firefox":
                log.info("Select Firefox browser");
                return createFirefoxDriver();
            case "headless":
                log.info("Select Headless browser");
                return createHeadlessChromeDriver();
            case "remote":
                log.info("Select remote web browser");
                return createRemoteChromeDriver();
            default:
                log.info("Select (default) Chrome browser");
                return createChromeDriver();
        }
    }

    public org.openqa.selenium.WebDriver createChromeDriver(){
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        return driver;
    }

    public org.openqa.selenium.WebDriver createRemoteChromeDriver() {
        try {
            log.info("--- Usage remove web driver ---");
            log.info("Hub url is "+SourceData.get("HubUrl"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
            driver = new RemoteWebDriver(new URL(SourceData.get("HubUrl")), options);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return driver;
    }

    public org.openqa.selenium.WebDriver createHeadlessChromeDriver(){
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        return driver;
    }

    public org.openqa.selenium.WebDriver createFirefoxDriver(){
        WebDriverManager.getInstance(FIREFOX).setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public void openWebPage() {
        log.info(SourceData.get("WebPage"));
        //open(SourceData.get("WebPage"));
        try{
            driver.get(SourceData.get("WebPage"));
        }catch (Exception e){
                Assert.fail("Can not open " + SourceData.get("WebPage"));
                e.printStackTrace();
        }
    }

    public void closeWebPage()
    {
        getInstance().getDriver().quit();
        instance = null;
    }
}
