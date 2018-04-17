import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class WebPage4Result extends WebPage {

    By.ByXPath SelectorPre = new By.ByXPath(".//pre");
    By.ByXPath  SelectorTableBody = new By.ByXPath(".//table/tbody");
    By.ByXPath  SelectorH1 = new By.ByXPath(".//h1");
    WebTable wTable;
    Map<String,String> ResultDictionary = new HashMap<String,String>();
    String KeyId = "Id";
    String KeyStatus = "Status";
    String KeyAmount = "Amount";
    String KeyCard = "Card Number";
    String KeyCardExpireDate = "Expiration";
    String KeyDate = "Date";
    int IdColumnWithParameters = 0;
    int IdColumnWithValues = 1;

    WebPage4Result()
    {
        super();

        new WebDriverWait(driver, 10).until(
                ExpectedConditions.and(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(SelectorPre),
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(SelectorTableBody),
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(SelectorH1)
                )
        );

        wTable = new WebTable(driver);

        for(int iRow=0; iRow < wTable.getRowCount();++iRow)
        {
            String currentResultParameter = wTable.getElementOfTable(iRow,IdColumnWithParameters).getText();
            String currentResultValue = wTable.getElementOfTable(iRow,IdColumnWithValues).getText();
            ResultDictionary.put(currentResultParameter, currentResultValue);
        }

    }

    public String getId()
    {
        return ResultDictionary.get(KeyId);
    }

    public String getStatus()
    {
        return ResultDictionary.get(KeyStatus);
    }

    public String getAmount()
    {
        return ResultDictionary.get(KeyAmount);
    }

    public String getCard()
    {
        return ResultDictionary.get(KeyCard);
    }

    public String getCardExpirationDate()
    {
        return ResultDictionary.get(KeyCardExpireDate);
    }

    public String getDate()
    {
        return ResultDictionary.get(KeyDate);
    }
}
