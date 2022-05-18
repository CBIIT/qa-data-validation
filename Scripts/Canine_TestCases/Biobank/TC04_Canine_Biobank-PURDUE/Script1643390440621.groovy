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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys


'Opens the browser of choice: Chrome, Firefox or Edge'
WebUI.closeBrowser()

'Driver opened by Katalon is used in Selenium from this step.'

//MGT-Australian Cattle Dog, Mixed breed -MammaryCancer,HealthyControl-Mammary Gland   //updated for MGT
 
'This step takes the Query from input excel and fetches data from Neo4j database. \r\nSaves the results from neo4j and application in the same name mentioned in the input excel. '
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC04_Canine_Biobank-PURDUE.xlsx')   //changed for MGT

'Clicks on the Cases button in the Navbar of ICDC\'s homepage.'
WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 20)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Canine_PopUp_Continue_Btn')
System.out.println ("Closed the popup window");
WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 20)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_Cases_Btn')

//WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/FilterByCases_Facet'), 20)
//WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/FilterByCases_Facet'), 5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/FilterByCases_Facet')
findTestObject('Object Repository/Canine/Filter/Biobank/Biobank_Ddn')

'Clicks on the Filter \'BIobank\' from left pane'
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Biobank/Biobank_Ddn'), 30)

WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/Biobank/Biobank_Ddn'), 5)
 
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Biobank/Biobank_Ddn')
 
'Selects the specific check box from \'Biobank\' filter.'
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Biobank/Purdue_Chkbx'), 30)  //changed for MGT

WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/Biobank/Purdue_Chkbx'), 5)  //changed for MGT

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Biobank/Purdue_Chkbx') //changed for MGT



CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarCanine'('Object Repository/Canine/StatBar/Canine_StatBar-Studies',
	'Object Repository/Canine/StatBar/Canine_StatBar-Cases', 'Object Repository/Canine/StatBar/Canine_StatBar-Samples',
	'Object Repository/Canine/StatBar/Canine_StatBar-CaseFiles', 'Object Repository/Canine/StatBar/Canine_StatBar-StudyFiles')  //added study files object; updated case files object

////clicking the Cases tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/CanineResults_Cases_Tab'), 30)
WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/CanineResults_Cases_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_Cases_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICD', GlobalVariable.G_StatBar_Studies, 'Object Repository/Canine/Canine_CasesTable',
	'Object Repository/Canine/Canine_TableHeader', 'Object Repository/Canine/Canine_CasesTabNextBtn', GlobalVariable.G_WebTabnameCases,
	GlobalVariable.G_CypherTabnameCases, GlobalVariable.G_QueryCasesTab)

