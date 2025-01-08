package org.example.OnlineBoutiqueService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    private By hotProductCardsBy = By.cssSelector(".hot-product-card");



    public HomePage(WebDriver driver) {
        this.driver = driver;

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (!driver.getTitle().contains("Online Boutique")) {
            throw new IllegalStateException("This is not the Home Page. Current page: " + driver.getCurrentUrl());
        }
    }

    public int getHotProductCardCount() {
        return driver.findElements(hotProductCardsBy).size();
    }


    public WebElement getHotProductCardByIndex(int index){
        if(index >= getHotProductCardCount() || index < 0){
            throw new IndexOutOfBoundsException("Invalid card index: " + index);
        }
        List<WebElement> hotProducts = driver.findElements(hotProductCardsBy);
        return hotProducts.get(index);
    }

    public Product chooseProductByIndex(int index){
        if(index >= getHotProductCardCount() || index < 0){
            throw new IndexOutOfBoundsException("Invalid card index: " + index);
        }
        List<WebElement> hotProducts = driver.findElements(hotProductCardsBy);
        WebElement product =  hotProducts.get(index);
        String title = product.getText();
        product.click();

        return new Product(driver,title);
    }

    public HomePage setCurrencyByIndex(int index){
        Currency.getInstance(driver).changeCurrencyByIndex(index);
        return this;
    }




}
