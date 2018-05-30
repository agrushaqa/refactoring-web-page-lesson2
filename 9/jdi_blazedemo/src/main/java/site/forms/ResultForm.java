package site.forms;

import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import org.openqa.selenium.By;

public class ResultForm {

    public void start() {
        Label h1 = new Label(By.xpath(".//h1"));
        h1.waitDisplayed();
    }
}
