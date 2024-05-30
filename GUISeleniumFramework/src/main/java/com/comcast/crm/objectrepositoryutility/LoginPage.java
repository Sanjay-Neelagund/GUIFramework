package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility{
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "user_name")
	private WebElement usernameEdt;

	@FindBy(name = "user_password")
	private WebElement passwordEdt;

	@FindBy(id = "submitButton")
	private WebElement Loginbtn;

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginbtn() {
		return Loginbtn;
	}
/**
 * Login to App based on arguments like url, username, and password provided
 * @param url
 * @param username
 * @param password
 * 
 */
	public void loginToApp(String url, String username, String password) {
		waitForPageToLoad(driver);
		maximizeWindow(driver);
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		Loginbtn.click();
	}
}
