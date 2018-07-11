package com.xss;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class XSSPage {
    private WebElement editField;
    public XSSPage(String page, By by){
        Configuration.browser = "firefox";
        Configuration.timeout = 3000;
        open(page);
        editField = $(by).shouldBe(Condition.visible);

    }

    public void send(String text){
        editField.clear();
        editField.sendKeys(text);
        editField.sendKeys(Keys.RETURN);
    }

    public void check(){
        Assert.assertNotNull(Selenide.confirm("1"));
    }
}
