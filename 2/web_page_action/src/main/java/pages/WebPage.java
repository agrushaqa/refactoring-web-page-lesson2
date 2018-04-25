package pages;

import get_data.web.Button;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.awt.AWTException;
import java.awt.*;
import java.util.logging.Logger;

public class WebPage {
    protected WebDriver driver;
    private static Robot robot;
    private static Logger log = Logger.getLogger(WebPage.class.getName());

    WebPage()
    {
        try {
            driver = WebSource.getInstance().getDriver();
        }catch (Exception e){
            Assert.fail("Can not set WebDriver");
        }
        try{
            robot = new Robot();
        }catch (AWTException e) {
            log.severe(e.getMessage() + " выбор цвета");
            Assert.fail("Can not set WebDriver");
        }
    }

    public void  moveMouseTo(int x, int y)
    {
        robot.mouseMove(x, y);
    }

    public String getColor(int x, int y){
        robot.mouseMove(x, y);
        Color colors = robot.getPixelColor(x, y);
        return colors.toString();
    }
}
