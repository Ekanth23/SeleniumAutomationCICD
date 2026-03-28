package rahulshetty.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponent;



public class LandingPage extends AbstractComponent {
		
	WebDriver driver; 
	
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initilization 
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	
	}
	
	//pageFactory 
	@FindBy(id="userEmail")
	WebElement userEmail; 
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement btnLogin; 
	
	@FindBy(css = ".ng-star-inserted")
	WebElement errorMessage; 
	
	//action method 
	
	//action method 
	public ProductCatalogue loginapplication(String email, String pass)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		btnLogin.click();
		
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue; 

	}
	
	public void goTo()
	{
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
	}
	
	public String getErrorMessage()
	{
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	
	

	

	
}
