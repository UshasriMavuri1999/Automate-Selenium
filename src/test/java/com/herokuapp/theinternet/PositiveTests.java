package com.herokuapp.theinternet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
	
	@Test
	public void registrationTest()
	{
		System.out.println("starting registration test");
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		sleep(3000);
		
		//maximize browser window
		driver.manage().window().maximize();
		
		driver.navigate().to("https://accounts.lambdatest.com/register");
		System.out.println("page is opened");
		driver.findElement(By.name("name")).sendKeys("ushasri");
		sleep(1500);
		driver.findElement(By.name("email")).sendKeys("ushasrimavuri@gmail.com");
		sleep(1500);
		driver.findElement(By.name("password")).sendKeys("Usha*24");
		sleep(1500);
		driver.findElement(By.name("phone")).sendKeys("8765432134");
		sleep(1500);
		
		//check box
		driver.findElement(By.xpath("/html//form[@id='signup-form']//samp[@class='customcheckbox']")).click();
		sleep(1500);
		driver.findElement(By.xpath("//form[@id='signup-form']//button[@type='submit']")).click();
		sleep(1500);
		 /*String expectedURL = "https://accounts.lambdatest.com/email/verify";
		 String actualURL = driver.getCurrentUrl();
		 Assert.assertEquals(actualURL, expectedURL);
		 sleep(1500);
		 String expectedTitle = "Verify Your Email Address - LambdaTest";
		 String actualTitle = driver.getTitle();
		 Assert.assertEquals(actualTitle, expectedTitle);
		 sleep(1500);*/
		 driver.quit();
		
		
		
	}
	@Test
	public void loginTest() {
		
		System.out.println("starting login test");
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = 
//				new RemoteWebDriver(new URL("http://10.128.0.2:4444/wd/hub"),DesiredCapabilities.chrome());
		//sleep for 3 seconds
		sleep(3000);
		
		//maximize browser window
		driver.manage().window().maximize();
		
		String url = "https://the-internet.herokuapp.com/login";
		//open test page
		driver.get(url );
		System.out.println("page is opened");
		
		//sleep for 3 seconds
		sleep(2000);
		TakesScreenshot ts = (TakesScreenshot)driver;
	    File scr = ts.getScreenshotAs(OutputType.FILE);
	    try {
            FileUtils.copyFile(scr, new File("C:\\Users\\USHA\\screenshots\\positivetest.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
		
		//enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(1500);
		
		//enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(3000);
		
		//click login  button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(5000);
		
		//verifications
		
		//new url
		String expetedurl = "https://the-internet.herokuapp.com/secure";
//		String expetedurl = "https://the-internet.herokuapp.com/secure-broken"; error
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expetedurl,"Actual page url is not the same as expected");
		
		//logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "LogOut Button is not visible");
	;
	
		//successful login message
		//WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = successMessage.getText();
//		Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\n Actual Message :" +actualMessage+
				"\nExpected Message:" +expectedMessage);
		//close browser
		driver.quit();
		
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
