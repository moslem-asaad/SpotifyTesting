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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


public class WebFormsTest {
    WebDriver driver;

    private static final String baseURL = "https://www.selenium.dev/selenium/web/web-form.html";
    private static final String input1 = "Hello world!";
    private static final String password = "myPassword";
    private static final String textArea = "Hi, I am a Software Engineer!";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }
    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    void testPageTitle() {
        driver.get(baseURL);

        String pageTitle = driver.getTitle();
        assertEquals("Web form", pageTitle);
    }

    @Test
    void testValidateFields() throws InterruptedException {
        driver.get(baseURL);
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> textInput.isDisplayed());
        assertNotNull(textInput);
        textInput.clear();
        textInput.sendKeys(input1);
        String inputValue = textInput.getDomProperty("value");
        assertEquals(input1,inputValue);

        WebElement passwordField = driver.findElement(By.name("my-password"));
        assertNotNull(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        assertEquals(password,passwordField.getDomProperty("value"));


        WebElement textAreaField = driver.findElement(By.name("my-textarea"));
        assertNotNull(textAreaField);
        textAreaField.clear();
        textAreaField.sendKeys(textArea);
        assertEquals(textArea,textAreaField.getDomProperty("value"));



        Thread.sleep(5000);

    }

    @Test
    void testDisableField() throws InterruptedException {
        driver.get(baseURL);
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> textInput.isDisplayed());

        WebElement disabledInputField = driver.findElement(By.name("my-disabled"));
        assertNotNull(disabledInputField);
        assertFalse(disabledInputField.isEnabled());
    }

    @Test
    void testReadOnlyField() throws InterruptedException {
        driver.get(baseURL);
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> textInput.isDisplayed());

        WebElement readOnlyInputField = driver.findElement(By.name("my-readonly"));
        assertNotNull(readOnlyInputField);
        assertEquals("Readonly input",readOnlyInputField.getDomProperty("value"));
        assertThrows(Exception.class,readOnlyInputField::clear); // insure the field can't be modified

    }

    @Test
    void testDropdownSelectField() throws InterruptedException {
        driver.get(baseURL);
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> textInput.isDisplayed());

        WebElement dropDownField = driver.findElement(By.name("my-select"));
        assertNotNull(dropDownField);
        Select dropdown = new Select(dropDownField);
        String defaultOption = dropdown.getFirstSelectedOption().getText();
        assertEquals("Open this select menu",defaultOption);

        for(WebElement option: dropdown.getOptions()){
            dropDownField.click();
            //Thread.sleep(2000);
            option.click();
            String optionText = option.getText();
            if (optionText.equals(defaultOption)) {
                continue;
            }
            //Thread.sleep(2000);
            //dropdown.selectByVisibleText(optionText);
            String selectedOption = dropdown.getFirstSelectedOption().getText();
            assertEquals(optionText,selectedOption);
            assertEquals("true",option.getDomProperty("selected"));
        }
    }

    @Test
    void testDropdownDataListField() {
        driver.get(baseURL);
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> textInput.isDisplayed());

        WebElement dropDownDataListField = driver.findElement(By.name("my-datalist"));
        assertNotNull(dropDownDataListField);
        assertEquals("Type to search...",dropDownDataListField.getDomProperty("placeholder"));
        String[] options = {"San Francisco", "New York", "Seattle", "Los Angeles", "Chicago"};
        for(String option : options){
            dropDownDataListField.clear();
            dropDownDataListField.click();
            dropDownDataListField.sendKeys(option);
            dropDownDataListField.click();
            assertEquals(option,dropDownDataListField.getDomProperty("value"));
        }
        dropDownDataListField.click();
    }

    @Test
    void testCheckedBoxField() {
        driver.get(baseURL);
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> textInput.isDisplayed());

        WebElement checkedBox1 = driver.findElement(By.id("my-check-1"));
        assertNotNull(checkedBox1);
        assertEquals("true",checkedBox1.getDomProperty("checked"));

        WebElement checkedBox2 = driver.findElement(By.id("my-check-2"));
        assertNotNull(checkedBox2);
        assertEquals("false",checkedBox2.getDomProperty("checked"));

        checkedBox1.click();
        assertEquals("false",checkedBox1.getDomProperty("checked"));

        checkedBox2.click();
        assertEquals("true",checkedBox2.getDomProperty("checked"));

        checkedBox1.click();
        assertEquals("true",checkedBox1.getDomProperty("checked"));
    }

    @Test
    void testCheckedRadioField() {
        driver.get(baseURL);
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(500));
        wait.until(d -> textInput.isDisplayed());

        WebElement radioBox1 = driver.findElement(By.id("my-radio-1"));
        assertNotNull(radioBox1);
        assertEquals("true",radioBox1.getDomProperty("checked"));

        WebElement radioBox2 = driver.findElement(By.id("my-radio-2"));
        assertNotNull(radioBox2);
        assertEquals("false",radioBox2.getDomProperty("checked"));

        radioBox1.click();
        assertEquals("true",radioBox1.getDomProperty("checked"));

        radioBox2.click();
        assertEquals("false",radioBox1.getDomProperty("checked"));
        assertEquals("true",radioBox2.getDomProperty("checked"));

        radioBox1.click();
        assertEquals("true",radioBox1.getDomProperty("checked"));
        assertEquals("false",radioBox2.getDomProperty("checked"));
    }

    @Test
    void testSubmit(){
        driver.get(baseURL);
        WebElement button = driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/button"));
        button.click();
        WebElement body = driver.findElement(By.xpath("/html/body/main/div/div[1]/div/h1"));
        assertEquals(body.getText(),"Form submitted");
    }


}
