package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDrverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest extends BaseClass {
	@Test
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

}
