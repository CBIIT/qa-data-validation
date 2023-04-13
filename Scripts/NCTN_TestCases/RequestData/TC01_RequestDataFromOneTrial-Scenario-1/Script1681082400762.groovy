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

WebUI.waitForElementPresent(findTestObject('NCTN/SideBar/RequestData-Link'), 3)
WebUI.click(findTestObject('NCTN/SideBar/RequestData-Link'))

WebUI.waitForElementPresent(findTestObject('NCTN/RequestData/Step1/ResearchPlanTitle-TxtBx'), 3)
WebUI.setText(findTestObject('NCTN/RequestData/Step1/ResearchPlanTitle-TxtBx'), findTestData('NCTN/RequestData').getValue(
        'ResearchPlanTitle', 1))

WebUI.setText(findTestObject('NCTN/RequestData/Step1/ResearchPlanDescription-TxtBx'), findTestData('NCTN/RequestData').getValue(
        'ResearchPlanDescription', 1))

WebUI.setText(findTestObject('NCTN/RequestData/Step1/NctNumPubMedID-TxtBx1'), findTestData('NCTN/RequestData').getValue(
        'NctNumberPubMedID', 1))

WebUI.click(findTestObject('NCTN/RequestData/Step1/IwilBeTheOnlyPerson-RdBtn'))

WebUI.setText(findTestObject('NCTN/RequestData/Step1/NameOfAor-TxtBx'), findTestData('NCTN/RequestData').getValue('NameOfAor', 
        1))

WebUI.setText(findTestObject('NCTN/RequestData/Step1/AorTitle-TxtBx'), findTestData('NCTN/RequestData').getValue('AorTitle', 
        1))

WebUI.setText(findTestObject('NCTN/RequestData/Step1/AorEmail-TxtBx'), findTestData('NCTN/RequestData').getValue('AorEmail', 
        1))

WebUI.setText(findTestObject('NCTN/RequestData/Step1/AorEntity-TxtBx'), findTestData('NCTN/RequestData').getValue('AorEntity', 
        1))

WebUI.click(findTestObject('NCTN/RequestData/Step1/iConfirmDesigntdAsAor-ChkBx'))

WebUI.click(findTestObject('NCTN/RequestData/Step1/Next-Btn'))

WebUI.verifyElementText(findTestObject('NCTN/RequestData/Step2/SummaryOfReqstdData-Txt'), findTestData('NCTN/RequestData').getValue(
	'SummaryOfReqstdData', 1))

WebUI.verifyElementText(findTestObject('NCTN/RequestData/Step2/NctNumAndTitle-Txt'), findTestData('NCTN/RequestData').getValue(
        'NctNumAndTitle', 1))




