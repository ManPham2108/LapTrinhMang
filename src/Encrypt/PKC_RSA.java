package Encrypt;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * Use to encrypt and decrypt key of text
 */
public class PKC_RSA {
    private final BigInteger NUMBER_ONE = new BigInteger("1");
    private final SecureRandom random = new SecureRandom();
    private final int BIT_LENGTH = 500;
    private BigInteger privatekey;
    private BigInteger publickey;
    private BigInteger n;
    
    public PKC_RSA(){
        RSA_Key_Generator();
    }
    
    /**
     * RSA Encryption key of another
     * @param Key_need_encrypt  need encrypt
     * @param publicKeyAnother  Key of another
     * @param n_Another  of another
     * @return Key_encrypted
     */
    public static BigInteger RSA_Encryption(BigInteger Key_need_encrypt, BigInteger publicKeyAnother, BigInteger n_Another)
    {
        BigInteger Key_encypted = Key_need_encrypt.modPow(publicKeyAnother, n_Another);
        return Key_encypted;
    }

    /**
     * RSA Decryption of itself
     * @param Key_need_decrypt
     * @return Key_original
     */
    private BigInteger RSA_Decryption(BigInteger Key_need_decrypt)
    {
        BigInteger Key_original = Key_need_decrypt.modPow(privatekey, n);
        return Key_original;
    }

    /**
     * RSA Public - Private Key Generation
     * @param number 
     */
    private void RSA_Key_Generator()
    {
        BigInteger p = BigInteger.probablePrime(BIT_LENGTH, random);
        BigInteger q = BigInteger.probablePrime(BIT_LENGTH, random);

        // n = pq
        n = p.multiply(q);
        
        //phi = (p-1)(q-1)
        BigInteger phi = (p.subtract(NUMBER_ONE)).multiply(q.subtract(NUMBER_ONE));
        
        // pubic key (n, e)
        // default public key: e = 65537
        publickey = new BigInteger("65537");
        
        // private key (n, d)
        privatekey = publickey.modInverse(phi);
    }
}
