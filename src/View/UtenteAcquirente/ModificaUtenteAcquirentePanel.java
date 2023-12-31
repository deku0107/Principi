package View.UtenteAcquirente;

import ActionListener.UtenteAcquirenteListener.UtenteAcquirenteListener;
import Buisness.PuntoVenditaBuisness;
import Buisness.Utente.UtenteBusiness;

import javax.swing.*;
import java.awt.*;

public class ModificaUtenteAcquirentePanel extends JPanel {

    private JLabel nameLabel;
    private JTextField nome;
    private JLabel surnameLabel;
    private JTextField cognome;
    private JLabel pointOfSaleLabel;
    private JComboBox puntoVendita;
    private JLabel dateOfBirthLabel;
    private JTextField giornoDataNascita;
    private JTextField meseDataNascita;
    private JTextField annoDataNascita;
    private JLabel usernameLabel;
    private JTextField username;
    private JLabel emailLabel;
    private JTextField email;
    private JLabel phoneLabel;
    private JTextField telefono;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel addressLabel;
    private JTextField indirizzo;
    private JLabel cityLabel;
    private JTextField citta;
    private JButton submitButton;
    private String dataDiNascita;
    private UtenteBusiness utenteBusiness;
    private String idUtente;


    public ModificaUtenteAcquirentePanel(String id){

        idUtente=id;
        utenteBusiness= UtenteBusiness.getInstance();


        nameLabel = new JLabel("Nome:");
        nome = new JTextField(20);
        nome.setText(utenteBusiness.getUtenteAcquirente(id).getNome());
        surnameLabel = new JLabel("Cognome:");
        cognome = new JTextField(20);
        cognome.setText(utenteBusiness.getUtenteAcquirente(id).getCognome());
        pointOfSaleLabel = new JLabel("Punto vendita:");
        puntoVendita = PuntoVenditaBuisness.getInstance().getPuntoVenditaBox();
        dateOfBirthLabel = new JLabel("Data di nascita (dd/MM/yyyy):");
        giornoDataNascita= new JTextField(6);
        giornoDataNascita.setText(String.valueOf(utenteBusiness.getUtenteAcquirente(id).getDataDiNascita().getGiorno()));
        meseDataNascita=new JTextField(6);
        meseDataNascita.setText(String.valueOf(utenteBusiness.getUtenteAcquirente(id).getDataDiNascita().getMese()));
        annoDataNascita= new JTextField(6);
        annoDataNascita.setText(String.valueOf(utenteBusiness.getUtenteAcquirente(id).getDataDiNascita().getAnno()));
        usernameLabel = new JLabel("Username:");
        username = new JTextField(20);
        username.setText(utenteBusiness.getUtenteAcquirente(id).getUsername());
        emailLabel = new JLabel("Email:");
        email = new JTextField(20);
        email.setText(utenteBusiness.getUtenteAcquirente(id).getEmail());
        phoneLabel = new JLabel("Telefono:");
        telefono = new JTextField(20);
        telefono.setText(utenteBusiness.getUtenteAcquirente(id).getTelefono());
        passwordLabel = new JLabel("Password:");
        password = new JPasswordField(20);
        addressLabel = new JLabel("Indirizzo:");
        indirizzo = new JTextField(20);
        indirizzo.setText(utenteBusiness.getUtenteAcquirente(id).getIndirizzo());
        cityLabel = new JLabel("Citta:");
        citta = new JTextField(20);
        citta.setText(utenteBusiness.getUtenteAcquirente(id).getCitta());
        submitButton = new JButton("Modifica");

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.EAST;
        add(nameLabel, constraints);

        constraints.gridx = 1;
        add(nome, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.EAST;
        add(surnameLabel, constraints);

        constraints.gridx = 1;
        add(cognome, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.EAST;
        add(pointOfSaleLabel, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        add(puntoVendita, constraints);


        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.EAST;
        add(dateOfBirthLabel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(giornoDataNascita, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        add(meseDataNascita, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        add(annoDataNascita, constraints);


        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.EAST;
        add(usernameLabel, constraints);

        constraints.gridx = 1;
        add(username, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.EAST;
        add(emailLabel, constraints);

        constraints.gridx = 1;
        add(email, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.EAST;
        add(phoneLabel, constraints);

        constraints.gridx = 1;
        add(telefono, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.EAST;
        add(passwordLabel, constraints);

        constraints.gridx = 1;
        add(password, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.EAST;
        add(addressLabel, constraints);

        constraints.gridx = 1;
        add(indirizzo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.EAST;
        add(cityLabel, constraints);

        constraints.gridx = 1;
        add(citta, constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(submitButton, constraints);
        submitButton.setActionCommand("modifica_utente");
        submitButton.addActionListener(new UtenteAcquirenteListener(this, null));






    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JTextField getNome() {
        return nome;
    }

    public JLabel getSurnameLabel() {
        return surnameLabel;
    }

    public JTextField getCognome() {
        return cognome;
    }

    public JLabel getPointOfSaleLabel() {
        return pointOfSaleLabel;
    }

    public JComboBox getPuntoVendita() {
        return puntoVendita;
    }

    public JLabel getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public JTextField getGiornoDataNascita() {
        return giornoDataNascita;
    }

    public JTextField getMeseDataNascita() {
        return meseDataNascita;
    }

    public JTextField getAnnoDataNascita() {
        return annoDataNascita;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JTextField getUsername() {
        return username;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public JTextField getEmail() {
        return email;
    }

    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    public JTextField getTelefono() {
        return telefono;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public JTextField getIndirizzo() {
        return indirizzo;
    }

    public JLabel getCityLabel() {
        return cityLabel;
    }

    public JTextField getCitta() {
        return citta;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public UtenteBusiness getUtenteBusiness() {
        return utenteBusiness;
    }

    public String getIdUtente() {
        return idUtente;
    }
}
