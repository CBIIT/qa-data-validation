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

/*This test script:
 - Opens the browser of choice: Chrome, Firefox or Edge
 - Driver opened by Katalon is used in Selenium.
 - Takes the case ids to be searched from input excel 
 - Clicks on the Case ID local Search text box in Bento Explore page
 - Enters the case id(s) and clicks enter.
 - Reads the results displayed pertaining to the provided case id
 */
WebUI.closeBrowser()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC02_Bento_LocalSearch-UploadCaseSet_Enter_CASEID_List.xlsx')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Cases-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Cases-Btn')

//as the facet already open by default, it need not be clicked on 
//WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/FilterByCases_Facet'),5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Cases_page/Filter/FilterByCases_Facet')


WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Bento_LocalSearch_Upld-View_CaseSet_Btn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_LocalSearch_UpldCaseSet_Btn')
Thread.sleep(5000)

//this input should be driven through xl  & for multiple ids - valid+invalid    have valid and invalid as flag in excel and execute a switch ?? to submit clear cancel ?
WebUI.sendKeys(findTestObject('Bento/Cases_page/Bento_LocalSearch_Upld_TxtArea'),'BENTO-CASE-16495, BENTO-CASE-3405467, BENTO-CASE-12345')
Thread.sleep(5000)
System.out.println('Entered case id into the upload case set description')
 
//click the clear button
//verify whether the Summary table disappears
//click the cancel button
/* verify whether the control is returned to the main page and default case count/stat bar is displayed. 
 */

 
WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Bento_LocalSearch_Upld_Submit_Btn'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_LocalSearch_Upld_Submit_Btn')
//Click on the upload case set button

Thread.sleep(5000)


CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarBento'('Object Repository/Bento/StatBar/Bento_StatBar-Programs',
	'Object Repository/Bento/StatBar/Bento_StatBar-Arms', 'Object Repository/Bento/StatBar/Bento_StatBar-Cases', 'Object Repository/Bento/StatBar/Bento_StatBar-Samples',
	'Object Repository/Bento/StatBar/Bento_StatBar-Assays', 'Object Repository/Bento/StatBar/Bento_StatBar-Files')

Thread.sleep(5000)
WebUI.closeBrowser()

