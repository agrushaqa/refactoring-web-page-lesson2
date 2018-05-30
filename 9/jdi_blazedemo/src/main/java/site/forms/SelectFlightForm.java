package site.forms;

import com.codeborne.selenide.Condition;
import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.IColumn;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

public class SelectFlightForm {

    @FindBy(css = "input[type=submit]")
    public Button next;

    @FindBy(xpath = ".//table/tbody")
    public Table FlightList;

    public void clickOnLastFlight(){
   /*     Table FlightList = new Table(By.xpath(".//table/tbody"));
        String FlightButton = "Choose";

        logger.info("111");
        IColumn colTableOfListFlight = FlightList.columns();
        ICell buttonNext = FlightList.cell();
        */
        Button next = new Button(By.cssSelector("tr:nth-child(1) input.btn.btn-small"));
        next.click();
        Table FlightList = new Table(By.cssSelector(".table > tbody"));
        //FlightList.shouldHave(Condition.not(Condition.visible));
/*        logger.info("222");
        MapArray<String, ICell> listButtonForSelectFlight = colTableOfListFlight.getColumn(FlightButton);
        logger.info("333");
        Pair<String, ICell> mapLastFlightButton = listButtonForSelectFlight.last();
        logger.info("444");
        mapLastFlightButton.value.click();
        logger.info("555");*/
    }

    public List<Float> getAllPrices(){
        //ISelect ColumnPrice = flightForm.FlightList.header(PriceTitle).select();
        List<Float> Prices = new ArrayList<Float>();
        String PriceTitle = "Price";
        MapArray<String, ICell> ColumnPrice = FlightList.column(PriceTitle);

        for(Pair<String, ICell> iCell: ColumnPrice.pairs) {
            logger.info("Price value is" + iCell.value.getText());
            String currentPrice = iCell.value.getText();
            if(currentPrice.charAt(0)=='$')
                currentPrice = currentPrice.substring(1);
            Prices.add(Float.parseFloat(currentPrice));
        }
        return Prices;
    }
}
