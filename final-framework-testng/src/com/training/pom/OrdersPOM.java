package com.training.pom;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrdersPOM {
	//Driver Details
	private WebDriver driver; 
	public OrdersPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Navigate to orders link
	@FindBy(xpath="//i[@class='fa fa-shopping-cart fw']")
	private WebElement cartIcon;

	//Click on Orders link
	@FindBy(xpath="//li//a[contains(text(),'Orders')]")
	private WebElement ordersLink;

	@FindBy(id="input-order-id")
	private WebElement orderId;

	@FindBy(xpath="//button[contains(text(),'Filter')]")
	private WebElement filterButton;

	@FindBy(id="input-customer")
	private WebElement customerName;
	
	//***** Delete Order Test case Web Element *******

	@FindBy(xpath="//div[@class='pull-right']//button[@type='button']")
	private WebElement deleteButton;

	//For change Order Details testcase

	@FindBy(xpath="//tbody//tr[1]//td[8]//a[2]")
	private WebElement editbutton;

	@FindBy(xpath="//div[@class='tab-pane active']//button[@type='button'][contains(text(),'Continue')]")
	private WebElement continuebutton;

	@FindBy(xpath="//ul[@id='order']/li[@class='disabled active']/a")
	private WebElement selectedTab;

	@FindBy(xpath="//table/tbody/tr/td/button[@data-original-title='Remove']")
	private WebElement removeIcon;

	@FindBy(id="input-product")
	private WebElement chooseProduct;

	@FindBy(id="input-quantity")
	private WebElement quantity;

	@FindBy(xpath="//button[@id='button-product-add']")
	private WebElement addProductButton;

	@FindBy(xpath="//button[@id='button-save']")
	private WebElement saveButton;

	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement alertMessage;
	
	//***** Admin Login Tests Web Elements*******

	@FindBy(xpath="//input[@id='input-date-added']")
	private WebElement dateAdded;

	@FindBy(xpath="//input[@placeholder='Date Modified']")
	private WebElement dateModified;

	//There is bug in system for Total .So code is commented
//	@FindBy(xpath="//input[@id='input-total']")
//	private WebElement Total;



	//****METHODS*****

	// Show all orders
	public void showOrdersList() {
		Actions action = new Actions(driver);
		action.moveToElement(this.cartIcon).perform();
		this.ordersLink.click();
	}

	// Show orders filtered by Order ID	
	public void showOrdersById(String orderID){
		ClearScreen();
		this.orderId.sendKeys(orderID);
		this.filterButton.click();
	}

	// Show orders filtered by Order Name
	public void showOrdersByCustomerName(String customerName) {
		ClearScreen();
		this.customerName.sendKeys(customerName);
		this.filterButton.click();
	}
	
	public void clickEditButton() {
		this.editbutton.click();
    }
	
	
	//select the check box
	public void selectOrderById(String orderID) {
		showOrdersById(orderID);
		driver.findElement(By.xpath("//table/tbody/tr[td[2][contains(text(), '"+ orderID +"')]]/td[1]")).click();
	}

	//Delete and Alert message details
	public void deleteSelectedItem(String orderID) throws InterruptedException
	{
		selectOrderById(orderID);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.deleteButton.click();
		driver.switchTo().alert().accept();
	}

	//For change Order Details testcase
	public void editOrderDetails(String orderID) throws InterruptedException
	{
		selectOrderById(orderID);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.editbutton.click();
		Thread.sleep(3000);
		this.continuebutton.click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	
	public String getSelectedTab() 
	{
		return this.selectedTab.getText();
	}
	
	public void getProductDeleted() 
	{
		this.removeIcon.click();
	}

	public void getSaved() throws InterruptedException
	{
		this.saveButton.click();
	}

	public String getAlertMessage() 
	{
		return this.alertMessage.getText();
	}
	public void addProduct() 
	{
		this.chooseProduct.sendKeys("Lorem ipsum dolor sit amet");
		//select the 1st item from filtered result
		WebElement productitem = driver.findElement(By.xpath("//input[@id='input-product']/following-sibling::ul/li/a"));
		Actions action = new Actions(driver);
		action.moveToElement(productitem).perform();
		productitem.click();
		
		this.quantity.clear();
		this.quantity.sendKeys("2");
		this.addProductButton.click();
	}

	public void SelectShippingMethod() 
	{
		WebElement shippingMethodDdl = driver.findElement(By.xpath("//select[@id='input-shipping-method']"));
		shippingMethodDdl.click();
		
		WebElement freeShipping = driver.findElement(By.xpath("//select[@id='input-shipping-method']/optgroup/option"));
		Actions action = new Actions(driver);
		action.moveToElement(freeShipping).perform();
		freeShipping.click();
	}

	public void clickContinueButton() throws InterruptedException
	{
		this.continuebutton.click();
	}

	//// For AdminFilterOrderdetailsTests

	public void selectOrderStatus() throws InterruptedException
	{
		Select OrderStatus = new Select(driver.findElement(By.id("input-order-status")));
		OrderStatus.selectByVisibleText("Pending");
		this.filterButton.click();
	}

	public void showOrderByDateAdded() throws InterruptedException
	{
		WebElement calendarIcon = driver.findElement(By.xpath("//div[@class='col-sm-4']//div[1]//div[1]//span[1]//button[1]"));
		calendarIcon.click();
		WebElement dateDayAdded = driver.findElement(By.xpath("//td[@class='day' or @class='day active today'][contains(text(),'25')]"));
		Actions action = new Actions(driver);
		action.moveToElement(dateDayAdded).perform();
		dateDayAdded.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.filterButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	//There is bug in system for Total .So code is commented
//	public void showOrdersByTotal(String expectedTotal) throws InterruptedException
//	{
//		this.Total.sendKeys(expectedTotal);
//		this.filterButton.click();
//	}
	public void showOrderByDateModified() throws InterruptedException
	{
			
		WebElement calendarIcon = driver.findElement(By.xpath("//div[@class='col-sm-4']//div[2]//div[1]//span[1]//button[1]"));
		calendarIcon.click();
	    List<WebElement> datelinks = driver.findElements(By.xpath("//td[@class='day' or @class='day active today'][contains(text(),'25')]"));
	    WebElement dateDayModified = datelinks.size() == 2 ? datelinks.get(1) : datelinks.get(0); 
		
		Actions action = new Actions(driver);
		action.moveToElement(dateDayModified).perform();
		dateDayModified.click();
	
		this.filterButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	public void ClearScreen()
	{
		// Write the code to clear all textboxes
		this.orderId.clear();
		this.dateAdded.clear();
		this.dateModified.clear();
		this.customerName.clear();

		Select OrderStatus = new Select(driver.findElement(By.id("input-order-status")));
		OrderStatus.selectByIndex(0);

	}
	
//	public void WaitForTabLoad() throws InterruptedException
//	{
//		Thread.sleep(3000);
//	}
	////div[@class='alert alert-danger']

}

