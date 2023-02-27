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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW

WebUI.closeBrowser()

//Step 1--------------------Opening the desired url ****************************************************************
System.out.println('This is base url: ' + GlobalVariable.baseUrl)

System.out.println('This is the suffix url: ' + sUrl)

Url = (GlobalVariable.baseUrl + sUrl)

GlobalVariable.fullUrl = Url

System.out.println('This is the full url: ' + GlobalVariable.fullUrl)

CustomKeywords.'ctdc.utilities.DataValidation.initDriver'()


//Step 2--------------------Verifying Dataset Name ****************************************************************
webDtstName = WebUI.getText(findTestObject('Object Repository/CCDC/Resource_page/ResourceName'))
 
System.out.println ("This is the value of Dataset Name obtained from UI :" + webDtstName)
System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
WebUI.verifyMatch(ipDtstName, webDtstName, false)
System.out.println ("Dataset Name in the UI matches with the input data")

/*
//Step 3--------------------Verifying Num of Cases  need ID ****************************************************************
webCasesCnt = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/ResrcCode'))

System.out.println ("This is the value of Resource Code obtained from UI :" + webResrcCode)
System.out.println ("This is the value of Resource Code obtained from input test data :" + ipResrcCode)
WebUI.verifyMatch(webResrcCode, ipResrcCode, false)
System.out.println ("Resource Code in the UI matches with the input data")
*/
//Step 4--------------------Verifying Case Sex ****************************************************************
webCaseSex = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseSex'))

System.out.println ("This is the value of Case Sex obtained from UI :" + webCaseSex)
System.out.println ("This is the value of Case Sex obtained from input test data :" + ipCaseSex)
WebUI.verifyMatch(webCaseSex, ipCaseSex, false)
System.out.println ("Case Sex in the UI matches with the input data")

//Step 5--------------------Verifying Case Age ****************************************************************
webCaseAge = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseAge'))

System.out.println ("This is the value of Case Age obtained from UI :" + webCaseAge)
System.out.println ("This is the value of Case Age obtained from input test data :" + ipCaseAge)
WebUI.verifyMatch(webCaseAge.toString(), ipCaseAge.toString(), false)
System.out.println ("Case Age in the UI matches with the input data")

//Step 6--------------------Verifying Case Race ****************************************************************
webCaseRace = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseRace'))

System.out.println ("This is the value of Case Race obtained from UI :" + webCaseRace)
System.out.println ("This is the value of Case Race obtained from input test data :" + ipCaseRace)
WebUI.verifyMatch(webCaseRace, ipCaseRace, false)
System.out.println ("Case Race in the UI matches with the input data")

//Step 7--------------------Verifying Case Ethnicity ****************************************************************
webCaseEthn = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseEthn'))

System.out.println ("This is the value of Case Ethnicity obtained from UI :" + webCaseEthn)
System.out.println ("This is the value of Case Ethnicity obtained from input test data :" + ipCaseEthn)
WebUI.verifyMatch(webCaseEthn, ipCaseEthn, false)
System.out.println ("Case Ethnicity in the UI matches with the input data")

//Step 8--------------------Verifying dbgap ID ****************************************************************
webDbgapID = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/dbgapID'))

System.out.println ("This is the value of dbgap ID obtained from UI :" + webDbgapID)
System.out.println ("This is the value of dbgap ID obtained from input test data :" + ipdbgapID)
WebUI.verifyMatch(webDbgapID, ipdbgapID, false)
System.out.println ("dbgapID in the UI matches with the input data")

//Step 9--------------------Verifying Grant ****************************************************************
webGrant = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/Grant'))

System.out.println ("This is the value of Grant obtained from UI :" + webGrant)
System.out.println ("This is the value of Grant obtained from input test data :" + ipGrant)
WebUI.verifyMatch(webGrant, ipGrant, false)
System.out.println ("Grant in the UI matches with the input data")

WebUI.closeBrowser()

 