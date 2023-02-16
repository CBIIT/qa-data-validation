package ctdc.utilities
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


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


class functions extends runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}

	//	public static WebDriver driver
	//	public static WebElement nxtBtn

	//variables
	//	static switchCanine
	//	static switchTrials
	//	static switchBento
	//	static switchGMB
	//	static switchCDS
	//	static String switchINS
	//	static String switchString
	//	static WebElement nextButton
	//	static WebElement nxtBtn
	//	static WebElement resultTab


	/**
	 * This function reads the results table and writes the web and database data to excel
	 * This function also verifies the stat-bar counts and compares the web and database excels
	 * @param appName
	 * @param statVal
	 * @param tbl
	 * @param tblHdr
	 * @param nxtBtn
	 * @param webdataSheetName
	 * @param dbdataSheetName
	 * @param tabQuery
	 * @throws IOException
	 */
	@Keyword
	public static void multiFunction(String appName, String statVal, String tbl, String tblHdr, String nxtBtn, String webdataSheetName, String dbdataSheetName, String tabQuery) throws IOException {
		System.out.println("This is the value of stat (string) obtained from multifunction: " + statVal);
		int statValue = convStringtoInt(statVal);
		System.out.println("This is the value of stat (integer) obtained from multifunction: " + statValue);

		if (statValue !=0) {
			ReadCasesTableKatalon(statVal, tbl,tblHdr,nxtBtn,webdataSheetName)
			System.out.println("control is after read table webdataxl creation and before readexcel neo4j function")
			ReadExcel.Neo4j(dbdataSheetName,tabQuery)
			System.out.println("control is before compare lists function from multifunction")
			compareLists(webdataSheetName, dbdataSheetName)
			System.out.println("control is before validate stat bar function from multifunction")
			validateStatBar(appName)
		}else {
			System.out.println("Skipping data collection from neo4j and compare lists of web and db as the stat value is 0")
		}
	}

	
	
	
	/**
	 * This function reads cases table
	 * @param statVal1
	 * @param tbl1
	 * @param hdr1
	 * @param nxtb1
	 * @param webSheetName
	 * @throws IOException
	 */
	@Keyword
	public static void ReadCasesTableKatalon(String statVal1, String tbl1, String hdr1, String nxtb1, String webSheetName) throws IOException {
		String switchCanine
		String switchTrials
		String switchBento
		String switchGMB
		String switchCDS
		String switchINS
		String switchString
		WebElement nextButton
		WebElement nxtBtn
		WebElement resultTab

		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("This is the stat value of cases/total (in case of cart) before converting to int: "+statVal1)
		int statValue = convStringtoInt(statVal1);
		System.out.println("This is the passed value of stat for this run : "+statValue)


		List<String> webData = new ArrayList<String>();  //this is not used
		List<String> wTableHdrData = new ArrayList<String>(); //to capture the table header data
		List<String> wTableBodyData = new ArrayList<String>(); //to capture the table body data
		String tbl_bdy;
		String tbl_main= givexpath(tbl1)
		System.out.println("This is the value of tbl main : "+tbl_main)

		tbl_bdy= tbl_main+"//tbody"
		//tbl_bdy= tbl_main+"/tbody"  //this is for INS
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy  //correct his variables name typo and also rename it to G_commons_casetblbdy
		System.out.println("This is the value of table body :"+GlobalVariable.G_cannine_caseTblBdy)

		//	driver.manage().window().maximize()  commenting to check the error in INS
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tbl_bdy)));
		scrolltoViewjs(driver.findElement(By.xpath(tbl_bdy)))
		System.out.println("Scrolled into view and ready to click again")
		clickElement(driver.findElement(By.xpath(tbl_bdy)));
		System.out.println("using jscriptexec, clicked again")

		WebElement TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
		List<WebElement> rows_table = TableBdy.findElements(By.tagName("tr"))
		System.out.println("This is the value of weblement rows table :"+rows_table);

		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the results table in first page: "+(rows_count))
		String nxt_str=     givexpath(nxtb1)
		System.out.println("This is the value of the xpath of nextbtn : "+nxt_str)
		nextButton = driver.findElement(By.xpath(nxt_str));
		System.out.println("This is the value of the webelem next button from readcasestablekatalon method : "+nextButton)
		System.out.println("This is the value of the hdr object: "+hdr1)
		String hdr_str= givexpath(hdr1)
		System.out.println("This is the value of the hdr string - xpath : "+hdr_str)
		WebElement tableHdr = driver.findElement(By.xpath(hdr_str))

		List<WebElement> colHeader = tableHdr.findElements(By.tagName("th"));

		int columns_count
		String hdrdata = ""

		//Read ICDC table header from result table for a specific tab
		if(((driver.getCurrentUrl()).contains("caninecommons")||(driver.getCurrentUrl()).contains("icdc.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/studies"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Canine/Canine_StudiesTabNextBtn'))); //remove these references of nxtbtn from all 4 ifs
			columns_count = (colHeader.size())
			for(int c=0;c<columns_count;c++){
				//if column header = 'Access' ignore adding it to the hdrdata string
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if (((driver.getCurrentUrl()).contains("caninecommons")||(driver.getCurrentUrl()).contains("icdc.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/files"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			System.out.println("This is the value of next button from canine cases switch: "+nxtBtn)
			if(statValue==0){
				System.out.println ("No records in the table as stat value is 0")
			}else{
				columns_count = (colHeader.size())-1
				for(int c=1;c<=columns_count;c++){
					if((colHeader.get(c).getAttribute("innerText"))!="Access"){    //if column header = 'Access' ignore adding it to the hdrdata string
						System.out.println ("This is the value of col header index : "+c)
						hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
					}
				} // for loop ends
			}// else for state value ends prevents writing header to xl when data is empty so xl comparison goes through fine.
		}
		
		

		wTableHdrData.add(hdrdata);

		System.out.println("No.of columns in the current result tab is : "+columns_count)
		System.out.println("Complete list of column headers in current result tab is : "+wTableHdrData)

		for(int index = 0; index < wTableHdrData.size(); index++) {
			System.out.println("Header data of the table is :" + wTableHdrData.get(index))
		}
		System.out.println("Val of statistics before while loop: "+statValue);


		//@@@@@@@@@@@@@@@@@@  COLLECTING THE TABLE BODY DATA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		int counter=1;
		if (statValue !=0) {
			while(counter <= 2)
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.G_cannine_caseTblBdy)));   //the name is misleading but it is only a placeholder for all the applications
				scrolltoViewjs(driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy)))
				TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
				System.out.println("finding the num of rows in the result page")
				Thread.sleep(5000) //Check first and then delete
				rows_table = TableBdy.findElements(By.tagName("tr"))
				System.out.println("This is the value of weblement rows table :"+rows_table);
				Thread.sleep(3000)
				rows_count = rows_table.size()
				System.out.println("This is the size of the rows in the table in the current page:"+(rows_count))
				// add code to check exception - if the value of rows_count=1, ie if the table has only header and no data, skip collecting the webdata.

				int i;

				for(i = 1; i <= rows_count; i++) { //before editing for fixing cotb issue

					String data = ""

					// @@@@@@@@@@@@@@@@  Canine table data collection starts here @@@@@@@@@@@@@@@@
					if(switchString == "Canine"){
						System.out.println("Inside Canine Switch Structure")
						switch(switchCanine){
							
							case("/studies"):
							
								int tblcol=GlobalVariable.G_rowcount_Katalon;
							//In ICDC - Cases Tab and Samples tab have 12 cols; Files tab has 8 cols. Hence the counter has to be changed if the tab id is related to files tab.
								if((tbl_main).equals('//*[@id="table_studies"]/div/div[3]/table')){
									System.err.println("Inside Studies Tab >>>>>>>>>>>>>>>>>>>>>>>>>>")
									tblcol=tblcol-2  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)
									for (int j = 1; j< tblcol-1; j = j + 1) {
										System.out.println("Value of i is: "+i)
										System.out.println("Value of j is: "+j)
										System.out.println ("This is the value of col index starting from 1: "+j)
										if((colHeader.get(j).getAttribute("innerText"))!="Access") {
											System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
											//data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]")).getText()) +"||")
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j) +"]/*[2]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data: "+data)
										}
									}

								}else if((tbl_main).equals('//*[@id="table_studies"]/div/div[3]/table333')){
									System.err.println("Inside Xpath Tab>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
									//*******************************added this for study files tab**************************************
									tblcol=tblcol-2  // this is needed when study files has 8 cols
									System.out.println("This is the count of tblcol when study files tab is selected: "+tblcol)
									for (int j = 0; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+i)
										System.out.println("Value of j is: "+j)
										System.out.println ("This is the value of col index starting from 1 : "+j)

										if((colHeader.get(j).getAttribute("innerText"))!="Access") {
											System.out.println("This is the name of column header  :"+colHeader.get(j).getAttribute("innerText"))
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data :"+data)
										}
									}

								}else if((statValue)==0){
									System.out.println("inside the if loop for statvalu equal to 0 : already collected the header data")
								}else{
									System.err.println("Inside Else Statment >>>>>>>>>>>>>>>>>>>>>>>>>>")
									System.out.println("This is the val of tblcol: "+tblcol)
									System.out.println("This is the output of data **************** "+ data)
									data = ""
									for (int j = 0; j< tblcol+1; j = j + 1) {
										System.out.println("Value of i is: "+i)
										System.out.println("Value of j is: "+j)
										System.out.println ("This is the value of col index starting from 1: "+j)
										if((colHeader.get(j).getAttribute("innerText"))!="Access") {
											System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]")).getText()) +"||")
											System.out.println("This is the value of data: "+data)
										}
									}

//									for (int j = 2; j<= tblcol; j = j + 1) {
//										System.out.println("Value of i is: "+i)
//										System.out.println("Value of j is: "+j)
//										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]/*[2]")).getAttribute("innerText")) +"||")
//										System.out.println("This is the value of data :"+data)
//									}
								}
								break;
//							case("/"):  //added for ICDC my cart validation
//								System.out.println("Inside filecentric cart case of ICDC - for 10 cols after excluding Access and Remove");
//								int tblcol=GlobalVariable.G_rowcount_Katalon;
//								System.out.println("This is the val of tblcol: "+tblcol)
//							//i=i-1; // to start from 0 and include the first column
//								System.out.println("afajfadafavfavfavfvanfvanfva**************** "+ data)
//								data = ""
//								for (int j = 1; j<= tblcol-3; j = j + 1) {
//									System.out.println("Value of i is: "+i)
//									System.out.println("Value of j is: "+j)
//									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]/*[2]")).getAttribute("innerText")) +"||")
//									System.out.println("This is the value of data :"+data)
//								}
//								break;
							default:
								System.out.println("Canine Case did not match")
								break;
						} //canine switch ends here

					}//canine if ends here
	
					
					System.out.println("===================  Verification of the data: ===================== "+ data)

					wTableBodyData.add(data)
				}//for loop ends


				System.out.println("Size of table body list in current result tab is: "+wTableBodyData.size())
				for(int index = 0; index < wTableBodyData.size(); index++) {
					System.out.println("Table body data from current page is: " + wTableBodyData.get(index))
				}
				GlobalVariable.G_CaseData= wTableHdrData + wTableBodyData;
				System.out.println("This is the contents of globalvar G_casedata: " +GlobalVariable.G_CaseData)

				//********************* CLICKING THE NEXT BUTTON IN RESULTS FOR NEXT PAGE *******************************
				// add a counter for 10 inside this for limitting 100 records

				scrolltoViewjs(nextButton)   //added to address the unable to scroll into view issue/ another element obscures next button issue
				System.out.println("past the scrollintoview block")
				if (nextButton.getAttribute("disabled")){
					break;

				} else {
					System.out.println("COLLECTED DATA FROM PAGE - " +counter);
					clickElement(nextButton); //uses jsexecutor to click
					counter++;
				}

			}//while loop ends
		} //if loop for body data collection ends
		else {
			System.out.println("Not collecting the table data as the stat value is 0")
		}

		writeToExcel(webSheetName);
		System.out.println("webdata written to excel successfully")

	}//ReadCasesTableKatalon function ends



}