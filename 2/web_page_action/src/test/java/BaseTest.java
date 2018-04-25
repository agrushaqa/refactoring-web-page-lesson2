import get_data.json.JsonReader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import pages.WebPage;
import pages.WebSource;

import java.util.logging.Logger;

public class BaseTest {
    Logger log = Logger.getLogger(BaseTest.class.getName());
    JsonReader SourceData; //= new JsonReader("./src/main/resources/config.json");

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

    @After
    public void closepage(){
        WebSource.getInstance().getDriver().quit();
        log.info("Test finished");
    }
}
