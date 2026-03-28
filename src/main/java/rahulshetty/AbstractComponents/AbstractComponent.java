package rahulshetty.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshetty.pageobjects.MyCartPage;
import rahulshetty.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver; 
	
	
	public AbstractComponent(WebDriver driver)
	{
		//initilization 
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader; 
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader; 

	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		ww.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy)
	{
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		ww.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
//		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(5)); 
//		ww.until(ExpectedConditions.invisibilityOf(ele)); 
	}
	
	public MyCartPage goToCartPage()
	{
		cartHeader.click();
		MyCartPage mycart = new MyCartPage(driver); 
		return mycart;	
	}
	
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage myorder = new OrderPage(driver); 
		return myorder;
	}
	
	public void enterText(WebElement ele, String data)
	{
		ele.sendKeys(data);
	}
	
	public void actionClassSendKeys(WebElement ele, String text)
	{
		
		Actions a = new Actions(driver); 
		a.sendKeys(ele, text).build().perform();
	}
	

}
