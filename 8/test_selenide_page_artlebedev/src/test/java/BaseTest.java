import get_data.WebBrowser;
import org.junit.Before;

import java.util.logging.Logger;

public class BaseTest {
    Logger log = Logger.getLogger(BaseTest.class.getName());
    WebBrowser browser;

    @Before
    public void prepare()
    {
        log.info("Test started");
        browser = new WebBrowser();
        browser.setDriver();
    }
}
