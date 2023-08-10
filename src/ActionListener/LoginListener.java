package ActionListener;

import Buisness.SessionManager;
import Buisness.Utente.LoginResult;
import Buisness.Utente.UtenteBusiness;
import View.Home;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    private Home frame;

    public LoginListener(Home frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
