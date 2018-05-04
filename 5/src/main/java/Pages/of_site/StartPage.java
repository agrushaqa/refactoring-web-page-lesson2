package Pages.of_site;

import Pages.BasePage;
import com.codeborne.selenide.Condition;
import get_data.web.Button;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class StartPage extends BasePage {
    private static final By.ByXPath WONEN_BUTTON = new By.ByXPath("//a[contains(@title,'Women')]/..");
    private static final By.ByCssSelector SELECTED_BUTTON = new By.ByCssSelector("li.sfHover > a[title='Women']");
    private static final By.ByCssSelector TSHIRT_MENU = new By.ByCssSelector("a[title='T-shirts']");
    private static final String URL_IS_TSHIRT = "http://automationpractice.com/index.php?id_category=5&controller=category";
    private static final By.ByXPath WONEN_BUTTON_LINK = new By.ByXPath("//a[contains(@title,'Women')]");

    public StartPage(){
        super();
        try{
            getWebDriver().get("http://automationpractice.com");//(SourceData.get("WebPage"));
        }catch (Exception e){
            log.severe(e.getMessage());
            Assert.fail("Can not open web page");
        }
    }

    public void hoverWomenButton(){
        $(WONEN_BUTTON).shouldBe(Condition.visible);
        $(WONEN_BUTTON).hover();
    }

    //the same method but via Button (for debug and for dz)
    public void selectedWomenButton(){
        Button button = new Button(WONEN_BUTTON);
        button.hover();
        try {
            new WebDriverWait(getWebDriver(), 10).until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(SELECTED_BUTTON)); // TODO delete waiting. waiting performed in isSelectedWomenButton. Need only for dz
        }catch (Exception e){
            button = new Button(WONEN_BUTTON_LINK);
            button.hover();
            //moveMouseTo(button.getLocation().getX(), button.getLocation().getY());
            new WebDriverWait(getWebDriver(), 15).until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(SELECTED_BUTTON)); // It is necessary for Firefox

        }
    }

    public boolean isSelectedWomenButton(){
        try{
            $(SELECTED_BUTTON).shouldBe(Condition.visible);
        }finally {
            return $(SELECTED_BUTTON).exists();
        }
    }

    public void clickOnMenuTShirt(){
        $(TSHIRT_MENU).shouldBe(Condition.visible).click();
    }

    public boolean isUrlTshirt(){
        try {
            new WebDriverWait(getWebDriver(), 10).until(
                    ExpectedConditions.urlToBe(URL_IS_TSHIRT));
            return true;
        }catch (Exception e){
            log.info("Url is:"+url());
          return false;
        }

    }
}
