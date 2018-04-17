import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebPage3ReserveFlight  extends WebPage{
    String PassengerName;
    String PassengerAddress;
    String PassengerCity;
    String PassengerState;
    String PassengerZipCode;
    String PassengerCardNumber;
    String PassengerCardExpireMonth;
    String PassengerCardExpireYear;
    String PassengerCardHolder;
    boolean RememberPersonalData = false;
    List<WebElement> FlightData;
    Map<String,String> FlightDataDictionary = new HashMap<String,String>();
    String KeyAirline = "Airline";
    String KeyFlight = "Flight Number";
    String KeyPrice = "Price";
    String KeyTax = "Arbitrary Fees and Taxes";
    String KeyTotalCost = "Total Cost";
    By.ByXPath H2Selector = new By.ByXPath(".//h2/following::p");
    By.ByXPath  HeaderSelector = new By.ByXPath(".//table/tbody/tr[1]/td");
    By.ByCssSelector SelectorInputName = new By.ByCssSelector("input#inputName");
    By.ByCssSelector SelectorInputAdress = new By.ByCssSelector("input#address");
    By.ByCssSelector SelectorInputCity = new By.ByCssSelector("input#city");
    By.ByCssSelector SelectorInputState = new By.ByCssSelector("input#state");
    By.ByCssSelector SelectorZipCode = new By.ByCssSelector("input#zipCode");
    By.ByCssSelector SelectorCardNumber = new By.ByCssSelector("input#creditCardNumber");
    By.ByCssSelector SelectorCardExpireMonth = new By.ByCssSelector("input#creditCardMonth");
    By.ByCssSelector SelectorCardExpireYear = new By.ByCssSelector("input#creditCardYear");
    By.ByCssSelector SelectorCardHolder = new By.ByCssSelector("input#nameOnCard");
    By.ByCssSelector SelectorRememberCheckbox = new By.ByCssSelector("input#rememberMe");

    int SearchResultIsFalse = -1;

            WebPage3ReserveFlight(String name,
                                  String address,
                                  String city,
                                  String state,
                                  String zipcode,
                                  String cardnumber,
                                  String cardmonth,
                                  String cardyear,
                                  String cardholder,
                                  boolean remember){
                super();
                PassengerName = name;
                PassengerAddress = address;
                PassengerCity = city;
                PassengerState = state;
                PassengerZipCode = zipcode;
                PassengerCardNumber = cardnumber;
                PassengerCardExpireMonth = cardmonth;
                PassengerCardExpireYear = cardyear;
                PassengerCardHolder = cardholder;
                RememberPersonalData = remember;

                new WebDriverWait(driver, 10).until(
                        ExpectedConditions.and(
                                ExpectedConditions.presenceOfAllElementsLocatedBy(SelectorInputName),
                                ExpectedConditions.presenceOfAllElementsLocatedBy(SelectorCardHolder)
                        )
                );

                getFlightData();
                ConvertFlightDataToDictionary();
    }

    private void getFlightData()
    {
        FlightData = driver.findElements(H2Selector);
    }

    private void ConvertFlightDataToDictionary()
    {
        for(WebElement iElement:FlightData)
        {
            String textOfCurrentElement = iElement.getText();
            if(textOfCurrentElement.lastIndexOf(':') == SearchResultIsFalse)
                break;
            int ColonIndex = textOfCurrentElement.indexOf(':');
            String DictonaryIndex = textOfCurrentElement.substring(0, ColonIndex);
            String DictionaryValue = textOfCurrentElement.substring(ColonIndex+1);
            FlightDataDictionary.put(DictonaryIndex, DictionaryValue);
        }
    }

    public void PrintFlightData()
    {
        for(String iFlightData: FlightDataDictionary.keySet())
        {
            System.out.print(iFlightData);
            System.out.print("\n");
            System.out.print(FlightDataDictionary.get(iFlightData));
            System.out.print("\n");
        }
    }

    public String getAirline()
    {
        return FlightDataDictionary.get(KeyAirline);
    }

    public String getFlight()
    {
        return FlightDataDictionary.get(KeyFlight);
    }

    public Float getPrice()
    {
        return Float.parseFloat(FlightDataDictionary.get(KeyPrice));
    }

    public Float getTaxi()
    {
        return Float.parseFloat(FlightDataDictionary.get(KeyTax));
    }

    public Float getTotalCost()
    {
        return Float.parseFloat(FlightDataDictionary.get(KeyTotalCost));
    }

    public void FillPassengerData()
    {
        setName();
        setAddress();
        setCity();
        setState();
        setZipCode();
        setCardNumber();
        setCardExpireMonth();
        setCardExpireYear();
        setCardHolder();
        if(RememberPersonalData==true)
        {
            setRemember();
        }
    }
    private void setName()
    {
        WebElement Name = driver.findElement(SelectorInputName);
        Name.sendKeys(PassengerName);
    }

    private void setAddress()
    {
        WebElement Address = driver.findElement(SelectorInputAdress);
        Address.sendKeys(PassengerAddress);
    }

    private void setCity()
    {
        WebElement City = driver.findElement(SelectorInputCity);
        City.sendKeys(PassengerCity);
    }

    private void setState()
    {
        WebElement State = driver.findElement(SelectorInputState);
        State.sendKeys(PassengerState);
    }

    private void setZipCode()
    {
        WebElement ZipCode = driver.findElement(SelectorZipCode);
        ZipCode.sendKeys(PassengerZipCode);
    }

    private void setCardNumber()
    {
        WebElement CardNumber = driver.findElement(SelectorCardNumber);
        CardNumber.sendKeys(PassengerCardNumber);
    }

    private void setCardExpireMonth()
    {
        WebElement CardExpireMonth = driver.findElement(SelectorCardExpireMonth);
        CardExpireMonth.sendKeys(PassengerCardNumber);
    }

    private void setCardExpireYear()
    {
        WebElement CardExpireYear = driver.findElement(SelectorCardExpireYear);
        CardExpireYear.sendKeys(PassengerCardNumber);
    }

    private void setCardHolder()
    {
        WebElement CardHolder = driver.findElement(SelectorCardHolder);
        CardHolder.sendKeys(PassengerCardHolder);
    }

    private void setRemember()
    {
        driver.findElement(SelectorRememberCheckbox).click();
    }
}
