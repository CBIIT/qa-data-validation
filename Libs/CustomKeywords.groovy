
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import java.lang.String

import org.openqa.selenium.WebDriver

import org.openqa.selenium.WebElement


def static "ctdc.utilities.ConnectDB.main"() {
    (new ctdc.utilities.ConnectDB()).main()
}

def static "ctdc.utilities.sandbox_g.createWebDataCSV"() {
    (new ctdc.utilities.sandbox_g()).createWebDataCSV()
}

def static "ctdc.utilities.sandbox_g.comparison"() {
    (new ctdc.utilities.sandbox_g()).comparison()
}

def static "ctdc.utilities.sandbox_g.readingCSVFile"() {
    (new ctdc.utilities.sandbox_g()).readingCSVFile()
}

def static "ctdc.utilities.ReadExcel.Test"(
    	String filename	) {
    (new ctdc.utilities.ReadExcel()).Test(
        	filename)
}

def static "ctdc.utilities.ReadExcel.readExceltoWeblist"(
    	String filename	
     , 	String sheetName	) {
    (new ctdc.utilities.ReadExcel()).readExceltoWeblist(
        	filename
         , 	sheetName)
}

def static "ctdc.utilities.ReadExcel.Neo4j"() {
    (new ctdc.utilities.ReadExcel()).Neo4j()
}

def static "ctdc.utilities.ReadExcel.initialLoad"() {
    (new ctdc.utilities.ReadExcel()).initialLoad()
}

def static "ctdc.utilities.ReadExcel.PrintG"() {
    (new ctdc.utilities.ReadExcel()).PrintG()
}

def static "ctdc.utilities.ReadExcel.ExcelToArray"(
    	String filename	) {
    (new ctdc.utilities.ReadExcel()).ExcelToArray(
        	filename)
}

def static "ctdc.utilities.RunTestcase.Run"(
    	String InputExcelname	
     , 	String pwd_file	) {
    (new ctdc.utilities.RunTestcase()).Run(
        	InputExcelname
         , 	pwd_file)
}

def static "ctdc.utilities.RunTestcase.ReadCasesTable"(
    	WebDriver driver	) {
    (new ctdc.utilities.RunTestcase()).ReadCasesTable(
        	driver)
}

def static "ctdc.utilities.RunTestcase.compareLists"() {
    (new ctdc.utilities.RunTestcase()).compareLists()
}

def static "ctdc.utilities.RunTestcase.browserDriver"(
    	String browserName	) {
    (new ctdc.utilities.RunTestcase()).browserDriver(
        	browserName)
}

def static "ctdc.utilities.ExtraFunctions.compareLists_1D"() {
    (new ctdc.utilities.ExtraFunctions()).compareLists_1D()
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
