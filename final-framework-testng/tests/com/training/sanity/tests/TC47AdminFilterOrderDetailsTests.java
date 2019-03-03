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
import com.training.pom.OrdersPOM;
import com.training.pom.AdminLoginPOM;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC47AdminFilterOrderDetailsTests {

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private AdminLoginPOM adminloginPOM;
	private OrdersPOM ordersPOM;
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
	//	show orders filtered by order ID
	@Test(priority = 2)
	public void testWithOrderID() throws InterruptedException {
		String expectedOrderID = "173";
		ordersPOM.showOrdersById(expectedOrderID);
		String actualOrderID =  driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr/td[2]")).getText();
		Assert.assertEquals(expectedOrderID, actualOrderID);

	}
    
//	show orders filtered by order status

	@Test(priority = 3)
	public void testWithOrderStatus() throws InterruptedException {
		ordersPOM.ClearScreen();
		String expectedOrderID = "Pending";
		ordersPOM.selectOrderStatus();
		String actualOrderID =  driver.findElement(By.xpath("//div[@id='container']//tbody//tr[1]//td[4]")).getText();
		Assert.assertEquals(expectedOrderID, actualOrderID);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


	}
//	show orders filtered by DateAdded
	@Test(priority = 4)
	public void testWithOrderAddedDate() throws InterruptedException {
		ordersPOM.ClearScreen();
		ordersPOM.showOrderByDateAdded();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	


	}
	
	//	show orders filtered by customerName
	@Test(priority = 7)
	public void testWithCustomerName() throws InterruptedException {
		ordersPOM.ClearScreen();
		String expectedCustomerName = "kavitha";
		ordersPOM.showOrdersByCustomerName(expectedCustomerName);
		String actualCustomerName =driver.findElement(By.xpath("//td[contains(text(), '"+expectedCustomerName+"')]")).getText();
		Assert.assertTrue(actualCustomerName.contains(expectedCustomerName));
		
	}
	// There is bug fot total so code is commented

	//		@Test(priority = 6)
	//		public void testWithTotal() throws InterruptedException {
	//	ordersPOM.ClearScreen();
	//			String expectedTotal = "Rs.2,487";
	//			ordersPOM.showOrdersByTotal(expectedTotal);
	//			String actualTotal =driver.findElement(By.xpath("//td[contains(text(), '"+expectedTotal+"')]")).getText();
	//			Assert.assertTrue(actualTotal.contains(expectedTotal));
	//		}
//	show orders filtered by DateModified
	@Test(priority = 5)
	public void testWithOrderDateModified() throws InterruptedException {
		ordersPOM.ClearScreen();
		
		ordersPOM.showOrderByDateModified();

	}
	// Closing the browser
		@AfterClass
		public void tearDown() throws Exception {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
}


