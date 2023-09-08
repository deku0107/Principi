package Buisness.Bridge.Mail;

public class InvioEmail extends EMail{


    public InvioEmail( MailAPI mailAPI){
        super(mailAPI);

    }


    @Override
    public void invia(String to, String msg, String sub, String path) {
        mailAPI.invioEmail(to, msg, sub, path);
    }
}
