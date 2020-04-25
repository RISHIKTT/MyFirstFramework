package com.qa.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.qa.util.TestUtil;

public class LoginVerifyTest {
	
	private static final Logger LOGGER=Logger.getLogger(LoginVerifyTest.class.getName());
	
	@FindBy(xpath="//*[contains(text(),'Username')]/following-sibling::input")
	WebElement username;
	
	@FindBy(xpath="//*[contains(text(),'Password')]/following-sibling::input")
	WebElement password;
	
	@FindBy(xpath="//*[@value='Sign in']")
	WebElement signinBtn;
	
	@FindBy(xpath="")
	WebElement dashboard;

	public void loginToApplication(WebDriver driver, ExtentTest test){
		PageFactory.initElements(driver, this);
		try{
			String user=TestBase.prop.getProperty("username");
			String pass=TestBase.prop.getProperty("password");
			LOGGER.info("***********Enter username***********");
			TestUtil.enterValue(username, user, 20);
			
			LOGGER.info("***********Enter password***********");
			TestUtil.enterValue(username, pass, 5);
			
			LOGGER.info("***********Click on Signin Button***********");
			TestUtil.clickOnElement(driver, signinBtn, 10);
			
			if(!verifyHomePage(driver)){
				test.fail("Failed  ::  Login failed with Given credential");
				Assert.assertTrue(false);
			}
			
			LOGGER.info("***********Login successful with given credentials***********");
			test.pass("Login successful with given credentials");
			
		}catch(Exception e){
			test.fail("Failed  ::  Exception occured while Login with Given credential");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
			
	}
	
	public boolean verifyHomePage(WebDriver driver){
		try{
			LOGGER.info("***********Wait for Dashboard to be displayed***********");
			TestUtil.waitForXpathPresent(driver, dashboard, 20);
			
			LOGGER.info("***********Verify the Dashboard Link***********");
			if(!dashboard.isDisplayed()){
				LOGGER.info("***********Login failed with given credentials***********");
				return false;
			}
			return true;
		}catch(Exception e){
			LOGGER.info("Exception occured while logging in to the application");
			e.printStackTrace();
			return false;
		}
		
		
	}

}
