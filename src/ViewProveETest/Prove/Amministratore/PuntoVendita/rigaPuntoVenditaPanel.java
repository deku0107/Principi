package ViewProveETest.Prove.Amministratore.PuntoVendita;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.PuntoVenditaBuisness;
import Buisness.Utente.UtenteBusiness;

import javax.swing.*;
import java.awt.*;

public class rigaPuntoVenditaPanel extends JPanel {

    private JLabel nome;
    private JTextField nomeField;
    private JLabel email;
    private JTextField emailField;
    private JLabel telefono;
    private JTextField telefonoField;
    private JLabel indirizzo;
    private JTextField indirizzoField;
    private JLabel citta;
    private JTextField cittaField;
    private JLabel manager;
    private JLabel managerLabel;
    private JLabel managerLabel1;
    private JComboBox managerBox;
    private JLabel cambiaManager;
    private JButton conferma;
    private JButton offerta;
    private Object puntoVendita;

    private final int COLUMNS=7;

    public rigaPuntoVenditaPanel(){


        setLayout(new GridBagLayout());


        nome= new JLabel("Nome");
        nomeField=new JTextField(COLUMNS);
        email= new JLabel("Email");
        emailField= new JTextField(COLUMNS);
        telefono= new JLabel("Telefono");
        telefonoField= new JTextField(COLUMNS);
        indirizzo= new JLabel("Indirizzo");
        indirizzoField= new JTextField(COLUMNS);
        citta= new JLabel("Città");
        cittaField= new JTextField(COLUMNS);
        manager= new JLabel("Manager");
        managerLabel= new JLabel("Manager attuale");
        managerLabel1= new JLabel();
        cambiaManager= new JLabel("Cambia manager");
        managerBox= UtenteBusiness.getInstance().getManagerBox();
        conferma= new JButton("Modifica");
        conferma.setActionCommand("inserimento_punto_vendita");
        conferma.addActionListener(new AmministratoreListener(this));
        offerta=new JButton("Offerta");
        offerta.setActionCommand("visualizza_offerta");
        offerta.setVisible(false);
        GridBagConstraints constraints = new GridBagConstraints();


        constraints.insets= new Insets(20,10,20,10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        add(nome, constraints);

        constraints.gridy = 1;
        add(nomeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(email, constraints);

        constraints.gridy = 1;
        add(emailField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(telefono, constraints);

        constraints.gridy = 1;
        add(telefonoField, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        add(indirizzo, constraints);

        constraints.gridy = 1;
        add(indirizzoField, constraints);

        constraints.gridx = 4;
        constraints.gridy = 0;
        add(citta, constraints);

        constraints.gridy = 1;
        add(cittaField, constraints);

        constraints.gridx = 5;
        constraints.gridy = 0;
        add(managerLabel, constraints);

        constraints.gridy = 1;
        add(managerLabel1, constraints);

        constraints.gridx = 6;
        constraints.gridy = 0;
        add(cambiaManager, constraints);

        constraints.gridy=1;
        add(managerBox, constraints);

        constraints.gridx=7;
        constraints.gridy=0;
        add(offerta, constraints);

        constraints.gridy=1;
        add(conferma, constraints);
    }


    public void updateData(Object puntoVendita){
        this.puntoVendita=puntoVendita;

        PuntoVenditaBuisness puntoVenditaBuisness= PuntoVenditaBuisness.getInstance();

        nomeField.setText(puntoVenditaBuisness.getNome(puntoVendita));
        emailField.setText(puntoVenditaBuisness.getEmail(puntoVendita));
        telefonoField.setText(puntoVenditaBuisness.getTelefono(puntoVendita));
        indirizzoField.setText(puntoVenditaBuisness.getIndirizzo(puntoVendita));
        cittaField.setText(puntoVenditaBuisness.getCitta(puntoVendita));
        managerLabel1.setText( puntoVenditaBuisness.getManagerName(puntoVendita) + " (id: " +puntoVenditaBuisness.getManagerId(puntoVendita)+")");
        conferma.setActionCommand("modifica_punto_vendita");
        offerta.setVisible(true);
        offerta.addActionListener(new AmministratoreListener(this));


    }

    public JLabel getNome() {
        return nome;
    }

    public void setNome(JLabel nome) {
        this.nome = nome;
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public void setNomeField(JTextField nomeField) {
        this.nomeField = nomeField;
    }

    public JLabel getEmail() {
        return email;
    }

    public void setEmail(JLabel email) {
        this.email = email;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public JLabel getTelefono() {
        return telefono;
    }

    public void setTelefono(JLabel telefono) {
        this.telefono = telefono;
    }

    public JTextField getTelefonoField() {
        return telefonoField;
    }

    public void setTelefonoField(JTextField telefonoField) {
        this.telefonoField = telefonoField;
    }

    public JLabel getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(JLabel indirizzo) {
        this.indirizzo = indirizzo;
    }

    public JTextField getIndirizzoField() {
        return indirizzoField;
    }

    public void setIndirizzoField(JTextField indirizzoField) {
        this.indirizzoField = indirizzoField;
    }

    public JLabel getCitta() {
        return citta;
    }

    public void setCitta(JLabel citta) {
        this.citta = citta;
    }

    public JTextField getCittaField() {
        return cittaField;
    }

    public void setCittaField(JTextField cittaField) {
        this.cittaField = cittaField;
    }

    public JLabel getManager() {
        return manager;
    }

    public void setManager(JLabel manager) {
        this.manager = manager;
    }

    public JComboBox getManagerBox() {
        return managerBox;
    }

    public void setManagerBox(JComboBox managerBox) {
        this.managerBox = managerBox;
    }

    public Object getPuntoVendita() {
        return puntoVendita;
    }
}
