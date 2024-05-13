package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class ProductsPage {
	@FindBy(xpath = "//input[@alt='Create Product...']")
	private WebElement createProductImgBtn; 
	

}
