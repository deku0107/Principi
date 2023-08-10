package View;

import ActionListener.LoginListener;
import Buisness.SessionManager;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;
import View.Decorator.GuestMenu;
import View.Decorator.MenuHome;
import View.Decorator.UtenteAcquirenteMenuDecorator;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    private JPanel utenteLoggato = new JPanel();
    private JPanel centro = new JPanel();
    private JPanel west = new JPanel();
    private JPanel nord = new JPanel();
    JPanel sud = new JPanel();

    private  JTextField username;
    private JPasswordField password;
    private JButton login;


    public Home() {
        super("HomePage");

        this.setSize(1280,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());


        nord.setLayout(new FlowLayout());
        sud.setLayout(new FlowLayout());

        sud.add(new JLabel("Progetto realizzato per l'esame di PIS"));


        c.add(nord, BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);

        mostraLogin();


        utenteLoggato.setLayout(new FlowLayout());

        west.setLayout(new GridLayout(10,1));

       GuestMenu guestMenu = new GuestMenu(this);

        for(JButton btn : guestMenu.getPulsanti()) {
            west.add(btn);
        }

        getContentPane().add(west, BorderLayout.WEST);


        this.setVisible(true);
    }

    public void mostraPannelloUtenteLoggato(String message) {

        nord.removeAll();
        nord.add(new JLabel( message));
        //1. togliere il pannello utente non loggato
        centro.removeAll();

        //2. inserire il pannello utente loggato
        utenteLoggato.removeAll();
        utenteLoggato.add(new JLabel(message));
        add(utenteLoggato, BorderLayout.NORTH);

        repaint();
        validate();

    }

    public void aggiornaMenuPulsanti() {
        west.removeAll();

        //..
        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        if(u instanceof UtenteAcquirente) {
            //decoriamo il menu usando il ClienteMenuDecorator
            MenuHome guestMenu = new GuestMenu(this);
            MenuHome clienteMenu = new UtenteAcquirenteMenuDecorator(guestMenu);
            for(JButton btn : clienteMenu.getPulsanti())
                west.add(btn);
        }
        //else if u instanceof Manager....
        //catena di decorator

        repaint();
        validate();
    }

    public void mostraCatalogo() {
        centro.removeAll();
        centro.setLayout(new GridLayout(1,1));
        centro.add(new CatalogoPanel());
        repaint();
        validate();
    }

    public void mostraLogin() {

        nord.removeAll();
        nord.add(new JLabel("Benvenuto, effettua il login"));
        username = new JTextField(20);
        password = new JPasswordField(20);
        JButton login = new JButton("Login");
        LoginListener loginListener= new LoginListener(this);
        login.addActionListener(loginListener);
        centro.removeAll();
        centro.setLayout(new FlowLayout());
        centro.add(username);
        centro.add(password);
        centro.add(login);
        repaint();
        validate();
    }
    public void mostraRegistrazione() {

        nord.removeAll();
        nord.add(new JLabel("Benvenuto, effettua la registrazione"));
        username = new JTextField(20);
        password = new JPasswordField(20);
        JButton login = new JButton("Login");
        LoginListener loginListener= new LoginListener(this);
        login.addActionListener(loginListener);
        centro.removeAll();
        centro.setLayout(new FlowLayout());
        centro.add(username);
        centro.add(password);
        centro.add(login);
        repaint();
        validate();
    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void mostraPannelloManager(String messaggio) {
    }

    public void mostraPannelloAdmin(String messaggio) {
    }

    public void mostraListe() {
    }
}
