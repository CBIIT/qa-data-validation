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
import org.apache.commons.io.FileUtils;
import internal.GlobalVariable
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Cookie as Cookie

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

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

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.usermodel.CellType
import java.util.Iterator
import java.util.Iterator;
import java.util.Set;

import java.nio.file.Path;
import java.nio.file.Paths;

import internal.GlobalVariable

public class CDSValidation implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}

	public static WebDriver driver

	/**
	 * This function reads the new excel file name from InputFiles
	 * @param input_file
	 */
	@Keyword
	public  void runKatalonDataValidation(String input_file) {

		String usrDir = System.getProperty("user.dir");
		String inputFiles = "InputFiles";
		Path filePath;

		filePath = Paths.get(usrDir, inputFiles, "CDS", input_file);


		if (filePath !=null) {
			KeywordUtil.markPassed("This is the full file path : "+filePath.toString())
			GlobalVariable.InputExcel=filePath.toString();
		}else{
			KeywordUtil.markFailed("Password File is not found")
		}


		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.InputExcel )
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
		excelparsingKatalon(sheetData_K);
		System.out.println("This is the value of sheetdata array from runkatalon function : "+sheetData_K)

	}

	/**
	 * This function reads input excels and assigns global variables to each query...
	 * @param sheetData
	 * @param dr
	 */
	private static void excelparsingKatalon(List<List<XSSFCell>> sheetData) {

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
				switch(sheetData.get(0).get(j).getStringCellValue().trim() )
				{
					case("TabName"):
						GlobalVariable.G_inputTabName = sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the tabname from input excel : "+GlobalVariable.G_inputTabName)
						break;
					case("query"):
						if(GlobalVariable.G_inputTabName=="CasesTab"){
							GlobalVariable.G_QueryCasesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of cases tab query from switch case : "+GlobalVariable.G_QueryCasesTab)
						}else if(GlobalVariable.G_inputTabName=="SamplesTab"){
							GlobalVariable.G_QuerySamplesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of samples tab query from switch case : "+GlobalVariable.G_QuerySamplesTab)
						}else if(GlobalVariable.G_inputTabName=="FilesTab"){
							GlobalVariable.G_QueryFilesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryFilesTab)
						}else if(GlobalVariable.G_inputTabName=="ProgramsTab"){
							GlobalVariable.G_QueryProgramsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryProgramsTab)
						}else if(GlobalVariable.G_inputTabName=="ParticipantsTab"){
							GlobalVariable.G_QueryParticipantsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Participants tab query from switch case : "+GlobalVariable.G_QueryParticipantsTab)
						}else if(GlobalVariable.G_inputTabName=="StudyFilesTab"){
							GlobalVariable.G_QueryStudyFilesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Study Files tab query from switch case : "+GlobalVariable.G_QueryStudyFilesTab)
						}else if(GlobalVariable.G_inputTabName=="SubjectsTab"){
							GlobalVariable.G_GQuerySubjectsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Study Files tab query from switch case : "+GlobalVariable.G_GQuerySubjectsTab)
						}else if(GlobalVariable.G_inputTabName=="GrantsTab"){
							GlobalVariable.G_QueryGrantsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Projects tab query from switch case : "+GlobalVariable.G_QueryGrantsTab)
						}else if(GlobalVariable.G_inputTabName=="PublicationsTab"){
							GlobalVariable.G_QueryPublicationsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Publications tab query from switch case : "+GlobalVariable.G_QueryPublicationsTab)
						}else if(GlobalVariable.G_inputTabName=="DatasetsTab"){
							GlobalVariable.G_QueryDatasetsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Datasets tab query from switch case : "+GlobalVariable.G_QueryDatasetsTab)
						}else if(GlobalVariable.G_inputTabName=="ClinicalTrialsTab"){
							GlobalVariable.G_QueryClinTrialsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Clinical Trials tab query from switch case : "+GlobalVariable.G_QueryClinTrialsTab)
						}else if(GlobalVariable.G_inputTabName=="PatentsTab"){
							GlobalVariable.G_QueryPatentsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Patemts tab query from switch case : "+GlobalVariable.G_QueryPatentsTab)
						}else if(GlobalVariable.G_inputTabName=="ArmsCohortsTab") {
							GlobalVariable.G_QueryArmsCohortsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Patemts tab query from switch case : "+GlobalVariable.G_QueryArmsCohortsTab)
						}
						break;

					case ("StatQuery"):  //query for stat bar only
						GlobalVariable.G_StatQuery= sheetData.get(i).get(j).getStringCellValue()
						break;
					case ("cartQuery"):  //query for My cart table only
						GlobalVariable.G_cartQuery= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of cart query from switch case : "+GlobalVariable.G_cartQuery)
						break;
					case("WebExcel"):
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
					case("dbExcel"):
						GlobalVariable.G_dbexcel = sheetData.get(i).get(j).getStringCellValue()
						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
						GlobalVariable.G_ResultPath=dbfilepath.toString()
						break;
					default :
						System.out.println("Error in initializing")
						break;
				}// Switch case ends here
			}//for loop j ends (column read)
		}//for loop i ends (row read)
	} //excelparsingKatalon function ends here



	public static void FindDataInExcel (String input_file, String participantID, String WebSheetName){
		String url = GlobalVariable.G_Urlname;
		String usrDir = System.getProperty("user.dir");
		String inputFiles = "InputFiles";
		String filePath;
		String execelSheetName="Metadata";

		if(url.contains("dataservice")) {
			filePath=Paths.get(usrDir, inputFiles, "CDS", input_file);
		}else {
			KeywordUtil.markFailed("Invalid App URL: Check RunKatalon function")
		}

		if (filePath !=null) {
			KeywordUtil.markPassed("This is the full file path : "+filePath.toString())
			GlobalVariable.InputExcel=filePath.toString();
		}else{
			KeywordUtil.markFailed("Password File is not found")
		}

		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.InputExcel )
		Thread.sleep(2000)

		//load the file using WorkbookFactory and grt the desired sheet
		File file=new File(filePath)
		Workbook wb=WorkbookFactory.create(file)
		Sheet sheet=wb.getSheet(execelSheetName)

		String searchValue= participantID;
		String columnName = "participant_id";
		int columnNumber=0;
		List<String>dataFound=[];
		List<Integer> rowNumbers=[];
		List<List> excelData=new ArrayList<>();

		//find the column number by columnName (loop through the header row to find match)
		Row headerRow = sheet.getRow(0);
		for(int i=0; i<headerRow.getLastCellNum(); i++) {
			String cellValue=headerRow.getCell(i).getStringCellValue();
			if(cellValue.equals(columnName)) {
				columnNumber=i
				System.out.println("participant_id column number:"+i)
				break;
			}
		}
		//list of the columns for the corresponding tabs
		List<Integer> ParticipTabcolNum= [4, 2, 1, 5, 8]
		List<Integer> SamplesTabcolNum= [8, 4, 2, 1, 10, 9]
		List<Integer> FilesTabcolNum= [14, 2, 1, 4, 8, 15]


		//loop through the rows and retrive the desired data from the specific columns
		List<Integer> columnNumbers
		for(int rowIndex=1; rowIndex<=sheet.getLastRowNum(); rowIndex++) {

			Row row=sheet.getRow(rowIndex);
			Cell cell=row.getCell(columnNumber);
			DataFormatter formatter =new DataFormatter();
			String cellValue=formatter.formatCellValue(cell)
			int rowNumber=0;
			//search for desired Participant ID (rows)
			if(cellValue.equals(searchValue)) { 
				dataFound.add(cellValue)
				rowNumber=rowIndex+1;
				rowNumbers.add(rowNumber)

				System.out.println("participant ID " + searchValue + " is on the row "+ rowNumber)
				List<String> data=[];
				if(WebSheetName==GlobalVariable.G_WebTabnameSamples) {
					columnNumbers=SamplesTabcolNum;
					for(int i=0; i<columnNumbers.size(); i++ ) {
						Cell cell2=row.getCell(columnNumbers.get(i))
						String cellValue2=formatter.formatCellValue(cell2)
						data.add(cellValue2)
					}
				}else if(WebSheetName==GlobalVariable.G_WebTabnameParticipants) {
					columnNumbers=ParticipTabcolNum;
					for(int i=0; i<columnNumbers.size(); i++ ) {
						Cell cell2=row.getCell(columnNumbers.get(i))
						String cellValue2=formatter.formatCellValue(cell2)
						data.add(cellValue2)
					}
				}else if(WebSheetName==GlobalVariable.G_WebTabnameFiles) {
					columnNumbers=FilesTabcolNum;
					for(int i=0; i<columnNumbers.size(); i++ ) {
						Cell cell2=row.getCell(columnNumbers.get(i))
						String cellValue2=formatter.formatCellValue(cell2)
						data.add(cellValue2)
					}

				}
				excelData.add(data);

			}

		}


		System.out.println("Participant ID number: " +dataFound.toString())
		System.out.println("Row numbers: " +rowNumbers.toString())
		System.out.println(excelData.toString())

		writeExcel(WebSheetName,  excelData);

	}


	public static void writeExcel(String WebSheetName, List<List> excelData ){  //add a tabname
		try{
			String excelPath = GlobalVariable.G_WebExcel;
			File file1 = new File(excelPath);
			FileOutputStream fos = null;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet;
			List<String> samplesTabHdr=["Sample ID", "Participant ID", "Study Name", "Accession", "Tumor", "Analyte Type"];
			List<String> participantsTabHdr=["Participant ID", "Study Name", "Accession", "Gender", "Sample ID"];
			List<String> filesTabHdr=["File Name", "Study Name", "Accession", "Participant ID", "Sample ID", "File Type"];
			
			List<List>  writeData= new ArrayList();
			if( file1.exists()){
				System.out.println( "File exists, creating a new worksheet in the same file.")
				FileInputStream fileinp = new FileInputStream(excelPath);
				workbook = new XSSFWorkbook(fileinp);
				sheet = workbook.createSheet(WebSheetName);
				fos = new FileOutputStream(excelPath);
			}else{
				fos = new FileOutputStream(new File(excelPath));
				System.out.println( "File does not exist, creating a new file.")
				workbook = new XSSFWorkbook();           // Create Workbook instance holding .xls file
				sheet = workbook.createSheet(WebSheetName);
			}

			if(WebSheetName==GlobalVariable.G_WebTabnameSamples) {
				writeData.add(samplesTabHdr);
				writeData.add(excelData.get(0))

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i);
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++);
						cell.setCellValue(cellD);
					}}
			}else if(WebSheetName==GlobalVariable.G_WebTabnameParticipants) {
			    writeData.add(participantsTabHdr);
				writeData.add(excelData.get(0))

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i);
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++);
						cell.setCellValue(cellD);
					}}
			}else if(WebSheetName==GlobalVariable.G_WebTabnameFiles) {
				 writeData.add(filesTabHdr);
				for(int i=0; i<excelData.size(); i++) {
					writeData.add(excelData.get(i));
				}

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i);
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++);
						cell.setCellValue(cellD);
					}
				}

			}//for loop of in
			
			workbook.write(fos);  //Write workbook into the excel
			fos.close(); //Close the workbook
			System.out.println("Web Data has been written to excel successfully");
			workbook.close();
		}catch (IOException ie){
			ie.printStackTrace();
		}
	}//write to excel method ends here

	public static void runTestCaseByParticipantID(String input_file, String participantID,String WebSheetName, String dbSheetName, String tbQuery) {
		System.out.println("----------------excel------")
		FindDataInExcel (input_file, participantID, WebSheetName)
		System.out.println("----------------db------")
		ReadExcel.Neo4j(dbSheetName, tbQuery)
		System.out.println("----------------comparelists------")
		compareLists(WebSheetName, dbSheetName)
	}

	//**************************************************
	@Keyword

	public static void compareLists(String WebSheetName, String dbSheetName) {  //pass the sheet names only. file name is not needed
		List<List<XSSFCell>> excelData = new ArrayList<>()
		List<List<XSSFCell>> neo4jData = new ArrayList<>()
		String UIfilename =  GlobalVariable.G_WebExcel.toString()   //UIfilepath.toString()
		System.out.println("This is the full uifilepath after converting to string :"+UIfilename);
		excelData = ReadExcel.readExceltoWeblist(UIfilename,WebSheetName)


		System.out.println("This is the Excel data read by comparelists function : "+excelData)
		System.out.println ("This is the row size of the UIdata : "+ excelData.size());
		Collections.sort( excelData , new runtestcaseforKatalon() )
		//	Collections.sort(UIData)

		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		System.out.println("This is the full neo4j filepath after converting to string :"+neo4jfilename);
		//neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_CypherTabnameCasesCasesCases)  //change the function name Test in parent class and here
		neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,dbSheetName)

		System.out.println ("This is the row size of the Neo4jdata : "+ neo4jData.size());
		System.out.println("This is the neo4j data read by comparelists function : "+neo4jData)
		Collections.sort( neo4jData , new runtestcaseforKatalon() )
		//	Collections.sort(neo4jData)

		compareTwoLists(excelData,neo4jData)  //This compares the two sorted lists - ui data and db data
	}

	//compare lists***********************************************************
	public static void compareTwoLists( List<List<XSSFCell>> l1, List<List<XSSFCell>> l2 ){
		System.out.println ("Comparing two Lists");
		int l2row=0;

		while( l2row < l2.size() ){

			for( int l1row = 0; l1row < l1.size(); l1row++ ){

				List<XSSFCell> l1rowList = l1.get(l1row)
				System.out.println(" This is the contents of Excel data : " + l1rowList )

				List<XSSFCell> l2rowList = l2.get(l2row)
				System.out.println(" This is the contents of DB data : " + l2rowList )


				System.out.println(" Comparing Excel data : " + l1rowList)
				System.out.println( " and corresponding DB data : " + l2rowList )

				boolean l1NullFlag = false, l2NullFlag = false
				for(int col = 0; col < l2rowList.size(); col++ ){ //compares all the columns in the excels - for each row
					if( l1rowList.get(col) == null || l1rowList.get(col).equals("") || l1rowList.get(col).getCellType() == l1rowList.get(col).CELL_TYPE_BLANK ){
						System.out.println("There is a NULL entry in Excel Data Row")
						l1NullFlag = true
					}
					if( l2rowList.get(col) == null || l2rowList.get(col).equals("") || l2rowList.get(col).getCellType() == l2rowList.get(col).CELL_TYPE_BLANK ){
						System.out.println("There is a NULL entry in DB Data Row")
						l2NullFlag = true
					}
					if( l1NullFlag == l2NullFlag ) { }//System.out.println("Content Matches for col number: "+ col)
					else System.out.println("Content does not match for col number: " + col )

					if( l1NullFlag || l2NullFlag ) continue   //if the data mismatches, print the data found in ui and db
						System.out.println("Excel data value is: "+ l1rowList.get(col).getStringCellValue() + "\nDB data value is: "+ l2rowList.get(col).getStringCellValue() )

					if( l1rowList.get(col).getStringCellValue() == l2rowList.get(col).getStringCellValue() ){
						System.out.println("Content matches for col number : " + col )
					}else{
						System.err.println("***********DATA MISMATCH:  ABORTING RUN********************")
						System.out.println("Content does not match for col: " + col )
						System.out.println( "Excel data Value (mismatch): " + l1rowList.get(col).getStringCellValue() )
						System.out.println( "DB data Value (mismatch): " + l2rowList.get(col).getStringCellValue() )
						KeywordUtil.markFailed("***********DATA MISMATCH in comparelists:  ABORTING RUN********************");

						//add steps for handling failure
					}
				}
				//				}else{
				//					System.out.println("UI Data and DB Data are not matching for :")
				//					// add what the code should display if contents mismatch outside the main loop for CTDC ID
				//				}
				l2row++} //l1forloop
		} // l2 while loop
	}

	//**************************************************
	public static void FindDataInExcel2(String input_file, String participantID, String WebSheetName){
		String url = GlobalVariable.G_Urlname;
		String usrDir = System.getProperty("user.dir");
		String inputFiles = "InputFiles";
		String filePath;
		String execelSheetName="Metadata";

		if(url.contains("dataservice")) {
			filePath=Paths.get(usrDir, inputFiles, "CDS", input_file);
		}else {
			KeywordUtil.markFailed("Invalid App URL: Check RunKatalon function")
		}

		if (filePath !=null) {
			KeywordUtil.markPassed("This is the full file path : "+filePath.toString())
			GlobalVariable.InputExcel=filePath.toString();
		}else{
			KeywordUtil.markFailed("Password File is not found")
		}

		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.InputExcel )
		Thread.sleep(2000)

		//load the file using WorkbookFactory and grt the desired sheet
		File file=new File(filePath)
		Workbook wb=WorkbookFactory.create(file)
		Sheet sheet=wb.getSheet(execelSheetName)

		String searchValue= participantID;
		String columnName = "participant_id";
		int columnNumber= findColNumber(columnName,sheet) //find the column number by columnName (loop through the header row to find match)
		List<String>dataFound=[];
		List<Integer> rowNumbers=[];
		//List<Cell> data = new ArrayList<>();
		List<List> excelData=new ArrayList<>();

		List<String> columnNames=["participant_id", "sample_id", "study_name", "phs_accession", "sample_tumor_status", "sample_type", "gender", "file_name", "file_type"]
		List<Integer> columnNumbers= findColNumbers(columnNames, sheet);//find the column number by columnNames
		System.out.println("column names fron Excel: "+columnNames.toString())
		System.out.println("column numbers fron Excel: "+columnNumbers.toString())
		
		for(int rowIndex=1; rowIndex<=sheet.getLastRowNum(); rowIndex++) {

			Row row=sheet.getRow(rowIndex);
			Cell cell=row.getCell(columnNumber);
			DataFormatter formatter =new DataFormatter();
			String cellValue=formatter.formatCellValue(cell)
			int rowNumber=0;
			List<String> data=[];
			//loop through rows to find the rows with the desired Participant ID
			if(rowIndex!=rowIndex-1 && cellValue.equals(searchValue)) {
				for(int i=0; i<columnNumbers.size(); i++ ) { //collect data for the desired Participant ID
					Cell cell2=row.getCell(columnNumbers.get(i))
					String cellValue2=formatter.formatCellValue(cell2)
					data.add(cellValue2)
				}
				excelData.add(data);

			}else if(cellValue.equals(searchValue)) { //search for desired Participant ID
				dataFound.add(cellValue)
				rowNumber=rowIndex+1;
				rowNumbers.add(rowNumber)
				System.out.println("participant ID " + searchValue + " is on the row "+ rowNumber)
				for(int i=0; i<columnNumbers.size(); i++ ) { //collect data for the desired Participant ID
					Cell cell2=row.getCell(columnNumbers.get(i))
					String cellValue2=formatter.formatCellValue(cell2)
					data.add(cellValue2)
					//data.add(cell2)

				}
				excelData.add(data);

			}

		}


		System.out.println("Participant ID number: " +dataFound.toString())
		System.out.println("Row numbers: " +rowNumbers.toString())
		System.out.println(excelData.toString())
		//excelparsingKatalon2(data);
		//System.out.println(SheetName)

		writeExcel2(WebSheetName,  excelData);

	}
	public static void runTestCaseByParticipantID2(String input_file, String participantID,String WebSheetName, String dbSheetName, String tbQuery) {
		System.out.println("----------------excel------")
		FindDataInExcel2(input_file, participantID, WebSheetName)
		System.out.println("----------------db------")
		ReadExcel.Neo4j(dbSheetName, tbQuery)
		System.out.println("----------------comparelists------")
		compareLists(WebSheetName, dbSheetName)
	}

	public static void writeExcel2(String WebSheetName, List<List> excelData ){  //add a tabname
		try
		{

			String excelPath = GlobalVariable.G_WebExcel;
			File file1 = new File(excelPath);
			FileOutputStream fos = null;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet;

			List<String> TabHdr=["Participant ID", "Sample ID", "Study Name", "Accession", "Tumor", "Analyte Type", "Gender", "File Name", "File Type"];
			List<List>  writeData= new ArrayList();

			if( file1.exists()){
				System.out.println( "File exists, creating a new worksheet in the same file.")
				FileInputStream fileinp = new FileInputStream(excelPath);
				workbook = new XSSFWorkbook(fileinp);
				sheet = workbook.createSheet(WebSheetName);
				fos = new FileOutputStream(excelPath);
			}else{
				fos = new FileOutputStream(new File(excelPath));
				System.out.println( "File does not exist, creating a new file.")
				workbook = new XSSFWorkbook();           // Create Workbook instance holding .xls file
				sheet = workbook.createSheet(WebSheetName);

			}


			writeData.add(TabHdr);
			for(int i=0; i<excelData.size(); i++) {
				writeData.add(excelData.get(i))
			}
			System.out.println("----------------writeData list---------")
			System.out.println(writeData.size())
			System.out.println(writeData.toString())
			for( int i = 0; i < writeData.size(); i++ ){
				Row row = sheet.createRow(i);
				int cellNo = 0
				ArrayList<String> cellData = writeData.get(i)
				for( String cellD: cellData ){
					//System.out.println("Cell data is: " + cellD )
					Cell cell = row.createCell(cellNo++);
					cell.setCellValue(cellD);
				}
			}//for loop of in
			workbook.write(fos);  //Write workbook into the excel
			fos.close(); //Close the workbook
			System.out.println("Web Data has been written to excel successfully");
			workbook.close();
		}catch (IOException ie)
		{
			ie.printStackTrace();
		}
	}//write to excel method 2 ends here


	//find the column number by columnName (loop through the header row to find match)
	public static int findColNumber(String columnName, Sheet sheet) {
		int columnNumber=0;
		Row headerRow = sheet.getRow(0);
		for(int i=0; i<headerRow.getLastCellNum(); i++) {
			String cellValue=headerRow.getCell(i).getStringCellValue();
			if(cellValue.equals(columnName)) {
				columnNumber=i
				System.out.println("participant_id column number:"+i)
				break;
			}
		}
		return columnNumber
	}// findColNumber ends here


	//find the column number by columnName (loop through the header row to find match)
	public static List<Integer> findColNumbers(List<String> columnNames, Sheet sheet) {
		List<Integer> columnNumbers=new ArrayList()
		Row headerRow = sheet.getRow(0);
		int idx=0;
		while(columnNumbers.size()!=columnNames.size()) {
			String columnName=columnNames.get(idx)

			for(int i=0; i<headerRow.getLastCellNum(); i++) {
				String cellValue=headerRow.getCell(i).getStringCellValue();
				if(cellValue.equals(columnName)) {
					columnNumbers.add(i);
					System.out.println("participant_id column number:"+i)
					break
				}
			}
			idx++
		}
		return columnNumbers
	}// findColNumber ends here


}//class ends here
