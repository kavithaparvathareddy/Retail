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
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class FilterOrderDetailsTests {

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
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

		ordersPOM = new OrdersPOM(driver);
	}

	@Test(priority = 1)
	public void showOrders() throws InterruptedException {

		ordersPOM.sendUserName("Admin");
		ordersPOM.sendPassword("admin@123");
		ordersPOM.clickLoginBtn();
		// Show all orders
		ordersPOM.showOrdersList();
	}
	//show orders filtered by order ID
	@Test(priority = 2)
	public void testWithOrderID() throws InterruptedException {
		String expectedOrderID = "105";
		ordersPOM.showOrdersById(expectedOrderID);
		String actualOrderID =  driver.findElement(By.xpath("//table[@class='table table-bordered table-hover']/tbody/tr/td[2]")).getText();
		Assert.assertEquals(expectedOrderID, actualOrderID);	
	}
	//show orders filtered by customerName
	@Test(priority = 3)
	public void testWithCustomerName() throws InterruptedException {
		String expectedCustomerName = "kavitha";
		ordersPOM.showOrdersByCustomerName(expectedCustomerName);
		String actualCustomerName =driver.findElement(By.xpath("//td[contains(text(), '"+expectedCustomerName+"')]")).getText();
		Assert.assertTrue(actualCustomerName.contains(expectedCustomerName));
	}

	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}


