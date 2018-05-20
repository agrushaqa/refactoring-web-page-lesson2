package get_data;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;

public class WebBrowser {
    private String BrowserName;

    public WebBrowser(){
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
}
