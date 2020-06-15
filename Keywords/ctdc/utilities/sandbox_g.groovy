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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.util.concurrent.TimeUnit;

import internal.GlobalVariable

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver as FirefoxDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.*
import java.lang.String as String
import groovy.transform.Field as Field


import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions as ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import java.util.*
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileDescriptor
import java.io.File
import java.lang.String
import java.lang.Object
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.String as String
import groovy.transform.Field as Field
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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import static org.junit.Assert.*;
import internal.GlobalVariable
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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import internal.GlobalVariable
import java.util.*;
import com.opencsv.CSVWriter;
import java.lang.String;
import java.io.PrintWriter
import java.util.Arrays

import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import com.kms.katalon.core.webui.driver.DriverFactory

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.io.BufferedReader;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.opencsv.CSVReader;   // added these for reading and writing csv
import com.opencsv.CSVWriter;
import java.io.FileReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import ctdc.utilities.ReadExcel  // importing this class to use the read excel function
import ctdc.utilities.RunTestcase //

//*************To create a csv file*********************************************
public class sandbox_g {

	/*	private static class Canine { 
	 private String CaseID; 
	 private String StudyCode; 
	 private String StudyType; 
	 public Country(String CaseID, String StudyCode, String StudyType) {
	 this.CaseID = CaseID; 
	 this.StudyCode = StudyCode;
	 this.StudyType = StudyType; 
	 } 
	 public String CaseID() { 
	 return CaseID; 
	 };
	 public String StudyCode() { 
	 return StudyCode; 
	 } 
	 public String StudyType() {
	 return StudyType; 
	 } 
	 @Override 
	 public String toString() {
	 return "Country [Case ID=" + CaseID + ", Study Code=" + StudyCode + ", Study Type=" + StudyType + "]"; 
	 } 
	 } */

	//*******************************************
	@Keyword
	public void canineUIValidation(){
//		WebUI.waitForElementPresent(findTestObject('Canine/NavBar/Canine_Cases_Btn'), 5)
//		//		WebUI.click(findTestObject('Canine/NavBar/Canine_Cases_Btn'))
	//have all the elements stored in a key value pair and pass the value	
		if(driver.findElement(By.xpath("value"))!= null){
			System.out.println("Element is Present");
			}else{
			System.out.println("Element is Absent");
			}
	}
	
	public void trialsUIValidation(){
	
    }
	
	
	@Keyword
	public void footerVal(){
		
	}
	
	
	@Keyword
	public void headerVal(){
		
	}
	
	//*******************************
	@Keyword
	public static void createWebDataCSV(){
		Path filepath = Paths.get(System.getProperty("user.dir"), "TestData", "firstCSVfile.csv");
		String filename = filepath.toString()
		List<String> writeData = new ArrayList<String>();
		writeData = GlobalVariable.G_CaseData
		System.out.println("this is the case data stored in global variable : "+ writeData)
		System.out.println("This is the size of the case data arraylist : "+writeData.size())
		CSVWriter writer = new CSVWriter(new FileWriter(filename));

		// String [] header = "4||David||Miller||Australia||30".split("\\|\\|");  //Pipe symbol is a metacharacter in regex. You need to excape it. otherwise it will print each alpha in a cell
		//Write the record to file
		// writer.writeNext(header);    //writer.writeAll(cellD);
		for( int i = 0; i < writeData.size(); i++ ){
			writer.writeNext(writeData.get(i).split("\\|\\|"));
		}//for loop of i
		writer.close();  //close the writer
	}

	//***********************************************
	//@Keyword
	//public void readCSVLinebyline(){
	/*
	 //  C:\Users\radhakrishnang2\Desktop\DataCommons_Automation\CTDC_Automation\TestData\DatabaseCSVfile.csv
	 //  C:\Users\radhakrishnang2\Desktop\DataCommons_Automation\CTDC_Automation\TestData\WebCSVfile.csv
	 //first reads and then stores as arraylist of string and then compares
	 //import au.com.bytecode.opencsv.CSVReader;
	 Path filepath = Paths.get(System.getProperty("user.dir"), "TestData", "WebCSVfile.csv");
	 String filename = filepath.toString()
	 System.out.println("********************Reading CSV line by line ****************************************")
	 //Build reader instance
	 //Read data.csv
	 //Default seperator is comma
	 //Default quote character is double quote
	 //Start reading from line number 2 (line numbers start from zero)
	 //String[] arr = list.toArray(new String[list.size()])
	 CSVReader reader = new CSVReader(new FileReader(filename));
	 //Read CSV line by line and use the string array as you want
	 String[] nextLine;
	 while ((nextLine = reader.readNext()) != null) {
	 if (nextLine != null) {
	 //Verifying the read data here
	 System.out.println(Arrays.toString(nextLine));
	 }
	 }
	 System.out.println("This is the contents of the string array after reading csv : "+nextLine)
	 System.out.println("This is the size of the string array which stores the read csv : "+nextLine.size())
	 */

	@Keyword
	public void comparison(){
		// Do not open the CSV Files in excel format. Excel attaches special
		// characters in the file while saving. Always open the
		// csv files in notepad++.
		// Rename the comparing file with your name

		List <String[]> file1List = readCSV("CSVfile1.csv")
		List <String[]> file2List = readCSV("CSVfile2.csv")
		//printCSVFileContents(file1List)
		//printCSVFileContents(file2List)

		List<Boolean> res=new ArrayList<Boolean>();

		int smallerFileSize = file1List.size() > file2List.size() ? file1List.size() : file2List.size()
		System.out.println("File1 List size: " + file1List.size() + " File2 List Size: " + file2List.size() )
		String fileSizeMatch = file1List.size() == file2List.size() ? "match" : "do not match"
		System.out.println( "File size " + fileSizeMatch )

		for( int i = 0; i < smallerFileSize; i++ ){
			//System.out.println("Comparing row: " + i)
			boolean rowMatch = true
			for( int j = 0;j < file1List.get(i).size(); j++ ){
				if( ! ( file1List.get(i)[j].trim().equals(file2List.get(i)[j].trim() ) ) ){
					rowMatch = false
					System.out.println( "File1[" + i + "][" + j + "]: " + file1List.get(i)[j] + " File2[" + i + "][" + j + "]: " + file2List.get(i)[j] )
					System.out.println("Col: " + ( j + 1 ) + " does not match for row: " + ( i + 1 ) )
					break
				}
			}
			res.add(rowMatch)
		}
	}

	public void printCSVFileContents(fileList){
		System.out.println("Printing contents")
		for(int i = 0; i < fileList.size(); i++ ){
			System.out.println("Contents of row: " + i)
			for( int j = 0; j < fileList.get(i).size(); j++ ){
				System.out.print( fileList.get(i)[j] + "," )
			}
		}
	}


	public static List<String[]> readCSV(csvFile) throws FileNotFoundException, IOException {
		System.out.println("Filename is: "+ csvFile)
		Path filepath = Paths.get(System.getProperty("user.dir"), "TestData", csvFile);
		String filename = filepath.toString()
		List <String[]>canine = new ArrayList<String[]>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		// Check whether you need to ignore the header
		//String line = br.readLine(); // Reading header, Ignoring
		String line = ""
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split(",");
			canine.add(fields);
			/*for(String field:fields)
			 System.out.println("Field: " + field)*/ 
			//String CaseID = fields[0];
			//String StudyCode = fields[1];
			//String StudyType = fields[2];
			//Canine displRecrd = new Canine(CaseID, StudyCode, StudyType);
			//countries.add(displRecrd);
		}
		br.close();
		return canine;
	}



	@Keyword
	public void readingCSVFile() {
		try {
			// create a reader
			Path filepath = Paths.get(System.getProperty("user.dir"), "TestData", "firstCSVfile.csv");
			String filename = filepath.toString()
			Reader reader = Files.newBufferedReader(filename);

			// read csv file
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader()
					.withIgnoreHeaderCase()
					.withTrim()
					.parse(reader);

			for (CSVRecord record : records) {
				System.out.println("Record #: " + record.getRecordNumber());
				System.out.println("Case ID: " + record.get("ID"));
				System.out.println("Study Code: " + record.get("Name"));
				System.out.println("Study Type: " + record.get("Email"));
				//System.out.println("Country: " + record.get("Country"));
			}

			// close the reader
			reader.close();
		}catch(Exception e){
			e.printStackTrace();
		}


	}

}//class ends