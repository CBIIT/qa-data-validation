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

WebUI.closeBrowser()

//Step 1--------------------Opening the desired url ****************************************************************
System.out.println('This is base url: ' + GlobalVariable.baseUrl)

System.out.println('This is the suffix url: ' + sUrl)

Url = (GlobalVariable.baseUrl + sUrl)

GlobalVariable.fullUrl = Url

System.out.println('This is the full url: ' + GlobalVariable.fullUrl)

//CustomKeywords.'ctdc.utilities.DataValidation.initDriver'()

WebDriver drv = CustomKeywords.'ctdc.utilities.DataValidation.passDriver'()
String elementlabel

 


//Step 2--------------------Verifying Resource Name ****************************************************************
System.out.println ("This is the value of Resource Name obtained from input test data :" + ipResrcName)
GlobalVariable.G_RsrcName=ipResrcName.toString()
elementlabel= "Resource Name"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Resource_page/ResourceName', ipResrcName, GlobalVariable.G_RsrcName, elementlabel)

 

//Step 3--------------------Verifying Resource Code ****************************************************************
System.out.println ("This is the value of Resource Code obtained from input test data :" + ipResrcCode)
GlobalVariable.G_resCode=ipResrcCode.toString()
elementlabel= "Resource Code"
CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Resource_page/ResourceCode', ipResrcCode, GlobalVariable.G_resCode, elementlabel)
 



 //Step 4--------------------Verifying Point of Contact ****************************************************************
 System.out.println ("This is the value of POC obtained from input test data :" + ipPOC)
 GlobalVariable.G_POC=ipPOC.toString()
  elementlabel= "POC"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Resource_page/POC', ipPOC, GlobalVariable.G_POC, elementlabel)
 
 


 //Step 5--------------------Verifying Dataset Summary Count ****************************************************************
 System.out.println ("This is the value of Dataset Summary count obtained from input test data :" + ipDtstSummCnt)
 GlobalVariable.G_DtstSummCnt=ipDtstSummCnt.toString()
  elementlabel= "Dataset Summary Count"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Resource_page/DtstSummaryCnt', ipDtstSummCnt, GlobalVariable.G_DtstSummCnt, elementlabel)
 
 
 
//Step 6--------------------Verifying Filter Type ****************************************************************
  System.out.println ("This is the value of Filter Type obtained from input test data :" + ipFilterType)
 GlobalVariable.G_FilterType=ipFilterType.toString()
  elementlabel= "Filter Type"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Resource_page/FilterType', ipFilterType, GlobalVariable.G_FilterType, elementlabel)
 
/*
 //Step 7--------------------Verifying Specialization ****************************************************************
 System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
 GlobalVariable.G_DtstName=ipDtstName.toString()
  elementlabel= "Dataset Name"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Dataset_page/DatasetName', ipDtstName, GlobalVariable.G_DtstName, elementlabel)
 
 */

/*
 //Step 2--------------------Verifying Data Update Date ****************************************************************
 System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
 GlobalVariable.G_DtstName=ipDtstName.toString()
  elementlabel= "Dataset Name"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Dataset_page/DatasetName', ipDtstName, GlobalVariable.G_DtstName, elementlabel)
 
 */

/*
 //Step 2--------------------Verifying Visualization Tools  ****************************************************************
 System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
 GlobalVariable.G_DtstName=ipDtstName.toString()
  elementlabel= "Dataset Name"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Dataset_page/DatasetName', ipDtstName, GlobalVariable.G_DtstName, elementlabel)
 
 */

/*
 //Step 2--------------------Verifying Analytic Tools  ****************************************************************
 System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
 GlobalVariable.G_DtstName=ipDtstName.toString()
  elementlabel= "Dataset Name"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Dataset_page/DatasetName', ipDtstName, GlobalVariable.G_DtstName, elementlabel)
 
 */

/*
 //Step 2--------------------Verifying Data Content Type****************************************************************
 System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
 GlobalVariable.G_DtstName=ipDtstName.toString()
  elementlabel= "Dataset Name"
 CustomKeywords.'ctdc.utilities.DataValidation.CCDCreadInfo'(drv,'Object Repository/CCDC/Dataset_page/DatasetName', ipDtstName, GlobalVariable.G_DtstName, elementlabel)
 
 */


 

WebUI.closeBrowser()


