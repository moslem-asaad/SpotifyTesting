import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.sql.Driver;
import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class WebdriverInteractionsTest {

    WebDriver driver;

    //private static final String baseURL = "C:\\dev\\BeyondDev\\BeyonDev4\\selenium_exercises\\pagination\\pagination1.html";
    String pagePath = Paths.get("C:\\dev\\BeyondDev\\BeyonDev4\\selenium_exercises\\pagination\\pagination1.html").toUri().toString();

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    void testPage1Title() throws InterruptedException {
        driver.get(pagePath);

        String pageTitle = driver.getTitle();
        assertEquals("Paginated Content 1", pageTitle);

    }

    @Test
    void testPage1Content() {
        driver.get(pagePath);
        WebElement webElement = driver.findElement(By.id("content"));
        assertEquals(webElement.getText(),"Welcome to Page 1! This is the content of the first page.");
    }

    @Test
    void testPage1() {
        driver.get(pagePath);
        WebElement prevButton = driver.findElement(By.id("prev"));
        assertTrue(prevButton.isDisplayed());

        WebElement page1Button  = driver.findElement(By.id("page-number1"));
        assertTrue(page1Button.isDisplayed());

        WebElement page2Button  = driver.findElement(By.id("page-number2"));
        assertTrue(page2Button.isEnabled());

        WebElement page3Button  = driver.findElement(By.id("page-number3"));
        assertTrue(page3Button.isEnabled());

        WebElement nextButton  = driver.findElement(By.id("next"));
        assertTrue(nextButton.isEnabled());

        WebElement jumpToPageButton  = driver.findElement(By.id("jump-to-page"));
        assertTrue(jumpToPageButton.isEnabled());

    }

    @Test
    void testPage2() throws InterruptedException {
        driver.get(pagePath);

        WebElement prevButton = driver.findElement(By.id("prev"));
        assertTrue(prevButton.isDisplayed());

        WebElement page1Button = driver.findElement(By.id("page-number1"));
        assertTrue(page1Button.isDisplayed());

        WebElement page2Button = driver.findElement(By.id("page-number2"));
        assertTrue(page2Button.isEnabled());

        driver.findElement(By.linkText("Page 2")).click();

        Set<String> windowHandlesSet = driver.getWindowHandles();
        assertEquals(1, windowHandlesSet.size(), "Expected only one tab/window to be open");

        String title = driver.getTitle();
        assertEquals("Paginated Content 2", title);

        WebElement page3Button  = driver.findElement(By.id("page-number3"));
        assertTrue(page3Button.isEnabled());

        WebElement nextButton  = driver.findElement(By.id("next"));
        assertTrue(nextButton.isEnabled());

        WebElement jumpToPageButton  = driver.findElement(By.id("jump-to-page"));
        assertTrue(jumpToPageButton.isEnabled());

    }

    @Test
    void testPage3() throws InterruptedException {
        driver.get(pagePath);

        WebElement prevButton = driver.findElement(By.id("prev"));
        assertTrue(prevButton.isDisplayed());

        WebElement page1Button = driver.findElement(By.id("page-number1"));
        assertTrue(page1Button.isDisplayed());

        WebElement page2Button = driver.findElement(By.id("page-number2"));
        assertTrue(page2Button.isEnabled());

        WebElement page3Button  = driver.findElement(By.id("page-number3"));
        assertTrue(page3Button.isEnabled());

        driver.findElement(By.linkText("Page 3")).click();

        Set<String> windowHandlesSet = driver.getWindowHandles();
        assertEquals(1, windowHandlesSet.size(), "Expected only one tab/window to be open");

        String title = driver.getTitle();
        assertEquals("Paginated Content 3", title);


        WebElement nextButton  = driver.findElement(By.id("next"));
        assertTrue(nextButton.isDisplayed());

        WebElement jumpToPageButton  = driver.findElement(By.id("jump-to-page"));
        assertTrue(jumpToPageButton.isEnabled());
    }

    @Test
    void testPage1To2To3AndBackTo2To1() throws InterruptedException {
        driver.get(pagePath);

        WebElement prevButton = driver.findElement(By.id("prev"));
        assertTrue(prevButton.isDisplayed());

        WebElement page1Button = driver.findElement(By.id("page-number1"));
        assertTrue(page1Button.isDisplayed());

        WebElement page2Button = driver.findElement(By.id("page-number2"));
        assertTrue(page2Button.isEnabled());

        WebElement page3Button  = driver.findElement(By.id("page-number3"));
        assertTrue(page3Button.isEnabled());

        driver.findElement(By.linkText("Page 2")).click();
        assertEquals("Paginated Content 2", driver.getTitle());

        driver.findElement(By.linkText("Next")).click();

        String title = driver.getTitle();
        assertEquals("Paginated Content 3", title);

        driver.findElement(By.linkText("Previous")).click();

        title = driver.getTitle();
        assertEquals("Paginated Content 2", title);

        driver.findElement(By.linkText("Previous")).click();

        title = driver.getTitle();
        assertEquals("Paginated Content 1", title);
    }

    @Test
    void testOpenPage2InNewTapAndMoveBetweenTaps() throws InterruptedException {
        driver.get(pagePath);

        WebElement prevButton = driver.findElement(By.id("prev"));
        assertTrue(prevButton.isDisplayed());

        WebElement page1Button = driver.findElement(By.id("page-number1"));
        assertTrue(page1Button.isDisplayed());

        WebElement page2Button = driver.findElement(By.id("page-number2"));
        assertTrue(page2Button.isEnabled());

        driver.findElement(By.linkText("Page 2")).click();

        Set<String> windowHandlesSet = driver.getWindowHandles();
        assertEquals(1, windowHandlesSet.size(), "Expected only one tab/window to be open");

        String title = driver.getTitle();
        assertEquals("Paginated Content 2", title);

        WebElement page3Button  = driver.findElement(By.id("page-number3"));
        assertTrue(page3Button.isEnabled());

        WebElement nextButton  = driver.findElement(By.id("next"));
        assertTrue(nextButton.isEnabled());

        WebElement jumpToPageButton  = driver.findElement(By.id("jump-to-page"));
        assertTrue(jumpToPageButton.isEnabled());

    }






}
