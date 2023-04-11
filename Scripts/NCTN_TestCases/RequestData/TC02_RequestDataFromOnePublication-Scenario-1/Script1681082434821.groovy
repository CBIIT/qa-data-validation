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

WebUI.callTestCase(findTestCase('NCTN_TestCases/Login/TC01_Login-Administrator'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('NCTN/SideBar/RequestData-Link'))

WebUI.setText(findTestObject('NCTN/RequestData/Step1/ResearchPlanTitle-TxtBx'), 'Katalon Test')

WebUI.setText(findTestObject('NCTN/RequestData/Step1/ResearchPlanDescription-TxtBx'), 'This is test')

WebUI.setText(findTestObject('NCTN/RequestData/Step1/NctNumPubMedID-TxtBx1'), 'NCT00354744')

WebUI.click(findTestObject('NCTN/RequestData/Step1/IwilBeTheOnlyPerson-RdBtn'), FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('NCTN/RequestData/Step1/NameOfAor-TxtBx'), 'Alex Smith')

WebUI.setText(findTestObject('NCTN/RequestData/Step1/AorTitle-TxtBx'), 'CEO')

WebUI.setText(findTestObject('NCTN/RequestData/Step1/AorEmail-TxtBx'), 'alex.smith@test.com')

WebUI.setText(findTestObject('NCTN/RequestData/Step1/AorEntity-TxtBx'), 'NCI')

WebUI.click(findTestObject('NCTN/RequestData/Step1/iConfirmDesigntdAsAor-ChkBx'))

WebUI.click(findTestObject('NCTN/RequestData/Step1/Next-Btn'))

