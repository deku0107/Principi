package ViewProveETest;

import ActionListener.LoginListener;
import Buisness.SessionManager;
import Model.Prodotti.Articolo;
import Model.Utenti.Amministratore;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;
import ViewProveETest.Decorator.*;
import ViewProveETest.Prove.UtenteAcquirente.RegistrazioneUtenteAcquirentePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame {


    private static final Home h = new Home();
    public static Home getInstance(){return h;}

    private final JPanel centro;
    private final JPanel west ;
    private final JPanel nord;
    private final JPanel est;
    private final JPanel sud;

    private  JTextField username;
    private JPasswordField password;
    private JButton back;



    public Home() {
        super("HomePage");

        back= new JButton("Indietro");
        back.setActionCommand("indietro");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getActionCommand().equalsIgnoreCase("indietro"))
                aggiornaMenuPulsanti();

            }
        });

        this.setSize(1280,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        nord=new JPanel();

        sud= new JPanel(new FlowLayout());

        sud.add(new JLabel("Progetto realizzato per l'esame di PIS"));

        centro=new JPanel();

        est= new JPanel();

        west= new JPanel();

        c.add(nord, BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(sud, BorderLayout.SOUTH);
        c.add(west, BorderLayout.WEST);
        c.add(est, BorderLayout.EAST);

        aggiornaMenuPulsanti();

        this.setVisible(true);
    }

    public void mostraPannelloUtenteLoggato(String message) {

        Utente u = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        UtenteAcquirente utenteAcquirente= (UtenteAcquirente) u;

        nord.removeAll();
        nord.add(new JLabel( message + " punto vendita " + ((UtenteAcquirente) u).getPuntoVendita().getId()));
        //1. togliere il pannello utente non loggato
        centro.removeAll();


        repaint();
        validate();

    }

    public void aggiornaMenuPulsanti() {
        centro.removeAll();
        centro.setLayout(new GridLayout(2,2,10,10));
        west.removeAll();
        nord.removeAll();
        est.removeAll();
        nord.setLayout(new FlowLayout((FlowLayout.CENTER ), 20, 10));
        nord.add(new JLabel("Benvenuto"));


        if(SessionManager.getSession().get(SessionManager.LOGGED_USER) instanceof UtenteAcquirente) {
            //decoriamo il menu usando il ClienteMenuDecorator
            MenuHome guestMenu = new GuestMenu(this);
            MenuHome clienteMenu = new UtenteAcquirenteMenuDecorator(guestMenu);
            for(JButton btn : clienteMenu.getPulsanti())
                centro.add(btn);
        }else
        if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER) instanceof Manager) {
            System.out.println("Siamo nel manager");
            //decoriamo il menu usando il MangerMenuDecorator
            MenuHome guestMenu = new GuestMenu(this);
            MenuHome mangerMenu = new ManagerMenuDecorator(guestMenu);
            for(JButton btn : mangerMenu.getPulsanti())
                centro.add(btn);
        }else
        if(SessionManager.getSession().get(SessionManager.LOGGED_ADMIN) instanceof Amministratore){
            System.out.println("Siamo amministratori");
            MenuHome guestMenu= new GuestMenu(this);
            MenuHome amministratoreMenu= new AmministratoreMenuDecorator(guestMenu);
            for(JButton btn : amministratoreMenu.getPulsanti())
                centro.add(btn);
        }else{
            System.out.println("Siamo Utente Guest");
            MenuHome guestMenu= new GuestMenu(this);
            for (JButton btn: guestMenu.getPulsanti())
                centro.add(btn);
        }



        repaint();
        validate();
    }

    public void mostraCatalogo() {
        nord.removeAll();
        nord.setLayout(new FlowLayout());
        nord.add(back);
        List<Articolo> articoloList= new ArrayList<>();
        SessionManager.getSession().put(SessionManager.CARRELLO, articoloList);
        centro.removeAll();
        est.removeAll();
        est.setLayout(new GridLayout(1,1));
        centro.setLayout(new GridLayout(1,1));
        new CatalogoPanel();


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
        login.setActionCommand("login");
        JButton resetPSW= new JButton("Password dimenticata?");
        resetPSW.setActionCommand("reset_password");
        JButton back= new JButton("Indietro");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaMenuPulsanti();
            }
        });

        LoginListener loginListener= new LoginListener(null);
        login.addActionListener(loginListener);
        resetPSW.addActionListener(loginListener);
        centro.removeAll();
        centro.setLayout(new FlowLayout());
        centro.add(username);
        centro.add(password);
        centro.add(login);
        centro.add(resetPSW);
        centro.add(back);
        repaint();
        validate();
    }
    public void mostraRegistrazione() {

        nord.removeAll();
        nord.add(new JLabel("Benvenuto, effettua la registrazione"));
        nord.add(back);
       /* username = new JTextField(20);
        password = new JPasswordField(20);
        JButton login = new JButton("Login");
        LoginListener loginListener= new LoginListener(this);
        login.addActionListener(loginListener);
        centro.removeAll();
        centro.setLayout(new FlowLayout());
        centro.add(username);
        centro.add(password);
        centro.add(login);
        centro.add(back);*/

        centro.removeAll();
        centro.setLayout(new FlowLayout());
        centro.add(new RegistrazioneUtenteAcquirentePanel());
        repaint();
        validate();
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


    public JPanel getCentro() {
        return centro;
    }

    public JPanel getWest() {
        return west;
    }

    public JPanel getNord() {
        return nord;
    }

    public JPanel getEst() {
        return est;
    }

    public JPanel getSud() {
        return sud;
    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JButton getBack() {
        return back;
    }
}
