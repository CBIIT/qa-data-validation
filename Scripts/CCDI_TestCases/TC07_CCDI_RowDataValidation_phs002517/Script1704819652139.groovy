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

WebUI.closeBrowser()



//System.out.println("Participant ID is: "+ participant_id)
GlobalVariable.G_Value=qa_participant_id
CustomKeywords.'ctdc.utilities.CDSValidation.printTCData'()
CustomKeywords.'ctdc.utilities.CDSValidation.printTestCaseNumber'()
CustomKeywords.'ctdc.utilities.CDSValidation.runKatalonDataValidation'("CCDI","TC07_CCDI_RowDataValidation_phs002517.xlsx")


CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("phs002517-participant.xlsx", GlobalVariable.G_Value,"participant_id", GlobalVariable.G_ExDataExcel ,
	GlobalVariable.G_WebTabnameParticipants,GlobalVariable.G_CypherTabnameParticipants, GlobalVariable.G_QueryParticipantsTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("phs002517-diagnosis.xlsx", GlobalVariable.G_Value,"qa_participant_id", GlobalVariable.G_ColumNamesDiagnosisTab ,
	GlobalVariable.G_WebTabnameDiagnosis,GlobalVariable.G_CypherTabnameDiagnosis, GlobalVariable.G_QueryDiagnosisTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("phs002517-sample.xlsx", GlobalVariable.G_Value,"qa_participant_id", GlobalVariable.G_ColumNamesSamplesTab,
	GlobalVariable.G_WebTabnameSamples,GlobalVariable.G_CypherTabnameSamples,GlobalVariable.G_QuerySamplesTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("phs002517-files.xlsx", GlobalVariable.G_Value,"qa_participant_id", GlobalVariable.G_ColumNamesFilesTab,
	GlobalVariable.G_WebTabnameFiles,GlobalVariable.G_CypherTabnameFiles, GlobalVariable.G_QueryFilesTab)