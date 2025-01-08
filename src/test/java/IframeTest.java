import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
public class IframeTest {
    WebDriver driver;

    //private static final String baseURL = "C:\\dev\\BeyondDev\\BeyonDev4\\selenium_exercises\\pagination\\pagination1.html";
    String pagePath = Paths.get("C:\\dev\\BeyondDev\\BeyonDev4\\selenium_exercises\\iframe.html").toUri().toString();

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    void testPlayButton() throws InterruptedException {
        driver.get(pagePath);

        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> iframe.isDisplayed());


        WebElement playButton = iframe.findElement(By.cssSelector(".playButton.medium"));
        assertNotNull(playButton);

        /* WebElement  = driver.findElement(By.cssSelector(".icon #masterPlay"));
        String initialClass = playButton.getAttribute("class");
        System.out.println(initialClass);
        Assertions.assertTrue(initialClass.contains("bi-play-fill"), "Button should initially be in the play state.");
        playButton.click();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isPause = wait.until(driver -> {
            String newClass = playButton.getAttribute("class");
            return newClass.contains("bi-pause-fill");
        });
        Assertions.assertTrue(isPause, "Button should change to pause state after clicking.");*/
    }


}
