package edu.utvt.selenium.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.utvt.selenium.base.components.DefaultComponents;
import edu.utvt.selenium.base.components.Require;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	private static Logger logger = LoggerFactory.getLogger(Base.class);

	private static WebDriver driver;
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	static {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
	    options.setImplicitWaitTimeout(Duration.ofSeconds(1));
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement findBy(By locator) {

		WebElement element = null;

		if (locator == null)
			Require.nonNull(DefaultComponents.DOM_COMPONENT);

		logger.info("findBy: " + locator.toString());
		element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		if (element == null)
			Require.nonNull(DefaultComponents.DOM_COMPONENT, locator.toString());

		return element;
	}

	/**
	 * 
	 * @param urlPath
	 */
	public void navigateTo(String urlPath) {

		if (urlPath == null)
			Require.nonNull(DefaultComponents.URL);

		logger.info("navigateTo: " + urlPath);
		driver.get(urlPath);
	}

	/**
	 * 
	 * @param locator
	 * @param text
	 */
	public void sendKeys(By locator, String text) {

		WebElement element = null;

		element = this.findBy(locator);
		element.clear();
		element.sendKeys(text.trim());
	}

	/**
	 * 
	 * @param locator
	 */
	public void onClick(By locator) {
		this.findBy(locator).click();
	}

	/**
	 * 
	 * @param locator
	 * @param value
	 */
	public void changeSelectedOptionByValue(By locator, String value) {
		Select select = null;
		WebElement element = null;

		element = this.findBy(locator);
		select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * 
	 * @param locator
	 * @param index
	 */
	public void changeSelectedOptionByIndex(By locator, Integer index) {
		Select select = null;
		WebElement element = null;

		element = this.findBy(locator);
		select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public Integer getSelectSize(By locator) {

		Integer totalElements = 0;
		Select select = null;
		WebElement element = null;

		element = this.findBy(locator);
		select = new Select(element);
		totalElements = select.getAllSelectedOptions().size();
		logger.info("getSelectSize: " + totalElements);
		

		return totalElements;
	}
	
	/**
	 * 
	 * @param horizontally
	 * @param vertically
	 */
	public void pageScroll(Integer horizontally , Integer vertically) {
		JavascriptExecutor executor = null;
		String scroll = null;
		
		if (horizontally == null)
			Require.nonNull(DefaultComponents.DOM_COMPONENT, "horizontally");
		
		if (vertically == null)
			Require.nonNull(DefaultComponents.DOM_COMPONENT, "vertically");
		
		
		scroll = "window.scrollBy(%s, %s)";
		executor = (JavascriptExecutor) driver;
		executor.executeScript(String.format(scroll, horizontally, vertically));		
	}
	
	/**
	 * 
	 */
	public void quit() {
		logger.info("quit has been completed");
		driver.quit();
	}	

}
