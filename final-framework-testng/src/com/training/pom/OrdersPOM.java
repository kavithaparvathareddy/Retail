package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class OrdersPOM {
	//Driver Details
	private WebDriver driver; 
	public OrdersPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="input-username")
	private WebElement userName; 

	@FindBy(id="input-password")
	private WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement loginBtn;

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

	@FindBy(xpath="//div[@class='pull-right']//button[@type='button']")
	private WebElement deleteButton;


	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}

	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}

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
	//select the check box
	public void selectOrderById(String orderID) {
		showOrdersById(orderID);

		driver.findElement(By.xpath("//table/tbody/tr[td[2][contains(text(), '"+ orderID +"')]]/td[1]")).click();
	}

	//Delete and Alert message details
	public void deleteSelectedItem(String orderID) throws InterruptedException
	{
		selectOrderById(orderID);
		Thread.sleep(2000);
		this.deleteButton.click();
		driver.switchTo().alert().accept();
	}

	public void ClearScreen()
	{
		// Write the code to clear all textboxes
		this.orderId.clear();
	}

}

