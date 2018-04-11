import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    String H2Selector = ".//h2/following::p";
    List<WebElement> FlightData;
    Map<String,String> FlightDataDictionary = new HashMap<String,String>();
    String KeyAirline = "Airline";
    String KeyFlight = "Flight Number";
    String KeyPrice = "Price";
    String KeyTax = "Arbitrary Fees and Taxes";
    String KeyTotalCost = "Total Cost";
    String SelectorInputName = "input#inputName";
    String SelectorInputAdress = "input#address";
    String SelectorInputCity = "input#city";
    String SelectorInputState = "input#state";
    String SelectorZipCode = "input#zipCode";
    String SelectorCardNumber = "input#creditCardNumber";
    String SelectorCardExpireMonth = "input#creditCardMonth";
    String SelectorCardExpireYear = "input#creditCardYear";
    String SelectorCardHolder = "input#nameOnCard";
    String SelectorRememberCheckbox = "input#rememberMe";

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
    }

    public void start(WebDriver wdriver) {
        driver = wdriver;
        PageIsVisible = true;

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.and(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(SelectorInputName)),
                        ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(SelectorCardHolder))
                )
        );

        getFlightData();
        ConvertFlightDataToDictionary();
    }

    private void getFlightData()
    {
        FlightData = driver.findElements(By.xpath(H2Selector));
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
        WebElement Name = driver.findElement(By.cssSelector(SelectorInputName));
        Name.sendKeys(PassengerName);
    }

    private void setAddress()
    {
        WebElement Address = driver.findElement(By.cssSelector(SelectorInputAdress));
        Address.sendKeys(PassengerAddress);
    }

    private void setCity()
    {
        WebElement City = driver.findElement(By.cssSelector(SelectorInputCity));
        City.sendKeys(PassengerCity);
    }

    private void setState()
    {
        WebElement State = driver.findElement(By.cssSelector(SelectorInputState));
        State.sendKeys(PassengerState);
    }

    private void setZipCode()
    {
        WebElement ZipCode = driver.findElement(By.cssSelector(SelectorZipCode));
        ZipCode.sendKeys(PassengerZipCode);
    }

    private void setCardNumber()
    {
        WebElement CardNumber = driver.findElement(By.cssSelector(SelectorCardNumber));
        CardNumber.sendKeys(PassengerCardNumber);
    }

    private void setCardExpireMonth()
    {
        WebElement CardExpireMonth = driver.findElement(By.cssSelector(SelectorCardExpireMonth));
        CardExpireMonth.sendKeys(PassengerCardNumber);
    }

    private void setCardExpireYear()
    {
        WebElement CardExpireYear = driver.findElement(By.cssSelector(SelectorCardExpireYear));
        CardExpireYear.sendKeys(PassengerCardNumber);
    }

    private void setCardHolder()
    {
        WebElement CardHolder = driver.findElement(By.cssSelector(SelectorCardHolder));
        CardHolder.sendKeys(PassengerCardHolder);
    }

    private void setRemember()
    {
        driver.findElement(By.cssSelector(SelectorRememberCheckbox)).click();
    }
}
