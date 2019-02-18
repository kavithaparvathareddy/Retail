package com.training.pom;

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

	@FindBy(id="input-username")
	private WebElement userName; 

	@FindBy(id="input-password")
	private WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement loginBtn;

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
	// Check box selection
	public void selectReturnById(String returnID) {
		showReturnsById(returnID);
		driver.findElement(By.xpath("//table/tbody/tr[td[2][contains(text(), '"+ returnID +"')]]/td[1]")).click();
	}
	//Delete and alert functionalities
	public void deleteSelectedItem(String returnID) throws InterruptedException
	{
		selectReturnById(returnID);
		Thread.sleep(2000);
		this.deleteButton.click();
		driver.switchTo().alert().accept();
	}

	public void ClearScreen()
	{
		// Write the code to clear all textboxes
		this.returnId.clear();
	}

}




