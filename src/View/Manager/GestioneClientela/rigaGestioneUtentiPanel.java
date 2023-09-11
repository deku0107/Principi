package View.Manager.GestioneClientela;

import ActionListener.AmministratoreListener.AmministratoreListener;
import ActionListener.ManagerListener.ManagerListener;
import Buisness.SessionManager;
import Buisness.Utente.UtenteBusiness;


import javax.swing.*;
import java.awt.*;

public class rigaGestioneUtentiPanel extends JPanel {

    private JLabel nome;
    private JLabel namelabel;
    private JLabel username;
    private JLabel usernameLabel;
    private JLabel stato;
    private JComboBox<String> comboBox;
    private JButton submit;
    private Object utente;

    public rigaGestioneUtentiPanel(){

        comboBox= new JComboBox<>();
        comboBox.addItem("Attivo");
        comboBox.addItem("Bloccato");
        comboBox.addItem("Eliminato");

        nome= new JLabel("Nome:");
        username= new JLabel("Username");
        stato= new JLabel("Stato");
        comboBox.setSelectedIndex(0); // prendo dalla buisness
        namelabel=new JLabel("Nome + cognome");//prendo dalla buisness
        usernameLabel= new JLabel("username"); // prendo dalla buisness
        submit= new JButton();

        //add action listener


        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();

        gbc.insets= new Insets(20,20,20,20);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.fill=GridBagConstraints.BOTH;
        add(nome, gbc);

        gbc.gridy=1;
        add(namelabel, gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        add(username, gbc);

        gbc.gridy=1;
        add(usernameLabel, gbc);


        gbc.gridx=2;
        gbc.gridy=0;
        add(stato, gbc);

        gbc.gridy=1;
        add(comboBox, gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        add(submit, gbc);



    }

    public void updateData(Object utente){
        this.utente=utente;
        UtenteBusiness u= UtenteBusiness.getInstance();

        namelabel.setText(u.getUsernameUtente(utente) +" "+ u.getCognomeUtente(utente));
        usernameLabel.setText(u.getUsernameUtente(utente));

        if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null) {
            if (u.getStato(utente).equalsIgnoreCase("attivo")) {
                comboBox.setSelectedIndex(0);
            } else if (u.getStato(utente).equalsIgnoreCase("bloccato")) {
                comboBox.setSelectedIndex(1);
            } else if (u.getStato(utente).equalsIgnoreCase("eliminato")) {
                comboBox.setSelectedIndex(2);
            }

            submit.setText("Conferma");
            submit.setActionCommand("modifica_stato");
            submit.addActionListener(new ManagerListener(this, null));
        }
        if(SessionManager.getSession().get(SessionManager.LOGGED_ADMIN)!=null){
            remove(comboBox);
            remove(stato);
            submit.setText("Modifica");
            submit.setActionCommand("modifica_manager");
            submit.addActionListener(new AmministratoreListener(this));
        }

    }


    public JLabel getNamelabel() {
        return namelabel;
    }

    public void setNamelabel(JLabel namelabel) {
        this.namelabel = namelabel;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

    public Object getUtente() {
        return utente;
    }

    public void setUtente(Object utente) {
        this.utente = utente;
    }
}
