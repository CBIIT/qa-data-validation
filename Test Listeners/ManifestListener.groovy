/*import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import java.lang.*;


class ManifestListener {
	String renameManifestFile = GlobalVariable.ManifestFlag
	
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 

	@BeforeTestCase
	def getTestScriptName(TestCaseContext testCaseContext) {
    String TestCaseId = testCaseContext.getTestCaseId()
	//System.out.println("**********this is the value of test case id string******** "+testCaseContext.getTestCaseId());
	GlobalVariable.G_currentTCName =TestCaseId.substring(TestCaseId.lastIndexOf("TC"))
	//GlobalVariable.G_currentTCNameWithSlash =TestCaseId.substring((TestCaseId.lastIndexOf("TC"))-1)
	System.out.println("This is the test case name: "+GlobalVariable.G_currentTCName)
	//System.out.println("This is the test case name with slash : "+GlobalVariable.G_currentTCNameWithSlash)
  }
	
	 @param testCaseContext related information of the executed test case.
	 
	@AfterTestCase
	def renameManifestFile(TestCaseContext testCaseContext) {
		//if(renameManifestFile==Y){
			//rename manifest
		//} 
		
	}*/
