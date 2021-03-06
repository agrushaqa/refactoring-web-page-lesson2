package Translate;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;
import web.*;

import java.util.logging.Logger;

public class TranslateSteps extends BaseTest {
    private static Logger log = Logger.getLogger(TranslateSteps.class.getName());
    private TranslateWebPage page;

    @Before
    public void before_calling_scenario(){
        log.info("Before");
    }

    @After
    public void after_calling_scenario(){
        log.info("after_after");
        page.close();
    }

    @Given("^word for translate$")
    public void word_for_translate(){
        log.info("Start cucumber test");
        page = new TranslateWebPage();
        page.open();
    }

    @When("^I search translate for \"(.*)\"")
    public void I_search_translate_for(String word)
    {
        log.info("set word for tranlate");
        page.setWordForTranslate(word);
    }

    @Then("^translation page contains \"(.*)\"")
    public void page_contains(String russian_translation){
        log.info("translation word:");
        log.info(russian_translation);
        page.pageHasTranslate(russian_translation);
    }
}
