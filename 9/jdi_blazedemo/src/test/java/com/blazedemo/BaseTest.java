package com.blazedemo;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeSuite;
import site.JDISiteBlazedemo;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

public class BaseTest extends TestNGBase {

    @BeforeSuite()
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        WebSite.init(JDISiteBlazedemo.class);
        logger.info("start blazedemo test");
    }
}
