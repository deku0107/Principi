package ActionListener;

import Buisness.SessionManager;
import View.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutListener implements ActionListener {

    private Home frame;

    public LogOutListener(Home frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SessionManager.getSession().clear();
        frame.aggiornaMenuPulsanti();
    }


}
