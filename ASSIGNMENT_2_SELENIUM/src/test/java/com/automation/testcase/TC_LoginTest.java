package com.automation.testcase;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {
	
	@Test(dataProvider = "Excel")
	public void TC01_LoginFirstAccount(String email, String password) throws InterruptedException{
		String URL = PropertiesFileUtils.getProperty("base_url");
		
		driver.get(URL);
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		WebElement iconSignIn = driver.findElement(By.xpath("//a[@href='/login']"));
		iconSignIn.click();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();
		
		WebElement iconSignOut = driver.findElement(By.xpath("//a[@href='/logout']"));
		Assert.assertEquals(true, iconSignOut.getText().contains("Logout"), "Đăng nhập thất bại!");
		iconSignOut.click();
		
		Thread.sleep(2000);
	}
	
	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {
		FileInputStream file = new FileInputStream("./data/assignment2_data_test.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet loginSheet = workbook.getSheet("Login");
		
		int numberOfRowData = loginSheet.getPhysicalNumberOfRows();
		
		Object[][] data = new Object[numberOfRowData][2];
		
		for(int i = 0; i < numberOfRowData; i++) {
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			data[i][0] = username.getStringCellValue();
			data[i][1] = password.getStringCellValue();
		}
		
		return data;
	}
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		if(ITestResult.FAILURE == result.getStatus()) {
			try {
				String email = (String)result.getParameters()[0];
				String password = (String)result.getParameters()[1];
				
				String accountName = email.substring(0, email.indexOf("@"));
				
				String filepath = CaptureScreenshot.takeScreenshot(driver, accountName);
				
				Thread.sleep(2000);

				CaptureScreenshot.attachScreenshot(filepath);
			}catch(Exception e) {
				System.out.println("Lỗi xảy ra screenshot" +e.getMessage());
			}
		}
	}
}
