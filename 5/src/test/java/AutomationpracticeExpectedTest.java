import Pages.of_site.StartPage;
import org.junit.Assert;
import org.junit.Test;

public class AutomationpracticeExpectedTest extends BaseTest {
    StartPage page;

    @Test
    public void test() {
        page = new StartPage();
        page.selectedWomenButton();
        //page.hoverWomenButton();
        Assert.assertTrue(page.isSelectedWomenButton());
        log.info("women button is selected");
        page.clickOnMenuTShirt();
        Assert.assertTrue(page.isUrlTshirt());
        closepage();
    }
}
