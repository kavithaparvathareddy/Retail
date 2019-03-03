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
	
	//*************Customer WebElements****************

	
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
	
	//*************Customer Reward PointsWebElements****************
	
	
	@FindBy(xpath="//table/tbody/tr[td[contains(text(),'kavitha p')]]/td[input[@type='checkbox']]")
	private WebElement editcustomerToAddRewardCheckBox;
	
	@FindBy(xpath="//table/tbody/tr[td[contains(text(),'kavitha p')]]/td/a[@data-original-title='Edit']")
	private WebElement editcustomerToAddRewardEditClick;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement editCustomerFirstName;
	
	@FindBy(xpath="//a[contains(text(),'Address 1')]")
	private WebElement editCustomerAddress1;
		
	@FindBy(xpath="//input[@id='input-postcode1']")
	private WebElement editCustomerPostalCode;
	
	@FindBy(xpath="//a[@href='#tab-reward']")
	private WebElement editRewardPointsLink;
	
	@FindBy(xpath="//input[@id='input-reward-description']")
	private WebElement editDescription;
	
	@FindBy(xpath="//input[@id='input-points']")
	private WebElement editRewardPoints;
	
	@FindBy(xpath="//button[@id='button-reward']")
	private WebElement AddRewardPoints;
	
	@FindBy(xpath="//button[@type='submit']")
	public WebElement saveButton;
		
	
	
	//*************Customer Reward PointsMethods****************
	
	public void EditCustomerDetails() {
		this.editcustomerToAddRewardCheckBox.click();
		this.editcustomerToAddRewardEditClick.click();
		this.editCustomerFirstName.clear();
		this.editCustomerFirstName.sendKeys("kavitha");
		this.editCustomerAddress1.click();
		this.editCustomerPostalCode.sendKeys("500072");
		this.editRewardPointsLink.click();
		this.editDescription.sendKeys("Exclusive Shopping");
		this.editRewardPoints.sendKeys("100");
		this.AddRewardPoints.click();
		//this.saveButton.click();
		
	}
	
	
	//*************CustomerDetails Methods****************
	
	

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
		this.customerName.clear();
	}

}

