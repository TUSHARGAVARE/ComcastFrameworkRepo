package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDrverUtility;

public class OrganizationInfoPage extends WebDrverUtility {
	 
	   WebDriver driver;
				public OrganizationInfoPage(WebDriver driver) {
					this.driver = driver;
					PageFactory.initElements(driver,this);
				}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;

	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}


   
}
