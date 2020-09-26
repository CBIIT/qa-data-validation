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

/*This test script:

  - Opens the browser of choice: Chrome, Firefox or Edge
  - Driver opened by Katalon is used in Selenium.
  - Takes the Query from input excel and fetches data from Neo4j database.
	Saves the results from neo4j and application in the same name mentioned in the input excel.
  - Clicks on the Cases button in the Navbar of CTDC's homepage.
  - Clicks on the Filter 'Diagnosis' from left pane
  - Selects the specific check box from 'Diagnosis' filter.
  - Reads the results displayed for the selected filter (from all the pages in UI) and saves in the excel mentioned in Input file
  - Reads the stat bar - counts from UI
  - Reads Neo4j DB using the query from Input file and saves the data in the excel mentioned in Input file
  - Reads Neo4j excel and Webdata excel as lists and compares the data.
  - Compares the stat bar results read from UI, with that stored in the excel
  */
WebUI.closeBrowser()

WebUI.openBrowser('')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.browserDriver'('')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC25_Trials_Filter_Diagnosis-SquamousCell.xlsx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Trials/Trials_CASES_Btn'), 5)

WebUI.click(findTestObject('Object Repository/Trials/Trials_CASES_Btn'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Trials/Filter/Diagnosis/DIAGNOSIS_Ddn'), 5)

WebUI.click(findTestObject('Object Repository/Trials/Filter/Diagnosis/DIAGNOSIS_Ddn'))

WebUI.click(findTestObject('Trials/Filter/Diagnosis/SquamCellLung_Chkbx'))

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.ReadCasesTableKatalon'('Object Repository/Trials/Trials_CasesTable', 
    'Object Repository/Trials/Trials_TableHeader', 'Object Repository/Trials/Trials_NextBtn', GlobalVariable.G_WebTabnameCasesCases)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readTrialsStatBar'('Object Repository/Trials/Trials_StatBar-Trials', 
    'Object Repository/Trials/Trials_StatBar-Cases', 'Object Repository/Trials/Trials_StatBar-Files')

CustomKeywords.'ctdc.utilities.ReadExcel.Neo4j'()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.compareLists'(GlobalVariable.G_WebTabnameCasesCases, GlobalVariable.G_CypherTabnameCasesCases)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.validateTrialsStatBar'()


