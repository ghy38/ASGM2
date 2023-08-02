package com.automation.testcase;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.pom.Customer;
import com.automation.pom.RegisterPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC01_RegisterFunction extends DriverInstance {
	
	private String username, password, repassword;
	private String url = PropertiesFileUtils.getProperty("baseUrl"); 
	private String registerPath = PropertiesFileUtils.getProperty("registerPath");
	
	@Test
	public void TC01_RegisterWithValidAccount() throws InterruptedException {
		driver.get(url+registerPath);
		
		username = Customer.GenerateValidUsername();
		password = Customer.GenerateValidPassword();
		repassword = password;
	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.Register(username, password, repassword);
		
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='text-success']")));
		assertTrue(message.getText().contains("Đăng ký tài khoản thành công"), "Register Failed!");
		
		PropertiesFileUtils.setProperty("username", username);
		PropertiesFileUtils.setProperty("password", password);
	}
	
	@Test
	public void TC02_RegisterWithExistUsername() throws InterruptedException {
		driver.get(url+registerPath);
		
		username = PropertiesFileUtils.getProperty("username");
		password = PropertiesFileUtils.getProperty("password");
		repassword = password;
	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.Register(username, password, repassword);
		
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='text-danger']")));
		assertTrue(message.getText().contains("Tài khoản này đã tồn tại"), "Register Failed!");
	}
	
	@Test(dataProvider = "InvalidUsername")
	public void TC03_RegisterWithInvalidUsername(String InvalidUsername) throws InterruptedException {
		driver.get(url+registerPath);
		
		username = InvalidUsername;
		password = Customer.GenerateValidPassword();
		repassword = password;
	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.Register(username, password, repassword);
		
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form-register']/div[1]/span")));
		assertTrue(message.getText().contains("Tên tài khoản bắt buộc từ 5-10 ký tự, phải chứa chữ thường và chữ số"), "Register Failed!");
	}
	
	@Test(dataProvider = "InvalidPassword")
	public void TC04_RegisterWithInvalidPassword(String InvalidPassword) throws InterruptedException {
		driver.get(url+registerPath);
		
		username = Customer.GenerateValidUsername();
		password = InvalidPassword;
		repassword = password;
	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.Register(username, password, repassword);
		
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form-register']/div[2]/span")));
		assertTrue(message.getText().contains("Mật khẩu phải từ 8-16 ký tự"), "Register Failed!");
	}
	
	@Test
	public void TC05_RegisterWithInvalidRePassword() throws InterruptedException {
		driver.get(url+registerPath);
		
		username = Customer.GenerateValidUsername();
		password = Customer.GenerateValidPassword();
		repassword = "as";
	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		RegisterPage registerPage = new RegisterPage(driver);
		registerPage.Register(username, password, repassword);
		
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='form-register']/div[3]/span")));
		assertTrue(message.getText().contains("Xác nhận mật khẩu không trùng khớp"), "Register Failed!");
	}
	
	@DataProvider(name = "InvalidUsername")
	public Object[][] InvalidUsernameData(){
		return new Object[][] {
			{"aa12"},
			{"aaaaabbbbbcc12"},
			{"123456"},
			{"abcdef"}
		};
	}
	
	@DataProvider(name = "InvalidPassword")
	public Object[][] InvalidPasswordData(){
		return new Object[][] {
			{"123456"},
			{"12345678fnkelfnel12k3klnk"}
		};
	}
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		if(ITestResult.FAILURE == result.getStatus()) {
			try {
				String fileName = result.getName() + "_" + username + "_" + password + "_" + repassword;
				
				String filepath = CaptureScreenshot.takeScreenshot(driver, fileName);
				
				CaptureScreenshot.attachScreenshot(filepath);
			}catch(Exception e) {
				System.out.println("Lỗi xảy ra screenshot" +e.getMessage());
			}
		}
	}
}
