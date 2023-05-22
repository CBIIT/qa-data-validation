import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.awt.AWTException as AWTException
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import java.io.IOException as IOException
import java.util.concurrent.TimeUnit as TimeUnit
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile as FirefoxProfile
import org.openqa.selenium.firefox.ProfilesIni as ProfilesIni
import org.openqa.selenium.firefox.FirefoxOptions as FirefoxOptions
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import java.util.Iterator as Iterator
import java.util.Set as Set
import java.nio.file.Path as Path
import java.nio.file.Paths as Paths
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.poi.xssf.usermodel.XSSFCell as XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow as XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet as XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions as ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait as WebDriverWait
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.HashMap as HashMap
import java.util.Map as Map
import org.apache.commons.io.FileUtils as FileUtils
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.TakesScreenshot as TakesScreenshot
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.interactions.Action as Action
import org.openqa.selenium.OutputType as OutputType
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.closeBrowser()

//Step 1--------------------Opening the desired url ****************************************************************
System.out.println('This is base url: ' + GlobalVariable.baseUrl)

System.out.println('This is the suffix url: ' + sUrl)

Url = (GlobalVariable.baseUrl + sUrl)

GlobalVariable.fullUrl = Url

System.out.println('This is the full url: ' + GlobalVariable.fullUrl)

CustomKeywords.'ctdc.utilities.DataValidation.initDriver'()

System.out.println('This is the URL of the current page :' + WebUI.getUrl())

//Step 2--------------------Verifying Target ID ****************************************************************
webTargID = WebUI.getText(findTestObject('Object Repository/MTP/TargetAssociationsPage/TargetID'))

System.out.println('This is the value of target ID obtained from UI :' + webTargID)

System.out.println('This is the value of target ID obtained from input test data :' + ipTargID)

WebUI.verifyMatch(ipTargID, webTargID, false)

System.out.println('Target ID in the UI matches with the input data')

//Step 3--------------------Verifying Target Name ****************************************************************
webTargName = WebUI.getText(findTestObject('Object Repository/MTP/TargetAssociationsPage/TargetName'))

System.out.println('This is the value of target Name obtained from UI :' + webTargName)

System.out.println('This is the value of target Name obtained from input test data :' + ipTargName)

WebUI.verifyMatch(ipTargName, webTargName, false)

System.out.println('Target Name in the UI matches with the input data')


//Step 4--------------------Verifying somaticAlt widget ****************************************************************
webSomAltWdgt = WebUI.verifyElementPresent(findTestObject('Object Repository/MTP/TargetProfilePage/SomaticAlt_Wdgt'), 5)

System.out.println('This is the status of somatic alt widget obtained from UI :' + webSomAltWdgt)

webSomAltWdgtTxt = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/SomaticAlt_Wdgt'))

System.out.println('This is the text of somatic alt widget obtained from UI :' + webSomAltWdgtTxt)

if ((webSomAltWdgt == true) && (webSomAltWdgtTxt == 'Available')) {
    System.out.println('Somatic Alterations is clickable. Data is available')

    WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/SomaticAlt_Wdgt'))

    Thread.sleep(2000)

    webSomaticAlt = 'TRUE'

    System.out.println('This is the value of Somatic Alterations obtained from input test data :' + ipSomaticAlt)

    WebUI.verifyMatch(ipSomaticAlt, webSomaticAlt, false)

    //Step 5--------------------Verifying snvByGene table **************************************************************** 
    boolean websnvByGeneTab = WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/MTP/TargetProfilePage/snvByGene_tab'), 
        'disabled', 5, FailureHandling.OPTIONAL)

    System.out.println('This is the value of the absence of disabled attribute for snvbyGene tab : ' + websnvByGeneTab)

    switch (websnvByGeneTab) {
        case 'true': 
		//When the tab is enabled - which means the absence of 'disabled' attribute is true
            WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/snvByGene_tab'))

            Thread.sleep(3000)

            boolean webPaginationSG = WebUI.verifyElementNotPresent(findTestObject('Object Repository/MTP/TargetProfilePage/snvByGene_pagination'), 
                5, FailureHandling.OPTIONAL)

            System.out.println('This is the value of pagination absence for snvbyGene tab : ' + webPaginationSG)

            if (webPaginationSG == true) {
                //if the pagination is missing when there are <=10 rows only, then count the rows manually
                websnvByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.countRows'('Object Repository/MTP/TargetProfilePage/SomAlt_snvByGene_TblBdy')

                System.out.println('This is the value of snv by gene count obtained from UI :' + websnvByGeneCnt //if the pagination is present-when there are >10 rows, then get the total count from the rows per page max number
                    )
            } else if (webPaginationSG == false) {
                tempCount = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/snvByGene_Cnt'))

                System.out.println('This is the value of tempcount before extracting the rowcount :' + tempCount)

                websnvByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.CountRowsfromPagination'(tempCount)

                System.out.println('This is the value of snv by gene count extracted from pagination in UI :' + websnvByGeneCnt)
            }
            
            System.out.println('This is the value of snv by gene count obtained from input test data :' + ipsnvByGene)

            WebUI.verifyMatch(ipsnvByGene, websnvByGeneCnt, false)

            break
        case 'false':
		//When the tab is disabled  - which means the absence of 'disabled' attribute is false

            System.out.println('Snv By Gene Tab is not clickable. There is no data present for the tab.')

            websnvByGeneCnt = '0'

            System.out.println('This is the value of snv by gene count obtained from input test data :' + ipsnvByGene)

            WebUI.verifyMatch(ipsnvByGene, websnvByGeneCnt, false)

            break
        default:
            System.out.println('Error in accessing Snv By Gene tab')

            break
    }
    
    //Step 6--------------------Verifying snvByVariant table****************************************************************
    boolean websnvByVarTab = WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/MTP/TargetProfilePage/snvByVar_tab'), 
        'disabled', 5, FailureHandling.OPTIONAL)

    System.out.println('This is the value of the absence of disabled attribute for snvbyVariant tab : ' + websnvByVarTab)

    switch (websnvByVarTab) {
        case 'true' : 
		//When the tab is enabled - which means the absence of 'disabled' attribute is true
       
            WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/snvByVar_tab'))

            Thread.sleep(2000)

            boolean webPaginationSV = WebUI.verifyElementNotPresent(findTestObject('Object Repository/MTP/TargetProfilePage/snvByVar_pagination'), 
                5, FailureHandling.OPTIONAL)

            System.out.println('This is the value of pagination absence for snvByVariant tab : ' + webPaginationSV)

            if (webPaginationSV == true) {
                //if pagination not available is true, when there are <=10 rows only, then count the rows manually
                websnvByVarCnt = CustomKeywords.'ctdc.utilities.DataValidation.countRows'('Object Repository/MTP/TargetProfilePage/SomAlt_snvByVar_TblBdy')

                System.out.println('This is the value of snv by variant count obtained from UI :' + websnvByVarCnt //if the pagination not available is false, when there are >10 rows, then get the total count from the rows per page max number
                    )
            } else if (webPaginationSV == false) {
                tempCount = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/snvByVar_Cnt'))

                System.out.println('This is the value of tempcount before extracting the rowcount :' + tempCount)

                websnvByVarCnt = CustomKeywords.'ctdc.utilities.DataValidation.CountRowsfromPagination'(tempCount)

                System.out.println('This is the value of snv by variant count extracted from pagination in UI :' + websnvByVarCnt)
            }
            
            System.out.println('This is the value of snv by variant count obtained from input test data :' + ipsnvByVariant)

            WebUI.verifyMatch(ipsnvByVariant, websnvByVarCnt, false)

            break
        case 'false':
		 //When the tab is disabled  - which means the absence of 'disabled' attribute is false
        
            System.out.println('Snv By Variant Tab is not clickable. There is no data present for the tab.')

            websnvByVarCnt = '0'

            System.out.println('This is the value of snv by variant count obtained from input test data :' + ipsnvByVariant)

            WebUI.verifyMatch(ipsnvByVariant, websnvByVarCnt, false)

            break
        default:
            System.out.println('Error in accessing Snv By Variant tab')

            break
    }
    
    //Step 7--------------------Verifying cnvByGene ****************************************************************
    boolean webcnvByGeneTab = WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/MTP/TargetProfilePage/cnvByGene_tab'), 
        'disabled', 5, FailureHandling.OPTIONAL)

    System.out.println('This is the value of the disabled attribute for cnvbyGene tab : ' + webcnvByGeneTab)

    switch (webcnvByGeneTab) {
        case 'true' :
		//When the tab is enabled - which means the absence of 'disabled' attribute is true
        
            WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/cnvByGene_tab'))

            Thread.sleep(2000)

            boolean webPaginationCG = WebUI.verifyElementNotPresent(findTestObject('Object Repository/MTP/TargetProfilePage/cnvByGene_pagination'), 
                5, FailureHandling.OPTIONAL)

            System.out.println('This is the value of pagination absence for cnvByGene tab : ' + webPaginationCG)

            if (webPaginationCG == true) {
                //if the pagination absence is true, when there are <=10 rows only, then count the rows manually
                webcnvByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.countRows'('Object Repository/MTP/TargetProfilePage/SomAlt_cnvByGene_TblBdy')

                System.out.println('This is the value of snv by variant count obtained from UI :' + webcnvByGeneCnt //if the pagination absence is false, when there are >10 rows, then get the total count from the rows per page max number
                    )
            } else if (webPaginationCG == false) {
                tempCount = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/cnvByGene_Cnt'))

                System.out.println('This is the value of tempcount before extracting the rowcount :' + tempCount)

                webcnvByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.CountRowsfromPagination'(tempCount)

                System.out.println('This is the value of cnv by gene count extracted from pagination in UI :' + webcnvByGeneCnt)
            }
            
            System.out.println('This is the value of cnv by gene count obtained from input test data :' + ipcnvByGene)

            WebUI.verifyMatch(ipcnvByGene, webcnvByGeneCnt, false)

            break
        case 'false' :
		//When the tab is disabled  - which means the absence of 'disabled' attribute is false
        
            System.out.println('CNV By Gene Tab is not clickable. There is no data present for the tab.')

            webcnvByGeneCnt = '0'

            System.out.println('This is the value of cnv by gene count obtained from input test data :' + ipcnvByGene)

            WebUI.verifyMatch(ipcnvByGene, webcnvByGeneCnt, false)

            break
        default:
            System.out.println('Error in accessing cnv By gene tab')

            break
    }
    
    //Step 8--------------------Verifying fusionByGene ****************************************************************
    boolean webfusionByGeneTab = WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/MTP/TargetProfilePage/fusionByGene_tab'), 
        'disabled', 5, FailureHandling.OPTIONAL)

    System.out.println('This is the status of the widget clickability :' + webfusionByGeneTab)

    System.out.println('This is the value of the absence of disabled attribute for fusionbygene tab : ' + webfusionByGeneTab)

    switch (webfusionByGeneTab) {
        case 'true' :
		//When the tab is enabled - which means the absence of 'disabled' attribute is true
        
            WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/fusionByGene_tab'))

            Thread.sleep(2000)

            boolean webPaginationFG = WebUI.verifyElementNotPresent(findTestObject('Object Repository/MTP/TargetProfilePage/fusionByGene_pagination'), 
                5, FailureHandling.OPTIONAL)

            System.out.println('This is the value of pagination absence for fusionByGene tab : ' + webPaginationFG)

            if (webPaginationFG == true) {
                //if the pagination absence is true, when there are <=10 rows only, then count the rows manually
                webfusionByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.countRows'('Object Repository/MTP/TargetProfilePage/SomAlt_fusionByGene_TblBdy')

                System.out.println('This is the value of snv by variant count obtained from UI :' + webfusionByGeneCnt //if the pagination absence is false -when there are >10 rows, then get the total count from the rows per page max number
                    //if the pagination is missing when there are <=10 rows only, then count the rows manually
                    )
            } else if (webPaginationFG == false) {
                tempCount = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/fusionByGene_Cnt'))

                System.out.println('This is the value of tempcount before extracting the rowcount :' + tempCount)

                webfusionByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.CountRowsfromPagination'(tempCount)

                System.out.println('This is the value of fusion by gene count extracted from pagination in UI :' + webfusionByGeneCnt)
            }
            
            System.out.println('This is the value of fusion by gene count obtained from input test data :' + ipfusionByGene)

            WebUI.verifyMatch(ipfusionByGene, webfusionByGeneCnt, false)

            break
        case 'false':
		 //When the tab is disabled  - which means the absence of 'disabled' attribute is false
        
            System.out.println('fusion By Gene Tab is not clickable. There is no data present for the tab.')

            webfusionByGeneCnt = '0'

            System.out.println('This is the value of fusion by gene count obtained from input test data :' + ipfusionByGene)

            WebUI.verifyMatch(ipfusionByGene, webfusionByGeneCnt, false)

            break
        default:
            System.out.println('Error in accessing fusion By gene tab')

            break
    }
   
    //Step 9--------------------Verifying fusion ****************************************************************
    boolean webfusionTab = WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/MTP/TargetProfilePage/fusion_tab'), 
        'disabled', 5, FailureHandling.OPTIONAL)

    System.out.println('This is the value of the disabled attribute for fusion tab : ' + webfusionTab)

    switch (webfusionTab) {
        case 'true':
		 //When the tab is enabled - which means the absence of 'disabled' attribute is true
        
            WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/fusion_tab'))

            Thread.sleep(2000)

            boolean webPaginationF = WebUI.verifyElementNotPresent(findTestObject('Object Repository/MTP/TargetProfilePage/fusion_pagination'), 
                5, FailureHandling.OPTIONAL)

            System.out.println('This is the value of pagination absence for fusion tab : ' + webPaginationF)

            if (webPaginationF == true) {
                //if the pagination absence is true, when there are <=10 rows only, then count the rows manually
                webfusionCnt = CustomKeywords.'ctdc.utilities.DataValidation.countRows'('Object Repository/MTP/TargetProfilePage/SomAlt_fusion_TblBdy')

                System.out.println('This is the value of fusion count obtained from UI :' + webfusionCnt //if the pagination absence is false -when there are >10 rows, then get the total count from the rows per page max number
                    //if the pagination is missing when there are <=10 rows only, then count the rows manually
                    )
            } else if (webPaginationF == false) {
                tempCount = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/fusion_Cnt'))

                System.out.println('This is the value of tempcount before extracting the rowcount :' + tempCount)

                webfusionCnt = CustomKeywords.'ctdc.utilities.DataValidation.CountRowsfromPagination'(tempCount)

                System.out.println('This is the value of fusion count extracted from pagination in UI :' + webfusionCnt)
            }
            
            System.out.println('This is the value of fusion count obtained from input test data :' + ipfusion)

            WebUI.verifyMatch(ipfusion, webfusionCnt, false)

            break
        case 'false':
		//When the tab is disabled  - which means the absence of 'disabled' attribute is false
       
            System.out.println('fusion Tab is not clickable. There is no data present for the tab.')

            webfusionCnt = '0'

            System.out.println('This is the value of fusion count obtained from input test data :' + ipfusion)

            WebUI.verifyMatch(ipfusion, webfusionCnt, false)

            break
        default:
            System.out.println('Error in accessing fusion tab')

            break //if condition ends for somatic alterations widget
    }
} else {
    System.out.println('Data is not available for Somatic Alterations widget.')

    webSomaticAlt = 'FALSE'

    System.out.println('This is the value of Somatic Alterations obtained from input test data :' + ipSomaticAlt)

    WebUI.verifyMatch(ipSomaticAlt, webSomaticAlt, false)
}


//Step 10--------------------Verifying opc GeneExp widget ****************************************************************
webGeneExpWdgt = WebUI.verifyElementPresent(findTestObject('Object Repository/MTP/TargetProfilePage/GeneExp_Wdgt'), 5)

System.out.println('This is the status of Gene Expression widget obtained from UI :' + webGeneExpWdgt)

webGeneExpWdgtTxt = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/GeneExp_Wdgt'))

System.out.println('This is the text of Gene Expression widget obtained from UI :' + webGeneExpWdgtTxt)

if ((webGeneExpWdgt == true) && (webGeneExpWdgtTxt == 'Available')) {
    System.out.println('Gene Expression widget is clickable. Data is available')

    WebUI.focus(findTestObject('Object Repository/MTP/TargetProfilePage/GeneExpression'), FailureHandling.OPTIONAL)

    //CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.scrolltoViewjs'('Object Repository/MTP/TargetProfilePage/GeneExpression')
    System.out.println('****Scrolled to Gene Expression widget****** ')

    WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/GeneExpression'))

    Thread.sleep(2000)

    webGeneExp = 'TRUE'

    System.out.println('This is the value of Gene Expression obtained from input test data :' + ipGeneExp)

    WebUI.verifyMatch(ipGeneExp, webGeneExp, false //if condition ends for gene expression widget
        )
} else {
    System.out.println('Data is not available for Gene Expression widget.')

    webGeneExp = 'FALSE'

    System.out.println('This is the value of Gene Expression obtained from input test data :' + ipGeneExp)

    WebUI.verifyMatch(ipGeneExp, webGeneExp, false)
}


//Step 11--------------------Verifying Differential Expression widget ****************************************************************
 webDiffExpWdgt = WebUI.verifyElementPresent(findTestObject('Object Repository/MTP/TargetProfilePage/diffExp_Wdgt'), 100)

System.out.println('This is the status of Differential Expression widget obtained from UI :' + webDiffExpWdgt)

webDiffExpWdgtTxt = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/diffExp_Wdgt'))

System.out.println('This is the text of Differential Expression widget obtained from UI :' + webDiffExpWdgtTxt)

if ((webDiffExpWdgt == true) && (webDiffExpWdgtTxt == 'Available')) {
	
	
    System.out.println('Differential Expression widget is clickable. Data is available')

    WebUI.focus(findTestObject('Object Repository/MTP/TargetProfilePage/DiffExpression'), FailureHandling.OPTIONAL)

     System.out.println('****Scrolled to Differential Expression widget****** ')
    WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/DiffExpression'))

    Thread.sleep(2000)

    webDiffExp = 'TRUE'

    System.out.println('This is the value of Differential Expression obtained from input test data :' + ipDiffExp)

    WebUI.verifyMatch(ipDiffExp, webDiffExp, false //if condition ends for gene expression widget
        )
} else {
	webDiffExp = 'FALSE'
    System.out.println('Data is not available for Differential Expression widget.')
	WebUI.verifyMatch(ipDiffExp, webDiffExp, false)//if condition ends for gene expression widget
	
}


//Step 12--------------------Verifying Epigenetic Modification widget ****************************************************************
webEpiModWdgt = WebUI.verifyElementPresent(findTestObject('Object Repository/MTP/TargetProfilePage/epiModif_Wdgt'), 5)
System.out.println('This is the status of Epigenetic Modification widget obtained from UI :' + webEpiModWdgt)

WebUI.waitForElementVisible(findTestObject('Object Repository/MTP/TargetProfilePage/epiModif_Wdgt'), 20)
webEpiModWdgtTxt = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/epiModif_Wdgt'))
System.out.println('This is the text of Epigenetic Modification widget obtained from UI :' + webEpiModWdgtTxt)

if ((webEpiModWdgt == true) && (webEpiModWdgtTxt == 'Available')) {
	System.out.println('Epigenetic Modification widget is clickable. Data is available')
	WebUI.focus(findTestObject('Object Repository/MTP/TargetProfilePage/EpiMod'), FailureHandling.OPTIONAL)
	System.out.println('****Scrolled to Epigenetic Modification widget****** ')
	WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/EpiMod'))
	Thread.sleep(2000)
	webEpiMod = 'TRUE'
	System.out.println('This is the value of Epigenetic Modification obtained from input test data :' + ipEpiMod)
	WebUI.verifyMatch(ipEpiMod, webEpiMod, false)

	
	
	//Step 13--------------------Verifying Methylation by Gene table ****************************************************************
	WebUI.focus(findTestObject('Object Repository/MTP/TargetProfilePage/methByGene_Tab'), FailureHandling.OPTIONAL)
	
		 System.out.println('****Scrolled to Epigenetic Modification - Meth by gene tab****** ')
		 boolean webmethByGeneTab = WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/MTP/TargetProfilePage/methByGene_Tab'),
		'disabled', 10, FailureHandling.OPTIONAL)

	System.out.println('This is the value of the absence of disabled attribute for methByGene tab : ' + webmethByGeneTab)
	 switch (webmethByGeneTab) {
		case 'true':
		//When the tab is enabled - which means the absence of 'disabled' attribute is true
		WebUI.focus(findTestObject('Object Repository/MTP/TargetProfilePage/methByGene_Tab'), FailureHandling.OPTIONAL)
			WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/methByGene_Tab'))
			Thread.sleep(3000)

			boolean webPaginationMG = WebUI.verifyElementNotPresent(findTestObject('Object Repository/MTP/TargetProfilePage/methByGene_pagination'),
				5, FailureHandling.OPTIONAL)
			System.out.println('This is the value of pagination absence for meth by gene tab : ' + webPaginationMG)

			if (webPaginationMG == true) {
				//if the pagination absence is true when there are <=10 rows only, then count the rows manually
				webmethByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.countRows'('Object Repository/MTP/TargetProfilePage/EpiMod_methByGene_TblBdy')
				System.out.println('This is the value of meth by gene count obtained from UI :' + webmethByGeneCnt )
			} else if (webPaginationMG == false) { 
				//if the pagination is present-when there are >10 rows, then get the total count from the rows per page max number	
				tempCount = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/methByGene_Cnt'))
				System.out.println('This is the value of tempcount before extracting the rowcount :' + tempCount)
				webmethByGeneCnt = CustomKeywords.'ctdc.utilities.DataValidation.CountRowsfromPagination'(tempCount)
				System.out.println('This is the value of meth by gene count extracted from pagination in UI :' + webmethByGeneCnt)
			}
			
			System.out.println('This is the value of meth by gene count obtained from input test data :' + ipmethylByGene)
			WebUI.verifyMatch(ipmethylByGene, webmethByGeneCnt, false)
			break
		case 'false':
		//When the tab is disabled  - which means the absence of 'disabled' attribute is false
			System.out.println('meth By Gene Tab is not clickable. There is no data present for the tab.')
			webmethByGeneCnt = '0'
			System.out.println('This is the value of meth by gene count obtained from input test data :' + ipmethylByGene)
			WebUI.verifyMatch(ipmethylByGene, webmethByGeneCnt, false)
			break
		default:
			System.out.println('Error in accessing meth By Gene tab')
			break
	}//meth by gene switch ends here
	
/*
		//Step 14--------------------Verifying Methylation by Isoform table ****************************************************************

	
	WebUI.focus(findTestObject('Object Repository/MTP/TargetProfilePage/methByIso_Tab'), FailureHandling.OPTIONAL)
	System.out.println('****Scrolled to Epigenetic Modification - Meth by Isoform  tab****** ')
	boolean webmethByIsoTab = WebUI.verifyElementNotHasAttribute(findTestObject('Object Repository/MTP/TargetProfilePage/methByIso_Tab'),'disabled', 10, FailureHandling.OPTIONAL) 
 
	System.out.println('This is the value of the absence of disabled attribute for methbyIsoform tab : ' + webmethByIsoTab)
	switch (webmethByIsoTab) {
		case 'true':
		//When the tab is enabled - which means the absence of 'disabled' attribute is true
		   WebUI.focus(findTestObject('Object Repository/MTP/TargetProfilePage/methByIso_Tab'), FailureHandling.OPTIONAL)
			WebUI.click(findTestObject('Object Repository/MTP/TargetProfilePage/methByIso_Tab'))
			Thread.sleep(3000)

			boolean webPaginationMI = WebUI.verifyElementNotPresent(findTestObject('Object Repository/MTP/TargetProfilePage/methByIso_pagination'),
				5, FailureHandling.OPTIONAL)
			System.out.println('This is the value of pagination absence for meth by gene tab : ' + webPaginationMI)

			if (webPaginationMI == true) {
				//if the pagination absence is true when there are <=10 rows only, then count the rows manually
				webmethByIsoCnt = CustomKeywords.'ctdc.utilities.DataValidation.countRows'('Object Repository/MTP/TargetProfilePage/EpiMod_methByIso_TblBdy')
				System.out.println('This is the value of meth by Isoform count obtained from UI :' + webmethByIsoCnt )
			} else if (webPaginationMI == false) {
				//if the pagination is present-when there are >10 rows, then get the total count from the rows per page max number
				tempCount = WebUI.getText(findTestObject('Object Repository/MTP/TargetProfilePage/methByIso_Cnt'))
				System.out.println('This is the value of tempcount before extracting the rowcount :' + tempCount)
				webmethByIsoCnt = CustomKeywords.'ctdc.utilities.DataValidation.CountRowsfromPagination'(tempCount)
				System.out.println('This is the value of meth by Iso count extracted from pagination in UI :' + webmethByIsoCnt)
			}
			
			System.out.println('This is the value of meth by Iso count obtained from input test data :' + ipmethylByIso)
			WebUI.verifyMatch(ipmethylByIso, webmethByIsoCnt, false)
			break
		case 'false':
		//When the tab is disabled  - which means the absence of 'disabled' attribute is false
			System.out.println('meth By Isoform Tab is not clickable. There is no data present for the tab.')
			webmethByIsoCnt = '0'
			System.out.println('This is the value of meth by gene count obtained from input test data :' + ipmethylByIso)
			WebUI.verifyMatch(ipmethylByIso, webmethByIsoCnt, false)
			break
		default:
			System.out.println('Error in accessing meth By Isoform tab')
			break
	}//meth by gene switch ends here
	
	*/
} else {
    System.out.println('Data is not available for Epigenetic Modification widget.')
    webEpiMod = 'FALSE'
    System.out.println('This is the value of Epigenetic Modification obtained from input test data :' + ipEpiMod)
    WebUI.verifyMatch(ipEpiMod, webEpiMod, false)
}



WebUI.closeBrowser()

