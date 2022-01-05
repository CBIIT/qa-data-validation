import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/ICDC_FinalRegression_FEv3.8.7-588-BE4.0.2-588_Part2_of_3')

suiteProperties.put('name', 'ICDC_FinalRegression_FEv3.8.7-588-BE4.0.2-588_Part2_of_3')

suiteProperties.put('description', 'This test suite contains 2, 5 or 10 testcases from each filter')

suiteProperties.put('stopImmediately', 'false')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.windows.keyword.contribution.WindowsDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.testng.keyword.internal.TestNGDriverCleaner())



RunConfiguration.setExecutionSettingFile("C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\Reports\\20211213_172017\\ICDC_FinalRegression_FEv3.8.7-588-BE4.0.2-588_Part2_of_3\\20211213_172017\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/ICDC_FinalRegression_FEv3.8.7-588-BE4.0.2-588_Part2_of_3', suiteProperties, new File("C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\Reports\\20211213_172017\\ICDC_FinalRegression_FEv3.8.7-588-BE4.0.2-588_Part2_of_3\\20211213_172017\\testCaseBinding"))
