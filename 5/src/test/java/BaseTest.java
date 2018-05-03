import get_data.json.JsonReader;
import get_data.web.WebEnvironment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import java.util.logging.Logger;

public class BaseTest {
    Logger log = Logger.getLogger(BaseTest.class.getName());
    WebEnvironment browser;

    @Before
    public void prepare()
    {
        log.info("Test started");
        browser = new WebEnvironment();
        browser.setDriver();
        browser.openWebPage();
    }

    @After
    public void closepage(){
        browser.close();
        log.info("Test finished");
    }
}
