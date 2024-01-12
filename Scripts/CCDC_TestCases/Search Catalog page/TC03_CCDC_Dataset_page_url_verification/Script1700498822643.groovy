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

import org.openqa.selenium.support.ui.WebDriverWait
import java.nio.file.Path as Path
import java.nio.file.Paths as Paths
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import org.testng.Assert;
WebUI.closeBrowser()

// public WebDriver driver
//Step 1--------------------Opening the desired url ****************************************************************
System.out.println('This is base url: ' + GlobalVariable.baseUrl)

System.out.println('This is the suffix url: ' + sUrl)

Url = (GlobalVariable.baseUrl + sUrl)

GlobalVariable.fullUrl = Url

System.out.println('This is the full url: ' + GlobalVariable.fullUrl)
//WebDriver drv = CustomKeywords.'ctdc.utilities.CustomBrowserDriver.createWebDriver'();
 
 
//System.out.println("This is urlname: "+GlobalVariable.fullUrl)
//driver.get(GlobalVariable.fullUrl)
//driver.manage().window().maximize()
//System.out.println("The window is maximized")


WebDriver drv = CustomKeywords.'ctdc.utilities.DataValidation.passDriver'()
String elementlabel


//Step 2--------------------Verifying Dataset Name ****************************************************************
System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
GlobalVariable.G_DtstName=ipDtstName.toString()
 elementlabel= "Dataset Name"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Dataset_page/DatasetName', ipDtstName, GlobalVariable.G_DtstName, elementlabel)



//Step 3--------------------Verifying URL ****************************************************************

CustomKeywords.'ctdc.utilities.DataValidation.FindingBrokenURLs'()


WebUI.closeBrowser()

 