import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash {

    //Hand made hashing function made by yours truly
    public static int leHash(String key){
        int hash = 9;
        for(int i = 0; i < key.length(); i++){
            hash *= (key.charAt(i) * 17);
        }
        hash += key.charAt(key.length()/2);
        hash *= 4 * hash * hash / 14 * (hash/2);

        if(hash < 0){
            hash *= -1;
        }

        return hash;
    } 


    //Much better hashing function created by ChatGPT
    public static int leHasher(String key){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(key.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            int hash = no.intValue();
            if(hash < 0) return hash * -1;
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}