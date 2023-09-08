package Buisness.Bridge.Mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailAPI implements IMailAPI{

    private static final String FROM = "myshop2023pis@gmail.com";
    private static String PASSWORD;

    @Override
    public void invioEmail(String to, String sub, String msg, String path) {
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            if (path!=null) {

                // Creare il corpo del messaggio
                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(msg);

                // Creare un oggetto Multipart per il messaggio
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                MimeBodyPart attachmentPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(path);
                attachmentPart.setDataHandler(new DataHandler(fileDataSource));
                attachmentPart.setFileName(path);
                multipart.addBodyPart(attachmentPart);

                // Impostare il contenuto del messaggio come Multipart
                message.setContent(multipart);
            }else {
                message.setText(msg);
            }


            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("message not sent");
        }

    }
    }

