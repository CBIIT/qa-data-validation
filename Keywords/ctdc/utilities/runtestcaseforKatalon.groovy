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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.remote.Command
import org.openqa.selenium.JavascriptExecutor;


import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import internal.GlobalVariable






public class runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue() .compareTo( l2.get(0).getStringCellValue() )
	}


	public static WebDriver driver

	@Keyword
	public  void RunKatalon(String input_file) {

		//Thread.sleep(2000)
		Path file_input = Paths.get(System.getProperty("user.dir"), "InputFiles", input_file);
		if ( file_input !=null) {
			KeywordUtil.markPassed("Test case file loaded " + "This is the full filepath after converting to string :"+file_input.toString())
			//	String wholeFileName = file_input.toString()
			GlobalVariable.G_input_file=file_input.toString()
		}
		else{
			KeywordUtil.markPassed ("Password File is not found" )
		}

		KeywordUtil.logInfo("Global variable set for password file is :  " + GlobalVariable.G_input_file )

		List<List<XSSFCell>> sheetData_K = new ArrayList<>();
		FileInputStream fis = new FileInputStream(GlobalVariable.G_input_file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis); // Create an excel workbook from the file system.
		int numberOfSheets = workbook.getNumberOfSheets();  	// Get the  sheets on the workbook
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

				//	System.out.println ( "Header Before switch  :" + sheetData.get(0).get(j).getStringCellValue())
				//System.out.println( "Data in variable : "  + sheetData.get(i).get(j).getStringCellValue())
				//--------------------
				switch(sheetData.get(0).get(j).getStringCellValue().trim() ) //First ROW
				{
					case("dbExcel"):
						GlobalVariable.G_dbexcel = sheetData.get(i).get(j).getStringCellValue()
						Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_dbexcel)
						GlobalVariable.G_ResultPath=dbfilepath.toString()
						break;
					case("query"):  //main db results query
						GlobalVariable.G_Query = sheetData.get(i).get(j).getStringCellValue()

						break;
					case("WebExcel"):
						GlobalVariable.G_WebExcel = sheetData.get(i).get(j).getStringCellValue()
						Path filepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", GlobalVariable.G_WebExcel)
						GlobalVariable.G_WebExcel=filepath.toString()
						break;
					case ("StatQuery"):  //query for stat bar only
						GlobalVariable.G_StatQuery= sheetData.get(i).get(j).getStringCellValue()
						break;
					default :
						System.out.println("Error in initializing")
						break;
				}
				str =str+ cell.getStringCellValue() + "||"
			}
		}
	}



	//----------------web data --------------

	public void  ReadCasesTableKatalon(String tbl1, String hdr1, String nxtb1) throws IOException {

		List<String> webData = new ArrayList<String>();
		String tbl_main= givexpath(tbl1)
		String tbl_bdy=	tbl_main+"//tbody"
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy

		String tbl_str= givexpath(tbl1)							//"//div[contains(text(),'Case')]//parent::span//parent::th//parent::tr//parent::thead//following-sibling::tbody"
		WebElement Table =driver.findElement(By.xpath(tbl_str))

		List<WebElement> rows_table = Table.findElements(By.xpath("//*[contains(@id, \"MUIDataTableBodyRow-\")]"))
		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the table in first page:"+(rows_count))
		String nxt_str=	givexpath(nxtb1)
		WebElement nextButton = driver.findElement(By.xpath(nxt_str));
		String hdr_str= givexpath(hdr1)
		WebElement tableHdr = driver.findElement(By.xpath(hdr_str))

		List<WebElement> colHeader = tableHdr.findElements(By.tagName("th"));
		int columns_count = (colHeader.size())-1
		System.out.println("No.of cols is : "+columns_count)
		String hdrdata = ""
		for(int c=1;c<=columns_count;c++){
			hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"
			//hdrdata = hdrdata + ((colHeader.get(c).getText()) + "||");
			//			System.out.println("This is the value of each header column :"+(colHeader.get(c).getText()))
			//			System.out.println("This is the value stored each time in headerdata :"+hdrdata)
		}
		webData.add(hdrdata);
		System.out.println("Size of web data list with header :" +webData.size())
		for(int index = 0; index < webData.size(); index++) {
			System.out.println("Web Data: with header data is :" + webData.get(index))
		}
		//driver.findElement(By.xpath('//*[@id="root"]/div[3]/div/div[2]/div[1]/div[2]/label/button')).click() // G added this line to close the view
		while(true)
		{
			rows_table = Table.findElements(By.xpath("//*[contains(@id, \"MUIDataTableBodyRow-\")]"))
			rows_count = rows_table.size()
			System.out.println("This is the size of the rows in the table in the current page:"+(rows_count))
			for(int i = 1; i <= rows_count; i++) { //rows_count
				String data = ""
				int tblcol=GlobalVariable.G_rowcount_Katalon; //12 //19
				for (int j = 3; j < columns_count+tblcol; j = j + 2) {
					data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/*[" + j + "]")).getText()) +"||")
				}
				webData.add(data)
			}
			System.out.println("Size of Web Data list with header in current page is : " + webData.size())
			for(int index = 0; index < webData.size(); index++) {
				System.out.println("Web Data: from current page is" + webData.get(index))
			}
			if (nextButton.getAttribute("disabled")) break;
			nextButton.click()
		}
		GlobalVariable.G_CaseData= webData;
		System.out.println("This is the contents of globalvar G_casedata :" +GlobalVariable.G_CaseData)
		//KeywordUtil.markFailed("failed")
		writeToExcel();
	}
	//*********************Validate Stat Bar************************************************************

	@Keyword
	public void readStatBar(String sFiles, String sSamples, String sCases, String sStudies){
		String xpFiles = givexpath(sFiles)
		String xpSamples = givexpath(sSamples)
		String xpCases = givexpath(sCases)
		String xpStudies = givexpath(sStudies)

		GlobalVariable.G_StatBar_Files = driver.findElement(By.xpath(xpFiles)).getText();
		System.out.println("This is the value of Files count from Stat bar :"+GlobalVariable.G_StatBar_Files)
		GlobalVariable.G_StatBar_Samples = driver.findElement(By.xpath(xpSamples)).getText();
		System.out.println("This is the value of Samples count from Stat bar :"+GlobalVariable.G_StatBar_Samples)
		System.out.println("this is the xpath of cases :"+xpCases)
		GlobalVariable.G_StatBar_Cases = driver.findElement(By.xpath(xpCases)).getText();
		System.out.println("This is the value of Cases count from Stat bar :"+GlobalVariable.G_StatBar_Cases)
		GlobalVariable.G_StatBar_Studies = driver.findElement(By.xpath(xpStudies)).getText();
		System.out.println("This is the value of Studies count from Stat bar :"+GlobalVariable.G_StatBar_Studies)

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

	//function to write webData to excel -- this writes the web results to excel
	public static void writeToExcel(){
		try
		{
			String excelPath = GlobalVariable.G_WebExcel;
			FileOutputStream fos = new FileOutputStream(new File(excelPath));
			XSSFWorkbook workbook = new XSSFWorkbook(); 		// Create Workbook instance holding .xls file
			XSSFSheet sheet = workbook.createSheet("WebData"); 		// Create a new Worksheet
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
	}//excel method ends here

	//check if null**********************************************************
	// verify element present
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
						if( l1NullFlag == l2NullFlag )	System.out.println("Content Matches for col: "+ col)
						else System.out.println("Content does not match for col: " + col )
						if( l1NullFlag || l2NullFlag )	continue
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
	@Keyword
	public static void compareLists() {
		List<List<XSSFCell>> UIData = new ArrayList<>()
		List<List<XSSFCell>> neo4jData = new ArrayList<>()
		String UIfilename =  GlobalVariable.G_WebExcel.toString()   //UIfilepath.toString()
		System.out.println("This is the full uifilepath after converting to string :"+UIfilename);
		UIData = ReadExcel.readExceltoWeblist(UIfilename,GlobalVariable.G_WebTabname)  //change the function name Test in parent class and here
		System.out.println("This is the data read after going through Test function : "+UIData)
		System.out.println ("This is the row size of the UIdata : "+ UIData.size());
		Collections.sort( UIData , new runtestcaseforKatalon() )
		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		System.out.println("This is the full neo4j filepath after converting to string :"+neo4jfilename);
		neo4jData = ReadExcel.readExceltoWeblist(neo4jfilename,GlobalVariable.G_CypherTabname)  //change the function name Test in parent class and here
		System.out.println ("This is the row size of the Neo4jdata : "+ neo4jData.size());
		Collections.sort( neo4jData , new runtestcaseforKatalon() )
		compareTwoLists(UIData,neo4jData)
	}


	@Keyword
	public void validateStatBar() {
		List<List<XSSFCell>> statData = new ArrayList<>()
		String neo4jfilename=  GlobalVariable.G_ResultPath.toString()
		//use the following for verifying assertion with invalid data
		//		Path dbfilepath = Paths.get(System.getProperty("user.dir"), "OutputFiles", "TC01_Canine_Filter_Breed-Akita_Neo4jDatainvalid.xlsx")
		//		String neo4jfilename=dbfilepath.toString()

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
}  //class ends here
