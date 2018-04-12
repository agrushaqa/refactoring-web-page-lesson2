import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;

public class WebPage {
    WebDriver driver;
    boolean PageIsVisible=false;
    WebElement submitButton;
    String ButtonSelector = "input[type=submit]";

    WebPage(WebDriver wdriver)
    {
        driver = wdriver;
        PageIsVisible=true;
    }

    protected WebElement getButton()
    {
        return driver.findElement(By.cssSelector(ButtonSelector));
    }


    public WebDriver nextPage()
    {
        getButton().click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        System.out.print("start switch to next page\n");
        PageIsVisible=false;
        return driver;
    }
}
