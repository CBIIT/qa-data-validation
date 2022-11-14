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
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import internal.GlobalVariable

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Cookie as Cookie

public class DataValidation {
	
	public static WebDriver driver
	

	
	@Keyword
	public void countRows(String tblbdy) throws IOException {
		
		String xpTblbdy = runtestcaseforKatalon.givexpath(tblbdy)
		
		driver = CustomBrowserDriver.createWebDriver();
		System.out.println("This is the driver from inside the Data Validation keyword : "+driver)
		
 
		findTestObject('Object Repository/MTP/TargetProfilePage/SomaticAlt_TblBdy')
		
		WebElement TableBdy =driver.findElement(By.xpath(xpTblbdy))
		List<WebElement> tableRows = TableBdy.findElements(By.tagName("tr"))
		System.out.println("This is the value of weblement for table rows :"+tableRows);

		int rows_count = tableRows.size()
		System.out.println("This is the size of the rows in the results table that doesnt have rows per page listed: "+(rows_count))
	 
	}
	
}
