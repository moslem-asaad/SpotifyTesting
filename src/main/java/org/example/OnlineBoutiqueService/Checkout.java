package org.example.OnlineBoutiqueService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout {

    WebDriver driver;

    private By tagH3 = By.tagName("h3");

    public Checkout(WebDriver driver){
        this.driver= driver;
    }

    public boolean isSuccessfullyOrdered(){
        return driver.findElement(tagH3).getText().contains("Your order is complete!");
    }

}
