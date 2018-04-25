import get_data.json.JsonReader;
import get_data.web.Button;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import pages.WebPage;
import pages.WebPageStart;
import pages.WebSource;

import java.awt.*;
import java.util.logging.Logger;

public class CheckColorOnLoginPageTest extends BaseTest {
    WebPageStart page;

    @Test
    public void test() {
        page = new WebPageStart(SourceData.get("WebPage"));
        Dimension originalWomenButton = page.getWomenButton().size();
        page.getWomenButton().moveMouseToCenter();
        //page.moveMouseTo(page.getWomenButton().getLeftTopCorner().x, page.getWomenButton().getLeftTopCorner().y);
        page.getWomenButton().getColor();
        log.info("Background color of button is:");
        log.info(page.getSelectedButton().getColor());
        log.info("Original Button Width: "+ originalWomenButton.getWidth());
        log.info("Original Button Height: "+ originalWomenButton.getHeight());
        //log.info(page.getColor(page.getWomenButton().getCenterTop().x, page.getWomenButton().getCenterTop().y));
        Assert.assertEquals(originalWomenButton, page.getWomenButton().size());
        Assert.assertEquals("rgba(51, 51, 51, 1)", page.getSelectedButton().getColor());
    }
}
