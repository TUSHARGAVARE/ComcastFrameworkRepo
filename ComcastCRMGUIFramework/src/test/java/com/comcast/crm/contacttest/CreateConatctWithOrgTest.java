package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDrverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateConatctWithOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {

	   //create object
		FileUtility fLib  = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDrverUtility wLib = new WebDrverUtility();

		
				String BROWSER  = fLib.getDataFromPropertiesFile("browser");
				String URL      = fLib.getDataFromPropertiesFile("url");
				String USERNAME =fLib.getDataFromPropertiesFile("username");
				String PASSWORD = fLib.getDataFromPropertiesFile("password");
				
						// read test script data from excel
				String orgName = eLib.getDataFromExcel("contact",7,2) + jLib.getRandomNumber();
				String contactLastName = eLib.getDataFromExcel("contact",7,3) + jLib.getRandomNumber();

	

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		// Step1 :Login to application
		
		wLib.waitForPageToLoad(driver);
		driver.manage().window().maximize();
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url","admin","admin");

		// step 2: navigate to the organization module

		HomePage hm = new HomePage(driver);
		hm.getOrgLink().click();
		
		// step 3: click on "create Organizations Button"
		
		OrganizationsPage oip = new OrganizationsPage(driver);
		oip.getCreateNewOrgBtn().click();
		
		// step4:enter all the details and create new organization
		
		CreateNewOrganizationPage cnop= new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify header message Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " header is created ====>PASS");
		} else {
			System.out.println(orgName + " is not created ====>FAIL");

		}

		// step 5:navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// step 6: click on "create Organizations Button"
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7:enter all the details and create new organization
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
	wLib.switchToTabOnURL(driver,"module=Accounts");
	
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to Parent Window
		wLib.switchToTabOnURL(driver,"Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		Thread.sleep(1000);

		// verify the header of contact
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(contactLastName)) {
			System.out.println(contactLastName + " header is verified ====>PASS");
		} else {
			System.out.println(contactLastName + " header is not verified ====>FAIL");

		}

		// verify the head orgName
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if (actOrgName.trim().equals(orgName)) {
			System.out.println(orgName + " Information is created ====>PASS");
		} else {
			System.out.println(orgName + " Information is not created ====>FAIL");

		}

		// step 5:logout
		Actions act = new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		act.moveToElement(ele).perform();Thread.sleep(1000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();

	}

}
