package rahulshetty.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import rahulshetty.pageobjects.LandingPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	
	protected WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initilizeDriver() throws IOException
	{
		
		Properties prop = new Properties(); 
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshetty\\properties\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
//		String borwserName = prop.getProperty("browser"); 
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions(); 
			if(browserName.contains("headless"))
			{
				options.addArguments("--headless=new");
			}
			
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			
		driver = new ChromeDriver(options); 
		driver.manage().window().setSize(new Dimension(1440, 900)); //full screen

		} else if(browserName.equalsIgnoreCase("firefox"))
		{
			
			driver = new FirefoxDriver(); 

		} else if (browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver(); 
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		WebDriverWait ww = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();

		
		return driver; 
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initilizeDriver(); 
		
		System.out.println("Driver initialized: " + driver);
		
		landingpage = new LandingPage(driver);
		
		landingpage.goTo();
		
		return landingpage; 
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod()
	{
		driver.quit();
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String path) throws IOException
	{
		//Read json to String 
		//encoding format: StandardCharsets
		String jsonConnect = FileUtils
				.readFileToString(new File(path), 
				StandardCharsets.UTF_8);
		
		
		//String to HashMap jakson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonConnect, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data; 
			
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		if (driver == null)
		{
			System.out.println("Driver is null - screenshot skipped");
			return null;
		}
		TakesScreenshot ta= (TakesScreenshot)driver;
		File src = ta.getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(src, dest);
		return dest.getAbsolutePath();
//		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	

}
