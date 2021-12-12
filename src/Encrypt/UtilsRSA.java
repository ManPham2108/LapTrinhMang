package Encrypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
public class UtilsRSA {

    private static final String PUBLIC_KEY_FILE = "src/Encrypt/publickey.txt";
    private static final String PRIVATE_KEY_FILE = "src/Encrypt/privatekey.txt";

    //Server tạo cặp key 
    public static  PublicKey key;
    public static void GenerateKeys() {
        try {
            //Tạo cặp key
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair pair = generator.generateKeyPair();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            //Lưu cặp key vào file
            File f_public = new File(PUBLIC_KEY_FILE);
            FileOutputStream fos_public = new FileOutputStream(f_public, false);
            fos_public.write(publicKey.getEncoded());
            fos_public.flush();
            fos_public.close();
            File f_private = new File(PRIVATE_KEY_FILE);
            FileOutputStream fos_private = new FileOutputStream(f_private, false);
            fos_private.write(privateKey.getEncoded());
            fos_private.flush();
            fos_private.close();

        } catch (NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(UtilsRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String loadPub() throws IOException{
        File publicKeyFile = new File(PUBLIC_KEY_FILE);
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
        String publicKeyString = Base64.getEncoder().encodeToString(publicKeyBytes);
        return publicKeyString;
    }
    // Client mã hóa
    public static String EncryptText(String plainText,String key) {
        String cipherText = "";
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] cipherBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            cipherText= Base64.getEncoder().encodeToString(cipherBytes);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(UtilsRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cipherText;
    }

    // Server để lấy private key từ file và giải mã
    public static String DecryptText(String cipherText) {
        String plainText = "";
        try {
            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] plainBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
            plainText = new String(plainBytes, "UTF-8");

        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(UtilsRSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plainText;
    }
}
