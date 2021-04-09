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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import java.nio.file.Paths
import java.nio.file.Path

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import internal.GlobalVariable


public class FileOperations {



	@Keyword
	public String pickLatestFileFromDownloads() {

		String downloadFolder = Paths.get(System.getProperty("user.dir"), "OutputFiles")
		String newfilename = GlobalVariable.G_currentTCName+"_Manifest"
		String newfilefullpath = Paths.get(System.getProperty("user.dir"), "OutputFiles", newfilename)
		newfilefullpath = newfilefullpath+".csv"  //otherwise it will be different from x-csv format and cant be opened in excel
		System.out.println("This is the full path of the new file : "+newfilefullpath)  //use this for new file full path
		GlobalVariable.newFileName = newfilefullpath;

		File dir = new File(downloadFolder);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			testLogger.info("There is no file in the folder");
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		String k = lastModifiedFile.toString();

		System.out.println(lastModifiedFile);
		Path p = Paths.get(k);
		String oldfilefullpath = p.toString();
		System.out.println ("full filepath of old file : "+oldfilefullpath)  // use this for old file full path
		String oldfile = p.getFileName().toString();
		System.out.println("this is the name of the last modified file from the folder : "+oldfile)
		GlobalVariable.oldFileName=oldfilefullpath
		return oldfile;
	}

	@Keyword
	public static void fileRename() {
		System.out.println("this is the name of the old : "+GlobalVariable.oldFileName)
		System.out.println("this is the name of the new: "+GlobalVariable.newFileName)

		File oldName = new File(GlobalVariable.oldFileName);
		File newName = new File(GlobalVariable.newFileName);
		System.out.println("this is the name of the old file: "+oldName)
		System.out.println("this is the name of the new file: "+newName)

		oldName.renameTo(newName)



		//		if(oldName.renameTo(newName)) {
		//			System.out.println("renamed");
		//		} else {
		//			System.out.println("Error");
		//		}
		
		//rename back to timestamp at last and then println saying this is the timestamp name we are using for this particular test script
	}
}