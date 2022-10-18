import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import org.openqa.selenium.firefox.FirefoxBinary as FirefoxBinary
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions as FirefoxOptions
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import java.nio.file.Path as Path
import java.nio.file.Paths as Paths

import java.io.File;

 

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.testGoogleLogin'()



//WebUI.waitForElementPresent(findTestObject('Object Repository/GMB/NavBar/GMB_Subjects-Btn'),5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/GMB/NavBar/GMB_Subjects-Btn')



CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC02_Bento_Filter_FileType-bai.xlsx')

System.out.println ("driver is created and the name is returned in previous step - inside runkatalon>>creatwebdriver from custombrowser>> drv returned to runtcfor katalon>>>>>>>>>>>>>>>>>>>>")

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Login_Btn')
System.out.println ("Clicked the LOGIN button")
Thread.sleep (3000)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Login'('Object Repository/Bento/Login/Login_SignInWGoogle_Btn','Object Repository/Bento/Login/Login_EmailID_Txtbx', 
	'Object Repository/Bento/Login/Login_EmailIDNextBtn','Object Repository/Bento/Login/Login_Passwd_Txtbx','Object Repository/Bento/Login/Login_PasswdNextBtn')

Thread.sleep(2000)


WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Cases-Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Cases-Btn')

WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/FileType/FILETYPE_Ddn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Cases_page/Filter/FileType/FILETYPE_Ddn')

WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/FileType/Bai_Chkbx'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Cases_page/Filter/FileType/Bai_Chkbx')


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarBento'('Object Repository/Bento/StatBar/Bento_StatBar-Programs',
	'Object Repository/Bento/StatBar/Bento_StatBar-Arms', 'Object Repository/Bento/StatBar/Bento_StatBar-Cases', 'Object Repository/Bento/StatBar/Bento_StatBar-Samples',
	'Object Repository/Bento/StatBar/Bento_StatBar-Assays', 'Object Repository/Bento/StatBar/Bento_StatBar-Files')





