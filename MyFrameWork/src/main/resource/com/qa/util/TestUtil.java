package com.qa.util;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	private static final Logger LOGGER=Logger.getLogger(TestUtil.class.getName());

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void sleep(long number) {
		try{
			Thread.sleep(number);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void waitForXpathPresent(WebDriver driver,WebElement element, int seconds) {
		try {
			WebDriverWait wait=new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(TimeoutException e){
			LOGGER.info("Exception ::  Got Timeout Exception - hence check the app behaviour again ## xpath :: "+element);
			e.printStackTrace();
		}catch(Exception e){
			LOGGER.info("Exception ::  Got unhandled Exception - hence check the app behaviour again ## xpath :: "+element);
			e.printStackTrace();
		}
	}
	
	public static void waitForElementClickable(WebDriver driver,WebElement element, int seconds) {
		try {
			WebDriverWait wait=new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			
		}catch(TimeoutException e){
			LOGGER.info("Exception ::  Got Timeout Exception - hence check the app behaviour again ## xpath :: "+element);
			e.printStackTrace();
		}catch(Exception e){
			LOGGER.info("Exception ::  Got unhandled Exception - hence check the app behaviour again ## xpath :: "+element);
			e.printStackTrace();
		}
	}
	
	public static void clickOnElement(WebDriver driver, WebElement element, int seconds){
		
			waitForXpathPresent(driver, element, seconds);
			sleep(3000l);
			try{
				LOGGER.info("Clicking on Element with xpath  ::  "+element);
				element.click();
				
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Trying to click with JavaScriptExecuter");
				js_executor_click(driver, element);
				
			}
	}
	
	public static void js_executor_click(WebDriver driver, WebElement element){
		try{
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		}catch(Exception e){
			LOGGER.info("Exception ::  Got unhandled Exception - Exception occured while click on Element :: "+element);
			e.printStackTrace();
		}
	}
	
	public static void enterValue(WebElement element, String value, int seconds) throws InterruptedException {
		try {
			LOGGER.info("********Enter Value: " + value + "*********");
			waitForXpathPresent(driver, element, seconds);
			element.clear();
			sleep(1000L);
			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				String s = new StringBuilder().append(c).toString();
				element.sendKeys(s);
				sleep(300l);
			}
			sleep(3000l);
			element.sendKeys(Keys.ARROW_DOWN.ENTER);
			sleep(2000l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.info("------------------Exception Block---------------------");
			e.printStackTrace();
		}
	}
}
