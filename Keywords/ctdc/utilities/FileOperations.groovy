package ctdc.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.nio.file.Path;
import java.nio.file.Paths;

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
import java.nio.file.Paths
import java.nio.file.Path
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.lang.Object;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import internal.GlobalVariable


public class FileOperations {

	private static final String CSV_SEPERATOR_CHAR=",";

	@Keyword
	public String pickLatestFileFromDownloads() {

		String downloadFolder = Paths.get(System.getProperty("user.dir"), "OutputFiles")
		String newfilename = GlobalVariable.G_currentTCName+"_Manifest"
		String newfilefullpath = Paths.get(System.getProperty("user.dir"), "OutputFiles", newfilename)
		String excelFilefullpath = newfilefullpath +".xlsx"
		GlobalVariable.excelFileName = excelFilefullpath;
		newfilefullpath = newfilefullpath+".csv"  //otherwise it will be different from x-csv format and cant be opened in excel
		System.out.println("This is the name of the current test case from global variable: " +GlobalVariable.G_currentTCName);
		System.out.println("This is the full path of the new file : "+newfilefullpath)  //use this for new file full path
		GlobalVariable.newFileName = newfilefullpath;

		File dir = new File(downloadFolder);
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
		return oldfile;
	}

	@Keyword
	public static void fileRename() {
		String downloadFolder = Paths.get(System.getProperty("user.dir"), "OutputFiles")
		String newfilename = GlobalVariable.G_currentTCName+"_Manifest"
		System.out.println("this is the name of the old : "+GlobalVariable.oldFileName)
		System.out.println("this is the name of the new: "+GlobalVariable.newFileName)

		File oldName = new File(GlobalVariable.oldFileName);
		File newName = new File(GlobalVariable.newFileName);
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
}