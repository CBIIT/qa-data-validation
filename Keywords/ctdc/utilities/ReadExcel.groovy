package ctdc.utilities
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords


import com.kms.katalon.core.annotation.Keyword

import internal.GlobalVariable
public class ReadExcel {


	@Keyword
	public static List<List<XSSFCell>> Test(String filename) {
		//added String filename

		List<List<XSSFCell>> sheetData = new ArrayList<>();  // Create a 2dimensional ArrayList to store the data read from excel sheet
		FileInputStream fis = new FileInputStream(filename);  //removed filepath.toString()
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
		XSSFSheet sheet = workbook.getSheetAt(0);  // Get the first sheet on the workbook from read results data from UI / Neo4j data
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			List<XSSFCell> data = new ArrayList<>();
			while (cells.hasNext()) {
				XSSFCell cell = (XSSFCell) cells.next();
				data.add(cell);
			}
			sheetData.add(data);
		}
		System.out.println(sheetData)
		System.out.println("This is the size of the data from ui results: "+sheetData.size())
		//showExcelData1(sheetData);
		return sheetData
	}



	//************************************************************************************
	//// this is the current function
	/*
	 @Keyword
	 public static List<List<XSSFCell>> readExceltoWeblist(String filename) {
	 //	public static List<List<XSSFCell>> readExceltoWeblist(String filename, String sheetName ) {
	 System.out.println('Filename is '+ filename)
	 List<List<XSSFCell>> allValues = new ArrayList<>();
	 FileInputStream fis = new FileInputStream(filename);  //removed filepath.toString()
	 XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
	 //reads from the specified tabname of the xl:
	 //XSSFSheet sheet = workbook.getSheetAt(sheetName);  // Get the specific sheet on the workbook		
	 XSSFSheet sheet = workbook.getSheetAt(0);  // Get the first sheet on the workbook from read results data from UI / Neo4j data
	 int rowSize = sheet.size()
	 int colSize = sheet.getRow(0).size()
	 System.out.println("Row size is: "+ rowSize + " Col size is: " + colSize )
	 for(int i = 1; i < rowSize; i++ ){
	 List<XSSFCell> currList = new ArrayList()
	 //System.out.println( "Val of first col is: " + sheet.getRow(i).getCell(0) )
	 int j = 0;
	 while( j < sheet.getRow(i).size() ){
	 currList.add( sheet.getRow(i).getCell(j) )
	 j++
	 }
	 while( j < colSize ){
	 currList.add( "" )
	 j++
	 }
	 allValues.add(currList)
	 }
	 return allValues
	 }
	 */
	///**********************************************************
	//This is the new function


	@Keyword
	public static List<List<XSSFCell>> readExceltoWeblist(String filename, String sheetName) {
		System.out.println('Filename is '+ filename)
		System.out.println('Sheetname to be read from the file is : '+sheetName)
		List<List<XSSFCell>> allValues = new ArrayList<>();
		FileInputStream fis = new FileInputStream(filename);  //removed filepath.toString()
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.

		int numberOfSheets = workbook.getNumberOfSheets()
		for(int inx = 0; inx < numberOfSheets; inx++)  //looping thru sheets to get names
		{
			XSSFSheet sheet = workbook.getSheetAt(inx);  // Get the first sheet on the workbook from read results data from UI / Neo4j data
			if (sheet.getSheetName().equals(sheetName)) {
				int rowSize = sheet.size()
				int colSize = sheet.getRow(0).size()
				System.out.println("Row size is: "+ rowSize + " Col size is: " + colSize )
				for(int i = 1; i < rowSize; i++ ){
					List<XSSFCell> currList = new ArrayList()
					int j = 0;
					while( j<sheet.getRow(i).size() ){
						currList.add( sheet.getRow(i).getCell(j) )
						j++
					}
					while( j < colSize ){
						currList.add( "" )
						j++
					}
					allValues.add(currList)
				}
			} //if loop ends

		} // for loop ends
		return allValues
	}

	//*****************************************************************


	private static void showExcelData1(List<List<XSSFCell>> sheetData) {
		// Iterates the data and print it out to the console.
		for (List<XSSFCell> data : sheetData) {
			String str = "";
			if(data.get(0).stringCellValue.equals("CTDC-43123")){
				for (XSSFCell cell : data) {

					str =str+ cell.getStringCellValue() + "||"
				}
				System.out.println(str);

			}
		}
	}


	@Keyword
	public static void Neo4j(String dbSheetName, String tbQuery) { //specific query as parameter
		String query = tbQuery  // this is the db main results query variable
		System.out.println("This is the value of tab query from neo4j:"+query)
		String statQuery = GlobalVariable.G_StatQuery
		String myCartQuery = GlobalVariable.G_cartQuery
		String caseDetailQuery = GlobalVariable.G_CaseDetailQ  //added for case detail
		String userName= GlobalVariable.G_UserId
		String pwd= GlobalVariable.G_Password
		String output= GlobalVariable.G_ResultPath
		String neo4jServer = GlobalVariable.G_server
		String statTabName = GlobalVariable.G_StatTabname
		String cartTabName = GlobalVariable.G_CypherTabnameMyCart
		String caseDetailTabName = GlobalVariable.G_CaseDetailStatTabname  //added for case detail
		String cypherTabName = dbSheetName

		System.out.println ( "Connection data for Neo4J is  :  " +  query +   GlobalVariable.G_UserId +   GlobalVariable.G_Password +  GlobalVariable.G_ResultPath + GlobalVariable.G_server )


		System.out.println("This is the value of stat query: "+statQuery)
		System.out.println("This is the value of cart query: "+myCartQuery)
		System.out.println("This is the value of casedetail query: "+caseDetailQuery) // added for case detail
		System.out.println("This is the value of output filename: "+output)
		System.out.println("This is the value of stat TabName: "+statTabName)
		System.out.println("This is the value of cypher TabName: "+cypherTabName)

		ConnectNeo4jV4 Test1 = new ConnectNeo4jV4()

		Test1.run(neo4jServer,userName,pwd,query,output,cypherTabName)   //this is for the tab data

		Test1.run(neo4jServer,userName,pwd,statQuery,output,statTabName)  //this is for the stat bar counts

		//Test1.run(neo4jServer,userName,pwd,myCartQuery,output,cartTabName) //this is for cart table data

		//Test1.run(neo4jServer,userName,pwd,caseDetailQuery,output,caseDetailTabName) // added for case detail page's table
	}



	@Keyword
	public static  void initialLoad() {    // this reads sheet 0, predecessor for connecting to DB

		List<List<XSSFCell>> sheetData = new ArrayList<>();  // Create an ArrayList to store the data read from excel sheet
		FileInputStream fis = new FileInputStream(GlobalVariable.G_input_file);  //give GlobalVariable.G_InputExcelFileName
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int numberOfSheets = workbook.getNumberOfSheets(); 	// Get the  sheets on the workbook.
		System.out.println("Total number of sheets in the excel: "+numberOfSheets)
		System.out.println("This is the name of the sheet to be read for DB connection: "+ workbook.getSheetName(0) )
		int countrow, countcol;   //for (int s = 0 ; s< numberOfSheets; s++){
		XSSFSheet sheet = workbook.getSheetAt(0);  // Reading sheet 0 to store Neo4j DB details and path for storing data in excel from DB.

		countrow = sheet.lastRowNum - sheet.firstRowNum;
		System.out.println ( "row count is  : " + countrow); //delete
		countcol = sheet.getRow(0).getLastCellNum();
		System.out.println("Col count : " + countcol); //delete
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			List<XSSFCell> data = new ArrayList<>();
			while (cells.hasNext()) {
				XSSFCell cell = (XSSFCell) cells.next();
				data.add(cell);
			}
			sheetData.add(data);
			//workbook.close();
		}
		System.out.println(   " Before Initialing Global Variables " + workbook.getSheetName(0) )
		Initialising(sheetData);  // calling function to initialise global variables before performing DB connection
	}


	private static void Initialising(List<List<XSSFCell>> sheetData) {  //this is DB initializing
		// Iterates the data and print it out to the console.



		int countrow = 0
		countrow = sheetData.size();
		System.out.println ( "row count from initializing fnc " + countrow ) //delete
		System.out.println ( "sheet  data size :" + sheetData.get(0).size())  //delete

		for (int i = 1; i < countrow; i++){
			List<XSSFCell> datarow = sheetData.get(i);
			System.out.println (" Columns Size from initializing fnc  " : + datarow.size())
			String str = "";  //delete ?
			for (int j = 0; j < datarow.size(); j++){
				System.out.println ("value of  i :"  + i + "  Value of j  : " + j )
				XSSFCell cell = datarow.get(j);

				System.out.println ( "Header Before switch  : " + sheetData.get(0).get(j).getStringCellValue())
				System.out.println( "Data in variable : "  + sheetData.get(i).get(j).getStringCellValue())
				//--------------------
				switch(sheetData.get(0).get(j).getStringCellValue().trim() ) //First ROW
				{
					case("Browser"): //browser switch case is a separate function. refer and correct this chunk
						GlobalVariable.G_Browser = sheetData.get(i).get(j).getStringCellValue()
						break;
					case("server"):
						GlobalVariable.G_server = sheetData.get(i).get(j).getStringCellValue()
						break;
					case("user_Id"):
						GlobalVariable.G_UserId = sheetData.get(i).get(j).getStringCellValue()
						break;
					case("Password"):
						GlobalVariable.G_Password = sheetData.get(i).get(j).getStringCellValue()
						break;
					case("location_path"):
						GlobalVariable.G_ResultPath = sheetData.get(i).get(j).getStringCellValue()
						Path filepath = Paths.get(System.getProperty("user.dir"), "TestData", GlobalVariable.G_ResultPath)
						GlobalVariable.G_ResultPath=filepath.toString()
						break;
					case("Environment"):
						GlobalVariable.G_Environment = sheetData.get(i).get(j).getStringCellValue()
						break;
					case("url"):
						GlobalVariable.G_Urlname = sheetData.get(i).get(j).getStringCellValue()
						break;
					case("query"):  // this value should be stored in a query array ? and iterated in neo4j ?
						GlobalVariable.G_Query = sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the current value stored in global gquery : ":+GlobalVariable.G_Query)
						break;
					case("WebExcel"):
						GlobalVariable.G_WebExcel = sheetData.get(i).get(j).getStringCellValue()
						Path filepath = Paths.get(System.getProperty("user.dir"), "TestData", GlobalVariable.G_WebExcel)

						GlobalVariable.G_WebExcel=filepath.toString()
						break;
					default :
						System.out.println("Error in initializing")
						break;
				}
				str =str+ cell.getStringCellValue() + "||"
			}
		}
	}


	@Keyword
	public static void PrintG() {
		//System.out.println ("Action :" + GlobalVariable.G_Action)
		System.out.println ("***************** PRINTING ENVIRONMENTAL VARIALBES *****************")

		System.out.println ("Environment : " + GlobalVariable.G_Environment)
		System.out.println ( "Browser : " + GlobalVariable.G_Browser)
		System.out.println ("Server : " + GlobalVariable.G_server)
		System.out.println ("UserID :" + GlobalVariable.G_UserId)
		System.out.println (" location_path : " + GlobalVariable.G_ResultPath)
		System.out.println (" Page :  " + GlobalVariable.G_Page)
		System.out.println (" Password : " + GlobalVariable.G_Password)
		System.out.println ("****************** END PRINGING *******************")
	}



	@Keyword
	public static  ExcelToArray( String filename) {
		List<List<XSSFCell>> sheetData = new ArrayList<>();
		FileInputStream fis = new FileInputStream(filename)
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator rows = sheet.rowIterator();
		System.out.println("This is the row count from ExcelToArrayfunction : "+rows)
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			List<XSSFCell> data = new ArrayList<>();
			while (cells.hasNext()) {
				XSSFCell cell = (XSSFCell) cells.next();
				data.add(cell.toString());
			}
			sheetData.add(data);
			System.out.println data //prints row level
			System.out.println("This is a break after row level----*********")
		}

		GlobalVariable.G_DBdata=sheetData
	}





}











