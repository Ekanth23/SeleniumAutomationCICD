package rahulshetty.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshetty.TestComponents.BaseTest;
import rahulshetty.TestComponents.Retry;
import rahulshetty.pageobjects.ConfirmationPage;
import rahulshetty.pageobjects.MyCartPage;
import rahulshetty.pageobjects.PlaceOrderPage;
import rahulshetty.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException
	{
		
		String productName="ZARA COAT 3";
		landingpage.loginapplication("ekanthrajan@gmail.com", "Password123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
	
		String productName="ZARA COAT 3";
		
		ProductCatalogue productcatalogue = landingpage.loginapplication("ekanthautomation@gmail.com", "Password@123");		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		
		MyCartPage mycart=productcatalogue.goToCartPage(); 
		Boolean match = mycart.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		
	}	
		
		
		
		
		
		
		
		
		
		
		
		
		

	}
