import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static io.github.bonigarcia.wdm.DriverManagerType.*;

public class WebSource {
    public static int implicitWaitTimeout = 100;
    public static int explicitWaitTimeout = 100;
    public static WebDriverWait wait;
    org.openqa.selenium.WebDriver driver;
    JsonReader SourceData = new JsonReader("./src/test/java/config.json");
    final static Logger logger = Logger.getLogger(WebSource.class);
    String current_date = new SimpleDateFormat("dd-MM-yyyy HH-mm ").format(new Date());

    private WebSource() {
        driver = setDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private static class WebDriverHolderInstance {
        private final static WebSource instance = new WebSource();
    }

    public static WebSource getInstance() {
        return WebDriverHolderInstance.instance;
    }

    private org.openqa.selenium.WebDriver setDriver() {
        //return createFirefoxDriver();//createCromeDriver(); //createIEDriver();//

        String my_browser = System.getProperty("browser");
        if (my_browser == null) my_browser = "chrome";
        switch (my_browser) {
            case "chrome":
                return createCromeDriver();
            case "firefox":
                return createFirefoxDriver();
            case "chrome_with_traffic":
                return createChromeDriverWithTraffic();
            //case "opera": return createOperaDriver();
            //case "edge": return createEdgeDriver();
            case "docker":
                return createDockerDriver();
            default:
                return createCromeDriver();
        }
    }

    public org.openqa.selenium.WebDriver createCromeDriver() {
        //System.setProperty("webdriver.chrome.driver", SourceData.get("ChromeLocation"));
        WebDriverManager.getInstance(CHROME).setup();
        driver = new ChromeDriver();
        return driver;
    }

    public WebDriver createChromeDriverWithTraffic() {
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        //DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(options);
        return driver;
    }

    public WebDriver createDockerDriver(){
        //WebDriverManager.getInstance(CHROME).setup();
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setVersion("67.0");
        //caps.setCapability("enableVNC", "true");
        try{
            driver = new RemoteWebDriver(
                    URI.create("http://192.168.56.101:4444/wd/hub").toURL(), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        wait = new WebDriverWait(driver, explicitWaitTimeout);
        driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.SECONDS);
        return driver;
    }

    public org.openqa.selenium.WebDriver createFirefoxDriver() {
        //System.setProperty("webdriver.firefox.driver", SourceData.get("FirefoxLocation"));
        WebDriverManager.getInstance(FIREFOX).setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public org.openqa.selenium.WebDriver createIEDriver() {
        //System.setProperty("webdriver.ie.driver", SourceData.get("IELocation"));
        WebDriverManager.getInstance(IEXPLORER).setup();
        driver = new FirefoxDriver();
        return driver;
    }

    public void FinishTest() {
        logger.info(" --- Finish test with browser:" + System.getProperty("browser"));
        if (null != System.getProperty("browser"))
            if (System.getProperty("browser").equals("chrome_with_traffic")) {
                logger.info("chrome_with_traffic");
                WebDriver driver = WebSource.getInstance().getDriver();
                try {
                    PrintWriter writer = new PrintWriter("./target/" + current_date + "traffic.txt", "UTF-8");
                    List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
                    logger.info("There is " + entries.size() + "string in log traffic");
                    writer.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");
                    for (LogEntry entry : entries) {
                        writer.println(
                                new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        driver.quit();
        logger.info("Test finished\n");
    }
}
