package Encrypt;

import java.io.File;

public class Test2 {

    public static void main(String[] args) {
        //File của client đưa vào
        File file = new File("src/Encrypt/one.jpg");
        
        String key = UtilsAES.generateKey();
        
        String cipher = UtilsAES.EncryptFile(key, file);
        
        byte[] en = UtilsAES.DecryptFile(key, cipher);
        
        //Trả về file như ban đầu
        //Vị trí lưu file src/Encrypt/"+nameFile+"."+extFile;
        UtilsAES.ConvertBytesToFile(en, "hello", "jpg");
    }

}
