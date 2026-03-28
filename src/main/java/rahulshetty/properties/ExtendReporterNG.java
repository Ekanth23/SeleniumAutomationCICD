package rahulshetty.properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {

		// ExtendReports, ExtendSparkReporter classes 
	

	
	public static ExtentReports getReportObject() {
		
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
		
		reporter.config().setReportName("Web Automation Results");
		
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ekanth");
		
		return extent;
		
	}
		
}
