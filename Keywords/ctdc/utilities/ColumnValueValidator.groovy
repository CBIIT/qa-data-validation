package ctdc.utilities
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ColumnValueValidator {
	public static void excelColCheck(String filePath, String sheetName, int columnIndex, double minValue, double maxValue) {
		 filePath ; //value of filepath for webdatafile is GlobalVariable.G_WebExcel;
		 sheetName ; // Adjust this to the name of the sheet you want to validate
		 columnIndex ; // Adjust this to the column index you want to validate
		 minValue ;
		 maxValue ;

		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			for (Row row : sheet) {
				Cell cell = row.getCell(columnIndex);
				if (cell != null) {
					double valueToValidate = cell.getNumericCellValue();
					if (valueToValidate >= minValue && valueToValidate <= maxValue) {
						System.out.println("Data in the Age column is Valid: " + valueToValidate);
					} else {
						System.out.println("Data in the Age column contains Invalid not specified in the limits: " + valueToValidate);
					}
				}
			}

			fis.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}