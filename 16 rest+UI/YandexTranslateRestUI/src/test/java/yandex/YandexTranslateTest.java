package yandex;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class YandexTranslateTest {

    @Test(dataProvider = "translated_tests")
    public void translateWordTest(String text, String expectedTranslatedText){
        YandexTranslateAPI api = new YandexTranslateAPI();
        YandexTranslateUI ui = new YandexTranslateUI();
        assertThat(api.getTranslatedText(text), ui.getResult(text), equalTo(expectedTranslatedText));
    }

    @DataProvider(name = "translated_tests")
    private Object[][] xssDataProvider(){
        return new Object[][]{
                {"time", "время"},
                {"hello", "привет"}
        };
    }
}
