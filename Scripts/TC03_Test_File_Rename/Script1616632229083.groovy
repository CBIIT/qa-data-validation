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

//CustomKeywords.'ctdc.utilities.FileOperations.changefileName'()

String csvManifestName = "TC47_Canine_Filter_Breed-YorkshireTerr_Manifest.csv"
String csvFilePath = Paths.get(System.getProperty("user.dir"), "OutputFiles", csvManifestName)

String xlsManifestName = "TC47_Canine_Filter_Breed-YorkshireTerr_Manifest.xls"
String xlsFilePath = Paths.get(System.getProperty("user.dir"), "OutputFiles", xlsManifestName)

String xlsxManifestName = "TC47_Canine_Filter_Breed-YorkshireTerr_Manifest.xlsx"
String xlsxFilePath = Paths.get(System.getProperty("user.dir"), "OutputFiles", xlsxManifestName)


String mycartWebData = Paths.get(System.getProperty("user.dir"), "OutputFiles", "TC47_Canine_Filter_Breed-YorkshireTerr_WebData.xlsx")
GlobalVariable.G_WebExcel = mycartWebData
//GlobalVariable.G_xlsxFilename = xlsxFilePath
GlobalVariable.G_ResultPath = xlsxFilePath


//generateXLSfromCSV

System.out.println("before convert function")
//CustomKeywords.'ctdc.utilities.FileOperations.csvToEXCEL'(csvFilePath,xlsFilePath) //puts all data into single cell sep by comma in xls

CustomKeywords.'ctdc.utilities.FileOperations.generateXLSfromCSV'(csvFilePath,xlsFilePath, 'ManifestSelectedCols')  

CustomKeywords.'ctdc.utilities.FileOperations.xlsTOxlsx'(xlsFilePath, xlsxFilePath)

CustomKeywords.'ctdc.utilities.FileOperations.copySheetXLSX'(xlsxFilePath, 'BackupManifestData')

CustomKeywords.'ctdc.utilities.FileOperations.deleteCol'(xlsxFilePath)

CustomKeywords.'ctdc.utilities.runtestcaseforKatalon.compareLists'('MyCartSelectedCols', 'BackupManifestData')
 

System.out.println("after convert function")
