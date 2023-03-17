package tests.mavens;
//NOTE ALL CLASSES MUST HAVE THE WORD "TEST" in them lest the tests don't run
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

class FilterTest {
	private static WebDriver driver;

	// specify SUT
	private static String url = "http://127.0.0.1:8000";
	private static String postUrl = "[redacted]";
	private static String workingDirectory=System.getProperty("user.dir");//get user directory
	private static WebDriverWait wait ;
	void waitForLoad(WebDriver driver) {
	    new WebDriverWait(driver, Duration.ofSeconds(30)).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 ChromeOptions options = new ChromeOptions();
		    options.addArguments("--disable-notifications");// disable random notification popups
		    options.addArguments("--disable-geolocation");
//			    options.addArguments("--headless");
				options.addArguments("--remote-allow-origins=*");
				DesiredCapabilities cp=new DesiredCapabilities();
				cp.setCapability(ChromeOptions.CAPABILITY, options);
				options.merge(cp);
			
	 	    options.addArguments("--disable-gpu");
			    String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.517 Safari/537.36";
			    options.addArguments("general.useragent.override",userAgent);
			    options.addArguments("--allow-insecure-localhost");
			    options.addArguments("--ignore-certificate-errors");
			    

			    options.addArguments("--window-size=1200,800");
			    WebDriverManager.chromedriver().setup();
////		        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

			    driver = new ChromeDriver(options); // create an instance of a web browser
			    wait = new WebDriverWait(driver,Duration.ofSeconds(30));
			    driver.get(postUrl);
			    
			    driver.manage().window().maximize();
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			    driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/label[3]/a/i")).click(); // click profile button
			    WebElement emailForm= driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/div[2]/form/input[2]"));
			    emailForm.sendKeys("varsityswap8@gmail.com");
			    WebElement passwordForm = driver.findElement(By.xpath(
			        "/html/body/section/div/div/div/div[2]/div[2]/form/input[3]"));
			    passwordForm.sendKeys("[redacted]");
			    passwordForm.sendKeys(Keys.ENTER);
			    
			    driver.findElement(By.xpath("/html/body/header/div/nav/div[2]/label[3]")).click(); // click profile page again
		

	}
    public void FilterButton() {
    	WebElement elem = driver.findElement(By.id("filterButtonSelector"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elem);
		elem.click();
    	
    }
	public void Type(String type) {
		if (type.equals("all")) {
			return;
		} else if (type.equals("Listing")) {
			driver.findElement(By.id("TYPEBUTTON")).click();
			driver.findElement(By.id("listing")).click();

		} else if (type.equals("Rentable")) {
			driver.findElement(By.id("TYPEBUTTON")).click();
			driver.findElement(By.id("rentable")).click();

		} else if (type.equals("Leasable")) {
			driver.findElement(By.id("TYPEBUTTON")).click();
			driver.findElement(By.id("lease")).click();

		}
		FilterButton();

	}

	public void Categories(String type) {
		if (type.equals("Furniture")) {
			return;
		} else if (type.equals("Clothes")) {
			driver.findElement(By.id("CATEGORIESBUTTON")).click();
			driver.findElement(By.id("Clothes")).click();

		} else if (type.equals("Electronics")) {
			driver.findElement(By.id("CATEGORIESBUTTON")).click();
			driver.findElement(By.id("Electronics")).click();

		} else if (type.equals("Kitchen")) {
			driver.findElement(By.id("CATEGORIESBUTTON")).click();
			driver.findElement(By.id("Kitchen")).click();

		} else if (type.equals("School Accesories")) {
			driver.findElement(By.id("CATEGORIESBUTTON")).click();
			driver.findElement(By.id("SchoolAccess")).click();

		} else if (type.equals("Books")) {
			driver.findElement(By.id("CATEGORIESBUTTON")).click();
			driver.findElement(By.id("Books")).click();

		}
		FilterButton();

	}

	public void Condition(String type) {
		if (type.equals("New")) {
			return;
		} else if (type.equals("Good")) {
			driver.findElement(By.id("CONDITIONBUTTON")).click();
			driver.findElement(By.id("Good")).click();
		} else if (type.equals("Slightly Used")) {
			driver.findElement(By.id("CONDITIONBUTTON")).click();
			driver.findElement(By.id("Slightly Used")).click();
		} else if (type.equals("Used Normal Wear")) {
			driver.findElement(By.id("CONDITIONBUTTON")).click();
			driver.findElement(By.id("Used Normal Wear")).click();
		}
		driver.findElement(By.id("filterButtonSelector")).click();

	}

	public void price(String type) {
		if (type.equals("Negotiable")) {
			return;
		} else if (type.equals("Fixed")) {
			driver.findElement(By.id("PriceButton")).click();
			driver.findElement(By.id("FixedButton")).click();

		} else if (type.equals("Free")) {
			driver.findElement(By.id("PriceButton")).click();
			driver.findElement(By.id("FreeButton")).click();

		}
		FilterButton();

	}

	public void priceAmount(String type) {
		if (type.equals("Normal")) {
			driver.findElement(By.id("minprice")).sendKeys("0");
			driver.findElement(By.id("maxprice")).sendKeys("2000000000");
			driver.findElement(By.id("GOBUTTON")).click();
		} else if (type.equals("minmessup")) {
			driver.findElement(By.id("minprice")).sendKeys("2000000000");
			driver.findElement(By.id("maxprice")).sendKeys("0");
			driver.findElement(By.id("GOBUTTON")).click();
		}
		FilterButton();

	}

	public void Distance(String type) {
		if (type.equals(">2")) {
			return;
		} else if (type.equals("<2")) {
			driver.findElement(By.id("DistanceBUTTON")).click();
			driver.findElement(By.id("distance-half4")).click();

		}
		FilterButton();

	}

	/***
	 * 
	 * @warning Make sure the method below is called the last during tests
	 */

	public void Utilities(String type) {
		if (type.equals("0")) {
			WebElement e = driver.findElement(By.id("filterButtonSelector"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
			return;
		} else if (type.equals("1")) {
			driver.findElement(By.id("UtilitiesButton")).click();
			driver.findElement(By.id("internet")).click();
		}
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get("[redacted]");
		waitForLoad(driver);
		FilterButton();
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

//expected 50 results;
	@Test
	@DisplayName("Base Test")
	void BaseTest() {
		Type("all");
		Categories("Furniture");
		Condition("New");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");
		assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/section/div/div[1]/a")).getText()
				.equals("Results Showing: 50"));

	}

//expected all items with buy tag
	@Test
	@DisplayName("Listing Test")
	void ListingTest() {
		Type("Listing");
		Categories("Furniture");
		Condition("New");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");
		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div[1]/h1[2]/span")).getText()
				.equals("For Sale");

	}

	// expected:all items with rent tag
	@Test
	@DisplayName("Rentable Test")
	void RentableTest() {
		Type("Rentable");
		Categories("Furniture");
		Condition("New");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");

		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div[1]/h1[2]/span")).getText()
				.equals("For Rent");
	}

	// expected:all items with leasable tag
	@Test
	@DisplayName("Leasable Test")
	void LeasableTest() {
		Type("Leasable");
		Categories("Furniture");
		Condition("New");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");
		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		assertTrue(driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div[1]/h1[2]/span")).getText()
				.equals("For Lease"));

	}



	// expected:all items with Electronics category
	@Test
	@DisplayName("Electronics Test")
	void ElectronicsTest() {
		Type("all");
		Categories("Electronics");
		Condition("New");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");

		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		
//System.out.println(driver.findElement(By.cssSelector("#product-right > div.additional-info > div.details > div > div.categories > div > a")).getText());
		
		
		wait.until(ExpectedConditions.textToBe(By.cssSelector("#product-right > div.additional-info > div.details > div > div.categories > div > a"), "Electronics"));


	}


// 	// expected:all items with School Accesories category
// 	@Test
// 	@DisplayName("School Accesories Test")
// 	void SchoolAccesoriesTest() {
// 		Type("all");
// 		Categories("School Accesories");
// 		Condition("New");
// 		price("Negotiable");
// 		priceAmount("Normal");
// 		Distance(">2");
// 		Utilities("0");

// 		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
// 		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
// 		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#product-right > div.additional-info > div.details > div > div.categories > div > a"),"School Accessories"));
		
	
	

// 	}

	// expected:all items with School Accesories category
	@Test
	@DisplayName("Books  Test")
	void BooksTest() {
		Type("all");
		Categories("Books");
		Condition("New");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");

		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);

		Boolean guru99seleniumlink;;
		guru99seleniumlink=wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#product-right > div.additional-info > div.details > div > div.categories > div > a"),"Books"));
		
		
		assertTrue(guru99seleniumlink);

	}

	// expected all items in good condtiion
	@Test
	@DisplayName("Good Test")
	void GoodTest() {
		Type("all");
		Categories("Furniture");
		Condition("Good");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");
		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
//		Boolean guru99seleniumlink;;
//		guru99seleniumlink=wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#product-right > div.additional-info > div.details > div > div:nth-child(3) > span"),"Good"));
//		
//		
//		assertTrue(guru99seleniumlink);
		

	}


	// expected all items in Used Normal Wear condtiion
	@Test
	@DisplayName("Used Normal Wear Test")
	void UsedNormalWearTest() {
		Type("all");
		Categories("Furniture");
		Condition("Used Normal Wear");
		price("Negotiable");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");
		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		   wait.until(ExpectedConditions.textToBe(By.xpath("/html/body/section/div/div[1]/div[2]/div[2]/div[1]/div/div[2]/span"), "Used Normal Wear"));

	}

	// expected all items with fixed price
	@Test
	@DisplayName("Fixed Test")
	void FixedTest() {
		Type("all");
		Categories("Furniture");
		Condition("New");
		price("Fixed");
		priceAmount("Normal");
		Distance(">2");
		Utilities("0");
		assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/section/div/div[1]/a")).getText()
				.equals("Results Showing: 50"));
		Utilities("0");
		WebElement e = driver.findElement(By.xpath("/html/body/div/div[3]/section/div/ul/li[1]/div/div[1]/a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
		wait.until(ExpectedConditions.textToBe(By.xpath("/html/body/section/div/div[1]/div[2]/div[2]/div[1]/div/div[1]/span"), "Fixed"));
	}

}
