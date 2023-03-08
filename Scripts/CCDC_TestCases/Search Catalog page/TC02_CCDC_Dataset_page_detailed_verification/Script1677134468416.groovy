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

//Step 1--------------------Opening the desired url ****************************************************************
System.out.println('This is base url: ' + GlobalVariable.baseUrl)

System.out.println('This is the suffix url: ' + sUrl)

Url = (GlobalVariable.baseUrl + sUrl)

GlobalVariable.fullUrl = Url

System.out.println('This is the full url: ' + GlobalVariable.fullUrl)

CustomKeywords.'ctdc.utilities.DataValidation.initDriver'()


//Step 2--------------------Verifying Dataset Name ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/DatasetName'),5)) {
	String webDtstName = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/DatasetName'))
	System.out.println ("This is the value of Dataset Name obtained from UI :" + webDtstName)
	System.out.println ("This is the value of Dataset Name obtained from input test data :" + ipDtstName)
GlobalVariable.G_DtstName=ipDtstName.toString()
	System.out.println ("This is the value of Dataset Name stored as global variable :" + GlobalVariable.G_DtstName)
	if(Assert.assertEquals(webDtstName,GlobalVariable.G_DtstName)) {
		System.out.println ("Dataset name in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in Dataset Name **************** ")
	}

}else {
	System.out.println ("Dataset Name is not available for this dataset")
		
}


//Step 3--------------------Verifying Resource Code****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/ResrcCode'),5)) {
	String webResCode = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/ResrcCode'))
	System.out.println ("This is the value of Resource Code obtained from UI :" + webResCode)
	System.out.println ("This is the value of Resource Code obtained from input test data :" + ipResCode)
    GlobalVariable.G_resCode=ipResCode.toString()
	System.out.println ("This is the value of Resource Code stored as global variable :" + GlobalVariable.G_resCode)
	if(Assert.assertEquals(webResCode,GlobalVariable.G_resCode)) {
		System.out.println ("Resource Code in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in Resource Code **************** ")
	}

}else {
	System.out.println ("Resource Code is not available for this dataset")
		
}
	
//Step 4--------------------Verifying Num of Cases ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/CasesCnt'),5)) {
	String webCasesCnt = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CasesCnt'))
	System.out.println ("This is the value of Cases Count obtained from UI :" + webCasesCnt)
	System.out.println ("This is the value of Cases Count obtained from input test data :" + ipCasesCnt)
    GlobalVariable.G_casesCnt=ipCasesCnt.toString()
	System.out.println ("This is the value of Cases Count stored as global variable :" + GlobalVariable.G_casesCnt)
	if(Assert.assertEquals(webCasesCnt,GlobalVariable.G_casesCnt)) {
		System.out.println ("Cases Count in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in Cases Count **************** ")
	}

}else {
	System.out.println ("Cases Count is not available for this dataset")
		
}
	
//Step 5--------------------Verifying Case Sex ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/CaseSex'),5)) {
	String webCaseSex = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseSex'))
	System.out.println ("This is the value of Case Sex obtained from UI :" + webCaseSex)
	System.out.println ("This is the value of Case Sex obtained from input test data :" + ipCaseSex)
	GlobalVariable.G_caseSex=ipCaseSex.toString()
	System.out.println ("This is the value of case Sex stored as global variable :" + GlobalVariable.G_caseSex)
	if(Assert.assertEquals(webCaseSex,GlobalVariable.G_caseSex)) {
		System.out.println ("case Sex in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in case Sex **************** ")
	}

}else {
	System.out.println ("case Sex is not available for this dataset")
		
}
//Step 6--------------------Verifying Case Age ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/CaseAge'),5)) {
	String webCaseAge = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseAge'))
	System.out.println ("This is the value of Case Age obtained from UI :" + webCaseAge)
	System.out.println ("This is the value of Case Age obtained from input test data :" + ipCaseAge)
	GlobalVariable.G_caseAge=ipCaseAge.toString()
	System.out.println ("This is the value of case Age stored as global variable :" + GlobalVariable.G_caseAge)
	if(Assert.assertEquals(webCaseAge,GlobalVariable.G_caseAge)) {
		System.out.println ("case Age in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in case Age **************** ")
	}

}else {
	System.out.println ("case Age is not available for this dataset")
		
}

	
//Step 7--------------------Verifying Case Race ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/CaseRace'),5)) {
	String webCaseRace = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseRace'))
	System.out.println ("This is the value of Case Race obtained from UI :" + webCaseRace)
	System.out.println ("This is the value of Case Race obtained from input test data :" + ipCaseRace)
	GlobalVariable.G_caseRace=ipCaseRace.toString()
	System.out.println ("This is the value of case Race stored as global variable :" + GlobalVariable.G_caseRace)
	if(Assert.assertEquals(webCaseRace,GlobalVariable.G_caseRace)) {
		System.out.println ("case Race in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in case Race **************** ")
	}

}else {
	System.out.println ("case Race is not available for this dataset")
		
}



//Step 8--------------------Verifying Case Ethnicity ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/CaseEthn'),5)) {
	String webCaseEthn = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/CaseEthn'))
	System.out.println ("This is the value of Case Ethnicity obtained from UI :" + webCaseEthn)
	System.out.println ("This is the value of Case Ethnicity obtained from input test data :" + ipCaseEthn)
	GlobalVariable.G_caseEthn=ipCaseEthn.toString()
	System.out.println ("This is the value of case Ethnicity stored as global variable :" + GlobalVariable.G_caseEthn)
	if(Assert.assertEquals(webCaseEthn,GlobalVariable.G_caseEthn)) {
		System.out.println ("case Ethinicity in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in case Ethnicity **************** ")
	}

}else {
	System.out.println ("case Ethnicity is not available for this dataset")
		
}



//Step 9--------------------Verifying dbgap ID ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/dbgapID'), 5)) {
	String webDbgapID = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/dbgapID'))
	System.out.println ("This is the value of dbgap ID obtained from UI :" + webDbgapID)
	System.out.println ("This is the value of dbgap ID obtained from input test data :" + ipdbgapID)
	GlobalVariable.G_dbgapID = ipdbgapID.toString()
	System.out.println ("This is the value of dbgapID stored as global variable :" + GlobalVariable.G_dbgapID)
	if(Assert.assertEquals(webDbgapID,GlobalVariable.G_dbgapID)) {
		System.out.println ("dbgapID in the UI matches with the input data")
	}else {
		System.out.println (" ********************** Mismatch in dbgapID **************** ")
	}

}else {
	System.out.println ("dbgapID is not available for this dataset")
		
}


//Step 10--------------------Verifying Grant ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/Grant'),5)) {
	String webGrant = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/Grant'))
	
	System.out.println ("This is the value of Grant obtained from UI :" + webGrant)
	System.out.println ("This is the value of Grant obtained from input test data :" + ipGrant)
	 GlobalVariable.G_Grant = ipGrant.toString()
	 System.out.println ("This is the value of Grant stored as global variable :" + GlobalVariable.G_Grant)

if(Assert.assertEquals(webGrant,GlobalVariable.G_Grant)) {
	System.out.println ("Grant in the UI matches with the input data")
}else {
	System.out.println (" ********************** Mismatch in Grant **************** ")
}
	
}else {
	System.out.println ("Grant is not present for this dataset")
}

//Step 11--------------------Verifying GrantInfo ****************************************************************
if(WebUI.waitForElementPresent(findTestObject('Object Repository/CCDC/Dataset_page/GrantInfo'),5)) {
	String webGrantInfo = WebUI.getText(findTestObject('Object Repository/CCDC/Dataset_page/GrantInfo'))
	
	System.out.println ("This is the value of Grant Info obtained from UI :" + webGrantInfo)
	System.out.println ("This is the value of Grant Info obtained from input test data :" + ipGrantInfo)
 	GlobalVariable.G_GrantInfo = ipGrantInfo.toString()
	 System.out.println ("This is the value of Grant Info stored as global variable :" + GlobalVariable.G_GrantInfo)

if(Assert.assertEquals(webGrantInfo,GlobalVariable.G_GrantInfo)) {
	System.out.println ("Grant Info in the UI matches with the input data")
}else {
	System.out.println (" ********************** Mismatch in Grant Info **************** ")
}
	
}else {
	System.out.println ("Grant information is not present for this dataset")
}


WebUI.closeBrowser()

 