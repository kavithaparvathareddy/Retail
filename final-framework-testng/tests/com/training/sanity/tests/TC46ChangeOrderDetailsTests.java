package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AdminLoginPOM;
import com.training.pom.OrdersPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC46ChangeOrderDetailsTests {

	private static final WebDriver WebDriverRefrence = null;
	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private AdminLoginPOM adminloginPOM;
	private  OrdersPOM ordersPOM;
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
		//POM initialization details
		adminloginPOM = new AdminLoginPOM(driver);
		ordersPOM = new OrdersPOM(driver);
	}
	// Logging and clicking orders link
	@Test(priority = 1)
	public void showOrders() throws InterruptedException {

		adminloginPOM.sendUserName("Admin");
		adminloginPOM.sendPassword("admin@123");
		adminloginPOM.clickLoginBtn();
		// Show all orders
		ordersPOM.showOrdersList();
	}
	// Filtering through customers name and editing the customer details
	@Test(priority = 2)
	public void editOrderPage() throws InterruptedException {
		
		// Waiting for tab to load fully
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);;
		Thread.sleep(3000);
		// Filter results by customer name
		String customerName = "kavitha";
		ordersPOM.showOrdersByCustomerName(customerName);
		ordersPOM.clickEditButton();

		// Checking for Edit Order Page is loaded
		String actualMessage =driver.findElement(By.xpath("//h3[@class='panel-title']")).getText();
		String expectedMessage="Edit Order";
		Assert.assertTrue(actualMessage.contains(expectedMessage));	

		// Checking for Customer Details tab is loaded
		String expectedCustomerMessage="Customer Details";
		String actualCustomerMessage=ordersPOM.getSelectedTab();
		Assert.assertTrue(actualCustomerMessage.contains(expectedCustomerMessage));	
		
		WebElement faxText = driver.findElement(By.xpath("//input[@id='input-fax']"));
		String faxValue = faxText.getText();
		faxText.sendKeys("1234");
		Thread.sleep(3000);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);;
		faxText.clear();
		faxText.sendKeys(faxValue);

		// Click Continue to proceed to Products tab
		ordersPOM.clickContinueButton();
	}

	// sometimes failures is happening because of displaying message to add IP address.and also message saying  u dont not have permissions
	//It needs to be manually run first and read IP address and then run automation test case

	// Deleting and adding the products
	@Test(priority = 3)
	public void editProductPage() throws InterruptedException {
		

		// Waiting for tab to load fully
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);;
		Thread.sleep(3000);
		// Checking for Products tab is loaded	
		String expectedProductMessage="Products";
		String actualProductMessage=ordersPOM.getSelectedTab();
		Assert.assertTrue(actualProductMessage.contains(expectedProductMessage));	

		// Delete the existing Product
		ordersPOM.getProductDeleted();
		// Add new product
		ordersPOM.addProduct();
		
		// Click Continue to proceed to Products tab		
		ordersPOM.clickContinueButton();
	}


	// Adding payment shipment and totals message
	@Test(priority = 4)
	public void editPaymentAndShipping() throws InterruptedException {
		
		// Waiting for tab to load fully
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);;
		Thread.sleep(3000);
		// Checking for Payment Details tab is loaded	
		String expectedPaymentMessage="Payment Details";
		String actualPaymentMessage=ordersPOM.getSelectedTab();
		Assert.assertTrue(actualPaymentMessage.contains(expectedPaymentMessage));
		
		// Click Continue to proceed to Shipping Details tab		
		ordersPOM.clickContinueButton();
		
		// Waiting for tab to load fully
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);;
		Thread.sleep(3000);
		// Checking for Shipping Details tab is loaded
		String expectedShippingMessage="Shipping Details";
		String actualShippingMessage=ordersPOM.getSelectedTab();
		Assert.assertTrue(actualShippingMessage.contains(expectedShippingMessage));	
		
		// Click Continue to proceed to Totals tab		
		ordersPOM.clickContinueButton();
		
		// Waiting for tab to load fully
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);;
		Thread.sleep(3000);
		// Totals 
		ordersPOM.SelectShippingMethod();
		ordersPOM.getSaved();
		String expectedAlertMessage="Success: You have modified orders!";
		String actualAlertMessage=ordersPOM.getAlertMessage();
		Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));	

	}
	   // Closing the browser
		@AfterClass
		public void tearDown() throws Exception {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
}


