package Encrypt;

public class Test1 {

    public static void main(String[] args) {  
        String original = "Xin chào một hai ba";
        //Tạo key RSA
        UtilsRSA.GenerateKeys();
        //Tạo key AES
        String keyAES = UtilsAES.generateKey();
        System.out.println("Key AES: "+keyAES);
        
        //-------------------------------------//
        
        String enKeyAES = UtilsRSA.EncryptText(keyAES);
        System.out.println("Mã hóa key AES: "+ enKeyAES);
        
        String decryptKeyAES = UtilsRSA.DecryptText(enKeyAES);
        System.out.println("Giải mã key AES: "+ decryptKeyAES);
        
        //-------------------------------------//
        
        String enDataAES = UtilsAES.EncryptText(keyAES, original);
        System.out.println("Mã hóa data text AES: "+enDataAES);
        
        String deDataAES = UtilsAES.DecryptText(decryptKeyAES, enDataAES);
        System.out.println("Giải mã data text AES: "+deDataAES);
        
    }
    
}
