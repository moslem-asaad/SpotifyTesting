package org.example.OnlineBoutiqueService;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Product {

    WebDriver driver;

    private By quantitySelector = By.id("quantity");

    private By addToCartButton = By.className("cymbal-button-primary");

    private By productWrapper = By.className("product-wrapper");

    private String title;


    public Product (WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }

    public Product (WebDriver driver, String title){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        this.title = title;

    }
    public Product selectQuantityByIndex(int index){
        WebElement quantityList = getQuantityList();
        Select quantities = new Select(quantityList);
        List<WebElement> options =  quantities.getOptions();
        if(index<0 || index>=options.size()){
            throw new IndexOutOfBoundsException("Invalid option index: " + index);
        }
        quantityList.click();
        options.get(index).click();
        quantityList.click();
        return this;
    }

    public Cart addToCart(){
        WebElement addToCartButton = getAddToCartButton();
        addToCartButton.click();
        return new Cart(driver);
    }

    public boolean verifyTheChosenProduct(){
        System.out.println(driver.findElement(productWrapper).getText());
        return driver.findElement(productWrapper).getText().contains(title);
    }
    public WebElement getQuantityList(){
        WebElement quantityList = driver.findElement(quantitySelector);
        if (quantityList == null){
            throw new IllegalArgumentException("Quantity Should be in the dom");
        }
        return quantityList;
    }

    public WebElement getAddToCartButton(){
        WebElement addToCartButton = driver.findElement(this.addToCartButton);
        if (addToCartButton == null){
            throw new IllegalArgumentException("add to cart button Should be in the dom");
        }
        return addToCartButton;
    }

    public Product setCurrencyByIndex(int index){
        Currency.getInstance(driver).changeCurrencyByIndex(index);
        return this;
    }




}
