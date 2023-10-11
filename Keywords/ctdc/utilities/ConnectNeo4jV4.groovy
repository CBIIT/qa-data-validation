/**
 *  ConnectNeo4jV4 connects to neo4j V4 version and store the result in excel sheet. 
 *
 * @author  Yizhen Chen
 * @version 2.0
 * @since   2022-06-10
 */


package ctdc.utilities
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Transaction;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public   class ConnectNeo4jV4{

	private List<String>  messages = new ArrayList<String>();

	public void run(String uri,String user,String password,String cypher,String output,String sheetName){

		List<String>  excelData = new ArrayList<String>();

		excelData =  executeCypher(uri,user,password,cypher);

		messages.add("Neo4j_URL:");
		messages.add(uri);
		messages.add("User_name:");
		messages.add(user);
		messages.add("PWD:");
		messages.add(password);
		messages.add("Cypher:");
		messages.add(cypher);
		messages.add("Output:");
		messages.add(output);

		export(excelData,output,sheetName);
	}


	public List<String>  executeCypher(String uri,String user,String password,String cypher) {

		Gson gson = new Gson();

		List<String>  output = new ArrayList<String>();

		// Neo4j driver
		Driver driver = GraphDatabase.driver(uri, AuthTokens.basic( user, password ) );

		try{
			Session session = driver.session()
			output= session.readTransaction( new TransactionWork<List<String> >()
					{
						@Override
						public List<String>  execute( Transaction tx )
						{

							Result result = tx.run(cypher);

							//store all the records in a list
							List<String> cypherOutput = new ArrayList<String>();

							while ( result.hasNext() ) {
								// iterate the result set and convert it into json format
								Record record = result.next();
								cypherOutput.add(gson.toJson(record.asMap()));
							}
							return cypherOutput;
						}
					});

			driver.close();
			return output;
		}catch(Exception e ) {

			System.out.print(e.getMessage());
			messages.add(e.getMessage());

		}finally{
			return output;
		}
	}

	public void export(List<String> data, String output,String sheetName) throws IOException {
		// define json parser
		JsonParser jsonParser = new JsonParser();

		File tempFile = new File(output);

		boolean exists = tempFile.exists();

		Workbook workbook=exists? new XSSFWorkbook(new FileInputStream(tempFile)): new XSSFWorkbook();

		Sheet sheet = workbook.getSheet(sheetName);

		int sheetIndex = workbook.getSheetIndex(sheetName);

		if(sheetIndex ==-1) {
			sheet = workbook.createSheet(sheetName);
		}

		// create header row
		Row header = sheet.createRow(0);

		if(null!=data && data.size()>0) {
			// create header
			// Parser first record as Json Object
			JsonElement headerJsonTree = jsonParser.parse(data.get(0));
			JsonObject headerJsonObject = headerJsonTree.getAsJsonObject();
			// Get a collection-view of the records
			Set<Map.Entry<String,JsonElement>> setHeaderDate = headerJsonObject.entrySet();
			int headerIndex = 0;
			// Iterate the collection-view of the records
			for(Map.Entry<String, JsonElement> m : setHeaderDate) {

				Cell headerCell = header.createCell(headerIndex);

				headerCell.setCellValue(m.getKey());
				headerIndex++;
			}
			// write record into excel
			int rowIndex = 0;
			for(String str : data) {
				System.out.println("Data STR:  "+str);
				rowIndex++;
				// Create row
				Row row = sheet.createRow(rowIndex);
				//  Parse a record
				JsonElement jsonTree = jsonParser.parse(str);
				// make sure it is  a Json Object
				if(jsonTree.isJsonObject()) {
					// Parser as Json Object
					JsonObject jsonObject = jsonTree.getAsJsonObject();
					// Get a collection-view of the records
					Set<Map.Entry<String,JsonElement>> setDate = jsonObject.entrySet();
					int recordIndex = 0 ;
					// Iterate the collection-view of the records
					for(Map.Entry<String, JsonElement> m : setDate) {
						// create cell
						Cell cell = row.createCell(recordIndex);
						//newly added line to address the "" Omic"" issue adds \\Omic\\

						String strM = m.getValue().toString();

						if(strM.startsWith("\"") && strM.endsWith("\"")  ) {

							strM = strM.substring(1,strM.length()-1);

						}
						strM = strM.replace("\\\"", "\"")



						cell.setCellValue(strM);
						//cell.setCellValue(m.getValue().toString().replace("\\\"", "\""));
						recordIndex++;
					}
				}
			}
		}
		// Add result tab
		workbook = addNewTabWithSimpleData(workbook,sheetName+"_Message",messages);

		saveWorkBookToFile(workbook,output);
	}


	private void saveWorkBookToFile(Workbook workbook,String output){
		// Save as file
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(output);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private Workbook addNewTabWithSimpleData(Workbook workbook,String sheetName,List<String> data ){

		Sheet infoSheet = workbook.getSheet(sheetName);

		int sheetIndex = workbook.getSheetIndex(sheetName);

		if(sheetIndex ==-1) {
			infoSheet = workbook.createSheet(sheetName);
		}

		int messageIndex = 0 ;
		for(String message : data) {
			Row row = infoSheet.createRow(messageIndex);
			Cell cell = row.createCell(0);
			cell.setCellValue(message.replace("\"", ""));
			messageIndex++;
		}
		return workbook;
	}

	
	
	
}