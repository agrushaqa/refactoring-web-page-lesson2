package yandex;

import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class YandexTranslateAPI {
    private static final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
    private static final String API_KEY = "trnsl.1.1.20180725T201522Z.9533dc3ce3c02c34.e97d3f9cb1add14b37d45d7eac569ef887e03a84";
    private static final String TEXT_IS = "&text=";
    private static final String LANGUAGE_PATH = "&lang=";
    private String language = "en-ru";
    final static Logger logger = Logger.getLogger(YandexTranslateAPI.class);

    public void YandexTranslateAPI(){
    }

    public void YandexTranslateAPI(String customLanguage){
         language = customLanguage;
    }

    public String getTranslatedText(String textForTranslate){
        String translatedText = given().get(BASE_URL + API_KEY + LANGUAGE_PATH + language + TEXT_IS + textForTranslate).print();
        logger.info("translated text" + translatedText);
        return JsonPath.read(translatedText, "text[0]");
    }
}
