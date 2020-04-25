package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.*;
import com.qa.testPackage.EmpirixVoiceWatchUITest;
import com.qa.util.WebEventListener;

public class TestBase {
	
	private static final Logger LOGGER=Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebDriverEventListener eventListener;
	protected static ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	
	public TestBase(){
		try {
			LOGGER.info("******Initializing properties File******");
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resource/com/qa/config/Config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void initialization(){
		String browserName = prop.getProperty("browser");
		
		LOGGER.info("******  Browser Used ::: "+browserName+"  ******");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/main/resource/chromedriver.exe");
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resource/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
	
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		LOGGER.info("******  Navigating to url ::: "+prop.getProperty("url")+"  ******");
		driver.get(prop.getProperty("url"));
		
	}
	
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void beforeSuiteExtentReportSetup(){
		htmlReporter = new ExtentHtmlReporter("ExtentHtmlReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
}
