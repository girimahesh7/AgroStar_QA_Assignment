package com.agrostar.automation.AmazonAutomation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * <b>Name:</b> AmazonPage_Class<br>
 * <b>Description:</b>This class is for perform the Test operation and load the page elements
 */
public class AmazonPage_Class{
	
	WebDriver driver;
	WebDriverWait wait;
	DriverActionClass driverAction;
	Logger log=Logger.getLogger(AmazonPage_Class.class);
	public AmazonPage_Class(WebDriver driver) {
		this.driver = driver;
		driverAction=new DriverActionClass(driver);
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = FrameWorkConstants.ACCOUNT_LINK)
	public WebElement accountLink;
	
	@FindBy(css = FrameWorkConstants.EMAIL_TEXT)
	public WebElement email;
	
	@FindBy(css = FrameWorkConstants.PASS_TEXT)
	public WebElement passWord;
	
	@FindBy(css = FrameWorkConstants.CONTINUE_BTN)
	public WebElement continueBtn;
	
	@FindBy(css = FrameWorkConstants.LOGIN_BTN)
	public WebElement loginBtn;
	
	@FindBy(css = FrameWorkConstants.SEARCH_TEXT)
	public WebElement searchText;
	
	@FindBy(xpath = FrameWorkConstants.SEARCH_GO)
	public WebElement searchGo;

	@FindBy(xpath = FrameWorkConstants.PRODUCT_LIST)
	public List<WebElement> listProduct;
	
	@FindBy(css = FrameWorkConstants.PRODUCT_TITLE)
	public WebElement productTitle;
	
	@FindBy(css = FrameWorkConstants.ADD_PRODUCT_TO_CART)
	public WebElement productToCartBtn;
	
	@FindBy(xpath = FrameWorkConstants.GIFT_CHKBX)
	public WebElement giftCheckbox;
	
	@FindBy(xpath = FrameWorkConstants.CART_BTN)
	public WebElement cartBtn;
	
	@FindBy(xpath = FrameWorkConstants.QTY_DROP)
	public WebElement dropDown;
	
	@FindBy(xpath = FrameWorkConstants.PROCEED_TO_BUY)
	public WebElement proceedToBuy;
	@FindBy(css = FrameWorkConstants.ADDRESS_FNAME)
	public WebElement addressFname;
	@FindBy(css = FrameWorkConstants.ADDRESS_PH)
	public WebElement addressPh;
	@FindBy(css = FrameWorkConstants.ADDRESS_PIN)
	public WebElement addressPin;
	@FindBy(css = FrameWorkConstants.ADDRESS_LINE1)
	public WebElement addressLine1;
	@FindBy(css = FrameWorkConstants.ADDRESS_LINE2)
	public WebElement addressLine2;
	@FindBy(css = FrameWorkConstants.ADDRESS_CITY)
	public WebElement addressCity;
	@FindBy(xpath = FrameWorkConstants.DELIVER_TO_ADDRESS)
	public WebElement deliverToAddress;
	
	@FindBy(xpath = FrameWorkConstants.SIGN_OUT)
	public WebElement signOut;
	@FindBy(xpath = FrameWorkConstants.SIGNOUT_LINK)
	public WebElement signOutLink;
	@FindBy(xpath = FrameWorkConstants.CART)
	public WebElement cartLink;
	@FindBy(xpath = FrameWorkConstants.DELETE_PRODUCT)
	public WebElement deleteProduct;
	
	public String loginMyAccount(String url,String uName,String password)
	{
		driverAction.openUrl(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS );
		driverAction.clickOnElement(accountLink,"Clicking on LoginLink");
		driverAction.setText(email,uName);
		driverAction.clickOnElement(continueBtn,"Clicking on Continue Button");
		driverAction.setText(passWord,password);
		driverAction.clickOnElement(loginBtn,"Clicking on Login Button");
		String title = driver.getTitle();
		return title;
		
	}
	
	public void searchProduct(String productName,String specification)
	{
		log.info("Searching For Product in Search text box");
		driverAction.setText(searchText,productName);
		driverAction.clickOnElement(searchGo,"Clicking on searchGo Button");
		
	}
	
	public boolean selectProduct(String specification)
	{
		boolean isProductPresent=true;
		log.info("Selecting the required product from the list of products");
	 List <WebElement>productList=new ArrayList<WebElement>();
	 
	 productList=driver.findElements(By.xpath(FrameWorkConstants.PRODUCT_LIST));
	 System.out.println("Total Products " +productList.size());
	 if(productList.size()>0){
	 for (int i=0;i < productList.size();i++)
	 {
		 String productName=productList.get(i).getText();
		
		 if(productName.contains(specification))
		 {
			 productList.get(i).click();
		 }
	 }
 
	 }else{
		 log.info("No Product is available For Given Search Result");
		 isProductPresent=false;	 
	 }
		return isProductPresent;
	}
	
	public String validateProduct(String productDetails)
	{
		driverAction.openAndSwitchToNewWindow();
		String displayedProduct=driverAction.getText(productTitle,"value","Getting Product Details");
		System.out.println("displayedProduct"+displayedProduct);
		return displayedProduct;
	}
	
	public void addProductToCart()
	{  	
		log.info("Adding Product To Cart");
		driverAction.clickOnElement(productToCartBtn,"Adding Product To Cart");
		driverAction.clickOnElement(giftCheckbox,"selecting gift Checkbox");
		driverAction.clickOnElement(cartBtn,"View Cart Product");
			}
	public String modifyOrder(String qauntity)
	{    String orderModifyQty=null;
		log.info("Changing Qunatity to Multiple");
		driverAction.selectDropDownValueByText(qauntity,dropDown);
		return orderModifyQty=driverAction.getSelectedDropDownValue(dropDown).trim();
	}
	public void buyProduct()
	{
		log.info("Buy the Product");
		driverAction.clickOnElement(proceedToBuy,"Proceed To Buy");	
	}
	public void addNewAddress(String fname,String ph,String pin,String add1,String add2,String city)
	{
		log.info("Adding New Address To Account ");
		driverAction.setText(addressFname,fname);
		driverAction.setText(addressPh,ph);
		driverAction.setText(addressPin,pin);
		driverAction.setText(addressLine1,add1);
		driverAction.setText(addressLine2,add2);
		driverAction.setText(addressCity,city);
		
	}
	public void clearCart()
	{   driverAction.openAndSwitchToNewWindow();
		log.info("Clear the Cart item before log-out");
		driverAction.clickOnElement(cartLink,"Cart Link");
		driverAction.clickOnElement(deleteProduct,"Delete added Product");
	}
	public void logOut()
	{
		Actions actions = new Actions(driver);
		//new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(signOutLink));
		actions.moveToElement(signOutLink);
		//new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(signOut));
		actions.moveToElement(signOut);
		actions.click().build().perform();
	}
}
