import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class WebPage2ChooseFlight extends WebPage{
    By.ByCssSelector ButtonSelector = new By.ByCssSelector("input[type=submit]");
    By.ByXPath  HeaderSelector = new By.ByXPath(".//table/tbody/tr[1]/td");
    String PriceTitle = "Price";
    String FlightTitle = "Flight #";
    String AirlineTitle = "Airline";
    int SelectedFlights = 0;
    WebTable wTable;

    WebPage2ChooseFlight()
    {
        super();
        wTable = new WebTable(driver);

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(HeaderSelector));
    }

    public List<Float> getAllPrices()
    {
        List<Float> Prices = new ArrayList<Float>();
        int ColumnPrice=wTable.getColumnIndexOfTable(PriceTitle);

        for (int i=0; i<wTable.getRowCount(); ++i)
        {
            if(i * wTable.getRowCount() + ColumnPrice >= wTable.size())
                break;
            String currentPrice = wTable.getElementOfTable(i,ColumnPrice).getText();
            if(currentPrice.charAt(0)=='$')
            {
                currentPrice = currentPrice.substring(1);
            }
            Prices.add(Float.parseFloat(currentPrice));
        }
        return Prices;
    }

    public String getSelectedFlight()
    {
        int ColumnPrice = wTable.getColumnIndexOfTable(FlightTitle);
        return wTable.getElementOfTable(SelectedFlights, ColumnPrice).getText();
    }

    public String getSelectedAirline()
    {
        int ColumnPrice = wTable.getColumnIndexOfTable(AirlineTitle);
        return wTable.getElementOfTable(SelectedFlights, ColumnPrice).getText();
    }

    public void selectRow(int selectedRow)
    {
        SelectedFlights = selectedRow;
    }

    protected WebElement getButton()
    {
        return driver.findElements(ButtonSelector).get(SelectedFlights);
    }
}
