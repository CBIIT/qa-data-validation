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

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.RunKatalon'('TC04_Bento_LocalSearch-UploadCaseSet_Upload_CSV.xlsx')

WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/NavBar/Bento_Cases-Btn'), 10)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/NavBar/Bento_Cases-Btn')

//as the facet already open by default, it need not be clicked on 
//WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Filter/FilterByCases_Facet'),5)
//CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Bento/Cases_page/Filter/FilterByCases_Facet')
Thread.sleep(5000)

WebUI.waitForElementClickable(findTestObject('Bento/Cases_page/Bento_LocalSearch_Upld-View_CaseSet_Btn'), 5)
System.out.println('viewed the upload case set button')
Thread.sleep(2000)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_LocalSearch_Upld-View_CaseSet_Btn')
System.out.println('clicked the upload case set button')
Thread.sleep(5000)	


WebUI.waitForElementClickable(findTestObject('Object Repository/Bento/Cases_page/Bento_LocalSearch_Upld_Browse_Btn'),5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_LocalSearch_Upld_Browse_Btn')
//select the xl from the path and upload it
// C:\Users\radhakrishnang2\Desktop\Commons_Automation\InputFiles\BentoUploadCaseSet.csv
Thread.sleep(5000)
System.out.println('clicked the browse button')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.BentoLocalFindFileUpld'('CSV')
System.out.println('uploaded the csv file')

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/Bento_LocalSearch_Upld_Submit_Btn')

Thread.sleep(5000)

//save the csv path + name in a global variable (stored in the input files folder)
//Further checks /negative validations to be added
/*verify the number of entered entries are matched
 * - get the ids & total count in xl cols  store total in variable.
 * - compare it with the matched id count from tab header in upload case set window.
 * - Verify if unmatched says 0
 * - read the submitted caseid column and associated prog column
 * - verify the submitted case id col with the case ids entered from excel
 * - scrape the webdata from explore page & verify the case ids & program id match from upload window ? */



CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.readStatBarBento'('Object Repository/Bento/StatBar/Bento_StatBar-Programs',
	'Object Repository/Bento/StatBar/Bento_StatBar-Arms', 'Object Repository/Bento/StatBar/Bento_StatBar-Cases', 'Object Repository/Bento/StatBar/Bento_StatBar-Samples',
	'Object Repository/Bento/StatBar/Bento_StatBar-Assays', 'Object Repository/Bento/StatBar/Bento_StatBar-Files')

Thread.sleep(5000)


WebUI.waitForElementPresent(findTestObject('Object Repository/Bento/Cases_page/BentoResults_Cases_Tab'), 5)
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.clickTab'('Object Repository/Bento/Cases_page/BentoResults_Cases_Tab')
Thread.sleep(5000)
 
CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.multiFunction'('Bento', GlobalVariable.G_StatBar_Publications, 'Object Repository/Bento/Cases_page/Bento_CasesTable',
	'Object Repository/Bento/Cases_page/Bento_CasesTableHeader', 'Object Repository/Bento/Cases_page/Bento_CasesTabNextBtn',
	GlobalVariable.G_WebTabnameCases, GlobalVariable.G_CypherTabnameCases, GlobalVariable.G_QueryCasesTab)



Thread.sleep(5000)
WebUI.closeBrowser()

