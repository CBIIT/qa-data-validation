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



public class ICDCstudyDetails extends runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}



	@Keyword
	public static void ReadTable(String tbl1, String hdr1, String nxtb1, String webSheetName) throws IOException {
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

		List<String> wTableHdrData = new ArrayList<String>(); //to capture the table header data
		List<String> wTableBodyData = new ArrayList<String>(); //to capture the table body data
		String tbl_bdy;
		String tbl_main= givexpath(tbl1)
		System.out.println("This is the value of tbl main : "+tbl_main)

		tbl_bdy= tbl_main+"//tbody"
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy
		System.out.println("This is the value of table body :"+GlobalVariable.G_cannine_caseTblBdy)

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

		if (((driver.getCurrentUrl()).contains("caninecommons")||(driver.getCurrentUrl()).contains("icdc.bento-tools.org"))&&((driver.getCurrentUrl()).contains("/study/"))){
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("This is the value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			nxtBtn =  driver.findElement(By.xpath(givexpath(nxtb1)));
			System.out.println("This is the value of next button from canine cases switch: "+nxtBtn)

			columns_count = (colHeader.size())
			for(int c=0;c<columns_count;c++){
				System.out.println ("This is the value of col header index : "+c)
				hdrdata = hdrdata + (colHeader.get(c).getAttribute("innerText")) + "||"

			} // for loop ends
		}

		wTableHdrData.add(hdrdata);

		System.out.println("No.of columns in the current result tab is : "+columns_count)
		System.out.println("Complete list of column headers in current result tab is : "+wTableHdrData)

		for(int index = 0; index < wTableHdrData.size(); index++) {
			System.out.println("Header data of the table is :" + wTableHdrData.get(index))
		}

		//@@@@@@@@@@@@@@@@@@  COLLECTING THE TABLE BODY DATA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		int counter=1;
		//		if (statValue !=0) {
		while(counter <= 10){
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
						case("/study/"):  //added for ICDC study details page - study files tab validation
							System.out.println("Inside study details page - arms & cohorts tab");
							int tblcol=columns_count;
							System.out.println("This is the val of tblcol: "+tblcol)
							System.out.println("Study Details page - arms & cohorts - body data collection **************** "+ data)
							data = ""
							for (int j = 1; j<= tblcol; j++) {
								System.out.println("Value of i is: "+i)
								System.out.println("Value of j is: "+j)

								//check if the inside cell we have more data
								WebElement el =driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]"))
								List<WebElement> childEl = el.findElements(By.xpath("*"))
								int countChildEl = childEl.size();
								System.out.println("Cell contains " + countChildEl + " rows")
								if(countChildEl>1) {
									for(int k=1; k<=countChildEl; k++) {
										data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]/li[" + k + "]")).getAttribute("innerText")))
										System.out.println("This is the value of data :"+data)
									}
								}else {
									data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]")).getAttribute("innerText")) +"||")
									System.out.println("This is the value of data :"+data)
								}
							}
							break;

						default:
							System.out.println("Canine Case did not match")
							break;
					} //canine switch ends here

				}//canine if ends here


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

		writeToExcel(webSheetName);
		System.out.println("webdata written to excel successfully")

	}//ReadTable ends


	@Keyword
	public static void multiFunctionSD(String appName, String tbl, String tblHdr, String nxtBtn, String webdataSheetName, String dbdataSheetName, String tabQuery) throws IOException {
		System.out.println("Inside multifunctionSD");
		ReadTable(tbl, tblHdr, nxtBtn, webdataSheetName)
		System.out.println("control is after read table webdataxl creation and before readexcel neo4j function")
		ReadExcel.Neo4j(dbdataSheetName,tabQuery)
		System.out.println("control is before compare lists function from multifunctionSD")
		compareLists(webdataSheetName, dbdataSheetName)

	}




}
