package site.pages;

import data.Passenger;
import site.forms.PurchaseForm;

public class WebPage3FlightPurchasePage {
    public static PurchaseForm purchaseForm;

    public void fill(){
        purchaseForm.loginAs(new Passenger());
        purchaseForm.next();
    }
}
