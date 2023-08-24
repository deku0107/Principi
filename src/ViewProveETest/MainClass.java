package ViewProveETest;

import Buisness.SessionManager;
import Model.Utenti.Utente;

import javax.swing.*;
import java.awt.*;

public class MainClass {
    private static Home home;
    public static void main(String[] args) {

        SessionManager.getSession().put(SessionManager.GUEST, new Utente());

        JFrame finestra = new JFrame("Prima finestra");

        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Container c = finestra.getContentPane();
        home= Home.getInstance();
        c.add(home);

        finestra.setVisible(true);


    }

    public Home getHome() {
        return home;
    }
}
