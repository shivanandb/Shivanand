package com.Launch.trial;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.lang.reflect.Method;
public class HubspotDemo {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	@BeforeSuite
	public void runbeforesuite() {
		extent = new ExtentReports("");
		extent.loadConfig(new File("C:\\Users\\lenovo\\eclipse-workspace\\HubSpot\\extent-config.xml"));
	}
	
	//Before Method to 
	@BeforeMethod
	 public void runbeforemethod(Method method) {
		test =extent.startTest((this.getClass().getSimpleName()+ method.getName()), method.getName());
		test.assignAuthor("Lord Shiva");
		test.assignCategory("Basic Test");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		
		 System.out.println("on test start");
		
		test.log(LogStatus.PASS, "Browser LAunched");

	}
	
	
	@Test
	  public void browserlaunch() {
		driver.get("www.google.com");
		String URL = driver.getCurrentUrl();
		test.log(LogStatus.PASS, "The URL of the page:"+ URL);
		

		//new ChromeDriver();
	}


	
	
}
