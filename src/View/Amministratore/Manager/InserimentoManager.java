package View.Amministratore.Manager;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.PuntoVenditaBuisness;

import javax.swing.*;
import java.awt.*;


public class InserimentoManager extends JPanel {

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
    private JLabel salaryLabel;
    private JTextField salario;
    private JLabel startOfAssignmentDateLabel;
    private JTextField giornoDataInizioIncarico;
    private JTextField meseDataInizioIncarico;
    private JTextField annoDataInizioIncarico;
    private JLabel endOfAssignmentDateLabel;
    private JTextField giornoDataFineIncarico;
    private JTextField meseDataFineIncarico;
    private JTextField annoDataFineIncarico;
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
    private String dataDiInizioIncarico;
    private String dataDiFineIncarico;

    public InserimentoManager(){
        nameLabel = new JLabel("Nome:");
        nome = new JTextField(20);
        surnameLabel = new JLabel("Cognome:");
        cognome = new JTextField(20);
        pointOfSaleLabel = new JLabel("Punto vendita:");
        puntoVendita = PuntoVenditaBuisness.getInstance().getPuntoVenditaBox();
        dateOfBirthLabel = new JLabel("Data di nascita (dd/MM/yyyy):");
        giornoDataNascita= new JTextField(6);
        meseDataNascita=new JTextField(6);
        annoDataNascita= new JTextField(6);
        salaryLabel = new JLabel("Salario:");
        salario = new JTextField(20);
        startOfAssignmentDateLabel = new JLabel("Data inizio incarico (dd/MM/yyyy):");
        giornoDataInizioIncarico = new JTextField(6);
        meseDataInizioIncarico = new JTextField(6);
        annoDataInizioIncarico = new JTextField(6);
        endOfAssignmentDateLabel = new JLabel("Data fine incarico (dd/MM/yyyy):");
        giornoDataFineIncarico = new JTextField(6);
        meseDataFineIncarico = new JTextField(6);
        annoDataFineIncarico = new JTextField(6);
        usernameLabel = new JLabel("Username:");
        username = new JTextField(20);
        emailLabel = new JLabel("Email:");
        email = new JTextField(20);
        phoneLabel = new JLabel("Telefono:");
        telefono = new JTextField(20);
        passwordLabel = new JLabel("Password:");
        password = new JPasswordField(20);
        addressLabel = new JLabel("Indirizzo:");
        indirizzo = new JTextField(20);
        cityLabel = new JLabel("Citta:");
        citta = new JTextField(20);
        submitButton = new JButton("Submit");

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
        constraints.gridy = 4;
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
        constraints.gridy = 5;
        constraints.anchor = GridBagConstraints.EAST;
        add(salaryLabel, constraints);

        constraints.gridx = 1;
        add(salario, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.anchor = GridBagConstraints.EAST;
        add(startOfAssignmentDateLabel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(giornoDataInizioIncarico, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        add(meseDataInizioIncarico, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        add(annoDataInizioIncarico, constraints);



        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.anchor = GridBagConstraints.EAST;
        add(endOfAssignmentDateLabel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        add(giornoDataFineIncarico, constraints);
        constraints.anchor = GridBagConstraints.CENTER;
        add(meseDataFineIncarico, constraints);
        constraints.anchor = GridBagConstraints.EAST;
        add(annoDataFineIncarico, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.anchor = GridBagConstraints.EAST;
        add(usernameLabel, constraints);

        constraints.gridx = 1;
        add(username, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.EAST;
        add(emailLabel, constraints);

        constraints.gridx = 1;
        add(email, constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.anchor = GridBagConstraints.EAST;
        add(phoneLabel, constraints);

        constraints.gridx = 1;
        add(telefono, constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.anchor = GridBagConstraints.EAST;
        add(passwordLabel, constraints);

        constraints.gridx = 1;
        add(password, constraints);

        constraints.gridx = 0;
        constraints.gridy = 12;
        constraints.anchor = GridBagConstraints.EAST;
        add(addressLabel, constraints);

        constraints.gridx = 1;
        add(indirizzo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 13;
        constraints.anchor = GridBagConstraints.EAST;
        add(cityLabel, constraints);

        constraints.gridx = 1;
        add(citta, constraints);

        constraints.gridx = 0;
        constraints.gridy = 14;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(submitButton, constraints);
        submitButton.setActionCommand("inserimento");
        submitButton.addActionListener(new AmministratoreListener(this)

        );

}

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JTextField getNome() {
        return nome;
    }

    public void setNome(JTextField nome) {
        this.nome = nome;
    }

    public JLabel getSurnameLabel() {
        return surnameLabel;
    }

    public void setSurnameLabel(JLabel surnameLabel) {
        this.surnameLabel = surnameLabel;
    }

    public JTextField getCognome() {
        return cognome;
    }

    public void setCognome(JTextField cognome) {
        this.cognome = cognome;
    }

    public JLabel getPointOfSaleLabel() {
        return pointOfSaleLabel;
    }

    public void setPointOfSaleLabel(JLabel pointOfSaleLabel) {
        this.pointOfSaleLabel = pointOfSaleLabel;
    }

    public JComboBox getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(JComboBox puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    public JLabel getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public void setDateOfBirthLabel(JLabel dateOfBirthLabel) {
        this.dateOfBirthLabel = dateOfBirthLabel;
    }

    public JTextField getGiornoDataNascita() {
        return giornoDataNascita;
    }

    public void setGiornoDataNascita(JTextField giornoDataNascita) {
        this.giornoDataNascita = giornoDataNascita;
    }

    public JTextField getMeseDataNascita() {
        return meseDataNascita;
    }

    public void setMeseDataNascita(JTextField meseDataNascita) {
        this.meseDataNascita = meseDataNascita;
    }

    public JTextField getAnnoDataNascita() {
        return annoDataNascita;
    }

    public void setAnnoDataNascita(JTextField annoDataNascita) {
        this.annoDataNascita = annoDataNascita;
    }

    public JLabel getSalaryLabel() {
        return salaryLabel;
    }

    public void setSalaryLabel(JLabel salaryLabel) {
        this.salaryLabel = salaryLabel;
    }

    public JTextField getSalario() {
        return salario;
    }

    public void setSalario(JTextField salario) {
        this.salario = salario;
    }

    public JLabel getStartOfAssignmentDateLabel() {
        return startOfAssignmentDateLabel;
    }

    public void setStartOfAssignmentDateLabel(JLabel startOfAssignmentDateLabel) {
        this.startOfAssignmentDateLabel = startOfAssignmentDateLabel;
    }

    public JTextField getGiornoDataInizioIncarico() {
        return giornoDataInizioIncarico;
    }

    public void setGiornoDataInizioIncarico(JTextField giornoDataInizioIncarico) {
        this.giornoDataInizioIncarico = giornoDataInizioIncarico;
    }

    public JTextField getMeseDataInizioIncarico() {
        return meseDataInizioIncarico;
    }

    public void setMeseDataInizioIncarico(JTextField meseDataInizioIncarico) {
        this.meseDataInizioIncarico = meseDataInizioIncarico;
    }

    public JTextField getAnnoDataInizioIncarico() {
        return annoDataInizioIncarico;
    }

    public void setAnnoDataInizioIncarico(JTextField annoDataInizioIncarico) {
        this.annoDataInizioIncarico = annoDataInizioIncarico;
    }

    public JLabel getEndOfAssignmentDateLabel() {
        return endOfAssignmentDateLabel;
    }

    public void setEndOfAssignmentDateLabel(JLabel endOfAssignmentDateLabel) {
        this.endOfAssignmentDateLabel = endOfAssignmentDateLabel;
    }

    public JTextField getGiornoDataFineIncarico() {
        return giornoDataFineIncarico;
    }

    public void setGiornoDataFineIncarico(JTextField giornoDataFineIncarico) {
        this.giornoDataFineIncarico = giornoDataFineIncarico;
    }

    public JTextField getMeseDataFineIncarico() {
        return meseDataFineIncarico;
    }

    public void setMeseDataFineIncarico(JTextField meseDataFineIncarico) {
        this.meseDataFineIncarico = meseDataFineIncarico;
    }

    public JTextField getAnnoDataFineIncarico() {
        return annoDataFineIncarico;
    }

    public void setAnnoDataFineIncarico(JTextField annoDataFineIncarico) {
        this.annoDataFineIncarico = annoDataFineIncarico;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JTextField getEmail() {
        return email;
    }

    public void setEmail(JTextField email) {
        this.email = email;
    }

    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    public void setPhoneLabel(JLabel phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    public JTextField getTelefono() {
        return telefono;
    }

    public void setTelefono(JTextField telefono) {
        this.telefono = telefono;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(JLabel addressLabel) {
        this.addressLabel = addressLabel;
    }

    public JTextField getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(JTextField indirizzo) {
        this.indirizzo = indirizzo;
    }

    public JLabel getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(JLabel cityLabel) {
        this.cityLabel = cityLabel;
    }

    public JTextField getCitta() {
        return citta;
    }

    public void setCitta(JTextField citta) {
        this.citta = citta;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getDataDiInizioIncarico() {
        return dataDiInizioIncarico;
    }

    public void setDataDiInizioIncarico(String dataDiInizioIncarico) {
        this.dataDiInizioIncarico = dataDiInizioIncarico;
    }

    public String getDataDiFineIncarico() {
        return dataDiFineIncarico;
    }

    public void setDataDiFineIncarico(String dataDiFineIncarico) {
        this.dataDiFineIncarico = dataDiFineIncarico;
    }
}
