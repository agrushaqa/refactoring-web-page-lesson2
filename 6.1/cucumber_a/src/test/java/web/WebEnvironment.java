package web;

import com.codeborne.selenide.Configuration;
import get_data.JsonReader;
import org.junit.Assert;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

public class WebEnvironment {

    protected JsonReader SourceData;
    private String BrowserName;
    protected static Logger log = Logger.getLogger(WebEnvironment.class.getName());

    public WebEnvironment() {
        try {
            SourceData = new JsonReader(System.getProperty("configpath"));
        } catch (Exception e) {
            Assert.fail("Can not read config file.");
        }
        try {
            BrowserName = System.getProperty("browser");
            if (BrowserName.isEmpty())
                BrowserName = "chrome";
        } catch (Exception e) {
            BrowserName = "chrome";
        }
    }

    public void setDriver() {
        switch (BrowserName) {
            case "chrome":
            case "firefox":
                Configuration.browser = BrowserName;
                break;
            default:
                Configuration.browser = "chrome";
        }
    }

    public void openWebPage() {
        log.info(SourceData.get("WebPage"));
        open(SourceData.get("WebPage"));
    }
}
