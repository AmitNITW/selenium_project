package com.example.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Lib implements IAutoConstant {
	public static String getCellValue(String sheetName, int rowNo, int cellNo) {
		String cellValue = "";
		try {
			WorkbookFactory.create(new FileInputStream(EXCEL_PATH)).getSheet(sheetName).getRow(rowNo).getCell(cellNo).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}
	
	public static String getProperty(String key) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(CONFIG_PATH));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}
	
	public static int getRowCount(String sheetName) {
		int rowCount = 0;
		try {
			WorkbookFactory.create(new FileInputStream(EXCEL_PATH)).getSheet(sheetName).getLastRowNum();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	public static void captureScreenshot(WebDriver driver, String methodName) {
		String currentDate = new Date().toString().replaceAll(":", "_");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(SCREENSHOT_PATH + methodName + "__" + currentDate + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
