import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class WebPage {
    WebDriver driver;
    boolean PageIsVisible=false;
    WebElement submitButton;
    By.ByCssSelector ButtonSelector  = new By.ByCssSelector("input[type=submit]");
    final static Logger logger = Logger.getLogger(WebPage.class);

    WebPage()
    {
        driver = WebSource.getInstance().getDriver();
        PageIsVisible=true;
    }

    protected WebElement getButton()
    {
        return driver.findElement(ButtonSelector);
    }



    public WebDriver nextPage()
    {
        getButton().click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        logger.debug("start switch to next page\n");
        PageIsVisible=false;
        return driver;
    }
}
