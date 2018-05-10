package web;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TranslateWebPage {
    private static final By.ById EDIT_FIELD_WORD_FOR_TRANSLATE = new By.ById("s");
    private static final By.ByCssSelector BUTTON_START_TRANSLATE = new By.ByCssSelector("[type='submit']");
    protected WebDriver driver;

    public TranslateWebPage(){
        try {
            driver = WebEnvironment.getInstance().getDriver();
        }catch (Exception e){
            Assert.fail("Can not set WebDriver");
        }
    }

    public void open()
    {
        WebEnvironment.getInstance().openWebPage();
    }

    public void close(){
        WebEnvironment.getInstance().closeWebPage();
    }

    public void setWordForTranslate(String word){
        try{
/*            $(EDIT_FIELD_WORD_FOR_TRANSLATE).shouldBe(Condition.visible);
            $(EDIT_FIELD_WORD_FOR_TRANSLATE).val(word).pressEnter();*/

            new WebDriverWait(driver, 10).until(
                    ExpectedConditions.and(
                            ExpectedConditions.visibilityOfAllElementsLocatedBy(EDIT_FIELD_WORD_FOR_TRANSLATE)
                    )
            );

            driver.findElement(EDIT_FIELD_WORD_FOR_TRANSLATE).sendKeys(word);
            driver.findElement(BUTTON_START_TRANSLATE).click();
        }catch (Exception e){
            Assert.fail("Can not set word for translate");
        }
    }

    public void pageHasTranslate(String word){
        try{
            //$(byText(word)).shouldBe(Condition.visible);
            new WebDriverWait(driver, 10).until(
                    ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '"+word+"')]"))
                    )
            );
        }catch (Exception e){
            Assert.fail("Can not find russian translation");
        }

    }
}
