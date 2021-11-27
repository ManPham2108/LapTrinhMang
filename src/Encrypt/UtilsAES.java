package Encrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class UtilsAES {

    public static String generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //AES : 128, 192, 256 bits
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            return SecretKey2String(secretKey);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilsAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String EncryptText(String keyAES, String plainText) {
        String cipherText = "";
        try {
            SecretKey secretKey = String2SecretKey(keyAES);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            cipherText = Base64.getEncoder().encodeToString(cipherBytes);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(UtilsAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cipherText;
    }

    public static String DecryptText(String keyAES, String cipherText) {
        String plaintText = "";
        try {
            SecretKey secretKey = String2SecretKey(keyAES);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] plainBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            plaintText = new String(plainBytes, "UTF-8");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException ex) {
            Logger.getLogger(UtilsAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plaintText;
    }

    public static String EncryptFile(String keyAES, File file) {
        String cipherTextFile = "";
        try {
            //Convert file to bytes
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            String strFile = Base64.getEncoder().encodeToString(fileBytes);
            cipherTextFile = EncryptText(keyAES, strFile);
        } catch (IOException ex) {
            Logger.getLogger(UtilsAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cipherTextFile;
    }

    public static byte[] DecryptFile(String keyAES, String cipherTextFile) {
        String decryptFile = DecryptText(keyAES, cipherTextFile);
        return Base64.getDecoder().decode(decryptFile);
    }

    public static void ConvertBytesToFile(byte[] bytes, String nameFile, String extFile){
        OutputStream os = null;
        try {
            File file = new File("src/Encrypt/"+nameFile+"."+extFile);
            os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UtilsAES.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilsAES.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String SecretKey2String(SecretKey secretKeyAES) {
        return Base64.getEncoder().encodeToString(secretKeyAES.getEncoded());
    }

    private static SecretKey String2SecretKey(String strKeyAES) {
        byte[] decodedKey = Base64.getDecoder().decode(strKeyAES);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

}
