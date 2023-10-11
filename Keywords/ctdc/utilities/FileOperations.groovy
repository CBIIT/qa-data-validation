package ctdc.utilities
//package org.apache.poi.ss.usermodel.helpers;


import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.*;

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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.lang.Object;
import com.opencsv.CSVReader;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import internal.GlobalVariable
import java.io.FileFilter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

//import java.nio.file.Paths
//import java.nio.file.Path
//import java.math.BigDecimal;
//import java.io.File;
//import java.io.IOException;
//import org.apache.commons.io.comparator.LastModifiedFileComparator;
//import org.apache.commons.io.filefilter.WildcardFileFilter;
//import java.io.IOException;
//import java.util.Iterator;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.io.*;
//import java.util.Map;
//import internal.GlobalVariable

public class FileOperations {

	private static final String CSV_SEPERATOR_CHAR="	";


	@Keyword
	public void assignMfstFilenames () {

		String downloadFolder = Paths.get(System.getProperty("user.dir"), "OutputFiles");
		String newfilename = GlobalVariable.G_currentTCName+"_Manifest";
		String newfilefullpath = Paths.get(System.getProperty("user.dir"), "OutputFiles", newfilename);
		String newcsvfilefullpath = newfilefullpath+".csv";
		String xlsManifestName = newfilename +".xls";
		String xlsxManifestName = newfilename +".xlsx";
		GlobalVariable.G_WebTabnameMyCartsvFileName = newcsvfilefullpath.toString();
		GlobalVariable.G_excelFileName = (Paths.get(System.getProperty("user.dir"), "OutputFiles", xlsManifestName)).toString();
		GlobalVariable.G_xlsxFileName = (Paths.get(System.getProperty("user.dir"), "OutputFiles", xlsxManifestName)).toString();
		System.out.println("This is the value stored of csvfilename from test case : "+GlobalVariable.G_WebTabnameMyCartsvFileName)
		System.out.println("Thsi is the value stored of excelfilename from test case : "+GlobalVariable.G_excelFileName)
	}


	@Keyword
	public void manifestFileOps (String csvfilename1, String xlsfilename1,  String xlsxfilename1, String mfstSelectedColsSheetNm,  String mfstBkupSheetNm) throws IOException {

		pickLatestFileFromDownloads()
		System.out.println("Taking the latest file downloaded after converting it to csv format");
		fileRename()
		System.out.println("Renaming the latest file downloaded");
		Thread.sleep(3000)
		System.out.println("This is the value stored in csvfilename global var : "+GlobalVariable.G_WebTabnameMyCartsvFileName)
		System.out.println("This is the value stored in excelfilename global var : "+GlobalVariable.G_excelFileName)

		System.out.println("This is the value from function parameter for csvfilename  : "+csvfilename1)
		System.out.println("This is the value from function parameter for excelfilename   : "+xlsfilename1)
		Thread.sleep(2000)
		generateXLSfromCSV(csvfilename1, xlsfilename1, mfstSelectedColsSheetNm)
		xlsTOxlsx(xlsfilename1, xlsxfilename1)
		copySheetXLSX(xlsxfilename1, mfstBkupSheetNm)
		deleteCol(xlsxfilename1)

	}

	@Keyword
	public String pickLatestFileFromDownloads() {


		System.out.println("This is the name of the current test case from global variable: " +GlobalVariable.G_currentTCName);

		//this global will work fine only after this function is called
		String downloadslocalFolder = "C:\\Users\\radhakrishnang2\\Downloads"
		File dir = new File(downloadslocalFolder);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			testLogger.info("There is no file in the folder");
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		String k = lastModifiedFile.toString();

		System.out.println(lastModifiedFile);
		Path p = Paths.get(k);
		String oldfilefullpath = p.toString();
		System.out.println ("full filepath of old file : "+oldfilefullpath)  // use this for old file full path
		String oldfile = p.getFileName().toString();
		System.out.println("this is the name of the last modified file from the folder : "+oldfile)
		GlobalVariable.oldFileName=oldfilefullpath
		System.out.println("this is the fullname of the csv  file downloaded : "+GlobalVariable.oldFileName)
		//return oldfile;
	}

	@Keyword
	public static void fileRename() {
		String downloadFolder = Paths.get(System.getProperty("user.dir"), "OutputFiles")
		String newfilename = GlobalVariable.G_currentTCName+"_Manifest"
		File oldName = new File(GlobalVariable.oldFileName);
		File newName = new File(GlobalVariable.G_WebTabnameMyCartsvFileName);  //contains the renamed manifest file's full path + name with csv extension
		System.out.println("this is the name of the old file: "+oldName)
		System.out.println("this is the name of the new file: "+newName)

		oldName.renameTo(newName)


		//change the tab name to a desired value stored in global var and then use it to pass in the comparelists function in test script

	}


	@Keyword
	public static void csvToEXCEL(String csvFileName,String excelFileName) throws Exception{
		checkValidFile(csvFileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFileName)));
		HSSFWorkbook myWorkBook = new HSSFWorkbook();
		FileOutputStream writer = new FileOutputStream(new File(excelFileName) );
		HSSFSheet mySheet = myWorkBook.createSheet();
		String line= "";
		int rowNo=0;
		while ( (line=reader.readLine()) != null ){
			String[] columns = line.split(CSV_SEPERATOR_CHAR);
			HSSFRow myRow =mySheet.createRow(rowNo);
			for (int i=0;i<columns.length;i++){
				HSSFCell myCell = myRow.createCell(i);
				myCell.setCellValue(columns[i]);
			}
			rowNo++;
		}
		myWorkBook.write(writer);
		writer.close();
		System.out.println ("The file has been successfully converted from CSV to Excel")
		System.out.println ("Full name of the newly converted Excel file is : "+GlobalVariable.G_excelFileName)
	}

	// check if the file is valid and existing*******************************
	private static void checkValidFile(String fileName){
		boolean valid=true;
		try{
			File f = new File(fileName);
			if ( !f.exists() || f.isDirectory() ){
				valid=false;
			}
		}catch(Exception e){
			valid=false;
		}
		if ( !valid){
			System.out.println("File doesn't exist: " + fileName);
			System.exit(0);
		}
	}

	//**********************************************************************

	// this function converts the downloaded manifest currently in csv format >>> to xls format and adds a sheetname to it
	@Keyword
	public static void generateXLSfromCSV(String csvFilePath, String xlsFilePath, String xlsSheetnm) throws IOException{ //change this to sheet index

		System.out.println("This is the value from the function for csvfilename  : "+csvFilePath)
		System.out.println("This is the value from the function for excelfilename   : "+xlsFilePath)
		System.out.println("This is the value of the sheet name   : "+xlsSheetnm)
		//		String csvFilePath = GlobalVariable.G_WebTabnameMyCartsvFileName
		ArrayList arList=null;
		ArrayList al=null;
		String thisLine;
		FileInputStream fis = new FileInputStream(csvFilePath);
		DataInputStream myInput = new DataInputStream(fis);
		int i=0;
		arList = new ArrayList();
		while ((thisLine = myInput.readLine()) != null)
		{
			al = new ArrayList();
			String[] strar = thisLine.split(",")
			for(int j=0;j<strar.length;j++)
			{
				al.add(strar[j].replaceAll("[^\\x00-\\x7F]", ""));
			}
			arList.add(al);
			System.out.println();
			i++;
		}
		System.out.println("Arrlist: "+ arList)


		try {
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet(xlsSheetnm);
			for(int k=0;k<arList.size();k++)
			{
				ArrayList ardata = (ArrayList)arList.get(k);
				HSSFRow row = sheet.createRow((short) 0+k);
				for(int p=0;p<ardata.size();p++)
				{
					HSSFCell cell = row.createCell((short) p);
					String data = ardata.get(p).toString();
					if(data.startsWith("=")){
						cell.setCellType(Cell.CELL_TYPE_STRING);
						data=data.replaceAll("\"", "");
						data=data.replaceAll("=", "");
						cell.setCellValue(data);
					}else if(data.startsWith("\"")){
						data=data.replaceAll("\"", "");
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(data);
					}else{
						data=data.replaceAll("\"", "");
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(data);
					}
					//*/
					// cell.setCellValue(ardata.get(p).toString());
				}
				System.out.println();
			}
			FileOutputStream fileOut = new FileOutputStream(xlsFilePath);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your xls file has been generated : ");

		} catch ( Exception ex ) {
			System.out.println("from catch block   - This is the value of the sheetname   : "+xlsSheetnm)
			ex.printStackTrace();
		} //main method ends


	}




	//***************************************************APACHE XLS TO XLSX********************************
	// This function converts the .xls file to .xlsx file
	//public  void xlsTOxlsx(String inpFn, String outFn) {

	@Keyword
	public static void xlsTOxlsx(String inputxlsname, String outputxlsxname) {



		String inpFn = inputxlsname
		String outFn = outputxlsxname



		InputStream inp = new BufferedInputStream(new FileInputStream(inpFn));
		try {
			Workbook wbIn = new HSSFWorkbook(inp);
			File outF = new File(outFn);
			if (outF.exists())
				outF.delete();

			Workbook wbOut = new XSSFWorkbook();
			int sheetCnt = wbIn.getNumberOfSheets();
			for (int i = 0; i < sheetCnt; i++) {
				Sheet sIn = wbIn.getSheetAt(0);
				Sheet sOut = wbOut.createSheet(sIn.getSheetName()+i);
				Iterator<Row> rowIt = sIn.rowIterator();
				while (rowIt.hasNext()) {
					Row rowIn = rowIt.next();
					Row rowOut = sOut.createRow(rowIn.getRowNum());

					Iterator<Cell> cellIt = rowIn.cellIterator();
					while (cellIt.hasNext()) {
						Cell cellIn = cellIt.next();
						Cell cellOut = rowOut.createCell(
								cellIn.getColumnIndex(), cellIn.getCellType());

						switch (cellIn.getCellType()) {
							case Cell.CELL_TYPE_BLANK:
								break;

							case Cell.CELL_TYPE_BOOLEAN:
								cellOut.setCellValue(cellIn.getBooleanCellValue());
								break;

							case Cell.CELL_TYPE_ERROR:
								cellOut.setCellValue(cellIn.getErrorCellValue());
								break;

							case Cell.CELL_TYPE_FORMULA:
								cellOut.setCellFormula(cellIn.getCellFormula());
								break;

							case Cell.CELL_TYPE_NUMERIC:
								cellOut.setCellValue(cellIn.getNumericCellValue());
								break;

							case Cell.CELL_TYPE_STRING:
								cellOut.setCellValue(cellIn.getStringCellValue());
								break;
						}

						CellStyle styleIn = cellIn.getCellStyle();
						CellStyle styleOut = cellOut.getCellStyle();
						styleOut.setDataFormat(styleIn.getDataFormat());

						cellOut.setCellComment(cellIn.getCellComment());

					} // inner while loop ends
				}// outer while loop ends
			}
			OutputStream out = new BufferedOutputStream(new FileOutputStream(outF));
			try {
				wbOut.write(out);
			} finally {
				out.close();
			}
		} finally {
			inp.close();

		}


	}

	//********This function copies the specified sheet to the same workbook in XLS format****************
	@Keyword
	public static void copySheetXLS (String fileNm, String SheetNm){ //change to sheet index ?
		//String excelname =   GlobalVariable.G_excelFileName
		//String excelname =   "C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\file_xlsx.xlsx"
		//String newSheetname = "newManifestData"

		FileInputStream fis = new FileInputStream(fileNm);
		HSSFWorkbook workbook = new HSSFWorkbook(fis); // Create an excel workbook from the file system.
		HSSFSheet sheet = workbook.getSheetAt(0);
		//workbook = WorkbookFactory.create(new FileInputStream(excelname));
		//  Workbook wbk = WorkbookFactory.create(excelname);
		//XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//XSSFSheet sheet = workbook.getSheetAt(0);
		workbook.cloneSheet(0);
		workbook.setSheetName(1,SheetNm)
		System.out.println ("Sheet copied successfully. Name of the newly created sheet is : "+SheetNm)
		//workbook.save("C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\file_xlsx.xls");
		FileOutputStream out = new FileOutputStream(fileNm);
		workbook.write(out);
		out.close();
	}

	//********This function copies the specified sheet to the same workbook in XLSX format****************
	@Keyword
	public static void copySheetXLSX (String fileNm, String SheetNm){
		//String excelname =   GlobalVariable.G_excelFileName
		//String excelname =   "C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\file_xlsx.xlsx"
		//String newSheetname = "newManifestData"

		FileInputStream fis = new FileInputStream(fileNm);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
		XSSFSheet sheet = workbook.getSheetAt(0);
		//workbook = WorkbookFactory.create(new FileInputStream(excelname));
		//  Workbook wbk = WorkbookFactory.create(excelname);
		//XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//XSSFSheet sheet = workbook.getSheetAt(0);
		workbook.cloneSheet(0);
		workbook.setSheetName(1,SheetNm)
		System.out.println ("Sheet copied successfully. Name of the newly created sheet is : "+SheetNm)
		//workbook.save("C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\file_xlsx.xls");
		FileOutputStream out = new FileOutputStream(fileNm);
		workbook.write(out);
	}
	//********************************************************************************

	//@Keyword
	//public static void deleteColumn(int SheetIndex, int columnToDelete ){
	//
	//	String excelname =   "C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\deleteColumn.xlsx"
	//	FileInputStream fis = new FileInputStream(excelname);
	//	XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
	//	XSSFSheet sheet = workbook.getSheetAt(SheetIndex);
	//
	//	int maxColumn = 0;
	//	for ( int r=0; r < sheet.getLastRowNum()+1; r++ ){
	//		Row row = sheet.getRow( r );
	//
	//		// if no row exists here; then nothing to do; next!
	//		if ( row == null )
	//			continue;
	//
	//		// if the row doesn't have this many columns then we are good; next!
	//		int lastColumn = row.getLastCellNum();
	//		if ( lastColumn > maxColumn )
	//			maxColumn = lastColumn;
	//
	//		if ( lastColumn < columnToDelete )
	//			continue;
	//
	//		for ( int x=columnToDelete+1; x < lastColumn + 1; x++ ){
	//			Cell oldCell    = row.getCell(x-1);
	//			if ( oldCell != null )
	//				row.removeCell( oldCell );
	//
	//			Cell nextCell   = row.getCell( x );
	//			if ( nextCell != null ){
	//				Cell newCell    = row.createCell( x-1, nextCell.getCellType() );
	//				cloneCell(newCell, nextCell);
	//			}
	//		}
	//		FileOutputStream out = new FileOutputStream(excelname);
	//		workbook.write(out);
	//		out.close();
	//	}
	//}

	//*************This function deletes the unwanted columns from the excel in xls format **************************
	@Keyword
	public static void deleteCol(String filenm){
		//public  void deleteCol(String filenm, String filetype){
		System.out.println("In delete column function")
		//		if (filetype == 'CartWebData'){
		//			FileInputStream fis = new FileInputStream(filenm);
		//			XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
		//			XSSFSheet sheet = workbook.getSheetAt(1);
		//			ArrayList<Integer> colsToDelete = new ArrayList<Integer>(){{add(5);add(4);add(3);add(2);add(1)}}; //these index are for mycartwebdata excel
		//			Collections.sort(colsToDelete)
		//			Collections.reverse(colsToDelete)
		//			for(int colToDelete: colsToDelete){
		//				deleteColumn(sheet,colToDelete);
		//			}
		//			printXLSX(sheet)
		//			fis.close();
		//			System.out.println("Saving the file")
		//			FileOutputStream fos = new FileOutputStream(new File(filenm));
		//			workbook.write(fos);
		//			printXLSX(sheet)
		//			fos.close()
		//		}else if (filetype == 'manifestData')
		//		{
		System.out.println ("This is the value of filename from manifestdata : "+filenm)
		FileInputStream fis = new FileInputStream(filenm);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
		XSSFSheet sheet = workbook.getSheetAt(1); //Deletes from sheet index 1, that is the second sheet in the workbook
		//			HSSFWorkbook workbook = new HSSFWorkbook(fis); // Create an excel workbook from the file system.
		//			HSSFSheet sheet = workbook.getSheetAt(1);
		ArrayList<Integer> colsToDelete = new ArrayList<Integer>(){{add(5);add(4);add(3)}}; //these index are for manifest excel
		Collections.sort(colsToDelete)
		Collections.reverse(colsToDelete)
		for(int colToDelete: colsToDelete){
			deleteColumn(sheet,colToDelete);
		}
		fis.close();
		System.out.println("Saving the file")
		FileOutputStream fos = new FileOutputStream(new File(filenm));
		workbook.write(fos);
		printXLSX(sheet)
		fos.close()

		//		}

	}

	@Keyword
	public static void deleteColumn( Sheet sheet, int columnToDelete){
		System.out.println("Column to delete: " + columnToDelete)
		int maxColumn = 0;
		for ( int r=0; r < sheet.getLastRowNum()+1; r++ ){
			Row row = sheet.getRow( r );

			// if no row exists here; then nothing to do; next!
			if ( row == null )
				continue;

			// if the row doesn't have this many columns then we are good; next!
			int lastColumn = row.getLastCellNum();
			if ( lastColumn > maxColumn )
				maxColumn = lastColumn;

			if ( lastColumn < columnToDelete )
				continue;

			for ( int x=columnToDelete+1; x < lastColumn + 1; x++ ){
				Cell oldCell    = row.getCell(x-1);
				if ( oldCell != null ){
					row.removeCell( oldCell );
				}

				Cell nextCell   = row.getCell(x);
				if ( nextCell != null ){
					Cell newCell    = row.createCell( x-1, nextCell.getCellType() );
					cloneCell(newCell, nextCell);
					//				sheet.setColumnWidth( x-1, sheet.getColumnWidth(x) );
				}
			}
		}

		// Adjust the column widths
		for ( int c=0; c < maxColumn; c++ ){
			sheet.setColumnWidth( c, sheet.getColumnWidth(c+1) );
		}
		//	workbook.saveToFile(excelname);
	}

	@Keyword
	public static void printXLS(HSSFSheet sheet){
		System.out.println("Printing contents of sheet...... ")
		for (Row myrow:sheet){
			for(Cell myCell:myrow){
				System.out.print(myCell.getStringCellValue() + " ")
			}
			System.out.println()
		}
	}


	@Keyword
	public static void printXLSX(XSSFSheet sheet){
		System.out.println("Printing contents of sheet...... ")
		for (Row myrow:sheet){
			for(Cell myCell:myrow){
				System.out.print(myCell.getStringCellValue() + " ")
			}
			System.out.println()
		}
	}

	/*
	 * Takes an existing Cell and merges all the styles and forumla
	 * into the new one
	 */
	@Keyword
	private static void cloneCell( Cell cNew, Cell cOld ){
		cNew.setCellComment( cOld.getCellComment() );
		cNew.setCellStyle( cOld.getCellStyle() );

		switch ( cNew.getCellType() ){
			case Cell.CELL_TYPE_BOOLEAN:
				cNew.setCellValue( cOld.getBooleanCellValue() );
				break;

			case Cell.CELL_TYPE_NUMERIC:
				cNew.setCellValue( cOld.getNumericCellValue() );
				break;

			case Cell.CELL_TYPE_STRING:
				cNew.setCellValue( cOld.getStringCellValue() );
				break;

			case Cell.CELL_TYPE_ERROR:
				cNew.setCellValue( cOld.getErrorCellValue() );
				break;

			case Cell.CELL_TYPE_FORMULA:
				cNew.setCellFormula( cOld.getCellFormula() );
				break;

		}
	}


	@Keyword
	public static void selectCols(String filenm) {
		File file = new File(filenm);
		Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
		Sheet sheet = workbook.getSheetAt(0);
		int column_index_1 = 0;
		int column_index_2 = 0;
		int column_index_3 = 0;
		Row row = sheet.getRow(0);
		for (Cell cell : row) {
			// Column header names.
			switch (cell.getStringCellValue()) {
				case "File Name":
					column_index_1 = cell.getColumnIndex();
					break;
				case "Case ID":
					column_index_2 = cell.getColumnIndex();
					break;
				case "Study Code":
					column_index_3 = cell.getColumnIndex();
					break;
			}
		}

		for (Row r : sheet) {
			if (r.getRowNum()==0) continue;//hearders
			Cell c_1 = r.getCell(column_index_1);
			Cell c_2 = r.getCell(column_index_2);
			Cell c_3 = r.getCell(column_index_3);
			if (c_1 != null && c_1.getCellType() != Cell.CELL_TYPE_BLANK
			&&c_2 != null && c_2.getCellType() != Cell.CELL_TYPE_BLANK
			&&c_3 != null && c_3.getCellType() != Cell.CELL_TYPE_BLANK) {
				System.out.print("  "+c_1 + "   " + c_2+"   "+c_3+"\n");
			}
		}

	}



	//******************************************************************
	//COPY TO SAME WORKBOOK
	//******************************************************************
	public static void copySheetToSameWkbook() throws IOException {
		String fname = "C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\file_xlsx.xlsx"
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fname));
		XSSFWorkbook workbook = new XSSFWorkbook(bis);
		XSSFWorkbook myWorkBook = new XSSFWorkbook();
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		XSSFSheet mySheet = null;
		XSSFRow myRow = null;
		XSSFCell myCell = null;

		//	HSSFWorkbook workbook = new HSSFWorkbook(bis);
		//	HSSFWorkbook myWorkBook = new HSSFWorkbook();
		//	HSSFSheet sheet = null;
		//	HSSFRow row = null;
		//	HSSFCell cell = null;
		//	HSSFSheet mySheet = null;
		//	HSSFRow myRow = null;
		//	HSSFCell myCell = null;
		int sheets = workbook.getNumberOfSheets();
		int fCell = 0;
		int lCell = 0;
		int fRow = 0;
		int lRow = 0;
		for (int iSheet = 0; iSheet < sheets; iSheet++) {
			sheet = workbook.getSheetAt(iSheet);
			if (sheet != null) {
				mySheet = myWorkBook.createSheet(sheet.getSheetName());
				fRow = sheet.getFirstRowNum();
				lRow = sheet.getLastRowNum();
				for (int iRow = fRow; iRow <= lRow; iRow++) {
					row = sheet.getRow(iRow);
					myRow = mySheet.createRow(iRow);
					if (row != null) {
						fCell = row.getFirstCellNum();
						lCell = row.getLastCellNum();
						for (int iCell = fCell; iCell < lCell; iCell++) {
							cell = row.getCell(iCell);
							myCell = myRow.createCell(iCell);
							if (cell != null) {
								myCell.setCellType(cell.getCellType());
								switch (cell.getCellType()) {
									case XSSFCell.CELL_TYPE_BLANK:
									//case HSSFCell.CELL_TYPE_BLANK:
										myCell.setCellValue("");
										break;
									case XSSFCell.CELL_TYPE_BOOLEAN:
									//case HSSFCell.CELL_TYPE_BOOLEAN:
										myCell.setCellValue(cell.getBooleanCellValue());
										break;

									case XSSFCell.CELL_TYPE_ERROR:
									//case HSSFCell.CELL_TYPE_ERROR:
										myCell.setCellErrorValue(cell.getErrorCellValue());
										break;

									case XSSFCell.CELL_TYPE_FORMULA:
									//case HSSFCell.CELL_TYPE_FORMULA:
										myCell.setCellFormula(cell.getCellFormula());
										break;

									case XSSFCell.CELL_TYPE_NUMERIC:
									//case HSSFCell.CELL_TYPE_NUMERIC:
										myCell.setCellValue(cell.getNumericCellValue());
										break;

									case XSSFCell.CELL_TYPE_STRING:
									//case HSSFCell.CELL_TYPE_STRING:
										myCell.setCellValue(cell.getStringCellValue());
										break;
									default:
										myCell.setCellFormula(cell.getCellFormula());
								}
							}
						}
					}
				}
			}
		}
		bis.close();
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copiedxlsx.xlsx", true));
		myWorkBook.write(bos);
		bos.close();
	}


	@Keyword
	public static void deleteFiles() {
		//			Path csvpath = FileSystems.getDefault().getPath("./src/test/resources/newFile.txt");
		//			Path xlspath = FileSystems.getDefault().getPath("./src/test/resources/newFile.txt");
		try{
			Files.deleteIfExists(GlobalVariable.G_WebTabnameMyCartsvFileName);
			Files.deleteIfExists(GlobalVariable.G_excelFileName);
		} catch (IOException x) {
			System.err.println(x);
		}
	}
}