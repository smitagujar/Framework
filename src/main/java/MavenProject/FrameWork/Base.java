package MavenProject.FrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.	util.Properties;

import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	WebDriver C1;

	public WebDriver InitDriver() throws IOException {
		Properties FileProp = new Properties();
		//FileInputStream PropFile = new FileInputStream("C:\\Users\\samee\\eclipse-workspace\\FrameWork\\src\\main\\java\\MavenProject\\FrameWork\\Input.txt");
		FileInputStream PropFile = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\MavenProject\\FrameWork\\Input.txt");
		
		
		FileProp.load(PropFile);
		String Browser = FileProp.getProperty("browser");

		System.out.println("Browser" + Browser);

		if (Browser.equals("chrome")) {
		//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\samee\\Desktop\\Smita Gujar\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			
			
			 C1 = new ChromeDriver();
		} else if (Browser.equals("firefox")) {
			//System.setProperty("webdriver.gecko.driver", "C:\\Users\\samee\\Desktop\\Smita Gujar\\geckodriver.exe");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
			 C1 = new FirefoxDriver();
				
		
		} else if (Browser.equals("edge")) {
			
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\msedgedriver.exe");
			
			//System.setProperty("webdriver.edge.driver", "C:\\Users\\samee\\Desktop\\Smita Gujar\\msedgedriver.exe");
			 C1 = new EdgeDriver();
		}

		return C1;

	}
	
	public String getScreenShotPath (String TestCase, WebDriver Arg) throws IOException
	{
		TakesScreenshot TS = (TakesScreenshot) Arg;
		
		File Source = TS.getScreenshotAs(OutputType.FILE) ;
		
		String destFile = System.getProperty("user.dir") + "\\reports\\" + TestCase + ".png";
		org.apache.commons.io.FileUtils.copyFile(Source, new File(destFile));
	
		return destFile;
	}

}
