package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDrverUtility;
import com.comcast.crm.listenerutiility.ListenerImpClass;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

@Listeners(com.comcast.crm.listenerutiility.ListenerImpClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups="smokeTest")
	public void CreateOrgTest() throws IOException, InterruptedException {
	
				UtilityClassObject.getTest().log(Status.INFO, "read data from Excel"); //parallel execution
						// read testscriptdata from excel
				String orgName = eLib.getDataFromExcel("org",4,2) +jLib.getRandomNumber();
				String industry = eLib.getDataFromExcel("org",4,3);
				String type = eLib.getDataFromExcel("org",4,4);
				
				
				//lp.getUsernameEdt().sendKeys("admin");
				//lp.getPasswordEdt().sendKeys("admin");
				//lp.getLoginBtn().click();
				
				// step 2: navigate to the organization module
				UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org page ");
				HomePage hm = new HomePage(driver);
				hm.getOrgLink().click();
				
				
				// step 3: click on "create Organizations Button"
				UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Org page ");

				OrganizationsPage oip = new OrganizationsPage(driver);
				oip.getCreateNewOrgBtn().click();
				
				// step4:enter all the details and create new organization
				UtilityClassObject.getTest().log(Status.INFO, " Create a new Org ");

				CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
				cnop.createOrg(orgName);
				UtilityClassObject.getTest().log(Status.INFO, orgName +"===>Create a new Org");

				
				
				//verify header message Expected Result
		      OrganizationInfoPage oipp = new OrganizationInfoPage(driver);
		      String actOrgName= oipp.getHeaderMsg().getText();
		      if(actOrgName.contains(orgName)) {
		    	  System.out.println(orgName+" organization name is verified===>Pass");
		      }else {
		    	  System.out.println(orgName + "organization name is not verified===>Fail");
		      }
				
				
				
	}
	@Test(groups="regressionTest")
	public void DeleteOrgTest() throws IOException, InterruptedException {
    
					
							// read testscriptdata from excel
					String orgName = eLib.getDataFromExcel("org",7,2) +jLib.getRandomNumber();
					
				
					// step 2: navigate to the organization module
					HomePage hm = new HomePage(driver);
					hm.getOrgLink().click();
					
					
					// step 3: click on "create Organizations Button"
					OrganizationsPage oip = new OrganizationsPage(driver);
					oip.getCreateNewOrgBtn().click();
					
					// step4:enter all the details and create new organization
					CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
					cnop.createOrg(orgName);
					
					
					//verify header message Expected Result
			      OrganizationInfoPage oipp = new OrganizationInfoPage(driver);
			      String actOrgName= oipp.getHeaderMsg().getText();
			      if(actOrgName.contains(orgName)) {
			    	  System.out.println(orgName+" organization name is verified===>Pass");
			      }else {
			    	  System.out.println(orgName + "organization name is not verified===>Fail");
			      }
			      
			      //go back to organization page
					hm.getOrgLink().click();
					
			      //search for organization
               oip.getSearchEdt().sendKeys(orgName);	
               wLib.select(oip.getSearchDD(),"Organization Name");
               oip.getSearchBtn().click();
					
					
					//In dynamic web table select and delete organization
			      
				driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
              Thread.sleep(2000);
				wLib.switchtoAlertAndAccept(driver);
					
	}
	
	@Test(groups="regressionTest")
	public void createOrgWithPhoneNo() throws EncryptedDocumentException, IOException, InterruptedException {
	
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

	@Test (groups="regressionTest")
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
