import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class WebDynamicTest {
    WebDriver driver;

    private static final String baseURL = "https://www.selenium.dev/selenium/web/dynamic.html";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    void testAddBox() {
        driver.get(baseURL);
        WebElement addBoxButton = driver.findElement(By.id("adder"));
        assertNotNull(addBoxButton);
        for(int i = 0;i<100;i++) {
            System.out.println(i);
            addBoxButton.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            WebElement box = driver.findElement(By.id("box" + i));
            assertNotNull(box);
        }
    }




}
