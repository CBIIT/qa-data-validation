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
import org.openqa.selenium.firefox.FirefoxProfile as FirefoxProfile

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
/* Study- Glioma,
Breed - Beagle, Bulldog, Dalmatian
Sex - Female
*/
WebUI.closeBrowser()

//WebUI.openBrowser('')
//WebUI.maximizeWindow()
//CustomKeywords.'ctdc.utilities.CustomBrowserDriver.createWebDriver'()
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.testSetup'('HEADLESS_DRIVER')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC03_Canine_E2E_MultipleFilters-Study_Breed_Sex.xlsx')

WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_Cases_Btn')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/FilterByCases_Facet'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/FilterByCases_Facet')

WebUI.waitForElementPresent(findTestObject('Canine/Filter/Study/Canine_Filter_Study'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Study/Canine_Filter_Study')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Study/Canine_Filter_Study-GLIOMA_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/BREED_Ddn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Breed/BREED_Ddn')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/Beagle_Chkbx'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Breed/Beagle_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/BullDog_Chkbx'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Breed/BullDog_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/Dalmatian_Chkbx'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Breed/Dalmatian_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Sex/SEX_Ddn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Sex/SEX_Ddn')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Sex/Female_Chkbx')

//WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_CasesTable'), 5)
//WebUI.waitForElementPresent(findTestObject('Canine/Canine_SelectAll'), 5)
//WebUI.maximizeWindow()

not_run: WebUI.click(findTestObject('Object Repository/Canine/Canine_SelectAll'))

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'all')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/Canine_SaveToMycases')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_MyFilesCart')

WebUI.maximizeWindow()

//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'allM')
//WebUI.click(findTestObject('Object Repository/Canine/Canine_MyCasesFiles_SelectAll'))
//add wait  and println
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/Canine_DownloadManifest')

Thread.sleep(15000)

CustomKeywords.'ctdc.utilities.FileOperations.pickLatestFileFromDownloads'()

Thread.sleep(5000)

CustomKeywords.'ctdc.utilities.FileOperations.fileRename'()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readMyCartCount'('Object Repository/Canine/fileCentricCart/totalRecordCount')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.ReadCasesTableKatalon'(GlobalVariable.G_myCartTotal, 'Object Repository/Canine/fileCentricCart/myFiles_Tbl',
'Object Repository/Canine/fileCentricCart/myFiles_TblHdr', 'Object Repository/Canine/fileCentricCart/myFilesTable_Nxtbtn', GlobalVariable.G_WebTabNameMyCart)