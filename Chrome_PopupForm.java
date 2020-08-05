package project;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Chrome_PopupForm {
AppiumDriver<MobileElement> driver;
	
	WebDriverWait wait;
	@BeforeClass
	public void setUp() throws MalformedURLException, InterruptedException {
		DesiredCapabilities desiredcapailities=new DesiredCapabilities();
		desiredcapailities.setCapability("deviceId", "31003d412c4d2305");
		desiredcapailities.setCapability("deviceName", "Galaxy J7 (2016)");
		desiredcapailities.setCapability("platformName", "Android");
		desiredcapailities.setCapability("automationName", "UiAutomator2");
		desiredcapailities.setCapability("appPackage", "com.android.chrome");
		desiredcapailities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		desiredcapailities.setCapability("noReset", true);
		
		
		URL appserver=new URL("http://localhost:4723/wd/hub");
		driver=new AndroidDriver<MobileElement>(appserver,desiredcapailities);

		wait = new WebDriverWait(driver, 10);
		
	}
	
	@Test(dataProvider = "fetchdata")
	public void VerifyCredentials(String UserName,String password,String Confirmation) throws InterruptedException, IOException {
		
		try {
			driver.navigate().to("https://www.training-support.net/selenium");
			
			
		} catch (Exception e) {
			
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")));
			driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")).click();
			
			//Get into Popup Form
			driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Popups\"))"));
			//driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).flingForward().getChildByText(UiSelector().className(\"android.widget.TextView\"),\"Login Form\")")).click();
			driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Popups']")).click();
			
			Thread.sleep(2000);
			//Click SignIn
			driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Sign In']")).click();
			
			//Enter the Credentials
			Thread.sleep(4000);
			driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id='username']")).sendKeys(UserName);
			driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id='password']")).sendKeys(password);
			
			//Click Login Button
			driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Log in']")).click();
			Thread.sleep(2000);
			String text = driver.findElement(MobileBy.xpath("//android.view.View[@resource-id='action-confirmation']")).getText();
			System.out.println(text);
			Reporter.log("Verify Home Page Text-"+text);
			Assert.assertEquals(text, Confirmation);
			
					
		}
		
		
	}
	
	
	
	@DataProvider(name="fetchdata")
	public String[][] getData() {
		String[][] data=new String[2][3];
		data[0][0]="admin";
		data[0][1]="password";
		data[0][2]="Welcome Back, admin";
		
		data[1][0]="admin";
		data[1][1]="admin";
		data[1][2]="Invalid Credentials";
		
		return data;
		
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
