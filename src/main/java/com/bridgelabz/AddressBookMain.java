package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
}
public class AddressBookMain {

    static Map<String, Contact> addressBook = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    static Contact contact;

    public AddressBookMain() {
        contact = new Contact();
    }
    public static void addContact(){
        contact = new Contact();
        String contactName = contact.addContact();
        addressBook.put(contactName, contact);
    }

    private static void editContact() {
        Scanner sc = new Scanner(System.in);
        contact = new Contact();
        System.out.println("Enter full name: ");
        String contactName = sc.nextLine();
        if(addressBook.containsKey(contactName)) {
            contact = addressBook.get(contactName);
            addressBook.remove(contactName);
            contactName = contact.editContactByName();
            addressBook.put(contactName, contact);
        }
        else
            System.out.println("contact don't exist");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("**Welcome to Address Book**");
        while (true){
            System.out.println("1. add contact 2. edit contact");
            int choice = sc.nextInt();

            switch(choice){
                case 1: addContact();
                break;
                case 2: editContact();
            }
        }
    }


}
