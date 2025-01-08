import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainPageTest {
    WebDriver driver;

    private static final String baseURL = "http://localhost:8082";
    private static final String expectedPageTitle = "Spotify Clone";
    private static final String expectedPageMessage = "Oops.... Not Available";


    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void testPageTitle() {
        driver.get(baseURL);

        String pageTitle = driver.getTitle();
        assertEquals(expectedPageTitle, pageTitle);
    }

    @Test
    void testCurrentStartIsZero() {
        driver.get(baseURL);
        WebElement currStart = driver.findElement(By.cssSelector("#currentStart"));
        assertEquals(currStart.getText(), "0:00");
    }

    @Test
    void testClickProgressBar() throws InterruptedException {
        driver.get(baseURL);
        WebElement progressBar = driver.findElement(By.cssSelector("#seek"));
        Actions actions = new Actions(driver);
        actions.moveToElement(progressBar, 0, 0).click().perform();
        String valueAfterClick = progressBar.getAttribute("value");
        System.out.println("Progress value after clicking: " + valueAfterClick);
        Thread.sleep(5000);
        WebElement currStart = driver.findElement(By.cssSelector("#currentStart"));
        WebElement currEnd = driver.findElement(By.cssSelector("#currentEnd"));
        String currEndText = currEnd.getText();
        int totalSeconds = convertToSeconds(currEndText);
        int halfSeconds = totalSeconds / 2;
        String halfTime = convertToTimeFormat(halfSeconds);
        assertEquals(halfTime,currStart.getText());
    }


    @Test
    void testPlayButton() throws InterruptedException {
        driver.get(baseURL);
        WebElement playButton = driver.findElement(By.cssSelector(".icon #masterPlay"));
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
        Assertions.assertTrue(isPause, "Button should change to pause state after clicking.");
    }

    @Test
    void testPlay5seconds() throws InterruptedException {
        driver.get(baseURL);
        WebElement playButton = driver.findElement(By.cssSelector(".icon #masterPlay"));
        String initialClass = playButton.getAttribute("class");
        System.out.println(initialClass);
        Assertions.assertTrue(initialClass.contains("bi-play-fill"), "Button should initially be in the play state.");
        playButton.click();
        Thread.sleep(5000);
        WebElement currStart = driver.findElement(By.cssSelector("#currentStart"));
        assertEquals(currStart.getText(), "0:05");
    }

    @Test
    void testPlayPauseButton() throws InterruptedException {
        driver.get(baseURL);
        WebElement playButton = driver.findElement(By.cssSelector(".icon #masterPlay"));
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
        Assertions.assertTrue(isPause, "Button should change to pause state after clicking.");
        playButton.click();
        boolean isPlay = wait.until(driver -> {
            String newClass = playButton.getAttribute("class");
            return newClass.contains("bi-play-fill");
        });
        Assertions.assertTrue(isPlay, "Button should change to play state after clicking.");
    }

    @Test
    void testPageMessage() {
        driver.get(baseURL);

        WebElement libraryLink = driver.findElement(By.cssSelector("a[href='/library']"));
        libraryLink.click();

        WebElement pageMessage = driver.findElement(By.cssSelector("div.content h1"));
        String pageMessageText = pageMessage.getText();

        assertEquals(expectedPageMessage, pageMessageText);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    private int convertToSeconds(String timeString) {
        String[] parts = timeString.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return (minutes * 60) + seconds;
    }

    private String convertToTimeFormat(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
