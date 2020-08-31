package ctdc.utilities

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By as By
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import internal.GlobalVariable






public class runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}


	public static WebDriver driver
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
		excelparsingKatalon(sheetData_K,driver);
		System.out.println("Excelparsing worked successfully")

	}


	private static void excelparsingKatalon(List<List<XSSFCell>> sheetData, WebDriver dr) {  //this is DB initializing
		// Iterates the data and print it out to the console.
		System.out.println("this is urlname :"+GlobalVariable.G_Urlname)
		driver.get(GlobalVariable.G_Urlname)
		driver.manage().window().maximize()
		int countrow = 0
		countrow = sheetData.size();
		System.out.println ( "row count from initializing fnc " + countrow ) //delete
		System.out.println ( "sheet  data size :" + sheetData.get(0).size())  //delete

		for (int i = 1; i < countrow; i++){
			List<XSSFCell> datarow = sheetData.get(i);
			System.out.println (" Columns Size from initializing fnc  " : + datarow.size())
			String str = "";  //delete ?
			for (int j = 0; j < datarow.size(); j++){
				System.out.println ("value of  i :"  + i + "  Value of j  : " + j )
				XSSFCell cell = datarow.get(j);

				//     System.out.println ( "Header Before switch  :" + sheetData.get(0).get(j).getStringCellValue())
				//System.out.println( "Data in variable : "  + sheetData.get(i).get(j).getStringCellValue())
				//--------------------
				switch(sheetData.get(0).get(j).getStringCellValue().trim() ) //First ROW
				//iterate for 2 more rows - sample tab and files tab
				{
					case("dbExcel"):
						GlobalVariable.G_dbexcel = sheetData.get(i).get(j).getStringCellValue()
						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
						GlobalVariable.G_ResultPath=dbfilepath.toString()
						break;
					case("query"):  //main db results query
						GlobalVariable.G_Query = sheetData.get(i).get(j).getStringCellValue()
						break;
					case ("StatQuery"):  //query for stat bar only
						GlobalVariable.G_StatQuery= sheetData.get(i).get(j).getStringCellValue()
						break;
					case("WebExcel"):
						GlobalVariable.G_WebExcel = sheetData.get(i).get(j).getStringCellValue()
						Path filepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_WebExcel)
						GlobalVariable.G_WebExcel=filepath.toString()
						break;
					default :
						System.out.println("Error in initializing")
						break;
				}
				str =str+ cell.getStringCellValue() + "||"
			}
		}
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
		System.out.println("This is the value returned for switch case:"+retnSwStr)
		return retnSwStr
	}
	//********************************************************
	/*This is a master function that performs the following operations by calling 4 functions inside it:
	 * ReadCasesTableKatalon - to read the result tab (Cases/Samples/Files) and collect the webdata and write it to an excel
	 * Neo4j- connects to neo4j db and extracts the results of a query and writes it to an excel
	 * compareLists - compares the webdata xl with neo4j xl
	 * validateStatBar - validates the counts displayed in statbar (with the counts displayed in individual result tabs - to be coded)
	 */
	@Keyword
	public static void multiFunction(String tbl, String tblHdr, String nxtBtn, String webdataSheetName,String filesCt, String samplesCt, String casesCt, String studiesCt, String dbdataSheetName) throws IOException {
		ReadCasesTableKatalon(tbl,tblHdr,nxtBtn,webdataSheetName)
		readStatBar(filesCt, samplesCt, casesCt, studiesCt)
		ReadExcel.Neo4j()
		compareLists(webdataSheetName, dbdataSheetName)
		validateStatBar()
	}

	//----------------web data --------------
	@Keyword
	public static void  ReadCasesTableKatalon(String tbl1, String hdr1, String nxtb1, String webSheetName) throws IOException {
		String switchCanine
		String switchTrials
		WebElement nextButton
		WebElement nxtBtn

		List<String> webData = new ArrayList<String>();
		String tbl_main= givexpath(tbl1)
		System.out.println("This is the value of tbl main : "+tbl_main)

		String tbl_bdy= tbl_main+"//tbody"
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy  //correct his variables name typo and also rename it to G_commons_casetblbdy
		System.out.println("This is the value of table body :"+GlobalVariable.G_cannine_caseTblBdy)

		//String tbl_str= givexpath(tbl1)                                   //"//div[contains(text(),'Case')]//parent::span//parent::th//parent::tr//parent::thead//following-sibling::tbody"
		//System.out.println("This is the value of tbl str string:"+tbl_str)
		//WebElement Table =driver.findElement(By.xpath(tbl_str))
		WebElement Table =driver.findElement(By.xpath(tbl_main))
		System.out.println("This is the value of weblement cases table :"+Table);

		WebElement TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
		//List<WebElement> rows_table = TableBdy.findElements(By.xpath("//*[contains(@id, \"MUIDataTableBodyRow-\")]"))
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
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Canine/Canine_File_NextBtn')));
			//*********added these lines******
			System.out.println("This is the value of nextbtn fm canine case switch :"+nxtBtn)
			System.out.println("This is the value of nextbtn fm the main readcasestable function :"+nextButton)
			//*********added these lines******
			columns_count = (colHeader.size())   //size 6
			for(int c=0;c<columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if (((driver.getCurrentUrl()).contains("caninecommons"))&&((driver.getCurrentUrl()).contains("/cases"))){
			switchCanine = getPageSwitch();
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			//*********added this 1 line******
			System.out.println("This is the value of next button from canine cases switch: "+nxtBtn)
			columns_count = (colHeader.size())-1
			for(int c=1;c<=columns_count;c++){
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			} // for loop ends
		}else if(((driver.getCurrentUrl()).contains("trialcommons"))&&((driver.getCurrentUrl()).contains("/case/"))){
			switchTrials = getPageSwitch();
			System.out.println ("This is the value of TRIALS switch string returned by getcurrentpage function: "+switchTrials)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Trials/Trials_File_NextBtn')));
			columns_count = (colHeader.size())
			for(int c=0;c<columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if(((driver.getCurrentUrl()).contains("trialcommons"))&&((driver.getCurrentUrl()).contains("/cases"))){
			switchTrials = getPageSwitch();
			System.out.println ("This is the value of TRIALS switch string returned by getcurrentpage function: "+switchTrials)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Trials/Trials_NextBtn')));
			columns_count = (colHeader.size())-1
			for(int c=0;c<columns_count;c++){  //comment this after case detail troubleshoot  //single case
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}//add one more loop to capture trialcommons && cases

		webData.add(hdrdata);
		System.out.println("No.of columns in the page is : "+columns_count)
		System.out.println("Size of web data list with header :" +webData.size())
		for(int index = 0; index < webData.size(); index++) {
			System.out.println("Web Data: with header data is :" + webData.get(index))
		}

		while(true)
		{
			rows_table = TableBdy.findElements(By.tagName("tr"))
			rows_count = rows_table.size()

			//System.out.println("Val of rows table determining the row count :"+rows_table)
			System.out.println("This is the size of the rows in the table in the current page:"+(rows_count))

			for(int i = 1; i <= rows_count; i++) { //before editing for fixing cotb issue
				String data = ""

				//*****************added switch here**********************
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
					//In ICDC - Cases Tab and Samples tab have 12 cols; Files tab has 10 cols. Hence the counter has to be changed if the tab id is related to files tab.
					if((tbl_main).equals('//*[@id="file_tab_table"]')){
						tblcol=tblcol-2
						System.out.println("This is the value of tblcol (11) when files tab is selected:"+tblcol)
					}
						for (int j = 3; j <= columns_count+tblcol; j = j + 2) {  
//														System.out.println("The value of i is :"+i);
//														System.out.println("The value of j is :"+j);
							data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getText()) +"||")
//														System.out.println("The value of data is :"+data)
						}
						break;
					default:
						System.out.println("Canine Case did not match")
						break;
				}

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
						for (int j = 3; j < columns_count+tblcol; j = j + 2) {
							data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getText()) +"||")
						}
						break;
					default:
					//System.out.println("Trials Case did not match")
						break;
				}


				webData.add(data)
			}
			System.out.println("Size of Web Data list with header in current page is : " + webData.size())
			for(int index = 0; index < webData.size(); index++) {
				System.out.println("Web Data: from current page is" + webData.get(index))
			}
			if (nxtBtn.getAttribute("disabled")) break;  //files next button in cases click; other wise canien next button
			nxtBtn.click()
		}
		GlobalVariable.G_CaseData= webData;
		System.out.println("This is the contents of globalvar G_casedata :" +GlobalVariable.G_CaseData)
		//KeywordUtil.markFailed("failed")
		writeToExcel(webSheetName); //add a sheetname argument
	}


	//*********************read Canine Stat Bar************************************************************

	@Keyword
	public static void readStatBar(String cFiles, String cSamples, String cCases, String cStudies){
		String xpFiles = givexpath(cFiles)
		String xpSamples = givexpath(cSamples)
		String xpCases = givexpath(cCases)
		String xpStudies = givexpath(cStudies)

		GlobalVariable.G_StatBar_Files = driver.findElement(By.xpath(xpFiles)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_StatBar_Files)
		GlobalVariable.G_StatBar_Samples = driver.findElement(By.xpath(xpSamples)).getText();
		System.out.println("This is the value of Samples count from Stat bar :"+GlobalVariable.G_StatBar_Samples)
		//System.out.println("this is the xpath of cases :"+xpCases)
		GlobalVariable.G_StatBar_Cases = driver.findElement(By.xpath(xpCases)).getText();
		System.out.println("This is the value of Cases count from Stat bar :"+GlobalVariable.G_StatBar_Cases)
		GlobalVariable.G_StatBar_Studies = driver.findElement(By.xpath(xpStudies)).getText();
		System.out.println("This is the value of Studies count from Stat bar :"+GlobalVariable.G_StatBar_Studies)

	}

	//****************************read Trials stat bar**************
	@Keyword
	public void readTrialsStatBar(String tTrials, String tCases, String tFiles){
		String xpTrials = givexpath(tTrials)
		String xpCases = givexpath(tCases)
		String xpFiles = givexpath(tFiles)
		GlobalVariable.G_TStatBar_Trials = driver.findElement(By.xpath(xpTrials)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_TStatBar_Trials)
		GlobalVariable.G_TStatBar_Cases = driver.findElement(By.xpath(xpCases)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_TStatBar_Cases)
		GlobalVariable.G_TStatBar_Files = driver.findElement(By.xpath(xpFiles)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_TStatBar_Files)
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
			FileOutputStream fos = new FileOutputStream(new File(excelPath));
			XSSFWorkbook workbook = new XSSFWorkbook();           // Create Workbook instance holding .xls file
			XSSFSheet sheet;
			sheet = workbook.createSheet(webSheetName);
			//			switch(webTabName){
			//			 case("WebData"):
			//			  sheet = workbook.createSheet("WebData");
			//			  break;
			//			 case("CaseDetails"):
			//			  sheet = workbook.createSheet("CaseDetails");
			//			  break;
			//			 default:
			//			  System.out.println("Tab name for writing web data is not provided")
			//			}
			//	XSSFSheet sheet = workbook.createSheet("WebData");    //create a dynamic worksheet - for WebData, CaseDetails
			List<String> writeData = new ArrayList<String>();
			writeData = GlobalVariable.G_CaseData
			//System.out.println ("This is the value of writedata from excelpath function :"+writeData)
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
			System.out.println("Canine Web Data has been written to excel successfully");
			workbook.close();
		}catch (IOException ie)
		{
			ie.printStackTrace();
		}
	}//write to excel method ends here

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
		//UIData = ReadExcel.readExceltoWeblist(UIfilename,GlobalVariable.G_WebTabname)  //change the function name Test in parent class and here
		UIData = ReadExcel.readExceltoWeblist(UIfilename,webSheetName)


		System.out.println("This is the data read after going through Test function : "+UIData)
		System.out.println ("This is the row size of the UIdata : "+ UIData.size());
		Collections.sort( UIData , new runtestcaseforKatalon() )
		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		System.out.println("This is the full neo4j filepath after converting to string :"+neo4jfilename);
		//neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_CypherTabname)  //change the function name Test in parent class and here
		neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,neoSheetName)

		System.out.println ("This is the row size of the Neo4jdata : "+ neo4jData.size());
		Collections.sort( neo4jData , new runtestcaseforKatalon() )
		compareTwoLists(UIData,neo4jData)
	}


	@Keyword
	public static void validateStatBar() {
		List<List<XSSFCell>> statData = new ArrayList<>()
		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		//use the following for verifying assertion with invalid data
		//           Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", "TC01_Canine_Filter_Breed-Akita_Neo4jDatainvalid.xlsx")
		//           String neo4jfilename=dbfilepath.toString()

		statData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_StatTabname)  //change the function name Test in parent class and here
		System.out.println("This is the first row - stat data read from neo4j stat sheet : "+statData[0])
		System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
		System.out.println("This is the value of Samples Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
		System.out.println("This is the value of Cases Count from Neo4j result"+statData.get(0).get(2).getStringCellValue())
		System.out.println("This is the value of Studies Count from Neo4j result "+statData.get(0).get(3).getStringCellValue())
		//assert statData.get(0).get(0).getStringCellValue()==GlobalVariable.G_StatBar_Files :KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Samples count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Samples count")
		(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
		(statData.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Studies)) ? KeywordUtil.markPassed("Statbar Studies count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Studies count")

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
		(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_TStatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_TStatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
		(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_TStatBar_Trials)) ? KeywordUtil.markPassed("Statbar Trials count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Trials count")

	}

	@Keyword
	public static WebDriver browserDriver(String browserName){
		//WebUI.openBrowser('')
		//WebDriver dr
		driver = DriverFactory.getWebDriver()
	}

	@Keyword
	public static Select_case_checkbox( String caseID,String count){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//String one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::td//preceding-sibling::td/div/span/span/input"


		System.out.println(" In the function  " + count + "caseid : "  + caseID )

		switch(count){
			case("one"):
			//System.out.println("in case 1")
				String one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::td//preceding-sibling::td/div/span/span/input"

			//String one_path ="//a[contains( text(),"+ caseID +")]//parent::div//parent::td//preceding-sibling::td"


			//System.out.println(" In the function dumbo1 "  + one_path )
				WebElement checkbox =driver.findElement(By.xpath(one_path))
				js.executeScript("arguments[0].click();", checkbox)
			//driver.findElement(By.xpath(one_path)).click()  //driver.findElement(By.xpath('//a[contains( text(),caseID)]//parent::div//parent::td//preceding-sibling::td'))
				break;
			case ("all"):
			////String str= givexpath(tbl1)
				String all_path ="//div[@id=\'table_cases\']//thead/tr/th/div/span/span/input"

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
				int tblcol=GlobalVariable.G_rowcount_Katalon; //12 //19
				//for (int j = 3; j < columns_count+tblcol; j = j + 2) {

				sCase= ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + 3 + "]")).getText()) )
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
		System.out.println ("This is the url of the current page (before reading case details table) :"+driver.getCurrentUrl())
		// calling the below function reads the data in the case details table
		ReadCasesTableKatalon ("Object Repository/Canine/Canine_FilesTable","Object Repository/Canine/Canine_FilesTable_Hdr", "Object Repository/Canine/Canine_File_NextBtn",GlobalVariable.G_caseDetailsTabName)




		driver.navigate().back()

		System.out.println ("This is the url of the current page AFTER reading case details table) :"+driver.getCurrentUrl())

		nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Canine/Canine_NextBtn')))


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
