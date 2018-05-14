package ru.sputnik.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.logging.Logger;

public class ResultPage extends PageObject {
    private static Logger log = Logger.getLogger(ResultPage.class.getName());

    @FindBy(css = ".js-result-list")
    private WebElementFacade panelSearchResult;

    @FindBy(css = ".js-result-list a")
    private List<WebElementFacade> linksArray;

    public boolean searchResult(){
        panelSearchResult.waitUntilVisible();
        return panelSearchResult.isVisible();
    }

    public void getAllResultLinks(){
        for(WebElementFacade iLink: linksArray)
            log.info(iLink.getAttribute("href"));
    }
}
