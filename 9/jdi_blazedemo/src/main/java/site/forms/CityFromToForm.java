package site.forms;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import data.UserPath;
import org.openqa.selenium.support.FindBy;

public class CityFromToForm extends Form<UserPath> {
    @FindBy(css = "select[name=fromPort]")
    public Dropdown fromCity;

    @FindBy(css = "select[name=toPort]")
    public Dropdown toCity;

    @FindBy(css = "input[type=submit]")
    public Button FindFlights;
}
