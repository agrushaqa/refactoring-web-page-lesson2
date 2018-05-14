package ru.sputnik.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.sputnik.ru/")
public class SputnikPage extends PageObject {

    @FindBy(id = "js-search-input")
    private WebElementFacade forSearchEditField;

    public void search(String text){
        forSearchEditField.type(text);
        forSearchEditField.submit();
    }
}
