package com.automation.pom;

import java.util.Properties;

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
	
	public void enterEmail(String email) throws InterruptedException{
		WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("login_email"))));
		
		inputEmail.sendKeys(email);
		//Thread.sleep(2000);
	}
	
	public void enterPassword(String password) throws InterruptedException{
		WebElement inputPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("login_password"))));
		
		inputPassword.sendKeys(password);
		
		//Thread.sleep(2000);
	}
	
	public void clickSignIn() throws InterruptedException{
		WebElement btnSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertiesFileUtils.getProperty("login_signin"))));
		
		btnSignIn.click();
		
		//Thread.sleep(2000);
	}
	
}
