package com.example.scripts;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.example.generic.BaseClass;
import com.example.generic.Lib;
import com.example.pom.LoginPage;

public class TestInvalidLogin extends BaseClass {
	@Test
	public void testInvalidLogin() {
		LoginPage lp = new LoginPage(driver);
		for (int i = 0; i < Lib.getRowCount("Invalid"); i++) {
			lp.setUsername(Lib.getCellValue("Valid", 1, 0));
			lp.setPasswword(Lib.getCellValue("Valid", 1, 1));
			lp.login();
			
			WebDriverWait   wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.titleIs("actiTIME - Enter Time-Track"));
			
			String actHomePageTitle= driver.getTitle();
			SoftAssert sa = new SoftAssert();
			sa.assertEquals(actHomePageTitle, "actiTIME - Enter Time-Track");
			sa.assertAll();
		}
	}
}
