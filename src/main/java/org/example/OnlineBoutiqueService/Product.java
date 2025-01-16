package org.example.OnlineBoutiqueService;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.*;


import java.time.Duration;
import java.util.List;

public class Product extends LoadableComponent<Product> {

    private final String baseURL = "https://cymbal-shops.retail.cymbal.dev";

    WebDriver driver;

    @FindBy(id = "quantity")
    private WebElement quantitySelector;

    @FindBy(className = "cymbal-button-primary")
    private WebElement addToCartButton ;


    @FindBy(className = "product-wrapper")
    private WebElement productWrapper;

    private String title;
    private String productURI;


    public Product (WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

    }

    public Product (WebDriver driver, String title,String productURI){
        /*this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        this.title = title;*/

        this.driver = driver;
        this.title = title;
        System.out.println(productURI);
        this.productURI = productURI;
        PageFactory.initElements(driver,this);

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
       // System.out.println(driver.findElement(productWrapper).getText());
        return productWrapper.getText().contains(title);
    }
    public WebElement getQuantityList(){
        WebElement quantityList = quantitySelector;
        if (quantityList == null){
            throw new IllegalArgumentException("Quantity Should be in the dom");
        }
        return quantityList;
    }

    public WebElement getAddToCartButton(){
        WebElement addToCartButton = this.addToCartButton;
        if (addToCartButton == null){
            throw new IllegalArgumentException("add to cart button Should be in the dom");
        }
        return addToCartButton;
    }

    public Product setCurrencyByIndex(int index){
        Currency.getInstance(driver).changeCurrencyByIndex(index);
        return this;
    }


    @Override
    protected void load() {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(baseURL + productURI);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains("product"));
    }
}
