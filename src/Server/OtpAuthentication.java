
package Server;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OtpAuthentication {
    public char[] randomOtp(){
        String numbers = "0123456789";
        Random rd = new Random();
        char[] otp = new char[4];
        for (int i = 0; i < 4; i++)
        {
            otp[i] = numbers.charAt(rd.nextInt(numbers.length()));
        }
        return otp;
    }
    public void sendMail(String email,String otp) throws AddressException, MessagingException{
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;
        // Step1: setup Mail Server
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); //Thay abc bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
        mailMessage.setSubject("MÃ OTP XÁC THỰC TÀI KHOẢN ĐĂNG KÍ");
        String emailBody = "<p>Mã Otp kích hoạt tài khoản là</p>"
                         + "<p>"+otp+"</p>";
        mailMessage.setContent(emailBody,"text/html; charset=UTF-8");
        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");
        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
        transport.connect("smtp.gmail.com", "bvemaybay@gmail.com", "vemaybay123"); 
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
        System.out.println("xog");
    }
    public static void main(String[] args) throws MessagingException {
        OtpAuthentication otp = new OtpAuthentication();            
        //System.out.println(otp.randomOtp());  
        otp.sendMail("man21082015@gmail.com", String.valueOf(otp.randomOtp()));
    }
}
