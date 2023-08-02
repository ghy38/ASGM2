package com.automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.PropertiesFileUtils;

public class LoginPage {
	private WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
	}
	
	public void sendUsername(String username) throws InterruptedException {
		WebElement UsernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("UsernameInput"))));
		UsernameInput.sendKeys(username);
	}
	
	public void sendPassword(String password) throws InterruptedException {
		WebElement PasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("PasswordInput"))));
		PasswordInput.sendKeys(password);
	}
	
	public void clickLoginButton() throws InterruptedException {
		WebElement LoginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("LoginButton"))));
		LoginButton.click();
	}
	
	public void Login(String username, String password) throws InterruptedException {
		this.sendUsername(username);
		this.sendPassword(password);
		this.clickLoginButton();
	}
}
