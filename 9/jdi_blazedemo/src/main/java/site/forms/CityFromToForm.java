package site.forms;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Selector;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import data.UserPath;
import org.openqa.selenium.support.FindBy;

public class CityFromToForm extends Form<UserPath> {
    //public static Dropdown t;

    @FindBy(css = "select[name=fromPort]")
    Selector fromWebElement;

    @FindBy(css = "select[name=toPort]")
    Selector toWebElement;

    @FindBy(css = "input[type=submit]")
    Button next;

    public void selectCityFrom(String FromCity)
    {
        fromWebElement.select(FromCity);
    }

    public void selectCityTo(String ToCity)
    {
        toWebElement.select(ToCity);
    }

    public void next(){
        next.click();
    }
}
