import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

<<<<<<< HEAD:Scripts/Canine_TestCases/End_To_End/TC01_END_TO_END_SelectAllCases/Script1589481381714.groovy

=======
>>>>>>> 144cdc0292be5ad7779574058ef4827f821359bf:Scripts/TC01_Canine_E2E_SelectAll/Script1590086039031.groovy
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
<<<<<<< HEAD:Scripts/Canine_TestCases/End_To_End/TC01_END_TO_END_SelectAllCases/Script1589481381714.groovy

=======
>>>>>>> 144cdc0292be5ad7779574058ef4827f821359bf:Scripts/TC01_Canine_E2E_SelectAll/Script1590086039031.groovy

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.browserDriver'('')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('Password_canine.xlsx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_Cases_Btn'), 5)

WebUI.click(findTestObject('Object Repository/Canine/Canine_Cases_Btn'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_CasesTable'), 5)

WebUI.waitForElementPresent(findTestObject('Canine/Canine_SelectAll'), 5)

WebUI.maximizeWindow()

not_run: WebUI.click(findTestObject('Canine/Canine_SelectAll'))

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'all')

WebUI.click(findTestObject('Canine/Canine_SaveToMycases'))

WebUI.click(findTestObject('Canine/Canine_MyCases'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_GoToFiles'), 5)

WebUI.click(findTestObject('Object Repository/Canine/Canine_GoToFiles'))

WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'allM')

//WebUI.click(findTestObject('Object Repository/Canine/Canine_MyCasesFiles_SelectAll'))
WebUI.click(findTestObject('Canine/Canine_DownloadManifest'))

