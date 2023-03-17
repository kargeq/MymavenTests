package tests.mavens;

/***
 * @author Kareem Ateeqi
 * @Note Location popups and 
 */

//lots of testing 
import static org.junit.jupiter.api.Assertions.*;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.JavascriptExecutor;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;



class LeaseTest {
	public static void takeScreenshot(WebDriver driver, File screenshotFile) throws 
	IOException {
	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    Files.copy(scrFile.toPath(), screenshotFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	void waitForLoad(WebDriver driver) {
	    new WebDriverWait(driver, Duration.ofSeconds(30)).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
///Users/ka/Desktop/Torturedfsfdsfasfdsafafas
	String workingDirectory=System.getProperty("user.dir");
	private static WebDriverWait wait ;
	public void safeJavaScriptClick(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}

	public void LeaseDetails(String type) {
		if (type.equals("empty")) {
			return;
		}
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[1]/input[1]"))
				.sendKeys("randomWord");
	}

	public void LeaseDetailsInfo() {
		String xpath = "/html/body/div[1]/div/div/div[3]/form/section[1]/p[2]";
		wait.until(ExpectedConditions.textToBe(By.xpath(xpath), "The sublease title field is required."));
	}

	public void GeneralLocation(String type) {
		if (type.equals("empty")) {
			return;
		}
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[1]/input[2]"))
				.sendKeys("randomWord");
	}

	public boolean GeneralLocationInfo() {
		String xpath = "/html/body/div[1]/div/div/div[3]/form/section[1]/p[4]";
		return driver.findElement(By.xpath(xpath)).getText().equals("The location field is required.");
	}

	public void Duration(String type) {
		if (type.equals("empty")) {
			return;
		}
		List<WebElement> dates = driver.findElements(By.id("datepicker"));
		WebElement from = dates.get(0);
		WebElement until = dates.get(1);
		if (type.equals("valid")) {// valid date put here
			from.sendKeys("11111111");
			until.sendKeys("12121212");
			return;
		} else if (type.equals("invalid")) {// invalid date put here
			until.sendKeys("11111111");
			from.sendKeys("12121212");
		}

	}

	public boolean DurationInfo(String type) {
		String xpathFrom="/html/body/div[1]/div/div/div[3]/form/section[1]/div[1]/div[1]/p";
		String xpathUntil="/html/body/div[1]/div/div/div[3]/form/section[1]/div[1]/div[2]/p";
		if (type.equals("empty")){
			return driver.findElement(By.xpath(xpathFrom)).getText().equals("The date from field is required.")
					&& driver.findElement(By.xpath(xpathUntil)).getText().equals("The date to field is required.");
			
		}
			
			List<WebElement> myList= driver.findElements
					(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[1]//p"));
			for(int i=0; i<myList.size(); i++) {
				if (myList.get(i).getText().equals("The date to must be a date after date from.")) {
					return true;
				}
			}
		return false;
		
		
	}
	
	public void RentalInfo(String type) {
		if (type.equals("empty")) {
			return;
		}
		WebElement Rentinfo = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[1]/input[3]"));
		
		if (type.equals("valid")) {// invalid date put here
			Rentinfo.sendKeys("123");
			
			return;
		} else if (type.equals("invalid")) {// valid date put here
			Rentinfo.sendKeys("-1e");
		}
	

	}
	
	public boolean RentalInfoInfo(String type) {
		String emptyError="/html/body/div[1]/div/div/div[3]/form/section[1]/p[7]";
		if (type.equals("adjust")) {
			 emptyError="/html/body/div[1]/div/div/div[3]/form/section[1]/p[6]";
		}else if (type.equals("emergency")) {
			try {
				driver.findElement(By.xpath(emptyError)).getText().equals("The rent field is required.");
			}catch(Exception e) {
				return true;
			}
		}

	
			return driver.findElement(By.xpath(emptyError)).getText().equals("The rent field is required.");
		
	}
	

	public void Description(String type) {
		if (type.equals("empty")) {
			return;
		}
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[2]/textarea"))
				.sendKeys("randomWord");
	}

	


	public void images(String type) {
		if (type.equals("empty")) {
			return;
		} else if (type.equals("1")) {
			WebElement Atatch = driver.findElement(By.id("image_uploads"));
			
			String imageName="mitten.jpg";
			String imageLocation=workingDirectory+File.separator+imageName;
			Atatch.sendKeys(imageLocation);

		}
	}
	

	public boolean imagesInfo() {
		List<WebElement> myList= driver.findElements
				(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[2]/p"));
		for(int i=0; i<myList.size(); i++) {
			if (myList.get(i).getText().equals("The image uploads field is required.")) {
				return true;
			}
		}
	return false;
		
		

		
	}
	
	public void Utilities(String type) {
		if (type.equals("empty")) {
			return;
		} else if (type.equals("1")) {
			
			WebElement webElement = driver.findElement(By.id("checkboxSix"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
		}
	}
	

	public boolean UtitlitiesInfo(String bool) {
		if (bool.equals("FALSE")){
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[2]/div[3]/p")));
			
			
		}else {
		 driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[2]/div[3]/p")).getText().equals("The utilities field is required.");

		}
	return true;
			
		

		
	}
	
	public void Location(String type) {
		if (type.equals("empty")) {
			return;

		} else {
			List<WebElement> inputFields = driver
					.findElements(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[3]/input"));

			if (type.equals("fieldInvalid")) {
				for (int i = 0; i < inputFields.size(); i++) {
					inputFields.get(i).sendKeys("z");
				}
			} else if (type.equals("fieldValid")) {
				inputFields.get(0).sendKeys("Expo Center Drive");
				inputFields.get(2).sendKeys("Novi");
				inputFields.get(3).sendKeys("MI");
				inputFields.get(4).sendKeys("United States");
				inputFields.get(5).sendKeys("48375");

			}

		}
	}


	public boolean LocationInfo(String bool) {
		if (bool.equals("FALSE")){
			 wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[3]/p[2]")));
		}else {
			driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[3]/p[2]")).getText().equals("The street field is required.");
		}
return true;
		
	
	}
	public void Next() {
		driver.findElement(By.id("next")).click();

	}

	public void Submit() {
		driver.findElement(By.id("submit")).click();

	}







	private static WebDriver driver;

	// specify SUT
	private static String url = "[redacted]";
	private static String postUrl = "[redacted]";

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
			    driver.get(url);
			    
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
//


	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
		
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get("http://collegemarketplacedeve-env-2.us-east-1.elasticbeanstalk.com/subleases/create");
		waitForLoad(driver);
	

	}

	@AfterEach
	void tearDown() throws Exception {

	}

	

	@Test
	/***
	 * Base Test input:everything empty
	 * Expected: all warnings of empty displayed
	 */
	@DisplayName("Base Test")

	void BaseTest() {
		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("Fixed");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("empty");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertTrue(DurationInfo("empty"));
		assertTrue(RentalInfoInfo(""));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	@Test
	/***
	 * 
	 * Expected: all warnings of empty except for General Location
	 */
	@DisplayName("All but General Location Empty")

	void GeneralLocationTest() {
		LeaseDetails("empty");
		GeneralLocation("");
		Duration("Fixed");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("empty");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertFalse(GeneralLocationInfo());
		assertTrue(DurationInfo("empty"));
		assertTrue(RentalInfoInfo("adjust"));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	
	@Test
	/***
	 * 
	 * Expected: all warnings of empty except duration which which has an invalid date warning
	 */
	@DisplayName("All but duration invalid")

	void InvalidDateTest() {

		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("invalid");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("empty");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertTrue(DurationInfo("dsafdfs"));
		assertTrue(RentalInfoInfo(""));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	@Test
	/***
	 * 
	 * Expected: all warnings of empty except duration 
	 */
	@DisplayName("All but duration valid")

	void ValidTest() {

		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("valid");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("empty");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertFalse(DurationInfo("dsafdfs"));
		assertTrue(RentalInfoInfo(""));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	
	
	@Test
	/***
	 * 
	 * Expected: all warnings except for rent
	 */
	@DisplayName("All empty but rent valid")

	void rentValidTest() {
		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("Fixed");
		RentalInfo("valid");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("empty");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertTrue(DurationInfo("empty"));
		assertFalse(RentalInfoInfo("adjust"));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	@Test
	/***
	 *
	 * Expected: all warnings except description
	 */
	@DisplayName("Description not empty test")

	void DescritpionTest() {
		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("Fixed");
		RentalInfo("empty");
		Next();
		
		Description("notEmpty");
		images("empty");
		Utilities("empty");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertTrue(DurationInfo("empty"));
		assertTrue(RentalInfoInfo(""));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	@Test
	/***
	 *
	 * Expected: all warnings except images
	 */
	@DisplayName("All empty but images")

	void ImagesTest() {
		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("Fixed");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("1");
		Utilities("empty");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertTrue(DurationInfo("empty"));
		assertTrue(RentalInfoInfo(""));
		Next();
		assertFalse(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	@Test
	/***
	 * Base Test input:everything empty
	 * Expected: all warnings of empty displayed
	 */
	@DisplayName("Utilities Test")

	void UtilitiesTest() {
		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("Fixed");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("1");
		Next();
		Location("empty");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertTrue(DurationInfo("empty"));
		assertTrue(RentalInfoInfo(""));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo("FALSE"));
		Next();
		assertTrue(LocationInfo(""));
		

	}
	@Test
	/***
	
	 * Expected: all warnings of empty except for location
	 */
	@DisplayName("Location Test")

	void LocationInvalidTest() {
		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("Fixed");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("empty");
		Next();
		Location("fieldInvalid");
		Submit();
	
	assertTrue(driver.getCurrentUrl().equals("http://collegemarketplacedeve-env-2.us-east-1.elasticbeanstalk.com/subleases/create"));
		

	}
	@Test
	/***
	
	 * Expected: all warnings of empty except for location which is valid
	 */
	@DisplayName("Location Valid Test")

	void LocationValidTest() {
		LeaseDetails("empty");
		GeneralLocation("empty");
		Duration("Fixed");
		RentalInfo("empty");
		Next();
		
		Description("empty");
		images("empty");
		Utilities("empty");
		Next();
		Location("fieldValid");
		Submit();
		
		LeaseDetailsInfo();
		assertTrue(GeneralLocationInfo());
		assertTrue(DurationInfo("empty"));
		assertTrue(RentalInfoInfo(""));
		Next();
		assertTrue(imagesInfo());
		assertTrue(UtitlitiesInfo(""));
		Next();
		assertTrue(LocationInfo("FALSE"));
		

	}
	
	@Test
	/***
	
	 * Expected: all warnings of empty except for location which is valid
	 */
	@DisplayName("Create and Delete Item Test also availability tests")

	void createAndDeleteTest() {
	
		LeaseDetails("");
		GeneralLocation("");
		Duration("valid");
		RentalInfo("valid");
		Next();
		
		Description("");
		images("1");
		Utilities("1");
		Next();
		Location("fieldValid");
		Submit();
		wait.until(ExpectedConditions.textToBe(By.id("getTitle"), "RandomWord"));//lease Details correct?
		wait.until(ExpectedConditions.textToBe(By.xpath("/html/body/section/div/div[1]/div[2]/div[2]/div[1]/div/div[3]/span"), "randomWord"));
//		assertTrue(driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div[2]/div[1]/div/div[3]/span")).getText().equals("randomWord"));//GeneralLocation Info correct
		
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/section/div/div[1]/div[2]/div[2]/div[1]/div/div[3]/span"),"randomWord"));
		
	     wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#product-right > div.additional-info > div.details > div > div.period > div > div.start > span"),"1111-11-11"));
		assertTrue(driver.findElement(By.cssSelector("#product-right > div.additional-info > div.details > div > div.period > div > div.end > span")).getText().equals("1212-12-12"));//right ending date?
//		String amount=driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div[1]/h1[2]")).getText().replaceAll("\\s", "");
//		System.out.println(amount);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/section/div/div[1]/div[2]/div[1]/h1[2]"),"$123.00"));//check rental info provided
		assertTrue(driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div[1]/p[1]")).getText().equals("randomWord"));//see if description matches 
		assertTrue(driver.findElement(By.cssSelector("#product-right > div.additional-info > div.location > div > p")).getText().equals("Novi, MI"));//verify correct location
		String statusLocation="/html/body/section/div/div[1]/div[2]/div[1]/div/p";
		assertTrue(driver.findElement(By.xpath(statusLocation)).getText().equals("Available"));//verify that item set properly
		driver.findElement(By.xpath("/html/body/section/div/div[1]/div[1]/div[2]/div/div[1]/form[2]/button")).click();
		assertTrue(driver.findElement(By.xpath(statusLocation)).getText().equals("Pending"));//verify that item set properly
		driver.findElement(By.xpath("/html/body/section/div/div[1]/div[1]/div[2]/div/div[1]/form[3]/button")).click();
		assertTrue(driver.findElement(By.xpath(statusLocation)).getText().equals("Leased"));//verify that item set properly
		
		 wait.until(ExpectedConditions.elementToBeClickable(By.id("delete-modal-trigger")));
	    driver.findElement(By.cssSelector("#delete-modal-trigger > i")).click();
	    driver.findElement(By.cssSelector(".deletebtn")).click();
	  
		
	}

	
	
}
