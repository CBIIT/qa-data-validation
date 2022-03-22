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

import java.io.*;

import java.util.Map;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;  //uses the WorkbookFactory class to operate on SS model combining XssF for xlsx and hssf for xls format of the spreadsheet

import org.supercsv.io.CsvMapReader;

import org.supercsv.io.ICsvMapReader;

import org.supercsv.prefs.CsvPreference;

/*This test script:
 - Opens the browser of choice: Chrome, Firefox or Edge
 - Driver opened by Katalon is used in Selenium.
 - Takes the Query from input excel and fetches data from Neo4j database.
   Saves the results from neo4j and application in the same name mentioned in the input excel.
 - Clicks on the Cases button in the Navbar of ICDC's homepage.
 - Clicks on the Filter 'Breed' from left pane
 - Selects the specific check box from 'Breed' filter.
 - Reads the results displayed for the selected filter (from all the pages in UI) and saves in the excel mentioned in Input file
 - Reads Neo4j DB using the query from Input file and saves the data in the excel mentioned in Input file
 - Reads Neo4j excel and Webdata excel as lists and compares the data.
 */
WebUI.closeBrowser()

//WebUI.openBrowser('')
//WebUI.maximizeWindow()
//CustomKeywords.'ctdc.utilities.CustomBrowserDriver.createWebDriver'()
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC02_Bento_E2E_Select-Single-Add-To-Cart.xlsx')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Cases-Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Cases-Btn')

//WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Filter/FilterByCases_Facet'), 5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/FilterByCases_Facet')

WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/Diagnosis/DIAGNOSIS_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Cases_page/Filter/Diagnosis/DIAGNOSIS_Ddn')
//WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Filter/Diagnosis/TubularCarcinoma_Chkbx'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/Diagnosis/TubularCarcinoma_Chkbx')
Thread.sleep(5000) //only if a wait is added, this step passes in headless browsers

WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/TumorSize/TumorSize_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/TumorSize/TumorSize_Ddn')
//WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Filter/TumorSize/3_to_4_Chkbx'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/TumorSize/3_to_4_Chkbx')
Thread.sleep(2000) //only if a wait is added, this step passes in headless browsers
 
WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/ERStatus/ERStatus_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/ERStatus/ERStatus_Ddn')
//WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Filter/ERStatus/Negative_Chkbx'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/ERStatus/Positive_Chkbx')
Thread.sleep(2000) //only if a wait is added, this step passes in headless browsers

WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/PRStatus/PRStatus_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/PRStatus/PRStatus_Ddn')
//WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Filter/PRStatus/Positive_Chkbx'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/PRStatus/Positive_Chkbx')
Thread.sleep(2000) //only if a wait is added, this step passes in headless browsers
 


//clicking the Cases tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Bento/Cases_page/BentoResults_Cases_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/BentoResults_Cases_Tab')
WebUI.waitForElementPresent(findTestObject('Bento/Cases_page/Bento_CasesTable'), 5)
WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('BENTO-CASE-4123', 'one')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_AddSelectedFiles_Btn')
 
WebUI.waitForElementPresent(findTestObject('Object Repository/Bento/Cases_page/Bento_MyFilesCart_Btn'),5)	
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_MyFilesCart_Btn')
 
WebUI.maximizeWindow()

 
WebUI.waitForElementPresent(findTestObject('Bento/FileCentricCart_page/Bento_DownloadManifest_Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/FileCentricCart_page/Bento_DownloadManifest_Btn')
Thread.sleep(5000)
CustomKeywords.'ctdc.utilities.FileOperations.pickLatestFileFromDownloads'()

CustomKeywords.'ctdc.utilities.FileOperations.fileRename'()

CustomKeywords.'ctdc.utilities.FileOperations.generateXLSfromCSV'(GlobalVariable.G_ManifestTabName)  //specifying the sheet name for manifest xls+

// convert manifest xls to xlsx
CustomKeywords.'ctdc.utilities.FileOperations.xlsTOxlsx'()   //to convert manifest xls to xlsx



//reading the filecentric cart table
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readMyCartCount'('Object Repository/Canine/fileCentricCart/totalRecordCount') // This stores the temp value of cart count in global var
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.ReadCasesTableKatalon'(GlobalVariable.G_myCartTotal, 'Object Repository/Bento/FileCentricCart_page/SelectedFilesTable',
'Object Repository/Bento/FileCentricCart_page/SelectedFiles_TblHdr', 'Object Repository/Bento/FileCentricCart_page/SelectedFiles_Nextbtn', GlobalVariable.G_WebTabNameMyCart)


CustomKeywords.'ctdc.utilities.FileOperations.copySheetXLSX'(GlobalVariable.G_WebExcel,"newCartData") // copy sheet in webdata excel
//delete unwanted cols in second sheet (of both manifest and webdata excels) before comparing
CustomKeywords.'ctdc.utilities.FileOperations.deleteCol'(GlobalVariable.G_WebExcel,"CartWebData")

//copy data to second sheet in both webdata and manifest xl
CustomKeywords.'ctdc.utilities.FileOperations.copySheetXLSX'(GlobalVariable.G_xlsxFilename,"newManifestData")  // copy sheet in manifest xl
CustomKeywords.'ctdc.utilities.FileOperations.deleteCol'(GlobalVariable.G_xlsxFilename,"manifestData")

//CustomKeywords.'ctdc.utilities.FileOperations.deleteFiles'()

//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.validateStatBar'('Bento') // reads only if the file is .xlsx
//delete the csv and xls form of manifest from folder

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.compareLists'("newCartData", "newManifestData")

