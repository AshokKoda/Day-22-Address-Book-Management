package controller;

import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import services.ImplAddressBook;

public class AddressBookManagement {

	public static void main(String[] args) 
			throws InterruptedException, JsonParseException, JsonMappingException, IOException{
		
		System.out.println("------Welcome To Address Book--------");
		
		ImplAddressBook util = new ImplAddressBook();
		Scanner scanner = new Scanner(System.in);
		
		//getting file if exist and if it is json the reading it again
		//and getting all the objects and lists of json into program
		boolean isExitAddressBook = false;
		System.out.println("Address book manager\n");
		
		while (!isExitAddressBook) {
			System.out.println("Select menu");
			System.out.println("1. New Address Book\n2. Open Address Book\n" + "3. Save Address Book\n"
					+ "4. SaveAs Address Book\n" + "5. Quit");
			int choice = scanner.nextInt();
			
			switch (choice) {
			case 1:
				// new address book
				util.createNewAddressBook();

				break;
			case 2:
				// open
				// opening address book
				util.openAddressBook();

				break;
			case 3:
				// save
				util.save();
				break;
			case 4:
				// saveAs
				util.saveAs();
				break;
			case 5:
				// quit
				isExitAddressBook = true;
				System.out.println("Thank you for your time");

				break;
			default:
				System.out.println("Invalid option");
				break;

			}
		}
		scanner.close();

	}

}
