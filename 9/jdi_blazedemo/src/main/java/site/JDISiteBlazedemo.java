package site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import ru.yandex.qatools.allure.annotations.Step;
import site.pages.HomePage;

@JSite("http://blazedemo.com/")
public class JDISiteBlazedemo extends WebSite {
    public static HomePage homePage;
}
