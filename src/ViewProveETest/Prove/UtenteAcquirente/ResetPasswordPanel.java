package ViewProveETest.Prove.UtenteAcquirente;

import ActionListener.LoginListener;
import Buisness.UtilityBuisness;

import javax.swing.*;
import java.awt.*;

public class ResetPasswordPanel extends JPanel {


    private String randomString;
    private JLabel emailLAbel;
    private JTextField email;
    private JLabel codiceLabel;
    private JTextField codice;
    private JButton invia;
    private JButton conferma;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton reset;
    private LoginListener loginListener;

    public ResetPasswordPanel(){
        randomString= UtilityBuisness.getInstance().getRandomString(5);
        emailLAbel= new JLabel("Inserisci la tua email");
        email= new JTextField(20);
        codiceLabel= new JLabel("Inserisci il codice inviato per email");
        codice= new JTextField(20);
        invia= new JButton("Invia");
        invia.setActionCommand("invio_email_reset_psw");
        conferma= new JButton("Conferma");
        conferma.setActionCommand("conferma_codice");
        passwordLabel= new JLabel("Inserisci la nuova password");
        password= new JPasswordField(20);
        reset= new JButton("Reset");
        reset.setActionCommand("reset_psw");
        loginListener= new LoginListener(this);
        invia.addActionListener(loginListener);



        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();


        gbc.insets= new Insets(20,20,20,20);

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(emailLAbel, gbc);


        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(email, gbc);


        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(invia, gbc);


        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(codiceLabel, gbc);


        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(codice, gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(conferma, gbc);

        gbc.gridx=0;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(passwordLabel, gbc);

        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(password, gbc);

        gbc.gridx=1;
        gbc.gridy=5;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(reset, gbc);




        codiceLabel.setVisible(false);
        codice.setEditable(false);
        codice.setVisible(false);
        conferma.setVisible(false);

        passwordLabel.setVisible(false);
        password.setEditable(false);
        password.setVisible(false);
        reset.setVisible(false);


    }

    public void visualizzaRigaCodice(){

        codiceLabel.setVisible(true);
        codice.setEditable(true);
        codice.setVisible(true);
        conferma.setVisible(true);
        conferma.addActionListener(loginListener);

        email.setEditable(false);
        invia.setActionCommand("annulla");
        invia.setText("Cambia email");

        repaint();
        validate();

    }

    public void visualizzaRigaPassword(){

        passwordLabel.setVisible(true);
        password.setEditable(true);
        password.setVisible(true);
        reset.setVisible(true);
        reset.addActionListener(loginListener);

        repaint();
        validate();
    }

    public void annulla(){

        codiceLabel.setVisible(false);
        codice.setEditable(false);
        codice.setVisible(false);
        conferma.setVisible(false);
        conferma.setText("");
        conferma.removeActionListener(loginListener);

        passwordLabel.setVisible(false);
        password.setEditable(false);
        password.setVisible(false);
        password.setText("");
        reset.setVisible(false);
        reset.removeActionListener(loginListener);

        email.setEditable(true);
        invia.setActionCommand("invio_email_reset_psw");
        invia.setText("Invia");

        repaint();
        validate();

    }

    public String getRandomString() {
        return randomString;
    }

    public JLabel getEmailLAbel() {
        return emailLAbel;
    }

    public JTextField getEmail() {
        return email;
    }

    public JLabel getCodiceLabel() {
        return codiceLabel;
    }

    public JTextField getCodice() {
        return codice;
    }

    public JButton getInvia() {
        return invia;
    }

    public JButton getConferma() {
        return conferma;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JButton getReset() {
        return reset;
    }

    public LoginListener getLoginListener() {
        return loginListener;
    }
}
