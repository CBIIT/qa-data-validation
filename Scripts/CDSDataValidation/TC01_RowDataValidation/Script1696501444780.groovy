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

<<<<<<< HEAD


//System.out.println("Participant ID is: "+ participant_id)
GlobalVariable.G_Value=participant_id
CustomKeywords.'ctdc.utilities.CDSValidation.printTCData'()
CustomKeywords.'ctdc.utilities.CDSValidation.printTestCaseNumber'()
CustomKeywords.'ctdc.utilities.CDSValidation.runKatalonDataValidation'("CDS","CDSValidation_by_ParticipantID.xlsx")


CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", GlobalVariable.G_Value,"participant_id", GlobalVariable.G_ParticipTabHdr ,
=======
System.out.println("Participant ID is: "+ participant_id)
GlobalVariable.G_Value=participant_id
System.out.println("GlobalVariable.G_Value is: "+ GlobalVariable.G_Value)
CustomKeywords.'ctdc.utilities.CDSValidation.runKatalonDataValidation'("CDSValidation_by_ParticipantID.xlsx")


CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", GlobalVariable.G_Value,"participant_id", GlobalVariable.G_ColumNamesParticipTab ,
>>>>>>> 0d05ed1e58e6937271e622cde22b02ad78a3be85
	GlobalVariable.G_WebTabnameParticipants,
	GlobalVariable.G_CypherTabnameParticipants, GlobalVariable.G_QueryParticipantsTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", GlobalVariable.G_Value,"participant_id", GlobalVariable.G_ColumNamesSamplesTab,
	GlobalVariable.G_WebTabnameSamples,GlobalVariable.G_CypherTabnameSamples,GlobalVariable.G_QuerySamplesTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", GlobalVariable.G_Value,"participant_id", GlobalVariable.G_ColumNamesFilesTab,
	GlobalVariable.G_WebTabnameFiles,
	GlobalVariable.G_CypherTabnameFiles, GlobalVariable.G_QueryFilesTab)