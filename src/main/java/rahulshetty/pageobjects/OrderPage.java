package rahulshetty.pageobjects;

import java.util.List;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshetty.AbstractComponents.AbstractComponent;



public class OrderPage extends AbstractComponent {
		
	WebDriver driver; 
	
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		//initilization 
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	
	}
	
	//pageFactory 
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames; 
	
	@FindBy(css=".totalRow button")
	WebElement btnCheckout; 
	
	
	

	
	//By Rahul
	public Boolean verifyOrderDisplay(String productName)
	{
	
		boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName)); 
		
		return match;
	}
	

	
	public PlaceOrderPage clickCheckoutButton()
	{
		
		btnCheckout.click();
		
		PlaceOrderPage placeorder = new PlaceOrderPage(driver); 
		
		return placeorder;

		
	}
	
	

	

	
}
