package com.automation.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFileUtils {
	//Đường dẫn đến properties files trong folder configuration
	private static String CONFIG_PATH = "./configuration/configs.properties";
	private static String TOKEN_PATH = "./configuration/token.properties";
	
	public static String getProperty(String key) {
		Properties properties = new Properties();
        InputStream inputStream = null;
        try{
        	inputStream = new FileInputStream(CONFIG_PATH);
        	
            properties.load(inputStream);
            
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
	
	public static void setProperty(String key, String value) {
		Properties properties = new Properties();
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream("./configuration/configs.properties");
            properties.load(inputStream);

            properties.setProperty(key, value);

            outputStream = new FileOutputStream("./configuration/configs.properties");
            properties.store(outputStream, null);

        } catch (IOException e) {
            System.err.println("Xảy ra lỗi khi ghi tập tin config.properties: " + e.getMessage());
        } finally {
        	// Đảm bảo đóng luồng đúng cách
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static String getToken() {
        try {
        	BufferedReader br = new BufferedReader(new FileReader(TOKEN_PATH));
            return br.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static void setToken(String token) {
        try {
            FileWriter fileWriter = new FileWriter(TOKEN_PATH);
            fileWriter.write(token);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}