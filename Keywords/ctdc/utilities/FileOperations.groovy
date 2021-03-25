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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import internal.GlobalVariable


public class FileOperations {
	
	@Keyword
	public static void changefileName() {
   
	 FileOperations fr= new FileOperations();
	 File newfile = fr.getTheNewestFile("C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles", "csv");
	 newfile.renameTo(new File("C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles\\renamedManifest.csv"));
	 String filename= newfile.getName();
	
	 System.out.println("latest file is="+filename);
	 
	 FileOperations fr1= new FileOperations();
	 File updated = fr1.getTheNewestFile("C:\\Users\\radhakrishnang2\\Desktop\\Commons_Automation\\OutputFiles", "csv");
	 System.out.println("Changed file name is ="+updated);
	 
	   }
	
	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);
   
		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
   
		return theNewestFile;
	}
   }