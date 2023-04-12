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
WebUI.openBrowser(GlobalVariable.G_Urlname)

WebUI.maximizeWindow()
//CustomKeywords.'ctdc.utilities.DataValidation.initDriver'()  use this when using datavalidation profile
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC01_Canine_Filter_Breed-Akita.xlsx')
Thread.sleep(2000)

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_PopUp_Continue_Btn'), 5)

WebUI.click(findTestObject('Object Repository/Canine/Canine_PopUp_Continue_Btn'))

System.out.println('Closed the popup window')

WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Programs_Btn'), 5)

WebUI.click(findTestObject('Canine/NavBar/Canine_Programs_Btn'))

System.out.println('This is the url of the current page : ' + WebUI.getUrl())

//	
//Step 1--------------------Verifying Program Header ****************************************************************
webHdrVal = WebUI.getText(findTestObject('Canine/ProgramsPage/PgmHeader', [('indexH') : ipHdrIndex]))

System.out.println('This is the value of Program Header from UI :' + webHdrVal)

System.out.println('This is the value of Program Header from excel :' + ipHdrVal)

WebUI.verifyMatch(ipHdrVal, webHdrVal, false)

System.out.println('Program Header in the UI matches with the input data')

// Step 2--------------------Verifying Program Description ****************************************************************
webDescVal = WebUI.getText(findTestObject('Canine/ProgramsPage/PgmDesc', [('indexD') : ipDescIndex]))

System.out.println('This is the value of Program Description from UI :' + webDescVal)

System.out.println('This is the value of Program Description from excel :' + ipDescVal)

WebUI.verifyMatch(ipDescVal, webDescVal.trim(), false)

System.out.println('Program Description in the UI matches with the input data')

// Step 3--------------------Verifying Study Count ****************************************************************
webStdCntVal = WebUI.getText(findTestObject('Canine/ProgramsPage/StudyCnt', [('indexC') : ipStdCntIndex]))

System.out.println('This is the value of Study Count from UI :' + webStdCntVal)

System.out.println('This is the value of Study Count from excel :' + ipStdCntVal)

WebUI.verifyMatch(ipStdCntVal, webStdCntVal, false)

System.out.println('Study Count in the UI matches with the input data')

WebUI.closeBrowser()



