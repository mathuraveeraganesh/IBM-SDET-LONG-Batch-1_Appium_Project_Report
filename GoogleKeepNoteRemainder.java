package project;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleKeepNoteRemainder {
	
	AppiumDriver<MobileElement> driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "31003d412c4d2305");
        caps.setCapability("deviceName", "Galaxy J7 (2016)");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity", ".activities.BrowseActivity");
        
        
     // Initialize driver
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
       
        
        System.out.println("Google Keep is open");
	}
	
	@Test
	public void GoogleKeepNotes() throws InterruptedException{
		 wait = new WebDriverWait(driver, 10);
		 
		//New Text Note
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("New text note")));
		driver.findElementByAccessibilityId("New text note").click();
		Thread.sleep(2000);
		
		//Add Title and Description
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("editable_title")));
		driver.findElement(MobileBy.id("editable_title")).sendKeys("Test");
		driver.findElement(MobileBy.id("edit_note_text")).sendKeys("Test Description");
		driver.navigate().back();
		
		//Add Reminder
		Thread.sleep(2000);
		driver.findElement(MobileBy.AccessibilityId("Single-column view")).click();
		driver.findElementById("save").click();
		
		Thread.sleep(2000);
		driver.navigate().back();
		
		//verify Title,Description and Reminder
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("index_note_title")));
		String Title = driver.findElementById("index_note_title").getText();
		System.out.println("Title-"+Title);
		String TitleDescription = driver.findElementById("index_note_text_description").getText();
		System.out.println("Title Description-"+TitleDescription);
		String Reminder = driver.findElementById("reminder_chip_text").getText();
		System.out.println("Reminder-"+Reminder);
		
		Assert.assertEquals(Title, "Test");
		Assert.assertEquals(TitleDescription, "Test Description");
		Assert.assertEquals(Reminder, "Today, 3:00 pm");
		
		Reporter.log("Verify the Title-'"+Title+"' Added To Google Keep Note Reminder");
		Reporter.log("Verify the Title Description-'"+TitleDescription+"' Added To Google Keep Note Reminder");
		Reporter.log("Verify the Reminder-'"+Reminder+"' Added To Google Keep Note Reminder");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	

}
