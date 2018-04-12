import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WebConfig {
    WebDriver driver;
    String GoFromCity = "Boston";
    String GoToCity = "Berlin";
    int WaitTimeoutInMiliSecond = 2000;

    public WebDriver createCromeDriver(){
        System.setProperty("webdriver.chrome.driver", "E:\\gru_java\\prog\\chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    public WebDriver getDriver(){
        return createCromeDriver();
        /*
        String value =  System.getProperty("webdriver");
        if (value == null) value = "chrome";
        switch (value){
            case "chrome": return createCromeDriver();
            case "firefox": return createFirefoxDriver();
            case "opera": return createOperaDriver();
            case "edge": return createEdgeDriver();
            default: return createCromeDriver();
        } */
    }

    @Before
    public void prepare()
    {
        System.out.print("Test started\n");
        driver = getDriver();
    }

    @Test
    public void test() {
        WebPage1Main page1 = new WebPage1Main(driver, "http://blazedemo.com/");
        List<String> fromCityList = page1.getFromList();
        List<String> toCityList = page1.getToList();

        if (IsCityInList(fromCityList, GoFromCity) && IsCityInList(toCityList, GoToCity)) {
            page1.switchDepartureCity(GoFromCity);
            page1.switchDestinationCity(GoToCity);
            waitLoadingWebPage();
        }
        driver = page1.nextPage();

        WebPage2ChooseFlight page2 = new WebPage2ChooseFlight(driver);
        System.out.print("\n-------------------\n");
        List<Float> PriceList = page2.getAllPrices();
        int IndexMinPrice = PriceList.indexOf(Collections.min(PriceList));;
        Float MinPrice = Collections.min(PriceList);
        System.out.print("Min price :" + Collections.min(PriceList) + "has index " + IndexMinPrice+"\n");
        page2.selectRow(IndexMinPrice);
        String SelectedFlight = page2.getSelectedFlight();
        System.out.print("Flight number: "+SelectedFlight+"\n");
        String SelectedAirline = page2.getSelectedAirline();
        System.out.print("Airline: "+SelectedAirline+"\n");
        driver = page2.nextPage();
        waitLoadingWebPage();
        System.out.print("\n*********************\n");

        WebPage3ReserveFlight page3 = new WebPage3ReserveFlight(driver,
                "Artem",
                "Kremlin",
                "Moscow",
                "Moscow state",
                "09524",
                "4000000000000002",
                "01",
                "2020",
                "Artem Ivanov",
                false );
        page3.PrintFlightData();
        page3.FillPassengerData();
        waitLoadingWebPage();
        assertEquals(page3.getAirline().trim(), SelectedAirline.trim());
        assertEquals(page3.getFlight().trim(), SelectedFlight.trim());
        assertEquals(page3.getPrice(), MinPrice);
        Float TotalCost = page3.getTotalCost();
        Float PriceWithTaxi = MinPrice + page3.getTaxi();
        assertEquals(TotalCost, PriceWithTaxi);
        driver = page3.nextPage();
        waitLoadingWebPage();

        WebPage4Result page4 = new WebPage4Result(driver);
        assertNotNull(page4.getId());
        System.out.print(page4.getId());
        System.out.print("\n");
        assertNotNull(page4.getStatus());
        System.out.print(page4.getStatus());
        System.out.print("\n");
        assertNotNull(page4.getAmount());
        System.out.print(page4.getAmount());
        System.out.print("\n");
        assertEquals("xxxxxxxxxxxx0002", page4.getCard());
        assertNotNull(page4.getCardExpirationDate());
        System.out.print(page4.getCardExpirationDate());
        System.out.print("\n");
        assertNotNull(page4.getDate());
        System.out.print(page4.getDate());
        System.out.print("\n");
    }

    @After
    public void closetest(){
        driver.quit();
        System.out.print("Test finished\n");
    }
    
    private boolean IsCityInList(List<String> CityList, String City)
    {
        for(String iCity: CityList)
        {
            System.out.print(iCity.concat("\n"));
        }
        Collections.sort(CityList);
        int SearchResult = Collections.binarySearch(CityList,City);
        System.out.print(SearchResult+"\n");

        if(SearchResult>=0)
            return true;
        return false;
    }

    private void waitLoadingWebPage()
    {
        try {
            Thread.sleep(WaitTimeoutInMiliSecond);
        } catch (InterruptedException e) {
            System.out.print("Thread.sleep error\n");
        }
    }
}
