package rahulshetty.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshetty.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		
	
		WebDriver driver = new ChromeDriver(); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		
		String productName="ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("ekanthrajan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@123"); 
		driver.findElement(By.id("login")).click();
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(5)); 
		
		ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".col-lg-4")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		
		//Streams
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().contains("ZARA COAT 3")).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//conventional method 
		
//		for(int i=0; i<products.size(); i++)
//		{
//			if(products.get(i).findElement(By.cssSelector("b")).getText() == "ZARA COAT 3")
//			{
//			
//				products.get(i).findElement(By.cssSelector(".card-body button:last-of-type")).click();
//			}
//		}
		
		ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		
		ww.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName)); 
		Assert.assertTrue(match);
		
		//click on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click(); 
		
		
		//fill the Personal information 
		driver.findElement(By.cssSelector("[class='input txt']:nth-child(2)")).sendKeys("123");
		
		driver.findElement(By.xpath("//div[text()='Name on Card ']/following-sibling::input[@class='input txt']")).sendKeys("ekanth lingam"); 
		
		//select on the dropdown 
		
		List<WebElement> countryList = driver.findElements(By.cssSelector("section button.list-group-item"));
		
		Actions a = new Actions(driver); 

		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		ww.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section button.list-group-item"))); 
		//css ==> section button.list-group-item:nth-of-type(2)
		//xpath ==> (//section/button[contains(@class, 'ta-item')])[2]
		driver.findElement(By.xpath("(//section/button[contains(@class, 'ta-item')])[2]")).click(); 
		
		//place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//thank you page 
		String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
