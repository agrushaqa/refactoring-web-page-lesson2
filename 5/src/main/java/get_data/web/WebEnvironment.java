package get_data.web;

import com.codeborne.selenide.Configuration;
import get_data.json.JsonReader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class WebEnvironment {
    protected JsonReader SourceData;
    private String BrowserName;
    protected static Logger log = Logger.getLogger(WebEnvironment.class.getName());

    public WebEnvironment(){
        try {
            SourceData = new JsonReader(System.getProperty("configpath"));
        }catch (Exception e){
            Assert.fail("Can not read config file.");
        }
        try {
            BrowserName = System.getProperty("browser");
            if (BrowserName.isEmpty())
                BrowserName = "chrome";
        }catch (Exception e){
            BrowserName = "chrome";
        }
    }

    public void setDriver(){
        switch (BrowserName){
            case "chrome": Configuration.browser = BrowserName; break;
            case "firefox": Configuration.browser = BrowserName; break;
            case "opera": createOperaDriver(); break;
            case "edge": createEdgeDriver(); break;
            case "ie": createIEDriver(); break;
            case "headless": createChromeHeadlessDriver(); break;
            default: Configuration.browser = "chrome";
        }
    }

    public void openWebPage(){
        open((SourceData.get("WebPage")));
    }

    private void createOperaDriver(){
        String OperaDriverPath = "lib/opera/operadriver.exe";
        System.setProperty("webdriver.opera.driver", OperaDriverPath);
        OperaOptions options = new OperaOptions();
        options.setBinary(SourceData.get("Opera"));
        setWebDriver(new OperaDriver(options));
    }

    private void createEdgeDriver(){
        //Configuration.browser = "edge";
        System.setProperty("webdriver.edge.driver", SourceData.get("Edge"));
        String USERNAME = SourceData.get("BrowserstackUserName");
        String AUTOMATE_KEY = SourceData.get("BrowserstackPass");
        String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
        try {
            java.net.URL browserStackUrl = new URL(URL);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "edge");
            caps.setCapability("browser_version", "14.0");
            caps.setCapability("os", "Windows");
            caps.setCapability("os_version", "10");
            caps.setCapability("resolution", "1024x768");
            caps.setCapability("browserstack.local", "true");

            WebDriver driver = new RemoteWebDriver(browserStackUrl, caps);
            driver.get("http://www.google.com");
        }catch (MalformedURLException e){
            Assert.fail("browserStackUrl is invalid");
        }catch (Exception e){
            Assert.fail("fail to driver initialize");
        }
    }

    private void createIEDriver(){
        Configuration.browserBinary = SourceData.get("IE");
        //DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("ignoreProtectedModeSettings", false);
        //capabilities.setCapability("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", false);
        //setWebDriver(new InternetExplorerDriver(capabilities));
    }

    private void createChromeHeadlessDriver(){
        Configuration.browser = "chrome";
        Configuration.headless = true;
    }

    public void close(){
        switch (BrowserName) {
            case "opera":
                getWebDriver().quit();
        }
    }
}
