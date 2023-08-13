package View;

import ActionListener.LoginListener;
import Buisness.SessionManager;
import Model.Prodotti.Articolo;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;
import View.Decorator.GuestMenu;
import View.Decorator.ManagerMenuDecorator;
import View.Decorator.MenuHome;
import View.Decorator.UtenteAcquirenteMenuDecorator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame {

    private final JPanel utenteLoggato = new JPanel();
    private final JPanel centro = new JPanel();
    private final JPanel west = new JPanel();
    private final JPanel nord = new JPanel();
    private final JPanel est= new JPanel();
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
        c.add(est, BorderLayout.EAST);

        mostraLogin();


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


        repaint();
        validate();

    }

    public void aggiornaMenuPulsanti() {
        west.removeAll();

        //..


        if(SessionManager.getSession().get(SessionManager.LOGGED_USER) instanceof UtenteAcquirente) {
            //decoriamo il menu usando il ClienteMenuDecorator
            MenuHome guestMenu = new GuestMenu(this);
            MenuHome clienteMenu = new UtenteAcquirenteMenuDecorator(guestMenu);
            for(JButton btn : clienteMenu.getPulsanti())
                west.add(btn);
        }
        if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER) instanceof Manager) {
            System.out.println("Siamo nel manager");
            //decoriamo il menu usando il MangerMenuDecorator
            MenuHome guestMenu = new GuestMenu(this);
            MenuHome clienteMenu = new ManagerMenuDecorator(guestMenu);
            for(JButton btn : clienteMenu.getPulsanti())
                west.add(btn);
        }
        //else if u instanceof Manager....
        //catena di decorator

        repaint();
        validate();
    }

    public void mostraCatalogo() {
        List<Articolo> articoloList= new ArrayList<>();
        SessionManager.getSession().put(SessionManager.CARRELLO, articoloList);
        centro.removeAll();
        est.removeAll();
        centro.setLayout(new GridLayout(1,1));
        CarrelloPanel carrelloPanel= CarrelloPanel.getInstance();
        est.add(carrelloPanel);
        centro.add(new CatalogoPanel());


        repaint();
        validate();
    }

    public void mostraLogin() {

        nord.removeAll();
        est.removeAll();
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

        nord.removeAll();
        nord.add(new JLabel( messaggio));
        //1. togliere il pannello utente non loggato
        centro.removeAll();

        repaint();
        validate();
    }

    public void mostraPannelloAdmin(String messaggio) {
    }

    public void mostraListe() {
    }
}
