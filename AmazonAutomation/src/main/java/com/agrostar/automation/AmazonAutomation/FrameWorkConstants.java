package com.agrostar.automation.AmazonAutomation;

public class FrameWorkConstants {

	/********************************************
	 * TEST DATA
	 ********************************************/

	public static final String BROWSEREXE_PATH = "./src/main/java/resources";
	public static final String SITE_URL = "https://www.amazon.in";
	public static final String USERNAME = "giri.mahesh77@gmail.com";
	public static final String PASSWORD = "Msworld@23";
	public static final String TITLE = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	public static final String SEARCH_PRODUCT = "apple iphone x 256 gb space grey";
	public static final String SEARCH_QTY = "1";
	public static final String PRODUCT_SPICIFICATION = "(256GB) - Space Grey";

	// ***Time out for WebDriverWait in second
	public static final int WEBDRIVER_WAIT_TIMEOUT = 60;

	public static final String FNAME = "AgroStar";
	public static final String PH = "9898909867";
	public static final String PIN = "411015";
	public static final String LINE1 = "Viman Nagar";
	public static final String LINE2 = "AirPort Road";
	public static final String CITY = "Pune";

	/********************************************
	 * PAGE LOCATORS
	 ********************************************/

	public static final String ACCOUNT_LINK = "//span[contains(text(),'Hello. Sign in')]";
	public static final String EMAIL_TEXT = "#ap_email";
	public static final String CONTINUE_BTN = "#continue";
	public static final String PASS_TEXT = "#ap_password";
	public static final String LOGIN_BTN = "#signInSubmit";
	public static final String SEARCH_TEXT = "#twotabsearchtextbox";
	public static final String SEARCH_GO = "//input[@type='submit'][@value='Go']";
	public static final String PRODUCT_LIST = "//span[@class='a-size-medium a-color-base a-text-normal']";
	public static final String PRODUCT_TITLE = "#productTitle";
	public static final String ADD_PRODUCT_TO_CART = "#add-to-cart-button";
	public static final String GIFT_CHKBX = "//div[@class='a-checkbox sc-gift-option a-align-top a-size-small']//label//input";
	public static final String CART_BTN = "//a[@id='hlb-view-cart-announce']";
	public static final String QTY_DROP = "//select[@name='quantity']";
	public static final String PROCEED_TO_BUY = "//input[@name='proceedToCheckout']";
	public static final String ADDRESS_FNAME = "#enterAddressFullName";
	public static final String ADDRESS_PH = "#enterAddressPhoneNumber";
	public static final String ADDRESS_PIN = "#enterAddressPostalCode";
	public static final String ADDRESS_LINE1 = "#enterAddressAddressLine1";
	public static final String ADDRESS_LINE2 = "#enterAddressAddressLine2";
	public static final String ADDRESS_CITY = "#enterAddressCity";
	public static final String DELIVER_TO_ADDRESS = "//span[@class='a-button a-button-primary a-padding-none']//input[@name='shipToThisAddress']";
	public static final String SIGNOUT_LINK = "//span[contains(text(),'Hello, DemoAccount')]";
	public static final String SIGN_OUT = "//span[contains(text(),'Sign Out')]";
	public static final String CART = "//span[@class='nav-cart-icon nav-sprite']";
	public static final String DELETE_PRODUCT = "//input[@value='Delete']";
}
