package get_data.web;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.WebSource;

import java.util.logging.Logger;

public class Button {
    By by;
    WebDriver driver;
    WebElement button;
    Actions actions;
    Dimension button_size;
    private static Logger log = Logger.getLogger(Button.class.getName());

    public Button(By by){
        this.by = by;
        try{
            driver = WebSource.getInstance().getDriver();
            new WebDriverWait(driver, 10).until(
                    ExpectedConditions.and(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(by)
                    )
            );
            button = driver.findElement(by);
            actions = new Actions(driver);
            button_size = button.getSize();
        }catch (Exception e){
            Assert.fail("Can not find button "+by.toString());
        }

    }

    public Point getLeftTopCorner(){
        return button.getLocation();
    }

    public Point getCenterLocation(){
        int x = button.getLocation().x + button_size.getWidth()/2;
        int y = button.getLocation().y + button_size.getHeight()/2;
        return new Point(x, y);
    }

    public Point getCenterTop(){
        int x = button.getLocation().x + button_size.getWidth()/2;
        int y = button.getLocation().y + button_size.getHeight()/4;
        return new Point(x, y);
    }

    public String getColor(){
        //log.info("background-color: " + button.getCssValue("background-color"));
        //log.info("color: " + button.getCssValue("color"));
        return button.getCssValue("background-color");
    }

    public int getWidth(){
       return button_size.getWidth();
    }

    public int getHeight(){
        return button_size.getHeight();
    }

    public void moveMouseToCenter(){
        actions.moveToElement(button).build().perform();
    }

    public void moveMouseOnTop(){
        actions.moveToElement(button, 0, button_size.getHeight()/4).build().perform();
    }
}
