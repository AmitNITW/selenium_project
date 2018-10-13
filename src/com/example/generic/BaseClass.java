package com.example.generic;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass implements IAutoConstant {
	public WebDriver driver = null;
	static {
		System.setProperty(CHROME_DRIVER, CHROME_DRIVER_PATH);
		System.setProperty(FIREFOX_DRIVER, FIREFOX_DRIVER_PATH);
	}

	@BeforeMethod
	public void launchBrowser(String browser) {
		String url = Lib.getProperty("URL");
		String timeoutPeriod = Lib.getProperty("ITO");
		if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		} else {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			driver = new FirefoxDriver(options);
		}
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(timeoutPeriod), TimeUnit.SECONDS);
	}

	@AfterMethod
	public void closeBroswer(ITestResult res) {
		if (ITestResult.FAILURE == res.getStatus()) {
			Date date = new Date();
			String image = res.getName();
		}
		driver.close();
	}
}
