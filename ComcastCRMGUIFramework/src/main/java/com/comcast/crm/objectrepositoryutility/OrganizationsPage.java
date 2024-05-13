package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDrverUtility;

public class OrganizationsPage extends WebDrverUtility {

	   WebDriver driver;
			public OrganizationsPage(WebDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver,this);
			}
			
			
	    @FindBy(name="search_text")
	    private WebElement searchEdt;
	    
		
	    @FindBy(name="search_field")
	    private WebElement searchDD;
	    
	    @FindBy(name="submit")
	    private WebElement searchBtn;
			
		@FindBy(xpath = "//img[@alt='Create Organization...']")
		private WebElement createNewOrgBtn;

		public WebElement getCreateNewOrgBtn() {
			return createNewOrgBtn;
			
		}
		

		public WebElement getSearchBtn() {
			return searchBtn;
		}


		public void setSearchBtn(WebElement searchBtn) {
			this.searchBtn = searchBtn;
		}


		public WebElement getSearchEdt() {
			return searchEdt;
		}

		public WebElement getSearchDD() {
			return searchDD;
		}
		
		
		
		
}
