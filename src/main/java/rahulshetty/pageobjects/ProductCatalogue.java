package rahulshetty.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshetty.AbstractComponents.AbstractComponent;



public class ProductCatalogue extends AbstractComponent{
		
	WebDriver driver; 
	
	
	public ProductCatalogue(WebDriver driver)
	{
		//initilization 
		super(driver);
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	
	}
	
	//pageFactory 
	@FindBy(css=".col-lg-4")
	List<WebElement> products; 
	
	@FindBy(css=".ng-animating")
	WebElement spinner; 
	
	@FindBy(css="[routerlink*=cart]")
	WebElement cartLink; 
	
	
	
	By allProducts = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By tostMessage = By.cssSelector("#toast-container"); 
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(allProducts);
		return products; 
	}
	
	
	public WebElement getProductByName(String porductName)
	{
		
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().contains(porductName)).findFirst().orElse(null);
		return prod;
	
	}
	
	
	public void addProductToCart(String porductName) throws InterruptedException
	{
		WebElement prod = getProductByName(porductName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(tostMessage);
		waitForElementToDisappear(spinner);
		
	}
	

	

	

	
}
