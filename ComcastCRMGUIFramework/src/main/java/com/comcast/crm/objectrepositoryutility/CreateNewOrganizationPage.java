package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDrverUtility;

public class CreateNewOrganizationPage extends WebDrverUtility {
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	

	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement industryDB;
	
	@FindBy(name = "accounttype")
	private WebElement typeEdt;
	
	

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
		public void createOrg(String orgName,String industry,String type) {
			
			orgNameEdt.sendKeys(orgName);
			Select sel = new Select(industryDB);
			sel.selectByVisibleText(industry);
			Select sel1 = new Select(typeEdt);
			sel1.selectByVisibleText(type);
			saveBtn.click();	
		
		
}
}