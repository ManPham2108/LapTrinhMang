package Encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class HashPassword {
    public String generateHash(String password){
        String hash = "";
        try {            
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(password.getBytes());
            byte[] digest = md.digest();
            hash = DatatypeConverter.printHexBinary(digest).toLowerCase();            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    
    public boolean checkPassword(String passUser, String passInput){
        return generateHash(passInput).equals(passUser);
    }
}
