package com.qa.testPackage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.qa.base.LoginVerifyTest;
import com.qa.base.TestBase;
import com.qa.pagePackage.EmpirixVoiceWatchUIPage;

public class EmpirixVoiceWatchUITest extends TestBase{
	
	private static final Logger LOGGER=Logger.getLogger(EmpirixVoiceWatchUITest.class.getName());
	LoginVerifyTest login=new LoginVerifyTest();
	EmpirixVoiceWatchUIPage EmpirixPage=new EmpirixVoiceWatchUIPage();
	
	
	@Test
	public void loginSuccessfulVerificationTest(){
		
		ExtentTest test = extent.createTest("loginTest :TC-001",
				"Verify User login Functionality on Application");
		LOGGER.info("******Verifying if Login is getting successful with valid credential******");
		login.loginToApplication(driver, test);
		
	}
	
	@Test
	public void switchLanguageVerificationTest(){
		
		ExtentTest test = extent.createTest("loginTest :TC-002",
				"Verify User is able to swtich to other Language in Application");
		LOGGER.info("******Verifying if Login is getting successful with valid credential******");
		login.loginToApplication(driver, test);
		
		LOGGER.info("***********Verify if User is able to switch to Japanese Language***********");
		if(!EmpirixPage.switchLanguagetoJapanese(driver, test)){
			test.fail("Failed  ::  Switch Language to Japanese Failed");
			Assert.assertTrue(false);
		}
		
		LOGGER.info("***********Verify if User is able to switch to English Language***********");
		if(!EmpirixPage.switchLanguagetoEnglish(driver, test)){
			test.fail("Failed  ::  Switch Language to English Failed");
			Assert.assertTrue(false);
		}
		
		test.fail("Failed  ::  Switch Language to both the option succssfully verified");
		Assert.assertTrue(true);
	}
	
	@Test
	public void allTabsAccessVerificationTest(){
		
		ExtentTest test = extent.createTest("loginTest :TC-003",
				"Verify User is able to access required tab in both Language");
		LOGGER.info("******Verifying if Login is getting successful with valid credential******");
		login.loginToApplication(driver, test);
		
		LOGGER.info("***********Verify if User is able to access all the tabs on both Language***********");
		if(EmpirixPage.requiredTabsAccessVerification(driver, test)){
			test.fail("Failed  ::  Tab access verification in both language succssfully verified");
			Assert.assertTrue(true);
		}else{
			test.fail("Failed  ::  Tab access verification Failed");
			Assert.assertTrue(false);
		}
		
	}
	
	@Test
	public void clientTabInfoVerificationTest(){
		
		ExtentTest test = extent.createTest("loginTest :TC-003",
				"Verify User is able to access client Tab and information is getting displayed");
		LOGGER.info("******Verifying if Login is getting successful with valid credential******");
		login.loginToApplication(driver, test);
		
		LOGGER.info("***********Verify if User is able to access client Tab***********");
		if(EmpirixPage.clientTabInfoVerificationPge(driver, test)){
			test.fail("Failed  ::  Client Tab and details under it succssfully verified");
			Assert.assertTrue(true);
		}else{
			test.fail("Failed  ::  CLient Tab verification Failed");
			Assert.assertTrue(false);
		}
		
	}

}
