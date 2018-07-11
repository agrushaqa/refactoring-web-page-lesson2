package com.xss;


import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class XSSTest {
    private static final String PAGE = "http://s91531-101020-ryd.croto.hack.me/xss1.php";
    private static final By.ByCssSelector XSS_EDIT_FIELD = new By.ByCssSelector("input[name='key']");

    @Test(dataProvider = "xsstests")
    public void test(String name, String XSSQuery){
        XSSPage page = new XSSPage(PAGE, XSS_EDIT_FIELD);
        page.send(XSSQuery);
        page.check();
    }

    @DataProvider(name = "xsstests")
    private Object[][] xssDataProvider(){
        return new Object[][]{
                {"xss1", "<script>alert('1');</script>"},
                {"xss2", "<scr<script>ipt>alert(1)</scr</script>ipt>"}
        };
    }
}
