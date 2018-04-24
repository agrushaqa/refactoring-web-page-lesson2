import get_data.json.JsonReader;
import get_data.web.Button;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.WebPageStart;

import java.util.logging.Logger;

public class CheckColorOnLoginPageTest {
    JsonReader SourceData; //= new JsonReader("./src/main/resources/config.json");
    Logger log = Logger.getLogger(CheckColorOnLoginPageTest.class.getName());
    WebPageStart page;

    @Before
    public void prepare()
    {
        log.info("Test started");
        try {
            SourceData = new JsonReader(System.getProperty("configpath"));
        }catch (Exception e){
            Assert.fail("Can not read config file.");
        }
    }

    @Test
    public void test() {
        page = new WebPageStart(SourceData.get("WebPage"));
        int originalWomentButtonWidth = page.getWomenButton().getWidth();
        int originalWomenButtonHeight  = page.getWomenButton().getHeight();
        page.getWomenButton().moveMouseToCenter();
        //page.moveMouseTo(page.getWomenButton().getLeftTopCorner().x, page.getWomenButton().getLeftTopCorner().y);
        page.getWomenButton().getColor();
        log.info("Background color of button is:");
        log.info(page.getSelectedButton().getColor());
        log.info("Original Button Width: "+ originalWomentButtonWidth);
        log.info("Original Button Height: "+ originalWomenButtonHeight);
        //log.info(page.getColor(page.getWomenButton().getCenterTop().x, page.getWomenButton().getCenterTop().y));
        Assert.assertEquals(originalWomentButtonWidth, page.getWomenButton().getWidth());
        Assert.assertEquals(originalWomenButtonHeight, page.getWomenButton().getHeight());
        Assert.assertEquals("rgba(51, 51, 51, 1)", page.getSelectedButton().getColor());
    }

    @After
    public void closetest(){
        page.close();
    }
}
