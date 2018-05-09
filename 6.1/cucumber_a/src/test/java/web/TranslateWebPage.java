package web;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TranslateWebPage {
    private static final By.ById EDIT_FIELD_WORD_FOR_TRANSLATE = new By.ById("s");
    private static final By.ByCssSelector BUTTON_START_TRANSLATE = new By.ByCssSelector("[type='submit']");

    public void setWordForTranslate(String word){
        try{
            $(EDIT_FIELD_WORD_FOR_TRANSLATE).shouldBe(Condition.visible);
            $(EDIT_FIELD_WORD_FOR_TRANSLATE).val(word).pressEnter();
        }catch (Exception e){
            Assert.fail("Can not set word for translate");
        }
    }

    public void pageHasTranslate(String word){
        try{
            $(byText(word)).shouldBe(Condition.visible);
        }catch (Exception e){
            Assert.fail("Can not find russian translation");
        }

    }
}
