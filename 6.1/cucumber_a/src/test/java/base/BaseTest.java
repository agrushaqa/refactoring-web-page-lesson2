package base;

import org.junit.Before;
import web.WebEnvironment;

import java.util.logging.Logger;

public class BaseTest {
    protected Logger log = Logger.getLogger(BaseTest.class.getName());
    protected WebEnvironment browser;

    @Before
    public void prepare()
    {
        log.info("Test started");
    }
}
