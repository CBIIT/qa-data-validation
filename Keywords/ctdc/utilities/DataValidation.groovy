package ctdc.utilities

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.util.Iterator;
import java.util.Set;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.Keys;
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.HashMap;
import java.util.Map;
//import java.util.Iterator;
//import java.util.Set;
import org.apache.commons.io.FileUtils;
import internal.GlobalVariable

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;

import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Cookie as Cookie
import ctdc.utilities.runtestcaseforKatalon as webUIHelper


public class DataValidation extends runtestcaseforKatalon{

	public static WebDriver driver


	@Keyword
	public static initDriver() {
		driver = CustomBrowserDriver.createWebDriver();
		System.out.println("This is the driver from inside the Data Validation keyword : "+driver)
		driver.get(GlobalVariable.fullUrl)
		driver.manage().window().maximize()
		System.out.println("The window is maximized")
		//passDriver()
	}

	@Keyword
	public static WebDriver passDriver() {
		driver = CustomBrowserDriver.createWebDriver();
		System.out.println("This is the driver from inside the Data Validation keyword : "+driver)
		driver.get(GlobalVariable.fullUrl)
		driver.manage().window().maximize()
		System.out.println("The window is maximized")
		return driver;
	}

	@Keyword
	public static passDriver(WebDriver dr) {
		System.out.println("Driver is ready to be passed"+dr)
	}

	@Keyword
	public static String countRows(String tblbdy) {

		String xpTblbdy =  webUIHelper.givexpath(tblbdy);

		System.out.println("This is the value of xptblbody : "+xpTblbdy)
		WebElement TableBdy= driver.findElement(By.xpath(xpTblbdy))

		List<WebElement> tableRows = TableBdy.findElements(By.tagName("tr"))
		System.out.println("This is the value of weblement for table rows :"+tableRows);

		int rows_count = tableRows.size()
		System.out.println("This is the size of the rows in the results table that doesnt have rows per page listed: "+(rows_count))
		String totalRows = rows_count.toString();
		System.out.println("This is the size of the rows in the results table that doesnt have rows per page listed -after converting to string: "+(totalRows))
		return totalRows;
	}


	@Keyword
	//This gives the substring from the pagination to find the total records in the table
	public static String CountRowsfromPagination (String pgntn) {
		String[] segments = pgntn.split(" ");
		String rowCnt = segments[segments.length-1]
		System.out.println ("This is the value of the total rows extracted from pagination : "+rowCnt)
		return rowCnt
	}


	@Keyword
	public static Boolean isObjPresent(String objID) {
		Boolean retnVal;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rawobjID = objID
		String objXpath = webUIHelper.givexpath(objID)
		System.out.println("This is the value of xpath of the element:"+objXpath);
		if((driver.findElement(By.Xpath(objXpath))==true)){
			retnVal = true
		}else {
			retnVal = false
		}
		return retnVal;
	}


	@Keyword
	public static Boolean isObjClickablet(String objID) {
		Boolean retnVal;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rawobjID = objID
		String objXpath = webUIHelper.givexpath(objID)
		System.out.println("This is the value of xpath of the element:"+objXpath);
		if((driver.findElement(By.Xpath(objXpath))==true)){
			retnVal = true
		}else {
			retnVal = false
		}
		return retnVal;
	}

	@Keyword
	public static CCDCreadInfo(WebDriver driver, String webElem, String ipElem, String globalV, String ElemLabel) throws IOException {

		WebDriverWait wait = new WebDriverWait(driver,30);

		//		driver = CustomBrowserDriver.createWebDriver();
		//		System.out.println("This is urlname: "+GlobalVariable.fullUrl)
		//		driver.get(GlobalVariable.fullUrl)
		//		driver.manage().window().maximize()
		//		System.out.println("The window is maximized")
		//

		//System.out.println ("Above the givexpath check")
		String xp = givexpath(webElem);

		JavascriptExecutor js = (JavascriptExecutor)driver;

		//System.out.println ("Above the isdisplayed check")
		//	boolean elemPresent = driver.findElement(By.xpath(xp)).isDisplayed();
		int elemPresent = driver.findElements(By.xpath(xp)).size()
		System.out.println("This is the value of elementpresent counter : "+elemPresent)

		if (elemPresent>0) {
			WebElement elem = driver.findElement(By.xpath(xp))
		
			js.executeScript("arguments[0].scrollIntoView(true);", elem);
			Thread.sleep(500)

			//scrolltoViewjs(driver.findElement(By.xpath(xp)))
			//Thread.sleep(2000) //added for Jenkins
			String webElemTxt = elem.getText();
			System.out.println ("This is the value of "+ ElemLabel + " Text obtained from UI :" + webElemTxt)
			//globalV=ipElem.toString();
			System.out.println ("This is the value of " + ElemLabel +" stored as global variable :" + globalV)
			(webElemTxt.contentEquals(globalV)) ? KeywordUtil.markPassed(ElemLabel+" matches"): KeywordUtil.markFailed("Mismatch in "+ElemLabel)
		}else {
			System.out.println ("******************"+ElemLabel+" is not available for this dataset. The count returned by the size function is : "+elemPresent +"****************")

		}


	}
}
