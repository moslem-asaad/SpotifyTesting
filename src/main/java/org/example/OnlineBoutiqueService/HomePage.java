package org.example.OnlineBoutiqueService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomePage extends LoadableComponent<HomePage> {

    private WebDriver driver;

    //private By hotProductCardsBy = By.cssSelector(".hot-product-card");

    @FindBy(css = ".hot-product-card")
    private List<WebElement> hotProductCardsBy;

    private final String baseURL = "https://cymbal-shops.retail.cymbal.dev";

    public HomePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver,this);
       /* this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        if (!driver.getTitle().contains("Online Boutique")) {
            throw new IllegalStateException("This is not the Home Page. Current page: " + driver.getCurrentUrl());
        }*/
    }

    @Override
    protected void load() {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(baseURL + "/");
        System.out.println(driver.getCurrentUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getTitle().contains("Online Boutique"));
    }



    public int getHotProductCardCount() {
        return hotProductCardsBy.size();
    }


    public WebElement getHotProductCardByIndex(int index){
        if(index >= getHotProductCardCount() || index < 0){
            throw new IndexOutOfBoundsException("Invalid card index: " + index);
        }
        List<WebElement> hotProducts = hotProductCardsBy;
        return hotProducts.get(index);
    }

    public Product chooseProductByIndex(int index){
        if(index >= getHotProductCardCount() || index < 0){
            throw new IndexOutOfBoundsException("Invalid card index: " + index);
        }
        List<WebElement> hotProducts = hotProductCardsBy;
        WebElement product =  hotProducts.get(index);
        String productURI = product.findElement(By.tagName("a")).getDomProperty("href");
        String title = product.getText();
        product.click();

        return new Product(driver,title,productURI);
    }

    public HomePage setCurrencyByIndex(int index){
        Currency.getInstance(driver).changeCurrencyByIndex(index);
        return this;
    }




}
