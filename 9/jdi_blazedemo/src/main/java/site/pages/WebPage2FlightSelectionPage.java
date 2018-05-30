package site.pages;

import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.IColumn;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import site.forms.SelectFlightForm;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

import java.util.ArrayList;
import java.util.List;

@JPage(url = "http://blazedemo.com/reserve.php", title = "BlazeDemo - reserve")
public class WebPage2FlightSelectionPage {
    public static SelectFlightForm flightForm;
    String PriceTitle = "Price";
    String FlightTitle = "Flight #";
    String AirlineTitle = "Airline";
    String FlightButton = "Choose";

    public List<Float> getAllPrices(){
        //ISelect ColumnPrice = flightForm.FlightList.header(PriceTitle).select();
        List<Float> Prices = new ArrayList<Float>();
        MapArray<String, ICell> ColumnPrice = flightForm.FlightList.column(PriceTitle);

        for(Pair<String, ICell> iCell: ColumnPrice.pairs) {
            logger.info("Price value is" + iCell.value.getText());
            String currentPrice = iCell.value.getText();
            if(currentPrice.charAt(0)=='$')
                currentPrice = currentPrice.substring(1);
            Prices.add(Float.parseFloat(currentPrice));
        }
        return Prices;
    }

    public void clickOnLastFlight(){
        logger.info("111");
        IColumn colTableOfListFlight = flightForm.FlightList.columns();
        logger.info("222");
        MapArray<String, ICell> listButtonForSelectFlight = colTableOfListFlight.getColumn(FlightButton);
        logger.info("333");
        Pair<String, ICell> mapLastFlightButton = listButtonForSelectFlight.last();
        logger.info("444");
        mapLastFlightButton.value.click();
        logger.info("555");
    }
}
