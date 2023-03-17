package tests.mavens;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
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


class RentableTest {
	private static WebDriver driver;
	private static WebDriverWait wait ;
	private static String url = "[redacted]";
	private static String postUrl = "[redacted]";
	String workingDirectory=System.getProperty("user.dir");
	void waitForLoad(WebDriver driver) {
	    new WebDriverWait(driver, Duration.ofSeconds(30)).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
	@BeforeAll
	
	static void setUpBeforeClass() throws Exception {

	 ChromeOptions options = new ChromeOptions();
	    options.addArguments("--disable-notifications");// disable random notification popups
	    options.addArguments("--disable-geolocation");
//		    options.addArguments("--headless");
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
////	        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

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

	  
	  @DisplayName("Submitting Empty Form")
	  @Test
	  public void emptySubmissionRent() {
	
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(12)"), "The postcode field is required."));
	    
	  }
	  @Test 
	  public void validLocation() {
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("street")).click();
	    driver.findElement(By.id("street")).sendKeys("32345 Cannon Road");
	    driver.findElement(By.id("postcode")).click();
	    driver.findElement(By.id("city")).click();
	    driver.findElement(By.id("city")).sendKeys("Solon");
	    driver.findElement(By.id("state")).click();
	    driver.findElement(By.id("state")).sendKeys("OH");
	    driver.findElement(By.id("country")).click();
	    driver.findElement(By.id("country")).sendKeys("US");
	    driver.findElement(By.id("postcode")).click();
	    driver.findElement(By.id("postcode")).sendKeys("22901");
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	  }


	  @Test
	  public void allbutMonthly() {
	    driver.findElement(By.id("rentalDuration")).click();
	      WebElement dropdown = driver.findElement(By.id("rentalDuration"));
	      dropdown.findElement(By.xpath("//option[. = 'Monthly']")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(12)"), "The postcode field is required."));
	  }
	  @Test
	  public void allEmptybutChangedToWeekly() {
	   
	    driver.findElement(By.id("rentalDuration")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("rentalDuration"));
	      dropdown.findElement(By.xpath("//option[. = 'Weekly']")).click();
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(12)"), "The postcode field is required."));
	  

}
	  @Test
	  public void allbutDaily() {
	    driver.findElement(By.id("rentalDuration")).click();
	      WebElement dropdown = driver.findElement(By.id("rentalDuration"));
	      dropdown.findElement(By.xpath("//option[. = 'Daily']")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(12)"), "The postcode field is required."));
	  }
	  @Test
	  public void allButGood() {
	    driver.findElement(By.name("condition")).click();
	      WebElement dropdown = driver.findElement(By.name("condition"));
	      dropdown.findElement(By.xpath("//option[. = 'Good']")).click();
	    driver.get("[redacted]");
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();	 
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(12)"), "The postcode field is required."));

	  }
	  @Test
	  public void allButNegotiableSelected() {
	    driver.findElement(By.name("negotiable")).click();
	    {
	      WebElement dropdown = driver.findElement(By.name("negotiable"));
	      dropdown.findElement(By.xpath("//option[. = 'Negotiable/ OBO (best offer)']")).click();
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required.")); 
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(12)"), "The postcode field is required."));
		}
	  
	  @Test
	  public void allButImages() {
	    driver.findElement(By.id("next")).click();
	    WebElement Atatch = driver.findElement(By.id("image_uploads"));
		String imageName="mitten.jpg";
		String imageLocation=workingDirectory+File.separator+imageName;
		Atatch.sendKeys(imageLocation);
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(3)"), "The rental title field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(7)"), "The rental charging field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(12)"), "The postcode field is required."));
	  
	  }
	  @Test
	  public void aLLEmptyExceptTitle() {
	    driver.findElement(By.name("rental_title")).sendKeys("OOGABOGA");
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	 
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".default-card > p:nth-child(6)"), "The rental charging field is required."));
	          wait.until(ExpectedConditions.textToBe(By.cssSelector(".conditionBox > p"), "The category field is required."));
	        driver.findElement(By.id("next")).click();
	          wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(4) > p:nth-child(9)"), "The image uploads field is required."));
	        driver.findElement(By.id("next")).click();
	          wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(3)"), "The street field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(6)"), "The city field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".listingCard:nth-child(5) > p:nth-child(8)"), "The state field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(10)"), "The country field is required."));
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("p:nth-child(12)"), "The postcode field is required."));
	    
	  }
	  
	  //create and then check if correct on posting then edit then check then edit with nothing submitted and see validation 
	  @Test
	  public void createAndDeleteRentable() {
	    driver.findElement(By.name("rental_title")).click();
	    driver.findElement(By.name("rental_title")).sendKeys("Rent Before");
	    driver.findElement(By.id("rental_charging")).click();
	    driver.findElement(By.id("rental_charging")).sendKeys("2");
	    driver.findElement(By.cssSelector("li:nth-child(1) > label:nth-child(2)")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.name("tags")).sendKeys("tag");
	    driver.findElement(By.cssSelector("textarea")).sendKeys("Description");
	    String imageName="mitten.jpg";
		WebElement Atatch = driver.findElement(By.id("image_uploads"));
		String imageLocation=workingDirectory+File.separator+imageName;
		Atatch.sendKeys(imageLocation);	 
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("street")).sendKeys("235 Yellowstone Drive");
	    driver.findElement(By.id("postcode")).sendKeys("22903");
	    driver.findElement(By.id("city")).sendKeys("Charlottesville");
	    driver.findElement(By.id("state")).sendKeys("VA");
	    driver.findElement(By.id("country")).sendKeys("USA");
	    driver.findElement(By.id("submit")).click();

	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".price"), "$2.00 / Hourly For Rent"));
	    }
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".details-outer > div:nth-child(1) > span"), "Fixed"));
	    }
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("div:nth-child(3) > span"), "New"));
	    }
	    driver.findElement(By.cssSelector(".details")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.linkText("Furniture"), "Furniture"));
	    }
	    driver.findElement(By.cssSelector(".details")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.linkText("tag"), "tag"));
	    }
	    driver.findElement(By.cssSelector(".changing-status > form:nth-child(2) > button")).click();
	    driver.findElement(By.id("product-right")).click();
	    driver.findElement(By.cssSelector("form:nth-child(3) > button")).click();
	   
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".product-status > p"), "Rented"));
	    }
	    driver.findElement(By.cssSelector(".changing-status > form:nth-child(2) > button")).click();

	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".product-status > p"), "Pending"));
	    }
	    driver.findElement(By.cssSelector(".fa-pencil")).click();
	driver.findElement(By.name("rental_title")).clear();
	    driver.findElement(By.name("rental_title")).sendKeys("Rent After");
	    driver.findElement(By.id("rental_charging")).clear();
	    driver.findElement(By.id("rental_charging")).sendKeys("2.46");
	    {
	      WebElement dropdown = driver.findElement(By.name("negotiable"));
	      dropdown.findElement(By.xpath("//option[. = 'Negotiable/ OBO (best offer)']")).click();
	    }
	  
	    driver.findElement(By.cssSelector("li:nth-child(1) > label:nth-child(2)")).click();
	    driver.findElement(By.cssSelector("li:nth-child(2) > label")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.name("tags")).click();
	    driver.findElement(By.name("tags")).sendKeys("tag modified");
	    driver.findElement(By.cssSelector("textarea")).click();
	    driver.findElement(By.cssSelector("textarea")).clear();
	    driver.findElement(By.cssSelector("textarea")).sendKeys("Description changed");
	    driver.findElement(By.cssSelector(".fa-circle-xmark")).click();
	     imageName="outputFile.png";
		 Atatch = driver.findElement(By.id("image_uploads"));
		 imageLocation=workingDirectory+File.separator+imageName;
		Atatch.sendKeys(imageLocation);	 
	    driver.findElement(By.id("next")).click();

	   driver.findElement(By.id("street")).clear();
	    driver.findElement(By.id("street")).sendKeys("200 Ednam Drive");
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.id("product-right")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".price"), "$2.46 / Hourly For Rent"));
	    }
	    driver.findElement(By.id("product-right")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector(".general-info > p:nth-child(3)"), "Description changed"));
	    }
	    driver.findElement(By.cssSelector(".details-outer > div:nth-child(1)")).click();
	 
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("div:nth-child(3) > span"), "New"));

	    driver.findElement(By.cssSelector(".details-outer > div:nth-child(3)")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.cssSelector("div:nth-child(3) > span"), "New"));
	    }
	    driver.findElement(By.cssSelector(".categories > div")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.linkText("Clothes"), "Clothes"));
	    }
	    driver.findElement(By.cssSelector(".tags > div")).click();
	    {
	      wait.until(ExpectedConditions.textToBe(By.linkText("tagtag modified"), "tagtag modified"));
	    }
	    driver.findElement(By.cssSelector("form:nth-child(1) > button:nth-child(3)")).click();
	    driver.findElement(By.name("rental_title")).click();
	    driver.findElement(By.id("rentalDuration")).click();
	    driver.findElement(By.id("rental_charging")).click();
	    driver.findElement(By.name("rental_title")).click();
	    driver.findElement(By.name("rental_title")).click();
	    driver.findElement(By.name("rental_title")).clear();
	    driver.findElement(By.id("rental_charging")).click();
	    driver.findElement(By.id("rental_charging")).click();
	    driver.findElement(By.id("rental_charging")).click();
	    driver.findElement(By.id("rental_charging")).clear();
	    driver.findElement(By.cssSelector("li:nth-child(2) > label")).click();
	    driver.findElement(By.id("rental_charging")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.name("tags")).click();
	    driver.findElement(By.name("tags")).clear();
	    driver.findElement(By.cssSelector(".listingCard:nth-child(5)")).click();
	    driver.findElement(By.cssSelector("textarea")).clear();
	    driver.findElement(By.cssSelector(".fa-circle-xmark")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("prev")).click();
	    driver.findElement(By.cssSelector(".listingCard > p:nth-child(3)")).click();
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listingCard > p:nth-child(3)")));
	    }
	    driver.findElement(By.cssSelector(".default-card")).click();
	    {
	      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".conditionBox > p")));
	    }
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("next")).click();
	    driver.findElement(By.id("submit")).click();
	     wait.until(ExpectedConditions.elementToBeClickable(By.id("delete-modal-trigger")));
	    driver.findElement(By.cssSelector("#delete-modal-trigger > i")).click();
	    driver.findElement(By.cssSelector(".deletebtn")).click();
	  }
	 
}
