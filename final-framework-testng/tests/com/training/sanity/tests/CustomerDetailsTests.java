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
import com.training.pom.CustomersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CustomerDetailsTests{

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private CustomersPOM  customersPOM ;
	private static Properties properties;
	private ScreenShot screenShot;

	//Iniiating chrome driver
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

		customersPOM = new CustomersPOM(driver);
	}
	//Login into URL
	@Test(priority = 1)
	public void showCustomers() throws InterruptedException {

		customersPOM.sendUserName("Admin");
		customersPOM.sendPassword("admin@123");
		customersPOM.clickLoginBtn();

		// Show all customers
		customersPOM.showCustomersList();
	}

	//Filtering customer with customer name
	@Test(priority = 2)
	public void testWithCustomerName() throws InterruptedException {
		String expectedCustomerName = "kavitha";
		customersPOM.showCustomersByName(expectedCustomerName);
		String actualCustomerName =  driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr/td[2]")).getText();
		Assert.assertTrue(actualCustomerName.contains(expectedCustomerName));	
	}

	//Filtering customer with customer name
	@Test(priority = 3)
	public void testWithCustomerEmail() throws InterruptedException {
		String expectedCustomerEmail = "kavithaparvathareddy@gmail.com";
		customersPOM.showCustomersByEmail(expectedCustomerEmail);
		String actualCustomerEmail =driver.findElement(By.xpath("//td[contains(text(), '"+expectedCustomerEmail+"')]")).getText();
		Assert.assertTrue(actualCustomerEmail.contains(expectedCustomerEmail));
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}


