package Encrypt;

import java.math.BigInteger;

/**
 * Default public key for server and client
 * Use to encrypt and decrypt key of text
 */
public class PKC_RSA {
    private final BigInteger NUMBER_ONE = new BigInteger("1");
    private final BigInteger p = new BigInteger("2310010084797206060970182958503017542754083072911744685007200562505870553287844787627036012084595177444067962019045355456247877265294016086431972206411");
    private final BigInteger q = new BigInteger("2406568958684850561886689852694393864213007223714844337463681934839133837196941210132601142693213653765331388465007706796240995966643434903606937610557");
    private final BigInteger n = new BigInteger("5559198564321915535997584315413393092493348347255843419771222580227273559376281043338357980467043907042637716818326704144978286703037762308482220625386384388624680062949875597814032263102045209295610051823779969687947948509258575370491964212854716485165414726530861795479919698326099361821127536680927");
    private final BigInteger publickey = new BigInteger("65537");    
    private BigInteger privatekey;
    
    public PKC_RSA(){
        RSA_Key_Generator();
    }
    
    /**
     * RSA Encryption
     * @param key
     * @return Key_encrypted
     */
    public String RSA_Encryption(String key)
    {
        BigInteger num_key = new BigInteger(key);
        BigInteger Key_encypted = num_key.modPow(publickey, n);
        return Key_encypted.toString();
    }

    /**
     * RSA Decryption
     * @param key
     * @return Key_original
     */
    public String RSA_Decryption(String key)
    {
        BigInteger num_key = new BigInteger(key);
        BigInteger Key_original = num_key.modPow(privatekey, n);
        return Key_original.toString();
    }

    /**
     * RSA Public - Private Key Generation
     * @param number 
     */
    private void RSA_Key_Generator()
    {
        //phi = (p-1)(q-1)
        BigInteger phi = (p.subtract(NUMBER_ONE)).multiply(q.subtract(NUMBER_ONE));
        // private key (n, d)
        privatekey = publickey.modInverse(phi);
    }
}
