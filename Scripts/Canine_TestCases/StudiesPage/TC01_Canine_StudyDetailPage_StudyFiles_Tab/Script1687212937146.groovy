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
/*
 * WebUI.openBrowser(GlobalVariable.G_Urlname)
 

WebUI.maximizeWindow()
//CustomKeywords.'ctdc.utilities.DataValidation.initDriver'()  use this when using datavalidation profile
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC01_Canine_Filter_Breed-Akita.xlsx')
Thread.sleep(2000)

*/

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC01_Canine_StudiesTab-StudiesTable.xlsx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_PopUp_Continue_Btn'), 5)

WebUI.click(findTestObject('Object Repository/Canine/Canine_PopUp_Continue_Btn'))

System.out.println('Closed the popup window')

WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Studies_Btn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_Studies_Btn')

System.out.println('This is the url of the current page : ' + WebUI.getUrl())

//Read Statbar
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarCanine'('Object Repository/Canine/StatBar/Canine_StatBar-Programs','Object Repository/Canine/StatBar/Canine_StatBar-Studies',
	'Object Repository/Canine/StatBar/Canine_StatBar-Cases', 'Object Repository/Canine/StatBar/Canine_StatBar-Samples',
	'Object Repository/Canine/StatBar/Canine_StatBar-CaseFiles', 'Object Repository/Canine/StatBar/Canine_StatBar-StudyFiles')
findTestObject('Object Repository/Canine/StudiesPage/NCATS_Hplink')
findTestObject('Object Repository/Canine/StudyDetailsPage/StudyFilesTab')
//reading the table inside the study files tab of a particular study details page :

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/StudiesPage/NCATS_Hplink'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/StudiesPage/NCATS_Hplink')
System.out.println('This is the url of the current page after clicking a particular study link : ' + WebUI.getUrl())
Thread.sleep(2000)
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/StudyDetailsPage/StudyFilesTab'), 5) 

boolean studyfilestab = WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/StudyDetailsPage/StudyFilesTab'),
				5, FailureHandling.OPTIONAL)
			System.out.println('This is the value of element presence for studyfiles tab : ' + studyfilestab)
			System.out.println('This is the value of the label in the button : ' + (findTestObject('Object Repository/Canine/StudyDetailsPage/StudyFilesTab')).getText())
			
			if (studyfilestab == true) {




CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineStudyDetailsPage/StudyFilesTab')
System.out.println('This is the url of the current page after clicking a particular study link : ' + WebUI.getUrl())
 Thread.sleep(2000)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_StudyFiles, 'Object Repository/Canine/Canine_StudyFiles_Table',
	'Object Repository/Canine/Canine_StudyFiles_TableHdr', 'Object Repository/Canine/Canine_StudyFilesTabNextBtn', GlobalVariable.G_WebTabnameStudyFiles,
	GlobalVariable.G_CypherTabnameStudyFiles, GlobalVariable.G_QueryStudyFilesTab)
			}
WebUI.closeBrowser()
