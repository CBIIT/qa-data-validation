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

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.browserDriver'('')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC01_Trials_E2E_SelectAllCases.xlsx')

WebUI.waitForElementPresent(findTestObject('Trials/Trials_CASES_Btn'), 5)

WebUI.click(findTestObject('Trials/Trials_CASES_Btn'))

WebUI.waitForElementPresent(findTestObject('Trials/Cases_page/Trials_CasesTable'), 5)

WebUI.waitForElementPresent(findTestObject('Trials/Cases_page/Trials_SelectAll'), 5)

WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'all')

WebUI.click(findTestObject('Trials/Trials_SaveToMycases'))

WebUI.click(findTestObject('Trials/Cases_page/Trials_MyCases'))

WebUI.waitForElementPresent(findTestObject('Trials/Cases_page/Trials_GoToFiles'), 5)

WebUI.click(findTestObject('Trials/Cases_page/Trials_GoToFiles'))

WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'allM')

//WebUI.click(findTestObject('Object Repository/Canine/Canine_MyCasesFiles_SelectAll'))
WebUI.click(findTestObject('Trials/Trials_DownloadManifest'))

