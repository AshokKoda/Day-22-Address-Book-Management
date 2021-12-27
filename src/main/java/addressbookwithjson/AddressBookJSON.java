package addressbookwithjson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;


enum EFileNames{
	JSON_FILE_NAME("AddressBook.json");
	
	final String fileName;
	
	private EFileNames(String fileName) {
		this.fileName = fileName;
	}
	
	public String getConstant() {
		return fileName;
	}
}

public class AddressBookJSON {

	public static void main(String[] args) {
		
		File jsonFile = new File(EFileNames.JSON_FILE_NAME.getConstant());
		
		try {
			if(jsonFile.createNewFile()) {
				System.out.println("New file is created");
			}else {
				System.out.println("File is exists!");
			}
			writeJsonFile(jsonFile, getListOfContatcs());
			readJsonFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static List<Contact> getListOfContatcs(){
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("Koda","Ashok", "akoda@gmail.com", "Address is Vizag", 123456789, 531163));
		contacts.add(new Contact("Gundu","Jhon", "john@gmail.com", "Address is Mumbai", 987456321, 520012));
		return contacts;
	}
	
	public static void writeJsonFile(File file, List<Contact> contacts) {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(contacts);
			System.out.println("Json String :: " + jsonString);
			
			FileWriter fileWrite = new FileWriter(EFileNames.JSON_FILE_NAME.getConstant());
			
			fileWrite.write(jsonString);
			System.out.println("Json data written");
			fileWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readJsonFile() {
		
		//JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		
		try {
			FileReader reader = new FileReader(EFileNames.JSON_FILE_NAME.getConstant());
			Object obj = jsonParser.parse(reader); //Read JSON file
			JSONArray jsonList = (JSONArray) obj;
			System.out.println("Json data read");
			System.out.println(jsonList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
