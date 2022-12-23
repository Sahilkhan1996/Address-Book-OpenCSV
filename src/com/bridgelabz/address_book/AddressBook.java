package com.bridgelabz.address_book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.exceptions.CsvException;

public class AddressBook {
	static Scanner scanner = new Scanner(System.in);
	List<Contacts> contactsList = new ArrayList<Contacts>();
	List<String[]> stringList = new ArrayList<String[]>();

	public static void main(String[] args) throws CsvException {
		AddressBook bs = new AddressBook();
		bs.Contactlist();
	}

	public void Contactlist() throws CsvException {
		boolean stopper = true;
		while (stopper) {
			System.out.println("Please enter any number to select \n 1. Add Contact"
					+ " \n 2. Edit Contact \n 3. Delete Contact \n 4. Display Contact \n 5. Write in CSV File \n 6. Exit from Contact Menu to Main Menu");
			int selectOption = scanner.nextInt();
			// System.out.println("Input checker: " + selectOption);
			switch (selectOption) {
			case 1: {
				// Adding new contacts to address book
				System.out.println("Enter the number of contacts you would like to enter?");
				int numberOfContacts = scanner.nextInt();
				addingContacts(numberOfContacts);
				System.out.println("All the contacts are added sucessfully!");
				System.out.println("----------------------------------------------------------");
				if (!contactsList.isEmpty()) {
					for (Object object : contactsList) {
						System.out.println(object);
					}
				} else {
					System.out.println("No contacts are added in the Address Book! \n "
							+ "please enter the the contacts in the Address Book");
				}
				System.out.println("----------------------------------------------------------");

				break;

			}

			case 2: {
				// UC3: Editing the contacts which you have added
				System.out.println("Please enter the first name of the added contact to edit: ");
				String firstNameToEdit = scanner.next();
				editContacts(firstNameToEdit);
				System.out.println("----------------------------------------------------------");
				break;
			}

			case 3: {
				// UC4: Deleting the Record with the First name
				System.out.println("Please enter the first name of the added contact to Delete: ");
				String firstNameToEdit1 = scanner.next();
				deleteContacts(firstNameToEdit1);
				System.out.println("----------------------------------------------------------");
				break;

			}
			case 4: {
				// Displaying added contacts
				if (!contactsList.isEmpty()) {
					for (Object object : contactsList) {
						System.out.println(object);
					}
				} else {
					System.out.println("No contacts are added in the Address Book! \n "
							+ "please enter the the contacts in the Address Book");
				}
				System.out.println("----------------------------------------------------------");
				break;
			}
			case 5: {
				String csv = "testContacts.csv";
				try {
					CSVWriter writer = new CSVWriter(new FileWriter(csv));
					writer.writeAll(stringList);
					System.out.println("CSV File written successfully All at a time");
					writer.close();

					/*
					 * //CsvToBean<String[]> csv1 = new CsvToBean<String[]>(); CSVReader csvReader =
					 * new CSVReader(new FileReader(csv));
					 * 
					 * List<String[]> ls=csvReader.readAll(); for(String[] str:ls) {
					 * System.out.println(str); }
					 */
					/*
					 * CSVReaderBuilder csvReader1 = new CSVReaderBuilder(new FileReader(csv));
					 * CSVReader csvReader= csvReader1.build(); String[] row = null;
					 * System.out.println("Read Line by Line Example");
					 * System.out.println("====================================================");
					 * // System.out.println("First Name\tlastName\t\tAgainst\tBy"); while ((row =
					 * csvReader.readNext()) != null) { System.out.println(row[0] + "\t" + row[1] +
					 * "\t" + row[2]+ "\t" + row[3]+ "\t" + row[4]+ "\t" + row[5]+ "\t" + row[6]+
					 * "\t" + row[7]); }
					 * System.out.println("====================================================");
					 */
				//FileReader fileReader = new FileReader(csv);
					System.out.println("check1");
					// create csvReader object
					//CSVReader csvReader = new CSVReaderBuilder(new FileReader(csv)).;
					CSVReader reader = new CSVReader(new FileReader(csv));
					String[] str2;
					while((str2=reader.readNext())!=null) {
						if(str2!=null) {
							System.out.println(Arrays.toString(str2));
						}
					}
					/*System.out.println("check2");
					List<String[]> allData = csvReader.readAll();

					System.out.println(allData);

					for (String[] row : allData) {

						for (String cell : row) {

							System.out.print(cell);
						}
						System.out.println();
					}
					csvReader.close();*/
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			}
			case 6: {
				stopper = false;
				System.out.println("Thank you for using Address Book Program!");
				System.exit(0);
			}

			default:
				System.out.println("Thank you for using Address Book Program!");
				stopper = false;
				throw new IllegalArgumentException("Unexpected value: " + selectOption);
			}
		}

	}

	public void addingContacts(int numberOfContacts) {
		for (int i = 1; i <= numberOfContacts; i++) {

			try {
				Contacts p1 = new Contacts();
				String firstName1 = null;
				// UC7: Checking if the first name is already added else ask the name again to
				// add in the contact
				// until new name not provided by the user
				boolean flag = true;
				while (flag) {
					System.out.println("Please enter the first name: ");
					String firstName = scanner.next();
					firstName1 = firstName;
					if (!checker(firstName)) {// if return true which means the contact with the first name is NOT in
												// the
												// list
						p1.setFirstName(firstName);
						flag = false;
					}
				}
				System.out.println("Please enter the last name: ");
				String lastName = scanner.next();
				p1.setLastName(lastName);
				System.out.println("Please enter the Address: ");
				String address = scanner.next();
				p1.setAddress(address);
				System.out.println("Please enter the city: ");
				String city = scanner.next();
				p1.setCity(city);
				System.out.println("Please enter the state: ");
				String state = scanner.next();
				p1.setState(state);
				System.out.println("Please enter the zip: ");
				Integer zip = scanner.nextInt();
				p1.setZip(zip);
				System.out.println("Please enter the Phone Number: ");
				Integer phone = scanner.nextInt();
				p1.setPhoneNumber(phone);
				System.out.println("Please enter the email: ");
				String email = scanner.next();
				p1.setEmail(email);
				contactsList.add(p1);
				stringList.add(new String[] { firstName1, lastName, address, city, state, zip.toString(),
						phone.toString(), email });

			} catch (InputMismatchException e) {
				System.out.println(e);

			}

			// adding the contact in the array list
			System.out.println(i + " contact are added sucessfully!");
		}

	}

//returns false if the first name is already in the contact list
	private boolean checker(String firstName) {
		boolean res = contactsList.stream().anyMatch(i1 -> i1.getFirstName().equals(firstName));
		return res;
		/*
		 * for (Contacts obj : contactsList) { // if
		 * (firstName.equals(obj.getFirstName())) { if
		 * (firstName.equalsIgnoreCase(obj.getFirstName())) {
		 * System.out.println("Sorry! Duplicate first Name is not allowed."); return
		 * false; } }
		 * 
		 * return true;
		 */
	}

	// I/P:First Name
	// O/P: editing all the details of the contact

	public void editContacts(String firstNameToEdit) {
		boolean isContactFound = true;
		for (Contacts object : contactsList) {
			if (firstNameToEdit.equalsIgnoreCase(object.getFirstName())) {
				System.out.println("Lets edit the contacts: ");
				object.editingContact();
				isContactFound = false;
				break;
			}
		}
		if (isContactFound) {
			System.out.println("Sorry there is no contact with this first name");
		}
	}

	public void deleteContacts(String firstNameToEdit1) {
		boolean isContactFound = true;
		for (Contacts object : contactsList) {
			if (firstNameToEdit1.equalsIgnoreCase(object.getFirstName())) {
				contactsList.remove(object);
				System.out.println("Contact is deleted sucessfully!");
				isContactFound = false;
				break;
			}
		}
		if (isContactFound) {
			System.out.println("Sorry there is no contact with this First Name");
		}

	}
}