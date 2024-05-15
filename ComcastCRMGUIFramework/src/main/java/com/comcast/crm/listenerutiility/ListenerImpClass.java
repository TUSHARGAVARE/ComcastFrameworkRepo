package com.comcast.crm.listenerutiility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImpClass implements ITestListener,ISuiteListener {
	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("==== ====>"+result.getMethod().getMethodName()+">=====Start======");
		 test= report.createTest(result.getMethod().getMethodName());
		 UtilityClassObject.setTest(test);
		 test.log(Status.INFO, result.getMethod().getMethodName()+"==> Execution Started<===");
	}

	@Override
	public void onStart(ISuite suite) {
		
		
		System.out.println("Report configuration");
		String time = new Date().toString().replace(" ","_").replace(":","_");

		//Spark Report Config
				ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReport/report_"+time+".html");
				spark.config().setDocumentTitle("CRM TEST SUITE RESULT");
				spark.config().setReportName("CRM REPORT");
				spark.config().setTheme(Theme.DARK);

				//Add Env Info & Create Test
				 report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows-10");
				report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("==== ====>"+result.getMethod().getMethodName()+">=====End=====");
		 test.log(Status.PASS, result.getMethod().getMethodName()+"==> Completed<===");


	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		
		//step 1 create an object to eventFiring webdriver
		//EventFiringWebDriver edriver = new EventFiringWebDriver(BaseClass.sdriver);
		//step 2:use get screenshotAs method to get file type of screenshot
		//File srcFile = edriver.getScreenshotAs(OutputType.FILE);
		TakesScreenshot eDriver = (TakesScreenshot) BaseClass.sdriver;
		String filePath = eDriver.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ","_").replace(":","_");
		test.addScreenCaptureFromBase64String(filePath,testName+"_"+time);
		 test.log(Status.FAIL, result.getMethod().getMethodName()+"==> Failed <===");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		 test.log(Status.SKIP, result.getMethod().getMethodName()+"==> Skipped <===");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	

}
