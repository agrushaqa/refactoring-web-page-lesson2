package pages;

import get_data.web.Button;
import org.junit.Assert;
import org.openqa.selenium.By;

public class WebPageStart extends WebPage {
    private static final By.ByXPath WONEN_BUTTON = new By.ByXPath("//a[contains(@title,'Women')]/..");
    private static final By.ByXPath WONEN_BUTTON_LINK = new By.ByXPath("//a[contains(@title,'Women')]");//By.ByCssSelector("button[type='submit']");
    private static final By.ByCssSelector SELECTED_BUTTON = new By.ByCssSelector("li.sfHover > a");
    private Button women;
    private Button womenLink;

    public WebPageStart(String WebPage){
        super();
        try{
            driver.get(WebPage);
        }catch (Exception e){
            Assert.fail("Can not open "+WebPage);
        }
        women = new Button(WONEN_BUTTON);
        womenLink = new Button(WONEN_BUTTON);
    }

    public Button getWomenButton(){
        return women;
    }

    public Button getWomenButtonLink(){
        return womenLink;
    }

    public Button getWomenSelected(){
        return womenLink;
    }

    public Button getSelectedButton(){
        return new Button(SELECTED_BUTTON);
    }
}
