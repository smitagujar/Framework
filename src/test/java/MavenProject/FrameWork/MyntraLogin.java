package MavenProject.FrameWork;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MyntraLogin extends Base {

	public WebDriver D1;
    public static Logger Log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void Init() throws IOException, InterruptedException {
		
		
		D1 = InitDriver();
		Log.info("Driver Initialized");
		D1.get("https://www.myntra.com");
		Log.info("Driver Initialized");
		 
		D1.manage().window().maximize();
		Thread.sleep(3000);

	}

	@Test(dataProvider = "getUserPass" , priority = 1)
	public void NavigateLogin(String UserName, String Password) throws IOException, InterruptedException {

		System.out.println("Test # 1");
		
		Log.info("Started NavigateLogin ");
		
		D1.findElement(By.xpath("//*[@class='desktop-user']")).click();
		D1.get("https://www.myntra.com/login/password");

		D1.findElement(By.id("mobileNumberPass")).sendKeys("7798993710");
		D1.findElement(By.xpath("//*[@class='form-control has-feedback']")).sendKeys("Saanika@100");
		D1.findElement(By.xpath("//*[@class='form-control has-feedback']")).click();
		D1.findElement(By.xpath("//*[@class='btn primary  lg block submitButton']")).click();

		Thread.sleep(2000);
		Log.info("Completed NavigateLogin ");

	}

	@Test (priority = 2)
	public void SelectandSort() throws IOException, InterruptedException {

		System.out.println("Test # 2");
		
		System.out.println("Before");

		Actions act = new Actions(D1);
		WebElement MenTab = D1.findElement(By.xpath("//*[@class='desktop-main']"));
		act.moveToElement(MenTab).build().perform();

		System.out.println("After");

		D1.findElement(By.xpath("//a[text()='Sweaters']")).click();

		System.out.println("After 1");

		int size1 = D1.findElements(By.xpath("//*[@class='sort-list']/li/label")).size();
		System.out.println("Links Count is :" + size1);

		List<WebElement> Links1 = D1.findElements(By.xpath("//*[@class='sort-list']/li/label"));

		WebElement dropdown = D1.findElement(By.xpath("//*[@id=\"desktopSearchResults\"]/div[1]/section/div[1]/div[1]/div/div/div/span[2]"));
		dropdown.click();

		for (int i = 0; i < Links1.size(); i++) {
			System.out.println("Text:" + Links1.get(i).getText());
			System.out.println("HTML:" + Links1.get(i).getAttribute("innerHTML"));

			Thread.sleep(2000);

			if (Links1.get(i).getText().contains("New")) {
				Links1.get(i).click();
				break;
			}
		}

	}

	@SuppressWarnings("deprecation")
	@Test (priority = 3)
	public void FilterandSelectProduct() throws IOException, InterruptedException {

		System.out.println("Test # 3");
		Log.info("Started FilterandSelectProduct ");
		
		// select brand
		List<WebElement> Links2 = D1.findElements(By.cssSelector("[class='vertical-filters-label common-customCheckbox']"));
		System.out.println("Links2 Count is :" + Links2.size());

		// Select Brand
		for (int i = 0; i < Links2.size(); i++) {
			System.out.println("Text:" + Links2.get(i).getText());
			System.out.println("HTML:" + Links2.get(i).getAttribute("innerHTML"));

			Thread.sleep(3000);
			if (Links2.get(i).getText().contains("Roadster")) {
				Links2.get(i).click();
				break;
			}
		}

		// Select Price Ranges
		List<WebElement> Price = D1.findElements(By.cssSelector("[class='common-customCheckbox vertical-filters-label']"));
		System.out.println("Price List Count is :" + Price.size());
		for (int i = 0; i < Price.size(); i++) {
			System.out.println("Price :" + Price.get(i).getText());
			Thread.sleep(2000);
			if (i < 3) // Select First Three Price List Items
			{
				Price.get(i).click();
				// break;
			}
		}

		// Select Discount
		List<WebElement> Discount = D1.findElements(By.cssSelector("[class='common-customRadio vertical-filters-label']"));
		System.out.println("Discount Count is :" + Discount.size());
		for (int i = 0; i < Discount.size(); i++) {
			System.out.println("Text:" + Discount.get(i).getText());
			Thread.sleep(2000);
			// if (Discount.get(i).getText().contains("40%") )
			// {
			// Discount.get(i).click();
			// break;
			// }

			if (i == 0) // Select first discount
			{
				Discount.get(i).click();
				break;
			}
		}

		Thread.sleep(5000);
		
		// select third product from the list
   	 List<WebElement> Prod = D1.findElements(By.cssSelector("[class='product-imageSliderContainer']"));
	
		System.out.println("Product List Count is :" + Prod.size());
		for (int i = 0; i < Prod.size(); i++) {
			System.out.println("Product :" + Prod.get(i).getText());
			Thread.sleep(2000);
			if (i == 2) // Select First Three Price List Items
			{
				Prod.get(i).click();
				break;
			}
		}

		//String parent = D1.getWindowHandle();
		System.out.println("Parent:- " + D1.getTitle());

		Set<String> s = D1.getWindowHandles();
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {
			String child_window = I1.next();
			// if(!parent.equals(child_window))
			// {
			D1.switchTo().window(child_window);

			System.out.println("Child:- " + D1.getTitle());
			System.out.println(D1.switchTo().window(child_window).getTitle());

			// driver.close();
			// }

		}
		   D1.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		   Log.info("Completed FilterandSelectProduct ");
		   
	}

	@Test(priority = 4)
	public void Size_AddCart() throws IOException, InterruptedException {
		
		System.out.println("Test # 4");
		Log.info("Started Size_AddCart ");
		
		 // Select Size
		    List <WebElement> Siz = D1.findElements(By.xpath("//p[@class='size-buttons-unified-size']"));
	   
		 System.out.println("Siz List Count is :" + Siz.size() );
		 for(int i= 0; i < Siz.size(); i++ )
		 	{
		 		System.out.println("Size Text :" +  Siz.get(i).getText());
		 		Thread.sleep(2000);
	    		if ( i==0 )  // Select First Three Price List Items
		 		{
	    			Siz.get(i).click();
	    			break;
		 		}
			}
	
		 
		 System.out.println("Add to the cart");
		 WebElement Bag = D1.findElement(By.xpath("//*[@Class='myntraweb-sprite pdp-whiteBag sprites-whiteBag pdp-flex pdp-center']"));
		 Bag.click();
	
		
		 
			Log.info("Completed Size_AddCart ");
		 
	}
	
	
	@Test(priority = 5)
	public void MoveToWishlist() throws IOException, InterruptedException {
	
		System.out.println("Test # 5");
		Log.info("Started MoveToWishlist ");
		
			
		 System.out.println("Open Cart");
		 WebElement Cart = D1.findElement(By.xpath("//*[@class='myntraweb-sprite desktop-iconBag sprites-headerBag']"));
		 Cart.click();
	
		 System.out.println("Move to Wishlist->");
	
		 
		 try 
		 {
			 //WebElement CheckList = D1.findElement(By.xpath("//*[@class='bulkActionStrip-activeIcon']"));
			 WebElement Gotit = D1.findElement(By.cssSelector("[class='itemComponents-base-invisibleBackDrop']"));
			 Gotit.click();
			
			 //2
	    	 WebElement Wish = D1.findElement(By.xpath("//*[@class='inlinebuttonV2-base-actionButton bulkActionStrip-desktopBulkWishlist']"));
			 System.out.println("Wish Text 2 :" +  Wish.getText());
	    	 Wish.click();
	    	 
	    	 WebElement Wish2 = D1.findElement(By.xpath("//*[@class='inlinebuttonV2-base-actionButton bulkActionStrip-waterMelon']"));
			 System.out.println("Wish Text 2.1 :" +  Wish2.getText());
	    	 Wish2.click();
	    	 
	    	 
	    	 /*
	    	 System.out.println("Wishlist Items");
         
	    	
	  	    List <WebElement> Wish1 = D1.findElements(By.xpath("//*[@class='inlinebuttonV2-base-actionButton bulkActionStrip-desktopBulkWishlist']"));
	  	    int WishSize = Wish1.size();
	  	    System.out.println("Wish List Count is :" + WishSize );
	      */    
		 
		 }
		 
		 catch(Exception e)
		 {
			 //1
			 //1
			 WebElement Wish = D1.findElement(By.xpath("//*[@class='inlinebuttonV2-base-actionButton itemContainer-base-inlineButton  wishlistButton']"));
			 System.out.println("Wish Text 1 :" +  Wish.getText());
	    	 Wish.click();
	    	 
	    	 /* 
	    	 System.out.println("Wishlist Items");
           
	 	    List <WebElement> Wish1 = D1.findElements(By.xpath("//*[@class='inlinebuttonV2-base-actionButton itemContainer-base-inlineButton  wishlistButton']"));
	 	    int WishSize = Wish1.size();
	 	    System.out.println("Wish List Count is :" + WishSize );
	       */
			
		 }
 		    
 	    System.out.println("Test Ended Successfully");
		
 		Log.info("Completed MoveToWishlist ");
 	    
	}
	
	
	
	@AfterTest
	public void Close() throws IOException, InterruptedException {
		Thread.sleep(5000);
		   System.out.println("Completed Test Execution" );
		   
			Log.info("Closing browser");
			
		// D1.close();
	}

	@DataProvider
	public Object[][] getUserPass() {
		Object[][] data = new Object[1][2];
		data[0][0] = "7798993710";
		data[0][1] = "Saanika@100";

		return data;
	}

}
