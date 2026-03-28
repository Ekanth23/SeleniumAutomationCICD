package rahulshetty.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshetty.TestComponents.BaseTest;
import rahulshetty.pageobjects.ConfirmationPage;
import rahulshetty.pageobjects.LandingPage;
import rahulshetty.pageobjects.MyCartPage;
import rahulshetty.pageobjects.PlaceOrderPage;
import rahulshetty.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationmessage;

	@Given("I landed on the Ecommerce page")
	public void I_landed_on_the_Ecommerce_page() throws IOException
	{
		
		landingPage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productcatalogue = landingpage.loginapplication(username, password);		

	}
	
	
	@When("I add product ZARA COAT {string} from cart")
	public void i_add_product_zara_coat_from_cart(String productName) throws InterruptedException {
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		throw new io.cucumber.java.PendingException();	    
		
	}
	
//	@When("^i add product (.+) from cart$")
//	public void i_add_product_from_cart(String productName) throws InterruptedException
//	{
//		List<WebElement> products = productcatalogue.getProductList();
//		productcatalogue.addProductToCart(productName);
//		throw new io.cucumber.java.PendingException();
//	}
	

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		MyCartPage mycart=productcatalogue.goToCartPage(); 
		Boolean match = mycart.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
 		//click on checkout button
		PlaceOrderPage placeorder = mycart.clickCheckoutButton(); 
		placeorder.personalInfo("123", "ekanth");
		placeorder.secectTheCountry("india");
		
		ConfirmationPage confirmationmessage = placeorder.submitOrder();
		
	}
	
	 
	@Then ("{string} message is displayed on Confirmation page")
	public void  message_displayed_Confirmation_page(String string)
	{
		String confirmationmsg = confirmationmessage.getConfirmationmsg();
		Assert.assertTrue(confirmationmsg.equalsIgnoreCase(string));
		
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string) throws Throwable
	{
		Assert.assertEquals(string, landingpage.getErrorMessage());
		driver.quit();

	}
	
	
}
