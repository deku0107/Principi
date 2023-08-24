package ActionListener;

import Buisness.Bridge.MailHelper;
import Buisness.SessionManager;
import Buisness.Utente.LoginResult;
import Buisness.Utente.UtenteBusiness;
import Buisness.UtilityBuisness;
import ViewProveETest.Home;
import ViewProveETest.Prove.UtenteAcquirente.ResetPasswordPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginListener implements ActionListener {

    private Home frame;
    private JPanel panel;

    public LoginListener(JPanel panel) {
        this.frame = Home.getInstance();
        this.panel=panel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getActionCommand().equalsIgnoreCase("login"))
            login();
        if(e.getActionCommand().equalsIgnoreCase("reset_password"))
            resetPSW();
        if(e.getActionCommand().equalsIgnoreCase("invio_email_reset_psw"))
            invioCodice();
        if(e.getActionCommand().equalsIgnoreCase("conferma_codice"))
            confermaCodice();
        if(e.getActionCommand().equalsIgnoreCase("reset_psw"))
            reset();
        if(e.getActionCommand().equalsIgnoreCase("annulla"))
            annulla();



    }

    private void annulla() {
        ResetPasswordPanel p= (ResetPasswordPanel) panel;
        p.annulla();
    }

    private void reset() {
        ResetPasswordPanel p= (ResetPasswordPanel) panel;

        if (p.getPassword().getPassword().length==0){
            JOptionPane.showMessageDialog(null, "Inserire una password valida");

        }else{

            int i= UtenteBusiness.getInstance().resetPassword(p.getEmail().getText(),new String(p.getPassword().getPassword()));
            if(i>0){
                JOptionPane.showMessageDialog(null, "Password aggiornata");
                frame.aggiornaMenuPulsanti();
            }else{
                JOptionPane.showMessageDialog(null, "Password non aggiornata");
            }

        }

    }

    private void confermaCodice() {
        ResetPasswordPanel p= (ResetPasswordPanel) panel;
        if (p.getRandomString().trim().equals(p.getCodice().getText().trim())){
            JOptionPane.showMessageDialog(null, "Codice corretto");
            p.visualizzaRigaPassword();
        }else{
            JOptionPane.showMessageDialog(null, "Codice errato");
        }

    }

    private void invioCodice() {

        ResetPasswordPanel p= (ResetPasswordPanel) panel;
        String email= p.getEmail().getText();
        boolean check= UtilityBuisness.getInstance().checkFormattedEmail(email);
        if (check){
            JOptionPane.showMessageDialog(null, "Formato email non valido");
        }else{
            check=UtilityBuisness.getInstance().checkEmail(email);
            if (!check){
                JOptionPane.showMessageDialog(null, "Email non presente nel DataBase");
            }else{
                MailHelper mailHelper= MailHelper.getInstance();
                mailHelper.send(email, "Ripristino password", p.getRandomString());
                JOptionPane.showMessageDialog(null, "Inserisci il codice mandato per email per ripristinare la password");
                p.visualizzaRigaCodice();
            }
        }

    }

    private void resetPSW() {
        frame.getCentro().removeAll();
        frame.getNord().removeAll();
        frame.getNord().add(frame.getBack());

        frame.getCentro().add(new ResetPasswordPanel());

        frame.repaint();
        frame.validate();
    }

    private void login() {

        String user=frame.getUsername().getText();
        String pwd=new String(frame.getPassword().getPassword());

        LoginResult result= UtenteBusiness.getInstance().login(user, pwd, UtenteBusiness.getInstance().tipoUtente(user));
        if(result.getResult() == LoginResult.Result.LOGIN_OK) {
            if(SessionManager.getSession().get(SessionManager.LOGGED_USER)!=null){
                frame.mostraPannelloUtenteLoggato(result.getMessaggio());
                //refresh view dei pulsanti
                frame.aggiornaMenuPulsanti();
            }else if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null){
                frame.mostraPannelloManager(result.getMessaggio());
                //refresh view dei pulsanti
                frame.aggiornaMenuPulsanti();
            }else if(SessionManager.getSession().get(SessionManager.LOGGED_ADMIN)!=null){
                frame.mostraPannelloAdmin(result.getMessaggio());
                //refresh view dei pulsanti
                frame.aggiornaMenuPulsanti();
            }
        }
        else
            JOptionPane.showMessageDialog(null, result.getMessaggio());
    }
}
