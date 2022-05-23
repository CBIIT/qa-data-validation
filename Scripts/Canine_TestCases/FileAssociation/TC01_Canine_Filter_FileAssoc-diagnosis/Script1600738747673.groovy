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


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC01_Canine_Filter_FileAssoc-diagnosis.xlsx')


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Canine_PopUp_Continue_Btn')
System.out.println ("Closed the popup window");

WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_Cases_Btn')


WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/FileAssociation/FILEASSOCIATION_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/FileAssociation/FILEASSOCIATION_Ddn')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/FileAssociation/diagnosis_Chkbx')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarCanine'('Object Repository/Canine/StatBar/Canine_StatBar-Programs','Object Repository/Canine/StatBar/Canine_StatBar-Studies',
	'Object Repository/Canine/StatBar/Canine_StatBar-Cases', 'Object Repository/Canine/StatBar/Canine_StatBar-Samples',
	'Object Repository/Canine/StatBar/Canine_StatBar-CaseFiles', 'Object Repository/Canine/StatBar/Canine_StatBar-StudyFiles')

// clicking the Cases tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/CanineResults_Cases_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_Cases_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_Studies, 'Object Repository/Canine/Canine_CasesTable', 
    'Object Repository/Canine/Canine_TableHeader', 'Object Repository/Canine/Canine_CasesTabNextBtn', GlobalVariable.G_WebTabnameCases, 
    GlobalVariable.G_CypherTabnameCases, GlobalVariable.G_QueryCasesTab)
/*
//clicking the Samples tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/CanineResults_Samples_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/CanineResults_Samples_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_Samples, 'Object Repository/Canine/Canine_Samples_Table',
	'Object Repository/Canine/Canine_Samples_TableHdr', 'Object Repository/Canine/Canine_SamplesTabNextBtn', GlobalVariable.G_WebTabnameSamples,
	GlobalVariable.G_CypherTabnameSamples, GlobalVariable.G_QuerySamplesTab)
	
//clicking the case Files tab
WebUI.waitForElementPresent(findTestObject('Canine/CanineResults_CaseFiles_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_CaseFiles_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_Files, 'Object Repository/Canine/Canine_Files_Table',
	'Object Repository/Canine/Canine_Files_TableHdr', 'Object Repository/Canine/Canine_FilesTabNextBtn', GlobalVariable.G_WebTabnameFiles,
	GlobalVariable.G_CypherTabnameFiles, GlobalVariable.G_QueryFilesTab)

//clicking the study Files tab
WebUI.waitForElementPresent(findTestObject('Canine/CanineResults_StudyFiles_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_StudyFiles_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_StudyFiles, 'Object Repository/Canine/Canine_StudyFiles_Table',
	'Object Repository/Canine/Canine_StudyFiles_TableHdr', 'Object Repository/Canine/Canine_StudyFilesTabNextBtn', GlobalVariable.G_WebTabnameStudyFiles,
	GlobalVariable.G_CypherTabnameStudyFiles, GlobalVariable.G_QueryStudyFilesTab)
  */ 
WebUI.closeBrowser()
