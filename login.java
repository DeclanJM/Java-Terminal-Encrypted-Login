import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class login{
    static String filename = "DB.txt";

    //Returns a PrintWriter that appends the DB.txt file
    public static PrintWriter getWriter(){
        try{
        FileWriter fWriter = new FileWriter(filename, true);
        PrintWriter pWriter = new PrintWriter(fWriter);
        return pWriter;
        }catch(FileNotFoundException e){
            System.out.println("No such file: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
    

    //In main application run this in a try catch block
    //Attempts to create a new account unless thrown an error or the account exists already
    public static boolean createUser(int username, int password) throws IOException{  
        PrintWriter pWriter = login.getWriter();
        
        if(!user.verify(username, password)){
            pWriter.println(username + " " + password);
            pWriter.close();
            return true;
        }
        else{
            System.out.println("\nError:  Unable to create account\n\tUsername/Password already exist\n");
            return false;
        }
    }

    //Attempts to login to an existing account
    public static boolean existingUser(int username, int password){
        boolean loginStatus;
        if(user.verify(username, password)){
            loginStatus = true;
        }
        else{
            loginStatus = false;
            System.out.println("Error:  Account does not exist");
        }
        return loginStatus;
    }

    //This method is called from printMenu after the user selects an option
    //The prompts are currently create Account, login to an existing account, or exit
    //I plan on implementing more functionality into this later
    public static void userPrompt(int option) throws IOException{
        Scanner scnr = new Scanner(System.in);
        int username;
        int pass;
        if(option < 0 || option > 3){
            System.out.println("Error: Invalid option\nPlease enter a number between 1 and 3");
            printMenu();
        }
        switch(option){
            case(1):
                System.out.print("Username: ");
                username = hash.leHasher(scnr.next());
                System.out.print("Password: ");
                String password = scnr.next();
                if(password.length() < 10){
                    System.out.println("Invalid Password Length, please try again");
                    userPrompt(1);
                }
                pass = hash.leHasher(scnr.next());
                if(user.verify(username, pass)){
                    System.out.println("Welcome back!");
                }
                else{
                    System.out.println("Oops! Something went wrong, make sure your username and password are correct.");
                    printMenu();
                }
                break;
            case(2):
                System.out.print("Create a Username: ");
                username = hash.leHasher(scnr.next());
                System.out.print("Create a Password (10-characters+): ");
                password = scnr.next();
                if(password.length() < 10){
                    System.out.println("Invalid password length, please try again.");
                    userPrompt(2);
                }
                pass = hash.leHasher(password);
                if(createUser(username, pass)){
                    System.out.println("Congratulations! Your account has successfully been created.");   
                }
                else{
                    System.out.println("Oops! Something went wrong, it looks like this account already exists. Try logging in instead.");   
                }
                printMenu();
                break;
            case(3):
                System.out.println("Goodbye!");
                break;
        }
        scnr.close();
        }

    //The main menu for the program
    //Only thing that needs to be called in a main method to run the program
    public static void printMenu(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Menu Options:  ");
        System.out.println("1. Login\n2. Create Account\n3. Quit");
        int option = Integer.parseInt(scnr.next());
        try {
            userPrompt(option);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Intro splash, basic for now but will update
    public static void printIntro(){
        System.out.println("Welcome to Declan's Encrypted Java Login");
        System.out.println("This application uses a hashing algorithm built by ChatGPT to encrypt the username and password.");
        
    }

    //Tests for the login.java Class
    public static void loginTests(){
        //splashScreen();
        String username = "Marco";
        String password = "Polo";
        int hashU = hash.leHasher(username);
        int hashP = hash.leHasher(password);
        
        try {
            createUser(hashU, hashP);
            System.out.println("User Existence Status:  " + user.verify(hashU, hashP));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        loginTests();
    }
}