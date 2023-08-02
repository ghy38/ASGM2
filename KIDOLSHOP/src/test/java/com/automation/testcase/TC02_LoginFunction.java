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
import com.automation.pom.LoginPage;
import com.automation.pom.RegisterPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC02_LoginFunction extends DriverInstance {
	private String username = PropertiesFileUtils.getProperty("username");
	private String password = PropertiesFileUtils.getProperty("password");
	private String url = PropertiesFileUtils.getProperty("baseUrl"); 
	private String registerPath = PropertiesFileUtils.getProperty("loginPath");
	
	@Test
	public void TC01_LoginWithValidAccount() throws InterruptedException {
		driver.get(url+registerPath);
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.Login(username, password);
		
		WebElement avatarUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='http://kidolshop.space/logout']/../../..")));
		avatarUser.click();
		
		WebElement btnLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='http://kidolshop.space/logout']")));
		assertTrue(btnLogout.getText().contains("Đăng Xuất"), "Login Failed!");
		
		btnLogout.click();
	}

	@Test(dataProvider = "InvalidAccount")
	public void TC02_LoginWithInvalidAccount(String username_data, String password_data) throws InterruptedException {
		driver.get(url+registerPath);
		
		username = username_data;
		password = password_data;
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.Login(username, password);
		
		WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='text-danger']")));
		assertTrue(message.getText().contains("Mật khẩu hoặc tài khoản không đúng"), "Login Failed!");
	}
	
	@DataProvider(name = "InvalidAccount")
	public Object[][] InvalidUsernameData(){
		
		return new Object[][] {
			{username,"mdsm"},
			{"sd",password},
			{"mfk","123"}
		};
	}
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		if(ITestResult.FAILURE == result.getStatus()) {
			try {
				String fileName = result.getName() + "_" + username + "_" + password;
				
				String filepath = CaptureScreenshot.takeScreenshot(driver, fileName);
				
				CaptureScreenshot.attachScreenshot(filepath);
			}catch(Exception e) {
				System.out.println("Lỗi xảy ra screenshot" +e.getMessage());
			}
		}
	}
}
