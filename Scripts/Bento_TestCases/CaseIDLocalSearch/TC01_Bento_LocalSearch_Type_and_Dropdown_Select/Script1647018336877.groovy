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
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.interactions.Action as Action

/*This test script:
 - Opens the browser of choice: Chrome, Firefox or Edge
 - Driver opened by Katalon is used in Selenium.
 - Takes the case ids to be searched from input excel 
 - Clicks on the Case ID local Search text box in Bento Explore page
 - Enters the case id(s) and clicks enter.
 - Reads the results displayed pertaining to the provided case id
 */
WebUI.closeBrowser()

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC01_Bento_LocalSearch.xlsx')

//WebUI.waitForElementPresent(findTestObject('Object Repository/Bento/NavBar/Bento_Cases-Btn'), 5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Canine/Canine_PopUp_Continue_Btn')
//System.out.println ("Closed the popup window");
WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Cases-Btn'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Cases-Btn')

WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Bento_LocalSearch_TxtBx'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Cases_page/Bento_LocalSearch_TxtBx')

//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_CaseIDLocalSearch_TxtBx')
WebUI.sendKeys(findTestObject('Bento/Cases_page/Bento_LocalSearch_TxtBx'), 'BENTO-CASE-3405') //This will reveal 3 ids.  Selecting the first id  with index 0
    

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.BentoLocalFindDdn'()

System.out.println('Reporting frm the test script after running bento local find function')

Thread.sleep(5000)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarBento'('Object Repository/Bento/StatBar/Bento_StatBar-Programs', 
    'Object Repository/Bento/StatBar/Bento_StatBar-Arms', 'Object Repository/Bento/StatBar/Bento_StatBar-Cases', 'Object Repository/Bento/StatBar/Bento_StatBar-Samples', 
    'Object Repository/Bento/StatBar/Bento_StatBar-Assays', 'Object Repository/Bento/StatBar/Bento_StatBar-Files')

Thread.sleep(5000)


WebUI.waitForElementPresent(findTestObject('Object Repository/Bento/Cases_page/BentoResults_Cases_Tab'), 5)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/BentoResults_Cases_Tab')
Thread.sleep(5000)
 

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('Bento', GlobalVariable.G_StatBar_Cases, 'Object Repository/Bento/Cases_page/Bento_CasesTable', 
    'Object Repository/Bento/Cases_page/Bento_CasesTableHeader', 'Object Repository/Bento/Cases_page/Bento_CasesTabNextBtn', 
    GlobalVariable.G_WebTabnameCases, GlobalVariable.G_CypherTabnameCases, GlobalVariable.G_QueryCasesTab)



WebUI.closeBrowser()

