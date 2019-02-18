package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.ReturnsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class DeleteProductReturnTests {

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private ReturnsPOM returnsPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	//Initializing Driver
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
	// deleting the selected return
	@Test(priority = 2)
	public void deleteWithReturnID() throws InterruptedException {
		String expectedReturnID = "22"; 
		returnsPOM.deleteSelectedItem(expectedReturnID);

		String actualMessage =driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String expectedMessage="Success";
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		boolean expectedDeletedSucessfully= true;
		boolean actualDeletedSucessfully = driver.findElements(By.xpath("//table/tbody/tr[td[2][contains(text(), '" + expectedReturnID + "')]]/td[1]")).size() == 0;
		Assert.assertEquals(actualDeletedSucessfully , expectedDeletedSucessfully);

	}
	@AfterClass
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}