package site.forms;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import data.Card;
import data.Passenger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class PurchaseForm extends Form<Passenger> {

    @FindBy(css = "input#inputName")
    public TextField name;

    @FindBy(css = "input#address")
    public TextField address;

    @FindBy(css = "input#city")
    public TextField city;

    @FindBy(css = "input#state")
    public TextField state;

    @FindBy(css = "input#zipCode")
    public TextField zipcode;

    @FindBy(css = "input#creditCardNumber")
    public TextField cardNumber;

    @FindBy(css = "input#creditCardMonth")
    public TextField cardExpireMonth;

    @FindBy(css = "input#creditCardYear")
    public TextField cardExpireYear;

    @FindBy(css = "input#nameOnCard")
    public TextField cardHolder;

    @FindBy(css = "input#rememberMe")
    public CheckBox rememberMe;

    @FindBy(css = "input[type=submit]")
    Button next;

    public void start(){
        rememberMe = new CheckBox(By.cssSelector("input#rememberMe"));
        rememberMe.waitDisplayed();

        name = new TextField(By.cssSelector("input#inputName"));
        address = new TextField(By.cssSelector("input#address"));
        city = new TextField(By.cssSelector("input#city"));
        state = new TextField(By.cssSelector("input#state"));
        zipcode = new TextField(By.cssSelector("input#zipCode"));
        cardNumber = new TextField(By.cssSelector("input#creditCardNumber"));
        cardExpireMonth = new TextField(By.cssSelector("input#creditCardMonth"));
        cardExpireYear = new TextField(By.cssSelector("input#creditCardYear"));
        cardHolder = new TextField(By.cssSelector("input#nameOnCard"));
        next = new Button(By.cssSelector("input[type=submit]"));
    }

    public void next(){
        next.click();
    }

    public void selectRememberMe(){
        rememberMe.check();
    }
}
