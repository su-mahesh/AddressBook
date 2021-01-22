package com.bridgelabz;

import java.util.InputMismatchException;
import java.util.Scanner;

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
}
public class AddressBookMain {
    static Scanner sc = new Scanner(System.in);
    static Contact contact;

    public AddressBookMain() {
        contact = new Contact();
    }
    public static void add(){
        contact = new Contact();
        String contactName = contact.addContact();
    }

    public static void main(String[] args) {

        System.out.println("**Welcome to Address Book**");
        while (true){
            System.out.println("1. add contact");

            int choice = sc.nextInt();

            switch (choice){
                case 1: add();
                break;
            }
        }
    }
}
