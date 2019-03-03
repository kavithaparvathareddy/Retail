package com.training.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ReturnsPOM {
	//Driver Details
	private WebDriver driver; 
	public ReturnsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	
	//Moving mouse to Returns link
	@FindBy(xpath="//i[@class='fa fa-shopping-cart fw']")
	private WebElement cartIcon;

	//navigating to returns link
	@FindBy(xpath="//li[@id='menu-sale']//a[contains(text(),'Returns')]")
	private WebElement returnsLink;

	@FindBy(id="input-return-id")
	private WebElement returnId;

	@FindBy(xpath="//button[contains(text(),'Filter')]")
	private WebElement filterButton;

	@FindBy(id="input-customer")
	private WebElement customerName;


	@FindBy(xpath="//i[@class='fa fa-trash-o']")
	private WebElement deleteButton;

	
	
	//************DeleteReturn WebElements**********
	
	@FindBy(xpath="//i[@class='fa fa-plus']")
	private WebElement addReturn;
	
	@FindBy(xpath="//input[@id='input-order-id']")
	private WebElement productReturnOrderID;
	
	@FindBy(xpath="//input[@id='input-customer']")
	private WebElement productReturnCustomerName;
	
	
	@FindBy(xpath="//input[@placeholder='First Name']")
	private WebElement productReturnFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement productReturnLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement productReturnEmail;
	
	@FindBy(xpath="//input[@placeholder='Telephone']")
	private WebElement productReturnTelephone;
	
	@FindBy(xpath="//input[@id='input-product']")
	private WebElement productReturnProductName;
	
	@FindBy(xpath="//input[@id='input-model']")
	private WebElement productReturnProductModel;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement productReturnSaveButton;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[2]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")
	private WebElement productReturnCheckBox;
	
	
	//********** Filter Product return Methods*******************
	//Show list of all return details
	public void showReturns() {
		Actions action = new Actions(driver);
		action.moveToElement(this.cartIcon).perform();
		this.returnsLink.click();
	}
	//Show list by entered ReturnID
	public void showReturnsById(String returnID){
		ClearScreen();
		this.returnId.sendKeys(returnID);
		this.filterButton.click();
	}
	//Show list by entered customer Name
	public void showReturnsByCustomerName(String customerName) {
		ClearScreen();
		this.customerName.sendKeys(customerName);
		this.filterButton.click();
	}
	//****************Delete Product Return Method*************
	// Check box selection
	public void selectReturnById(String returnID) {
		showReturnsById(returnID);
		driver.findElement(By.xpath("//table/tbody/tr[td[2][contains(text(), '"+ returnID +"')]]/td[1]")).click();
	}
	//Delete and alert functionalities
	public void deleteSelectedItem(String returnID) throws InterruptedException
	{
		selectReturnById(returnID);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.deleteButton.click();
		driver.switchTo().alert().accept();
	}

		//Clear functionality
	public void ClearScreen()
	{
		this.returnId.clear();
	}
	
	//************ADD and DeleteReturn Methods**********
	
	//Add customer details of returned product
	public void addProductReturndetails() {
	this.addReturn.click();
	this.productReturnOrderID.sendKeys("105");
	this.productReturnCustomerName.sendKeys("kavitha");
	this.productReturnFirstName.sendKeys("sooman");
	this.productReturnLastName.sendKeys("bose");
	this.productReturnEmail.sendKeys("sbose@abc.com");
	this.productReturnTelephone.sendKeys("9986577899");
	this.productReturnProductName.sendKeys("Integer vitae iaculis massa");
	this.productReturnProductModel.sendKeys("SKU-003");
	this.productReturnSaveButton.click();
	}
	//deleting the returned prouct
	public void deleteProductReturnDetails(String productReturnOrderID) throws InterruptedException
	{
		this.productReturnCheckBox.click();
		this.deleteButton.click();
		driver.switchTo().alert().accept();
	}
}




