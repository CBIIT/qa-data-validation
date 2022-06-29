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
import java.io.*
import java.util.Map as Map
import org.apache.poi.hssf.usermodel.HSSFWorkbook as HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.supercsv.io.CsvMapReader as CsvMapReader
import org.supercsv.io.ICsvMapReader as ICsvMapReader
import org.supercsv.prefs.CsvPreference as CsvPreference


WebUI.closeBrowser()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC03_GMB_Filter_Race-BlkAfricanAmr.xlsx')

WebUI.waitForElementClickable(findTestObject('Object Repository/GMB/NavBar/GMB_Subjects-Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/GMB/NavBar/GMB_Subjects-Btn')

//Clicking race drop-down
Thread.sleep(2000)
WebUI.waitForElementClickable(findTestObject('GMB/Filter/Race/Race_Ddn'),20)
Thread.sleep(2000)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('GMB/Filter/Race/Race_Ddn')

//Clicking BlkAfricanAmr checkbox
WebUI.waitForElementClickable(findTestObject('GMB/Filter/Race/BlkAfricanAmr_Chkbx'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('GMB/Filter/Race/BlkAfricanAmr_Chkbx')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('GMB/Filter/Race/Race_Ddn')


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readGMBStatBar'('Object Repository/GMB/StatBar/GMB_StatBar-Trials',
	'Object Repository/GMB/StatBar/GMB_StatBar-Subjects', 'Object Repository/GMB/StatBar/GMB_StatBar-Files')

//clicking the Subjects tab
WebUI.waitForElementPresent(findTestObject('Object Repository/GMB/GMBResults_Subjects_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/GMB/GMBResults_Subjects_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('GMB', GlobalVariable.G_GStatBar_Subjects, 'Object Repository/GMB/GMB_Subjects_Table',
	'Object Repository/GMB/GMB_Subjects_TableHdr', 'Object Repository/GMB/GMB_Subjects_TabNextBtn', GlobalVariable.G_GWebTabnameSubjects,
	GlobalVariable.G_GCypherTabnameSubjects, GlobalVariable.G_GQuerySubjectsTab)

//clicking the case Files tab
WebUI.waitForElementPresent(findTestObject('Object Repository/GMB/GMBResults_Files_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/GMB/GMBResults_Files_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('GMB', GlobalVariable.G_StatBar_ClinTrials, 'Object Repository/GMB/GMB_Files_Table',
	'Object Repository/GMB/GMB_Files_TableHdr', 'Object Repository/GMB/GMB_FilesTabNextBtn', GlobalVariable.G_WebTabnameFiles,
	GlobalVariable.G_CypherTabnameFiles, GlobalVariable.G_QueryFilesTab)

WebUI.closeBrowser()