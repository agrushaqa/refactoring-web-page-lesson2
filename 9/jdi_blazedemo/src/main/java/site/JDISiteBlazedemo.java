package site;

import com.codeborne.selenide.Condition;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import data.Passenger;
import data.UserPath;
import org.openqa.selenium.By;
import site.forms.CityFromToForm;
import site.forms.PurchaseForm;
import site.forms.ResultForm;
import site.forms.SelectFlightForm;
import site.pages.HomePage;

@JSite("http://blazedemo.com/")
public class JDISiteBlazedemo extends WebSite {
    public static HomePage homePage;
    private static UserPath city = new UserPath();

    public static CityFromToForm setUpPathFrom;
    public static SelectFlightForm selectionForm;
    public static PurchaseForm purchaseForm;
    public static ResultForm resultForm;

    public static void setUpPath(){
        setUpPathFrom.selectCityFrom(city.FromCity);
        setUpPathFrom.selectCityTo(city.ToCity);
        setUpPathFrom.next();
        selectionForm = new SelectFlightForm();
    }

    public static void selectFlight(){
        selectionForm.clickOnLastFlight();
        purchaseForm = new PurchaseForm();
    }

    public static void purchaseFlight(){
        //purchaseForm.get(By.cssSelector("input#inputName"));
        purchaseForm.start();
        //purchaseForm.loginAs();
        purchaseForm.selectRememberMe();
        Passenger user = new Passenger();
        purchaseForm.name.setValue(user.name);
        purchaseForm.address.setValue(user.address);
        purchaseForm.city.setValue(user.city);
        purchaseForm.state.setValue(user.state);
        purchaseForm.zipcode.setValue(user.zipcode);
        purchaseForm.cardNumber.setValue(user.cardNumber);
        purchaseForm.cardExpireMonth.setValue(user.cardExpireMonth);
        purchaseForm.cardExpireYear.setValue(user.cardExpireYear);
        purchaseForm.cardHolder.setValue(user.cardHolder);
        purchaseForm.next();
        resultForm = new ResultForm();
    }

    public static void checkResults(){
        resultForm.start();
    }
}
