package addressbookwithcsv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class AddressBookUsingCSV {
	
	public static void writeData() {
		try {
			CSVWriter csvWriter = new CSVWriter(new FileWriter("csvfile.csv"));
			String[] contact = "Koda, Ashok, a.koda@gmail.com, 123456789, 531163".split(",");
			String[] contact2 = "Vasupalli, Navin, navin@gmail.com, 123456789, 531163".split(",");
			csvWriter.writeNext(contact);
			csvWriter.writeNext(contact2);
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readDataLineByLine() {
		try {
			CSVReader reader = new CSVReader(new FileReader("csvfile.csv"), ',' , '"' , 1);
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine != null) {
					//Verifying the read data here
					System.out.println(Arrays.toString(nextLine));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Write Data:");
		writeData();
		System.out.println("Read Data:");
		readDataLineByLine();
	}

}
