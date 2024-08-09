package edu.utvt.selenium;

import edu.utvt.selenium.base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElementsTest extends BaseTest {

    @Test
    void test() throws InterruptedException {

        //Variables
        String url = "https://demoqa.com/";
        String elementXPath = "//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[1]";
        String elementOptionId = "item-0";
        String firstNameCssSelector = "#userName";
        String firstNameTest = "Pedro Picapiedra";

        String emailId = "userEmail";
        String emailText = "pedro.picapiedra@email.com";

        String currentAddressId = "currentAddress";
        String currentAddressText = "Atarasquillo, Lerma";

        String btnXpath = "//button[@id='submit']";

        navigateTo(url);
        onClick(By.xpath(elementXPath));
        onClick(By.id(elementOptionId));
        sendKeys(By.cssSelector(firstNameCssSelector), firstNameTest);

        sendKeys(By.id(emailId), emailText);
        sendKeys(By.id(currentAddressId), currentAddressText);

        pageScroll(0, 300);
        onClick(By.xpath(btnXpath));

        Thread.sleep(3000);
    }
}
