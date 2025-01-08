package org.example.OnlineBoutiqueService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Cart {

    private WebDriver driver;

    private By placeOrderButton = By.cssSelector(".cymbal-button-primary[type='submit']");


    public Cart(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public Checkout makeOrder(){
        WebElement makeOrderButton = driver.findElement(placeOrderButton);
        makeOrderButton.click();
        return new Checkout(driver);
    }

    public Cart setCurrencyByIndex(int index){
        Currency.getInstance(driver).changeCurrencyByIndex(index);
        return this;
    }
}
