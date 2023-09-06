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

public class runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}


	public static WebDriver driver
	public static WebElement nxtBtn


	@Keyword
	public void Login (String signinButton, String emailID, String emailNxtBtn, String Passwd, String PasswdNxtBtn){
		String xsigninButton = givexpath(signinButton)
		String xemailNxtBtn = givexpath(emailNxtBtn)
		String xPasswd = givexpath(Passwd)
		String xPasswdNxtBtn = givexpath(PasswdNxtBtn)

		JavascriptExecutor js = (JavascriptExecutor)driver;

		Set<String> allHandlesb4signin = driver.getWindowHandles();
		System.out.println("Count of windows BEFORE sign in with google :"+allHandlesb4signin.size());
		System.out.println(allHandlesb4signin);
		String currentWindowHandleB4 = allHandlesb4signin.iterator().next();
		System.out.println("currentWindow Handle - default handle before signin : "+currentWindowHandleB4);

		//removed the if loop


		System.out.println("Waiting to log in")

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xsigninButton)));
		Set<String> allHandlesAftersignin = driver.getWindowHandles();
		System.out.println("Count of windows AFTER sign in with google :"+allHandlesAftersignin.size());
		System.out.println(allHandlesAftersignin);
		String parent=driver.getWindowHandle();
		for(String curWindow : allHandlesAftersignin){
			System.out.println ("This is the id of the curr window :"+curWindow)
			driver.switchTo().window(curWindow);   //switching to the child window
		}
		String currentWindowHandleAFTER = allHandlesAftersignin.iterator().next();
		System.out.println("currentWindow Handle -default after signin : "+currentWindowHandleAFTER);
		//store parent window & child window

		//switch to child window
		WebUI.switchToWindowIndex(1)
		String FirstWndUrl = driver.getCurrentUrl();
		System.out.println("First Popup window's url: " + FirstWndUrl)
		driver.manage().window().maximize();


		//Entering the email id or username
		Thread.sleep(2000)
		driver.findElement(By.xpath(xemailID)).sendKeys(GlobalVariable.G_AppUserName);
		System.out.println("Reading the text typed in email field: "+driver.findElement(By.xpath(xemailID)).getAttribute("value"));
		Thread.sleep(2000)


		//Clicking the next button after email id
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xemailNxtBtn)));
		Thread.sleep(10000);

		//Entering the password
		driver.findElement(By.xpath(xPasswd)).sendKeys(GlobalVariable.G_AppPassword);
		System.out.println("Getting password: "+driver.findElement(By.xpath(xPasswd)).getAttribute("value"));
		Thread.sleep(2000)

		//Clicking the next button after password
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xPasswdNxtBtn)));
		Thread.sleep(3000)

		System.out.println("Typed password and clicked the next button in password window. Moving back to the parent window handle")
		driver.switchTo().window(parent);
		driver.manage().window().maximize();
		Thread.sleep(3000)

		System.out.println("This is the current url post login & moving back to parent window handle : "+ driver.getCurrentUrl() );

		Set<String> allHandlesAfterLogin = driver.getWindowHandles();
		System.out.println(allHandlesAfterLogin);
		System.out.println("Count of windows after successful login :"+allHandlesAfterLogin.size());
		String curWindowHandlePostLogin = allHandlesAfterLogin.iterator().next();
		System.out.println("currentWindow Handle -default after successful login : "+curWindowHandlePostLogin);

		System.out.println("After successful login, the landing page's url: " + driver.getCurrentUrl())

	}//login function ends here




	/**
	 * This function reads the new excel file name from InputFiles
	 * @param input_file
	 */
	@Keyword
	public  void RunKatalon(String input_file) {

		String url = GlobalVariable.G_Urlname;
		String usrDir = System.getProperty("user.dir");
		String inputFiles = "InputFiles";
		Path filePath;


		if(url.contains("bento-tools.org")) {
			filePath = Paths.get(usrDir, inputFiles, "Bento", input_file);
		}else if(url.contains("caninecommons")) {
			filePath = Paths.get(usrDir, inputFiles, "ICDC", input_file);
		}else if(url.contains("studycatalog")) {
			filePath = Paths.get(usrDir, inputFiles, "INS", input_file);
		}else if(url.contains("dataservice")) {
			filePath = Paths.get(usrDir, inputFiles, "CDS", input_file);
		}else if(url.contains("gmb")) {
			filePath = Paths.get(usrDir, inputFiles, "GMB", input_file);
		}else if(url.contains("ctdc")) {
			filePath = Paths.get(usrDir, inputFiles, "CTDC", input_file);
		}else if(url.contains("mtp")) {
			filePath = Paths.get(usrDir, inputFiles, "MTP", input_file);
		}else if(url.contains("datacatalog")) {
			filePath = Paths.get(usrDir, inputFiles, "CCDC", input_file);
		}else if(url.contains("datacommons")) {
			filePath = Paths.get(usrDir, inputFiles, "CRDC", input_file);
		}else {
			KeywordUtil.markFailed("Invalid App URL: Check RunKatalon function")
		}

		if (filePath !=null) {
			KeywordUtil.markPassed("This is the full file path : "+filePath.toString())
			GlobalVariable.InputExcel=filePath.toString();
		}else{
			KeywordUtil.markFailed("Password File is not found")
		}


		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.InputExcel )
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
		System.out.println("This is the driver from inside the runkatalon method : "+driver)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		excelparsingKatalon(sheetData_K,driver);
		System.out.println("This is the value of sheetdata array from runkatalon function : "+sheetData_K)

	}
	//******************* Read Katalon Function Ends Here ***************************

	/**
	 * This function converts any given string value into integer
	 * @param stringVal Add the value to be converted to Int
	 * @return
	 */
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



	/**
	 * Gayathri will update this later..
	 */
	public static void manifestDownloadRobot(){
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	/**
	 * This function reads input excels and assigns global variables to each query...
	 * @param sheetData
	 * @param dr
	 */
	private static void excelparsingKatalon(List<List<XSSFCell>> sheetData, WebDriver dr) {
		System.out.println("This is the value of browser driver from exelparsingkatalon: "+dr)

		System.out.println("This is urlname: "+GlobalVariable.G_Urlname)
		driver.get(GlobalVariable.G_Urlname)
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
				//Look for specific column names to perform action
				switch(sheetData.get(0).get(j).getStringCellValue().trim() )
				{
					case("TabName"):
						GlobalVariable.G_inputTabName = sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the tabname from input excel : "+GlobalVariable.G_inputTabName)
						break;
					case("query"):
						if(GlobalVariable.G_inputTabName=="CasesTab"){
							GlobalVariable.G_QueryCasesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of cases tab query from switch case : "+GlobalVariable.G_QueryCasesTab)
						}else if(GlobalVariable.G_inputTabName=="SamplesTab"){
							GlobalVariable.G_QuerySamplesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of samples tab query from switch case : "+GlobalVariable.G_QuerySamplesTab)
						}else if(GlobalVariable.G_inputTabName=="FilesTab"){
							GlobalVariable.G_QueryFilesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryFilesTab)
						}else if(GlobalVariable.G_inputTabName=="ProgramsTab"){
							GlobalVariable.G_QueryProgramsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of files tab query from switch case : "+GlobalVariable.G_QueryProgramsTab)
						}else if(GlobalVariable.G_inputTabName=="ParticipantsTab"){
							GlobalVariable.G_QueryParticipantsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Participants tab query from switch case : "+GlobalVariable.G_QueryParticipantsTab)
						}else if(GlobalVariable.G_inputTabName=="StudyFilesTab"){
							GlobalVariable.G_QueryStudyFilesTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Study Files tab query from switch case : "+GlobalVariable.G_QueryStudyFilesTab)
						}else if(GlobalVariable.G_inputTabName=="SubjectsTab"){
							GlobalVariable.G_GQuerySubjectsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Study Files tab query from switch case : "+GlobalVariable.G_GQuerySubjectsTab)
						}else if(GlobalVariable.G_inputTabName=="GrantsTab"){
							GlobalVariable.G_QueryGrantsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Projects tab query from switch case : "+GlobalVariable.G_QueryGrantsTab)
						}else if(GlobalVariable.G_inputTabName=="PublicationsTab"){
							GlobalVariable.G_QueryPublicationsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Publications tab query from switch case : "+GlobalVariable.G_QueryPublicationsTab)
						}else if(GlobalVariable.G_inputTabName=="DatasetsTab"){
							System.out.println("This is the value of Datasets tab query from switch case : "+GlobalVariable.G_QueryDatasetsTab)
						}else if(GlobalVariable.G_inputTabName=="ClinicalTrialsTab"){
							GlobalVariable.G_QueryClinTrialsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Clinical Trials tab query from switch case : "+GlobalVariable.G_QueryClinTrialsTab)
						}else if(GlobalVariable.G_inputTabName=="PatentsTab"){
							GlobalVariable.G_QueryPatentsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Patemts tab query from switch case : "+GlobalVariable.G_QueryPatentsTab)
						}else if(GlobalVariable.G_inputTabName=="ArmsCohortsTab") {
							GlobalVariable.G_QueryArmsCohortsTab = sheetData.get(i).get(j).getStringCellValue()
							System.out.println("This is the value of Patemts tab query from switch case : "+GlobalVariable.G_QueryArmsCohortsTab)
						}
						break;

					case ("StatQuery"):  //query for stat bar only
						GlobalVariable.G_StatQuery= sheetData.get(i).get(j).getStringCellValue()
						break;
					case ("cartQuery"):  //query for My cart table only
						GlobalVariable.G_cartQuery= sheetData.get(i).get(j).getStringCellValue()
						System.out.println("This is the value of cart query from switch case : "+GlobalVariable.G_cartQuery)
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
					default :
						System.out.println("Error in initializing")
						break;
				}// Switch case ends here
				str =str+ cell.getStringCellValue() + "||"
			}//for loop j ends (column read)
		}//for loop i ends (row read)
	} //excelparsingKatalon function ends here


	/**for case detail level automation
	 * @return
	 */
	@Keyword
	public static String getPageSwitch()
	{
		System.out.println("Inside pageswitch function")
		String pgUrl = driver.getCurrentUrl()    //https://caninecommons-qa.cancer.gov/#/case/NCATS-COP01CCB010015
		String[] arrOfStr = pgUrl.split("#", 2);
		System.out.println ("This is the value of the array of strings after splitting url : "+arrOfStr)
		//String refStr = arrOfStr[1].toString()    //arrOfStr[1]="/case/NCATS-COP01CCB010015"
		String switchStr=getSwitchStr(arrOfStr[1])
		return switchStr
	}

	/**
	 * This function returns a string for a particular application"s page for further processing
	 * and is called above in getPageSwitch function
	 * @param mainStr
	 * @return
	 */
	public static String getSwitchStr(String mainStr)
	{
		String retnSwStr
		if (mainStr.contains("/cases")){
			retnSwStr = "/cases"
		}else if(mainStr.contains("/case/")){
			retnSwStr = "/case/"
		}else if(mainStr.contains("/study/")){
			retnSwStr = "/study/"
		}else if(mainStr.contains("/explore")){
			retnSwStr = "/explore"
		}else if(mainStr.contains("/subjects")){
			retnSwStr = "/subjects"
		}else if(mainStr.contains("/data")){
			retnSwStr = "/data"
		}else if(mainStr.contains("/fileCentricCart")){
			retnSwStr = "/fileCentricCart"
		}else if(mainStr.contains("/projects")){
			retnSwStr = "/projects"
		}else if(mainStr.contains("/studies")){
			retnSwStr = "/studies"
		}else if(mainStr.contains("/programs")){
			retnSwStr = "/programs"
		}
		System.out.println("This is the value returned for switch case: "+retnSwStr)
		return retnSwStr
	}



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
	 * This function reads the ui table in MyCart ICDC
	 * @param appName1
	 * @param totalRecCountMyCart1
	 * @param tblMyCart1
	 * @param hdrMyCart1
	 * @param nxtbMyCart1
	 * @param myCartWebSheetName1
	 * @param myCartdbSheetName1
	 * @param cartQuery1
	 * @throws IOException
	 */
	public static void readMyCartTable(String appName1, String totalRecCountMyCart1, String tblMyCart1, String hdrMyCart1, String nxtbMyCart1, String myCartWebSheetName1, String myCartdbSheetName1, String cartQuery1) throws IOException {
		System.out.println("This is the value of my cart db query : "+ cartQuery1)
		System.out.println("This is the value of cart count  : "+ totalRecCountMyCart1)
		System.out.println("This is the value of my cart db query stored in global variable : "+ GlobalVariable.G_cartQuery)
		System.out.println("This is the value of cart count stored in global variable : "+ GlobalVariable.G_myCartTotal)
		ReadCasesTableKatalon(totalRecCountMyCart1, tblMyCart1, hdrMyCart1, nxtbMyCart1, myCartWebSheetName1)
		System.out.println("Control is before readexcel neo4j function")
		ReadExcel.Neo4j(myCartdbSheetName1,cartQuery1)
		System.out.println("Control is before compare lists function in readcarttable")
		compareLists(myCartWebSheetName1, myCartdbSheetName1)
	}

	/**
	 * Gayathri will updata the details
	 * @param sTblbdy1
	 * @param sTblHdr1
	 * @param webSheetName
	 */
	@Keyword
	public static void readSelectedCols(String sTblbdy1, String sTblHdr1, String sNxtBtn, String webSheetName) {
		List<String> sTableHdrData = new ArrayList<String>();
		List<String> sTableBodyData = new ArrayList<String>();

		String tableHdr= givexpath(sTblHdr1)
		String tableBdy= givexpath(sTblbdy1)
		String nextButton = givexpath(sNxtBtn)
		GlobalVariable.G_customTblHdr=tableHdr
		GlobalVariable.G_customTblBdy=tableBdy
		System.out.println("This is the value of custom table header fm global var : "+GlobalVariable.G_customTblHdr)
		System.out.println("This is the value of custom table body fm global var : "+GlobalVariable.G_customTblBdy)

		driver.manage().window().maximize();
		scrolltoViewjs(driver.findElement(By.xpath(tableHdr)))
		System.out.println("Scrolled into view of custom table header")

		WebElement wbTableHdr = driver.findElement(By.xpath(tableHdr))
		List<WebElement> col_Headers = wbTableHdr.findElements(By.tagName("th"));
		WebElement wbTableBdy = driver.findElement(By.xpath(tableBdy))
		List<WebElement> rows_table;

		System.out.println("This is the value stored in column header list: "+col_Headers)
		int columns_count=col_Headers.size()-1;
		System.out.println("This is the num of cols in the table: "+columns_count);

		rows_table = wbTableBdy.findElements(By.tagName("tr"))
		System.out.println("This is the value of list containing weblements of rows from the table :"+rows_table);
		int rows_count = rows_table.size()
		System.out.println("This is the num of rows in the table in the current page: "+rows_count);

		//*******************************CUSTOM COLUMN HEADER DATA COLLECTION****************************************************
		String hdrdata = ""
		for(int c=1;c<=columns_count;c++){
			if((col_Headers.get(c).getAttribute("innerText"))!="Access"){
				//if ( ((col_Headers.get(c).getAttribute("innerText")) == 'File Name')||((col_Headers.get(c).getAttribute("innerText")) == 'Study Code')||((col_Headers.get(c).getAttribute("innerText")) == 'Case ID') ) {
				hdrdata = hdrdata + (col_Headers.get(c).getAttribute("innerText")) + "||"
				System.out.println("column "+ (c) +"added from header")
			}
		}

		sTableHdrData.add(hdrdata);
		System.out.println("No.of columns in the current result tab is : "+columns_count)
		System.out.println("Complete list of column headers in current result tab is : "+sTableHdrData)

		for(int index = 0; index < sTableHdrData.size(); index++) {
			System.out.println("Header data of the table is :" + sTableHdrData.get(index))
		}
		//*********************************CUSTOM ROW  DATA COLLECTION FOR THE CHOSEN HEADERS******************************************
		int counter = 1;
		scrolltoViewjs(driver.findElement(By.xpath(GlobalVariable.G_customTblBdy)));
		// add code to check exception - if the value of rows_count=1, ie if the table has only header and no data, skip collecting the webdata.

		int i;

		for(i = 1; i <= rows_count; i++) { //loop through each row in current page
			String data = ""
			System.out.println("Inside filecentric cart case of ICDC - for 10 cols after excluding Access and Remove"+ "--  row number: "+i);
			for(int j=2;j<=columns_count+1;j++){

				//*[@id='table_selected_files']//thead/tr/th[2]
				String colNameChk = ((driver.findElement(By.xpath(tableHdr +"/tr/th" + "[" + j + "]")).getAttribute("innerText")))
				//System.out.println ("Column header name before if condition : "+colNameChk)
				//	if((colNameChk!="Access"){
				//if((colNameChk == 'File Name')||(colNameChk == 'Study Code')||(colNameChk == 'Case ID')) {

				System.out.println("Value of i is: "+i)  //this tells the row index
				System.out.println("Value of j is: "+j) //this tells the column index
				//*[@id='table_selected_files']//tbody/tr[1]/td[2]/div[2]   last div index is always 2
				data = data + ((driver.findElement(By.xpath(tableBdy +"/tr" + "[" + i + "]/td[" + j + "]/div[2]")).getAttribute("innerText")) +"||")
				System.out.println("This is the value of data : "+data+" from column name : "+colNameChk)
				//} //if loop

			} //inner for loop

			sTableBodyData.add(data)
			System.out.println("Size of table body list in current result tab is: "+sTableBodyData.size())
			for(int index = 0; index < sTableBodyData.size(); index++) {
				System.out.println("Table body data from current page is: " + sTableBodyData.get(index))
			}

			System.out.println("============================ Verification of the data: =========================")
			GlobalVariable.G_CaseData= sTableHdrData + sTableBodyData;   //GlobalVariable.G_CustomTblData
			System.out.println("This is the contents of globalvar G_casedata : " +GlobalVariable.G_CaseData)

			//********************* CLICKING THE NEXT BUTTON IN RESULTS FOR NEXT PAGE *******************************
			// add a counter for 10 inside this for limitting 100 records

			scrolltoViewjs(nextButton)   //added to address the unable to scroll into view issue/ another element obscures next button issue
			System.out.println("past the scrollintoview block")

			if (nextButton.contains("disabled")){
				break;

			} else {
				System.out.println("COLLECTED DATA FROM PAGE - " +counter);
				clickElement(nextButton); //uses jsexecutor to click
				counter++;
			}

		} //outer for loop



		writeToExcel(webSheetName);
		System.out.println("Custom webdata written to excel successfully")

	}// readSelectedCols function ends








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
		String trim

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

		String crntUrl=driver.getCurrentUrl();
		//Read ICDC table header from result table for a specific tab
		if(((driver.getCurrentUrl()).contains("caninecommons")||(driver.getCurrentUrl()).contains("icdc.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/case/"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Canine/Canine_File_NextBtn'))); //remove these references of nxtbtn from all 4 ifs
			columns_count = (colHeader.size())
			for(int c=0;c<columns_count;c++){
				//if column header = 'Access' ignore adding it to the hdrdata string
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if (((driver.getCurrentUrl()).contains("caninecommons")||(driver.getCurrentUrl()).contains("icdc.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/explore"))){
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
		}else if (((driver.getCurrentUrl()).contains("caninecommons")||(driver.getCurrentUrl()).contains("icdc.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/study"))){
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
		else if (((driver.getCurrentUrl()).contains("caninecommons")||(driver.getCurrentUrl()).contains("icdc.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/fileCentricCart"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			System.out.println("This is the value of next button from canine mycart switch: "+nxtBtn)
			if(statValue==0){
				System.out.println ("No files in the cart")
			}else{
				columns_count = (colHeader.size())-1
				for(int c=1;c<=columns_count;c++){
					//if column header = 'Access' ignore adding it to the hdrdata string
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
				} // for loop ends
			}// else for stat val ends   prevents writing header to xl when data is empty so xl comparison goes through fine.

		}	//Read GMB table header from result table for a specific tab
		else if (((driver.getCurrentUrl()).contains("studycatalog"))&&((driver.getCurrentUrl()).contains("/explore"))){

			//else if (((driver.getCurrentUrl()).contains("studycatalog")||(driver.getCurrentUrl()).contains("https://ins-dev.bento-tools.org/"))&&((driver.getCurrentUrl()).contains("/projects"))){
			switchINS = getPageSwitch();
			switchString = "INS";
			System.out.println ("This is the value of INS switch string returned by getcurrentpage function: "+switchINS)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			System.out.println("This is the value of next button from INS projects switch: "+nxtBtn)
			if(statValue==0){
				System.out.println ("No records in the table as stat value is 0")
			}else{
				columns_count = colHeader.size()
				for(int c=0;c<columns_count;c++){

					System.out.println ("This is the value of col header index : "+c)
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"

				} // for loop ends
			}// else for state value ends prevents writing header to xl when data is empty so xl comparison goes through fine.
		}
		else if(((driver.getCurrentUrl()).contains("gmb")||(driver.getCurrentUrl()).contains("gmb-qa.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/subjects"))){
			System.out.println ("Control is about to go to case switch ")
			switchGMB = getPageSwitch();
			System.out.println ("Control is about to go to case switch After case switch ")
			switchString = "GMB";
			System.out.println ("This is the value of GMB switch string returned by getcurrentpage function: "+switchGMB)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/GMB/GMB_Subjects_TabNextBtn'))); //remove these references of nxtbtn from all 4 ifs
			columns_count = (colHeader.size())   //size should be 11 for subjects tab
			columns_count=columns_count-1;
			System.out.println("Inside GMB switch case for header data::  " +columns_count)
			for(int c=1;c<=columns_count;c++){
				//if column header = 'Access' ignore adding it to the hdrdata string
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}

			//CDS function starts here
		}else if(((driver.getCurrentUrl()).contains("dataservice")) && ((driver.getCurrentUrl()).contains("/data"))){
			System.out.println ("Control is about to go to case switch ")
			switchCDS = getPageSwitch();
			System.out.println ("Control is about to go to case switch After case switch ")
			switchString = "CDS";
			System.out.println ("This is the value of CDS switch string returned by getcurrentpage function: "+switchCDS)

			columns_count = (colHeader.size())
			columns_count=columns_count-1;
			System.out.println("Inside CDS switch case for header data::  " +columns_count)
			for(int c=1;c<=columns_count;c++){
				//if column header = 'Access' ignore adding it to the hdrdata string
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}

			//******** CTDC function starts here ********
		}else if((crntUrl.contains("ctdc")) && (crntUrl.contains("/case/"))){
			switchTrials = getPageSwitch();
			switchString = "Trials";
			System.out.println ("Value of TRIALS switch string returned by getcurrentpage function: "+switchTrials)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Trials/Trials_File_NextBtn')));
			columns_count = (colHeader.size())
			for(int c=0;c<columns_count;c++){
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}
		}else if((crntUrl.contains("ctdc")) && (crntUrl.contains("/explore"))){
			switchTrials = getPageSwitch();
			switchString = "Trials";
			System.out.println ("Inside CTDC explore page for table header collection: ")
			System.out.println ("Value of TRIALS switch string returned by getcurrentpage function: "+switchTrials)
			nxtBtn =  driver.findElement(By.xpath(givexpath('Object Repository/Trials/Cases_page/Trials_CasesTabNextBtn')));
			columns_count = (colHeader.size())
			System.out.println ("Total number of columns in CTDC result tab in explore page: "+ columns_count)
			for(int c=0;c<columns_count;c++){
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			}


			//********** Bento Function Start here *********
		}else if((crntUrl.contains("bento-tools.org")) && (crntUrl.contains("/explore"))){
			switchBento = getPageSwitch();
			switchString = "Bento";
			System.out.println ("Value of Bento switch string-Case returned by getcurrentpage function: "+switchBento)
			columns_count = (colHeader.size())-1
			hdrdata = ""
			for(int c=1;c<=columns_count;c++){
				if((colHeader.get(c).getAttribute("innerText"))!="Access"){
					System.out.println ("This is the value of col header index: "+c)
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
					System.out.println ("This is the value of header data from the else condition: "+hdrdata)
				}
			}
		}else if ((crntUrl.contains("bento-tools.org")) && (crntUrl.contains("/fileCentricCart"))){
			switchBento = getPageSwitch();
			switchString = "Bento";
			System.out.println("Value of BENTO switch string returned by getcurrentpage function: "+switchBento)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			System.out.println("This is the value of next button from Bento mycart switch: "+nxtBtn)
			if(statValue==0){
				System.out.println ("No files in the cart")
			}else{
				columns_count = (colHeader.size())-1
				for(int c=0;c<columns_count;c++){
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
				} // for loop ends
			}// else for stat val ends prevents writing header to xl when data is empty so xl comparison goes through fine.
		}

		else if ((crntUrl.contains("bento-tools.org")) && (crntUrl.contains("/programs"))){
			switchBento = getPageSwitch();
			switchString = "Bento";
			System.out.println ("Value of BENTO switch string returned by getcurrent program page function: "+switchBento)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			System.out.println("Value of next button from Bento pgm page switch: "+nxtBtn)
			if(statValue==0){
				System.out.println ("No records for pgms")
			}else{
				columns_count = (colHeader.size())
				for(int c=0;c<columns_count;c++){
					//if((colHeader.get(c).getAttribute("innerText"))!="PubMed ID"){
					//System.out.println ("This is the value of col header index: "+c)
					hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
					//}
				} // for loop ends
			}// else for stat val ends   prevents writing header to xl when data is empty so xl comparison goes through fine.
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

			while(counter <= 10)
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
					//@@@@@@@@@@@@@@@@ GMB table data collection starts here  @@@@@@@@@@@@@@@@
					if(switchString == "GMB"){
						System.out.println("Just before GMB Switch Structure for body data collection")
						switch(switchGMB){
							case("/subjects"):
								System.out.println("Inside GMB switch case for body data")
								int tblcol=GlobalVariable.G_rowcountFiles
								System.out.println("Value of tblcol : "+tblcol)  //should be 11
							//tblcol=tblcol-2
								for (int j = 1; j <=tblcol; j = j +1) {
									System.out.println("Value of i is: "+i)
									System.out.println("Value of j is: "+j)
									System.out.println("This is the name of column header : "+colHeader.get(j).getAttribute("innerText"))
									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
									System.out.println("This is the value of data : "+data)
								}
								break;
							case("/to edit"):
								int tblcol=GlobalVariable.G_rowcount_Katalon;
								if((tbl_main).equals('//*[@id="case_tab_table"]')){
									tblcol=tblcol-2  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected:"+tblcol)
									for (int j = 1; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+i)
										System.out.println("Value of j is: "+j)
										System.out.println ("This is the value of col index starting from 1 : "+j)

										if((colHeader.get(j).getAttribute("innerText"))!="Access") {
											System.out.println("This is the name of column header : "+colHeader.get(j).getAttribute("innerText"))
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data : "+data)
										}
									}
								}else if((statValue)==0){
									System.out.println("inside the if loop for statvalu equal to 0 : already collected the header data")
								}else{
									System.out.println("This is the val of tblcol: "+tblcol)
									data = ""

									for (int j = 2; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+i)
										System.out.println("Value of j is: "+j)

										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data : "+data)
									}
								}
						}
					}



					//@@@@@@@@@@@@@@@@ CDS table data collection starts here  @@@@@@@@@@@@@@@@
					//the following is from Sohil
					if(switchString == "CDS"){
						switch(switchCDS){
							case("/data"):
								System.out.println("Inside CDS switch case for body data")
								int tblcol=GlobalVariable.G_rowcountFiles

								if((tbl_main).equals('//div[@id="case_tab_table"]')){
									tblcol=tblcol-3;
									for (int j = 1; j <=tblcol; j = j +1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: " + j)
										System.out.println("This is the name of column header : "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data : "+data)
									}
								}else if((tbl_main).equals('//*[@id="sample_tab_table"]')){
									tblcol=tblcol-2;
									for (int j = 1; j <=tblcol; j = j +1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: " + j)
										System.out.println("This is the name of column header : "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data : "+data)
									}
								}else if((tbl_main).equals('//*[@id="file_tab_table"]')){
									tblcol=tblcol-2;
									for (int j = 1; j <=tblcol; j = j +1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: " + j)
										System.out.println("This is the name of column header : "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data : "+data)
									}
								}
								break;
							default:
								System.err.println("Check CDS switch statment for this error")
								break;

						}
					}

					// @@@@@@@@@@@@@@@@  Canine table data collection starts here @@@@@@@@@@@@@@@@
					if(switchString == "Canine"){
						System.out.println("Inside Canine Switch Structure")
						switch(switchCanine){

							case("/explore"):
								int tblcol=GlobalVariable.G_rowcount_Katalon;

								if((tbl_main).equals("//*[@id='case_tab_table']")){ //This is for cases tab
									data = ""
									System.out.println("This is the val of tblcol: "+tblcol+"\nThis is the output of data: "+ data)
									for (int j = 2; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+i+"\nValue of j is: "+j)

										if( ((tbl_main).equals("//*[@id='case_tab_table']")) && (colHeader.get(j-1).getAttribute("innerText")=="Case ID")){
											System.out.println("Inside the dog filter control structure")
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr[" + i + "]/td[" + j +"]/div/a")).getAttribute("innerText").trim()) +"||")
											System.out.println("This is the data after filtering for dog icon :"+data)
										}else {
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr[" + i + "]/td[" + j + "]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data : "+data)
										}
									}

								}else if((tbl_main).equals("//*[@id='sample_tab_table']")){ //This is for samples tab
									data = ""
									System.out.println("This is the val of tblcol: "+tblcol+"\nThis is the output of data: "+ data)
									for (int j = 2; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+i+"\nValue of j is: "+j)
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr[" + i + "]/td[" + j + "]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data : "+data)
									}

								}else if((tbl_main).equals("//*[@id='file_tab_table']")){ //This is for case file tab
									tblcol=tblcol-2  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)
									for (int j = 1; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+i+"\nValue of j is: "+j)

										if((colHeader.get(j).getAttribute("innerText"))!="Access") {
											System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
											data = data + ( (driver.findElement(By.xpath(tbl_bdy +"/tr[" + i + "]/td[" + (j+1) +"]")).getAttribute("innerText").trim()) +"||")
											System.out.println("This is the data after filtering for dog icon :"+data)
										}
									}

								}else if((tbl_main).equals("//table")){ //This is for case file tab
									tblcol=tblcol-5  // this is needed when study files has 8 cols
									System.out.println("This is the count of tblcol when study files tab is selected: "+tblcol)
									for (int j = 1; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+i+"\nValue of j is: "+j)

										if((colHeader.get(j).getAttribute("innerText"))!="Access") {
											System.out.println("This is the name of column header  :"+colHeader.get(j).getAttribute("innerText"))
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"//tr" + "[" + i + "]/td[" + (j+1) +"]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data :"+data)
										}
									}
								}else{
									System.err.println("Invalid Tab! Could not read the tab")
								}
								break;

							case("/fileCentricCart"):
								System.out.println("Inside filecentric cart case of ICDC - for 10 cols after excluding Access and Remove");
							//*[@id='table_selected_files']//tbody/tr[1]/td[2]    td runs from 2 to 11
								int tblcol=GlobalVariable.G_rowcount_Katalon;
								System.out.println("This is the val of tblcol: "+tblcol)
							//i=i-1; // to start from 0 and include the first column
								System.out.println("**************** "+ data)
								data = ""
								for (int j = 2; j<= tblcol-2; j = j + 1) {
									System.out.println("Value of i is: "+i+"\nValue of j is: "+j)
									//*[@id='table_selected_files']//tbody/tr[1]/td[2]/div[2]   where div[2] is a constant
									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]/div[2]")).getAttribute("innerText")) +"||")
									System.out.println("This is the value of data :"+data)
								}
								break;
							default:
								System.out.println("Canine Case did not match")
								break;
						} //canine switch ends here

					}//canine if ends here


					// @@@@@@@@@@@@@@@@  INS table data collection starts here @@@@@@@@@@@@@@@@
					if(switchString == "INS"){
						System.out.println("Inside INS Switch Structure")
						switch(switchINS){
							case("/explore"):
								int tblcol=GlobalVariable.G_rowcount_Katalon; //13
								System.out.println("This is the number of columns from the results table : "+tblcol)
							//In ICDC - Cases Tab and Samples tab have 12 cols; Files tab has 8 cols. Hence the counter has to be changed if the tab id is related to files tab.
								if((tbl_main).equals('//*[@id="project_tab_table"]/div/div[2]/div[3]/table')){
									System.out.println("Inside grants tab")
									tblcol=tblcol-5  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)
									for (int j = 0; j< tblcol+2; j = j + 1) {
										System.out.println("Value of i is: "+i+"\nValue of j is: "+j)
										System.out.println ("This is the value of col index starting from 0: "+j)

										System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data: "+data)

									}
									//this is for publications tab in INS***************************************
								}else if((tbl_main).equals('//*[@id="publication_tab_table"]/div/div[2]/div[3]/table')){
									System.out.println("Inside publications tab")
									tblcol=tblcol-8  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)
									for (int j = 0; j< tblcol+2; j = j + 1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)
										System.out.println ("This is the value of col index starting from 0: "+j)
										//*[@id="publication_tab_table"]/div/div[2]/div[3]/table//tbody/tr[1]/*[1]/*[2]  - this is the generic xpath for all columns
										//*[@id="publication_tab_table"]/div/div[2]/div[3]/table//tbody/tr[1]/td[1]/*[2]/div/span/a  - this is the specific xpath for the pubmed id col to avoid the external link image
										System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
										//if( ((tbl_main).equals('//*[@id="case_tab_table"]')) && (colHeader.get(j-1).getAttribute("innerText")=="Case ID")){
										if((colHeader.get(j).getAttribute("innerText")=="PubMed ID")){
											System.out.println("Inside the INS Pubmed ID column which has external link icon div that should be avoided")
											data = data + ( (driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]/div/span/a")).getAttribute("innerText").trim()) +"||")
											System.out.println("This is the data after eliminating the div for pubmed id external icon :"+data)

										}else {
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data: "+data)
										}







									}
									//this is for datasets tab in INS***************************************
								}else if((tbl_main).equals('//*[@id="dataset_tab_table"]/div/div[2]/div[3]/table')){
									System.out.println("Inside datasets tab")
									tblcol=tblcol-7  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)
									for (int j = 0; j< tblcol+2; j = j + 1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)
										System.out.println ("This is the value of col index starting from 0: "+j)

										System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data: "+data)

									}
									//this is for clinical trials tab in INS***************************************
								}else if((tbl_main).equals('//*[@id="clinical_trial_tab_table"]/div/div[2]/div[3]/table')){
									System.out.println("Inside clin trials tab")
									tblcol=tblcol-10  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)
									for (int j = 0; j< tblcol+2; j = j + 1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)
										System.out.println ("This is the value of col index starting from 0: "+j)

										System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data: "+data)

									}
									//this is for patents tab in INS***************************************
								}else if((tbl_main).equals('//*[@id="patent_tab_table"]/div/div[2]/div[3]/table')){
									System.out.println("Inside patents tab")
									tblcol=tblcol-2  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)
									for (int j = 0; j< tblcol+2; j = j + 1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)
										System.out.println ("This is the value of col index starting from 0: "+j)

										System.out.println("This is the name of column header: "+colHeader.get(j).getAttribute("innerText"))
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) +"]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data: "+data)

									}

								}else if((statValue)==0){
									System.out.println("inside the if loop for statvalue equal to 0 : already collected the header data")
								}else{
									System.out.println("This is the val of tblcol: "+tblcol)
									System.out.println("This is the output of data **************** "+ data)
									data = ""

									for (int j = 2; j<= tblcol; j = j + 1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]/*[2]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data :"+data)
									}
								}
								break;

						} // INS switch ends here

					} //INS if ends here
					//@@@@@@@@@@@@@@@@ CTDC table data collection starts here  @@@@@@@@@@@@@@@@

					if(switchString == "Trials"){
						System.out.println("Inside Trials Switch Structure")
						switch(switchTrials){
							case("/case/"):
								System.out.println("Inside trials switch case")
								int tblcol=GlobalVariable.G_rowcountFiles
								System.out.println ("This is the value of tblcol variable: "+tblcol);
								for (int j = 2; j < columns_count+tblcol; j = j + 2) {
									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getText()) +"||")
								}
								break;
							case("/explore"):
								int tblcol=GlobalVariable.G_rowcount_Katalon;
								System.out.println("This is the value of the variable tblcol: "+tblcol);
								if((tbl_main).equals('//*[@id="file_tab_table"]')){
									tblcol=tblcol-2
									System.out.println("This is the count of tblcol after adjusting: "+tblcol)
								}
								if((statValue)==0){
									System.out.println("inside the if loop for statvalue equal to 0 : already collected the header data")
								}else{
									System.out.println("This is the count of tblcol inside Trials: "+tblcol) //tblcol =10 for cases tab subtract 2 from it
									System.out.println("This is the value of data before data collection: "+data)
									for (int j = 1; j <= tblcol-2; j = j + 1) {
										System.out.println("Value of i is: "+i)
										System.out.println("Value of j is: "+j)
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]/div[2]")).getAttribute("innerText")) +"||")   //*[@id="case_tab_table"]//tbody/tr[1]/td[1]/div[2]/div/a
										System.out.println("This is the value of data: "+data)
									}
								}
								break;
							default:
							//System.out.println("Trials Case did not match")
								break;
						}


					}
					//@@@@@@@@@@@@@@@@ Bento table data collection starts here  @@@@@@@@@@@@@@@
					if(switchString == "Bento"){
						System.out.println("inside Bento switch structure");

						switch(switchBento){

							case("/explore"):
								System.out.println("Inside Bento switch for all cases")
								int tblcol=GlobalVariable.G_rowcount_Katalon;
								data = ""
								System.out.println("Value of columns_count variable : "+columns_count+"\nValue of tblcol variable : "+tblcol)

								if((tbl_main).equals('//*[@id="case_tab_table"]')) {
									for (int j = 1; j <columns_count+1; j = j + 1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: " + j)
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr[" + i + "]/td[" + (j+1) +"]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data : "+data)
									}
								}else if((tbl_main).equals('//*[@id="sample_tab_table"]')){
									for (int j = 1; j <columns_count+1; j = j + 1) {
										System.out.println("Value of i is: "+ i +"\nValue of j is: " + j)
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr[" + i + "]/td[" + (j+1) +"]")).getAttribute("innerText")) +"||")
										System.out.println("This is the value of data : "+data)
									}
								}else if((tbl_main).equals('//*[@id="file_tab_table"]')){
									for (int j = 1; j <columns_count+1; j = j + 1) {
										if((colHeader.get(j).getAttribute("innerText"))!="Access") {
											System.out.println("Value of i is: "+ i +"\nValue of j is: " + j)
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr[" + i + "]/td[" + (j+1) +"]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data : "+data)
										}
									}
								}else {
									KeywordUtil.markFailed("Invalid Tab name! Check Bento table data collection function")
								}

								break;
							case("/fileCentricCart"):
								int tblcol=GlobalVariable.G_rowcount_Katalon;
								for (int j = 1; j < columns_count+tblcol; j = j + 1) {
									System.out.println("Value of i is: "+i+"\nValue of j is: "+j)
									data = data+((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getAttribute("innerText")) +"||")
									System.out.println("This is the value of data : "+data)
								}
								break;
							case("/programs"):
								System.out.println("Inside Bento switch for all Programs cases")
								int tblcol=GlobalVariable.G_rowcount_Katalon;
								System.out.println("Value of columns_count variable : "+columns_count + "\nValue of tblcol variable : "+tblcol)
								for (int j = 1; j < columns_count+1; j = j + 1) {
									System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)

									//if((colHeader.get(j).getAttribute("innerText"))!="PubMed ID") {
									//System.out.println("This is the name of Pgm table column header  :"+colHeader.get(j).getAttribute("innerText"))
									//System.out.println("This is the value of data before calculating the index for innertext of the td: "+data)

									//data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + (j+1) + "]/*[2]")).getAttribute("innerText")) +"||")
									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getAttribute("innerText").trim()) +"||")
									System.out.println("This is the value of data :"+data)
									//}
								}
								break;

							default:
								KeywordUtil.markFailed("Bento Case switch did not match")
								break;
						}
					}
					System.out.println("===================  Verification of the data: ===================== \n"+ data)

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

				if (nextButton.getAttribute("class").contains("disabled")){
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


	/**
	 * This function reads Bento Statbar
	 * @param bProgs
	 * @param bArms
	 * @param bCases
	 * @param bSamples
	 * @param bAssays
	 * @param bFiles
	 */
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

		GlobalVariable.G_StatBar_Programs = driver.findElement(By.xpath(xbProgs)).getAttribute('innerHTML');
		System.out.println("This is the value of Programs count from Stat bar :"+GlobalVariable.G_StatBar_Programs)
		GlobalVariable.G_StatBar_Arms = driver.findElement(By.xpath(xbArms)).getAttribute('innerHTML');
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

	/**
	 * This function reads the count displayed near the cart icon in ICDC
	 * @param cmyCartCount
	 */
	@Keyword
	public void readMyCartCount(String cmyCartCount)
	{
		Thread.sleep(5000);
		String xcmyCartCount = givexpath(cmyCartCount)
		Thread.sleep(2000)
		GlobalVariable.G_myCartTotal = driver.findElement(By.xpath(xcmyCartCount)).getAttribute("innerText");
		System.out.println("This is the value of count from cart icon :"+GlobalVariable.G_myCartTotal)
	}

	/**
	 * This function reads Canine Statbar
	 * @param cProgs
	 * @param cStuds
	 * @param cCases
	 * @param cSamples
	 * @param cFiles
	 * @param cStudyFiles
	 */
	@Keyword
	public void readStatBarCanine(String cProgs, String cStuds, String cCases, String cSamples, String cFiles, String cStudyFiles)
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

	/**
	 * This function reads CTDC Statbar
	 * @param tTrials
	 * @param tCases
	 * @param tFiles
	 */
	@Keyword
	public void readTrialsStatBar(String tTrials, String tCases, String tFiles){
		String xpTrials = givexpath(tTrials)
		String xpCases = givexpath(tCases)
		String xpFiles = givexpath(tFiles)
		GlobalVariable.G_TStatBar_Trials = driver.findElement(By.xpath(xpTrials)).getText();
		System.out.println("This is the value of Trials count from Stat bar :"+GlobalVariable.G_TStatBar_Trials)
		GlobalVariable.G_TStatBar_Cases = driver.findElement(By.xpath(xpCases)).getText();
		System.out.println("This is the value of Cases count from Stat bar :"+GlobalVariable.G_TStatBar_Cases)
		GlobalVariable.G_TStatBar_Files = driver.findElement(By.xpath(xpFiles)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_TStatBar_Files)
	}

	@Keyword
	public void readINSStatBar(String tProgs, String tProjs,String tGrants, String tPubs, String tDsets, String tClinTrials, String tPatents){
		String xpProgs = givexpath(tProgs)
		String xpProjs = givexpath(tProjs)
		String xpGrants = givexpath(tGrants)
		String xpPubs = givexpath(tPubs)
		String xpDsets = givexpath(tDsets)
		String xpClinTrials = givexpath(tClinTrials)
		String xpPatents = givexpath(tPatents)
		GlobalVariable.G_StatBar_Programs = driver.findElement(By.xpath(xpProgs)).getText();
		System.out.println("This is the value of Programs count from Stat bar :"+GlobalVariable.G_StatBar_Programs)
		GlobalVariable.G_StatBar_Projects = driver.findElement(By.xpath(xpProjs)).getText();
		System.out.println("This is the value of Projects count from Stat bar :"+GlobalVariable.G_StatBar_Projects)
		GlobalVariable.G_StatBar_Grants = driver.findElement(By.xpath(xpGrants)).getText();
		System.out.println("This is the value of Grants count from Stat bar :"+GlobalVariable.G_StatBar_Grants)
		GlobalVariable.G_StatBar_Publications = driver.findElement(By.xpath(xpPubs)).getText();
		System.out.println("This is the value of Publications count from Stat bar :"+GlobalVariable.G_StatBar_Publications)
		GlobalVariable.G_StatBar_Datasets = driver.findElement(By.xpath(xpDsets)).getText();
		System.out.println("This is the value of Datasets count from Stat bar :"+GlobalVariable.G_StatBar_Datasets)
		GlobalVariable.G_StatBar_ClinTrials = driver.findElement(By.xpath(xpClinTrials)).getText();
		System.out.println("This is the value of Clinical Trials count from Stat bar :"+GlobalVariable.G_StatBar_ClinTrials)
		GlobalVariable.G_StatBar_Patents = driver.findElement(By.xpath(xpPatents)).getText();
		System.out.println("This is the value of Patents count from Stat bar :"+GlobalVariable.G_StatBar_Patents)
	}


	/**
	 * This function reads GMB Statbar
	 * @param gTrials
	 * @param gSubjects
	 * @param gFiles
	 */
	@Keyword
	public void readGMBStatBar(String gTrials, String gSubjects, String gFiles){
		String gmbTrials = givexpath(gTrials)
		String gmbSubjects = givexpath(gSubjects)
		String gmbFiles = givexpath(gFiles)

		Thread.sleep(5000)
		GlobalVariable.G_GStatBar_Trials = driver.findElement(By.xpath(gmbTrials)).getAttribute("innerText")
		System.out.println("This is the value of Trials count from Stat bar: "+GlobalVariable.G_GStatBar_Trials)
		Thread.sleep(2000)
		GlobalVariable.G_GStatBar_Subjects = driver.findElement(By.xpath(gmbSubjects)).getAttribute("innerText")
		System.out.println("This is the value of Subjects count from Stat bar: "+GlobalVariable.G_GStatBar_Subjects)
		Thread.sleep(2000)
		GlobalVariable.G_GStatBar_Files = driver.findElement(By.xpath(gmbFiles)).getAttribute("innerText")
		System.out.println("This is the value of Case Files count from Stat bar: "+GlobalVariable.G_GStatBar_Files)
	}


	/**
	 * This function reads CDS Statbar
	 * @param cdsStuds
	 * @param cdsDisesSite
	 * @param cdsParticipants
	 * @param cdsSamples
	 * @param cdsFiles
	 */
	@Keyword
	public void readStatBarCDS(String cdsStuds, String cdsParticipants, String cdsSamples, String cdsFiles)
	{
		Thread.sleep(5000);

		String cStuds = givexpath(cdsStuds)
		//String cdisSite = givexpath(cdsDisesSite)
		String cParticipants = givexpath(cdsParticipants)
		String cSamples = givexpath(cdsSamples)
		String cFiles = givexpath(cdsFiles)

		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Studies = driver.findElement(By.xpath(cStuds)).getAttribute('innerHTML');
		System.out.println("This is the value of Studies count from Stat bar: "+GlobalVariable.G_StatBar_Studies)
		Thread.sleep(2000)
		//GlobalVariable.G_StatBar_DisSite = driver.findElement(By.xpath(cdisSite)).getText();
		//System.out.println("This is the value of Disease Sites count from Stat bar: "+GlobalVariable.G_StatBar_DisSite)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Participants = driver.findElement(By.xpath(cParticipants)).getAttribute('innerHTML');
		System.out.println("This is the value of Participants count from Stat bar: "+GlobalVariable.G_StatBar_Participants)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Samples = driver.findElement(By.xpath(cSamples)).getAttribute('innerHTML');
		System.out.println("This is the value of Samples count from Stat bar: "+GlobalVariable.G_StatBar_Samples)
		Thread.sleep(2000)
		GlobalVariable.G_StatBar_Files = driver.findElement(By.xpath(cFiles)).getAttribute('innerHTML');
		System.out.println("This is the value of Files count from Stat bar: "+GlobalVariable.G_StatBar_Files)
	}


	//This function returns the xpath of a given string (from the obj stored in katalons object repository)
	@Keyword
	public static String givexpath(String objname) {
		TestObject obj = findTestObject(objname)
		String xpathOfObj = obj.findPropertyValue('xpath')
		System.out.println(xpathOfObj)
		return xpathOfObj;

	}


	//@@@@@@@@@@@@@@@@ Write web result to excel @@@@@@@@@@@@@@@@
	/**
	 * This function write webData to excel
	 * @param webSheetName
	 */
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
	}//write to excel method ends here


	/**
	 * This function is used for bento local find functionality
	 */
	@Keyword
	public void BentoLocalFindDdn() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String ddnXpath = givexpath('Object Repository/Bento/Cases_page/Bento_LocalSearch_popup');
		System.out.println("This is the value of xpath of the dynamic ddn element:"+ddnXpath);
		// Locating the Main Menu (Parent element)
		WebElement dynDDn = driver.findElement(By.xpath(ddnXpath));
		js.executeScript("arguments[0].scrollIntoView(true);", dynDDn);

		//Instantiating Actions class
		Actions actions = new Actions(driver);
		//Hovering on main menu
		actions.moveToElement(dynDDn);

		String optionXpath = givexpath('Object Repository/Bento/Cases_page/Bento_LocalSearch_option');
		System.out.println("This is the value of xpath of the option element:"+optionXpath);
		// Locating the element from Sub Menu
		WebElement firstOption = driver.findElement(By.xpath(optionXpath));
		js.executeScript("arguments[0].scrollIntoView(true);", firstOption);

		Thread.sleep(3000)
		//To mouseover on sub menu
		actions.moveToElement(firstOption);
		Thread.sleep(3000)
		//build()- used to compile all the actions into a single step
		actions.click().build().perform();
		Thread.sleep(3000)
		System.out.println("Reporting frm the keyword : about to complete running bento local find function")
	}

	@Keyword
	public void BentoLocalFindFileUpld(String filetype) {
		String fileUpldXpath = givexpath('Object Repository/Bento/Cases_page/Bento_LocalSearch_Upld_WndwsFileUpload');
		WebElement flUpld=driver.findElement(By.xpath(fileUpldXpath));
		Path inpFile;
		// windows file upload with file path
		if (filetype == 'TXT') {
			inpFile = Paths.get(System.getProperty("user.dir"), "InputFiles", "BentoUploadCaseSet.txt");
		}else if (filetype == 'CSV') {
			inpFile = Paths.get(System.getProperty("user.dir"), "InputFiles", "BentoUploadCaseSet.csv");
		}
		String inpFileStr = inpFile.toString();
		flUpld.sendKeys(inpFileStr);
		Thread.sleep(3000)
		System.out.println("This is the value of the input file for case id local find upload : "+inpFileStr)

	}

	//THIS IS FOR CTDC LOCALFIND**************************************
	@Keyword
	public void CTDCLocalFindDdn() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String ddnXpath = givexpath('Object Repository/Trials/Cases_page/Trials_LocalFind_popup');
		System.out.println("This is the value of xpath of the dynamic ddn element:"+ddnXpath);
		// Locating the Main Menu (Parent element)
		WebElement dynDDn = driver.findElement(By.xpath(ddnXpath));
		js.executeScript("arguments[0].scrollIntoView(true);", dynDDn);

		//Instantiating Actions class
		Actions actions = new Actions(driver);
		//Hovering on main menu
		actions.moveToElement(dynDDn);
		String optionXpath = givexpath('Object Repository/Trials/Cases_page/Trials_LocalFind_option');
		System.out.println("This is the value of xpath of the option element:"+optionXpath);
		// Locating the element from Sub Menu
		WebElement firstOption = driver.findElement(By.xpath(optionXpath));
		js.executeScript("arguments[0].scrollIntoView(true);", firstOption);

		Thread.sleep(3000)
		//To mouseover on sub menu
		actions.moveToElement(firstOption);
		Thread.sleep(3000)
		//build()- used to compile all the actions into a single step
		actions.click().build().perform();
		Thread.sleep(3000)
		System.out.println("Reporting frm the keyword : about to complete running ctdc local find function")
	}

	@Keyword
	public void canineUIValidation() {
		HashMap<String, String> hshmap = new HashMap<String, String>();    /*declaring HashMap */
		hshmap.put("Study Dropdown", 'Object Repository/Canine/Filter/Study/Canine_Filter_Study');  /*Adding elements to HashMap*/
		hshmap.put("Study Type Dropdown", 'Object Repository/Canine/Filter/StudyType/Canine_Filter_StudyType');
		hshmap.put("Breed Dropdown", 'Object Repository/Canine/Filter/Breed/BREED_Ddn');
		hshmap.put("Diagnosis Dropdown", 'Object Repository/Canine/Filter/Diagnosis/DIAGNOSIS_Ddn');
		hshmap.put("Primary Disease Site Dropdown", 'Object Repository/Canine/Filter/PrimDiseaseSite/PRIMARYDISEASESITE_Ddn');
		hshmap.put("Sex Dropdown", 'Object Repository/Canine/Filter/Sex/SEX_Ddn');

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

		System.out.println("passing hash map to the validation function")
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

	//trying new comparetwolists function
	public static List<String> findMismatchedItems(List<XSSFCell> list1, List<XSSFCell> list2) {
		List<String> mismatchedItems = new ArrayList<>();

		if (list1.size() != list2.size()) {
			mismatchedItems.add("List sizes are different.");
			return mismatchedItems;
		}

		for (int i = 0; i < list1.size(); i++) {
			XSSFCell cell1 = list1.get(i);
			XSSFCell cell2 = list2.get(i);

			if (!cell1.toString().equals(cell2.toString())) {
				mismatchedItems.add("Cell " + (i + 1) + ": " + cell1.toString() + " <> " + cell2.toString());
			}
		}

		return mismatchedItems;
	}


	//trying another comparelists function
	public static List<String> findMismatchedItemstry2(List<XSSFCell> list1, List<XSSFCell> list2) {
		List<String> mismatchedItems = new ArrayList<>();

		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();

		for (XSSFCell cell : list1) {
			set1.add(cell.toString());
		}

		for (XSSFCell cell : list2) {
			set2.add(cell.toString());
		}

		if (!set1.equals(set2)) {
			mismatchedItems.add("Lists have different cell values.");
		}

		return mismatchedItems;
	}

	//compare lists***********************************************************
	public static void compareTwoLists( List<List<XSSFCell>> l1, List<List<XSSFCell>> l2 ){
		System.out.println ("Comparing two Lists");
		int l2row=0;
		//	for( int l2row = 0; l2row < l2.size(); l2row++ ){
		while( l2row < l2.size() ){
			//			List<XSSFCell> l2rowList = l2.get(l2row)
			//			System.out.println(" This is the contents of DB data : " + l2rowList )

			for( int l1row = 0; l1row < l1.size(); l1row++ ){

				List<XSSFCell> l1rowList = l1.get(l1row)
				System.out.println(" This is the contents of Web(UI) data : " + l1rowList )

				List<XSSFCell> l2rowList = l2.get(l2row)
				System.out.println(" This is the contents of DB data : " + l2rowList )

				//				 System.out.println(" This is the contents of DB data at getL2row : " + l2rowList.get(l2row).getStringCellValue() )
				//				 System.out.println(" This is the contents of Web data at getL1row : " + l1rowList.get(l1row).getStringCellValue() )

				//				if( l2rowList.get(0).getStringCellValue() == l1rowList.get(0).getStringCellValue()){ //takes CTDC ID as the primary key for comparison & checks if two values are equal
				//
				System.out.println(" Comparing UI data : " + l1rowList)
				System.out.println( " and corresponding DB data : " + l2rowList )

				boolean l1NullFlag = false, l2NullFlag = false
				for(int col = 0; col < l2rowList.size(); col++ ){ //compares all the columns in the excels - for each row
					if( l1rowList.get(col) == null || l1rowList.get(col).equals("") || l1rowList.get(col).getCellType() == l1rowList.get(col).CELL_TYPE_BLANK ){
						System.out.println("There is a NULL entry in UI Data Row")
						l1NullFlag = true
					}
					if( l2rowList.get(col) == null || l2rowList.get(col).equals("") || l2rowList.get(col).getCellType() == l2rowList.get(col).CELL_TYPE_BLANK ){
						System.out.println("There is a NULL entry in DB Data Row")
						l2NullFlag = true
					}
					if( l1NullFlag == l2NullFlag ) { }//System.out.println("Content Matches for col number: "+ col)
					else System.out.println("Content does not match for col number: " + col )

					if( l1NullFlag || l2NullFlag ) continue   //if the data mismatches, print the data found in ui and db
						System.out.println("UI data value is: "+ l1rowList.get(col).getStringCellValue() + "\nDB data value is: "+ l2rowList.get(col).getStringCellValue() )

					if( l1rowList.get(col).getStringCellValue() == l2rowList.get(col).getStringCellValue() ){
						System.out.println("Content matches for col number : " + col )
					}else{
						System.err.println("***********DATA MISMATCH:  ABORTING RUN********************")
						System.out.println("Content does not match for col: " + col )
						System.out.println( "UI data Value (mismatch): " + l1rowList.get(col).getStringCellValue() )
						System.out.println( "DB data Value (mismatch): " + l2rowList.get(col).getStringCellValue() )
						KeywordUtil.markFailed("***********DATA MISMATCH in comparelists:  ABORTING RUN********************");

						//add steps for handling failure
					}
				}
				//				}else{
				//					System.out.println("UI Data and DB Data are not matching for :")
				//					// add what the code should display if contents mismatch outside the main loop for CTDC ID
				//				}
				l2row++} //l1forloop
		} // l2 while loop
	}

	//**************************************************
	@Keyword

	public static void compareLists(String webSheetName, String neoSheetName) {  //pass the sheet names only. file name is not needed
		List<List<XSSFCell>> UIData = new ArrayList<>()
		List<List<XSSFCell>> neo4jData = new ArrayList<>()
		String UIfilename =  GlobalVariable.G_WebExcel.toString()   //UIfilepath.toString()
		System.out.println("This is the full uifilepath after converting to string :"+UIfilename);
		//UIData = ReadExcel.readExceltoWeblist(UIfilename,GlobalVariable.G_WebTabnameCasesCasesCases)  //change the function name Test in parent class and here
		UIData = ReadExcel.readExceltoWeblist(UIfilename,webSheetName)


		System.out.println("This is the UI data read by comparelists function : "+UIData)
		System.out.println ("This is the row size of the UIdata : "+ UIData.size());
		Collections.sort( UIData , new runtestcaseforKatalon() )
		//	Collections.sort(UIData)

		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		System.out.println("This is the full neo4j filepath after converting to string :"+neo4jfilename);
		//neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_CypherTabnameCasesCasesCases)  //change the function name Test in parent class and here
		neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,neoSheetName)

		System.out.println ("This is the row size of the Neo4jdata : "+ neo4jData.size());
		System.out.println("This is the neo4j data read by comparelists function : "+neo4jData)
		Collections.sort( neo4jData , new runtestcaseforKatalon() )
		//	Collections.sort(neo4jData)

		compareTwoLists(UIData,neo4jData)  //This compares the two sorted lists - ui data and db data
	}


	//**************************************************************************************
	//this is a duplicate of comparelists created to test the xl manifest and cart xl comparison
	@Keyword
	//public static void compareLists(String wCasesSheet, String wCaseDetailsSheet, String nCasesSheet, String nCaseDetailsSheet) {  //pass the sheet names only. file name is not needed
	public static void compareManifestLists(String webCartSheetName, String manifestSheetName) {  //pass the sheet names only. file name is not needed
		System.out.println("This is the name of the current test case from global variable : "+GlobalVariable.G_currentTCName)
		String newfilename = GlobalVariable.G_currentTCName+"_Manifest";
		String xlsxManifestName = newfilename +".xlsx";
		Path xlsxfilename = Paths.get(System.getProperty("user.dir"), "OutputFiles", xlsxManifestName);
		System.out.println("This is the file name of xlsx manifest: "+GlobalVariable.G_xlsxFileName);

		List<List<XSSFCell>> UIData = new ArrayList<>()
		List<List<XSSFCell>> manifestData = new ArrayList<>()
		String UIfilename =  GlobalVariable.G_WebExcel.toString()
		System.out.println("This is the full webdata pathname for my cart :"+UIfilename);
		UIData = ReadExcel.readExceltoWeblist(UIfilename,webCartSheetName)
		//C:\Users\radhakrishnang2\Desktop\Commons_Automation\OutputFiles\TC01_Bento_E2E_Select-All-Add-To-Cart_WebData

		System.out.println("This is the data read and stored in arraylist UIData : "+UIData)
		System.out.println ("This is the row size of the UIdata : "+ UIData.size());
		Collections.sort( UIData , new runtestcaseforKatalon())

		GlobalVariable.G_xlsxFilename = xlsxfilename.toString()
		//System.out.println("This is the file name of xlsx manifest: "+manifestFileName);
		//		System.out.println("This is the full neo4j filepath after converting to string :"+manifestFileName);
		//neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_CypherTabnameCasesCasesCases)  //change the function name Test in parent class and here
		manifestData = ReadExcel.readExceltoWeblist(GlobalVariable.G_xlsxFilename, manifestSheetName)

		System.out.println ("This is the row size of the Neo4jdata : "+ manifestData.size());
		Collections.sort( manifestData , new runtestcaseforKatalon())
		compareTwoLists(UIData,manifestData)
	}

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
			System.out.println("This is the value of Programs Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())  //add in the query in input file later
			System.out.println("This is the value of Arms Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Cases Count from Neo4j result "+statData.get(0).get(2).getStringCellValue())
			System.out.println("This is the value of Samples Count from Neo4j result "+statData.get(0).get(3).getStringCellValue())
			System.out.println("This is the value of Assays Count from Neo4j result "+statData.get(0).get(4).getStringCellValue())
			System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(5).getStringCellValue())

			//assert statData.get(0).get(0).getStringCellValue()==GlobalVariable.G_StatBar_ClinTrials :KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Programs)) ? KeywordUtil.markPassed("Statbar Programs count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Programs count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Arms)) ? KeywordUtil.markPassed("Statbar Arms count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Arms count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
			(statData.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Samples count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Samples count")
			(statData.get(0).get(4).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Assays)) ? KeywordUtil.markPassed("Statbar Assays count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Assays count")
			(statData.get(0).get(5).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		}else if (getAppName=='INS') {

			System.out.println("This is the value of Programs Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
			System.out.println("This is the value of Projects Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Grants Count from Neo4j result "+statData.get(0).get(2).getStringCellValue())
			System.out.println("This is the value of Publications Count from Neo4j result "+statData.get(0).get(3).getStringCellValue())
			System.out.println("This is the value of Datasets Count from Neo4j result "+statData.get(0).get(4).getStringCellValue())
			System.out.println("This is the value of Clinical Trials Count from Neo4j result "+statData.get(0).get(5).getStringCellValue())
			System.out.println("This is the value of Patents from Neo4j result "+statData.get(0).get(6).getStringCellValue())
			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Programs)) ? KeywordUtil.markPassed("Statbar Programs count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Programs count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Projects)) ? KeywordUtil.markPassed("Statbar Projects count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Projects count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Grants)) ? KeywordUtil.markPassed("Statbar Grants count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Grants count")
			(statData.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Publications)) ? KeywordUtil.markPassed("Statbar Publications count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Publications count")
			(statData.get(0).get(4).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Datasets)) ? KeywordUtil.markPassed("Statbar Datasets count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Datasets count")
			(statData.get(0).get(5).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_ClinTrials)) ? KeywordUtil.markPassed("Statbar clinical Trials count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Clinical Trials count")
			(statData.get(0).get(6).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Patents)) ? KeywordUtil.markPassed("Statbar Patents count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Patents count")

		}else if (getAppName=='ICDC'){
			System.out.println ("control is in line 1842");
			System.out.println("This is the value of Programs Count from Neo4j result: "+statData.get(0).get(0).getStringCellValue())
			System.out.println("This is the value of Studies Count from Neo4j result: "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Cases Count from Neo4j result: "+statData.get(0).get(2).getStringCellValue())
			System.out.println("This is the value of Samples Count from Neo4j result: "+statData.get(0).get(3).getStringCellValue())
			System.out.println("This is the value of CaseFiles Count from Neo4j result: "+statData.get(0).get(4).getStringCellValue())
			System.out.println("This is the value of StudyFiles Count from Neo4j result: "+statData.get(0).get(5).getStringCellValue())
			//assert statData.get(0).get(0).getStringCellValue()==GlobalVariable.G_StatBar_ClinTrials :KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Programs)) ? KeywordUtil.markPassed("Statbar Programs count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Programs count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Studies)) ? KeywordUtil.markPassed("Statbar Studies count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Studies count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
			(statData.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Samples count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Samples count")
			(statData.get(0).get(4).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Case Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Case Files count")
			(statData.get(0).get(5).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_StudyFiles)) ? KeywordUtil.markPassed("Statbar Study Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Study Files count")

		}
		else if (getAppName=='CTDC') {

			System.out.println("This is the value of Trials Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
			System.out.println("This is the value of Cases Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(2).getStringCellValue())
			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_TStatBar_Trials)) ? KeywordUtil.markPassed("Statbar Trials count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Trials count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_TStatBar_Cases)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_TStatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		}
		else if (getAppName=='GMB') {

			System.out.println("This is the value of Trails Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
			System.out.println("This is the value of Subjects Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(2).getStringCellValue())
			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_GStatBar_Trials)) ? KeywordUtil.markPassed("Statbar Trials count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Trials count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_GStatBar_Subjects)) ? KeywordUtil.markPassed("Statbar Subjects count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Subjects count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_GStatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		}
		else if (getAppName=='CDS'){

			System.out.println("This is the value of Studies Count from Neo4j result: "+statData.get(0).get(0).getStringCellValue())  //add in the query in input file later
			System.out.println("This is the value of Participants Count from Neo4j result: "+statData.get(0).get(1).getStringCellValue())
			System.out.println("This is the value of Samples Count from Neo4j result: "+statData.get(0).get(2).getStringCellValue())
			System.out.println("This is the value of Files Count from Neo4j result: "+statData.get(0).get(3).getStringCellValue())

			(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Studies)) ? KeywordUtil.markPassed("Statbar Studies count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Studies count")
			(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Participants)) ? KeywordUtil.markPassed("Statbar Participants count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Participants count")
			(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Samples)) ? KeywordUtil.markPassed("Statbar Samples count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Samples count")
			(statData.get(0).get(3).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Files)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
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
		statData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_StatTabname)  //change the function name Test in parent class and here
		System.out.println("This is the first row - stat data read from neo4j stat sheet : "+statData[0])
		System.out.println("This is the value of Files Count from Neo4j result "+statData.get(0).get(0).getStringCellValue())
		System.out.println("This is the value of Cases Count from Neo4j result "+statData.get(0).get(1).getStringCellValue())
		System.out.println("This is the value of Trials Count from Neo4j result"+statData.get(0).get(2).getStringCellValue())
		(statData.get(0).get(0).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Datasets)) ? KeywordUtil.markPassed("Statbar Files count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Files count")
		(statData.get(0).get(1).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_ClinTrials)) ? KeywordUtil.markPassed("Statbar Cases count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Cases count")
		(statData.get(0).get(2).getStringCellValue().contentEquals(GlobalVariable.G_StatBar_Grants)) ? KeywordUtil.markPassed("Statbar Trials count matches"): KeywordUtil.markFailed("Mismatch in Stat Bar Trials count")
	}


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


	@Keyword
	public static clickTabCanineStat(String TbName){

		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rawTabName = TbName
		String tabxpath = givexpath(TbName)
		System.out.println("This is the value of xpath of the element:"+tabxpath);
		WebElement resultTab = driver.findElement(By.xpath(tabxpath));
		js.executeScript("arguments[0].scrollIntoView(true);", resultTab);
		js.executeScript("arguments[0].click();", resultTab);
		System.out.println("Successfully clicked desired element")

		String xcCases = givexpath('Object Repository/Canine/StatBar/Canine_StatBar-Cases')
		GlobalVariable.G_StatBar_Cases = driver.findElement(By.xpath(xcCases)).getAttribute("innerText");
		System.out.println("This is the value of Cases count from Stat bar :"+GlobalVariable.G_StatBar_Cases)
		Thread.sleep(1000)
	}

	@Keyword
	public static clickTabINSStat(String TbName){

		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rawTabName = TbName
		String tabxpath = givexpath(TbName)
		System.out.println("This is the value of xpath of the element:"+tabxpath);
		WebElement resultTab = driver.findElement(By.xpath(tabxpath));
		js.executeScript("arguments[0].scrollIntoView(true);", resultTab);
		js.executeScript("arguments[0].click();", resultTab);
		System.out.println("Successfully clicked desired element")

		String xcprojects = givexpath('Object Repository/INS/Statbar/INS_Statbar-Projects')
		GlobalVariable.G_StatBar_Grants = driver.findElement(By.xpath(xcprojects)).getAttribute("innerText");
		System.out.println("This is the value of Projects count from Stat bar :"+GlobalVariable.G_StatBar_Grants)
		Thread.sleep(1000)
	}

	@Keyword
	public static clickTabGMBStat(String TbName){

		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rawTabName = TbName
		String tabxpath = givexpath(TbName)
		System.out.println("This is the value of xpath of the element: "+tabxpath);
		WebElement resultTab = driver.findElement(By.xpath(tabxpath));
		js.executeScript("arguments[0].scrollIntoView(true);", resultTab);
		js.executeScript("arguments[0].click();", resultTab);
		System.out.println("Successfully clicked desired element")


		String xcSubjects = givexpath('Object Repository/GMB/StatBar/GMB_StatBar-Subjects')
		GlobalVariable.G_StatBar_Grants = driver.findElement(By.xpath(xcSubjects)).getAttribute("innerText");
		System.out.println("This is the value of Subjects count from Stat bar :"+GlobalVariable.G_GStatBar_Subjects)
		Thread.sleep(1000)
	}


	@Keyword
	public static clickTabCDSStat(String TbName){

		JavascriptExecutor js = (JavascriptExecutor)driver;
		String rawTabName = TbName
		String tabxpath = givexpath(TbName)
		System.out.println("This is the value of xpath of the element: "+tabxpath);
		WebElement resultTab = driver.findElement(By.xpath(tabxpath));
		js.executeScript("arguments[0].scrollIntoView(true);", resultTab);
		js.executeScript("arguments[0].click();", resultTab);
		System.out.println("Successfully clicked desired element")


		String xcStudies = givexpath('Object Repository/CDS/StatBar/CDS_StatBar-Studies')
		GlobalVariable.G_StatBar_Files = driver.findElement(By.xpath(xcStudies)).getAttribute("innerText");
		System.out.println("This is the value of Studies count from Stat bar: "+GlobalVariable.G_StatBar_Files)
		Thread.sleep(3000)
	}
	@Keyword
	public static void scrolltoViewjs(WebElement elem){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", elem);
	}
	@Keyword
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

				if (driver.getCurrentUrl().contains("bento-tools.org/")){
					one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::div//parent::td//preceding-sibling::td/div/span/span/input"
				}
				else if (driver.getCurrentUrl().contains("caninecommons")){

					one_path ="//a[contains( text(),'"+ caseID +"')]//parent::div//parent::div//parent::td//preceding-sibling::td/div/span/span/input"
				}

				WebElement checkbox =driver.findElement(By.xpath(one_path))
				js.executeScript("arguments[0].click();", checkbox)

				break;
			case ("all"):
				String all_path ="//div[@id=\'case_tab_table\']//thead/tr/th/div/span/span/input"

				System.out.println ("All Path :" + all_path )
				WebElement checkbox =driver.findElement(By.xpath(all_path))
				js.executeScript("arguments[0].click();", checkbox)

				break;
			case ("allM"):
				String all_M="//div[text()=\'Case ID\']//parent::div//parent::div//parent::span//parent::th//preceding-sibling::th/div/span/span/input"
				System.out.println ("All Path :" + all_M )
				WebElement checkbox =driver.findElement(By.xpath(all_M))
				js.executeScript("arguments[0].click();", checkbox)

				break;
			case ("caseM"):
				String all_M="//div[text()='" + caseID + "']//parent::div//parent::div//parent::span//parent::th//preceding-sibling::th/div/span/span/input"
				System.out.println ("All Path :" + all_M )
				WebElement checkbox =driver.findElement(By.xpath(all_M))
				js.executeScript("arguments[0].click();", checkbox)
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
		WebElement checkbox =driver.findElement(By.xpath(one_path))
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
		System.out.println("This is the size of the rows in the table in first page in files: "+(rows_count))
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
			System.out.println("This is the size of the rows in the table in the current page: "+(rows_count))
			for(int i = 1; i <= rows_count; i++) { //rows_count
				String data = ""
				String sCase
				int tblcol=GlobalVariable.G_rowcount_Katalon; //12 //19 This is for icdc

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
		System.out.println("This is the contents of globalvar G_casesarray: " +GlobalVariable.G_CasesArray)

	}
	//}

	@Keyword
	public static void clickcase(String lCases )
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i

		String Str1
		Str1 = "//a[contains(@href,'" + lCases + "')]"
		WebElement caseIDlink =driver.findElement(By.xpath(Str1))
		js.executeScript("arguments[0].click();", caseIDlink)
		System.out.println ("This is the url of the current page - case details (before reading case details table): "+driver.getCurrentUrl())
		// calling the below function reads the data in the case details table
		ReadCasesTableKatalon(GlobalVariable.G_StatBar_Grants,'Object Repository/Bento/CaseDetail_page/Bento_CDFilesTable','Object Repository/Bento/CaseDetail_page/Bento_CDFilesTable_Hdr', 'Object Repository/Bento/CaseDetail_page/Bento_CDFilesTable_NxtBtn',GlobalVariable.G_caseDetailsTabName)

		driver.navigate().back()
		System.out.println ("This is the url of the current page - all cases, AFTER reading case details table) :"+driver.getCurrentUrl())
		casedetailsQueryBuilder(lCases)
	}


	@Keyword
	public static void casedetailsQueryBuilder(String lCases )
	{
		System.out.println("This is the value of lcasesfromfunction: "+lCases)
		System.out.println("First part new is : "+GlobalVariable.G_CaseDetailsQFirstPart)
		System.out.println("Second part new is : "+GlobalVariable.G_CaseDetailsQSecondPart)

		String finalQ = GlobalVariable.G_CaseDetailsQFirstPart + lCases + GlobalVariable.G_CaseDetailsQSecondPart
		System.out.println ("This is the concatenated query for breed greyhound: "+finalQ )

		GlobalVariable.G_CaseDetailQ=finalQ
		System.out.println ("This is the reassigned global variable from query builder function: "+GlobalVariable.G_CaseDetailQ )
	}






}  //class ends here
