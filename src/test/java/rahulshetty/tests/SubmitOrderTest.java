package rahulshetty.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshetty.TestComponents.BaseTest;
import rahulshetty.pageobjects.ConfirmationPage;
import rahulshetty.pageobjects.MyCartPage;
import rahulshetty.pageobjects.OrderPage;
import rahulshetty.pageobjects.PlaceOrderPage;
import rahulshetty.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	String productName="ZARA COAT 3";

	@Test(dataProvider="getDataDirect", groups= {"Purchase"})
	public void submitorder(String email, String pass, String prod) throws IOException, InterruptedException
	{
		
	
		
		
//		LandingPage landingpage=launchApplication();
		
		ProductCatalogue productcatalogue = landingpage.loginapplication(email, pass);		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(prod);
		
		MyCartPage mycart=productcatalogue.goToCartPage(); 
		Boolean match = mycart.verifyProductDisplay(prod);
		Assert.assertTrue(match);
		
 		//click on checkout button
		PlaceOrderPage placeorder = mycart.clickCheckoutButton(); 
		placeorder.personalInfo("123", "ekanth");
		placeorder.secectTheCountry("india");
		
		ConfirmationPage confirmationmessage = placeorder.submitOrder();
		String confirmationmsg = confirmationmessage.getConfirmationmsg();
		Assert.assertTrue(confirmationmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	@Test(dataProvider="getData", groups= {"PurchaseMap"})
	public void submitordermap(HashMap <String, String> input) throws IOException, InterruptedException
	{
		
	
		
		
//		LandingPage landingpage=launchApplication();
		
		ProductCatalogue productcatalogue = landingpage.loginapplication(input.get("email"), input.get("pass"));		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("prod"));
		
		MyCartPage mycart=productcatalogue.goToCartPage(); 
		Boolean match = mycart.verifyProductDisplay(input.get("prod"));
		Assert.assertTrue(match);
		
 		//click on checkout button
		PlaceOrderPage placeorder = mycart.clickCheckoutButton(); 
		placeorder.personalInfo("123", "ekanth");
		placeorder.secectTheCountry("india");
		
		ConfirmationPage confirmationmessage = placeorder.submitOrder();
		String confirmationmsg = confirmationmessage.getConfirmationmsg();
		Assert.assertTrue(confirmationmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	
	@Test(dependsOnMethods = {"submitorder"})
	public void orderHistoryTest()
	{
	
		ProductCatalogue productcatalogue = landingpage.loginapplication("ekanthrajan@gmail.com", "Password@123");		
		OrderPage ordersPage = productcatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	
	}
	

	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshetty\\data\\PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
		

	}
	
	@DataProvider
	public Object[][] getDataDirect(){
		
//		HashMap<String, String> map = new HashMap<>();
//		map.put("email", "ekanthrajan@gmail.com");
//		map.put("pass", "Password@123"); 
//		map.put("prod", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<>();
//		map1.put("email", "ekanthautomation@gmail.com");
//		map1.put("pass", "Password@123"); 
//		map1.put("prod", "ADIDAS ORIGINAL");
		
		return new Object[][] { {"ekanthrajan@gmail.com", "Password@123", "ZARA COAT 3"}, 
			{"ekanthautomation@gmail.com", "Password@123", "ADIDAS ORIGINAL"}
			};
		
	}


}
