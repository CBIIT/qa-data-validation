package ctdc.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class ExtraFunctions {


	//to get trimmed string********************************************************************************
	def getTrimmedString ( pData ) {
		try {
			if ( pData != null ) {
				return pData.toString ( ).trim ( )
			} else {
				return null
			}

		} catch ( MalformedURLException e ) {
			assert true
			log.error " -------- OBSERVED EXCEPTION ------------------------- : in method getTrimmedString ( pData )"
			assert e in MalformedURLException
		}
	}


	//to split a string using a delimiter********************************************************************************

	def strSplit ( pStr, pDelimiter ) {
		def rtrnStr
		try {
			if ( pStr != "" && pStr != null && pDelimiter != "" && pDelimiter != null ) {
				rtrnStr = pStr.split ( pDelimiter )
			} else {
				rtrnStr = pStr
			}

			return rtrnStr

		} catch ( MalformedURLException e ) {
			assert true
			log.error " -------- EXCEPTION ------------------------- : in method strSplit ( pStr, pDelimiter )"
			assert e in MalformedURLException
		}
	}


	// to get null object when excel input says nullobject******************************************************
	def getNullObject ( pData ){
		try {
			if ( ( pData == "nullobject" ) || ( pData == "NULLOBJECT" ) ) {
				return null
			} else {
				if ( pData == "true" || pData == "TRUE" ) {
					return true
				} else if ( pData == "false" || pData == "FALSE" ) {
					return false
				} else {
					return pData
				}
			}
		} catch ( MalformedURLException e ) {
			assert true
			log.error " -------- OBSERVED EXCEPTION ------------------------- : in method getNullObject ( pData )"
			assert e in MalformedURLException
		}
	}

	//What does this one do ?
	def listIfEmptyOrNull ( pList, pVal ) {
		try {
			if ( pVal != "" && pVal != null ) {
				if ( pVal == "true" || pVal == true ) {
					pList.push ( "Y" )
				}
				else if ( pVal == "false" || pVal == false ) {
					pList.push ( "N" )
				}
				else if ( pVal == "null" || pVal == "" || pVal == null ) {
					pList.push ( "null" )
				}
				else {
					pList.push ( pVal )
				}
			}

			return getSortedList ( pList )

		} catch ( MalformedURLException e ) {
			assert true
			log.error "     -- EXCEPTION ------------------------- : in method listIfEmptyOrNull ( pList, pVal )"
			assert e in MalformedURLException
		}
	}

	//***********************************************************************************
	// recursive helper to traverse the list by depth first, sort the list and return the values, along with printing them to log.
	def getSortedList ( pListToSort ){  // for one dim only
		try {
			def sortedList	 =  ( pListToSort.sort { x, y -> x <=> y } )
			return sortedList
		} catch ( MalformedURLException e ) {
			assert true
			log.error " -------- OBSERVED EXCEPTION ------------------------- : in method getSortedList ( pListToSort )"
			assert e in MalformedURLException
		}
	}



	// get sorted array ********************************************************************
	def sortedArray ( pList, pVal ) {  // for one dim only
		try {
			if ( pVal != "" && pVal != null ) {
				if ( pVal == "true" || pVal == true ) {
					pList.push ( "Y" )
				}
				else if ( pVal == "false" || pVal == false ) {
					pList.push ( "N" )
				}
				else if ( pVal == "null" || pVal == "" || pVal == null ) {
					pList.push ( "null" )
				}
				else {
					pList.push ( pVal )
				}
			}

			return pList

		} catch ( MalformedURLException e ) {
			assert true
			log.error "     -- EXCEPTION ------------------------- : in method listIfEmptyOrNull ( pList, pVal )"
			assert e in MalformedURLException
		}
	}


	//************************************************************************************************************/

	def differenceInLists ( pList1, pList2 ) {  // for one dim only
		try {
			def commons = pList1.intersect(pList2)
			def difference = pList1.plus(pList2)
			def sizeDiff
			difference.removeAll(commons)

			if ( pList1.size > pList2.size ) {
				sizeDiff = pList1.size - pList2.size
			} else {
				sizeDiff = pList2.size - pList1.size
			}

			log.info "     -- DIFFERENCE BETWEEN DB & JSON SIZES -- : " + sizeDiff
			log.info "     -- DIFFERENCE BETWEEN DB & JSON VALUES -- : " + getSortedList ( difference )
			assert pList1.size == pList2.size, "     -- ERROR ------------------------- : Comparison of sizes of DB list & JSON list FAILED."
		} catch ( MalformedURLException e ) {
			assert true
			log.error "     -- EXCEPTION ------------------------- : in method differenceInLists ( pList1, pList2 )"
			assert e in MalformedURLException
		}
	}

	@Keyword
	public void compareLists_1D() {  //public static void main () {
		ArrayList<String> firstList=new ArrayList<String>(Arrays.asList("Orange", "Apple", "Kiwi","Banana"));
		ArrayList<String> secondList=new ArrayList<String>(Arrays.asList("Banana", "Apple", "Kiwi", "Orange"));
		System.out.println("First arraylist before sorting is: "+firstList);
		//		sandbox_g cl = new sandbox_g();
		//		cl.getSortedList(firstList)
		ArrayList<String> sortedFirstList = getSortedList(firstList)
		System.out.println("First arraylist after sorting is: "+sortedFirstList);
		System.out.println("Second arraylist before sorting is: "+secondList);
		ArrayList<String> sortedSecondList = getSortedList(secondList)
		System.out.println("Second arraylist after sorting is: "+sortedSecondList);
		differenceInLists(sortedFirstList,sortedSecondList)
	}

}
