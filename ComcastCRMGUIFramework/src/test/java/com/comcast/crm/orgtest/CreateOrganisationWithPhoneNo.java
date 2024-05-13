package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDrverUtility;

public class CreateOrganisationWithPhoneNo extends BaseClass {
	@Test

	public void  CreateOrganisationWithPhoneNo()  throws IOException, InterruptedException {
		
				
										// read testscriptdata from excel
				String orgName = eLib.getDataFromExcel("org",7,2) + jLib.getRandomNumber();
				String phoneNumber = eLib.getDataFromExcel("org",7,3);
				
		
				
				
				// step 2: navigate to the organization module
				
				driver.findElement(By.linkText("Organizations")).click();
				
				// step 3: click on "create Organizations Button"
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				// step4:enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.id("phone")).sendKeys(phoneNumber);
			
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(1000);
				
				//verify the phone number
				
				String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
				if(actPhoneNumber.equals(phoneNumber)) {
					System.out.println(phoneNumber + " Information is verified ====>PASS");
				}else {
					System.out.println(phoneNumber + " Information is not verified ====>FAIL");

				}
				

	}

}
