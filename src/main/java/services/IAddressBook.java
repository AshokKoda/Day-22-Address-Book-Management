package services;

import java.io.FileNotFoundException;

public interface IAddressBook {

	public void readJson();
	public void createNewAddressBook();
	public void openAddressBook();
	public void save();
	public void saveAs() throws FileNotFoundException;
	
	public void addPerson();
	public void editPerson();
	public void deletePerson();
}
