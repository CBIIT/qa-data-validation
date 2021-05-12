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


import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.*;
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

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import internal.GlobalVariable



public class runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}


	public static WebDriver driver //= CustomBrowserDriver.createWebDriver()

	public static WebElement nxtBtn


	@Keyword
	public  void RunKatalon(String input_file) {

		//Thread.sleep(2000)
		Path file_input = Paths.get(System.getProperty("user.dir"), "InputFiles", input_file);
		if ( file_input !=null) {
			KeywordUtil.markPassed("Test case file loaded " + "This is the full filepath after converting to string :"+file_input.toString())
			//     String wholeFileName = file_input.toString()
			GlobalVariable.G_input_file=file_input.toString()
		}
		else{
			KeywordUtil.markPassed ("Password File is not found" )
		}

		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.G_input_file )

		List<List<XSSFCell>> sheetData_K = new ArrayList<>();
		FileInputStream fis = new FileInputStream(GlobalVariable.G_input_file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
		int numberOfSheets = workbook.getNumberOfSheets();    // Get the  sheets on the workbook
		int countrow = 0
		int countcol= 0


		XSSFSheet sheet = workbook.getSheetAt(0);  //reading input query
		countrow = sheet.lastRowNum- sheet.firstRowNum;
		System.out.println ( "Row count is  : " + countrow);
		countcol = sheet.getRow(0).getLastCellNum();
		System.out.println("Col count is : " + countcol);

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
		System.out.println("This is the driver from inside the runkatalon method : "+driver)
		excelparsingKatalon(sheetData_K,driver);
		System.out.println("Excelparsing worked successfully")
		System.out.println("This is the value of sheetdata array from runkatalon function : "+sheetData_K)

	}

	public static int convStringtoInt (String stringVal)	{
		int i =0;
		try
		{
			System.out.println("string value is = " + stringVal);
			i = Integer.parseInt(stringVal.trim());
			System.out.println("integer value is = " + i);
		}
		catch (NumberFormatException nfe)
		{
			System.out.println("NumberFormatException: " + nfe.getMessage());
		}
		return i;
	}

	public static void manifestDownloadRobot(){
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	private static void excelparsingKatalon(List<List<XSSFCell>> sheetData, WebDriver dr) {  //this is DB initializing ||  String queryvariable
		// Iterates the data and print it out to the console.
		System.out.println("this is the value of browser driver from exelparsingkatalon :"+dr)
		System.out.println("this is urlname :"+GlobalVariable.G_Urlname)
		driver.get(GlobalVariable.G_Urlname)
		driver.manage().window().maximize()
		System.out.println("The window is mazimized")
		Thread.sleep(3000)
		int countrow = 0
		countrow = sheetData.size();
		System.out.println ( "row count from initializing fnc " + countrow ) //delete
		System.out.println ( "sheet  data size :" + sheetData.get(0).size())  //delete  this is the col count

		for (int i = 1; i < countrow; i++){
			List<XSSFCell> datarow = sheetData.get(i);
			System.out.println (" Columns Size from initializing fnc  " : + datarow.size())
			String str = "";  //delete ?
			for (int j = 0; j < datarow.size(); j++){
				System.out.println ("value of  i :"  + i + "  Value of j  : " + j )
				XSSFCell cell = datarow.get(j);

				switch(sheetData.get(0).get(j).getStringCellValue().trim() ) //First ROW - header row
				//iterate for 2 more rows - sample tab and files tab
				{

					case("TabName"):
						GlobalVariable.G_inputTabName = sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the tabname from input excel : "+GlobalVariable.G_inputTabName)
						break;
					case("query"):  //main db results query
					//GlobalVariable.G_Query = sheetData.get(i).get(j).getStringCellValue()
					//capture each query in a different variable each time - gquery-cases/samples/files
						if(GlobalVariable.G_inputTabName=="CasesTab"){
							GlobalVariable.G_QueryCasesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of cases tab query from switch case : "+GlobalVariable.G_QueryCasesTab)
						}else if(GlobalVariable.G_inputTabName=="SamplesTab"){
							GlobalVariable.G_QuerySamplesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of samples tab query from switch case : "+GlobalVariable.G_QuerySamplesTab)
						}else if(GlobalVariable.G_inputTabName=="FilesTab"){
							GlobalVariable.G_QueryFilesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryFilesTab)
						}
					//System.out.println("This is the val stored in global gquery inside -runkatalon-excelparsingkatalon func : "+GlobalVariable.G_Query)
						break;
					case ("StatQuery"):  //query for stat bar only
						GlobalVariable.G_StatQuery= sheetData.get(i).get(j).getStringCellValue()
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
					case("dbExcel"):
						GlobalVariable.G_dbexcel = sheetData.get(i).get(j).getStringCellValue()
						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
						GlobalVariable.G_ResultPath=dbfilepath.toString()
						break;
					case("ManifestFlag"):
						GlobalVariable.ManifestFlag = sheetData.get(i).get(j).getStringCellValue()
						break;
					default :
						System.out.println("Error in initializing")
						break;
				}
				str =str+ cell.getStringCellValue() + "||"
				//System.out.println("this is the value of str :"+str)
			}//for loop j ends
		}//for loop i ends
	}


	//*********for case detail level automation***************
	public static String getPageSwitch()
	{
		String pgUrl = driver.getCurrentUrl()         //https://caninecommons-qa.cancer.gov/#/case/NCATS-COP01CCB010015
		String[] arrOfStr = pgUrl.split("#", 2);
		System.out.println ("This is the value of the array of strings after splitting url : "+arrOfStr)
		//String refStr = arrOfStr[1].toString()    //arrOfStr[1]="/case/NCATS-COP01CCB010015"
		String switchStr=getSwitchStr(arrOfStr[1])
		return switchStr
	}

	public static String getSwitchStr(String mainStr)
	{
		String retnSwStr
		if (mainStr.contains("/cases")){
			retnSwStr = "/cases"
		}else if(mainStr.contains("/case/")){
			retnSwStr = "/case/"
		}
		else if(mainStr.contains("/fileCentricCart")){
			retnSwStr = "/fileCentricCart"
		}

		System.out.println("This is the value returned for switch case:"+retnSwStr)
		return retnSwStr
	}



	//************************************************************************************
	/*This is a master function that performs the following operations by calling 4 functions inside it:
	 * ReadCasesTableKatalon - to read the result tab (Cases/Samples/Files) and collect the webdata and write it to an excel
	 * Neo4j- connects to neo4j db and extracts the results of a query and writes it to an excel
	 * compareLists - compares the webdata xl with neo4j xl
	 * validateStatBar - validates the counts displayed in statbar (with the counts displayed in individual result tabs - to be coded)
	 */

	@Keyword
	public static void multiFunction(String appName, String statVal, String tbl, String tblHdr, String nxtBtn, String webdataSheetName, String dbdataSheetName, String tabQuery) throws IOException {
		ReadCasesTableKatalon(statVal, tbl,tblHdr,nxtBtn,webdataSheetName) //add stat count variable
		ReadExcel.Neo4j(dbdataSheetName,tabQuery)
		compareLists(webdataSheetName, dbdataSheetName)  //commented temporarily for developing bento scripts
		validateStatBar(appName)

	}

	/*@Keyword
	 public static void manifestValidation (String mycartSheetName, String manifestSheetName) {
	 readMyCartTable(totalRecCountMyCart,tblMyCart,hdrMyCart,nxtbMyCart,myCartSheetName)
	 compareLists(mycartSheetName, manifestSheetName)
	 }
	 public static void readMyCartTable(String totalRecCountMyCart1, String tblMyCart1, String hdrMyCart1, String nxtbMyCart1, String myCartSheetName1){
	 } */

	//----------------web data --------------
	@Keyword
	public static void ReadCasesTableKatalon(String statVal1, String tbl1, String hdr1, String nxtb1, String webSheetName) throws IOException {
		String switchCanine
		String switchTrials
		String switchBento
		String switchString
		WebElement nextButton
		WebElement nxtBtn
		WebElement resultTab

		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("This is the stat value of cases before converting to int: "+statVal1)
		int statValue = convStringtoInt(statVal1);
		System.out.println("This is the passed value of stat for this run : "+statValue)


		List<String> webData = new ArrayList<String>();  //this is not used
		List<String> wTableHdrData = new ArrayList<String>(); //to capture the table header data
		List<String> wTableBodyData = new ArrayList<String>(); //to capture the table body data
		String tbl_bdy;
		String tbl_main= givexpath(tbl1)
		System.out.println("This is the value of tbl main : "+tbl_main)

		//		if ((driver.getCurrentUrl()).contains("/fileCentricCart")){  // this is for filecentric cart
		//			 tbl_bdy= tbl_main
		//		}else{
		tbl_bdy= tbl_main+"//tbody"
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy  //correct his variables name typo and also rename it to G_commons_casetblbdy
		System.out.println("This is the value of table body :"+GlobalVariable.G_cannine_caseTblBdy)
		//	}
		//click the result tab again:

		driver.manage().window().maximize()
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tbl_bdy)));
		scrolltoViewjs(driver.findElement(By.xpath(tbl_bdy)))
		System.out.println("Scrolled into view and ready to click again")
		clickElement(driver.findElement(By.xpath(tbl_bdy)));
		System.out.println("using jscriptexec, clicked again")

		WebElement TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
		List<WebElement> rows_table = TableBdy.findElements(By.tagName("tr"))
		System.out.println("This is the value of weblement rows table :"+rows_table);

		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the cases table in first page:"+(rows_count))
		String nxt_str=     givexpath(nxtb1)
		System.out.println("This is the value of the xpath of nextbtn : "+nxt_str)
		nextButton = driver.findElement(By.xpath(nxt_str));
		System.out.println("This is the value of the webelem next button from readcasestablekatalon method : "+nextButton)
		System.out.println("This is the value of the hdr : "+hdr1)
		String hdr_str= givexpath(hdr1)
		System.out.println("This is the value of the hdr string - xpath : "+hdr_str)
		WebElement tableHdr = driver.findElement(By.xpath(hdr_str))

		List<WebElement> colHeader = tableHdr.findElements(By.tagName("th"));
		int columns_count
		// int columns_count = (colHeader.size())-1    //uncomment after fixing columns count switch

		String hdrdata = ""   //moved to top

		if(((driver.getCurrentUrl()).contains("caninecommons"))&&((driver.getCurrentUrl()).contains("/case/"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Canine/Canine_File_NextBtn'))); //remove these references of nxtbtn from all 4 ifs
			//*********added these lines******
			//System.out.println("This is the value of nextbtn fm canine case switch :"+nxtBtn)
			//System.out.println("This is the value of nextbtn fm the main readcasestable function :"+nextButton)
			//*********added these lines******
			columns_count = (colHeader.size())   //size 6
			for(int c=0;c<columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if (((driver.getCurrentUrl()).contains("caninecommons"))&&((driver.getCurrentUrl()).contains("/cases"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			//*********added this 1 line******
			System.out.println("This is the value of next button from canine cases switch: "+nxtBtn)
			if(statValue==0){

			}else{
				columns_count = (colHeader.size())-1
				for(int c=1;c<=columns_count;c++){
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
				} // for loop ends
			}// else for stat val ends   prevents writing header to xl when data is empty so xl comparison goes through fine.
		}
		//adding this for mycart table data:*************************************************
		else if (((driver.getCurrentUrl()).contains("caninecommons"))&&((driver.getCurrentUrl()).contains("/fileCentricCart"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			//*********added this 1 line******
			System.out.println("This is the value of next button from canine mycart switch: "+nxtBtn)
			if(statValue==0){
				System.out.println ("No files in the cart")
			}else{
				columns_count = (colHeader.size())-1
				for(int c=1;c<=columns_count;c++){
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
				} // for loop ends
			}// else for stat val ends   prevents writing header to xl when data is empty so xl comparison goes through fine.
		}
		//*************************//adding the above for mycart table data:*************************************************

		else if(((driver.getCurrentUrl()).contains("trialcommons"))&&((driver.getCurrentUrl()).contains("/case/"))){
			switchTrials = getPageSwitch();
			switchString = "Trials";
			System.out.println ("This is the value of TRIALS switch string returned by getcurrentpage function: "+switchTrials)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Trials/Trials_File_NextBtn')));
			columns_count = (colHeader.size())
			for(int c=0;c<columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if(((driver.getCurrentUrl()).contains("trialcommons"))&&((driver.getCurrentUrl()).contains("/cases"))){
			switchTrials = getPageSwitch();
			switchString = "Trials";
			System.out.println ("This is the value of TRIALS switch string returned by getcurrentpage function: "+switchTrials)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Trials/Trials_NextBtn')));
			columns_count = (colHeader.size())-1
			for(int c=0;c<columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if(((driver.getCurrentUrl()).contains("bento-tools.org"))&&((driver.getCurrentUrl()).contains("/case/"))){
			switchBento = getPageSwitch();
			switchString = "Bento";
			System.out.println ("This is the value of Bento switch string-Case returned by getcurrentpage function: "+switchBento)
			//nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Bento/Bento_CasesTabNextBtn')));
			columns_count = (colHeader.size())-1
			for(int c=0;c<columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if(((driver.getCurrentUrl()).contains("bento-tools.org"))&&((driver.getCurrentUrl()).contains("/cases"))){
			switchBento = getPageSwitch();
			switchString = "Bento";
			System.out.println ("This is the value of Bento switch string-Cases returned by getcurrentpage function: "+switchBento)
			//nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Bento/Bento_CasesTabNextBtn')));
			columns_count = (colHeader.size())-1
			for(int c=1;c<=columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}
		else if (((driver.getCurrentUrl()).contains("bento-tools.org"))&&((driver.getCurrentUrl()).contains("/fileCentricCart"))){
			switchBento = getPageSwitch();
			switchString = "Bento";
			System.out.println ("This is the value of BENTO switch string returned by getcurrentpage function: "+switchBento)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			System.out.println("This is the value of next button from Bento mycart switch: "+nxtBtn)
			if(statValue==0){
				System.out.println ("No files in the cart")
			}else{
				columns_count = (colHeader.size())-1
				for(int c=0;c<columns_count;c++){
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
				} // for loop ends
			}// else for stat val ends   prevents writing header to xl when data is empty so xl comparison goes through fine.
		}//add one more loop to capture trialcommons && cases

		wTableHdrData.add(hdrdata);

		System.out.println("No.of columns in the current result tab is : "+columns_count)
		System.out.println("Complete list of column headers in current result tab is : "+wTableHdrData)

		for(int index = 0; index < wTableHdrData.size(); index++) {
			System.out.println("Header data of the table is :" + wTableHdrData.get(index))
		}
		System.out.println("Val of statistics before while loop: "+statValue);

		while(true)
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.G_cannine_caseTblBdy)));
			scrolltoViewjs(driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy)))
			TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
			//clickElement(driver.findElement(By.xpath(TableBdy)));
			System.out.println("finding the num of rows in the result page")

			Thread.sleep(5000)
			//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("tr")));
			rows_table = TableBdy.findElements(By.tagName("tr"))
			System.out.println("This is the value of weblement rows table :"+rows_table);
			Thread.sleep(3000)
			rows_count = rows_table.size()
			System.out.println("This is the size of the rows in the table in the current page:"+(rows_count))
			// add code to check exception - if the value of rows_count=1, ie if the table has only header and no data, skip collecting the webdata.

			int i;

			for(i = 1; i <= rows_count; i++) { //before editing for fixing cotb issue

				String data = ""
				//*****************added switch here**********************
				if(switchString == "Canine"){

					switch(switchCanine){
						case("/case/"):  //should be file next btn  **********//caninecommons- case detail
							System.out.println("Inside canine switch case")
							int tblcol=GlobalVariable.G_rowcountFiles
							for (int j = 2; j < columns_count+tblcol; j = j + 2) {
								data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getText()) +"||")
							}
							break;
						case("/cases"):  //should be canine next btn ********** // caninecommons- all cases
							int tblcol=GlobalVariable.G_rowcount_Katalon;
						//In ICDC - Cases Tab and Samples tab have 12 cols; Files tab has 8 cols. Hence the counter has to be changed if the tab id is related to files tab.
							if((tbl_main).equals('//*[@id="file_tab_table"]')){
								tblcol=tblcol-2  // this is when files had 10 cols
								System.out.println("This is the count of tblcol when files tab is selected:"+tblcol)
							}
							if((statValue)==0){
								System.out.println("inside the if loop for statvalu equal to 0 : already collected the header data")
							}else{
								System.out.println("This is the val of tblcol: "+tblcol)
								System.out.println("afajfadafavfavfavfvanfvanfva**************** "+ data)
								data = ""
								for (int j = 2; j<= tblcol; j = j + 1) {
									System.out.println("Value of i is: "+i)
									System.out.println("Value of j is: "+j)
									//*[@id="sample_tab_table"]//tbody/tr[1]/*[2]/*[2]   the last index 2 is constant  only the first two will vary
									String etho = ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]/*[2]")).getAttribute("innerText")) +"||")
									//System.out.println("Element data ^^^^^^^^^^^^^^^^^^^^************ "+ etho)
									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]/*[2]")).getAttribute("innerText")) +"||")
									System.out.println("This is the value of data :"+data)
								}
							}
							break;
						default:
							System.out.println("Canine Case did not match")
							break;
					}
				}else if(switchString == "Trials"){

					switch(switchTrials){
						case("/case/"):  //should be file next btn  **********//trialcommons- case detail
							System.out.println("Inside trials switch case")
							int tblcol=GlobalVariable.G_rowcountFiles
							for (int j = 2; j < columns_count+tblcol; j = j + 2) {
								data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getText()) +"||")
							}
							break;
						case("/cases"):  //should be cases next btn ********** // trialcommons- all cases
							int tblcol=GlobalVariable.G_rowcount_Katalon;
							if((tbl_main).equals('//*[@id="file_tab_table"]')){
								tblcol=tblcol-2
								System.out.println("This is the count of tblcol in Bento:"+tblcol)
							}
							if((statValue)==0){
								System.out.println("inside the if loop for statvalu equal to 0 : already collected the header data")
							}else{
								for (int j = 3; j <= columns_count+tblcol; j = j + 2) {
									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getText()) +"||")
								}
							}
							break;
						default:
						//System.out.println("Trials Case did not match")
							break;
					}
				}else if(switchString == "Bento"){
					System.out.println("inside Bento switch structure");
					switch(switchBento){
						case("/case/"):  //should be file next btn  **********//Bento- case detail
							System.out.println("Inside Bento switch case")
							int tblcol=GlobalVariable.G_rowcountFiles
							for (int j = 2; j < columns_count+tblcol; j = j + 2) {
								data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getAttribute("innerText")) +"||")
							}
							break;
						case("/cases"):  //should be cases next btn ********** // Bento- all cases
							int tblcol=GlobalVariable.G_rowcount_Katalon;
							for (int j = 2; j < columns_count+tblcol-1; j = j + 1) {
								System.out.println("Value of i is: "+i)
								System.out.println("Value of j is: "+j)
								data = data+((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getAttribute("innerText")) +"||")
								System.out.println("This is the value of data :"+data)
							}
							break;
						case("/fileCentricCart"):
							int tblcol=GlobalVariable.G_rowcount_Katalon;
							for (int j = 1; j < columns_count+tblcol; j = j + 1) {
								System.out.println("Value of i is: "+i)
								System.out.println("Value of j is: "+j)
								data = data+((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getAttribute("innerText")) +"||")
								System.out.println("This is the value of data :"+data)
							}
							break;
						default:
							System.out.println("Bento Case switch did not match")
							break;
					}
				}
				System.out.println("==========================================Verification of the data: ========================="+ data)

				wTableBodyData.add(data)
			}//for loop ends


			System.out.println("Size of table body list in current result tab is : "+wTableBodyData.size())
			for(int index = 0; index < wTableBodyData.size(); index++) {
				System.out.println("Table body data from current page is" + wTableBodyData.get(index))
			}
			GlobalVariable.G_CaseData= wTableHdrData + wTableBodyData;
			System.out.println("This is the contents of globalvar G_casedata :" +GlobalVariable.G_CaseData)

			//*********************CLICKING THE NEXT BUTTON IN RESULTS FOR NEXT PAGE*******************************
			scrolltoViewjs(nextButton)   //added to address the unable to scrollintoview issue/ another element obscures next button issue
			System.out.println("past the scrollintoview block")
			if (nextButton.getAttribute("disabled")){
				break;
			} else { //files next button in cases click; other wise canien next button
				//nextButton.click()
				clickElement(nextButton); //uses jsexecutor to click
			}
			//clickTab(nxtBtn)
			System.out.println("next button clicked successfully")
			i=1;

		}//while loop ends
		writeToExcel(webSheetName); //add a sheetname argument
		System.out.println("webdata written to excel successfully")

	}//function ends
	//*********************read Canine Stat Bar************************************************************

	@Keyword
	public void readStatBarBento(String bProgs, String bArms, String bCases, String bSamples, String bAssays, String bFiles)
	{
		Thread.sleep(8000);


		String xbProgs = givexpath(bProgs)
		String xbArms = givexpath(bArms)
		String xbCases = givexpath(bCases)
		String xbSamples = givexpath(bSamples)
		String xbAssays = givexpath(bAssays)
		String xbFiles = givexpath(bFiles)


		GlobalVariable.G_StatBar_Programs = driver.findElement(By.xpath(xbProgs)).getText();
		System.out.println("This is the value of Programs count from Stat bar :"+GlobalVariable.G_StatBar_Programs)
		GlobalVariable.G_StatBar_Arms = driver.findElement(By.xpath(xbArms)).getText();
		System.out.println("This is the value of Arms count from Stat bar :"+GlobalVariable.G_StatBar_Arms)
		GlobalVariable.G_StatBar_Cases = driver.findElement(By.xpath(xbCases)).getText();
		System.out.println("This is the value of Cases count from Stat bar :"+GlobalVariable.G_StatBar_Cases)
		GlobalVariable.G_StatBar_Samples = driver.findElement(By.xpath(xbSamples)).getText();
		System.out.println("This is the value of Samples count from Stat bar :"+GlobalVariable.G_StatBar_Samples)
		GlobalVariable.G_StatBar_Assays = driver.findElement(By.xpath(xbAssays)).getText();
		System.out.println("This is the value of Assays count from Stat bar :"+GlobalVariable.G_StatBar_Assays)
		GlobalVariable.G_StatBar_Files = driver.findElement(By.xpath(xbFiles)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_StatBar_Files)
	}

	//***************************************************************
	public void readMyCartCount(String cmyCartCount)
	{
		Thread.sleep(5000);

		String xcmyCartCount = givexpath(cmyCartCount)
		Thread.sleep(2000)
		GlobalVariable.G_myCartTotal = driver.findElement(By.xpath(xcmyCartCount)).getAttribute("innerText");
		System.out.println("This is the value of Studies count from Stat bar :"+GlobalVariable.G_myCartTotal)
	}

	@Keyword
	public void readStatBarCanine(String cStuds, String cCases, String cSamples, String cFiles, String cAliqs)
	{
		Thread.sleep(5000);

		String xcStuds = givexpath(cStuds)
		String xcCases = givexpath(cCases)
		String xcSamples = givexpath(cSamples)
		String xcFiles = givexpath(cFiles)
		String xcAliqs = givexpath(cAliqs)

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
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_StatBar_Files)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Aliquots = driver.findElement(By.xpath(xcAliqs)).getAttribute("innerText");
		System.out.println("This is the value of Aliquots count from Stat bar :"+GlobalVariable.G_StatBar_Aliquots)
	}
	//****************************read Trials stat bar**************
	@Keyword
	public void readTrialsStatBar(String tTrials, String tCases, String tFiles){
		String xpTrials = givexpath(tTrials)
		String xpCases = givexpath(tCases)
		String xpFiles = givexpath(tFiles)
		GlobalVariable.G_StatBar_Cases = driver.findElement(By.xpath(xpTrials)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_StatBar_Cases)
		GlobalVariable.G_StatBar_Files = driver.findElement(By.xpath(xpCases)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_StatBar_Files)
		GlobalVariable.G_StatBar_Samples = driver.findElement(By.xpath(xpFiles)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_StatBar_Samples)
	}


	//************************************************************************************


	//this function returns the xpath of a given string (from the obj stored in katalons object repository)
	@Keyword
	public static String givexpath(String objname) {
		TestObject obj = findTestObject(objname)
		String xpathOfObj = obj.findPropertyValue('xpath')
		System.out.println(xpathOfObj)
		return xpathOfObj;

	}

	//*********************function to write webData to excel -- this writes the web results to excel******************

	public static void writeToExcel(String webSheetName){  //add a tabname
		try
		{
			String excelPath = GlobalVariable.G_WebExcel;
			File file1 = new File(excelPath);
			FileOutputStream fos = null;
			XSSFWorkbook workbook = null;
			XSSFSheet sheet;

			if( file1.exists()){
				System.out.println( "File exists, creating a new worksheet in the same file.")
				FileInputStream fileinp = new FileInputStream(excelPath);
				workbook = new XSSFWorkbook(fileinp);
				sheet = workbook.createSheet(webSheetName);
				fos = new FileOutputStream(excelPath);
			}
			else{
				fos = new FileOutputStream(new File(excelPath));
				System.out.println( "File does not exist, creating a new file.")
				workbook = new XSSFWorkbook();           // Create Workbook instance holding .xls file
				sheet = workbook.createSheet(webSheetName);
			}

			List<String> writeData = new ArrayList<String>();
			writeData = GlobalVariable.G_CaseData
			for( int i = 0; i < writeData.size(); i++ ){
				Row row = sheet.createRow(i);
				int cellNo = 0
				ArrayList<String> cellData = writeData.get(i).split("\\|\\|");
				for( String cellD: cellData ){
					//System.out.println("Cell data is: " + cellD )
					Cell cell = row.createCell(cellNo++);
					cell.setCellValue(cellD);
				}
			}//for loop of in
			workbook.write(fos);  //Write workbook into the excel
			fos.close(); //Close the workbook
			System.out.println("Web Data has been written to excel successfully");
			workbook.close();
		}catch (IOException ie)
		{
			ie.printStackTrace();
		}
	}//write to excel method ends here  */

	//*******************************************************************************************
	// verify element present

	@Keyword
	public void canineUIValidation() {
		HashMap<String, String> hshmap = new HashMap<String, String>();    /*declaring HashMap */
		hshmap.put("Study Dropdown", 'Object Repository/Canine/Filter/Study/Canine_Filter_Study');  /*Adding elements to HashMap*/
		hshmap.put("Study Type Dropdown", 'Object Repository/Canine/Filter/StudyType/Canine_Filter_StudyType');
		hshmap.put("Breed Dropdown", 'Object Repository/Canine/Filter/Breed/BREED_Ddn');
		hshmap.put("Diagnosis Dropdown", 'Object Repository/Canine/Filter/Diagnosis/DIAGNOSIS_Ddn');
		hshmap.put("Primary Disease Site Dropdown", 'Object Repository/Canine/Filter/PrimDiseaseSite/PRIMARYDISEASESITE_Ddn');
		//hshmap.put("Stage of Disease Dropdown", 'Object Repository/Canine/Filter/Breed/BREED_Ddn');
		//hshmap.put("Response to Treatment Dropdown", '');
		hshmap.put("Sex Dropdown", 'Object Repository/Canine/Filter/Sex/SEX_Ddn');
		//hshmap.put("Neutered Status Dropdown", '');
		//hshmap.put("Associated File Type Dropdown", 'Object Repository/Canine/Filter/AssocFileType/AssocFileType_Ddn');
		//hshmap.put("Associated File Format Dropdown", 'Object Repository/Canine/Filter/AssocFileFormat/AssocFileFormat_Ddn');

		System.out.println("passing hash map to the validaiton function")
		UIValidation(hshmap)  // calling the validation function
		System.out.println("successfully completed UI validaiton")
	}

	@Keyword
	public void trialsUIValidation(){

	}

	//**************************************
	@Keyword
	public void footerVal() {
		HashMap<String, String> hshmap = new HashMap<String, String>();    /*declaring HashMap */

		//About ICDC****************
		hshmap.put("Purpose Hyperlink", 'Object Repository/Canine/Footer/Purpose_Hplink');
		hshmap.put("Steering Committee Hyperlink", 'Object Repository/Canine/Footer/SteeringComm_Hplink');
		hshmap.put("CRDC Hyperlink", 'Object Repository/Canine/Footer/CRDC_Hplink');
		hshmap.put("ContactUs Hyperlink", 'Object Repository/Canine/Footer/ContactUs_Hplink');
		//About the Data *****************
		hshmap.put("ICDC DataModel Hyperlink", 'Object Repository/Canine/Footer/ICDCDataModel_Hplink');
		hshmap.put("AnalyzingData Hyperlink", 'Object Repository/Canine/Footer/AnalyzingData_Hplink');
		hshmap.put("Developers Hyperlink",'Object Repository/Canine/Footer/Developers_Hplink');
		hshmap.put("Submission Guide Hyperlink",'Object Repository/Canine/Footer/SubmissionGuide_Hplink');

		//More Information***********************
		hshmap.put("Policies Hyperlink",'Object Repository/Canine/Footer/Policies_Hplink')
		hshmap.put("Disclaimer Hyperlink",'Object Repository/Canine/Footer/Disclaimer_Hplink')
		hshmap.put("Accessibility Hyperlink",'Object Repository/Canine/Footer/Accessibility_Hplink');
		hshmap.put("FOIA Hyperlink",'Object Repository/Canine/Footer/FOIA_Hplink')

		//other links*******************
		hshmap.put("HHS Hyperlink",'Object Repository/Canine/Footer/HHS_Hplink')
		hshmap.put("NIH Hyperlink",'Object Repository/Canine/Footer/NIH_Hplink')
		hshmap.put("NCI Hyperlink",'Object Repository/Canine/Footer/NCI_Hplink')
		hshmap.put("USA Hyperlink",'Object Repository/Canine/Footer/USA_Hplink')

		//labels*******************************
		hshmap.put("Turning Discovery Label",'Object Repository/Canine/Footer/TurningDiscIntoHlth_Label')
		hshmap.put("National Cancer Institute Image",'Object Repository/Canine/Footer/NatCanInst_Img')
		hshmap.put("About ICDC Label",'Object Repository/Canine/Footer/AboutICDC_Label')
		hshmap.put("About the Data Label",'Object Repository/Canine/Footer/AboutTheData_Label')
		hshmap.put("More Info Label",'Object Repository/Canine/Footer/MoreInfo_Label')

		System.out.println("passing hash map to the validaiton function")
		UIValidation(hshmap)  // calling the validation function
		System.out.println("successfully completed UI validaiton")

	}



	@Keyword
	public void headerVal(){

	}



	//******************UI VALIDATION - HASHMAP VALIDATION FUNCTION ***************************
	public void UIValidation(HashMap<String, String> hmap) {
		Set set = hmap.entrySet();     /* Display content using Iterator*/
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Map.Entry mpEntry = (Map.Entry)iter.next();
			//System.out.print("key is: "+ mpEntry.getKey() + " & Value is: "+ mpEntry.getValue());
			String elemXpath = givexpath(mpEntry.getValue())
			//System.out.println ("Xpath of the given object is : "+elemXpath)
			if(driver.findElement(By.xpath(elemXpath))!= null){
				KeywordUtil.markPassed(mpEntry.getKey()+" is Present");
			}else{
				KeywordUtil.markFailed(mpEntry.getKey()+" is Absent");
			}
		}
	}

	//compare lists***********************************************************
	public static void compareTwoLists( List<List<XSSFCell>> l1, List<List<XSSFCell>> l2 ){
		System.out.println ("Comparing two Lists");
		for( int l2row = 0; l2row < l2.size(); l2row++ ){
			List<XSSFCell> l2rowList = l2.get(l2row)
			//System.out.println(" L2Row contents: " + l2rowList )
			for( int l1row = 0; l1row < l1.size(); l1row++ ){
				List<XSSFCell> l1rowList = l1.get(l1row)
				//System.out.println(" L1Row contents: " + l1rowList )
				if( l2rowList.get(0).getStringCellValue() == l1rowList.get(0).getStringCellValue() ) //takes CTDCID as the primary key for comparison
				{
					System.out.println(" L1Row contents Matched with: " + l1rowList + " and: " + l2rowList )
					boolean l1NullFlag = false, l2NullFlag = false
					for(int col = 0; col < l2rowList.size(); col++ ){ //compares all the columns in the excels - for each row
						if( l1rowList.get(col) == null || l1rowList.get(col).equals("") || l1rowList.get(col).getCellType() == l1rowList.get(col).CELL_TYPE_BLANK ){
							System.out.println("There is a NULL entry in L1 Row")
							l1NullFlag = true
						}
						if( l2rowList.get(col) == null || l2rowList.get(col).equals("") || l2rowList.get(col).getCellType() == l2rowList.get(col).CELL_TYPE_BLANK ){
							System.out.println("There is a NULL entry in L2 Row")
							l2NullFlag = true
						}
						if( l1NullFlag == l2NullFlag ) System.out.println("Content Matches for col: "+ col)
						else System.out.println("Content does not match for col: " + col )
						if( l1NullFlag || l2NullFlag ) continue
							System.out.println("L1Cell: "+ l1rowList.get(col).getStringCellValue() + " L2 Cell: "+ l2rowList.get(col).getStringCellValue() )
						if( l1rowList.get(col).getStringCellValue() == l2rowList.get(col).getStringCellValue() ){
							System.out.println("Content matches for col: " + col )
						}
						else{
							System.out.println("Content does not match for col: " + col )
							System.out.println( "L1Row Value: " + l1rowList.get(col).getStringCellValue() )
							System.out.println( "L2Row Value: " + l2rowList.get(col).getStringCellValue() )
						}
					}
				}else{
					// add what the code should display if contents mismatch outside the main loop for CTDC ID
				}
			}
		}
	}

	//**************************************************
	@Keyword
	//public static void compareLists(String wCasesSheet, String wCaseDetailsSheet, String nCasesSheet, String nCaseDetailsSheet) {  //pass the sheet names only. file name is not needed
	public static void compareLists(String webSheetName, String neoSheetName) {  //pass the sheet names only. file name is not needed
		List<List<XSSFCell>> UIData = new ArrayList<>()
		List<List<XSSFCell>> neo4jData = new ArrayList<>()
		String UIfilename =  GlobalVariable.G_WebExcel.toString()   //UIfilepath.toString()
		System.out.println("This is the full uifilepath after converting to string :"+UIfilename);
		//UIData = ReadExcel.readExceltoWeblist(UIfilename,GlobalVariable.G_WebTabnameCasesCasesCases)  //change the function name Test in parent class and here
		UIData = ReadExcel.readExceltoWeblist(UIfilename,webSheetName)


		System.out.println("This is the data read after going through Test function : "+UIData)
		System.out.println ("This is the row size of the UIdata : "+ UIData.size());
		Collections.sort( UIData , new runtestcaseforKatalon() )

		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		System.out.println("This is the full neo4j filepath after converting to string :"+neo4jfilename);
		//neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_CypherTabnameCasesCasesCases)  //change the function name Test in parent class and here
		neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,neoSheetName)

		System.out.println ("This is the row size of the Neo4jdata : "+ neo4jData.size());
		Collections.sort( neo4jData , new runtestcaseforKatalon() )
		compareTwoLists(UIData,neo4jData)
	}
	/*
	 @Keyword
	 public static void validateStatBarCanine() {
	 List<List<XSSFCell>> statDataCanine  = new ArrayList<>()
	 String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
	 //use the following for verifying assertion with invalid data
	 //           Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", "TC01_Canine_Filter_Breed-Akita_Neo4jDatainvalid.xlsx")
	 //           String neo4jfilename=dbfilepath.toString()
	 statDataCanine = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_StatTabname)  //change the function name Test in parent class and here
	 System.out.println("This is the value of Files Count from Neo4j result "+statDataCanine.get(0).get(0).getStringCellValue())
	 System.out.println("This is the value of Samples Count from Neo4j result "+statDataCanine.get(0).get(1).getStringCellValue())
	 System.out.println("This is the value of Cases Count from Neo4j result "+statDataCanine.get(0).get(2).getStringCellValue())
	 System.out.println("This is the value of Studies Count from Neo4j result "+statDataCanine.get(0).get(3).getStringCellValue())
	 //assert statData.get(0).get(0).getStringCellValue()==GlobalVariable.G_StatBar_Files :KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
	 (statDataCanine.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
	 (statDataCanine.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Samples count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Samples count")
	 (statDataCanine.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
	 (statDataCanine.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Studies)) ? KeywordUtil.markPassed("Statbar Arms count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Studies count")
	 }
	 */

	@Keyword
	public static void validateStatBar(getAppName) {

		List<List<XSSFCell>> statData = new ArrayList<>()
		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		//use the following for verifying assertion with invalid data
		//           Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", "TC01_Canine_Filter_Breed-Akita_Neo4jDatainvalid.xlsx")
		//           String neo4jfilename=dbfilepath.toString()
		statData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_StatTabname)

		if(getAppName=='Bento'){
			//change the function name Test in parent class and here
			System.out.println("This is the first row - stat data read from neo4j stat sheet : "+statData[0])
			System.out.println("This is the value of Programs Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
			System.out.println("This is the value of Arms Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Cases Count from Neo4j result "+statData.get(0).get(2).getStringCellValue())
			System.out.println("This is the value of Samples Count from Neo4j result "+statData.get(0).get(3).getStringCellValue())
			System.out.println("This is the value of Assays Count from Neo4j result "+statData.get(0).get(4).getStringCellValue())
			System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(5).getStringCellValue())

			//assert statData.get(0).get(0).getStringCellValue()==GlobalVariable.G_StatBar_Files :KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Programs)) ? KeywordUtil.markPassed("Statbar Programs count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Programs count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Arms)) ? KeywordUtil.markPassed("Statbar Arms count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Arms count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
			(statData.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Samples count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Samples count")
			(statData.get(0).get(4).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Assays)) ? KeywordUtil.markPassed("Statbar Assays count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Assays count")
			(statData.get(0).get(5).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		}
		else if (getAppName=='ICDC'){
			System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
			System.out.println("This is the value of Samples Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Cases Count from Neo4j result "+statData.get(0).get(2).getStringCellValue())
			System.out.println("This is the value of Studies Count from Neo4j result "+statData.get(0).get(3).getStringCellValue())
			//assert statData.get(0).get(0).getStringCellValue()==GlobalVariable.G_StatBar_Files :KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Samples count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Samples count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
			(statData.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Studies)) ? KeywordUtil.markPassed("Statbar Arms count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Studies count")
		}
	}

	@Keyword
	public void validateCanineDetailStat(){
		//****** to be added for the validaiton of extra tab in neo4j
	}

	@Keyword
	public void validateTrialsDetailStat(){
		//****** to be added for the validaiton of extra tab in neo4j
	}
	@Keyword
	public void validateTrialsStatBar() {
		List<List<XSSFCell>> statData = new ArrayList<>()
		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		//use the following for verifying assertion with invalid data
		//           Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", "TC01_Canine_Filter_Breed-Akita_Neo4jDatainvalid.xlsx")
		//           String neo4jfilename=dbfilepath.toString()

		statData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_StatTabname)  //change the function name Test in parent class and here
		System.out.println("This is the first row - stat data read from neo4j stat sheet : "+statData[0])
		System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
		System.out.println("This is the value of Cases Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
		System.out.println("This is the value of Trials Count from Neo4j result"+statData.get(0).get(2).getStringCellValue())

		//assert statData.get(0).get(0).getStringCellValue()==GlobalVariable.G_StatBar_Files :KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
		(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Cases)) ? KeywordUtil.markPassed("Statbar Trials count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Trials count")

	}


	// this function creates a browser driver; clears the outputfile directory before starting the run.
	//clearing folder should be moved to beforeSuite and should not be executed before every test case

	/*	@Keyword
	 public static WebDriver testSetup(String browserName){
	 //	driver = DriverFactory.getWebDriver()
	 //Path manifestPath = Paths.get(System.getProperty("user.dir"), "OutputFiles")
	 Path manifestDir = Paths.get(System.getProperty("user.dir"), "OutputFiles")
	 GlobalVariable.manifestPath = manifestDir.toString()
	 System.out.println("This is the path till the output directory of manifest files : "+GlobalVariable.manifestPath)
	 //String manifestPath = "C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\"  //the double slash at the end is required
	 if((browserName=='Chrome')||(browserName=='HEADLESS_DRIVER')){
	 System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
	 ChromeOptions options = new ChromeOptions()
	 Map<String, Object> chromePrefs = new HashMap<String, Object>()
	 chromePrefs.put("download.default_directory", GlobalVariable.manifestPath)
	 chromePrefs.put("download.prompt_for_download", false)
	 options.setExperimentalOption("prefs", chromePrefs)
	 driver = new ChromeDriver(options)
	 }
	 else if(browserName=='Firefox')
	 {
	 System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverPath())
	 ProfilesIni profile = new ProfilesIni();
	 FirefoxProfile myprofile = profile.getProfile("manifestICDC");
	 myprofile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
	 myprofile.setPreference("browser.download.folderList",2);
	 myprofile.setPreference("browser.download.manager.showWhenStarting",false);
	 myprofile.setPreference("browser.download.dir",GlobalVariable.manifestPath);
	 FirefoxOptions opt = new FirefoxOptions();
	 opt.setProfile(myprofile);
	 driver =  new FirefoxDriver(opt);
	 //driver = DriverFactory.getWebDriver()
	 //			  driver = new FirefoxDriver(myprofile);
	 }
	 }*/


	@Keyword
	public static clickTab(String TabName){

		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rawTabName = TabName
		String tabxpath = givexpath(TabName)
		System.out.println("This is the value of xpath of the element:"+tabxpath);
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tabxpath)));
		WebElement resultTab = driver.findElement(By.xpath(tabxpath));
		js.executeScript("arguments[0].scrollIntoView(true);", resultTab);
		js.executeScript("arguments[0].click();", resultTab);
		System.out.println("Successfully clicked desired element")
	}

	public static void scrolltoViewjs(WebElement elem){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", elem);
	}

	public static void clickElement(WebElement el){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", el);
	}

	@Keyword
	public static Select_case_checkbox( String caseID,String count){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//String one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::td//preceding-sibling::td/div/span/span/input"


		System.out.println(" In the function  " + count + "caseid : "  + caseID )
		String one_path;
		switch(count){
			case("one"):
			//System.out.println("in case 1")

				if (driver.getCurrentUrl().contains("bento-tools.org/")){
					//For Bento, the below one_path needs an extra parent div
					one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::div//parent::td//preceding-sibling::td/div/span/span/input"
				}
				else if (driver.getCurrentUrl().contains("caninecommons")){
					one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::td//preceding-sibling::td/div/span/span/input"  //this is the one written for ICDC which did not work for Bento
				}
			//   String one_path ="//a[contains( text(),'"+ caseID +"')]"
			//String one_path ="//a[contains( text(),"+ caseID +")]//parent::div//parent::td//preceding-sibling::td"


			//System.out.println(" In the function dumbo1 "  + one_path )
				WebElement checkbox =driver.findElement(By.xpath(one_path))
				js.executeScript("arguments[0].click();", checkbox)
			//driver.findElement(By.xpath(one_path)).click()  //driver.findElement(By.xpath('//a[contains( text(),caseID)]//parent::div//parent::td//preceding-sibling::td'))
				break;
			case ("all"):
			////String str= givexpath(tbl1)
				String all_path ="//div[@id=\'case_tab_table\']//thead/tr/th/div/span/span/input"

				System.out.println ("All Path :" + all_path )
			//String all_path ="//div[text()=\'Case ID\']//parent::span//parent::th//preceding-sibling::th/div/span/span/input"
				WebElement checkbox =driver.findElement(By.xpath(all_path))
				js.executeScript("arguments[0].click();", checkbox)
			//driver.findElement(By.xpath( "//div[@id=\"table_cases\"]//thead/tr/th[1]/div/span"  )).click()
			//div[@id="table_cases"]//thead/tr/th[1]/div/span

				break;
			case ("allM"):
			////String str= givexpath(tbl1)
			//String all_path ="//div[@id=\'table_cases\']//thead/tr/th/div/span/span/input"
				String all_M="//div[text()=\'Case ID\']//parent::div//parent::div//parent::span//parent::th//preceding-sibling::th/div/span/span/input"
				System.out.println ("All Path :" + all_M )
			//String all_path ="//div[text()=\'Case ID\']//parent::span//parent::th//preceding-sibling::th/div/span/span/input"
				WebElement checkbox =driver.findElement(By.xpath(all_M))
				js.executeScript("arguments[0].click();", checkbox)
			//driver.findElement(By.xpath( "//div[@id=\"table_cases\"]//thead/tr/th[1]/div/span"  )).click()
			//div[@id="table_cases"]//thead/tr/th[1]/div/span

				break;
			case ("caseM"):
			////String str= givexpath(tbl1)
			//String all_path ="//div[@id=\'table_cases\']//thead/tr/th/div/span/span/input"
				String all_M="//div[text()='" + caseID + "']//parent::div//parent::div//parent::span//parent::th//preceding-sibling::th/div/span/span/input"
				System.out.println ("All Path :" + all_M )
			//String all_path ="//div[text()=\'Case ID\']//parent::span//parent::th//preceding-sibling::th/div/span/span/input"
				WebElement checkbox =driver.findElement(By.xpath(all_M))
				js.executeScript("arguments[0].click();", checkbox)
			//driver.findElement(By.xpath( "//div[@id=\"table_cases\"]//thead/tr/th[1]/div/span"  )).click()
			//div[@id="table_cases"]//thead/tr/th[1]/div/span

				break;
		}
	}


	@Keyword
	public  static isDriverOpen(){

		try{
			DriverFactory.getCurrentWindowIndex()
			//driver.getTitle();
			KeywordUtil.logInfo("A browser instance is already open.")
			System.out.println("A browser instance is already open. Quitting the browser")
			driver.quit()
			// browser is open
		} catch(NoSuchSessionError) {
			// browser is closed
			KeywordUtil.logInfo("Browser is NOT Existing")

		}
	}


	@Keyword
	public static JsFunc()
	{
		String caseID ='COTC007B0203'

		JavascriptExecutor js = (JavascriptExecutor)driver;

		String one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::td//preceding-sibling::td/div/span/span/input"

		System.out.println ("one_path :" + one_path)
		//System.out.println(" In the function dumbo1 "  + one_path )
		WebElement checkbox =driver.findElement(By.xpath(one_path))

		//driver.findElement(By.xpath(one_path)).click()

		js.executeScript("arguments[0].click();", checkbox)

	}


	@Keyword
	public static File_details(String tbl1, String hdr1, String nxtb1)
	{

		List<String> caseId = new ArrayList<String>()

		//List<String> webData = new ArrayList<String>();
		String tbl_main= givexpath(tbl1)
		String tbl_bdy=     tbl_main+"//tbody"
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy

		String tbl_str= givexpath(tbl1)                                   //"//div[contains(text(),'Case')]//parent::span//parent::th//parent::tr//parent::thead//following-sibling::tbody"
		WebElement Table =driver.findElement(By.xpath(tbl_str))

		List<WebElement> rows_table = Table.findElements(By.xpath("//*[contains(@id, \"MUIDataTableBodyRow-\")]"))
		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the table in first page in files:"+(rows_count))
		System.out.println("This is the  url of thecurrent  page : "+driver.getCurrentUrl())

		String nxt_str=     givexpath(nxtb1)
		WebElement nextButton = driver.findElement(By.xpath(nxt_str));
		System.out.println("This is the value of next button coming from file_details function : "+nextButton)
		String hdr_str= givexpath(hdr1)
		WebElement tableHdr = driver.findElement(By.xpath(hdr_str))

		List<WebElement> colHeader = tableHdr.findElements(By.tagName("th"));
		int columns_count = (colHeader.size())-1
		System.out.println("No.of cols in the case detail page is : "+columns_count)
		String sTable
		String sHeasder
		String sNext
		String hdrdata = ""

		while(true)
		{
			rows_table = Table.findElements(By.xpath("//*[contains(@id, \"MUIDataTableBodyRow-\")]"))
			rows_count = rows_table.size()
			System.out.println("This is the size of the rows in the table in the current page:"+(rows_count))
			for(int i = 1; i <= rows_count; i++) { //rows_count
				String data = ""
				String sCase
				int tblcol=GlobalVariable.G_rowcount_Katalon; //12 //19 This is for icdc
				//for (int j = 3; j < columns_count+tblcol; j = j + 2) {
				//sCase= ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + 3 + "]")).getText()) )  // this is for ICDC
				sCase= ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + 2 + "]")).getText())) // this is for Bento
				data =  sCase
				System.out.println ("This is the case ID before clicking:" + sCase)
				clickcase sCase  // calling the function clickcase

				// TO DO
				//Read case level stat bar
				// Neo 4 Data base query
				// Wedata from files AVALABLE FILES
				//Compare Ne4j output and Web data for file

				caseId.add(data)
			}
			if (nxtBtn.getAttribute("disabled")) break;
			nxtBtn.click()
		}
		GlobalVariable.G_CasesArray= caseId;
		System.out.println("This is the contents of globalvar G_casesarray :" +GlobalVariable.G_CasesArray)

	}
	//}

	@Keyword
	public static void clickcase(String lCases )
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i
		//for (i= 1 ;i<lCases.size(); i++)
		//{
		String Str1
		Str1 = "//a[contains(@href,'" + lCases + "')]"
		WebElement caseIDlink =driver.findElement(By.xpath(Str1))
		js.executeScript("arguments[0].click();", caseIDlink)
		System.out.println ("This is the url of the current page - case details (before reading case details table) :"+driver.getCurrentUrl())
		// calling the below function reads the data in the case details table
		ReadCasesTableKatalon(GlobalVariable.G_StatBar_Cases,'Object Repository/Bento/Bento_CDFilesTable','Object Repository/Bento/Bento_CDFilesTable_Hdr', 'Object Repository/Bento/Bento_CDFilesTable_NxtBtn',GlobalVariable.G_caseDetailsTabName)
		//ReadCasesTableKatalon ("Object Repository/Canine/Canine_FilesTable","Object Repository/Canine/Canine_FilesTable_Hdr", "Object Repository/Canine/Canine_File_NextBtn",GlobalVariable.G_caseDetailsTabName)
		//ReadCasesTableKatalon(statVal, tbl,tblHdr,nxtBtn,webdataSheetName)
		driver.navigate().back()

		System.out.println ("This is the url of the current page - all cases, AFTER reading case details table) :"+driver.getCurrentUrl())
		nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Bento/Bento_CasesTabNextBtn')))
		//nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Canine/Canine_NextBtn'))) This is for ICDC

		driver.findElement(By.xpath("//input[@type='hidden']//parent::div")).click()
		driver.findElement(By.xpath("//ul[@role='listbox']/li[4]")).click()
		System.out.println ("case clicked and" + lCases +  "going back ")

		casedetailsQueryBuilder(lCases)

	}

	@Keyword
	public static void casedetailsQueryBuilder(String lCases )
	{
		//System.out.println("This is the size of the case id data from cases array: "+GlobalVariable.G_CasesArray)

		System.out.println("This is the value of lcasesfromfunction: "+lCases)
		System.out.println("First part new is : "+GlobalVariable.G_CaseDetailsQFirstPart)
		System.out.println("Second part new is : "+GlobalVariable.G_CaseDetailsQSecondPart)

		String finalQ = GlobalVariable.G_CaseDetailsQFirstPart + lCases + GlobalVariable.G_CaseDetailsQSecondPart
		System.out.println ("This is the concatenated query for breed greyhound :"+finalQ )

		GlobalVariable.G_CaseDetailQ=finalQ
		System.out.println ("This is the reassigned global variable from query builder function :"+GlobalVariable.G_CaseDetailQ )
	}

}  //class ends here


/* use this for case details page's automation:
 MATCH (f:file)-[*]->(c:case) WITH DISTINCT(f) AS f, c MATCH (f)-->(parent) WHERE c.case_id IN [case_ids,case_ids] RETURN f.file_status AS file_status,f.file_name AS file_name ,f.file_type AS file_type,f.file_description AS file_description,f.file_format AS file_format,f.file_size AS file_size,f.md5sum AS md5sum,f.uuid AS uuid,f.file_locations AS file_locations, head(labels(parent)) AS parent, c.case_id AS case_id
 MATCH (f:file)-[*]->(c:case) WITH DISTINCT(f) AS f, c MATCH (f)-->(parent) WHERE c.case_id IN ['NCATS-COP01CCB010072'] RETURN f.file_name AS `File Name` ,f.file_type AS `File Type`,head(labels(parent)) AS `Association`, f.file_description AS `Description`,f.file_format AS Format,f.file_size AS Size
 MATCH (f:file)-[*]->(c:case) WITH DISTINCT(f) AS f, c MATCH (f)-->(parent)
 WHERE c.case_id IN ['NCATS-COP01CCB050022']
 WITH
 ['Bytes', 'KB', 'MB', 'GB', 'TB'] AS units,
 toInteger(floor(log(f.file_size)/log(1024))) as i,
 2 as precision,
 f,parent
 WITH f.file_size /(1024^i) AS value, 10^precision AS factor, units[i] as unit,f,parent
 RETURN f.file_name AS `File Name` ,f.file_type AS `File Type`,head(labels(parent)) AS `Association`, f.file_description AS `Description`,f.file_format AS Format,round(factor * value)/factor+" "+unit AS Size
 */
