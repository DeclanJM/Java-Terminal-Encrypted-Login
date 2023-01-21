import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
    public static void createUser(int username, int password) throws IOException{  
        PrintWriter pWriter = login.getWriter();
        
        if(!user.verify(username, password)){
            pWriter.println(username + " " + password);
            pWriter.close();
            return;
        }
        else{
            System.out.println("\nError:  Unable to create account\n\tUsername/Password already exist\n");
            return;
        }
    }

    public static void existingUser(int username, int password){

    }

    public static void userPrompt(){
        System.out.println("");
    }

    public static void splashScreen(){
        
    }

    //Tests for the login.java Class
    public static void loginTests(){
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