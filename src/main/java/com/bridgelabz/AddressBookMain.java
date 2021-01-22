package com.bridgelabz;

import java.util.*;

class Contact {

    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String state;
    private int zip;
    private int phone_number;
    private String email;
    Scanner sc = new Scanner(System.in);

    public String addContact() {
        boolean input = true;
        System.out.println("enter first name");
        first_name = sc.nextLine();
        System.out.println("enter last name");
        last_name = sc.nextLine();
        System.out.println("enter address");
        address = sc.nextLine();
        System.out.println("enter city");
        city = sc.nextLine();
        System.out.println("enter state");
        state = sc.nextLine();
        while (input) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("enter zip");
                zip = sc.nextInt();
                input = false;
            }catch (InputMismatchException e) {
                System.out.println("wrong input");
                input = true;
            }
        }
        input = true;
        while (input){
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("enter phone number");
                phone_number = sc.nextInt();
                sc.reset();
                input = false;
            }catch (InputMismatchException e) {
                System.out.println("wrong input");
                input = true;
            }
        }
        System.out.println("enter email");
        email = sc.nextLine();

        return first_name + " " + last_name;
    }

    public String editContactByName(){
        Scanner sc = new Scanner(System.in);
        String firstName = first_name;
        String lastName = last_name;

            while(true){

                System.out.println("\n**edit contact**\n1. first name   2. last name");
                System.out.println("3. address      4. city");
                System.out.println("5. state        6. zip");
                System.out.println("7. phone number 8. email");
                System.out.println("any key. exit");

                int choice = sc.nextInt();
                sc.nextLine();
                if(choice < 1 || choice > 8)
                    break;

                System.out.print("Enter field: ");
                String field = sc.nextLine();

                switch(choice){
                    case 1:	first_name = field;
                        firstName = first_name;
                        break;
                    case 2:	last_name = field;
                        lastName = last_name;
                        break;
                    case 3:	address = field;
                        break;
                    case 4:	city = field;
                        break;
                    case 5:	state = field;
                        break;
                    case 6:	zip = Integer.parseInt(field);
                        break;
                    case 7:	phone_number = Integer.parseInt(field);
                        break;
                    case 8:	email = field;
                        break;
                }
            }
            return firstName+" "+lastName;
        }
        public void showContact(){
            System.out.println("first name: "+first_name);
            System.out.println("last name: "+last_name);
            System.out.println("address: "+address);
            System.out.println("city: "+city);
            System.out.println("state: "+state);
            System.out.println("zip: "+zip);
            System.out.println("phone number: "+phone_number);
            System.out.println("email: "+email);

        }
}
public class AddressBookMain {

    static Map<String, Contact> addressBook = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    static Map<String, Map<String, Contact>> addressBookCollection = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    static String addressBookName = "default";
    static Contact contact;
    static String contactName;

    AddressBookMain(){
        addressBookCollection.put("default", addressBook);
    }
    public static void addContact(){

        contact = new Contact();
        contactName = contact.addContact();
        addressBookCollection.get(addressBookName).put(contactName, contact);
     //   addressBookCollection.put(addressBookName, addressBook);
    }

    private static void editContact() {
        Scanner sc = new Scanner(System.in);
        contact = new Contact();
        System.out.println("Enter full name: ");
        String contactName = sc.nextLine();
        if(addressBookCollection.get(addressBookName).containsKey(contactName)) {
            contact = addressBookCollection.get(addressBookName).get(contactName);
            addressBookCollection.get(addressBookName).remove(contactName);
            contactName = contact.editContactByName();
            addressBookCollection.get(addressBookName).put(contactName, contact);
        }
        else
            System.out.println("contact don't exist");
    }

    static public void deleteContact(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter full name");
        contactName = sc.nextLine();

        if(addressBookCollection.get(addressBookName).containsKey(contactName)){
            addressBookCollection.get(addressBookName).remove(contactName);
            System.out.println("contact deleted");
        }
        else System.out.println("contact don't exist");
    }

    static public void showContact(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter full name");
        contactName = sc.nextLine();

        if(addressBookCollection.get(addressBookName).containsKey(contactName)){
            addressBookCollection.get(addressBookName).get(contactName).showContact();
        }
        else System.out.println("contact don't exist");

    }
    public static void addAddressBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter new address book name: ");
        String addressBookNameTemp = sc.nextLine();
        if (!addressBookCollection.containsKey(addressBookNameTemp)){
            addressBook = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            addressBookCollection.put(addressBookNameTemp, addressBook);
            addressBookName = addressBookNameTemp;
        }
        else System.out.println("address book already exist");
    }
    public static void changeAddressBook(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter address book name: ");
        String addressBookNameTemp = sc.nextLine();
        if (addressBookCollection.containsKey(addressBookNameTemp))
            addressBookName = addressBookNameTemp;
        else System.out.println("address book don't exist");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AddressBookMain addressBookMain = new AddressBookMain();
     //   addressBookCollection.put("default", )
        System.out.println("**Welcome to Address Book**");
        while (true){
            System.out.println("*********s**** Address Book: "+ addressBookName +" ************");
            System.out.println("********************* MENU *********************");
            System.out.println("1. add contact  2. edit contact      3. delete contact");
            System.out.println("4. show contact 5. add address book  6. change address book");
            int choice = sc.nextInt();

            switch(choice){
                case 1: addContact();
                break;
                case 2: editContact();
                break;
                case 3: deleteContact();
                break;
                case 4: showContact();
                break;
                case 5: addAddressBook();
                break;
                case 6: changeAddressBook();
                break;
            }
        }
    }
}
