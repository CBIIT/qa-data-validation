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

//Study IN (COTC022), Breed IN (Boxer, Great Dane), Diagnosis IN (OsteoSarcoma), Primary Disease Site IN (Bone (Appendicular))
WebUI.closeBrowser()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC06_Canine_StudyCOTC022-Breed_Diagnosis_PrimDiseaseSite.xlsx')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Canine_PopUp_Continue_Btn')
System.out.println ("Closed the popup window");

WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 20)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Canine/NavBar/Canine_Cases_Btn')

//WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/FilterByCases_Facet'), 20)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/FilterByCases_Facet')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Study/Canine_Filter_Study'), 30)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Filter/Study/Canine_Filter_Study')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Study/Canine_Filter_Study-COTC022_Chkbx'), 30)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/Study/Canine_Filter_Study-COTC022_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/BREED_Ddn'), 30)

WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/BREED_Ddn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/Breed/BREED_Ddn')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/BullDog_Chkbx'), 30)

WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/BullDog_Chkbx'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/Breed/BullDog_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/GoldenRetr_Chkbx'), 30)

WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/Breed/GoldenRetr_Chkbx'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/Breed/GoldenRetr_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/Diagnosis/DIAGNOSIS_Ddn'), 30)

WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/Diagnosis/DIAGNOSIS_Ddn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/Diagnosis/DIAGNOSIS_Ddn')

WebUI.waitForElementPresent(findTestObject('Canine/Filter/Diagnosis/OsteoSarcoma_Chkbx'), 30)

WebUI.verifyElementPresent(findTestObject('Canine/Filter/Diagnosis/OsteoSarcoma_Chkbx'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Canine/Filter/Diagnosis/OsteoSarcoma_Chkbx')

WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/PrimDiseaseSite/PRIMARYDISEASESITE_Ddn'), 30)

WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/Filter/PrimDiseaseSite/PRIMARYDISEASESITE_Ddn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Canine/Filter/PrimDiseaseSite/PRIMARYDISEASESITE_Ddn')


WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/Filter/PrimDiseaseSite/BoneApendicular_Chkbx'), 30)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTabCanineStat'('Object Repository/Canine/Filter/PrimDiseaseSite/BoneApendicular_Chkbx')


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarCanine'('Object Repository/Canine/StatBar/Canine_StatBar-Studies', 
    'Object Repository/Canine/StatBar/Canine_StatBar-Cases', 'Object Repository/Canine/StatBar/Canine_StatBar-Samples', 
    'Object Repository/Canine/StatBar/Canine_StatBar-CaseFiles', 'Object Repository/Canine/StatBar/Canine_StatBar-StudyFiles')

////clicking the Cases tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/CanineResults_Cases_Tab'), 30)
WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/CanineResults_Cases_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_Cases_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_Publications, 'Object Repository/Canine/Canine_CasesTable',
	'Object Repository/Canine/Canine_TableHeader', 'Object Repository/Canine/Canine_CasesTabNextBtn', GlobalVariable.G_WebTabnameParticipants,
	GlobalVariable.G_CypherTabnameParticipants, GlobalVariable.G_QueryParticipantsTab)

//clicking the Samples tab
WebUI.waitForElementPresent(findTestObject('Object Repository/Canine/CanineResults_Samples_Tab'), 30)
WebUI.verifyElementPresent(findTestObject('Object Repository/Canine/CanineResults_Samples_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/CanineResults_Samples_Tab')
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('ICDC', GlobalVariable.G_StatBar_Datasets, 'Object Repository/Canine/Canine_Samples_Table',
	'Object Repository/Canine/Canine_Samples_TableHdr', 'Object Repository/Canine/Canine_SamplesTabNextBtn', GlobalVariable.G_WebTabnameSamples,
	GlobalVariable.G_CypherTabnameSamples, GlobalVariable.G_QuerySamplesTab)

