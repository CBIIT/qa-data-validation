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

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC01_Canine_E2E_SelectAll-Add-To-Cart.xlsx')

WebUI.waitForElementClickable(findTestObject('Canine/NavBar/Canine_Cases_Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_Cases_Btn')

//WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Filter/FilterByCases_Facet'), 5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Filter/FilterByCases_Facet')


WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Diagnosis/DIAGNOSIS_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Diagnosis/DIAGNOSIS_Ddn')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/Filter/Diagnosis/BCellLymphoma_Chkbx')
Thread.sleep(5000) //only if a wait is added, this step passes in headless browsers

WebUI.waitForElementPresent(findTestObject('Canine/Filter/StageOfDisease/STAGEOFDISEASE_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/Filter/StageOfDisease/STAGEOFDISEASE_Ddn')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/Filter/StageOfDisease/4_Chkbx')
Thread.sleep(2000) //only if a wait is added, this step passes in headless browsers

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarCanine'('Object Repository/Canine/StatBar/Canine_StatBar-Studies','Object Repository/Canine/StatBar/Canine_StatBar-Cases',
	'Object Repository/Canine/StatBar/Canine_StatBar-Samples','Object Repository/Canine/StatBar/Canine_StatBar-Files',
   'Object Repository/Canine/StatBar/Canine_StatBar-Aliquots')

//clicking the Cases tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/CanineResults_Cases_Tab'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_Cases_Tab')

//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_Publications, 'Object Repository/Canine/Canine_CasesTable',
//   'Object Repository/Canine/Canine_TableHeader', 'Object Repository/Canine/Canine_CasesTabNextBtn', GlobalVariable.G_WebTabnameParticipants,
//   GlobalVariable.G_CypherTabnameParticipants, GlobalVariable.G_QueryParticipantsTab)
 
 

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cases_page/Canine_SelectAll'), 5)   
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cases_page/Canine_SelectAll')

//WebUI.maximizeWindow()
 //CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'all')
 
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Canine_AddAssocFiles_Btn')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Canine_MyFilesCart_Btn')
 
WebUI.maximizeWindow()
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/fileCentricCart/Canine_DownloadManifest_Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/fileCentricCart/Canine_DownloadManifest_Btn')
Thread.sleep(5000)

 
CustomKeywords.'ctdc.utilities.FileOperations.pickLatestFileFromDownloads'()
Thread.sleep(5000)
CustomKeywords.'ctdc.utilities.FileOperations.fileRename'()

CustomKeywords.'ctdc.utilities.FileOperations.generateXLSfromCSV'(GlobalVariable.G_ManifestTabName)  //specifying the sheet name for manifest xls+


//reading the filecentric cart table
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.ReadCasesTableKatalon'(GlobalVariable.G_myCartTotal, 'Object Repository/Canine/fileCentricCart/Canine_myFiles_Tbl',
'Object Repository/Canine/fileCentricCart/Canine_myFiles_TblHdr', 'Object Repository/Canine/fileCentricCart/Canine_myFilesTable_Nxtbtn', GlobalVariable.G_WebTabNameMyCart)
 

//copy data to second sheet in both webdata and manifest xl
CustomKeywords.'ctdc.utilities.FileOperations.copySheetXLS'(GlobalVariable.G_excelFileName,"newManifestData")  // copy sheet in manifest xl
CustomKeywords.'ctdc.utilities.FileOperations.copySheetXLSX'(GlobalVariable.G_WebExcel,"newCartData") // copy sheet in webdata excel

//delete unwanted cols in second sheet (of both manifest and webdata excels) before comparing
CustomKeywords.'ctdc.utilities.FileOperations.deleteCol'(GlobalVariable.G_excelFileName,"manifestData")
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readMyCartCount'('Object Repository/Canine/fileCentricCart/Canine_totalRecordCount')
CustomKeywords.'ctdc.utilities.FileOperations.deleteCol'(GlobalVariable.G_WebExcel,"CartWebData")


 
/*


WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_CasesTable'), 5)

WebUI.waitForElementPresent(findTestObject('Canine/Cases_page/Canine_SelectAll'), 5)

WebUI.maximizeWindow()

not_run: WebUI.click(findTestObject('Canine/Cases_page/Canine_SelectAll'))

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'all')

WebUI.click(findTestObject('Canine/Canine_SaveToMycases'))

WebUI.click(findTestObject('Canine/NavBar/Canine_MyFilesCart'))

//WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_GoToFiles'), 5)
//
//WebUI.click(findTestObject('Object Repository/Canine/Canine_GoToFiles'))

WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'allM')

//WebUI.click(findTestObject('Object Repository/Canine/Canine_MyCasesFiles_SelectAll'))
WebUI.click(findTestObject('Canine/Cart/Canine_DownloadManifest'))

*/

//test