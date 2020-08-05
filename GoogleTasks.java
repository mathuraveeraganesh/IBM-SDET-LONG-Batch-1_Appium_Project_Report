package project;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleTasks {
	AndroidDriver<MobileElement> driver;
	WebDriverWait wait;
	
	 @Test
	    public void task() throws InterruptedException, IOException {
	        // Set the Desired Capabilities
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("deviceId", "31003d412c4d2305");
	        caps.setCapability("deviceName", "Galaxy J7 (2016)");
	        caps.setCapability("platformName", "Android");
	        caps.setCapability("appPackage", "com.google.android.apps.tasks");
	        caps.setCapability("appActivity", ".ui.TaskListsActivity");

	        // Instantiate Appium Driver
	        AppiumDriver<MobileElement> driver = null;
	        try {
	            // Initialize driver
	            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	            wait = new WebDriverWait(driver, 10);
	            
	            System.out.println("Google Task is open");
	            
	            //Get Started
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@text='Get started']")));
	            driver.findElementByXPath("//android.widget.Button[@text='Get started']").click();
	            
	            //Add Complete Activity with Google Tasks
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tasks_fab")));
	            driver.findElementById("tasks_fab").click();
	            
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_task_title")));
	            driver.findElementById("add_task_title").sendKeys("Complete Activity with Google Tasks");
	            
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_task_done")));
	            driver.findElementById("add_task_done").click();
	            
	            String text1 = driver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView").getText();
	            System.out.println(text1);
	            
	            //Add Complete Activity with Google Keep
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tasks_fab")));
	            driver.findElementById("tasks_fab").click();
	            
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_task_title")));
	            driver.findElementById("add_task_title").sendKeys("Complete Activity with Google Keep");
	            
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_task_done")));
	            driver.findElementById("add_task_done").click();
	            Thread.sleep(2000);
	            String text2 = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc='Complete Activity with Google Keep']/android.widget.LinearLayout/android.widget.TextView").getText();
	            System.out.println(text2);

	            //Add Complete the second Activity Google Keep
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tasks_fab")));
	            driver.findElementById("tasks_fab").click();
	            
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_task_title")));
	            driver.findElementById("add_task_title").sendKeys("Complete the second Activity Google Keep");
	            
	            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_task_done")));
	            driver.findElementById("add_task_done").click();
	            Thread.sleep(2000);
	            String text3 = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc='Complete the second Activity Google Keep']/android.widget.LinearLayout/android.widget.TextView").getText();
	            System.out.println(text3);
	            
	            Assert.assertEquals(text1, "Complete Activity with Google Tasks");
	            Assert.assertEquals(text2, "Complete Activity with Google Keep");
	            Assert.assertEquals(text3, "Complete the second Activity Google Keep");
	            
	            Reporter.log("Verify the Google task "+text1+" Added Sucessfully");
	            Reporter.log("Verify the Google task "+text2+" Added Sucessfully");
	            Reporter.log("Verify the Google task "+text3+" Added Sucessfully");
	            
	            driver.quit();
	            
	        } catch (MalformedURLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

}
