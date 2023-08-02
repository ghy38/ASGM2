package com.automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.PropertiesFileUtils;

public class RegisterPage {
	private WebDriver driver;
	WebDriverWait wait;
	
	public RegisterPage(WebDriver driver) {
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
	
	public void sendRePassword(String repassword) throws InterruptedException {
		WebElement RePasswordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("RePasswordInput"))));
		RePasswordInput.sendKeys(repassword);
	}
	
	public void clickRegister() throws InterruptedException {
		WebElement RegisterButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("RegisterButton"))));
		RegisterButton.click();
	}
	
	public void Register(String username, String password, String repassword) throws InterruptedException {
		this.sendUsername(username);
		this.sendPassword(password);
		this.sendRePassword(repassword);
		this.clickRegister();
	}
}
