package ctdc.utilities

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.awt.AWTException
import java.awt.Robot
import java.awt.event.KeyEvent
import java.io.IOException
import java.util.concurrent.TimeUnit
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.ProfilesIni
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.util.Iterator
import java.util.Set

import java.nio.file.Path
import java.nio.file.Paths
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.Keys
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.HashMap
import java.util.Map
import org.apache.commons.io.FileUtils
import internal.GlobalVariable
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.OutputType
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
import com.kms.katalon.core.logging.KeywordLogger
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
import java.util.Iterator
import java.util.Set

import java.nio.file.Path
import java.nio.file.Paths

import internal.GlobalVariable
import java.lang.AutoCloseable

public class CDSValidation implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}

	public static WebDriver driver

	/**
	 * This function reads the excel file from InputFiles
	 * @param input_file
	 */
	@Keyword
	public  void runKatalonDataValidation(String appName, String input_file) {

		String usrDir = System.getProperty("user.dir")
		String inputFiles = "InputFiles"
		Path filePath

		if(appName=="CDS"){
			filePath = Paths.get(usrDir, inputFiles, "CDS", input_file)
		}else if(appName=="CCDI") {
			filePath = Paths.get(usrDir, inputFiles, "CCDI", input_file)
		}


		if (filePath !=null) {
			KeywordUtil.markPassed("This is the full file path : "+filePath.toString())
			GlobalVariable.InputExcel=filePath.toString()
		}else{
			KeywordUtil.markFailed("Password File is not found")
		}

		try {
			KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.InputExcel )
			Thread.sleep(2000)
			List<List<XSSFCell>> sheetData_K = new ArrayList<>()
			FileInputStream fis = new FileInputStream(GlobalVariable.InputExcel)
			XSSFWorkbook workbook = new XSSFWorkbook(fis) // Create an excel workbook from the file system.

			int numberOfSheets = workbook.getNumberOfSheets()// Get the  sheets on the workbook
			int countrow = 0
			int countcol= 0
			Thread.sleep(2000)
			XSSFSheet sheet = workbook.getSheetAt(0)  //reading input query
			countrow = sheet.lastRowNum- sheet.firstRowNum
			System.out.println ( "Row count is  : " + countrow)
			countcol = sheet.getRow(0).getLastCellNum()
			System.out.println("Col count is : " + countcol)

			//This loops through the rows of the table till there is next row
			Iterator rows = sheet.rowIterator()
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next()
				Iterator cells = row.cellIterator()
				List<XSSFCell> data = new ArrayList<>()
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next()
					data.add(cell)
				}
				sheetData_K.add(data)
			}

			KeywordUtil.markPassed("Data loaded from input file for the test case. " + GlobalVariable.G_Value)
			excelparsingKatalon(sheetData_K)
			//System.out.println("This is the value of sheetdata array from runkatalon function : "+sheetData_K)
			//KeywordUtil.markPassed("This is the value of sheetdata array from runkatalon function : "+sheetData_K)

			fis.close()
			workbook.close()
			workbook=null

		}catch (IOException ie){
			ie.printStackTrace()
		}

	}

	/**
	 * This function reads input excels and assigns global variables to each query...
	 * @param sheetData
	 * @param dr
	 */
	private static void excelparsingKatalon(List<List<XSSFCell>> sheetData) {

		int countrow = 0
		countrow = sheetData.size()
		System.out.println ( "row count from initializing fnc: " + countrow )
		System.out.println ( "sheet  data size: " + sheetData.get(0).size())

		//Loop through rows
		for (int i = 1; i < countrow; i++){
			List<XSSFCell> datarow = sheetData.get(i)
			System.out.println (" Columns size from initializing fnc:  " + datarow.size())
			String str = ""
			//loop through columns
			for (int j = 0; j < datarow.size(); j++){
				System.out.println ("value of  i : "  + i + "  Value of j  : " + j )
				XSSFCell cell = datarow.get(j)
				//Look for specific column names to perform action
				switch(sheetData.get(0).get(j).getStringCellValue().trim() )
				{
					case("TabName"):
						GlobalVariable.G_inputTabName = sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the tabname from input excel : "+GlobalVariable.G_inputTabName)
						KeywordUtil.markPassed("This is the tabname from input excel : "+GlobalVariable.G_inputTabName)
						break
					case("query"):

						if(GlobalVariable.G_inputTabName=="SamplesTab"){
							String QuerySamplesTab = sheetData.get(i).get(j).getStringCellValue()

							GlobalVariable.G_QuerySamplesTab = ChangeValue(QuerySamplesTab)
							System.out.println("This is the value of samples tab query from switch case : "+GlobalVariable.G_QuerySamplesTab)
							KeywordUtil.markPassed("This is the value of samples tab query from switch case : "+GlobalVariable.G_QuerySamplesTab)

						}else if(GlobalVariable.G_inputTabName=="FilesTab"){
							String QueryFilesTab = sheetData.get(i).get(j).getStringCellValue()

							GlobalVariable.G_QueryFilesTab =ChangeValue (QueryFilesTab)
							System.out.println("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryFilesTab)
							KeywordUtil.markPassed("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryFilesTab)

						}else if(GlobalVariable.G_inputTabName=="ParticipantsTab"){
							String QueryParticipantsTab = sheetData.get(i).get(j).getStringCellValue()

							GlobalVariable.G_QueryParticipantsTab = ChangeValue(QueryParticipantsTab)
							System.out.println("This is the value of Participants tab query from switch case : "+GlobalVariable.G_QueryParticipantsTab)
							KeywordUtil.markPassed("This is the value of Participants tab query from switch case : "+GlobalVariable.G_QueryParticipantsTab)

						}else if(GlobalVariable.G_inputTabName=="DiagnosisTab"){
							String QueryDiagnosisTab = sheetData.get(i).get(j).getStringCellValue()

							GlobalVariable.G_QueryDiagnosisTab = ChangeValue(QueryDiagnosisTab)
							System.out.println("This is the value of Diagnosis Tab query from switch case : "+GlobalVariable.G_QueryDiagnosisTab)
							KeywordUtil.markPassed("This is the value of Diagnosis Tab query from switch case : "+GlobalVariable.G_QueryDiagnosisTab)
						}
						break;

					case("ExDataExcel"):
						String ExDataExcel = sheetData.get(i).get(j).getStringCellValue()

						GlobalVariable.G_ExDataExcel = ChangeValue(ExDataExcel)
						GlobalVariable.G_OutputFileName = GlobalVariable.G_ExDataExcel
						System.out.println("This is the value of gexdataexcel before appending with directory :"+GlobalVariable.G_ExDataExcel)
						System.out.println("This is the value of output filename stored in a global var :"+GlobalVariable.G_OutputFileName)


						Path outputDir = Paths.get(System.getProperty("user.dir"), "OutputFiles")
						GlobalVariable.G_OutputDir =outputDir.toString()
						System.out.println("This is the path till the output directory : "+GlobalVariable.G_OutputDir)

						Path filepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_ExDataExcel)
						GlobalVariable.G_ExDataExcel=filepath.toString()
						System.out.println("This is the full path stored in global variable gwebexcel: "+GlobalVariable.G_ExDataExcel)
						KeywordUtil.markPassed("This is the full path stored in global variable gwebexcel: "+GlobalVariable.G_ExDataExcel)
						break;

					case("dbExcel"):
						String dbExcel = sheetData.get(i).get(j).getStringCellValue()

					//GlobalVariable.G_dbexcel=sheetData.get(i).get(j).getStringCellValue()

						GlobalVariable.G_dbexcel = ChangeValue(dbExcel)

						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
						GlobalVariable.G_ResultPath=dbfilepath.toString()
						KeywordUtil.markPassed("This is the full path stored in global variable dbExcel: "+ GlobalVariable.G_ResultPath)
						break;
					default :
						System.out.println("Error in initializing")
						break
				}// Switch case ends here
			}//for loop j ends (column read)
		}//for loop i ends (row read)
	} //excelparsingKatalon function ends here



	public static void FindDataInExcel (String input_file, String keyInput, String ExDataSheetName, String columnName,List<Integer> columnNames ){
		String url = GlobalVariable.G_Urlname
		String usrDir = System.getProperty("user.dir")
		String inputFiles = "InputFiles"
		String filePath

		if(url.contains("dataservice")) {
			filePath=Paths.get(usrDir, inputFiles, "CDS", input_file)
		}else if(url.contains("ccdi")) {
			filePath=Paths.get(usrDir, inputFiles, "CCDI", input_file)
		}else {
			KeywordUtil.markFailed("Invalid App URL: Check RunKatalon function")
		}

		if (filePath !=null) {
			KeywordUtil.markPassed("This is the full file path : " + filePath.toString())
			GlobalVariable.InputExcel=filePath.toString()
		}else{
			KeywordUtil.markFailed("Password File is not found")
		}

		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.InputExcel )
		Thread.sleep(2000)

		//load the file using WorkbookFactory and grt the desired sheet
		File file=new File(filePath)
		Workbook wb=WorkbookFactory.create(file)
		Sheet sheet=wb.getSheetAt(0)

		String searchValue= keyInput
		//String columnName = "participant_id"; // set up in the method
		int columnNumber=findColNumber(columnName, sheet)
		List<String>dataFound=[]
		List<Integer> rowNumbers=[]
		List<List> excelData=new ArrayList<>()

		//loop through the rows and retrieve the desired data from the specific columns
		List<Integer> columnNumbers= findColNumbers(columnNames, sheet)//find the column number by columnNames
		for(int rowIndex=1; rowIndex<=sheet.getLastRowNum(); rowIndex++) {

			Row row=sheet.getRow(rowIndex)
			Cell cell=row.getCell(columnNumber)
			DataFormatter formatter =new DataFormatter()
			String cellValue=formatter.formatCellValue(cell)
			int rowNumber=0
			//search for desired Participant ID (rows)
			if(cellValue.equals(searchValue)) {
				dataFound.add(cellValue)
				rowNumber=rowIndex+1
				rowNumbers.add(rowNumber)

				System.out.println(columnName + " : "+ searchValue + " is on the row "+ rowNumber)
				List<String> data=[]
				if(ExDataSheetName==GlobalVariable.G_WebTabnameSamples) {
					for(int i=0; i<columnNumbers.size(); i++ ) {
						Cell cell2=row.getCell(columnNumbers.get(i))
						String cellValue2=formatter.formatCellValue(cell2)
						data.add(cellValue2)
					}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameParticipants) {
					for(int i=0; i<columnNumbers.size(); i++ ) {
						Cell cell2=row.getCell(columnNumbers.get(i))
						String cellValue2=formatter.formatCellValue(cell2)
						data.add(cellValue2)
					}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameFiles) {
					for(int i=0; i<columnNumbers.size(); i++ ) {
						Cell cell2=row.getCell(columnNumbers.get(i))
						String cellValue2=formatter.formatCellValue(cell2)
						data.add(cellValue2)
					}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameDiagnosis) {
					for(int i=0; i<columnNumbers.size(); i++ ) {
						Cell cell2=row.getCell(columnNumbers.get(i))
						String cellValue2=formatter.formatCellValue(cell2)
						data.add(cellValue2)
					}
				}

				excelData.add(data)
			}
		}


		System.out.println("Participant ID number: " +dataFound.toString())
		System.out.println("Row numbers: " +rowNumbers.toString())
		System.out.println(excelData.toString())

		writeExcel(ExDataSheetName,  excelData)
		KeywordUtil.markPassed("Excel Data has been written to excel successfully")

	}


	public static void writeExcel(String ExDataSheetName, List<List> excelData ){  //add a tabname
		try{
			String excelPath = GlobalVariable.G_ExDataExcel
			File file1 = new File(excelPath)
			FileOutputStream fos = null
			XSSFWorkbook workbook = null
			XSSFSheet sheet

			List<List>  writeData= new ArrayList()
			if( file1.exists()){
				System.out.println( "File exists, creating a new worksheet in the same file.")
				FileInputStream fileinp = new FileInputStream(excelPath)
				workbook = new XSSFWorkbook(fileinp)
				sheet = workbook.createSheet(ExDataSheetName)
				fos = new FileOutputStream(excelPath)
			}else{
				fos = new FileOutputStream(new File(excelPath))
				System.out.println( "File does not exist, creating a new file.")
				workbook = new XSSFWorkbook()           // Create Workbook instance holding .xls file
				sheet = workbook.createSheet(ExDataSheetName)
			}

			if(ExDataSheetName==GlobalVariable.G_WebTabnameSamples) {
				writeData.add(GlobalVariable.G_SamplesTabHdr)
				//				writeData.add(excelData.get(0))
				for(int i=0; i<excelData.size(); i++) {
					writeData.add(excelData.get(i))
				}

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i)
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++)
						cell.setCellValue(cellD)
					}}
			}else if(ExDataSheetName==GlobalVariable.G_WebTabnameParticipants) {
				writeData.add(GlobalVariable.G_ParticipTabHdr)
				//writeData.add(excelData.get(0))
				for(int i=0; i<excelData.size(); i++) {
					writeData.add(excelData.get(i))
				}

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i)
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++)
						cell.setCellValue(cellD)
					}}
			}else if(ExDataSheetName==GlobalVariable.G_WebTabnameFiles) {
				writeData.add(GlobalVariable.G_FilesTabHdr)
				for(int i=0; i<excelData.size(); i++) {
					writeData.add(excelData.get(i))
				}

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i)
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++)
						cell.setCellValue(cellD)
					}}
			}else if(ExDataSheetName==GlobalVariable.G_WebTabnameDiagnosis) {
				writeData.add(GlobalVariable.G_DiagnosisTabHdr)
				//writeData.add(excelData.get(0))
				for(int i=0; i<excelData.size(); i++) {
					writeData.add(excelData.get(i))
				}

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i)
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++)
						cell.setCellValue(cellD)
					}}
			}



			workbook.write(fos)  //Write workbook into the excel
			fos.close() //Close the workbook
			System.out.println("Data has been written to excel successfully")
			workbook.close()
		}catch (IOException ie){
			ie.printStackTrace()
		}
	}//write to excel method ends here

	public static void writeExcel(String appName, String ExDataSheetName, List<List> excelData ){  //add a tabname
		try{
			String excelPath = GlobalVariable.G_ExDataExcel
			File file1 = new File(excelPath)
			FileOutputStream fos = null
			XSSFWorkbook workbook = null
			XSSFSheet sheet

			List<List>  writeData= new ArrayList()
			if( file1.exists()){
				System.out.println( "File exists, creating a new worksheet in the same file.")
				FileInputStream fileinp = new FileInputStream(excelPath)
				workbook = new XSSFWorkbook(fileinp)
				sheet = workbook.createSheet(ExDataSheetName)
				fos = new FileOutputStream(excelPath)
			}else{
				fos = new FileOutputStream(new File(excelPath))
				System.out.println( "File does not exist, creating a new file.")
				workbook = new XSSFWorkbook()           // Create Workbook instance holding .xls file
				sheet = workbook.createSheet(ExDataSheetName)
			}
			if(appName=="CCDI") {
				if(ExDataSheetName==GlobalVariable.G_WebTabnameSamples) {
					writeData.add(GlobalVariable.G_SamplesTabHdr)
					//				writeData.add(excelData.get(0))
					for(int i=0; i<excelData.size(); i++) {
						writeData.add(excelData.get(i))
					}

					System.out.println(writeData.size())
					System.out.println(writeData.toString())
					for( int i = 0; i < writeData.size(); i++ ){
						Row row = sheet.createRow(i)
						int cellNo = 0
						ArrayList<String> cellData = writeData.get(i)
						for( String cellD: cellData ){
							//System.out.println("Cell data is: " + cellD )
							Cell cell = row.createCell(cellNo++)
							cell.setCellValue(cellD)
						}}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameParticipants) {
					writeData.add(GlobalVariable.G_ParticipTabHdr)
					writeData.add(excelData.get(0))

					System.out.println(writeData.size())
					System.out.println(writeData.toString())
					for( int i = 0; i < writeData.size(); i++ ){
						Row row = sheet.createRow(i)
						int cellNo = 0
						ArrayList<String> cellData = writeData.get(i)
						for( String cellD: cellData ){
							//System.out.println("Cell data is: " + cellD )
							Cell cell = row.createCell(cellNo++)
							cell.setCellValue(cellD)
						}}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameFiles) {
					writeData.add(GlobalVariable.G_FilesTabHdr)
					for(int i=0; i<excelData.size(); i++) {
						writeData.add(excelData.get(i))
					}

					System.out.println(writeData.size())
					System.out.println(writeData.toString())
					for( int i = 0; i < writeData.size(); i++ ){
						Row row = sheet.createRow(i)
						int cellNo = 0
						ArrayList<String> cellData = writeData.get(i)
						for( String cellD: cellData ){
							//System.out.println("Cell data is: " + cellD )
							Cell cell = row.createCell(cellNo++)
							cell.setCellValue(cellD)
						}}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameDiagnosis) {
					writeData.add(GlobalVariable.G_DiagnosisTabHdr)
					//writeData.add(excelData.get(0))
					for(int i=0; i<excelData.size(); i++) {
						writeData.add(excelData.get(i))
					}}
			}else if(appName=="CDS"){
				if(ExDataSheetName==GlobalVariable.G_WebTabnameSamples) {
					writeData.add(GlobalVariable.G_SamplesTabHdr)
					writeData.add(excelData.get(0))

					System.out.println(writeData.size())
					System.out.println(writeData.toString())
					for( int i = 0; i < writeData.size(); i++ ){
						Row row = sheet.createRow(i)
						int cellNo = 0
						ArrayList<String> cellData = writeData.get(i)
						for( String cellD: cellData ){
							//System.out.println("Cell data is: " + cellD )
							Cell cell = row.createCell(cellNo++)
							cell.setCellValue(cellD)
						}}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameParticipants) {
					writeData.add(GlobalVariable.G_ParticipTabHdr)
					writeData.add(excelData.get(0))

					System.out.println(writeData.size())
					System.out.println(writeData.toString())
					for( int i = 0; i < writeData.size(); i++ ){
						Row row = sheet.createRow(i)
						int cellNo = 0
						ArrayList<String> cellData = writeData.get(i)
						for( String cellD: cellData ){
							//System.out.println("Cell data is: " + cellD )
							Cell cell = row.createCell(cellNo++)
							cell.setCellValue(cellD)
						}}
				}else if(ExDataSheetName==GlobalVariable.G_WebTabnameFiles) {
					writeData.add(GlobalVariable.G_FilesTabHdr)
					for(int i=0; i<excelData.size(); i++) {
						writeData.add(excelData.get(i))
					}

					System.out.println(writeData.size())
					System.out.println(writeData.toString())
					for( int i = 0; i < writeData.size(); i++ ){
						Row row = sheet.createRow(i)
						int cellNo = 0
						ArrayList<String> cellData = writeData.get(i)
						for( String cellD: cellData ){
							//System.out.println("Cell data is: " + cellD )
							Cell cell = row.createCell(cellNo++)
							cell.setCellValue(cellD)
						}}
				}

				System.out.println(writeData.size())
				System.out.println(writeData.toString())
				for( int i = 0; i < writeData.size(); i++ ){
					Row row = sheet.createRow(i)
					int cellNo = 0
					ArrayList<String> cellData = writeData.get(i)
					for( String cellD: cellData ){
						//System.out.println("Cell data is: " + cellD )
						Cell cell = row.createCell(cellNo++)
						cell.setCellValue(cellD)
					}}
			}



			workbook.write(fos)  //Write workbook into the excel
			fos.close() //Close the workbook
			System.out.println("Data has been written to excel successfully")
			workbook.close()
			workbook=null
		}catch (IOException ie){
			ie.printStackTrace()
		}
	}//write to excel method ends here

	public static void runTestCaseByParticipantID(String input_file, String keyInput, String columnName, List<String> columnNames,String ExDataSheetName, String dbSheetName, String tbQuery) {
		System.out.println("----------------excel------")
		FindDataInExcel (input_file, keyInput, ExDataSheetName, columnName, columnNames )
		System.out.println("----------------db------")
		KeywordUtil.markPassed("db connection starts")
		ReadExcel.Neo4j(dbSheetName, tbQuery)
		System.out.println("----------------comparelists------")
		compareLists(ExDataSheetName, dbSheetName)
	}

	//**************************************************
	@Keyword

	public static void compareLists(String ExDataSheetName, String dbSheetName) {  //pass the sheet names only. file name is not needed
		List<List<XSSFCell>> excelData = new ArrayList<>()
		List<List<XSSFCell>> neo4jData = new ArrayList<>()
		String Excelfilename =  GlobalVariable.G_ExDataExcel.toString()   //Excelfilename.toString()
		System.out.println("This is the full uifilepath after converting to string :" + Excelfilename)
		excelData = ReadExcel.readExceltoWeblist(Excelfilename,ExDataSheetName)


		System.out.println("This is the Excel data read by comparelists function : "+excelData)
		System.out.println ("This is the row size of the Excel data : "+ excelData.size())
		Collections.sort( excelData , new runtestcaseforKatalon() )
		//	Collections.sort(ExcelData)

		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		System.out.println("This is the full neo4j filepath after converting to string :"+neo4jfilename)
		//neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_CypherTabnameCasesCasesCases)  //change the function name Test in parent class and here
		neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,dbSheetName)

		System.out.println ("This is the row size of the Neo4jdata : "+ neo4jData.size())
		System.out.println("This is the neo4j data read by comparelists function : "+neo4jData)
		Collections.sort( neo4jData , new runtestcaseforKatalon() )
		//	Collections.sort(neo4jData)
        if(excelData.size()==neo4jData.size()) {
		compareTwoLists(excelData,neo4jData)  //This compares the two sorted lists - ui data and db data
		KeywordUtil.markPassed("Two Lists is compared")
        }else if(excelData.size()>neo4jData.size()) {
			KeywordLogger logger = new KeywordLogger()
			KeywordUtil.markFailed("***********DATA MISMATCH in comparelists: Excel contains more data rows********************")
			KeywordUtil.markFailed("This is the row size of the Excel data : "+ excelData.size())
			KeywordUtil.markFailed("This is the Excel data read by comparelists function : "+excelData)
			KeywordUtil.markFailed("This is the row size of the Neo4jdata : "+ neo4jData.size())
			KeywordUtil.markFailed("This is the neo4j data read by comparelists function : "+neo4jData)
		}else if(excelData.size()<neo4jData.size()) {
			KeywordLogger logger = new KeywordLogger()
			KeywordUtil.markFailed("***********DATA MISMATCH in comparelists: Neo4j contains more data rows********************")
			KeywordUtil.markFailed("This is the row size of the Neo4jdata : "+ neo4jData.size())
			KeywordUtil.markFailed("This is the neo4j data read by comparelists function : "+neo4jData)
			KeywordUtil.markFailed("This is the row size of the Excel data : "+ excelData.size())
			KeywordUtil.markFailed("This is the Excel data read by comparelists function : "+excelData)
			
		}
	}

	//compare lists***********************************************************
	public static void compareTwoLists( List<List<XSSFCell>> l1, List<List<XSSFCell>> l2 ){
		System.out.println ("Comparing two Lists")
		int l2row=0

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
					if( l2rowList.get(col)== null || l2rowList.get(col).equals("") || l2rowList.get(col).getCellType() == l2rowList.get(col).CELL_TYPE_BLANK ){
						System.out.println("There is a NULL entry in DB Data Row")
						l2NullFlag = true
					}
					if( l1NullFlag == l2NullFlag ) { }//System.out.println("Content Matches for col number: "+ col)
					else System.out.println("Content does not match for col number: " + col )

					if( l1NullFlag || l2NullFlag ) continue   //if the data mismatches, print the data found in row excel and db
						System.out.println("Excel data value is: "+ l1rowList.get(col).getStringCellValue() + "\nDB data value is: "+ l2rowList.get(col).getStringCellValue() )

					if( l1rowList.get(col).getStringCellValue().toUpperCase() == l2rowList.get(col).getStringCellValue().toUpperCase() ){
						KeywordLogger logger = new KeywordLogger()
						logger.logInfo("Content match for col: " + col)
						logger.logInfo("Excel data Value (match): " + l1rowList.get(col).getStringCellValue())
						logger.logInfo( "DB data Value (match): " + l2rowList.get(col).getStringCellValue())
						System.out.println("Content matches for col number : " + col )
					}else{
						KeywordLogger logger = new KeywordLogger()
						logger.logInfo("Content does not match for col: " + col)
						logger.logInfo("Excel data Value (mismatch): " + l1rowList.get(col).getStringCellValue())
						logger.logInfo( "DB data Value (mismatch): " + l2rowList.get(col).getStringCellValue())
						System.err.println("***********DATA MISMATCH:  ABORTING RUN********************")
						System.out.println("Content does not match for col: " + col)
						System.out.println( "Excel data Value (mismatch): " + l1rowList.get(col).getStringCellValue() )
						System.out.println( "DB data Value (mismatch): " + l2rowList.get(col).getStringCellValue() )
						KeywordUtil.markFailed("***********DATA MISMATCH in comparelists:  ABORTING RUN********************")

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



	//find the column number by columnName (loop through the header row to find match)
	public static int findColNumber(String columnName, Sheet sheet) {
		int columnNumber=0
		Row headerRow = sheet.getRow(0)
		for(int i=0; i<headerRow.getLastCellNum(); i++) {
			String cellValue=headerRow.getCell(i).getStringCellValue()
			if(cellValue.equals(columnName)) {
				columnNumber=i
				System.out.println(columnName + " column number:"+i)
				break
			}
		}
		return columnNumber
	}// findColNumber ends here


	//find the column number by columnName (loop through the header row to find match)
	public static List<Integer> findColNumbers(List<String> columnNames, Sheet sheet) {
		List<Integer> columnNumbers=new ArrayList()
		Row headerRow = sheet.getRow(0)
		int idx=0
		while(columnNumbers.size()!=columnNames.size()) {
			String columnName=columnNames.get(idx)

			for(int i=0; i<headerRow.getLastCellNum(); i++) {
				String cellValue=headerRow.getCell(i).getStringCellValue()
				if(cellValue.equals(columnName)) {
					columnNumbers.add(i)
					System.out.println(columnName +" column number:"+i)
					break
				}
			}
			idx++
		}
		return columnNumbers
	}// findColNumber ends here


	public static ChangeValue(String temp) {
		if(temp.contains("InptVlue")) {
			temp=temp.replace("InptVlue", GlobalVariable.G_Value)
		}
	}


	public static printTestCaseNumber(){
		System.out.println("This is the test case name: "+GlobalVariable.G_currentTCName +"_"+ GlobalVariable.G_Value)
		KeywordUtil.markPassed("This is the test case name: "+GlobalVariable.G_currentTCName +"_"+ GlobalVariable.G_Value)
	}

	public static printTCData() {
		System.out.println("This Participant ID used in this test case is: "+ GlobalVariable.G_Value)
		KeywordUtil.markPassed("This Participant ID used in this test case is: "+ GlobalVariable.G_Value)
	}



}//class ends here
