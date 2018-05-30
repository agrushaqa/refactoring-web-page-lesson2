package com.blazedemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static site.JDISiteBlazedemo.*;

public class JDITest extends BaseTest {

    @Test
    public void buyAviaTicketFullScenario(){
        WebDriverManager.chromedriver().setup();
        homePage.open();
        setUpPath();
        selectFlight();
        purchaseFlight();
    }

    @Test
    public void testAdd() {
        String str = "TestNG is working fine";
        assertEquals("TestNG is working fine", str) ;
    }
}
