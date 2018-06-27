import com.automation.remarks.video.annotations.Video;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.util.Collections;
import java.util.List;

//@Listeners({TestListener.class, VideoListener.class})
@Listeners(TestListener.class)
public class WebConfig {

    String GoFromCity;
    String GoToCity;
    final static Logger logger = Logger.getLogger(WebConfig.class);

    int WaitTimeoutInMiliSecond = 2000;
    JsonReader SourceData = new JsonReader("./src/test/java/config.json");

    @BeforeTest
    public void prepare()
    {
        logger.info("Test started\n");
    }

    @org.testng.annotations.Test
    @Video
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
        //assertTrue(false);
        logger.debug("\n-------------------\n");
        List<Float> PriceList = page2.getAllPrices();
        int IndexMinPrice = PriceList.indexOf(Collections.min(PriceList));;
        Float MinPrice = Collections.min(PriceList);
        logger.debug("Min price :" + Collections.min(PriceList) + "has index " + IndexMinPrice+"\n");
        page2.selectRow(IndexMinPrice);
        String SelectedFlight = page2.getSelectedFlight();
        logger.debug("Flight number: "+SelectedFlight+"\n");
        String SelectedAirline = page2.getSelectedAirline();
        logger.debug("Airline: "+SelectedAirline+"\n");
        page2.nextPage();
        waitLoadingWebPage();
        logger.debug("\n*********************\n");

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
        Assert.assertEquals(page3.getAirline().trim(), SelectedAirline.trim());
        Assert.assertEquals(page3.getFlight().trim(), SelectedFlight.trim());
        Assert.assertEquals(page3.getPrice(), MinPrice);
        Float TotalCost = page3.getTotalCost();
        Float PriceWithTaxi = MinPrice + page3.getTaxi();
        Assert.assertEquals(TotalCost, PriceWithTaxi);
        page3.nextPage();
        waitLoadingWebPage();

        WebPage4Result page4 = new WebPage4Result();
        Assert.assertNotNull(page4.getId());
        logger.debug(page4.getId());
        logger.debug("\n");
        Assert.assertNotNull(page4.getStatus());
        logger.debug(page4.getStatus());
        logger.debug("\n");
        Assert.assertNotNull(page4.getAmount());
        logger.debug(page4.getAmount());
        logger.debug("\n");
        Assert.assertEquals("xxxxxxxxxxxx0002", page4.getCard());
        Assert.assertNotNull(page4.getCardExpirationDate());
        logger.debug(page4.getCardExpirationDate());
        logger.debug("\n");
        Assert.assertNotNull(page4.getDate());
        logger.debug(page4.getDate());
        logger.debug("\n");
    }

    @AfterTest
    public void closetest(){
        WebSource.getInstance().FinishTest();
    }

    private boolean IsCityInList(List<String> CityList, String City)
    {
        for(String iCity: CityList)
        {
            logger.debug(iCity.concat("\n"));
        }
        Collections.sort(CityList);
        int SearchResult = Collections.binarySearch(CityList,City);
        logger.debug(SearchResult+"\n");

        if(SearchResult>=0)
            return true;
        return false;
    }

    private void waitLoadingWebPage()
    {
        try {
            Thread.sleep(WaitTimeoutInMiliSecond);
        } catch (InterruptedException e) {
            logger.error("Thread.sleep error\n");
        }
    }
}