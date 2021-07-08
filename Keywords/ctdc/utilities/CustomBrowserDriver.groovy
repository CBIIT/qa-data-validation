package ctdc.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory


import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeOptions

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import java.nio.file.Paths
import java.nio.file.Path

import internal.GlobalVariable

import java.io.File;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CustomBrowserDriver {



	public static String getExecutedBrowser() {
		String exBrowser = DriverFactory.getExecutedBrowser().getName()
		GlobalVariable.execBrowser = exBrowser;
		System.out.println("This is the value of executed browser : "+GlobalVariable.execBrowser);
		return GlobalVariable.execBrowser;
	}


	@Keyword
	public static WebDriver createWebDriver() {

		WebDriver drv
		Path manifestDir = Paths.get(System.getProperty("user.dir"), "OutputFiles")
		GlobalVariable.manifestPath = manifestDir.toString()
		System.out.println("This is the path till the output directory of manifest files : "+GlobalVariable.manifestPath)

		String manifestPath = "C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\"  //the double slash at the end is required

		switch (getExecutedBrowser()) {
			case 'FIREFOX_DRIVER':
				System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverPath())
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("manifestICDC");

			//FirefoxProfile myprofile = new FirefoxProfile();
				myprofile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
				myprofile.setPreference("browser.download.folderList",2);  // 0 for Desktop, 1 for Downloads folder, 2 for specified folder
				myprofile.setPreference("browser.download.manager.showWhenStarting",false);
				myprofile.setPreference("browser.download.dir","C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\");
				FirefoxOptions opt = new FirefoxOptions();
				opt.setProfile(myprofile);
				drv  =  new FirefoxDriver(opt);
				DriverFactory.changeWebDriver(drv)
				System.out.println("This is the value of dr from createwebdriver : "+drv)

			//			FirefoxProfile profile = new FirefoxProfile();
			//			profile.setPreference("browser.download.dir","C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles");
			//			profile.setPreference("browser.download.folderList", 2);
			//			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
			//			profile.setPreference( "browser.download.manager.showWhenStarting", false );
			//
			//			 dr = new FirefoxDriver(profile);
				break;
			case 'CHROME_DRIVER':
				System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
				ChromeOptions options = new ChromeOptions()
				Map<String, Object> chromePrefs = new HashMap<String, Object>()
				chromePrefs.put("download.default_directory", manifestPath)
				chromePrefs.put("download.prompt_for_download", false)
				options.setExperimentalOption("prefs", chromePrefs)
			//				if (browser.open)() {
				if(DriverFactory.getCurrentWindowIndex()) {
				System.out.println("A browser instance is already open.")
				System.out.println("A browser instance is already open. Quitting the browser")
				WebUI.closeBrowser()	//					close the browser if it is opened already
				}
				drv  = new ChromeDriver(options)
				DriverFactory.changeWebDriver(drv)
				System.out.println("This is the value of dr from createwebdriver : "+drv)
				break;

			case 'IE_DRIVER':
				String ieDriverPath = DriverFactory.getIEDriverPath()
				WebUI.comment(">>> ieDriverPath=${ieDriverPath}")
				System.setProperty("webdriver.ie.driver", ieDriverPath)
				drv  = new InternetExplorerDriver()
				DriverFactory.changeWebDriver(drv)
				System.out.println("This is the value of dr from createwebdriver : "+drv)
				break;

			case 'EDGE_DRIVER':
				String edgeDriverPath = DriverFactory.getEdgeDriverPath()
				WebUI.comment(">>> edgeDriverPath=${edgeDriverPath}")
				System.setProperty("webdriver.edge.driver", edgeDriverPath)
			// you can insert code for browser customization here --- TODO
				drv  = new EdgeDriver()
				DriverFactory.changeWebDriver(drv)
				System.out.println("This is the value of dr from createwebdriver : "+drv)
				break;
			case 'HEADLESS_DRIVER':  //this is for headless chrome driver
			//open headless in fullsize
				System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setCapability(ChromeOptions.CAPABILITY, options);
			//options.merge(dc);

				Map<String, Object> chromePrefs = new HashMap<String, Object>()
				chromePrefs.put("download.default_directory", manifestPath)
				chromePrefs.put("download.prompt_for_download", false)
				options.setExperimentalOption("prefs", chromePrefs)
				options.merge(dc);
				
				if(DriverFactory.getCurrentWindowIndex()) {
					System.out.println("A browser instance is already open.")
					System.out.println("A browser instance is already open. Quitting the browser")
					WebUI.closeBrowser()	//					close the browser if it is opened already
					}
				drv  = new ChromeDriver(options)
				DriverFactory.changeWebDriver(drv)
				System.out.println("This is the value of dr from createwebdriver : "+drv)
				break;

			case 'FIREFOX_HEADLESS_DRIVER':
				System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverPath())
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("manifestICDC");
				myprofile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
				myprofile.setPreference("browser.download.folderList",2);  // 0 for Desktop, 1 for Downloads folder, 2 for specified folder
				myprofile.setPreference("browser.download.manager.showWhenStarting",false);
				myprofile.setPreference("browser.download.dir","C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\");
				FirefoxOptions ffoptions = new FirefoxOptions();
				ffoptions.setProfile(myprofile);
				DesiredCapabilities desiredCap = DesiredCapabilities.firefox();
				desiredCap.setCapability("headless", true);
				ffoptions.addArguments("--headless");
				ffoptions.merge(desiredCap);
				drv = new FirefoxDriver(ffoptions);
				DriverFactory.changeWebDriver(drv)
				System.out.println("This is the value of dr from createwebdriver : "+drv)
				break;

			default:
				throw new IllegalStateException("unsupported browser type: ${executedBrowser}")

		}
		return drv
	}


	//for headless chrome browser

	@Keyword
	public static void chromeHeadless() {
		System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(dc);

		ChromeDriver driver = new ChromeDriver(options);
		driver.get("http://demo.guru99.com/");
		driver.manage().window().maximize();
		String BrDriverName = DriverFactory.getExecutedBrowser().getName()
		System.out.println("**********************browser driver name is : "+BrDriverName)
		String title = driver.getTitle();
		System.out.println("Page Title: " +title);
		driver.quit();
		//HEADLESS_DRIVER
	}

	@Keyword
	public static void firefoxHeadless(){
		System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverPath())
		DesiredCapabilities desiredCap = DesiredCapabilities.firefox();
		desiredCap.setCapability("headless", true);
		FirefoxOptions ffoptions = new FirefoxOptions();
		ffoptions.addArguments("--headless");
		ffoptions.merge(desiredCap);

		FirefoxDriver driver = new FirefoxDriver(ffoptions);
		driver.get("http://demo.guru99.com/");
		driver.manage().window().maximize();
		String BrDriverName = DriverFactory.getExecutedBrowser().getName()
		System.out.println("**********************browser driver name is : "+BrDriverName)
		String title = driver.getTitle();
		System.out.println("Page Title: " +title);
		driver.quit();
		//FIREFOX_HEADLESS_DRIVER
	}
}
