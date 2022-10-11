package ctdc.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.apache.poi.xssf.usermodel.XSSFCell
import org.openqa.selenium.WebDriver

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class DataValidation {
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


	/**
	 * This function reads the new excel file name from InputFiles
	 * @param input_file
	 */
	@Keyword
	public  void readInputFile(String input_file) {

		Path file_input = Paths.get(System.getProperty("user.dir"), "InputFiles", input_file);
		if ( file_input !=null) {
			KeywordUtil.markPassed("Test case file loaded " + "This is the full filepath after converting to string :"+file_input.toString())
			GlobalVariable.G_input_file=file_input.toString()
		}
		else{
			KeywordUtil.markPassed ("Password File is not found" )
		}


		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.G_input_file )
		Thread.sleep(2000)
		List<List<XSSFCell>> sheetData_K = new ArrayList<>();
		FileInputStream fis = new FileInputStream(GlobalVariable.G_input_file);
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
		System.out.println("This is the driver from inside the runkatalon method : "+driver)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		excelparsingKatalon(sheetData_K,driver);
		System.out.println("This is the value of sheetdata array from runkatalon function : "+sheetData_K)

	}
	//******************* Read Katalon Function Ends Here ***************************



	private static void DVexcelparsing(List<List<XSSFCell>> sheetData, WebDriver dr) {
		String fullUrl;
		System.out.println("This is the value of browser driver from DVexcelparsing: "+dr)
		//		System.out.println("This is base urlname: "+GlobalVariable.baseUrl)
		//		System.out.println("This is the suffix urlname: ")  // add the suffix url from the excel here
		//		//concatenate the suffix url with the base url here & store it as Url
		driver.get(GlobalVariable.G_Url)
		driver.manage().window().maximize()
		System.out.println("The window is maximized")
		Thread.sleep(3000)
		//---------------------------------------------move the above lines to after reading the excel so that the concatenated url can be opened


		int countrow = 0
		countrow = sheetData.size();
		System.out.println ( "row count from initializing fnc: " + countrow )
		System.out.println ( "sheet  data size: " + sheetData.get(0).size())

		//Loop through rows
		for (int i = 1; i < countrow; i++){
			List<XSSFCell> datarow = sheetData.get(i);
			System.out.println (" Columns size from initializing fnc:  " + datarow.size())
			String str = "";
			//loop through columns
			for (int j = 0; j < datarow.size(); j++){
				System.out.println ("value of  i : "  + i + "  Value of j  : " + j )
				XSSFCell cell = datarow.get(j);
				//Look for specific column names to perform action
				switch(sheetData.get(0).get(j).getStringCellValue().trim() ) // change to i
				{

					case ("suffixUrl"):
						GlobalVariable.suffixUrl= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of suffix url from excel stored in global variable: "+GlobalVariable.suffixUrl)
						break;
					case ("targetID"):
						GlobalVariable.targetID= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of target id from excel stored in global variable: "+GlobalVariable.targetID)
						break;
					case ("targetName"):
						GlobalVariable.targetID= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of target id from excel stored in global variable: "+GlobalVariable.targetName)
						break;
					case ("PMTL"):
						GlobalVariable.targetID= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of target id from excel stored in global variable: "+GlobalVariable.PMTL)
						break;
					case ("assocCount"):
						GlobalVariable.targetID= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of target id from excel stored in global variable: "+GlobalVariable.assocCount)
						break;
					case("outputFilename"):
						GlobalVariable.G_WebExcel = sheetData.get(i).get(j).getStringCellValue()
						GlobalVariable.G_OutputFileName = GlobalVariable.G_WebExcel
						System.out.println("This is the value of gwebexcel before appending with directory :"+GlobalVariable.G_WebExcel)
						System.out.println("This is the value of output filename stored in a global var :"+GlobalVariable.G_OutputFileName)

						Path outputDir = Paths.get(System.getProperty("user.dir"), "OutputFiles")
						GlobalVariable.G_OutputDir =outputDir.toString()
						System.out.println("This is the path till the output directory : "+GlobalVariable.G_OutputDir)

						Path filepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_WebExcel)
						GlobalVariable.G_WebExcel=filepath.toString()
						System.out.println("This is the full path stored in global variable gwebexcel: "+GlobalVariable.G_WebExcel)
						break;
					//					case("dbExcel"):
					//						GlobalVariable.G_dbexcel = sheetData.get(i).get(j).getStringCellValue()
					//						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
					//						GlobalVariable.G_ResultPath=dbfilepath.toString()
					//						break;
					default :
						System.out.println("Error in initializing")
						break;
				}// Switch case ends here

				System.out.println("This is base urlname: "+GlobalVariable.baseUrl)
				System.out.println("This is the suffix urlname: "+GlobalVariable.suffixUrl)
				fullUrl//concatenate the suffix url with the base url here & store it as Url

				str =str+ cell.getStringCellValue() + "||"
			}//for loop j ends (column read)
		}//for loop i ends (row read)
	} //excelparsingKatalon function ends here
}
