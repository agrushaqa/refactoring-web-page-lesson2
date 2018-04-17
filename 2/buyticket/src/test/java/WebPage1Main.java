import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class WebPage1Main extends WebPage{
    WebElement fromWebElement;
    WebElement toWebElement;
    List<WebElement> fromWebElementList;
    List<WebElement> toWebElementList;
    By.ByCssSelector fromWebElementSelector = new By.ByCssSelector("select[name=fromPort]");
    By.ByCssSelector toWebElementSelector  = new By.ByCssSelector("select[name=toPort]");
    By.ByCssSelector fromWebElementListSelector = new By.ByCssSelector("select[name=fromPort] option");
    By.ByCssSelector toWebElementListSelector = new By.ByCssSelector("select[name=toPort] option");

    WebPage1Main(String WebPage)
    {
        super();
        try{driver.get(WebPage);
        }catch (Exception e){
            Assert.fail("Can not open "+WebPage);
        }



        new WebDriverWait(driver, 10).until(
                ExpectedConditions.and(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(fromWebElementSelector),
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(toWebElementSelector),
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(ButtonSelector)
                )
        );
        fromWebElement = driver.findElement(fromWebElementSelector);
        toWebElement = driver.findElement(toWebElementSelector);
        fromWebElementList = driver.findElements(fromWebElementListSelector);
        toWebElementList = driver.findElements(toWebElementListSelector);
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
