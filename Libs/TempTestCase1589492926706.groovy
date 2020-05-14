import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.contribution.WebUiDriverCleaner
import com.kms.katalon.core.mobile.contribution.MobileDriverCleaner
import com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner
import com.kms.katalon.core.windows.keyword.contribution.WindowsDriverCleaner


DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.windows.keyword.contribution.WindowsDriverCleaner())


RunConfiguration.setExecutionSettingFile('C:\\Users\\RADHAK~1\\AppData\\Local\\Temp\\1\\Katalon\\Test Cases\\Trials_TestCases\\TrialCode\\TC01_Trials_Filter_TrialCode-NCIMatch\\20200514_144846\\execution.properties')

TestCaseMain.beforeStart()

        TestCaseMain.runTestCase('Test Cases/Trials_TestCases/TrialCode/TC01_Trials_Filter_TrialCode-NCIMatch', new TestCaseBinding('Test Cases/Trials_TestCases/TrialCode/TC01_Trials_Filter_TrialCode-NCIMatch',[:]), FailureHandling.STOP_ON_FAILURE , true, true)
    
