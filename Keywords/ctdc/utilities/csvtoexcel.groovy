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

import internal.GlobalVariable
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.*;


public class csvtoexcel {
	public static void main(String[] args) {
		String downloadFolder = Paths.get(System.getProperty("user.dir"), "OutputFiles");

		String xlsxfilename = GlobalVariable.G_currentTCName+"_Manifest.xlsx";
		String csvFilePath = GlobalVariable.oldFileName  //this comes from the picklatesfromdownloads in fileoperations.groovy
		String excelFilePath = Paths.get(System.getProperty("user.dir"), "OutputFiles", xlsxfilename);

		try {
			convertCSVToExcel(csvFilePath, excelFilePath);
			System.out.println("Conversion completed successfully.");
			System.out.println("Converted filename is : " + excelFilePath);
		} catch (IOException e) {
			System.out.println("An error occurred during the conversion: " + e.getMessage());
		}
	}

	private static void convertCSVToExcel(String csvFilePath, String excelFilePath) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet1");

		BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));
		String line;
		int rowNumber = 0;

		while ((line = bufferedReader.readLine()) != null) {
			String[] columns = line.split(",");
			Row row = sheet.createRow(rowNumber++);
			int columnNumber = 0;

			for (String column : columns) {
				Cell cell = row.createCell(columnNumber++);
				cell.setCellValue(column);
			}
		}

		bufferedReader.close();

		FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
	}
}


