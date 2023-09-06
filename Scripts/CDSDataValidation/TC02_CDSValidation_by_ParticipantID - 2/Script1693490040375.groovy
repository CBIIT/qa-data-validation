import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW

WebUI.closeBrowser()

CustomKeywords.'ctdc.utilities.CDSValidation.runKatalonDataValidation'("TC02_CDSValidation_by_ParticipantID - 2.xlsx")

//CustomKeywords.'ctdc.utilities.CDSValidation.FindDataInExcel'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", "1", 
//	 GlobalVariable.G_QuerySamplesTab, GlobalVariable.G_WebTabnameSamples)
//
//CustomKeywords.'ctdc.utilities.ReadExcel.Neo4j'(GlobalVariable.G_CypherTabnameSamples,GlobalVariable.G_QuerySamplesTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", "2", 
	 GlobalVariable.G_WebTabnameSamples,GlobalVariable.G_CypherTabnameSamples,GlobalVariable.G_QuerySamplesTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", "2",
	GlobalVariable.G_WebTabnameParticipants,
	GlobalVariable.G_CypherTabnameParticipants, GlobalVariable.G_QueryParticipantsTab)

CustomKeywords.'ctdc.utilities.CDSValidation.runTestCaseByParticipantID'("cds_pdxnet_bingliang_phs001980_index20230323_curatorQCed_April2023_bento.xlsx", "2",
	GlobalVariable.G_WebTabnameFiles,
	GlobalVariable.G_CypherTabnameFiles, GlobalVariable.G_QueryFilesTab)



