import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class user {

    int username;
    int password;
    static String filename = "DB.txt";
    static ArrayList<user> users = user.readFile(filename);

    public user(int username, int password){
        this.username = username;
        this.password = password;
    }
    public String toString(){
        String profile = "";
        profile += (this.username + ": " + this.password);
        return profile;
    }
    public int getUser(){
        return this.username;
    }
    public int getPass(){
        return this.password;
    }


    //Attempts to open the file given filename
    //Opens a scanner and creates a name variable and a password variable each row while the scanner has a next row.
    //then adds a new user with the hashed name and password to the arraylist users
    public static ArrayList<user> readFile(String filename){      
        users = new ArrayList<user>();
        FileInputStream file;
        try {
            file = new FileInputStream(filename);
            Scanner inf = new Scanner(file);
            int fSize = getFileSize(filename);
            
            for(int i = 0; i < fSize; i++){
                int name = Integer.parseInt(inf.next());
                int pass = Integer.parseInt(inf.next());
                users.add(new user(name, pass));
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("\nIOUB brah\n");
            e.printStackTrace();
        }catch (NoSuchElementException e){
            System.out.println("\nNo Such Element\n");
            e.printStackTrace();
        }catch (FileNotFoundException e) {
            System.out.println("\nWhere file?\n");
            e.printStackTrace();
        } 
        return users;
        
    }

    //Verifies that the user and password exist in the DB.txt file.
    public static boolean verify(int username, int password){
        users = readFile("DB.txt");
            //System.out.println(username +": "+ password);
        for(user u : users){
            if(u.getUser() == username && u.getPass() == password){
                return true;
            }
        }
        return false;
    }

    ///Returns an integer value representing the number of rows in the file
    public static int getFileSize(String filename){     
        int size = 0;
        try {
            FileInputStream file = new FileInputStream(filename);
            Scanner inf = new Scanner(file);
            while(inf.hasNextLine()){
                size++;
                inf.nextLine();
            }
            return size;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }

    //Tests for the user.java Class
    public static void userTests(){
        users = readFile("DB.txt");
        int hashPass = users.get(0).getPass();
        System.out.println("Hashed Password: " + hashPass);

        int hU = hash.leHasher("Declan");
        int hP = hash.leHasher("McPassword");

        System.out.println(users.get(0));
        System.out.println();
        System.out.println(verify(hU, hP));

    }

    public static void main(String[] args) {
        userTests();
    }

}
