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





public class ICDCcaseDetails extends runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}

	public static WebDriver driver
	public  static WebElement nxtBtn
	/**
	 * This function reads input excels and assigns global variables to each query...
	 * @param sheetData
	 * @param dr
	 */

	@Keyword
	public  void readInput(String input_file) {

		Path file_input = Paths.get(System.getProperty("user.dir"), "InputFiles", input_file);
		if ( file_input !=null) {
			KeywordUtil.markPassed("Test case file loaded " + "This is the full filepath after converting to string :"+file_input.toString())
			GlobalVariable.InputExcel=file_input.toString()
		}
		else{
			KeywordUtil.markPassed ("Password File is not found" )
		}


		KeywordUtil.logInfo("Global variable set for file is :  " + GlobalVariable.InputExcel )
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
		driver = CustomBrowserDriver.createWebDriver();
		System.out.println("This is the driver from ICDC case details keyword : "+driver)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		excelparsing(sheetData_K,driver);
		System.out.println("This is the value of sheetdata array from create driver function : "+sheetData_K)

	}

	@Keyword
	private void excelparsing(List<List<XSSFCell>> sheetData, WebDriver dr) {
		System.out.println("This is the value of browser driver from exelparsingkatalon function in ICDCcaseDetails class: "+dr)

		System.out.println("This is urlname: "+GlobalVariable.fullUrl)
		//	driver=dr
		driver.get(GlobalVariable.fullUrl)

		driver.manage().window().maximize()
		System.out.println("The window is maximized")
		Thread.sleep(3000)


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


				cell.setCellType(Cell.CELL_TYPE_STRING) //to read non string values






				//Look for specific column names to perform action
				switch(sheetData.get(0).get(j).getStringCellValue().trim() )
				{
					case("caseID"):
						GlobalVariable.G_inputTabName = sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the case id from input excel : "+GlobalVariable.G_inputTabName)
						break;
					case("samplesQuery"):
						if(GlobalVariable.G_inputTabName==GlobalVariable.G_caseID){
							GlobalVariable.G_QuerySamplesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of samples tab query from switch case : "+GlobalVariable.G_QuerySamplesTab)
						}
						break;
					case ("filesQuery"):  //query for files table in case details page
						if(GlobalVariable.G_inputTabName==GlobalVariable.G_caseID){
							GlobalVariable.G_QueryFilesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryFilesTab)
						}
						break;
					case ("statQuery"):  //query for stat bar only
						if(GlobalVariable.G_inputTabName==GlobalVariable.G_caseID){
							GlobalVariable.G_StatQuery= sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of stat query from switch case : "+GlobalVariable.G_StatQuery)
						}
						break;
					case("dbExcel"):
						GlobalVariable.G_dbexcel = sheetData.get(i).get(j).getStringCellValue()
						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
						GlobalVariable.G_ResultPath=dbfilepath.toString()
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

					default :
						System.out.println("skipping this value")
						break;
				}// Switch case ends here
				str =str+ cell.getStringCellValue() + "||"
			}//for loop j ends (column read)
		}//for loop i ends (row read)
	}


	@Keyword
	public void readStatBarICDC(String cProgs, String cStuds, String cCases, String cSamples, String cFiles, String cStudyFiles)
	{
		Thread.sleep(5000);

		String xcProgs = givexpath(cProgs)
		String xcStuds = givexpath(cStuds)
		String xcCases = givexpath(cCases)
		String xcSamples = givexpath(cSamples)
		String xcFiles = givexpath(cFiles)
		String xcStudyFiles = givexpath(cStudyFiles)

		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Programs = driver.findElement(By.xpath(xcProgs)).getAttribute("innerText");
		System.out.println("This is the value of Programs count from Stat bar :"+GlobalVariable.G_StatBar_Programs)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Studies = driver.findElement(By.xpath(xcStuds)).getAttribute("innerText");
		System.out.println("This is the value of Studies count from Stat bar :"+GlobalVariable.G_StatBar_Studies)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Cases = driver.findElement(By.xpath(xcCases)).getAttribute("innerText");
		System.out.println("This is the value of Cases count from Stat bar :"+GlobalVariable.G_StatBar_Cases)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Samples = driver.findElement(By.xpath(xcSamples)).getAttribute("innerText");
		System.out.println("This is the value of Samples count from Stat bar :"+GlobalVariable.G_StatBar_Samples)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Files = driver.findElement(By.xpath(xcFiles)).getAttribute("innerText");
		System.out.println("This is the value of Case Files count from Stat bar :"+GlobalVariable.G_StatBar_Files)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_StudyFiles = driver.findElement(By.xpath(xcStudyFiles)).getAttribute("innerText");
		System.out.println("This is the value of Study Files count from Stat bar :"+GlobalVariable.G_StatBar_StudyFiles)
	}



	@Keyword
	public static void multiFunctionCD(String appName, String tbl, String tblHdr, String nxtBtn, String webdataSheetName, String dbdataSheetName, String tabQuery) throws IOException {
		System.out.println("Inside multifunctionCD");
		readTable(tbl,tblHdr,nxtBtn,webdataSheetName)
		System.out.println("control is after read table webdataxl creation and before readexcel neo4j function")
		ReadExcel.Neo4j(dbdataSheetName,tabQuery)
		System.out.println("control is before compare lists function from multifunction")
		compareLists(webdataSheetName, dbdataSheetName)
		System.out.println("control is before validate stat bar function from multifunction")
		validateStatBar(appName)
	}

	@Keyword
	public static void readTable(String tbl1, String hdr1, String nxtb1, String webSheetName) throws IOException {

		String switchCanine

		String switchString
		WebElement nextButton
		WebElement nxtBtn
		WebElement resultTab

		WebDriverWait wait = new WebDriverWait(driver,30);


		List<String> webData = new ArrayList<String>();  //this is not used
		List<String> wTableHdrData = new ArrayList<String>(); //to capture the table header data
		List<String> wTableBodyData = new ArrayList<String>(); //to capture the table body data
		String tbl_bdy;
		String tbl_main= givexpath(tbl1)
		System.out.println("This is the value of tbl main : "+tbl_main)

		tbl_bdy= tbl_main+"/tbody"
		//tbl_bdy= tbl_main+"/tbody"  //this is for INS
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy  //correct his variables name typo and also rename it to G_commons_casetblbdy
		System.out.println("This is the value of table body :"+GlobalVariable.G_cannine_caseTblBdy)

		//	driver.manage().window().maximize()  commenting to check the error in INS
		/*
		 *  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tbl_bdy)));
		 *
		 scrolltoViewjs(driver.findElement(By.xpath(tbl_bdy)))
		 System.out.println("Scrolled into view and ready to click again")
		 clickElement(driver.findElement(By.xpath(tbl_bdy)));
		 System.out.println("using jscriptexec, clicked again")
		 */

		WebElement TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
		List<WebElement> rows_table = TableBdy.findElements(By.tagName("tr"))
		System.out.println("This is the value of weblement rows table :"+rows_table);

		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the results table in first page: "+(rows_count))
		String nxt_str=     givexpath(nxtb1)
		System.out.println("This is the value of the xpath of nextbtn : "+nxt_str)
		nextButton = driver.findElement(By.xpath(nxt_str));
		System.out.println("This is the value of the webelem next button from readtable method : "+nextButton)
		System.out.println("This is the value of the hdr object: "+hdr1)
		String hdr_str= givexpath(hdr1)
		System.out.println("This is the value of the hdr string - xpath : "+hdr_str)
		String newHdr = hdr_str+"/tr";
		System.out.println("This is the value of the new hdr string : "+newHdr)
		WebElement tableHdr = driver.findElement(By.xpath(newHdr))

		List<WebElement> colHeader = tableHdr.findElements(By.tagName("th"));

		int columns_count
		String hdrdata = ""
		//@@@@@@@@@@@@@@@@@@  COLLECTING THE TABLE BODY DATA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		//System.out.println ("This is the url of the current page : "+driver.getCurrentUrl())
		//Read ICDC table header from result table for a specific tab
			 if(((driver.getCurrentUrl()).contains("caninecommons"))&&((driver.getCurrentUrl()).contains("/case/"))){
		 
		switchCanine = "/case/"
		
		 
		System.out.println ("This is the value of CANINE switch string : "+switchCanine)
	 
		columns_count = colHeader.size()
		for(int c=1;c<columns_count;c++){
			if((colHeader.get(c).getAttribute("innerText"))!="Access") {
			//if column header = 'Access' ignore adding it to the hdrdata string
			hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}
			 }else {
		System.out.println ("Unable to find the desired page")
			 }

		wTableHdrData.add(hdrdata);

		System.out.println("No.of columns in the current result tab is : "+(columns_count-1))
		GlobalVariable.ColsCount=columns_count-1
		System.out.println("Complete list of column headers in current result tab is : "+wTableHdrData)

		for(int index = 1; index < wTableHdrData.size(); index++) {
			System.out.println("Header data of the table is :" + wTableHdrData.get(index))
		}



		//@@@@@@@@@@@@@@@@@@  COLLECTING THE TABLE BODY DATA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		int counter=1;

		while(counter <= 10)
		{
			System.out.println("This is the value of tblbody stored in global variable :"+GlobalVariable.G_cannine_caseTblBdy)
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.G_cannine_caseTblBdy)));   //the name is misleading but it is only a placeholder for all the applications
		//	scrolltoViewjs(driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy)))
			TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
			System.out.println("finding the num of rows in the result page")
			Thread.sleep(2000) //Check first and then delete
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
			 
					System.out.println("Inside Canine Switch Structure")
					switch(switchCanine){
						case("/case/"):
							 
						//In ICDC - Cases Tab and Samples tab have 12 cols; Files tab has 8 cols. Hence the counter has to be changed if the tab id is related to files tab.
							if((tbl_main).equals('//*[@id="table_case_detail"]/div[1]/div/div[2]/div[1]/div/div/div[3]/table')){
								GlobalVariable.ColsCount
								System.out.println ("Inside the loop where Samples table is read **************************************")
								for (int j = 1; j<= GlobalVariable.ColsCount; j = j + 1) {
									System.out.println("Value of i is: "+i)
									System.out.println("Value of j is: "+j)
									  //*[@id="table_case_detail"]/div[1]/div/div[2]/div[1]/div/div/div[3]/table/tbody
								      //*[@id="table_case_detail"]/div[1]/div/div[2]/div[1]/div/div/div[3]/table/tbody/tr[1]/td[2]/div[2]
										System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + (j+1) +"]/div[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data: "+data)
								 
								}

							}else if ((tbl_main).equals('//*[@id="table_case_detail_samples"]/div/div/div[2]/div[1]/div/div/div[3]/table')){
								System.out.println ("Inside the loop where Files table is read **************************************")
								for (int j = 1; j<= GlobalVariable.ColsCount; j = j + 1) {
									if((colHeader.get(j).getAttribute("innerText"))!="Access") {
									System.out.println("Value of i is: "+i)
									System.out.println("Value of j is: "+j)
									  //*[@id="table_case_detail_samples"]/div/div/div[2]/div[1]/div/div/div[3]/table/tbody
									  //*[@id="table_case_detail_samples"]/div/div/div[2]/div[1]/div/div/div[3]/table/tbody/tr[1]/td[2]/div[2]
										System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + (j+1) +"]/div[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data: "+data)
									}//if loop ends
								 
								} //for loop ends
								//data = ""
								
							}
							break;

						default:
							System.out.println("Canine Case did not match")
							break;
					} //canine switch ends here

			 

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

		//	scrolltoViewjs(nextButton)   //added to address the unable to scroll into view issue/ another element obscures next button issue
		//	System.out.println("past the scrollintoview block")
			if (nextButton.getAttribute("disabled")){
				break;

			} else {
				System.out.println("COLLECTED DATA FROM PAGE - " +counter);
				clickElement(nextButton); //uses jsexecutor to click
				counter++;
			}

		}//while loop ends



		writeToExcel(webSheetName);
		System.out.println("webdata written to excel successfully")
	} //readcasestable ends here

}
