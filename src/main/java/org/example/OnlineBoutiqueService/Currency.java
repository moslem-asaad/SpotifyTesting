package org.example.OnlineBoutiqueService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Currency {

    private static Currency instance;
    private WebDriver driver;

    private By currencyFormSelector = By.name("currency_code");

    private Currency(WebDriver driver) {
        this.driver = driver;
    }

    public static Currency getInstance(WebDriver driver) {
        if (instance == null) {
            synchronized (Currency.class) {
                if (instance == null) {
                    instance = new Currency(driver);
                }
            }
        }
        return instance;
    }

    public void changeCurrencyByIndex(int index) {
        WebElement currencyForm = driver.findElement(currencyFormSelector);
        Select currencies = new Select(currencyForm);
        currencyForm.click();
        List<WebElement> options = currencies.getOptions();
        options.get(index).click();
    }
}
