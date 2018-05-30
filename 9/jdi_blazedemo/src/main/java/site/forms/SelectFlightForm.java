package site.forms;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import org.openqa.selenium.support.FindBy;

public class SelectFlightForm {

    @FindBy(css = "input[type=submit]")
    public Button next;

    @FindBy(xpath = ".//table/tbody")
    public Table FlightList;
}
