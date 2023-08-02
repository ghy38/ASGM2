package com.automation.base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {
	protected ChromeDriver driver;
	
	@BeforeClass
	public void init() {
		System.out.println("Open: ini browser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void closeDriver() {
		System.out.println("FINISH TEST");
		driver.close();
	}
}
