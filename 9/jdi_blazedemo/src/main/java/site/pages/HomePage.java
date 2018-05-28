package site.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import data.UserPath;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import site.forms.CityFromToForm;

@JPage(url = "http://blazedemo.com/", title = "BlazeDemo")
public class HomePage extends WebPage {

    public static CityFromToForm cityForm;

    @Step
    public static void next(){
        //cityForm.loginAs( new UserPath());
        cityForm.fill(new UserPath());
    }
}
