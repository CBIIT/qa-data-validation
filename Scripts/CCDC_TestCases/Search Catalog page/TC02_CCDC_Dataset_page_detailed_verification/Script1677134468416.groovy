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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import org.testng.Assert;
WebUI.closeBrowser()

// public WebDriver driver
//Step 1--------------------Opening the desired url ****************************************************************
System.out.println('This is base url: ' + GlobalVariable.baseUrl)

System.out.println('This is the suffix url: ' + sUrl)

Url = (GlobalVariable.baseUrl + sUrl)

GlobalVariable.fullUrl = Url

System.out.println('This is the full url: ' + GlobalVariable.fullUrl)
//WebDriver drv = CustomKeywords.'ctdc.utilities.CustomBrowserDriver.createWebDriver'();
 
 
//System.out.println("This is urlname: "+GlobalVariable.fullUrl)
//driver.get(GlobalVariable.fullUrl)
//driver.manage().window().maximize()
//System.out.println("The window is maximized")


WebDriver drv = CustomKeywords.'ctdc.utilities.DataValidation.passDriver'()
String elementlabel

//Step 2--------------------Verifying Dataset Name ****************************************************************
System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
GlobalVariable.G_DtstName=ipDtstName.toString()
 elementlabel= "Dataset Name"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Dataset_page/DatasetName', ipDtstName, GlobalVariable.G_DtstName, elementlabel)


//Step 3--------------------Verifying Resource Code****************************************************************
System.out.println ("This is the value of Resource Code obtained from input test data :" + ipResCode)
GlobalVariable.G_resCode=ipResCode.toString()
 elementlabel = "Resource Code"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/ResrcCode', ipResCode, GlobalVariable.G_resCode, elementlabel)


//Step 4--------------------Verifying Dataset Description ****************************************************************
System.out.println ("This is the value of Dataset Description obtained from input test data :" + ipDtstDesc)
GlobalVariable.G_DtstDesc=ipDtstDesc.toString()
 elementlabel = "Dataset Description"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/DatasetDesc', ipDtstDesc, GlobalVariable.G_DtstDesc, elementlabel)


 
//Step 5--------------------Verifying Dataset Scope****************************************************************
System.out.println ("This is the value of Dataset Scope obtained from input test data :" + ipDtstScope)
GlobalVariable.G_DtstScope=ipDtstScope.toString()
 elementlabel = "Dataset Scope"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/DtstScope', ipDtstScope, GlobalVariable.G_DtstScope, elementlabel)

/*
//Step 6--------------------Verifying POC Name****************************************************************
System.out.println ("This is the value of POC Name obtained from input test data :" + ipPOC)
GlobalVariable.G_POC=ipPOC.toString()
 elementlabel = "POC Name"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/POC', ipPOC, GlobalVariable.G_POC, elementlabel)

*/
//Step 7--------------------Verifying POC Email****************************************************************
System.out.println ("This is the value of POC Email obtained from input test data :" + ipPOCemail)
GlobalVariable.G_POCemail=ipPOCemail.toString()
 elementlabel = "POC email"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/POCemail', ipPOCemail, GlobalVariable.G_POCemail, elementlabel)

 //Step 8--------------------Verifying Published In****************************************************************
 System.out.println ("This is the value of Published In obtained from input test data :" + ipPubIn)
 GlobalVariable.G_PubIn=ipPubIn.toString()
  elementlabel = "Published In"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/PublishedIn', ipPubIn, GlobalVariable.G_PubIn, elementlabel)
 
 

//Step 9--------------------Verifying Num of Cases ****************************************************************
 System.out.println ("This is the value of Cases Count obtained from input test data :" + ipCasesCnt)
 GlobalVariable.G_casesCnt=ipCasesCnt.toString()
 elementlabel = "Number of Cases"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/CasesCnt', ipCasesCnt, GlobalVariable.G_casesCnt, elementlabel)
 
 //Step 10--------------------Verifying Num of Samples ****************************************************************
 System.out.println ("This is the value of Samples Count obtained from input test data :" + ipSamplesCnt)
 GlobalVariable.G_samplesCnt=ipSamplesCnt.toString()
 elementlabel = "Number of Samples"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/SamplesCnt', ipSamplesCnt, GlobalVariable.G_samplesCnt, elementlabel)
 
 //Step 11--------------------Verifying Case Disease Diagnosis ****************************************************************
 System.out.println ("This is the value of Case Disease Diagnosis obtained from input test data :" + ipCaseDisDiag)
 GlobalVariable.G_caseDisDiag=ipCaseDisDiag.toString()
 elementlabel = "Case Disease diagnosis"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/caseDisDiag', ipCaseDisDiag, GlobalVariable.G_caseDisDiag, elementlabel)
 
 //Step 12--------------------Verifying Case Age ****************************************************************
 System.out.println ("This is the value of Case Age obtained from input test data :" + ipCaseAge)
 GlobalVariable.G_caseAge=ipCaseAge.toString()
 elementlabel = "Case Age"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/CaseAge', ipCaseAge, GlobalVariable.G_caseAge, elementlabel)
 
  
 //Step 13--------------------Verifying Case Ethnicity ****************************************************************
 System.out.println ("This is the value of Case Ethnicity obtained from input test data :" + ipCaseEthn)
 GlobalVariable.G_caseEthn=ipCaseEthn.toString()
 elementlabel = "Case Ethnicity"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/CaseEthn', ipCaseEthn, GlobalVariable.G_caseEthn, elementlabel)
 
 
 //Step 14--------------------Verifying Case Race ****************************************************************
 System.out.println ("This is the value of Case Race obtained from input test data :" + ipCaseRace)
 GlobalVariable.G_caseRace=ipCaseRace.toString()
 elementlabel = "Case Race"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/CaseRace', ipCaseRace, GlobalVariable.G_caseRace, elementlabel)
 
  
 //Step 15--------------------Verifying Case Sex ****************************************************************
 System.out.println ("This is the value of Case Sex obtained from input test data :" + ipCaseSex)
 GlobalVariable.G_caseSex=ipCaseSex.toString()
 elementlabel = "Case Sex"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/CaseSex', ipCaseSex, GlobalVariable.G_caseSex, elementlabel)
    
 //Step 16--------------------Verifying Case Gender ****************************************************************  
 System.out.println ("This is the value of Case Gender obtained from input test data :" + ipCaseGender)
GlobalVariable.G_caseGender=ipCaseGender.toString()
elementlabel = "Case Gender"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/CaseGender', ipCaseGender, GlobalVariable.G_caseGender, elementlabel)

//Step 17--------------------Verifying Case Tumor Site ****************************************************************  
System.out.println ("This is the value of Case Tumor Site obtained from input test data :" + ipCTumorSite)
GlobalVariable.G_cTumorSite=ipCTumorSite.toString()
elementlabel = "Case Tumor Site"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/caseTumorSite', ipCTumorSite, GlobalVariable.G_cTumorSite, elementlabel)
	
//Step 18--------------------Verifying Case Treatment Administered ****************************************************************  
System.out.println ("This is the value of Case Treatment Administered obtained from input test data :" + ipCTrtmtAdmn)
GlobalVariable.G_caseTrtmtAdmn=ipCTrtmtAdmn.toString()
elementlabel = "Case Treatment Administered"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/caseTrtmtAdmn', ipCTrtmtAdmn, GlobalVariable.G_caseTrtmtAdmn, elementlabel)
	
//Step 19--------------------Verifying Case Treatment Outcome **************************************************************** 
System.out.println ("This is the value of Case Treatmemt Outcome obtained from input test data :" + ipCTrtmtOutcm)
GlobalVariable.G_caseTrtmtOutcm=ipCTrtmtOutcm.toString()
elementlabel = "Case Treatment Outcome"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/caseTrtmtOutcm', ipCTrtmtOutcm, GlobalVariable.G_caseTrtmtOutcm, elementlabel)


//Step 20--------------------Verifying Sample Assay Method **************************************************************** 
System.out.println ("This is the value of Sample Assay Method obtained from input test data :" + ipSampAssMeth)
GlobalVariable.G_sampleAssMeth=ipSampAssMeth.toString()
elementlabel = "Sample Assay Method"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/sampleAssMethod', ipSampAssMeth, GlobalVariable.G_sampleAssMeth, elementlabel)
	
//Step 21--------------------Verifying Sample Analyte Type **************************************************************** edit this
System.out.println ("This is the value of Sample Analyte Type obtained from input test data :" + ipSampAnalType)
GlobalVariable.G_sampleAnalType=ipSampAnalType.toString()
elementlabel = "Sample Analyte Typer"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/sampleAnalType', ipSampAnalType, GlobalVariable.G_sampleAnalType, elementlabel)
	
//Step 22--------------------Verifying Sample Anatomic Site **************************************************************** edit this
System.out.println ("This is the value of Sample Anatomic Site obtained from input test data :" + ipSampAnatSite)
GlobalVariable.G_sampleAnatSite=ipSampAnatSite.toString()
elementlabel = "Sample Anatomic Site"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/sampleAnatSite', ipSampAnatSite, GlobalVariable.G_sampleAnatSite, elementlabel)


	
//Step 23--------------------Verifying Sample Composition Type **************************************************************** edit this
System.out.println ("This is the value of Case Sex obtained from input test data :" + ipSampCompType)
GlobalVariable.G_sampleCompType=ipSampCompType.toString()
elementlabel = "Sample Composition Type"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/sampleCompType', ipSampCompType, GlobalVariable.G_sampleCompType, elementlabel)
	
//Step 24--------------------Verifying Sample Is Normal **************************************************************** edit this
System.out.println ("This is the value of Case Sex obtained from input test data :" + ipSampIsNml)
GlobalVariable.G_sampleIsNml=ipSampIsNml.toString()
elementlabel = "Sample Is Normal"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/sampleIsNormal', ipSampIsNml, GlobalVariable.G_sampleIsNml, elementlabel)
	
//Step 25--------------------Verifying Sample Is Xenograft **************************************************************** edit this
System.out.println ("This is the value of Case Sex obtained from input test data :" + ipSampIsXeno)
GlobalVariable.G_sampleIsXeno=ipSampIsXeno.toString()
elementlabel = "Sample Is Xenograft"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/sampleIsXeno', ipSampIsXeno, GlobalVariable.G_sampleIsXeno, elementlabel)
	
 
//Step 26--------------------Verifying dbgap ID ****************************************************************
System.out.println ("This is the value of dbgap ID obtained from input test data :" + ipdbgapID)
GlobalVariable.G_dbgapID = ipdbgapID.toString()
elementlabel = "DBGaP ID"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/dbgapID', ipdbgapID, GlobalVariable.G_dbgapID, elementlabel)
 
 

//Step 27--------------------Verifying Grant ****************************************************************
System.out.println ("This is the value of Grant obtained from input test data :" + ipGrant)
GlobalVariable.G_Grant = ipGrant.toString()
elementlabel = "Grant"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/Grant', ipGrant, GlobalVariable.G_Grant, elementlabel)
/*
//Step 28--------------------Verifying GrantInfo ****************************************************************
System.out.println ("This is the value of Grant Info obtained from input test data :" + ipGrantInfo)
GlobalVariable.G_GrantInfo = ipGrantInfo.toString()
elementlabel = "Grant Info"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv, 'Object Repository/CCDC/Dataset_page/GrantInfo', ipGrantInfo, GlobalVariable.G_GrantInfo, elementlabel)
 */

WebUI.closeBrowser()

 