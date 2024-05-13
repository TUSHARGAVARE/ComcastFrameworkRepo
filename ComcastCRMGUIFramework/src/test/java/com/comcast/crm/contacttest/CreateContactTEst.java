package com.comcast.crm.contacttest;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDrverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.SearchLookUp;

/**
 * @author Tushar Gavare
 */
public class CreateContactTEst extends BaseClass {
	@Test(groups="smokeTest")
	public void createContactTest() throws InterruptedException, IOException {
		
		/*read testscriptdata from excel*/
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to the Contact module
		HomePage hm = new HomePage(driver);
		hm.getContactLink().click();

		// step 3: click on "create contact Button"
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactbutton().click();

		// step 4:enter all the details and create new Contact
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.createContact(lastName);

		// verify the Header phone number info Expected Result
		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertTrue(status);

		
		
		/*if (actHeader.equals(lastName)) {
			System.out.println(lastName + " HEADER is verified ====>PASS");
		} else {
			System.out.println(lastName + " HEADER is not verified ====>FAIL");
		}*/
		
		
	

		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName,lastName);
		soft.assertAll();
				
	//	Assert.assertEquals(actLastName, lastName);

		/*if (actLastName.equals(lastName)) {
			System.out.println(lastName + " Information is verified ====>PASS");
		} else {
			System.out.println(lastName + " Information is not verified ====>FAIL");
		}*/
	}
		
	@Test(groups="regressionTest")
	public void createContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException {
			//read data from excel
			String lastName=eLib.getDataFromExcel("contact",7,3)+jLib.getRandomNumber();
			String orgName=eLib.getDataFromExcel("contact",7,2)+jLib.getRandomNumber();

			HomePage hp =new HomePage(driver);
			hp.getOrgLink().click();;
			
			OrganizationsPage op =new OrganizationsPage(driver);
			op.getCreateNewOrgBtn().click();
			
			CreateNewOrganizationPage cno=new CreateNewOrganizationPage(driver);
			cno.createOrg(orgName);Thread.sleep(2000);
			hp.getContactLink().click();
			ContactPage cp =new ContactPage(driver);
			cp.getCreateContactbutton().click();
			cp.getLastnameEdit().sendKeys(lastName);
			CreateNewContactPage cnp=new CreateNewContactPage(driver);
			cnp.getOrgLookup().click();
			wLib.switchToTabOnURL(driver, "Accounts&action");
			SearchLookUp sl=new SearchLookUp(driver);
			sl.getSearchEdit().sendKeys(orgName);
		   sl.getSearchbutton().click();Thread.sleep(2000);
		   driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();Thread.sleep(2000);		
		   wLib.switchToTabOnURL(driver, "Contacts&action");
		    cp.getSavebutton().click();
					//verify orgName
			
			String actOrgname1 = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actOrgname1.trim().equals(orgName)) {
				System.out.println(orgName+" organisation is verfied ===> pass");
			}else {
				System.out.println(orgName+" organisation is verfied ===> fail");
			}
		}
	@Test(groups="regressionTest")
	public void createContactWithsupportDateTest() throws InterruptedException, EncryptedDocumentException, IOException {
		//read data from excel
				String lastName=eLib.getDataFromExcel("contact",4,2)+jLib.getRandomNumber();
				/*Navigate to contact Module*/
				HomePage hp=new HomePage(driver);
				hp.getContactLink().click();
				/*Navigate to create contact Button*/
				ContactPage cp=new ContactPage(driver);
				cp.getCreateContactbutton().click();

				/*Enter all the details & craete new contact*/
				String enddate = jLib.getRequiredDateYYYYDDMM(30);
				String startdate = jLib.getSystemDateYYYYDDMM();
				CreateNewContactPage cnc=new CreateNewContactPage(driver);
				/**	cnc.createContactWithsupportDateTest(lastName,startdate,enddate);     **/

				cnc.getLastnameEdit().sendKeys(lastName);
				cnc.getSupportStartDate().clear();
				cnc.getSupportStartDate().sendKeys(startdate);Thread.sleep(2000);
				cnc.getSupportEndDate().clear();
				cnc.getSupportEndDate().sendKeys(enddate);
				cnc.getSavebutton().click();;
             
				//verification
				String actlastname=driver.findElement(By.id("dtlview_Last Name")).getText();
				if(actlastname.equals(lastName)) {
					System.out.println(lastName+" lastname is verified==> PASS");}
				else {
					System.out.println(lastName+" lastname is verified==> FAIL");
				}
				String actstartdate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if(actstartdate.equals(startdate)) {
					System.out.println(startdate+" startdate is verified==> PASS");}
				else {
					System.out.println(startdate+" startdate is verified==> FAIL");
				}
				String actenddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actenddate.equals(enddate)) {
					System.out.println(enddate+" enddate is verified==> PASS");}
				else {
					System.out.println(enddate+" enddate is verified==> FAIL");
				}

			}	
}










