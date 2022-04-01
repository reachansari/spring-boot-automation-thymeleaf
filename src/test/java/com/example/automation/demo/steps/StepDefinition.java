package com.example.automation.demo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {

	WebDriver driver = null;

	@Given("user navigates to home page")
	public void user_navigates_to_homePage() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://localhost:8080/");
	}

	@When("i click on the show cars link")
	public void i_click_on_the_link() throws InterruptedException {
		Thread.sleep(5000);
		new WebDriverWait(driver, 10);
		captureScreen();
		driver.findElement(By.linkText("Show cars")).click();
		
	}

	@Then("home page should return some results")
	public void homePage_should_return_some_results() throws InterruptedException {
		new WebDriverWait(driver, 10);
		try {
			Thread.sleep(5000);
			captureScreen();
			String title = driver.findElement(By.xpath(("/html/body/h2"))).getText();
			String id = driver.findElement(By.xpath(("html/body/table/tbody/tr[1]/th[1]"))).getText();
			String name = driver.findElement(By.xpath(("html/body/table/tbody/tr[1]/th[2]"))).getText();
			String price = driver.findElement(By.xpath(("html/body/table/tbody/tr[1]/th[3]"))).getText();
			assertEquals("List of cars", title);
			assertEquals("Id", id);
			assertEquals("Name", name);
			assertEquals("Price", price);

		} finally {
			driver.close();
			driver.quit();
		}
	}
	
	public String captureScreen() {
	    String path;
	    try {
	        WebDriver augmentedDriver = new Augmenter().augment(driver);
	        File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	        path = "./target/screenshots/" + source.getName();
	        FileUtils.copyFile(source, new File(path)); 
	    }
	    catch(IOException e) {
	        path = "Failed to capture screenshot: " + e.getMessage();
	    }
	    return path;
	}

	public String GetTimeStampValue()throws IOException{
	    Calendar cal = Calendar.getInstance();
	    Date time = cal.getTime();
	    String timestamp = time.toString();
	    System.out.println(timestamp);
	    String systime = timestamp.replace(":", "-");
	    System.out.println(systime);
	    return systime;
	}

}
