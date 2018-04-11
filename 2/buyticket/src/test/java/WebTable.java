import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebTable {
    WebDriver driver;
    String RowSelector = ".//table/tbody/tr";
    String ColumnSelector = ".//table/tbody/tr/td";
    String HeaderSelector = ".//table/thead/tr/th";
    String FirstRowSelector = ".//table/tbody/tr[1]/td";
    int RowCount = 0;
    int ColumnCount =0;
    List<WebElement> TableElements;

    WebTable(WebDriver wdriver)
    {
        driver = wdriver;

        new WebDriverWait(driver, 10).until(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(ColumnSelector)));

        RowCount = getRowCountInTable();
        ColumnCount = getColumnCountInTable();
        TableElements = getTableWebElements();
    }

    public int size()
    {
        return TableElements.size();
    }

    public int getRowCount()
    {
        return RowCount;
    }

    public int getColumnIndexOfTable(String ColumnTitle)
    {
        int ColumnIndex=0;
        for(WebElement iWebElement: getHeaderOfTableElements()){
            if(iWebElement.getText().equals(ColumnTitle))
                break;
            ColumnIndex+=1;
        }
        return ColumnIndex;
    }

    public WebElement getElementOfTable(int row, int column)
    {
        try {
            return TableElements.get(row*ColumnCount+column);
        }catch(Exception e){
            throw new ArrayIndexOutOfBoundsException("There is not element: "+(row*ColumnCount+column)+"Table size is: "+TableElements.size());
        }
    }

    public List<WebElement> getHeaderOfTableElements()
    {
        return driver.findElements(By.xpath(HeaderSelector));
    }

    public int getRowCountInTable()
    {
        List<WebElement> Row = driver.findElements(By.xpath(RowSelector));
        return Row.size();
    }

    public int getColumnCountInTable()
    {
        List<WebElement> Column = driver.findElements(By.xpath(FirstRowSelector));
        return Column.size();
    }

    public List<WebElement> getTableWebElements()
    {
        return driver.findElements(By.xpath(ColumnSelector));
    }

    public void PrintAllTextOfTableWithIndex()
    {
        for (int j=0;j<TableElements.size();++j)
        {
            System.out.print("index is:"+j);
            System.out.print("\n");
            System.out.print(TableElements.get(j).getText());
            System.out.print("\n");
        }
    }

    public void PrintAllTextOfTable()
    {int i=0;
        for(WebElement iElement:TableElements)
        {
            System.out.print(iElement.getText());
            System.out.print("\n");
            System.out.print("index is:");
            System.out.print("\n");
            TableElements.indexOf(iElement);
            System.out.print("\n");
            i+=1;
        }
    }
}
