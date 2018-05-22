import org.junit.Before;

import java.util.logging.Logger;

public class BaseTest {
    Logger log = Logger.getLogger(BaseTest.class.getName());

    @Before
    public void prepare()
    {
        log.info("Test started");
    }
}
