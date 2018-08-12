package yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class YandexTranslateUI {
    protected static Logger log = Logger.getLogger(YandexTranslateUI.class.getName());
    private String language = "en-ru";
    private static final By INPUT_FIELD = By.id("textarea");
    private static final SelenideElement TRANSLATION_RESULT = $(".translation-chunk");

    public YandexTranslateUI(){
        setSite();
    }

    public void YandexTranslateUI(String customLanguage){
        language = customLanguage;
        setSite();
    }

    private void setSite(){
        try{
            getWebDriver().get("https://translate.yandex.ru/?lang=" + language);
        }catch (Exception e){
            log.severe(e.getMessage());
        }
    }

    public String getResult(String text){
        setText(text);
        TRANSLATION_RESULT.shouldNot(Condition.empty);
        return TRANSLATION_RESULT.getText();
    }

    private void setText(String text){
        $(INPUT_FIELD).clear();
        $(INPUT_FIELD).setValue(text);
        $(INPUT_FIELD).pressEnter();
    }
}
