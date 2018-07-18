package com.HubSpot.trial;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.DoNotOpen.JavaSyntaxChecker;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class HubspotDemo {
	public static WebDriver driver;
	ExtentTest test;
	ExtentReports extent;
	//@RetryCountIfFailed(10)	
	//Before Method to initialize Extent Report
	@BeforeSuite
	public void init() {
				
		// initialize the HtmlReporter
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();

		// attach only HtmlReporter
		extent.attachReporter(htmlReporter);

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		 System.out.println("on test start");
		
	}
	
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        //below line is just to append the date format with the screenshot name to avoid duplicate names 
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
File finalDestination = new File(destination);
FileUtils.copyFile(source, finalDestination);
        
return destination;
}
	@Test (priority =0)
	  public void browserlaunch() throws IOException, InterruptedException {
		test = extent.createTest("Hubspot Launch");
		test.pass("details");
		test.info("Executing browser hubspot launch");
		
		String file=System.getProperty("user.dir")+"\\TestData\\HSTestData.xlsx";
		
		String un=XL.getCellValue(file, 0, 1, 0);
		String pwd=XL.getCellValue(file, 0, 1, 1);
		//String data=XL.getCellValue(file, 0, 1, 2);
		
		
		driver.get("https://app.hubspot.com/login");
		
		//Maximize window
		driver.manage().window().maximize();
		
		
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		 //UI Selenium code 		
		 driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys(un);
		 driver.findElement(By.xpath("//input[contains(@id,'password')]")).sendKeys(pwd);
		 driver.findElement(By.xpath("//button[contains(@id,'loginBtn')]")).click();
		 driver.getTitle().equals("Reports dashboard");
		 WebDriverWait wt = new WebDriverWait(driver, 10 );
		 
		 wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[contains(@class,'private-page__title')]")));
				 driver.findElement(By.xpath("//img[contains(@class,'nav-avatar')]")).click();
				 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		 driver.findElement(By.xpath("//a[contains(@class,'sign-out')]")).click();
	//	 wt.until("//a[@class,'buttonText'][@value=Sign in with Google]
		// String el = driver.findElement(By.xpath("//h1[contains(@class,'private-page__title')]")).getText();
		 //System.out.println(el);
		 
		 
		
		//kankishore@gmail.com
		//Hubspot@123
				 
			 test.info("Test Ended browser launch");
	}
	
	
	
	@Test (priority =1)
	  public void test2() {
		test = extent.createTest("Login");
		test.fail("details");
			
	}	//RestAssurred Tests
	String APIKey = "bf882b0f-e520-4c81-9673-d14b20f294a9"; //Define key
	
	
   @Test (priority =2)
   public void TestCompany() {
	   test = extent.createTest("RestAssured Test");
	   test.pass("details");
	   test.info("Executing RestAssured Tests");
		
	   String URL="https://api.hubapi.com/companies/v2/companies?hapikey="+APIKey;
	String body="{  \"properties\": [    {      \"name\": \"name\",      \"value\": \"A company name\"    },    {      \"name\": \"description\",      \"value\": \"A company description\"    } ]}";
    given().contentType("application/json").body(body).when().post(URL).then().statusCode(200);

}

   
   
   @Test
	public void addLocationTestCase() {
	   test = extent.createTest("addLocationTestCase Test");
	   test.pass("details");
	   test.info("Executing addLocationTestCase Tests");
		
	   
	   System.out.println("Im in add location test case");
	}
	
	@Test 
	public void addDepartmentTestCase() {
		 test = extent.createTest("addDepartmentTestCase Test");
		   test.fail("details");
		   test.info("Executing addDepartmentTestCase Tests");
			
	//	   org.testng.Assert.fail("you wandered onto the wrong path");
		System.out.println("Im in add department test case");
	}
	
	@Test
	public void webtabledemo() {
 
		

	}
	
	@Test 
	public void addEmployeeTestCase() {
		test = extent.createTest("addEmployeeTestCase Test");
		   test.pass("details");
		   test.info("Executing addEmployeeTestCase Tests");
			
		System.out.println("Im in add employee test case");}
	
	
	@AfterSuite
	public void exitTest() {
		extent.flush();
	}
	
}
