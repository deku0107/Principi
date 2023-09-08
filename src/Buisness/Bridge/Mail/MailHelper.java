package Buisness.Bridge.Mail;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
public class MailHelper {

    private static MailHelper instance;

    private static final String FROM = "myshop2023pis@gmail.com";
    private static String PASSWORD;

    public static synchronized MailHelper getInstance() {
        if(instance == null)
            instance = new MailHelper();
        return instance;
    }

    /*public static void main(String[] args) {


        getInstance().send("fragrassi825@gmail.com", "oggetto", "msg di test");
    }*/

    public void send(String to,String sub,String msg){

        String osName = System.getProperty("os.name");
        if(osName.equalsIgnoreCase("Windows 11")||osName.equalsIgnoreCase("Windows 10")||osName.equalsIgnoreCase("Windows")){
            PASSWORD="jpmnsahvpofiadaj";
        }else if (osName.equalsIgnoreCase("Mac OS X")){
            PASSWORD="wgkyetaesgnpijad";
        }

        System.out.println(osName);
        System.out.println(PASSWORD);
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", FROM);
        props.put("mail.smtp.password", PASSWORD);
        //get Session
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM,PASSWORD);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("message not sent");
        }

    }

}
