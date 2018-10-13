package com.example.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	WebElement user;
	
	@FindBy(name="pwd")
	WebElement pass;
	
	@FindBy(xpath="//div[text() = 'Login ']")
	WebElement login;
	
	public void setUsername(String username) {
		user.sendKeys(username);
	}
	
	public void setPasswword(String password) {
		pass.sendKeys(password);
	}
	
	public void login() {
		login.click();
	}
}
