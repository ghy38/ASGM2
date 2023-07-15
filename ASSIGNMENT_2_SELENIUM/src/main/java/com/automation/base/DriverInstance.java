package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverInstance {
	protected WebDriver driver;
	
	@BeforeClass
	public void initDriverInstance() {
		System.out.println("ini: Open browser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void closeDriverInstance() {
		System.out.println("FINISH TEST");
		driver.close();
	}
}
