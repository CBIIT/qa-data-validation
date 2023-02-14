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


//Step 2--------------------Verifying Resource Name ****************************************************************
webResrcName = WebUI.getText(findTestObject('Object Repository/CCDC/Resource_page/ResourceName'))
 
System.out.println ("This is the value of Resource Name obtained from UI :" + webResrcName)
System.out.println ("This is the value of Resource Name obtained from input test data :" + ipResrcName)
WebUI.verifyMatch(ipResrcName, webResrcName, false)
System.out.println ("Resource Name in the UI matches with the input data")


//Step 3--------------------Verifying Resource Code ****************************************************************
webResrcCode = WebUI.getText(findTestObject('Object Repository/CCDC/Resource_page/ResourceCode'))

System.out.println ("This is the value of Resource Code obtained from UI :" + webResrcCode)
System.out.println ("This is the value of Resource Code obtained from input test data :" + ipResrcCode)
WebUI.verifyMatch(webResrcCode, ipResrcCode, false)
System.out.println ("Resource Code in the UI matches with the input data")


/*
//Step 4--------------------Verifying Dataset Summaries Count ****************************************************************
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.findObj'('Object Repository/CCDC/Resource_page/DtstSummaryCnt')
//WebUI.scrollToElement(findTestObject('Object Repository/CCDC/Resource_page/DtstSummaryTitle'), 5)
//Thread.sleep(3000)
//System.out.println('Element in focus - from test script')

WebUI.scrollToElement(findTestObject('Object Repository/CCDC/Resource_page/DtstSummaryCnt'), 5)
initDtstSummCount = WebUI.getText(findTestObject('Object Repository/CCDC/Resource_page/DtstSummaryCnt'))
System.out.println('This is the value of Data Summary Count obtained from UI :' + initDtstSummCount)
String initCnt = initDtstSummCount.toString()
GlobalVariable.initSummCnt=initCnt
System.out.println('This is the value of Data Summary Count obtained from UI after conv to string :' + GlobalVariable.initSummCnt)
String webDtstSummCount = 'ctdc.utilities.runtestcaseforKatalon.getSummaryCnt'(GlobalVariable.initSummCnt)

System.out.println('This is the value of Data Summary Count obtained from input test data :' + ipDtstSummCnt)

WebUI.verifyMatch(webDtstSummCount, ipDtstSummCnt, false)

System.out.println('Dataset Summaries in the UI matches with the input data')

// findObj
*/

//Step 5--------------------Verifying Filter Type ****************************************************************
webFilterTyp = WebUI.getText(findTestObject('Object Repository/CCDC/Resource_page/FilterType'))
Thread.sleep(2000)
System.out.println ("This is the value of Filter Type obtained from UI :" + webFilterTyp)
System.out.println ("This is the value of Filter Type obtained from input test data :" + ipFilterTyp)
WebUI.verifyMatch(webFilterTyp, ipFilterTyp, false)
System.out.println ("Filter Type in the UI matches with the input data")

WebUI.closeBrowser()


