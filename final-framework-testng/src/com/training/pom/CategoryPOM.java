package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CategoryPOM {
	//Driver Details
	private WebDriver driver; 
	public CategoryPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//****Categories WebElements******
	

	@FindBy(xpath="//i[@class='fa fa-tags fw']")
	private WebElement cartIcon;
	
	@FindBy(xpath="//li//a[contains(text(),'Categories')]")
	private WebElement categoriesLink;
	
	@FindBy(xpath="//div[@class='pull-right']//a[@class='btn btn-primary']")
	private WebElement addCategory;
	
	@FindBy(xpath="//input[@id='input-name1']")
	private WebElement categoryName;
	
	@FindBy(xpath="//div[@class='note-editable panel-body']")
	private WebElement categoryDescription;
	
	@FindBy(xpath="//input[@id='input-meta-title1']")
	private WebElement metaTagTitle;
	
	@FindBy(xpath="//textarea[@id='input-meta-description1']")
	private WebElement metaTagTitleDescription;
	
	@FindBy(xpath="//a[contains(text(),'Data')]")
	private WebElement dataTab;
	
	@FindBy(xpath="//a[contains(text(),'Design')]")
	private WebElement designTab;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement saveButton;
	
	//****Product WebElements*****************
	
	@FindBy(xpath="//li[@class='active open']//a[contains(text(),'Products')]")
	private WebElement productLink;
	
	@FindBy(xpath="//div[@class='pull-right']//a[@class='btn btn-primary']")
	private WebElement addProduct;
	
	@FindBy(xpath="//input[@id='input-name1']")
	private WebElement productName;
	
	@FindBy(xpath="//div[@class='note-editable panel-body']")
	private WebElement productDescription;
	
	@FindBy(xpath="//input[@placeholder='Meta Tag Title']")
	private WebElement productMetaTagTitle;
	
	@FindBy(xpath="//textarea[@id='input-meta-description1']")
	private WebElement productMetaTagTitleDescription;
	
	@FindBy(xpath="//a[contains(text(),'Data')]")
	private WebElement productdataTab;
	
	@FindBy(xpath="//a[contains(text(),'Links')]")
	private WebElement linksTab;
	
	@FindBy(xpath="//a[@href='#tab-attribute']")
	private WebElement attributeTab;
	
	@FindBy(xpath="//a[@href='#tab-option']")
	private WebElement optionTab;
	
	@FindBy(xpath="//a[@href='#tab-recurring']")
	private WebElement recurringTab;
	
	@FindBy(xpath="//a[contains(text(),'Discount')]")
	private WebElement discountTab;
	
	@FindBy(xpath="//a[contains(text(),'Special')]")
	private WebElement specialTab;
	
	@FindBy(xpath="//a[contains(text(),'Image')]")
	private WebElement imageTab;
	
	@FindBy(xpath="//a[@href='#tab-reward']")
	private WebElement rewardPointsTab;
	
	@FindBy(xpath="//a[contains(text(),'Design')]")
	private WebElement productDesignTab;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement productSaveButton;
	
	@FindBy(xpath="//input[@id='input-model']")
	private WebElement productModel;
	
	@FindBy(xpath="//input[@id='input-category']")
	private WebElement linkCategories;
	
	@FindBy(xpath="//input[@id='input-category']/following-sibling::ul/li/a")
	private WebElement linkCategoriesText;
	
	
	//****Categories METHODS******
	
	//Method to click on categories link
	public void showCategoriesList() {
		Actions action = new Actions(driver);
		action.moveToElement(this.cartIcon).perform();
		this.categoriesLink.click();
	}
	
	// Actions performed while adding a category
	public void addCategoryDetails() {
		this.addCategory.click();
		this.categoryName.sendKeys("ORNAMENTS");
		this.categoryDescription.sendKeys("ornaments for ladies");
		this.metaTagTitle.sendKeys("ORNAMENTS");
		this.metaTagTitleDescription.sendKeys("ornaments for ladies");
		this.dataTab.click();
		this.designTab.click();
		this.saveButton.click();
		
	}
	
	//****Product METHODS******
	
	//Method to click on products link
	
	public void showproductList() {
		Actions action = new Actions(driver);
		action.moveToElement(this.cartIcon).perform();
		this.productLink.click();
	}
	
	// Actions performed while adding a product
	public void addProductDetails() {
		this.addProduct.click();
		this.productName.sendKeys("Finger Ring");
		this.productDescription.sendKeys("Finger Ring for ladies");
		this.productMetaTagTitle.sendKeys("ORNAMENTS");
		this.productMetaTagTitleDescription.sendKeys("Finger Ring for ladies");
		this.productdataTab.click();
		this.productModel.sendKeys("10");
		this.linksTab.click();
		this.linkCategories.click();
		this.linkCategories.sendKeys("orn");
		this.linkCategories.sendKeys(this.linkCategoriesText.getText());
		this.attributeTab.click();
		this.optionTab.click();
		this.recurringTab.click();
		this.discountTab.click();
		this.specialTab.click();
		this.imageTab.click();
		this.productDesignTab.click();
		this.productSaveButton.click();
		
	}
}