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

//This testcase belongs to Canine_Testcases/NeuteredStatus.   Move there once fixed

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.browserDriver'('')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC02_Canine_Filter_StudyType-Transcriptomics.xlsx')

WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 5)

WebUI.click(findTestObject('Canine/NavBar/Canine_Cases_Btn'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/StudyType/Canine_Filter_StudyType'), 5)

WebUI.click(findTestObject('Object Repository/Canine/Filter/StudyType/Canine_Filter_StudyType'))

WebUI.click(findTestObject('Canine/Filter/StudyType/Canine_Filter_StudyType-Transcrip_Chkbx'))

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('NCATS-COP01-CCB010031', 'one')

WebUI.click(findTestObject('Canine/Canine_SaveToMycases'))

WebUI.click(findTestObject('Canine/NavBar/Canine_MyFilesCart'))

//WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Canine_GoToFiles'), 5)
//
//WebUI.click(findTestObject('Object Repository/Canine/Canine_GoToFiles'))
WebUI.maximizeWindow()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.Select_case_checkbox'('', 'allM')

//WebUI.click(findTestObject('Canine/Canine_SelectAll'))
WebUI.click(findTestObject('Canine/Canine_DownloadManifest'))

