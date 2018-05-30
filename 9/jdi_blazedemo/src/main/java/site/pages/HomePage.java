package site.pages;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import data.UserPath;
import ru.yandex.qatools.allure.annotations.Step;
import site.forms.CityFromToForm;
import site.forms.SelectFlightForm;

@JPage(url = "http://blazedemo.com/", title = "BlazeDemo")
public class HomePage extends WebPage {

    public static CityFromToForm cityForm;
    public static SelectFlightForm flightForm;
}
