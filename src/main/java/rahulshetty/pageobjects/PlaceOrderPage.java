package rahulshetty.pageobjects;

import java.util.List;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import rahulshetty.AbstractComponents.AbstractComponent;



public class PlaceOrderPage extends AbstractComponent {
		
	WebDriver driver; 
	
	
	public PlaceOrderPage(WebDriver driver)
	{
		super(driver);
		//initilization 
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	
	}
	
	//pageFactory 
	@FindBy(xpath ="//div[text()='Name on Card ']/following-sibling::input[@class='input txt']")
	WebElement textboxname; 
	
	@FindBy(css="[class='input txt']:nth-child(2)")
	WebElement textboxcvv; 
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement txtBoxCountry;
	
	@FindBy(xpath = "(//section/button[contains(@class, 'ta-item')])[2]")
	WebElement countryloc;
	
	@FindBy(css=".action__submit")
	WebElement btnSubmit;
	
	By countryele = By.cssSelector("section button.list-group-item");
	
	
	public void personalInfo(String cvvno, String name)
	{		
		enterText(textboxcvv, cvvno);
		enterText(textboxname, name); 
	}
	
	public void secectTheCountry(String country)
	{
		actionClassSendKeys(txtBoxCountry, country);
		
		waitForElementToAppear(countryele);
		
		countryloc.click();
		
		

	}
	
	public ConfirmationPage submitOrder()
	{
		btnSubmit.click();
		
		ConfirmationPage confirmationmessage= new ConfirmationPage(driver);
		
		return confirmationmessage;
	}
	
	

	

	
}
