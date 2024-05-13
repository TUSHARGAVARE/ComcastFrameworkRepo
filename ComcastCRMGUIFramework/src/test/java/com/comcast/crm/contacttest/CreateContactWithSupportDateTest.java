package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDateTest extends BaseClass {
	
	@Test

	public void CreateContactWithSupportDateTest() throws InterruptedException, IOException {
				
   
		//read data from excel
		String lastName=eLib.getDataFromExcel("contact",7,5)+jLib.getRandomNumber();
	
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click();
		
		ContactPage cp=new ContactPage(driver);
		cp.getCreateContactbutton().click();

		CreateNewContactPage cnc=new CreateNewContactPage(driver);
		cnc.getLastnameEdit().sendKeys(lastName);
		
		String startdate = jLib.getSystemDateYYYYDDMM();
		String enddate = jLib.getRequiredDateYYYYDDMM(30);
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
		/*Actions a=new Actions(driver);Thread.sleep(2000);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();*/
	
				
	}

}
