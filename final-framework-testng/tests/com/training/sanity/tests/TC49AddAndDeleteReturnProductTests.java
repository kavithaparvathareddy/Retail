package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminLoginPOM;
import com.training.pom.ReturnsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC49AddAndDeleteReturnProductTests {

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private AdminLoginPOM adminloginPOM;
	private ReturnsPOM returnsPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	//Driver Initialization details
	@BeforeClass
	public  void setUpBeforeClass() throws IOException, InterruptedException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		mainUrl = properties.getProperty("mainUrl");
		// open the browser 
		driver.get(mainUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Initialization of POM
		adminloginPOM = new AdminLoginPOM(driver);
		returnsPOM = new ReturnsPOM(driver);
	}
      // Logging Details
	@Test(priority = 1)
	public void Login() throws InterruptedException {

		adminloginPOM.sendUserName("Admin");
		adminloginPOM.sendPassword("admin@123");
		adminloginPOM.clickLoginBtn();
		 
		
	}
	// returns list and adding product return details
	@Test(priority = 2)
	public void addProductReturnDetails() throws InterruptedException {
	    returnsPOM.showReturns();
		returnsPOM.addProductReturndetails();
		String expectedProductReturnMessage="Success: You have modified returns!";
		String actualProductReturnMessage=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertTrue(actualProductReturnMessage.contains(expectedProductReturnMessage));
	}
	// deleting   returnproduct
	@Test(priority = 3)
	public void deleteProductReturned() throws InterruptedException {
		returnsPOM.deleteProductReturnDetails("100");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String expectedProductReturnMessage="Success: You have modified returns!";
		String actualProductReturnMessage=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertTrue(actualProductReturnMessage.contains(expectedProductReturnMessage));
	}
      //Browser closing
	@AfterClass
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}
}


