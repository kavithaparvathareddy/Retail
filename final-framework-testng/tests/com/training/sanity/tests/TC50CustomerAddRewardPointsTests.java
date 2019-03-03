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
import com.training.pom.CustomersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC50CustomerAddRewardPointsTests{

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private AdminLoginPOM adminloginPOM;
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
		adminloginPOM = new AdminLoginPOM(driver);
		customersPOM = new CustomersPOM(driver);
	}
	//Login into URL and show customer list
	@Test(priority = 1)
	public void LoginAndCustomerList() throws InterruptedException {

		adminloginPOM.sendUserName("Admin");
		adminloginPOM.sendPassword("admin@123");
		adminloginPOM.clickLoginBtn();

		// Show all customers
		customersPOM.showCustomersList();
	}
	
//Editing customer details by adding reward points
	@Test(priority = 2)
	public void testWithCustomerName() throws InterruptedException {
		customersPOM.EditCustomerDetails();
		String expectedRewardPointMessage="Success: You have modified customers!";
		String actualRewardPointMessage=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertTrue(actualRewardPointMessage.contains(expectedRewardPointMessage));
		customersPOM.saveButton.click();
		String expectedCustomerMessage="Success: You have modified customers!";
		String actualCustomerMessage=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertTrue(actualCustomerMessage.contains(expectedCustomerMessage));
		
		
	}
}	