package com.example.scripts;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.example.generic.BaseClass;
import com.example.generic.Lib;
import com.example.pom.LoginPage;

public class TestInvalidLogin extends BaseClass {
	@Test
	public void testInvalidLogin() {
		LoginPage lp = new LoginPage(driver);
		int rows = Lib.getRowCount("Invalid");
		for (int i = 1; i <= rows; i++) {
			lp.setUsername(Lib.getCellValue("Invalid", i, 0));
			lp.setPasswword(Lib.getCellValue("Invalid", i, 1));
			lp.login();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SoftAssert sa = new SoftAssert();
			sa.assertEquals("Username or Password is invalid. Please try again.", lp.getErrorMsg());
			sa.assertEquals("rgba(0, 0, 0, 1)", lp.getErrorColor());
			sa.assertAll();
		}
		Reporter.log("Hi Amit !!!");
	}
}
