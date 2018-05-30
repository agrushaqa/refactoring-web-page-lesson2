package site.forms;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import data.Card;
import data.Passenger;
import org.openqa.selenium.support.FindBy;

public class PurchaseForm extends Form<Passenger> {

    @FindBy(css = "input#inputName")
    TextField name;

    @FindBy(css = "input#address")
    TextField address;

    @FindBy(css = "input#city")
    TextField city;

    @FindBy(css = "input#state")
    TextField state;

    @FindBy(css = "input#zipCode")
    TextField zipcode;

    @FindBy(css = "input#creditCardNumber")
    TextField cardNumber;

    @FindBy(css = "input#creditCardMonth")
    TextField cardExpireMonth;

    @FindBy(css = "input#creditCardYear")
    TextField cardExpireYear;

    @FindBy(css = "input#nameOnCard")
    TextField cardHolder;

    @FindBy(css = "input#rememberMe")
    CheckBox rememberMe;

    @FindBy(css = "input[type=submit]")
    Button next;

    public void next(){
        next.click();
    }
}
