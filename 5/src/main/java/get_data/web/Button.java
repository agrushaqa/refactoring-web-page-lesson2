package get_data.web;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Button {
    WebElement button;
    Actions actions;

    public Button(By by){
        button = $(by).shouldBe(Condition.visible);
        actions = new Actions(getWebDriver());
    }

    public void hover(){
        actions.moveToElement(button).build().perform();
    }

    public void click(){
        button.click();
    }

    public boolean isDisplayed(){
        return button.isDisplayed();
    }

    public Point getLocation(){
       return button.getLocation();
    }
}

