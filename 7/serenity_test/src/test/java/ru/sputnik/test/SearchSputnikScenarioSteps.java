package ru.sputnik.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.sputnik.pages.ResultPage;
import ru.sputnik.pages.SputnikPage;
import static org.junit.Assert.assertTrue;

public class SearchSputnikScenarioSteps {
    SputnikPage page;
    ResultPage result_page;

    @Given("User open site for search")
    public void userOpenSiteForSearch(){
        page.open();
    }

    @When("User search info about '(.*)'")
    public void userSearchInfoAbout(String word){
        page.search(word);
    }

    @Then("User see list of result")
    public void userSeeListOfResult(){
        assertTrue(result_page.searchResult());
        result_page.outputAllResultLinks();
        assertTrue(result_page.getCountResultLinks() > 0);
    }
}
