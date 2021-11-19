package Encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

/**
 * When 1 user created then DES_For_Client also created. Client create key DES
 * and encrypt this key by public key RSA of server.
 */
public class DES_For_Client {
    private DESKeySpec _desKeySpec;
    private SecretKeyFactory _keyFactory;
    private Cipher _cipher;
    private SecretKey _secretKey;
    private String _keyDES;

    /**
     * Get string of getKeyDES method to encrypt by RSA and send for server.
     *
     * @return
     */
    public String getKeyDES() {
        return _keyDES;
    }

    public DES_For_Client() {
        GenerateKey();
    }

    private void GenerateKey() {
        try {
            SecureRandom random = new SecureRandom();
            BigInteger num = BigInteger.probablePrime(28, random);
            _keyDES = num.toString();
            _desKeySpec = new DESKeySpec(_keyDES.getBytes());
            _keyFactory = SecretKeyFactory.getInstance("DES");
            _secretKey = _keyFactory.generateSecret(_desKeySpec);
            _cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException ex) {
            Logger.getLogger(DES_For_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String Encrypt(String plaintxt) {
        String encrypted = "";
        try {
            _cipher.init(Cipher.ENCRYPT_MODE, _secretKey);
            byte[] bytes = _cipher.doFinal(plaintxt.getBytes("UTF-8"));
            encrypted = Base64.getEncoder().encodeToString(bytes);
        } catch (InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(DES_For_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encrypted;
    }

    public String Decrypt(String ciphertxt) {
        String decrypted = "";
        try {
            byte[] data = Base64.getDecoder().decode(ciphertxt);            
            _cipher.init(Cipher.DECRYPT_MODE, _secretKey);
            byte[] decryptedBytes = _cipher.doFinal(data);
            decrypted = new String(decryptedBytes, "UTF-8");
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            Logger.getLogger(DES_For_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decrypted;
    }
}
