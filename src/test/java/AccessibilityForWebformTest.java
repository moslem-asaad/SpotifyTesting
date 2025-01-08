import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.sql.Driver;
import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class AccessibilityForWebformTest {

    WebDriver driver;

    private static final String baseURL = "https://www.selenium.dev/selenium/web/web-form.html";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    void testInputTest() throws InterruptedException {
        driver.get(baseURL);
        new Actions(driver)
                .keyDown(Keys.TAB)
                .sendKeys("john")
                .perform();

        WebElement textInput = driver.findElement(By.id("my-text-id"));
        String inputValue = textInput.getDomProperty("value");
        assertEquals("john",inputValue);

        new Actions(driver)
                .keyDown(Keys.TAB)
                .sendKeys("1234")
                .perform();

        WebElement passwordField = driver.findElement(By.name("my-password"));
        assertNotNull(passwordField);
        assertEquals("1234",passwordField.getDomProperty("value"));


        new Actions(driver)
                .keyDown(Keys.TAB)
                .sendKeys("Elvis has left the building")
                .perform();

        WebElement textAreaField = driver.findElement(By.name("my-textarea"));
        assertNotNull(textAreaField);
        assertEquals("Elvis has left the building",textAreaField.getDomProperty("value"));

        new Actions(driver)
                .keyDown(Keys.TAB)
                .perform();

        new Actions(driver)
                .keyDown(Keys.TAB)
                .perform();

        new Actions(driver)
                .keyDown(Keys.TAB)
                .keyDown(Keys.SPACE)
                .keyDown(Keys.DOWN)
                .keyDown(Keys.DOWN)
                .keyDown(Keys.ENTER)
                .perform();

        WebElement dropDownField = driver.findElement(By.name("my-select"));
        Select dropdown = new Select(dropDownField);
        assertEquals("Two",dropdown.getFirstSelectedOption().getText());


        new Actions(driver)
                .keyDown(Keys.TAB)
                .keyDown(Keys.TAB)
                .keyDown(Keys.TAB)
                .keyDown(Keys.TAB)
                .keyDown(Keys.TAB)
                .keyDown(Keys.DOWN)
                .perform();



        new Actions(driver)
                .keyDown(Keys.TAB)
                .keyDown(Keys.TAB)
                .keyDown(Keys.TAB)
                .keyDown(Keys.TAB)
                .keyDown(Keys.RIGHT)
                .keyDown(Keys.RIGHT)
                .keyDown(Keys.RIGHT)
                .keyDown(Keys.RIGHT)
                .keyDown(Keys.RIGHT)
                .perform();

        Thread.sleep(3000);
    }

}
