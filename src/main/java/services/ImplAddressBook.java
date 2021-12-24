package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entity.Address;
import entity.AddressBookModel;
import entity.Person;

public class ImplAddressBook implements IAddressBook {

	int counter = 0;
	String path = "D:/Eclipse Java Projects/eclipse-workspace/Day-22_Address_Book_Management/src/main/java/jsonfiles/addressbook";
	static AddressBookModel model = new AddressBookModel();
	static ArrayList<Person> persons = new ArrayList<Person>();
	Scanner scanner = new Scanner(System.in);
	String statename = "";
	
	//method for reading json
	public void readJson() {
		File file = new File(path); //checking whether it is empty or not
		if(file.exists() && file.length() != 0) {
			try {
				model = (AddressBookModel) JsonUtil.readMapper(path, model);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			persons.addAll(model.getPersons());
			counter = persons.size();
		}
		
	}

	//creating new address book
	public void createNewAddressBook() {
		System.out.println("-------New Address Book--------");
		System.out.println("Enter state name: ");
		statename = scanner.next();
		boolean isFoundState = false;
		
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAddressObj().getState().equals(statename)) {
				isFoundState = true;
				break;
			}
		}
		
		if (!isFoundState) {
			System.out.println("->State is added<-");
			boolean close = false;
			
			while (!close) {
				System.out.println(
						"Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7.close");
				
				int option = scanner.nextInt();
				switch (option) {
				case 1:
					addPerson(); //Add person
					break;
				default:
					System.out.println("Invalid option");
				}
			}
		}else
			System.out.println("State exist please try again");
		
	}

	//method for opening address book
	public void openAddressBook() {
		System.out.println("----------Open Address Book--------");
		HashMap<String, String> map = new HashMap<>();
		for (int i = 0; i < persons.size(); i++) {
			map.put(persons.get(i).getAddressObj().getState(), persons.get(i).getAddressObj().getState());
		}
		
		System.out.println("States available " + map.keySet());
		System.out.println("Enter state");
		statename = scanner.next();
		
		boolean isFoundState = false;
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getAddressObj().getState().equals(statename)) {
				isFoundState = true;
				break;
			}
		}
		
		if (isFoundState) {
			System.out.println("->State is found<-");
			boolean close2 = false;
			
			while (!close2) {
				System.out.println(
						"Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7.quit");
				switch (scanner.nextInt()) {
				case 1:
					addPerson(); //add person
					break;
					
				default:
					System.out.println("Invalid option");
				}
			}
		}else
			System.out.println("Please create new state of that name\nelse try new state name");
		
	}

	//method for save object into json file
	public void save() {
		System.out.println("---------Save Address Book----------");
		System.out.println("->Saving address book into json<-");
		model.setPersons(persons);
		
		try {
			JsonUtil.writeMapper(path, model);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Saved successful....");
	}

	//method for save as which will save object into json file
	public void saveAs() throws FileNotFoundException {
		System.out.println("-------Save As Address Book------------");
		
		System.out.println("->Save as<-");
		System.out.println("This option requires path where you want to store file");
		System.out.println("for continue press (y/n)");
		
		if (scanner.next().charAt(0) == 'y') {
			String pathInput = "D:/Eclipse Java Projects/eclipse-workspace/Day-22_Address_Book_Management/src/main/java/jsonfiles/";
			//checking whether path is valid or not
			System.out.println("Enter filename");
			pathInput += scanner.next();
			pathInput += ".json";
			
			if (new File(pathInput).exists()) {
				throw new FileNotFoundException("You cannot rewrite existing file");
			}else {
				// writing into file
				System.out.println("->Saving address book into file<-");
				model.setPersons(persons);
				
				try {
					JsonUtil.writeMapper(pathInput, model);
				} catch (IOException e) {

					e.printStackTrace();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println();
				System.out.println("Save as file successful....");
			}
		}
		
	}

	//method for adding person
	@Override
	public void addPerson() {
		System.out.println("Add person details...");
		Person person = new Person();
		
		System.out.println("Enter mobile");
		Long mobile = scanner.nextLong();
		//validating mobile is not taken by anyone
		boolean isMobileTaken = false;
		for (int i = 0; i < persons.size(); i++) {
			if (persons.get(i).getMobile() == mobile) {
				isMobileTaken = true;
				break;
			}
		}
		
		if (isMobileTaken) {
			System.out.println("This mobile number is already taken");
		}else {
			person.setMobile(mobile);
			System.out.println("Enter person first name: ");
			person.setFirstname(scanner.next().toLowerCase());
			System.out.println("Enter person last name: ");
			person.setLastname(scanner.next().toLowerCase());
			System.out.println("Enter address Details: ");
			
			Address address = new Address();
			System.out.println("Enter address: ");
			address.setAddressLocal(scanner.next());
			System.out.println("Enter city: ");
			address.setCity(scanner.next());
			address.setState(statename);
			System.out.println("Enter zip: ");
			address.setZip(scanner.nextInt());
			
			person.setAddressObj(address);
			
			persons.add(person);
			
			System.out.println();
			System.out.println("Person added");
			counter++;
		}
		
	}

}
