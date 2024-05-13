package com.comcast.crm.basetest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDrverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	public  WebDriver driver = null;
	public  static WebDriver sdriver = null;
	
	

	

	//create object
			public FileUtility fLib  = new FileUtility();
			public	ExcelUtility eLib = new ExcelUtility();
			public JavaUtility jLib = new JavaUtility();
			public	WebDrverUtility wLib = new WebDrverUtility();
			public	DatabaseUtility dbLib = new DatabaseUtility();
			

	@BeforeSuite/*(groups= {"smokeTest","regressionTest"})*/
	public void configBeforeSuite() throws SQLException {
		System.out.println("====Connect To Database,Report Config=====");
		dbLib.getDbconnection();
		
	}
	
	//@Parameters ("BROWSER")
	@BeforeClass/*(groups= {"smokeTest","regressionTest"})*/
	public void configBeforeclass(/*String browser*/) throws IOException {
		System.out.println("===Launch The Browser===");
		String BROWSER = //browser;
				fLib.getDataFromPropertiesFile("browser");

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
	}
	//@Parameters({"username","password"})
	@BeforeMethod/*(groups= {"smokeTest","regressionTest"})*/
	public void configBeforeMethod(/*String username,String password*/) throws IOException {
		System.out.println("===Login To Application===");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME =//username;
				fLib.getDataFromPropertiesFile("username");
		String PASSWORD = //password;
		fLib.getDataFromPropertiesFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
		
	}
	@AfterMethod/*(groups= {"smokeTest","regressionTest"})*/
	public void configAfterMethod(){
		System.out.println("===Logout To Application===");	
		HomePage hp= new HomePage(driver);
		hp.Logout();
	}
	@AfterClass/*(groups= {"smokeTest","regressionTest"})*/
	public void configAfterclass() throws SQLException {
		System.out.println("==Close The Browser==");
		driver.quit();
	}
	
	@AfterSuite/*(groups= {"smokeTest","regressionTest"})*/
	public void configAfterSuite() throws SQLException {
		System.out.println("=====Close Database,Report Backup======");
		dbLib.closeDbconnection();
	

	}
	
}

