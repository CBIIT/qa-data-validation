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
 - Clicks on the Cases button in the Navbar of ICDC's homepage.
 - Clicks on the Filter 'Breed' from left pane
 - Selects the specific check box from 'Breed' filter.
 - Reads the results displayed for the selected filter (from all the pages in UI) and saves in the excel mentioned in Input file
 - Reads Neo4j DB using the query from Input file and saves the data in the excel mentioned in Input file
 - Reads Neo4j excel and Webdata excel as lists and compares the data.
 */
WebUI.closeBrowser()
 
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC08_CDS_Filter_PHSAccession-phs002305.xlsx')
 
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/CDS/Popup_Continue_Btn')
System.out.println ("Closed the popup window");

//Clicking data tab
WebUI.waitForElementPresent(findTestObject('Object Repository/CDS/NavBar/CDS_Data-Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCDSStat'('Object Repository/CDS/NavBar/CDS_Data-Btn')

//Clicking the PHS Accession dropdown
WebUI.waitForElementPresent(findTestObject('Object Repository/CDS/Data_page/Filter/PHS_Accession/PHS_Accession_Ddn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCDSStat'('Object Repository/CDS/Data_page/Filter/PHS_Accession/PHS_Accession_Ddn')
 

//Clicking the desired PHS Accession checkbox
WebUI.waitForElementPresent(findTestObject('Object Repository/CDS/Data_page/Filter/PHS_Accession/phs002305_Chkbx'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCDSStat'('Object Repository/CDS/Data_page/Filter/PHS_Accession/phs002305_Chkbx')
 
//Read statbar
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarCDS'('Object Repository/CDS/StatBar/CDS_StatBar-Studies',
 'Object Repository/CDS/StatBar/CDS_StatBar-Participants','Object Repository/CDS/StatBar/CDS_StatBar-Samples', 'Object Repository/CDS/StatBar/CDS_StatBar-Files')

 
//Clicking participants tab
WebUI.waitForElementPresent(findTestObject('CDS/Data_page/CDSResults_Participants_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/CDS/Data_page/CDSResults_Participants_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('CDS',GlobalVariable.G_StatBar_Participants, 'Object Repository/CDS/Data_page/CDS_ParticipantsTable',
	'Object Repository/CDS/Data_page/CDS_ParticipantsTableHeader', 'Object Repository/CDS/Data_page/CDS_ParticipantsTabNextBtn', GlobalVariable.G_WebTabnameParticipants,	GlobalVariable.G_CypherTabnameParticipants, GlobalVariable.G_QueryParticipantsTab)

//clicking Samples tab
WebUI.waitForElementPresent(findTestObject('Object Repository/CDS/Data_page/CDSResults_Samples_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/CDS/Data_page/CDSResults_Samples_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('CDS',GlobalVariable.G_StatBar_Samples, 'Object Repository/CDS/Data_page/CDS_SamplesTable',
	'Object Repository/CDS/Data_page/CDS_SamplesTableHeader', 'Object Repository/CDS/Data_page/CDS_SamplesTabNextBtn', GlobalVariable.G_WebTabnameSamples,
	GlobalVariable.G_CypherTabnameSamples, GlobalVariable.G_QuerySamplesTab)

//clicking Files tab
WebUI.waitForElementPresent(findTestObject('Object Repository/CDS/Data_page/CDSResults_Files_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/CDS/Data_page/CDSResults_Files_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('CDS',GlobalVariable.G_StatBar_Files, 'Object Repository/CDS/Data_page/CDS_FilesTable',
	'Object Repository/CDS/Data_page/CDS_FilesTableHeader', 'Object Repository/CDS/Data_page/CDS_FilesTabNextBtn', GlobalVariable.G_WebTabnameFiles,
	GlobalVariable.G_CypherTabnameFiles, GlobalVariable.G_QueryFilesTab)
 
WebUI.closeBrowser()