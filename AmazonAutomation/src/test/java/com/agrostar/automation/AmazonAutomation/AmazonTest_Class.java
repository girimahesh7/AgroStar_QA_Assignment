package com.agrostar.automation.AmazonAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * <b>Name:</b> AmazonTest_Class<br>
 * <b>Description:</b> For any user, Search for an "Apple iPhone X” which has
 * specifications as "256GB" memory and "Space gray color", to an individual’s
 * amazon cart. Open the product in a new tab and then add it to your cart, From
 * the cart page modify its quantity (multiple gifts). And order it a new
 * address (Agrostar’s) and then logout.
 */
public class AmazonTest_Class extends Amazon_BaseClass {

	AmazonPage_Class amazonPage;

	@Test
	public void verifyFunctionalityOfAddingItemsToCart() {
		String siteUrl = FrameWorkConstants.SITE_URL;
		String userName = FrameWorkConstants.USERNAME;
		String passWord = FrameWorkConstants.PASSWORD;
		String pageTitle = FrameWorkConstants.TITLE;
		String searchProduct = FrameWorkConstants.SEARCH_PRODUCT;
		String productQuantity = FrameWorkConstants.SEARCH_QTY;
		String productSpecification = FrameWorkConstants.PRODUCT_SPICIFICATION;

		String fName = FrameWorkConstants.FNAME;
		String phoneNo = FrameWorkConstants.PH;
		String pinNo = FrameWorkConstants.PIN;
		String add1 = FrameWorkConstants.LINE1;
		String add2 = FrameWorkConstants.LINE2;
		String city = FrameWorkConstants.CITY;

		amazonPage = new AmazonPage_Class(driver);
		sAssert = new SoftAssert();
		String dashBoardText = amazonPage.loginMyAccount(siteUrl, userName, passWord);
		sAssert.assertEquals(dashBoardText.trim(), pageTitle.trim(),
				"Login Not Successfull: Expected Text is: " + pageTitle + "but Found: " + dashBoardText);
		amazonPage.searchProduct(searchProduct, productSpecification);

		Assert.assertTrue(amazonPage.selectProduct(productSpecification),
				"No Product is available for given Search Result :ABORTING PURCHASE");
		sAssert.assertTrue(
				amazonPage.validateProduct(productSpecification).trim().contains(productSpecification.trim()),
				"Wrong Product is Displayed");
		amazonPage.addProductToCart();
		sAssert.assertTrue(amazonPage.modifyOrder(productQuantity).equalsIgnoreCase(productQuantity),
				"Unable to Modify the product Quantity");
		amazonPage.buyProduct();
		amazonPage.addNewAddress(fName, phoneNo, pinNo, add1, add2, city);
		//TODO	//Here for some specific product order is limited only one for per user 
		//so clearing the cart will make sure test will always executed
		amazonPage.clearCart();
		amazonPage.logOut();
		sAssert.assertAll();

	}

}
