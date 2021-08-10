
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import org.apache.poi.ss.usermodel.Sheet

import org.apache.poi.hssf.usermodel.HSSFSheet

import org.apache.poi.xssf.usermodel.XSSFSheet

import org.apache.poi.ss.usermodel.Cell

import org.openqa.selenium.WebElement



def static "ctdc.utilities.ExtraFunctions.compareLists_1D"() {
    (new ctdc.utilities.ExtraFunctions()).compareLists_1D()
}


def static "ctdc.utilities.FileOperations.pickLatestFileFromDownloads"() {
    (new ctdc.utilities.FileOperations()).pickLatestFileFromDownloads()
}


def static "ctdc.utilities.FileOperations.fileRename"() {
    (new ctdc.utilities.FileOperations()).fileRename()
}


def static "ctdc.utilities.FileOperations.csvToEXCEL"(
    	String csvFileName	
     , 	String excelFileName	) {
    (new ctdc.utilities.FileOperations()).csvToEXCEL(
        	csvFileName
         , 	excelFileName)
}


def static "ctdc.utilities.FileOperations.generateXLSfromCSV"(
    	String XLSSheetnm	) {
    (new ctdc.utilities.FileOperations()).generateXLSfromCSV(
        	XLSSheetnm)
}


def static "ctdc.utilities.FileOperations.copySheetXLS"(
    	String fileNm	
     , 	String SheetNm	) {
    (new ctdc.utilities.FileOperations()).copySheetXLS(
        	fileNm
         , 	SheetNm)
}


def static "ctdc.utilities.FileOperations.copySheetXLSX"(
    	String fileNm	
     , 	String SheetNm	) {
    (new ctdc.utilities.FileOperations()).copySheetXLSX(
        	fileNm
         , 	SheetNm)
}


def static "ctdc.utilities.FileOperations.deleteCol"(
    	String filenm	
     , 	String filetype	) {
    (new ctdc.utilities.FileOperations()).deleteCol(
        	filenm
         , 	filetype)
}


def static "ctdc.utilities.FileOperations.deleteColumn"(
    	Sheet sheet	
     , 	int columnToDelete	) {
    (new ctdc.utilities.FileOperations()).deleteColumn(
        	sheet
         , 	columnToDelete)
}


def static "ctdc.utilities.FileOperations.printXLS"(
    	HSSFSheet sheet	) {
    (new ctdc.utilities.FileOperations()).printXLS(
        	sheet)
}


def static "ctdc.utilities.FileOperations.printXLSX"(
    	XSSFSheet sheet	) {
    (new ctdc.utilities.FileOperations()).printXLSX(
        	sheet)
}


def static "ctdc.utilities.FileOperations.cloneCell"(
    	Cell cNew	
     , 	Cell cOld	) {
    (new ctdc.utilities.FileOperations()).cloneCell(
        	cNew
         , 	cOld)
}


def static "ctdc.utilities.FileOperations.selectCols"(
    	String filenm	) {
    (new ctdc.utilities.FileOperations()).selectCols(
        	filenm)
}


def static "ctdc.utilities.FileOperations.deleteFiles"() {
    (new ctdc.utilities.FileOperations()).deleteFiles()
}


def static "ctdc.utilities.sandbox.CaseData"() {
    (new ctdc.utilities.sandbox()).CaseData()
}


def static "ctdc.utilities.sandbox.getall"() {
    (new ctdc.utilities.sandbox()).getall()
}


def static "ctdc.utilities.sandbox.getElementID"(
    	WebElement Tab	
     , 	String caseid	) {
    (new ctdc.utilities.sandbox()).getElementID(
        	Tab
         , 	caseid)
}


def static "ctdc.utilities.sandbox.clicking"() {
    (new ctdc.utilities.sandbox()).clicking()
}


def static "ctdc.utilities.sandbox.tablesize"() {
    (new ctdc.utilities.sandbox()).tablesize()
}
