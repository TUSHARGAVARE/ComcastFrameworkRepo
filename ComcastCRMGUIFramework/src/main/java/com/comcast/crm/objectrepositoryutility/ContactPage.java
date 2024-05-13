package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDrverUtility;

public class ContactPage extends WebDrverUtility {

		WebDriver driver;
		@FindBy(xpath="//img[@title='Create Contact...']")
		private WebElement createContactbutton;
		
		@FindBy(name="lastname")
		private WebElement lastnameEdit;
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement savebutton;
		
		
		public ContactPage(WebDriver driver) {
			this.driver=driver;
	    	 PageFactory.initElements(driver,this);
	     }	
		@FindBy(className="dvHeaderText")
		private WebElement headerMsg;

		public WebElement getHeaderMsg() {
			return headerMsg;
		}

		public void setHeaderMsg(WebElement headerMsg) {
			this.headerMsg = headerMsg;
		}

		public WebElement getCreateContactbutton() {
			return createContactbutton;
		}

		public WebElement getLastnameEdit() {
			return lastnameEdit;
		}

		public WebElement getSavebutton() {
			return savebutton;
		}
	 
			
	}

