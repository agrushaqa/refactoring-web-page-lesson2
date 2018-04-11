import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class WebPage1Main extends WebPage{
    List<String> FromList;
    List<String> ToList;
    WebElement fromWebElement;
    WebElement toWebElement;
    List<WebElement> fromWebElementList;
    List<WebElement> toWebElementList;
    WebElement submitButton;
    String fromWebElementSelector = "select[name=fromPort]";
    String toWebElementSelector = "select[name=toPort]";

    WebPage1Main(WebDriver wdriver)
    {
        driver = wdriver;
        PageIsVisible=true;
        driver.get("http://blazedemo.com/");

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.and(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(fromWebElementSelector)),
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(toWebElementSelector)),
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(ButtonSelector))
                )
        );
        fromWebElement = wdriver.findElement(By.cssSelector(fromWebElementSelector));
        toWebElement = wdriver.findElement(By.cssSelector(toWebElementSelector));
        fromWebElementList = wdriver.findElements(By.cssSelector("select[name=fromPort] option"));
        toWebElementList = wdriver.findElements(By.cssSelector("select[name=toPort] option"));
    }

    List<String> getFromList()
    {
        return getTextOfAllElementsInList(fromWebElementList);
    }

    List<String> getToList()
    {
        return getTextOfAllElementsInList(toWebElementList);
    }

    public void switchDepartureCity(String FromCity)
    {
        Select selectFromList = new Select(fromWebElement);
        selectFromList.selectByVisibleText(FromCity);
    }

    public void switchDestinationCity(String ToCity)
    {
        Select selectToList = new Select(toWebElement);
        selectToList.selectByVisibleText(ToCity);
    }

    private List<String> getTextOfAllElementsInList(List<WebElement> listWebElement){
        List<String> listPopupText = new ArrayList<String>();
        for(WebElement iWebElement: listWebElement){
            listPopupText.add(iWebElement.getText());
        }
        return listPopupText;
    }
}
