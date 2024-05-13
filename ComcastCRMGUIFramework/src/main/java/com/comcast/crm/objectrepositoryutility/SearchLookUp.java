package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDrverUtility;

public class SearchLookUp extends WebDrverUtility {
	
		@FindBy(id="search_txt")
		private WebElement searchEdit;

		@FindBy(name="search")
		private WebElement searchbutton;


		public SearchLookUp(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}


		public WebElement getSearchEdit() {
			return searchEdit;
		}


		public WebElement getSearchbutton() {
			return searchbutton;
		}

	}
