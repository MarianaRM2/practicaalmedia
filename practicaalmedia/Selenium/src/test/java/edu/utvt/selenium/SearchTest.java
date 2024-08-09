package edu.utvt.selenium;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SearchTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() throws InterruptedException {
        // Variables
        String url = "https://demoqa.com/";
        String elementXPath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[1]";
        String elementOptionId = "item-0";
        String firstNameCssSelector = "#userName";
        String firstNameTest = "Pedro Picapiedra";

        String emailId = "userEmail";
        String emailText = "user@email.com";

        String currentAddressId = "currentAddress";
        String currentAddressText = "Atarasquillo, Lerma";

        String btnXpath = "//button[@id='submit']";

        driver.get(url);

        this.onClick(By.xpath(elementXPath));
        this.onClick(By.id(elementOptionId));
        this.sendKeys(By.cssSelector(firstNameCssSelector), firstNameTest);
        this.sendKeys(By.id(emailId), emailText);
        this.sendKeys(By.id(currentAddressId), currentAddressText);
        scrollDown();

        this.onClick(By.xpath(btnXpath));

        Thread.sleep(5000);
    }

    private void scrollDown() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0, 300)");
    }

    private WebElement findBy(By locator) {
        WebElement element = null;

        if (locator != null)
            element = driver.findElement(locator);

        return element;
    }

    private void sendKeys(By locator, String text) {
        WebElement element = null;

        element = this.findBy(locator);
        element.clear();
        element.sendKeys(text);
    }

    private void onClick(By locator) {
        this.findBy(locator).click();
    }
}
