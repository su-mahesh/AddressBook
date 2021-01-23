package com.bridgelabz;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


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
                try {
                    System.out.println("\n**edit contact**\n1. first name   2. last name");
                    System.out.println("3. address      4. city");
                    System.out.println("5. state        6. zip");
                    System.out.println("7. phone number 8. email");
                    System.out.println("any key. exit");

                    int choice = sc.nextInt();
                    sc.nextLine();
                    if (choice < 1 || choice > 8)
                        break;

                    System.out.print("Enter field: ");
                    String field = sc.nextLine();

                    switch (choice) {
                        case 1:
                            first_name = field;
                            firstName = first_name;
                            break;
                        case 2:
                            last_name = field;
                            lastName = last_name;
                            break;
                        case 3:
                            address = field;
                            break;
                        case 4:
                            city = field;
                            break;
                        case 5:
                            state = field;
                            break;
                        case 6:
                            zip = Integer.parseInt(field);
                            break;
                        case 7:
                            phone_number = Integer.parseInt(field);
                            break;
                        case 8:
                            email = field;
                            break;
                    }
                }catch (Exception e){
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

    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getFullName(){
        return first_name+" "+last_name;
    }

}

public class AddressBookMain {

    static Map<String, Contact> addressBook = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    static ArrayList<Contact> cityContactList = new ArrayList<>();
    static ArrayList<Contact> stateContactList = new ArrayList<>();

    static Map<String, ArrayList<Contact>> addressBookCity = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    static Map<String, ArrayList<Contact>> addressBookState = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

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
        if(!addressBookCollection.get(addressBookName).containsKey(contactName)){
            addressBookCollection.get(addressBookName).put(contactName, contact);

            if(!addressBookCity.containsKey(contact.getCity())) {
                cityContactList = new ArrayList<>();
                addressBookCity.put(contact.getCity(), cityContactList);
            }
            if (!addressBookState.containsKey(contact.getState())){
                stateContactList = new ArrayList<>();
                addressBookState.put(contact.getState(), stateContactList);
            }
            addressBookCity.get(contact.getCity()).add(contact);
            addressBookState.get(contact.getState()).add(contact);

            System.out.println("contact added");
        }
        else System.out.println("contact already exist");

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
        contact = new Contact();
        System.out.println("Enter full name");
        contactName = sc.nextLine();
        String cityName, stateName;
        if(addressBookCollection.get(addressBookName).containsKey(contactName)){
            contact =  addressBookCollection.get(addressBookName).get(contactName);
            cityName = contact.getCity();
            stateName = contact.getState();

            addressBookCollection.get(addressBookName).remove(contactName);

            addressBookCity.get(cityName).remove(contact);
            addressBookState.get(stateName).remove(contact);

            if(addressBookCity.get(cityName).size() == 0)
                addressBookCity.remove(cityName);

            addressBookState.get(stateName).remove(contact);

            if (addressBookState.get(stateName).size() == 0)
                addressBookState.remove(stateName);

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

    public static void searchPerson(){
        Scanner sc = new Scanner(System.in);
        String city = "";
        String state = "";
        System.out.println("Enter full name: ");
        String personName = sc.nextLine();
        System.out.println("1. enter city 2. enter state");
        String ch = sc.nextLine();
        switch (ch){
            case "1":
                System.out.println("Enter city: ");
                city = sc.nextLine();
                break;
            case "2":
                System.out.println("Enter state: ");
                state = sc.nextLine();
                break;
        }

        boolean flag = true;
        for ( Map<String, Contact> addressBook : addressBookCollection.values()){
            for (Contact contactMap : addressBook.values()){
                if(ch.equals("1")) {
                    if (city.equalsIgnoreCase(contactMap.getCity()) && personName.equalsIgnoreCase(contactMap.getFullName())) {
                        flag = false;
                        System.out.println(contactMap.getFullName() + " in " + contactMap.getCity());
                    }
                    else System.out.println("no data found");
                }
                else if (ch.equalsIgnoreCase("2")){
                    if(state.equalsIgnoreCase(contactMap.getState()) && personName.equalsIgnoreCase(contactMap.getFullName())){
                        flag = false;
                        System.out.println(contactMap.getFullName()+" in "+contactMap.getState());
                    }
                    else System.out.println("no data found");
                }

            }
        }
        if(flag)
            System.out.println("no person found");
    }

    public static void viewPersonByCity(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter city name: ");
        String city = sc.nextLine();
        AtomicInteger index = new AtomicInteger(1);
        addressBookCity.forEach((cityName, contactList) ->{
            if(city.equalsIgnoreCase(cityName)){
                System.out.println("City: "+cityName+"\npersons: ");
                contactList.forEach(contactPerson -> {
                System.out.println("\nperson: "+index.getAndIncrement());
                contactPerson.showContact();
        });}});

    }
    public static void viewPersonByState(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter state name: ");
        String state = sc.nextLine();
        AtomicInteger index = new AtomicInteger(1);
        addressBookState.forEach((stateName, contactList) ->{
            if(state.equalsIgnoreCase(stateName)){
                System.out.println("State: "+stateName+"\npersons: ");
                contactList.forEach(contactPerson -> {
                    System.out.println("\nperson: "+index.getAndIncrement());
                    contactPerson.showContact();
                });}});

    }
    public static void getCountByCity(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter city name");
        String city = sc.nextLine();

        if(addressBookCity.get(city) != null)
            System.out.println("total number of contacts in city "+city+": "+addressBookCity.get(city).size());
        else System.out.println("no data found");
    }

    public static void getCountByState(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter state name");
        String state = sc.nextLine();

        if(addressBookCity.get(state) != null)
            System.out.println("total number of contacts in state "+state+": "+addressBookCity.get(state).size());
        else System.out.println("no data found");
    }


    public static void main(String[] args) {
        Scanner sc;
        AddressBookMain addressBookMain = new AddressBookMain();
        System.out.println("**Welcome to Address Book**");
        int choice = 1;
        while(choice < 12 && choice > 0){
            sc = new Scanner(System.in);
            try {
                System.out.println("***************** Address Book: " + addressBookName + " ******************");
                System.out.println("*******************************( MENU )***********************************");
                System.out.println("1. add contact          2. edit contact            3. delete contact");
                System.out.println("4. show contact         5. add address book        6. change address book");
                System.out.println("7. search person        8. view persons by city    9. view persons by state");
                System.out.println("10. get count by city  11. get count by state     12. exit");
                System.out.print("enter: ");

                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addContact();
                        break;
                    case 2:
                        editContact();
                        break;
                    case 3:
                        deleteContact();
                        break;
                    case 4:
                        showContact();
                        break;
                    case 5:
                        addAddressBook();
                        break;
                    case 6:
                        changeAddressBook();
                        break;
                    case 7:
                        searchPerson();
                        break;
                    case 8:
                        viewPersonByCity();
                        break;
                    case 9:
                        viewPersonByState();
                        break;
                    case 10:
                        getCountByCity();
                        break;
                    case 11:
                        getCountByState();
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println("wrong input");
            }
        }
    }
}
