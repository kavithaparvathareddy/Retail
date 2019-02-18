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
import com.training.pom.ReturnsPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class FilterProductReturnTests {

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private ReturnsPOM returnsPOM;
	private static Properties properties;
	private ScreenShot screenShot;

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

		returnsPOM = new ReturnsPOM(driver);
	}


	@Test(priority = 1)
	public void showReturnsTest() throws InterruptedException {
		returnsPOM.sendUserName("Admin");
		returnsPOM.sendPassword("admin@123");
		returnsPOM.clickLoginBtn();
		// Show all returns
		returnsPOM.showReturns();

	}
	//Filter by Return ID
	@Test(priority = 2)
	public void testWithReturnID() throws InterruptedException {
		String expectedReturnId = "45";
		returnsPOM.showReturnsById(expectedReturnId);
		String actualReturnID =  driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr/td[2]")).getText();
		Assert.assertEquals(expectedReturnId, actualReturnID);	
	}
	//Filter by customer Name 
	@Test(priority = 3)
	public void testWithCustomerName() throws InterruptedException {
		String expectedCustomerName = "Shilpa";
		returnsPOM.showReturnsByCustomerName(expectedCustomerName);
		String actualCustomerName =driver.findElement(By.xpath("//td[contains(text(), '"+expectedCustomerName+"')]")).getText();
		Assert.assertTrue(actualCustomerName.contains(expectedCustomerName));
	}
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}


