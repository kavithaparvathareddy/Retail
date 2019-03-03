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
import com.training.pom.CategoryPOM;

import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class TC48CreateCategoryAddProductDetailsTests {

	private WebDriver driver;
	private String baseUrl;
	private String mainUrl;
	private AdminLoginPOM adminloginPOM;
	private CategoryPOM categoryPOM;
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
		categoryPOM = new CategoryPOM(driver);
	}
	// Logging details 
	@Test(priority = 1)
	public void Login() throws InterruptedException {

		adminloginPOM.sendUserName("Admin");
		adminloginPOM.sendPassword("admin@123");
		adminloginPOM.clickLoginBtn();
			
	}
	
	//Showing category details and adding category
	@Test(priority = 2)
	public void addCategory() throws InterruptedException {
	    categoryPOM.showCategoriesList();
		categoryPOM.addCategoryDetails();
		String expectedCategoryMessage="Success: You have modified categories!";
		String actualCategoryMessage=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertTrue(actualCategoryMessage.contains(expectedCategoryMessage));
	}
	//Showing product details and adding product
	@Test(priority = 3)
	public void addProduct() throws InterruptedException {
		categoryPOM.showproductList();
		categoryPOM.addProductDetails();
		String expectedProductMessage="Success: You have modified products!";
		String actualProductMessage=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertTrue(actualProductMessage.contains(expectedProductMessage));
	}
}	