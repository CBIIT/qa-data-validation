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
public class DataValidation implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}
	/* POC for Target Assoc
	 * 1. read the input excel tab and store data as needed
	 * 2. append appl url + page url and the launch the page
	 * 3. read the web and copy it to a webdata excel for the required data: 
	 *  -target id
	 *  - target name
	 *  - pmtl
	 *  - assoc counts
	 *  print the xl values, ui values 
	 * 4. use xl comparator to compare the values and print the result
	 *     
	 */
	public static WebDriver driver


 
	@Keyword
	public  void readInputFile(String input_file) {

		Path file_input = Paths.get(System.getProperty("user.dir"), "InputFiles", input_file);
		if ( file_input !=null) {
			KeywordUtil.markPassed("Test case file loaded " + "This is the full filepath after converting to string :"+file_input.toString())
			GlobalVariable.InputExcel=file_input.toString()
		}
		else{
			KeywordUtil.markPassed ("Password File is not found" )
		}


		KeywordUtil.logInfo("Global variable set for input file is :  " + GlobalVariable.InputExcel )
		Thread.sleep(2000)
		List<List<XSSFCell>> sheetData_K = new ArrayList<>();
		FileInputStream fis = new FileInputStream(GlobalVariable.InputExcel);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
		int numberOfSheets = workbook.getNumberOfSheets();// Get the  sheets on the workbook
		int countrow = 0
		int countcol= 0
		Thread.sleep(2000)
		XSSFSheet sheet = workbook.getSheetAt(0);  //reading input query
		countrow = sheet.lastRowNum- sheet.firstRowNum;
		System.out.println ( "Row count is  : " + countrow);
		countcol = sheet.getRow(0).getLastCellNum();
		System.out.println("Col count is : " + countcol);

		//This loops through the rows of the table till there is next row
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			List<XSSFCell> data = new ArrayList<>();
			while (cells.hasNext()) {
				XSSFCell cell = (XSSFCell) cells.next();
				data.add(cell);
			}
			sheetData_K.add(data);
		}

		KeywordUtil.markPassed("Data loaded from input file for the test case. " )
		driver = CustomBrowserDriver.createWebDriver();
		System.out.println("This is the driver from inside the readinputfile method : "+driver)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		DVexcelparsing(sheetData_K,driver);
		System.out.println("This is the value of sheetdata array from readinputfile function : "+sheetData_K)

	}
	//******************* readInputFile Function Ends Here ***************************



	private static void DVexcelparsing(List<List<XSSFCell>> sheetData, WebDriver dr) {
		String Url ;
		int assocCnt;
		System.out.println("This is the value of browser driver from DVexcelparsing: "+dr)
		
	 
		int countrow = 0
		countrow = sheetData.size();
		System.out.println ( "row count from initializing fnc: " + countrow )
		System.out.println ( "sheet  data size: " + sheetData.get(0).size())

		//Loop through rows  //how to parametrize the rows and make it run through each row recursively
		for (int i = 1; i < countrow; i++){
			List<XSSFCell> datarow = sheetData.get(i);
			System.out.println (" Columns size from initializing fnc:  " + datarow.size())
			String str = "";
			
			//loop through columns
			for (int j = 0; j < datarow.size(); j++){
				System.out.println ("value of  i : "  + i + "  Value of j  : " + j )
				XSSFCell cell = datarow.get(j);
				//Look for specific column names to perform action
				switch(sheetData.get(0).get(j).getStringCellValue().trim() ) // 
				{
/*
					case ("pageName"):
						GlobalVariable.UIpageName= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of the page name from excel stored in global variable: "+GlobalVariable.UIpageName)
						break;
	*/
					case ("suffixUrl"):
						GlobalVariable.suffixUrl= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of suffix url from excel stored in global variable: "+GlobalVariable.suffixUrl)
						System.out.println("This is base url: "+GlobalVariable.baseUrl)
						System.out.println("This is the suffix url: "+GlobalVariable.suffixUrl)
						
						 Url = GlobalVariable.baseUrl + GlobalVariable.suffixUrl
						 GlobalVariable.fullUrl = Url;
						 System.out.println("This is the full url: "+GlobalVariable.fullUrl)//concatenate the suffix url with the base url here & store it as Url
						 
						driver.get(GlobalVariable.fullUrl)
						driver.manage().window().maximize()
						System.out.println("The window is maximized")
						Thread.sleep(3000)
						break;
/*					case ("targetID"):
						GlobalVariable.targID= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of target id from excel stored in global variable: "+GlobalVariable.targID)
						break;
					case ("targetName"):
						GlobalVariable.targetName= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of target name from excel stored in global variable: "+GlobalVariable.targetName)
						break;
					case ("PMTL"):
						GlobalVariable.PMTL= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of PMTL from excel stored in global variable: "+GlobalVariable.PMTL)
						break;
					case ("assocCount"):
						 assocCnt= sheetData.get(i).get(j).getNumericCellValue()
						GlobalVariable.assocCount = assocCnt.toString()
						System.out.println("This is the value of assocCount from excel stored in global variable: "+GlobalVariable.assocCount)
						break;
					case("outputFilename"):
					   // System.out.println ("before")
						GlobalVariable.OutputFileName = sheetData.get(i).get(j).getStringCellValue()
						//System.out.println ("after")			
						System.out.println("This is the value of output filename stored in a global var :"+GlobalVariable.OutputFileName)
					Path filepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.OutputFileName)
						GlobalVariable.OutputExcel=filepath.toString()
						System.out.println("This is the full path of the outputfile stored in global variable : "+GlobalVariable.OutputExcel)
						break;
					//					case("dbExcel"):
					//						GlobalVariable.G_dbexcel = sheetData.get(i).get(j).getStringCellValue()
					//						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
					//						GlobalVariable.G_ResultPath=dbfilepath.toString()
					//						break;
		*/			default :
						System.out.println("Error in initializing")
						break;
				}// Switch case ends here

				
				
				//str =str+ cell.getStringCellValue() + "||"
			}//for loop j ends (column read)
		}//for loop i ends (row read)
	} //DVexcelparsing function ends here
	
	
	@Keyword
	public static void multiFunction( String ValPageName, String inpSheetName, String webdataSheetName, String opSheetName) throws IOException {
	 
		 
	 
			readUIData(ValPageName,inpSheetName)
			
			writeData(ValPageName, opSheetName)
			
			
			compareLists(inpSheetName, opSheetName)
			//validateData(pageName)
		 
	}

	
	@Keyword
	public static void readUIData (String pgName, String webShName) throws IOException{
		List<String> excelHdrData = new ArrayList<String>(); //to capture the table header data
		List<String> excelRowData = new ArrayList<String>(); //to capture the table body data
		
		
		if(GlobalVariable.UIpageName=='targetAssociations') {
			
		}else if(GlobalVariable.UIpageName=='targetProfile') {
			
		}else {
			System.out.println("Page name is not matching with the available options")
		}
	}//readUIData ends here
	
	@Keyword
	public static void writeData (String pgName, String opShName) throws IOException{
		if(GlobalVariable.UIpageName=='targetAssociations') {
		
	}else if(GlobalVariable.UIpageName=='targetProfile') {
		
	}else {
		System.out.println("Page name is not matching with the available options")
	}
	
	} // writeData ends here
	
	@Keyword
	public static void compareLists (String inpShName, String opShName) throws IOException{
	}//compareLists ends here
	
}
