import org.example.DriverFactory;
import org.example.LoginPage;
import org.example.OnlineBoutiqueService.Currency;
import org.example.OnlineBoutiqueService.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.OnlineBoutiqueService.HomePage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class OnlineBoutiqueServiceTest {

    private WebDriver driver;

    private HomePage homePage;



    @BeforeEach
    public void setUp() throws MalformedURLException {
        driver = DriverFactory.getDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        driver = new RemoteWebDriver(new URL("http://16.171.132.126:4444"), options);
        homePage = new HomePage(driver).get();

        /*driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.get("https://cymbal-shops.retail.cymbal.dev/");
        homePage = new HomePage(driver).get();*/
    }

    @Test
    public void testHotProduct1() {
        WebElement hotProduct = homePage.getHotProductCardByIndex(0);
        assertTrue(hotProduct.getText().contains("Sunglasses"));
    }

    @Test
    public void verifyProductSelected(){
        Product product = homePage.chooseProductByIndex(0);/*.selectQuantityByIndex(3).addToCart()*/
        assertTrue(product.verifyTheChosenProduct());
    }

    @Test
    public void verifyQuantityListElementExistsInProductPage(){
        Product product = homePage.chooseProductByIndex(0);
        assertDoesNotThrow(product::getQuantityList);
    }

    @Test
    public void testAddToCartWorksWellInProductPage(){
        Product product = homePage.chooseProductByIndex(0);
        assertDoesNotThrow(product::getAddToCartButton);
        WebElement addToCartButton = product.getAddToCartButton();
        assertTrue(addToCartButton.getText().contains("Add To Cart"));
        assertTrue(addToCartButton.isEnabled());
    }

    @Test
    public void testMakeAnOrderInEUR(){
        homePage.setCurrencyByIndex(0);
        homePage.chooseProductByIndex(0).addToCart().makeOrder().isSuccessfullyOrdered();
    }

    @Test
    public void testMakeAnOrderInUSD(){
        homePage.setCurrencyByIndex(1);
        homePage.chooseProductByIndex(0).addToCart().makeOrder().isSuccessfullyOrdered();
    }

    @Test
    public void testMakeAnOrderInJBY(){
        homePage.setCurrencyByIndex(2);
        homePage.chooseProductByIndex(0).addToCart().makeOrder().isSuccessfullyOrdered();
    }

    @Test
    public void testMakeAnOrderInGBP(){
        homePage.setCurrencyByIndex(3);
        homePage.chooseProductByIndex(0).addToCart().makeOrder().isSuccessfullyOrdered();
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
