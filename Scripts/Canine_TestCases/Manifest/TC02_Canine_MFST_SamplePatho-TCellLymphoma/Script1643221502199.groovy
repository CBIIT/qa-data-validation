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


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC02_Canine_MFST_SamplePatho-TCellLymphoma.xlsx')

WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Canine_PopUp_Continue_Btn')
System.out.println ("Closed the popup window");


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_Cases_Btn')
//WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/FilterBySamples_Facet'), 5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/FilterBySamples_Facet')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/SamplePathology/SAMPLEPATHOLOGY_Ddn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/SamplePathology/SAMPLEPATHOLOGY_Ddn')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/SamplePathology/TCellLymphoma_Chkbx')



CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarCanine'('Object Repository/Canine/StatBar/Canine_StatBar-Programs','Object Repository/Canine/StatBar/Canine_StatBar-Studies',
	'Object Repository/Canine/StatBar/Canine_StatBar-Cases', 'Object Repository/Canine/StatBar/Canine_StatBar-Samples',
	'Object Repository/Canine/StatBar/Canine_StatBar-CaseFiles', 'Object Repository/Canine/StatBar/Canine_StatBar-StudyFiles')


//clicking the Cases tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/CanineResults_Cases_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_Cases_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_Cases, 'Object Repository/Canine/Canine_CasesTable',
	'Object Repository/Canine/Canine_TableHeader', 'Object Repository/Canine/Canine_CasesTabNextBtn', GlobalVariable.G_WebTabnameParticipants,
	GlobalVariable.G_CypherTabnameParticipants, GlobalVariable.G_QueryParticipantsTab)


//OR----------------------
//option3: use 'Add associated files for all' button

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cases_page/Canine_AddAssocFilesForAll_Btn'), 5)  // same xpath for bento select all also, to rename -generic
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cases_page/Canine_AddAssocFilesForAll_Btn')
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cases_page/Canine_AddAll_Yes_Btn'), 5)   
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cases_page/Canine_AddAll_Yes_Btn')

//find and click the my cart button
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/fileCentricCart/fileCentricCart_Btn')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readMyCartCount'('Object Repository/Canine/Cart/Canine_MyFiles_Counter')

//##############################################################################
//COMPARISON 1 ------------------------------------------------------------------------------------ web cart vs DB data
// click on the columns icon
System.out.println("In the cart page")
//remove the checks fm the checkboxes - Access and Remove
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cart/Canine_ViewColumns_Btn'), 5)   
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cart/Canine_ViewColumns_Btn')
System.out.println("Clicked view cols button")
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cart/Canine_ShowHdCols_Access_Chkbx'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cart/Canine_ShowHdCols_Access_Chkbx')
System.out.println("Deselected Access checkbox")
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cart/Canine_ShowHdCols_Remove_Chkbx'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cart/Canine_ShowHdCols_Remove_Chkbx')
System.out.println("Deselected Remove checkbox")
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cart/Canine_ViewColumns_Close_Btn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cart/Canine_ViewColumns_Close_Btn')
System.out.println("Closed View Hide columns")


//collecting information from my cart table - to write in webdata and neo4j xls  // Compare web with db 
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readMyCartTable'('ICDC',GlobalVariable.G_myCartTotal, 'Object Repository/Canine/Cart/Canine_Cart_Table',
	'Object Repository/Canine/Cart/Canine_Cart_TableHdr', 'Object Repository/Canine/Cart/Canine_Cart_TableNextBtn', GlobalVariable.G_WebTabnameMyCart,
	GlobalVariable.G_CypherTabnameMyCart, GlobalVariable.G_cartQuery)
System.out.println("Completed writing web and db data of Cart table after removing Access and Remove col data - total 10 cols")


System.out.println ("***********************************completed Verification 1*******************************")

/*

//##############################################################################
//*COMPARISON 2 --------------------------------------------------------------------------- web cart vs manifest downloaded
 
//hardcoding to be removed and parametrized
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readSelectedCols'('Object Repository/Canine/Cart/Canine_Cart_TableBdy', 'Object Repository/Canine/Cart/Canine_Cart_TableHdr', GlobalVariable.G_WebMyCartSelectCols )


WebUI.waitForElementPresent(findTestObject('Canine/Cart/Canine_DownloadManifest'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cart/Canine_DownloadManifest')
Thread.sleep(2000) 

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Cart/Canine_DownloadManifest_Confirmation'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Cart/Canine_DownloadManifest_Confirmation')
Thread.sleep(2000)


CustomKeywords.'ctdc.utilities.FileOperations.assignMfstFilenames'()

//CustomKeywords.'ctdc.utilities.FileOperations.pickLatestFileFromDownloads'()
//System.out.println("Taking the latest file downloaded after converting it to csv format");
//
////Thread.sleep(5000)
//CustomKeywords.'ctdc.utilities.FileOperations.fileRename'()
//System.out.println("Renaming the latest file downloaded");

//Thread.sleep(3000)
//System.out.println("searching for the renamed csv manifest");   
//

//System.out.println("Thsi is the value stored in excelfilename global var : "+GlobalVariable.G_xlsxFileName)

System.out.println("This is the value stored of csvfilename from test case : "+GlobalVariable.G_WebTabnameMyCartsvFileName)
System.out.println("Thsi is the value stored of excelfilename from test case : "+GlobalVariable.G_excelFileName)

CustomKeywords.'ctdc.utilities.FileOperations.manifestFileOps'(GlobalVariable.G_WebTabnameMyCartsvFileName, GlobalVariable.G_excelFileName, GlobalVariable.G_xlsxFileName,  'ManifestSelectedCols', 'BackupManifestData')  

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.compareManifestLists'('MyCartSelectedCols', 'BackupManifestData')
*/


WebUI.closeBrowser()
