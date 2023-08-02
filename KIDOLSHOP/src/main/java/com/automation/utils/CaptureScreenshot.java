package com.automation.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.FileHandler;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CaptureScreenshot {
	public static String takeScreenshot(WebDriver driver, String name) {
		String filePath = "";
	    try {
	    	
	    	File theDir = new File("./Screenshots/");
	    	if(!theDir.exists()) {
	    		theDir.mkdirs();
	    	}

	        String imageName = name + ".png";
	        
	        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

	        filePath = "./Screenshots/" +imageName;
	        File desFile = new File(filePath);
	        ImageIO.write(image, "png", desFile);

	    } catch (Exception ex) {
	        System.out.println("Đã xảy ra lỗi khi chụp màn hình!");
	        ex.printStackTrace();
	    }
	    return filePath;
	}
	
	public static void attachScreenshot(String filePath) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			File file = new File(filePath);
			
			Reporter.log("<br> <a title=\"Screenshot\" href=\"" +file.getAbsolutePath()+ "\" >" +"<img alt='" +file.getName()+ "' src='"
					+file+ "' height= '243' width='418'></a><br>");
		}catch(Exception e) {
			System.out.println("Lỗi đính kèm ảnh!");
		}
	}
}
