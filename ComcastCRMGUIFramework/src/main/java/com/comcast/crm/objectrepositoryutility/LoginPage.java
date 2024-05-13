package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;
/**
 * @author Tushar Gavare
 * 
 * Contains Login page elements & business Library like login
 * 
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDrverUtility;

public class LoginPage extends WebDrverUtility{ //Rule 1 create a separate java class

	//Rule 2 object creation 
	WebDriver driver;
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name ="user_name")
	private WebElement usernameEdt;

	@FindBy(name ="user_password")
	private WebElement passwordEdt;

	@FindBy(id="submitButton")
	private WebElement loginBtn;
 //Rule 3 Object Initialization

	

	//Rule 4 : Object Encapsulation

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}                 
  
/**
 *  login to application based username,password ,url arguments
 * @param url
 * @param username
 * @param password
 */
	public void loginToApp(String url,String username,String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
        passwordEdt.sendKeys(password);
        loginBtn.click();
}
}