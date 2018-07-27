package ya.dictionary;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YandexTranslateTest {
    private static final String BASE_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/";
    private static final String LANGUAGE_URL = BASE_URL + "getLangs?key=";
    private static final String TRANSLATE_URL = BASE_URL + "lookup?key=";
    private static final String API_KEY = "dict.1.1.20180726T213718Z.9e7e36e27241f5c9.1ffac620634a6cbb5f92415f93b6fe2a06b4fbe0";
    private static final String LANGUAGE_PATH = "&lang=en-ru";
    private static final String TEXT_IS = "&text=";
    final static Logger logger = Logger.getLogger(YandexTranslateTest.class);

    @Test
    public void listLanguagesTest() {
        RestAssured.useRelaxedHTTPSValidation(); //SSL


        String listLanguagesResp = given().get(LANGUAGE_URL + API_KEY).body().print();
        logger.info(listLanguagesResp);
        List<String> listLang = from(listLanguagesResp).get();
        assertEquals(listLang.size(), 101);
        assertEquals(listLanguagesResp,"[\"be-be\",\"be-ru\",\"bg-ru\",\"cs-en\",\"cs-ru\",\"da-en\",\"da-ru\",\"de-de\",\"de-en\",\"de-ru\",\"de-tr\",\"el-en\",\"el-ru\",\"en-cs\",\"en-da\",\"en-de\",\"en-el\",\"en-en\",\"en-es\",\"en-et\",\"en-fi\",\"en-fr\",\"en-it\",\"en-lt\",\"en-lv\",\"en-nl\",\"en-no\",\"en-pt\",\"en-ru\",\"en-sk\",\"en-sv\",\"en-tr\",\"en-uk\",\"es-en\",\"es-es\",\"es-ru\",\"et-en\",\"et-ru\",\"fi-en\",\"fi-ru\",\"fi-fi\",\"fr-fr\",\"fr-en\",\"fr-ru\",\"hu-hu\",\"hu-ru\",\"it-en\",\"it-it\",\"it-ru\",\"lt-en\",\"lt-lt\",\"lt-ru\",\"lv-en\",\"lv-ru\",\"mhr-ru\",\"mrj-ru\",\"nl-en\",\"nl-ru\",\"no-en\",\"no-ru\",\"pl-ru\",\"pt-en\",\"pt-ru\",\"ru-be\",\"ru-bg\",\"ru-cs\",\"ru-da\",\"ru-de\",\"ru-el\",\"ru-en\",\"ru-es\",\"ru-et\",\"ru-fi\",\"ru-fr\",\"ru-hu\",\"ru-it\",\"ru-lt\",\"ru-lv\",\"ru-mhr\",\"ru-mrj\",\"ru-nl\",\"ru-no\",\"ru-pl\",\"ru-pt\",\"ru-ru\",\"ru-sk\",\"ru-sv\",\"ru-tr\",\"ru-tt\",\"ru-uk\",\"sk-en\",\"sk-ru\",\"sv-en\",\"sv-ru\",\"tr-de\",\"tr-en\",\"tr-ru\",\"tt-ru\",\"uk-en\",\"uk-ru\",\"uk-uk\"]");
    }

    @Test(dataProvider = "translated_tests")
    public void translateWordTest(String text, String expectedTranslatedText){
        String translatedText = given().get(TRANSLATE_URL + API_KEY + LANGUAGE_PATH + TEXT_IS + text).print();
        logger.info("all translated text" + translatedText);
        String listTranslatedText = JsonPath.read(translatedText, "$.def[0].tr[0].text");
        logger.info("translated text is " + listTranslatedText);
        assertEquals(listTranslatedText, expectedTranslatedText);
    }

    @DataProvider(name = "translated_tests")
    private Object[][] xssDataProvider(){
        return new Object[][]{
                {"time", "время"},
                {"hello", "привет"}
        };
    }
}
