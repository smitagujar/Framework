package MavenProject.FrameWork;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

public class Listeners extends Base implements ITestListener {

	private static final WebDriver Null = null;
	private static final String ExtentTest = null;
	
	ExtentReports extent=  ExtentReporterNG.getReportObject();
	com.aventstack.extentreports.ExtentTest test;
	
	ThreadLocal<com.aventstack.extentreports.ExtentTest> extentTest = new ThreadLocal<com.aventstack.extentreports.ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		
		System.out.println("Teststart");
		
	     test  = extent.createTest(result.getMethod().getMethodName());
	     extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestSuccess(result);
		
		System.out.println("Testsuccess");
		//test.log(Status.PASS, "Test Passed");
		
		extentTest.get().log(Status.PASS, "Test Passed");
		
		String TestName = result.getMethod().getMethodName();
		WebDriver Dr = Null;
		
		try {
			Dr = 	(WebDriver) result.getTestClass().getRealClass().getDeclaredField("D1").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			getScreenShotPath(TestName, Dr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestFailure(result);
	
		System.out.println("TestFailure");
		test.log(Status.FAIL, "Test Failed");
		
		//test.fail(result.getThrowable());
		// Threadsafe - new code
		extentTest.get().fail(result.getThrowable());
		
		String TestName = result.getMethod().getMethodName();
		WebDriver Dr = Null;
		
		try {
			Dr = 	(WebDriver) result.getTestClass().getRealClass().getDeclaredField("D1").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
		//	getScreenShotPath(TestName, Dr);
			
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(TestName, Dr) , result.getMethod().getMethodName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestSkipped(result);
		
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	//	ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// Auto-generated method stub
	//	ITestListener.super.onFinish(context);
		
		extent.flush();
		
	}

	

}
