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

public class Chrome_ToDoList {
AppiumDriver<MobileElement> driver;
	
	WebDriverWait wait;
	@BeforeClass
	public void setUp() throws MalformedURLException {
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
	
	@Test
	public void launchUrl() throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			driver.navigate().to("https://www.training-support.net/selenium");
			
			
		} catch (Exception e) {
			
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")));
			driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")).click();
			//driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).flingForward().getChildByText(UiSelector().className(\"android.widget.TextView\"),\"To-Do List\")")).click();
			driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"To-Do List\"))"));
			Thread.sleep(4000);
			driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
			
			//Add the Three Task
			Thread.sleep(4000);
			driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id='taskInput']")).sendKeys("Add tasks to list");
			driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Add Task']")).click();
			driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id='taskInput']")).sendKeys("Get number of tasks");
			driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Add Task']")).click();
			driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id='taskInput']")).sendKeys("Clear the list");
			driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Add Task']")).click();
			
			//Get the First Task
			String text1 = driver.findElement(MobileBy.xpath("//android.view.View[@text='Add tasks to list']")).getText();
			driver.findElement(MobileBy.xpath("//android.view.View[@text='Add tasks to list']")).click();
			System.out.println(text1);
			
			//Get the Second Task
			String text2 = driver.findElement(MobileBy.xpath("//android.view.View[@text='Get number of tasks']")).getText();
			driver.findElement(MobileBy.xpath("//android.view.View[@text='Get number of tasks']")).click();
			System.out.println(text2);
			
			//Get the Third Task
			String text3 = driver.findElement(MobileBy.xpath("//android.view.View[@text='Clear the list']")).getText();
			driver.findElement(MobileBy.xpath("//android.view.View[@text='Clear the list']")).click();
			System.out.println(text3);
			
			//Verify all the Three Task
			Assert.assertEquals(text1, "Add tasks to list");
			Assert.assertEquals(text2, "Get number of tasks");
			Assert.assertEquals(text3, "Clear the list");
			
			Reporter.log("Verify the Task1 Created-"+text1);
			Reporter.log("Verify the Task2 Created-"+text2);
			Reporter.log("Verify the Task3 Created-"+text3);
			
			//Clear All Task
			driver.findElementByXPath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]").click();
			
		}
		
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
