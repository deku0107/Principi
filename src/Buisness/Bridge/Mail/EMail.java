package Buisness.Bridge.Mail;

public abstract class EMail {

    protected MailAPI mailAPI;

    public EMail (MailAPI mailAPI){
        this.mailAPI= mailAPI;

    }

    public abstract void invia(String to, String msg, String sub, String path);
}
