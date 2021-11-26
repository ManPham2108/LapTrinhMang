package Encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES_For_Server {

    public  String Encrypt(String keyDES, String plaintxt){
        String encrypted = "";
        try {
            DESKeySpec desKeySpec = new DESKeySpec(keyDES.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(plaintxt.getBytes("UTF-8"));
            encrypted = Base64.getEncoder().encodeToString(bytes);
        } catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DES_For_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encrypted;
    }
    
    public  String Decrypt(String keyDES, String ciphertxt){
        String decrypted = "";
        try {
            byte[] data = Base64.getDecoder().decode(ciphertxt);
            DESKeySpec desKeySpec = new DESKeySpec(keyDES.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(data);
            decrypted = new String(decryptedBytes, "UTF-8");
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(DES_For_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decrypted;
    }
}
