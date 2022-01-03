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

//This testcase contains verification of HOME page all links, log, text and buttons verifications
WebUI.closeBrowser()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC07_HomePage_AllLinks_Buttons_Verifications.xlsx')

WebUI.waitForElementPresent(findTestObject('Bento/Home_page/Bento_logo'), 5)

WebUI.verifyElementPresent(findTestObject('Bento/Home_page/Bento_logo'), 5)

WebUI.waitForElementClickable(findTestObject('Bento/Home_page/HomePage_ExploreTheSite_Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Home_page/HomePage_ExploreTheSite_Btn')

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Programs'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Arms'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Cases'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Samples'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Assays'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Files'), 2)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarBento'('Object Repository/Bento/StatBar/Bento_StatBar-Programs', 
    'Object Repository/Bento/StatBar/Bento_StatBar-Arms', 'Object Repository/Bento/StatBar/Bento_StatBar-Cases', 'Object Repository/Bento/StatBar/Bento_StatBar-Samples', 
    'Object Repository/Bento/StatBar/Bento_StatBar-Assays', 'Object Repository/Bento/StatBar/Bento_StatBar-Files')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Home-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Home-Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Home_page/Programs_View_Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Home_page/Programs_View_Btn')

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Programs'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Arms'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Cases'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Samples'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Assays'), 2)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Files'), 2)

//Commented out because this function is not applicable to home page as we don't show study in home page.
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarBento'('Bento/Home_page/HomePage_StatBar-Programs',
	'Bento/Home_page/HomePage_StatBar-Arms', 'Bento/Home_page/HomePage_StatBar-Cases', 'Bento/Home_page/HomePage_StatBar-Samples',
	, 'Bento/Home_page/HomePage_StatBar-Files')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarBento'('Object Repository/Bento/StatBar/Bento_StatBar-Programs', 
    'Object Repository/Bento/StatBar/Bento_StatBar-Arms', 'Object Repository/Bento/StatBar/Bento_StatBar-Cases', 'Object Repository/Bento/StatBar/Bento_StatBar-Samples', 
    'Object Repository/Bento/StatBar/Bento_StatBar-Assays', 'Object Repository/Bento/StatBar/Bento_StatBar-Files')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Home-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Home-Btn')

WebUI.waitForElementClickable(findTestObject('Bento/Home_page/Resources_ReadMore_Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Home_page/Resources_ReadMore_Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Home_page/AboutPage_Resources_title'), 5)

WebUI.verifyElementText(findTestObject('Bento/Home_page/AboutPage_Resources_title'), 'Resources')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Home-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Home-Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Home_page/AboutBento_ReadMore_Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Home_page/AboutBento_ReadMore_Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Home_page/AboutBento_Title'), 5)

WebUI.verifyElementText(findTestObject('Bento/Home_page/AboutBento_Title'), 'Bento')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Home-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Home-Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Home_page/Explore_Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Home_page/Explore_Btn')

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Programs'), 5)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Arms'), 5)

WebUI.verifyElementPresent(findTestObject('Object Repository/Bento/StatBar/Bento_StatBar-Cases'), 5)

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Home-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Home-Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Footer/FNLCR_Logo'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/FNLCR_Logo')

WebUI.switchToWindowIndex(1)

WebUI.verifyElementPresent(findTestObject('Bento/Footer/Externel_Apps/FNLCR_HomePage_Logo'), 2)

WebUI.closeWindowIndex(1)

WebUI.switchToWindowIndex(0)

WebUI.waitForElementPresent(findTestObject('Bento/Footer/Purpose_Hplink'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/Purpose_Hplink')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Home-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Home-Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Footer/Resources_Hplink'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/Resources_Hplink')

WebUI.verifyElementText(findTestObject('Bento/Home_page/AboutPage_Resources_title'), 'Resources')
WebUI.delay(2)
WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Home-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Home-Btn')

WebUI.waitForElementPresent(findTestObject('Bento/Footer/Documentation_Hplink'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/Documentation_Hplink')

WebUI.switchToWindowIndex(1)
WebUI.waitForElementPresent(findTestObject('Bento/Home_page/Bento_logo'), 5)
WebUI.verifyElementPresent(findTestObject('Bento/Home_page/Bento_logo'), 5)

WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

WebUI.waitForElementPresent(findTestObject('Bento/Footer/HHS_Hplink'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/HHS_Hplink')

WebUI.switchToWindowIndex(1)
WebUI.waitForElementPresent(findTestObject('Bento/Footer/Externel_Apps/HHS_logo'), 5)
WebUI.verifyElementPresent(findTestObject('Bento/Footer/Externel_Apps/HHS_logo'), 5)
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

WebUI.waitForElementPresent(findTestObject('Bento/Footer/NIH_Hplink'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/NIH_Hplink')

WebUI.switchToWindowIndex(1)
WebUI.waitForElementPresent(findTestObject('Bento/Footer/Externel_Apps/NIH_logo'), 5)
WebUI.verifyElementPresent(findTestObject('Bento/Footer/Externel_Apps/NIH_logo'), 5)
WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

WebUI.waitForElementPresent(findTestObject('Bento/Footer/NCI_Hplink'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/NCI_Hplink')

WebUI.switchToWindowIndex(1)
WebUI.waitForElementPresent(findTestObject('Bento/Footer/Externel_Apps/NCI_logo'), 5)
WebUI.verifyElementPresent(findTestObject('Bento/Footer/Externel_Apps/NCI_logo'), 5)

WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)

WebUI.waitForElementPresent(findTestObject('Bento/Footer/USA_Hplink'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Footer/USA_Hplink')

WebUI.switchToWindowIndex(1)
WebUI.waitForElementPresent(findTestObject('Bento/Footer/Externel_Apps/USA_logo'), 5)
WebUI.verifyElementPresent(findTestObject('Bento/Footer/Externel_Apps/USA_logo'), 5)

WebUI.closeWindowIndex(1)
WebUI.switchToWindowIndex(0)
WebUI.delay(3)

