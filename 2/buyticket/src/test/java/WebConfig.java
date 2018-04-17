import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WebConfig {

    String GoFromCity;
    String GoToCity;

    int WaitTimeoutInMiliSecond = 2000;
    JsonReader SourceData = new JsonReader("./src/test/java/config.json");

    @Before
    public void prepare()
    {
        System.out.print("Test started\n");
    }

    @Test
    public void test() {
        WebPage1Main page1 = new WebPage1Main(SourceData.get("WebPage"));
        GoFromCity = SourceData.get("FromCity");
        GoToCity = SourceData.get("ToCity");
        List<String> fromCityList = page1.getFromList();
        List<String> toCityList = page1.getToList();

        if (IsCityInList(fromCityList, GoFromCity) && IsCityInList(toCityList, GoToCity)) {
            page1.switchDepartureCity(GoFromCity);
            page1.switchDestinationCity(GoToCity);
            waitLoadingWebPage();
        }
        page1.nextPage();

        WebPage2ChooseFlight page2 = new WebPage2ChooseFlight();
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
        page2.nextPage();
        waitLoadingWebPage();
        System.out.print("\n*********************\n");

        WebPage3ReserveFlight page3 = new WebPage3ReserveFlight(SourceData.get("PassengerName"),
                SourceData.get("PassengerAddress"),
                SourceData.get("PassengerCity"),
                SourceData.get("PassengerState"),
                SourceData.get("PassengerZipCode"),
                SourceData.get("CardNumber"),
                SourceData.get("CardMonth"),
                SourceData.get("CardYear"),
                SourceData.get("CardHolder"),
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
        page3.nextPage();
        waitLoadingWebPage();

        WebPage4Result page4 = new WebPage4Result();
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
        WebSource.getInstance().FinishTest();
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