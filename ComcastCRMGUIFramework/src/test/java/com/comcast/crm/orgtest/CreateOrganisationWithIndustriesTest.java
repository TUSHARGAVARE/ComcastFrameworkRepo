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
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateOrganisationWithIndustriesTest extends BaseClass{

	@Test
	public void CreateOrganisationWithIndustriesTest() throws IOException, InterruptedException {
		
				

						// read testscriptdata from excel
				String orgName = eLib.getDataFromExcel("org",4,2) + jLib.getRandomNumber();
				String industry = eLib.getDataFromExcel("org",4,3);
				String type = eLib.getDataFromExcel("org",4,4);
		
				
				// step 2: navigate to the organization module
				
				HomePage hm = new HomePage(driver);
				hm.getOrgLink().click();
				
				// step 3: click on "create Organizations Button"
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				// step4:enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				WebElement wbsele1 = driver.findElement(By.name("industry"));
		        wLib.select(wbsele1,industry);
				
				WebElement wbsele2 = driver.findElement(By.name("accounttype"));
                wLib.select(wbsele2, type);		
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				Thread.sleep(1000);
				
				//verify the industries and type info
				
				String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
				
				if(actIndustries.equals(industry)) {
					System.out.println(industry + " Information is verified ====>PASS");
				}else {
					System.out.println(industry + " Information is not verified ====>FAIL");

				}
				
				String actType = driver.findElement(By.id("dtlview_Type")).getText();
				if(actType.equals(type)) {
					System.out.println(type + " type is verified ====>PASS");
				}else {
					System.out.println(type + " type is not verified ====>FAIL");

				}
				
	}

}
