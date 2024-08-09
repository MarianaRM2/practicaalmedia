package edu.utvt.selenium.base;

import org.junit.jupiter.api.AfterEach;

public class BaseTest extends Base {

	@AfterEach
	void close() {
		this.quit();
	}
	
	
}
