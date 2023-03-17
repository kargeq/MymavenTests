package tests.mavens;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.JavascriptExecutor;

class ListingTest {
	private static WebDriverWait wait ;
	String workingDirectory=System.getProperty("user.dir");

	private static WebDriver driver;
	private static String url = "[redacted]";
	private static String postUrl = "[redacted]";
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



			    options.addArguments("--window-size=1900,800");
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
		driver.get(postUrl);
		waitForLoad(driver);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void OneOrMore() {
	    driver.findElement(By.id("price-input")).click();
	    driver.findElement(By.id("price-input")).sendKeys("11");
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)")).getText(), ("The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).getText(), ("The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).getText(), ("The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).getText(), ("The state field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(10)")).getText(), ("The country field is required."));
	    driver.findElement(By.cssSelector("p:nth-child(12)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(12)")).getText(), "The postcode field is required.");
	}
	@Test
	void allButFree() {
		driver.findElement(By.id("price-type")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("price-type"));
	      dropdown.findElement(By.xpath("//option[. = 'Free']")).click();
	    }
	    driver.findElement(By.id("price-input")).click();
	    {
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("price-input")));
	      String value = driver.findElement(By.id("price-input")).getAttribute("value");
	      assertEquals(value, ("0"));
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    {
	      String value = driver.findElement(By.id("price-input")).getAttribute("value");
	      assertEquals(value, ("0"));
	    }
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)")).getText(), ("The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).getText(), ("The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).getText(), ("The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).getText(), ("The state field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(10)")).getText(), ("The country field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(12)")).getText(), ("The postcode field is required."));
	}
	@Test
	  public void allButGood() {
	    driver.findElement(By.name("condition")).click();
	    {
	      WebElement dropdown = driver.findElement(By.name("condition"));
	      dropdown.findElement(By.xpath("//option[. = 'Good']")).click();
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).getText(), ("The price field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).getText(), ("The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).getText(), ("The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).getText(), ("The state field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).getText(), ("The country field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(12)")).getText(), ("The postcode field is required."));
	  }
	@Test
	  public void allButNegotiable() {
	    
	    driver.findElement(By.id("price-type")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("price-type"));
	      dropdown.findElement(By.xpath("//option[. = 'Negotiable/ OBO (best offer)']")).click();
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).getText(), ("The price field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).getText(), ("The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).getText(), ("The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).getText(), ("The state field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).getText(), ("The country field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(12)")).getText(), ("The postcode field is required."));
	  }
	  @Test
	  public void allButSlightlyUsed() {

	    driver.findElement(By.name("condition")).click();
	    {
	      WebElement dropdown = driver.findElement(By.name("condition"));
	      dropdown.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[1]/div[2]/select/option[3]")).click();
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).getText(), ("The price field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).getText(), ("The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).getText(), ("The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).getText(), ("The state field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).getText(), ("The country field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(12)")).getText(), ("The postcode field is required."));
	  }
	  @Test
	  public void allButUsedNormalWear() {

	    driver.findElement(By.name("condition")).click();
	    {
	      WebElement dropdown = driver.findElement(By.name("condition"));
	      dropdown.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/form/section[1]/div[2]/select/option[4]")).click();
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("prev")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).getText(), ("The price field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)")).getText(), ("The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).getText(), ("The street field is required."));
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).getText(), ("The city field is required."));
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).getText(), ("The state field is required."));
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).getText(), ("The country field is required."));
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(12)")).getText(), ("The postcode field is required."));
	  }
	  @Test
	  public void baseTest() {
	
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("listingForm")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).getText(), ("The price field is required."));
	    driver.findElement(By.cssSelector(".conditionBox > p")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(4)")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).getText(), ("The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).getText(), ("The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).getText(), ("The state field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).getText(), ("The country field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector("p:nth-child(12)")).getText(), ("The postcode field is required."));
	  }
	  @Test
	  public void locationTest() {

	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("street")).click();
	    driver.findElement(By.id("street")).sendKeys("43655 Ashburn Metro Drive ");
	    driver.findElement(By.id("city")).click();
	    driver.findElement(By.id("apartment_floor")).click();
	    driver.findElement(By.id("city")).click();
	    driver.findElement(By.id("city")).sendKeys("Sterling");
	    driver.findElement(By.id("state")).click();
	    driver.findElement(By.id("state")).sendKeys("VA");
	    driver.findElement(By.id("country")).click();
	    driver.findElement(By.id("country")).sendKeys("US");
	    driver.findElement(By.id("postcode")).click();
	    driver.findElement(By.id("postcode")).sendKeys("20142");
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard > p:nth-child(3)")).getText(), ("The item name field is required."));
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard > p:nth-child(5)")).getText(), ("The price field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    assertEquals(driver.findElement(By.cssSelector(".conditionBox > p")).getText(), ("The category field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(4)")).click();
	    assertEquals(driver.findElement(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)")).getText(), ("The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	  }
	  @Test
	  public void nonEmptyTitle() {
	    driver.findElement(By.name("item_name")).click();
	    driver.findElement(By.name("item_name")).sendKeys("dsfdasfasf");
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".default-card > p:nth-child(4)")));
	    driver.findElement(By.cssSelector(".default-card > p:nth-child(4)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(4)"), "The price field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(3)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard > p:nth-child(3)"), "The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	    driver.findElement(By.cssSelector("p:nth-child(10)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(10)"), "The country field is required."));
	    driver.findElement(By.cssSelector("p:nth-child(12)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(12)"), "The postcode field is required."));
	  }
	  @Test
	  public void nothingButOneCategory() {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li:nth-child(2) > label")));
	    driver.findElement(By.cssSelector("li:nth-child(2) > label")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card > p:nth-child(3)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The item name field is required."));
	    driver.findElement(By.cssSelector(".default-card")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard > p:nth-child(5)"), "The price field is required."));
	    driver.findElement(By.id("next")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	    driver.findElement(By.cssSelector("p:nth-child(12)")).click();
	    wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(12)"),"The postcode field is required."));
	  }
	  @Test
	  public void createAndDeleteListing() {
		  driver.get("[redacted]");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name("item_name")));
	    driver.findElement(By.name("item_name")).sendKeys("I Love Cats");
	    driver.findElement(By.id("price-input")).sendKeys("111");
	    driver.findElement(By.cssSelector("li:nth-child(1) > label:nth-child(2)")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.name("tags")).sendKeys("animals");
	    driver.findElement(By.cssSelector("textarea")).sendKeys("cats are fun");
		WebElement Atatch = driver.findElement(By.id("image_uploads"));
	    String imageName="mitten.jpg";
		String imageLocation=workingDirectory+File.separator+imageName;
		Atatch.sendKeys(imageLocation);
		driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("street")).sendKeys("2280 Ivy Road");
	    driver.findElement(By.id("postcode")).sendKeys("22903");
	    driver.findElement(By.id("city")).sendKeys("Charlottesville");
	    driver.findElement(By.id("state")).sendKeys("VA");
	    driver.findElement(By.id("country")).sendKeys("USA");
	    driver.findElement(By.id("submit")).click();
	    
	    
	      wait.until(ExpectedConditions.textToBe(By.id("getTitle"), "I Love Cats"));
	    
	    
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".general-info > p:nth-child(3)"), "cats are fun"));
	    
	    
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".price"), "$111.00 For Sale"));
	    
	    
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".details-outer > div:nth-child(1) > span"), "Fixed"));
	    
	    
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("div:nth-child(3) > span"), "New"));
	    
	    
	      wait.until(ExpectedConditions.textToBe(By.linkText("Furniture"), "Furniture"));
	    
	    
	      wait.until(ExpectedConditions.textToBe(By.linkText("animals"), "animals"));
	    
	    driver.findElement(By.cssSelector("form:nth-child(1) > button:nth-child(3)")).click();
	   
	   
	  
	    wait.until(ExpectedConditions.attributeToBe(By.name("item_name"), "value", "I Love Cats"));

	   
	         wait.until(ExpectedConditions.attributeToBe(By.id("price-input"), "value", "111.00"));

	   
	    driver.findElement(By.id("next")).click();
	    

	    wait.until(ExpectedConditions.attributeToBe(By.name("tags"), "value", "animals"));

	    
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("textarea"), "value", "cats are fun"));

	    driver.findElement(By.id("next")).click();
	    wait.until(ExpectedConditions.attributeToBe(By.id("city"), "value", "Charlottesville"));

	    {
	      String value = driver.findElement(By.id("state")).getAttribute("value");
	      assertEquals(value, ("VA"));
	    }
	  
	    {
	      String value = driver.findElement(By.id("country")).getAttribute("value");
	      assertEquals(value, ("USA"));
	    }
	    
	    {
	      String value = driver.findElement(By.id("postcode")).getAttribute("value");
	      assertEquals(value, ("22903"));
	    }
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".changing-status > form:nth-child(2) > button")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".product-status > p"), "Pending"));
	    }
	    
	    System.out.println("CATCH");
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#product-left > div.user-controls > div > div.changing-status > form:nth-child(3) > button")));
	    driver.findElement(By.cssSelector("#product-left > div.user-controls > div > div.changing-status > form:nth-child(3) > button")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".product-status > p"), "Sold"));
	    }
	    
	    
	    driver.findElement(By.cssSelector("form:nth-child(1) > button:nth-child(3)")).click();
	    driver.findElement(By.cssSelector(".default-card")).click();
	    driver.findElement(By.name("item_name")).clear();
	    driver.findElement(By.id("price-input")).click();
	    driver.findElement(By.id("price-input")).clear();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    driver.findElement(By.name("tags")).clear();
	    driver.findElement(By.cssSelector("textarea")).click();
	    driver.findElement(By.cssSelector("textarea")).clear();
	    driver.findElement(By.cssSelector(".fa-circle-xmark")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(6)")).click();
	    driver.findElement(By.id("street")).clear();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(6)")).click();
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(6)")).click();
	    driver.findElement(By.id("state")).clear();
	    driver.findElement(By.id("listingForm")).click();
	    driver.findElement(By.id("country")).clear();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(6)")).click();
	    driver.findElement(By.id("postcode")).clear();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.cssSelector(".default-card")).click();
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".default-card > p:nth-child(3)")));
	    }
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".default-card > p:nth-child(5)")));
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listingCard > p:nth-child(4)")));
	    }
	    driver.findElement(By.id("next")).click();
	    
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listingCard:nth-child(6) > p:nth-child(3)")));
	    }
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listingCard:nth-child(6) > p:nth-child(6)")));
	    }
	    driver.findElement(By.cssSelector(".listingCard:nth-child(6)")).click();
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listingCard:nth-child(6) > p:nth-child(8)")));
	    }
	    driver.findElement(By.cssSelector(".listingCard:nth-child(6)")).click();
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listingCard:nth-child(6) > p:nth-child(10)")));
	    }
	    driver.findElement(By.cssSelector(".listingCard:nth-child(6)")).click();
	 
	    driver.findElement(By.id("street")).sendKeys("2328 Old Ivy Road");
	    driver.findElement(By.id("country")).click();
	    driver.findElement(By.id("prev")).click();
	    driver.findElement(By.cssSelector("textarea")).click();
	    driver.findElement(By.cssSelector("textarea")).click();
	    driver.findElement(By.cssSelector("textarea")).clear();
	    driver.findElement(By.cssSelector("textarea")).sendKeys("cats are annoying");
	    driver.findElement(By.name("tags")).clear();
	    driver.findElement(By.name("tags")).sendKeys("trouble");
	    driver.findElement(By.cssSelector(".fa-circle-xmark")).click();
	    Atatch = driver.findElement(By.id("image_uploads"));
	     imageName="outputFile.png";
		 imageLocation=workingDirectory+File.separator+imageName;
		Atatch.sendKeys(imageLocation);
	    driver.findElement(By.id("prev")).click();
	    driver.findElement(By.name("item_name")).clear();
	    driver.findElement(By.name("item_name")).sendKeys("I hate Cats");
	    driver.findElement(By.id("price-input")).click();
	    driver.findElement(By.id("price-input")).click();
	    driver.findElement(By.id("price-input")).click();
	    driver.findElement(By.id("price-input")).sendKeys("0");
	    driver.findElement(By.cssSelector("li:nth-child(1) > label:nth-child(2)")).click();
	    driver.findElement(By.cssSelector("li:nth-child(6) > label")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.id("product-right")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.id("getTitle"), "I Hate Cats"));
	    }
	    driver.findElement(By.id("product-right")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".general-info > p:nth-child(3)"), "cats are annoying"));
	    }
//	    driver.findElement(By.linkText("Books")).click();
	  
	  
//	    driver.findElement(By.id("product-right")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.linkText("Books"), "Books"));
	    }
//	    driver.findElement(By.id("product-right")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.linkText("trouble"), "trouble"));
	    }
	    driver.findElement(By.id("right-button")).click();
	    
	    
	     wait.until(ExpectedConditions.elementToBeClickable(By.id("delete-modal-trigger")));
	    driver.findElement(By.cssSelector("#delete-modal-trigger > i")).click();
	    driver.findElement(By.cssSelector(".deletebtn")).click();
	  
	  }
	  
	
}
