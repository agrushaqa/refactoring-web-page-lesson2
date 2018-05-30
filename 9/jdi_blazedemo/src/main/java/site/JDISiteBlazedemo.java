package site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import data.UserPath;
import ru.yandex.qatools.allure.annotations.Step;
import site.forms.CityFromToForm;
import site.forms.SelectFlightForm;
import site.pages.HomePage;
import site.pages.WebPage2FlightSelectionPage;
import site.pages.WebPage3FlightPurchasePage;

@JSite("http://blazedemo.com/")
public class JDISiteBlazedemo extends WebSite {
    public static HomePage homePage;
    public static WebPage2FlightSelectionPage flightSelectionPage;
    public static WebPage3FlightPurchasePage purchasePage;
    private static UserPath city = new UserPath();

    public static CityFromToForm setUpPathFrom;

    @Step
    public static void setUpPath(){
        //homePage.next();
        //setUpPathFrom.loginAs(new UserPath());
        setUpPathFrom.selectCityFrom(city.FromCity);
        setUpPathFrom.selectCityTo(city.ToCity);
        setUpPathFrom.next();
    }

    @Step
    public static void selectFlight(){
        flightSelectionPage.clickOnLastFlight();
    }

    @Step
    public static void purchaseFlight(){
        purchasePage.fill();
    }
}
