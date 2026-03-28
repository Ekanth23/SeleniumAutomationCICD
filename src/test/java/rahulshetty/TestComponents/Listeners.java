package rahulshetty.TestComponents;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshetty.properties.ExtendReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtendReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		
		extendTest.set(extent.createTest(result.getMethod().getMethodName())); 
//		extendTest.set(test);//unique thread id --> creates map [thread id : test name] 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		
		extendTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		if(extendTest.get() !=null) {
		extendTest.get().fail(result.getThrowable());//get the error message 
		}
		
		try {
//			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());

//			driver = (WebDriver) result.getInstance().getClass().getSuperclass().getDeclaredField("driver").get(result.getInstance());
			Field field = result.getInstance()
		            .getClass()
		            .getSuperclass()
		            .getDeclaredField("driver");

		    field.setAccessible(true);

		    driver = (WebDriver) field.get(result.getInstance());

		    System.out.println("Driver from listener: " + driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//take screenshot and attach with the report 
		
	    if (driver == null) {

	        System.out.println("Driver is null — screenshot skipped");

	        return;
	    }
		
		String filePath=null;
		
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		extendTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		if (filePath != null) {

		    extendTest.get().addScreenCaptureFromPath(
		            filePath,
		            result.getMethod().getMethodName());

		}
		else {

		    System.out.println("Screenshot not added — filePath is null");

		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		
		extent.flush();
	}
	
	
	

}
