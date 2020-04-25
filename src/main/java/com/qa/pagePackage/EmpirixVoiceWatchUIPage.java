package com.qa.pagePackage;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class EmpirixVoiceWatchUIPage {
	
	private static final Logger LOGGER=Logger.getLogger(EmpirixVoiceWatchUIPage.class.getName());
	
	@FindAll({@FindBy(xpath="//*[starts-with(@data-ng-click,'setSelectedLanguage')]")})
	List<WebElement> languageOptions;
	
	@FindBy(xpath="//*[contains(@data-ng-click,'en-US')]/parent::div")
	WebElement selectedLang;
	
	@FindBy(xpath="//*[contains(@data-ng-click,'en-US')]")
	WebElement selectEngLang;
	
	@FindBy(xpath="//*[contains(@data-ng-click,'ja-JP')]")
	WebElement selectJapaneseLang;
	
	@FindBy(xpath="//*[@data-i18n='_overallPerformance_']")
	WebElement overallPerfomanceTxt;
	
	@FindBy(xpath="//a[@data-i18n='_dashboard_']")
	WebElement dashboardTab;
	
	@FindBy(xpath="//a[@data-i18n='_alerts_']")
	WebElement alertsTab;
	
	@FindBy(xpath="//a[@data-i18n='_tests_']")
	WebElement testsTab;
	
	@FindBy(xpath="//a[@data-i18n='_variables_']")
	WebElement variablesTab;
	
	@FindBy(xpath="//a[@data-i18n='_notifications_']")
	WebElement notificationsTab;
	
	@FindBy(xpath="//*[@data-i18n='_client_']")
	WebElement clientTab;
	
	@FindBy(xpath="//*[@data-i18n='_clientDetails_']")
	WebElement clientDetailsWindow;
	
	
	public boolean languageOptionsVerification(WebDriver driver, ExtentTest test){
		PageFactory.initElements(driver, this);
		try{
			LOGGER.info("***********Click on current user dropdown***********");
			WebElement currentUserDropdown=driver.findElement(By.xpath("//a[contains(text(),'"+TestBase.prop.getProperty("username")+"')]"));
			TestUtil.clickOnElement(driver, currentUserDropdown, 20);
			
			LOGGER.info("***********Verify the Language Selection option***********");
			for(WebElement lang : languageOptions){
				if(!"English".equalsIgnoreCase(lang.getText())){
					if(!"Japanese".equalsIgnoreCase(lang.getText())){
						test.fail("English and Japanese as Language options doesn't exist");
						return false;
					}
				}
			}
			
			test.pass("English and Japanese as Language options exist");
			return true;
		}catch(Exception e){
			test.fail("Failed  ::  Exception occured while executing the Switch Language Testcase");
			e.printStackTrace();
			return false;
		}	
	}
	
	public boolean switchLanguageVerificationTest(WebDriver driver, ExtentTest test){
		PageFactory.initElements(driver, this);
		try{
			
			
			return true;
		}catch(Exception e){
			test.fail("Failed  ::  Exception occured while executing the Switch Language Testcase");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean switchLanguagetoEnglish(WebDriver driver, ExtentTest test){
		PageFactory.initElements(driver, this);
		try{
			LOGGER.info("***********Verify if English is already selected***********");
			if(selectedLang.getAttribute("ng-if").equals("selectedLanguage =='en-US'")){
				LOGGER.info("English Language is already selected");
				return true;
			}
			
			LOGGER.info("**********Select the English Language***********");
			TestUtil.clickOnElement(driver, selectEngLang, 20);
			
			LOGGER.info("**********Handle the popup by clicking on Ok***********");
			Alert alert=driver.switchTo().alert();
			alert.accept();
			
			LOGGER.info("**********Verify the text changed to English***********");
			if(!overallPerfomanceTxt.getText().equals("Overall Performance")){
				test.fail("Text verification after switching to English Failed");
				return false;
			}
			
			test.pass("Languge switch to English successfully verified");
			return true;
		}catch(Exception e){
			test.fail("Failed  ::  Exception occured while executing the Switch Language Testcase");
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean switchLanguagetoJapanese(WebDriver driver, ExtentTest test){
		PageFactory.initElements(driver, this);
		try{
			LOGGER.info("***********Verify if Japanese is already selected***********");
			if(selectedLang.getAttribute("ng-if").equals("selectedLanguage =='ja-JP'")){
				LOGGER.info("Japanese Language is already selected");
				return true;
			}
			
			LOGGER.info("**********Select the Japanese Language***********");
			TestUtil.clickOnElement(driver, selectJapaneseLang, 20);
			
			LOGGER.info("**********Handle the popup by clicking on Ok***********");
			Alert alert=driver.switchTo().alert();
			alert.accept();
			
			LOGGER.info("**********Verify the text changed to Japanese***********");
			if(!overallPerfomanceTxt.getText().equals("総合成績")){
				test.fail("Text verification after switching to Japanese Failed");
				return false;
			}
			
			test.pass("Languge switch to Japanese successfully verified");
			return true;
		}catch(Exception e){
			test.fail("Failed  ::  Exception occured while executing the Switch Language Testcase");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean requiredTabsAccessVerification(WebDriver driver, ExtentTest test){
		PageFactory.initElements(driver, this);
		try{
			LOGGER.info("**********Switch Language to japanese***********");
			switchLanguagetoJapanese(driver, test);
			
			if(dashboardTab.isEnabled() && alertsTab.isEnabled() && testsTab.isEnabled() && variablesTab.isEnabled() && notificationsTab.isEnabled()){
				test.pass("Tabs are successfully verified in Japanese Language");
			}else{
				test.fail("Tabs verification failed in Japanese Language");
				return false;
			}
			
			LOGGER.info("**********Switch Language to japanese***********");
			switchLanguagetoEnglish(driver, test);
			
			if(dashboardTab.isEnabled() && alertsTab.isEnabled() && testsTab.isEnabled() && variablesTab.isEnabled() && notificationsTab.isEnabled()){
				test.pass("Tabs are successfully verified in English Language");
			}else{
				test.pass("Tabs verification failed in English Language");
				return false;
			}
			
			return true;
		}catch(Exception e){
			test.fail("Failed  ::  Exception occured while executing the Tab Access Veriifcation test");
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean clientTabInfoVerificationPge(WebDriver driver, ExtentTest test){
		PageFactory.initElements(driver, this);
		try{
			LOGGER.info("***********Click on current user dropdown***********");
			WebElement currentUserDropdown=driver.findElement(By.xpath("//a[contains(text(),'"+TestBase.prop.getProperty("username")+"')]"));
			TestUtil.clickOnElement(driver, currentUserDropdown, 20);
			
			LOGGER.info("***********Click on client Tab***********");
			TestUtil.clickOnElement(driver, clientTab, 20);
			
			LOGGER.info("***********Verify if Client Details window opened***********");
			if(!clientDetailsWindow.isDisplayed()){
				test.fail("Client detail window doesn't opened successfully");
				return false;
			}
			test.pass("Client detail window verified successfully");
			return true;
		}catch(Exception e){
			test.fail("Failed  ::  Exception occured while executing the Client Tab Access Veriifcation test");
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	

}
