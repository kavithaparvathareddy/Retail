package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CustomersPOM{
	//Initiating driver
	private WebDriver driver; 
	public CustomersPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="input-username")
	private WebElement userName; 

	@FindBy(id="input-password")
	private WebElement password;

	@FindBy(xpath="//button[@type='submit']")
	private WebElement loginBtn;

	//navigating to customers menu
	@FindBy(xpath="//i[@class='fa fa-user fw']")
	private WebElement cartIcon;

	//clicking on customers link
	@FindBy(xpath="//li[@id='menu-customer']//a[contains(text(),'Customers')]")
	private WebElement customersLink;

	@FindBy(id="input-name")
	private WebElement customerName;

	@FindBy(xpath="//button[contains(text(),'Filter')]")
	private WebElement filterButton;

	@FindBy(id="input-email")
	private WebElement customerEmail;

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

	public void showCustomersList() {
		Actions action = new Actions(driver);
		action.moveToElement(this.cartIcon).perform();
		this.customersLink.click();
	}

	public void showCustomersByName(String customerName){
		ClearScreen();
		this.customerName.sendKeys(customerName);
		this.filterButton.click();
	}

	public void showCustomersByEmail(String customerEmail) {
		ClearScreen();
		this.customerEmail.sendKeys(customerEmail);
		this.filterButton.click();
	}

	public void ClearScreen()
	{
		// Write the code to clear all textboxes
		this.customerName.clear();
	}

}

