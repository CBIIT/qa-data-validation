
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.Keys;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Cookie as Cookie


class functions extends runtestcaseforKatalon implements Comparator<List<XSSFCell>>{
	public int compare( List<XSSFCell> l1, List<XSSFCell> l2 ){
		return l1.get(0).getStringCellValue().compareTo( l2.get(0).getStringCellValue() )
	}
	public static WebDriver driver

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
			ReadTabKatalon(statVal, tbl,tblHdr,nxtBtn,webdataSheetName)
			System.out.println("Control is after read table webdataxl creation and before readexcel neo4j function")
			ReadExcel.Neo4j(dbdataSheetName,tabQuery)
			System.out.println("Control is before compare lists function from multifunction")
			compareLists(webdataSheetName, dbdataSheetName)
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
	public static void ReadTabKatalon(String statVal1, String tbl1, String hdr1, String nxtb1, String webSheetName) throws IOException {
		//Add app name below
		String switchCanine
		String switchString
		WebElement nextButton
		WebElement nxtBtn
		WebElement resultTab

		WebDriverWait wait = new WebDriverWait(driver,30);
		int statValue = convStringtoInt(statVal1);
		System.out.println("This is the passed value of stat for this run: "+statValue)

		List<String> wTableHdrData = new ArrayList<String>();
		List<String> wTableBodyData = new ArrayList<String>();
		String tbl_bdy;
		String tbl_main= givexpath(tbl1)
		System.out.println("This is the value of tbl main: "+tbl_main)

		tbl_bdy= tbl_main+"//tbody"
		GlobalVariable.G_cannine_caseTblBdy=tbl_bdy  //correct his variables name typo and also rename it to G_commons_casetblbdy
		System.out.println("This is the value of table body: "+GlobalVariable.G_cannine_caseTblBdy)

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tbl_bdy)));
		scrolltoViewjs(driver.findElement(By.xpath(tbl_bdy)))
		clickElement(driver.findElement(By.xpath(tbl_bdy)));

		WebElement TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
		List<WebElement> rows_table = TableBdy.findElements(By.tagName("tr"))
		System.out.println("This is the value of weblement rows table: "+rows_table);

		int rows_count = rows_table.size()
		System.out.println("This is the size of the rows in the results table in first page: "+(rows_count))
		String nxt_str = givexpath(nxtb1)
		System.out.println("This is the value of the xpath of nextbtn: "+nxt_str)
		nextButton = driver.findElement(By.xpath(nxt_str));
		System.out.println("This is the value of the webelem next button from readcasestablekatalon method: "+nextButton)
		System.out.println("This is the value of the hdr object: "+hdr1)
		String hdr_str= givexpath(hdr1)
		System.out.println("This is the value of the hdr string - xpath : "+hdr_str)
		WebElement tableHdr = driver.findElement(By.xpath(hdr_str))

		List<WebElement> colHeader = tableHdr.findElements(By.tagName("th"));

		int columns_count
		String hdrdata = ""

		//Read ICDC table header from result table for a specific tab
		String crntUrl = driver.getCurrentUrl();
		if( (crntUrl.contains("caninecommons") && crntUrl.contains("/studies")) ||  (crntUrl.contains("caninecommons") && crntUrl.contains("/program"))   )   {
			switchCanine = getPageSwitch();
			switchString = "Canine";
			System.out.println ("Value of CANINE switch string returned by getcurrentpage function: "+switchCanine)
			columns_count = (colHeader.size())
			GlobalVariable.colCnt=columns_count
			System.out.println ("Value of col count fm global var is: "+GlobalVariable.colCnt)
			for(int c=0;c<columns_count;c++){

				String colHdrVlu = colHeader.get(c).getAttribute("innerText");

				if((colHdrVlu=="Study Code") || (colHdrVlu=="Program") || (colHdrVlu=="Study Name")
				|| (colHdrVlu=="Study Type") || (colHdrVlu=="Accession ID") || (colHdrVlu=="Cases")) {

					System.out.println ("Value of col header index from Studies is: "+c +"\nValue of col header text is: " + colHdrVlu)
					hdrdata = hdrdata + (colHdrVlu) + "||"
				}
			}
		}else {
			System.out.println ("/studies tab is not found in the current URL")
		}

		wTableHdrData.add(hdrdata);
		System.out.println("No.of columns in the current result tab is : "+columns_count)
		System.out.println("Complete list of column headers in current result tab is : "+wTableHdrData)

		for(int index = 0; index < wTableHdrData.size(); index++) {
			System.out.println("Header data of the table is :" + wTableHdrData.get(index))
		}
		System.out.println("Val of statistics before while loop: "+statValue);


		//*********** COLLECTING THE TABLE BODY DATA ***********
		int counter=1;
		if (statValue !=0) {
			while(counter <=1)
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(GlobalVariable.G_cannine_caseTblBdy)));
				scrolltoViewjs(driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy)))
				TableBdy =driver.findElement(By.xpath(GlobalVariable.G_cannine_caseTblBdy))
				Thread.sleep(5000) //Check first and then delete
				rows_table = TableBdy.findElements(By.tagName("tr"))
				Thread.sleep(3000)
				rows_count = rows_table.size()
				System.out.println("This is the size of the rows in the table in the current page: "+(rows_count))

				int i;

				for(i = 1; i <=rows_count; i++) { //before editing for fixing cotb issue

					String data = ""
					String headerlabel =""
					//***********  Canine Studies Tab data collection starts here ***********
					if(switchString == "Canine"){
						System.out.println("Inside Canine Switch Structure")
						switch(switchCanine){

							case("/studies"):
								System.out.println("This is the value of switch string: "+switchCanine)
								int tblcol=GlobalVariable.G_rowcount_Katalon; //Change this to global GlobalVariable.colCnt used in calculating the count of col headers

								if((tbl_main).equals('//*[@id="table_studies"]/div/div[3]/table')){
									System.out.println ("Value of tblbody inside studies switch case: "+tbl_bdy)
									tblcol=tblcol-2  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+tblcol)

									for (int j = 1; j<=tblcol; j = j + 1) {
										String hdrVlue=colHeader.get(j-1).getAttribute("innerText");

										if((hdrVlue=="Study Code") || (hdrVlue=="Program") || (hdrVlue=="Study Name")
										|| (hdrVlue=="Study Type") || (hdrVlue=="Accession ID") || (hdrVlue=="Cases")) {

											System.out.println("This is the name of column header: "+hdrVlue)
											System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]/div[2]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data: "+data)
										}
									}
								}else{
									System.out.println("Expected column names not fount in the table. Please check!")
								}
								break;



							case("/program"):
								System.out.println("This is the value of switch string: "+switchCanine)
							//	int tblcol=GlobalVariable.G_rowcount_Katalon; //Change this to global GlobalVariable.colCnt used in calculating the count of col headers

								if((tbl_main).equals('//*[@id="table_studies"]/div/div[3]/table')){
									System.out.println ("Value of tblbody inside the table in program page: "+tbl_bdy)
									//	tblcol=tblcol-2  // this is needed when files tab has 11 cols
									System.out.println("This is the count of tblcol when files tab is selected: "+GlobalVariable.colCnt)

									for (int j = 1; j<=GlobalVariable.colCnt; j = j + 1) {
										String hdrVlue=colHeader.get(j-1).getAttribute("innerText");

										if((hdrVlue=="Study Code") || (hdrVlue=="Program") || (hdrVlue=="Study Name")
										|| (hdrVlue=="Study Type") || (hdrVlue=="Accession ID") || (hdrVlue=="Cases")) {

											System.out.println("This is the name of column header: "+hdrVlue)
											System.out.println("Value of i is: "+ i +"\nValue of j is: "+j)
											data = data + ((driver.findElement(By.xpath(tbl_bdy +"/tr" + "[" + i + "]/td[" + j + "]/div[2]")).getAttribute("innerText")) +"||")
											System.out.println("This is the value of data: "+data)
										}
									}
								}else{
									System.out.println("Expected column names not fount in the table. Please check!")
								}
								break;

							default:
								System.out.println("Canine Case did not match")
								break;
						} //Canine switch ends here
					}//Canine if ends here

					System.out.println("===================  Verification of the data: ===================== \n"+ data)
					wTableBodyData.add(data)
				}//for loop ends


				System.out.println("Size of table body list in current result tab is: "+wTableBodyData.size())
				for(int index = 0; index < wTableBodyData.size(); index++) {
					System.out.println("Table body data from current page is: " + wTableBodyData.get(index))
				}
				GlobalVariable.G_CaseData= wTableHdrData + wTableBodyData;
				System.out.println("This is the contents of globalvar G_casedata: " +GlobalVariable.G_CaseData)



				//********************* CLICKING THE NEXT BUTTON IN RESULTS FOR NEXT PAGE *********************
				scrolltoViewjs(nextButton)
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
		System.out.println("Webdata written to excel successfully")

	}//ReadCasesTableKatalon function ends


	//*************** CRDC functions start here ****************

	@Keyword
	public static void navigateToCrdc() {
		driver = CustomBrowserDriver.createWebDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(GlobalVariable.G_Urlname);
		WebUI.waitForPageLoad(10)
	}

	final static String dataFile='CRDC/SubmReqts/Section-A';
	final static String sectionA='CRDC/SubmissionRequest/Section-A/';
	/**
	 * 
	 * @param dataFileRowNum Please add data file row number to be read
	 * @throws IOException
	 */
	@Keyword
	public static void enterPiInfo(int dataFileRowNum) throws IOException {

		//		WebElement piFirstName=driver.findElement(By.xpath('//*[@id="section-a-pi-first-name"]'));
		//		piFirstName.click();
		//		piFirstName.clear();
		//		piFirstName.sendKeys(findTestData('CRDC/Section-A').getValue('pi-first-name', dataFileRowNum));


		WebUI.clearText(findTestObject(sectionA+'PI_FirstName-Txtbx'));
		Thread.sleep(5000);
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_FirstName-Txtbx'), findTestData(dataFile).getValue(
				'pi-first-name', dataFileRowNum));

		//Thread.sleep(5000);

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_LastName-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_LastName-Txtbx'), findTestData(dataFile).getValue(
				'pi-last-name', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_Position-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_Position-Txtbx'), findTestData(dataFile).getValue(
				'position', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_Email-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_Email-Txtbx'), findTestData(dataFile).getValue(
				'pi-email', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_Institution-Dd'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_Institution-Dd'), findTestData(dataFile).getValue(
				'pi-institution', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_InstitutionAddress-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PI_InstitutionAddress-Txtbx'), findTestData(dataFile).getValue(
				'pi-institution-address', dataFileRowNum));
	}

	/**
	 *
	 * @param dataFileRowNum Please add data file row number to be read
	 * @throws IOException
	 */
	@Keyword
	public static void enterPrimaryContactInfo(int dataFileRowNum) {

		Thread.sleep(3000);
		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_FirstName-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_FirstName-Txtbx'), findTestData(dataFile).getValue(
				'pc-first-name', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_LastName-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_LastName-Txtbx'), findTestData(dataFile).getValue(
				'pc-last-name', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Position-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Position-Txtbx'), findTestData(dataFile).getValue(
				'pc-position', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Email-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Email-Txtbx'), findTestData(dataFile).getValue(
				'pc-email', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Institution-Dd'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Institution-Dd'), findTestData(dataFile).getValue(
				'pc-institution', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Phone-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/PrimContact_Phone-Txtbx'), findTestData(dataFile).getValue(
				'pc-phone', dataFileRowNum));
	}


	/**
	 *
	 * @param dataFileRowNum Please add data file row number to be read
	 * @throws IOException
	 */
	@Keyword
	public static void enterAdditionalContactInfo(int dataFileRowNum) {

		Thread.sleep(5000);
		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_FirstName-Txtbx'));
		Thread.sleep(5000);
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_FirstName-Txtbx'), findTestData(dataFile).getValue(
				'ac-first-name', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_LastName-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_LastName-Txtbx'), findTestData(dataFile).getValue(
				'ac-last-name', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Position-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Position-Txtbx'), findTestData(dataFile).getValue(
				'ac-position', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Email-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Email-Txtbx'), findTestData(dataFile).getValue(
				'ac-email', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Institution-Dd'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Institution-Dd'), findTestData(dataFile).getValue(
				'ac-institution', dataFileRowNum));

		WebUI.clearText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Phone-Txtbx'));
		WebUI.setText(findTestObject('CRDC/SubmissionRequest/Section-A/AdditionalContact_Phone-Txtbx'), findTestData(dataFile).getValue(
				'ac-phone', dataFileRowNum));
	}



	/**
	 * The function is used to login to CRDC application.
	 */
	@Keyword
	public static void loginToCrdc() {

		Thread.sleep(4000);
		WebUI.waitForPageLoad(10)
		WebUI.waitForElementPresent(findTestObject('CRDC/NavBar/WarningBanner_Continue-Btn'), 5)
		WebUI.click(findTestObject('CRDC/NavBar/WarningBanner_Continue-Btn'))

		WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login-Dd'), 5)
		WebUI.click(findTestObject('CRDC/Login/Login-Dd'))

		WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login.gov-Btn'), 5)
		WebUI.click(findTestObject('CRDC/Login/Login.gov-Btn'))

		WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login.gov_UserEmail-TxtBx'), 5)
		WebUI.setText(findTestObject('CRDC/Login/Login.gov_UserEmail-TxtBx'), GlobalVariable.userEmail)
		Thread.sleep(2000);

		WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login.gov_UserPass-TxtBx'), 5)
		WebUI.setText(findTestObject('CRDC/Login/Login.gov_UserPass-TxtBx'), GlobalVariable.userPassword)
		Thread.sleep(2000);

		WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login.gov_SignIn-Btn'), 5)
		WebUI.click(findTestObject('CRDC/Login/Login.gov_SignIn-Btn'))

		for (int i=1; i<=3; i++) {

			WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login.gov_BackupSecurityCode-TxtBx'), 5)

			WebUI.setText(findTestObject('CRDC/Login/Login.gov_BackupSecurityCode-TxtBx'),
					findTestData('CRDC/Login/LoginData').getValue('sec-backup-codes', i))
			System.out.println("Entering backup code: " + findTestData('CRDC/Login/LoginData').getValue('sec-backup-codes', i));

			WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login.gov_OneTimeCode_Submit-Btn'), 5)
			WebUI.click(findTestObject('CRDC/Login/Login.gov_OneTimeCode_Submit-Btn'))

			Thread.sleep(3000);
			String url = WebUI.getUrl();

			if (url.contains("idp.int.identity")) {

				System.out.println("Current URL is: "+ url);
				System.out.println("Old code detected, Trying new code...");

			} else {
				System.out.println("Valid Code, Continuing with Consent");
				break;
			}
		}


		WebUI.waitForElementPresent(findTestObject('CRDC/Login/Login.gov_ConsentGrant-Btn'), 5)
		WebUI.click(findTestObject('CRDC/Login/Login.gov_ConsentGrant-Btn'))

		if(WebUI.getUrl().contains("hub")) {
			WebUI.waitForElementPresent(findTestObject('CRDC/Login/UserProfile-Dd'), 5)
			WebUI.verifyElementPresent(findTestObject('CRDC/Login/UserProfile-Dd'), 5)
			String userName = WebUI.getText(findTestObject('CRDC/Login/UserProfile-Dd'));

			if(userName.contains("KATALON"))
				System.out.println("User " + userName + " sucessfully logged in");
			System.out.println("Current URL is: "+ WebUI.getUrl());
		}else {
			System.err.println("Landed on the wrong page!");
		}



	}





}//class ends