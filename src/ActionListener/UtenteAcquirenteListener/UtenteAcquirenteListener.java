package ActionListener.UtenteAcquirenteListener;

import Buisness.SessionManager;
import Buisness.Utente.UtenteBusiness;
import Buisness.UtilityBuisness;
import Model.Prodotti.Articolo;
import ViewProveETest.Home;
import ViewProveETest.Prove.UtenteAcquirente.CommentoPanel;
import ViewProveETest.Prove.UtenteAcquirente.ModificaUtenteAcquirentePanel;
import ViewProveETest.Prove.UtenteAcquirente.RegistrazioneUtenteAcquirentePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class UtenteAcquirenteListener implements ActionListener {

    private JPanel panel;
    private Home finestra;

    public UtenteAcquirenteListener(JPanel panel, Home finestra){
        this.panel=panel;
        this.finestra=finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(panel instanceof RegistrazioneUtenteAcquirentePanel)
            System.out.println("registrazione "+registrazione());
        if(panel instanceof ModificaUtenteAcquirentePanel)
            System.out.println("Modifica " + modifica());
        if(e.getActionCommand().equalsIgnoreCase("commento"))
             lasciaCommento();
        if(e.getActionCommand().equalsIgnoreCase("impostazioni"))
            impostazioni();
        if(e.getActionCommand().equalsIgnoreCase("liste"))
            mostracatalogo();

    }


    public int registrazione(){

        RegistrazioneUtenteAcquirentePanel p= (RegistrazioneUtenteAcquirentePanel) panel;

        JTextField nome= p.getNome();
        JTextField cognome= p.getCognome();
        JComboBox puntoVendita=p.getPuntoVendita();
        JTextField username= p.getUsername();
        JTextField email= p.getEmail();
        JTextField telefono= p.getTelefono();
        JPasswordField password=p.getPassword();
        JTextField indirizzo= p.getIndirizzo();
        JTextField citta=p.getCitta();
        JTextField giornoDataNascita= p.getGiornoDataNascita();
        JTextField meseDataNascita=p.getMeseDataNascita();
        JTextField annoDataNascita= p.getAnnoDataNascita();
        String dataDiNascita;


        boolean b= UtilityBuisness.getInstance().chekData(Calendar.getInstance().get(Calendar.YEAR), 1900, giornoDataNascita.getText(), meseDataNascita.getText(), annoDataNascita.getText());
        if (b) {
            dataDiNascita = annoDataNascita.getText() + "-" + meseDataNascita.getText() + "-" + giornoDataNascita.getText();

            String message;
            if(nome.getText().trim().length()==0){
                message="Inserire il nome";
                System.out.println(message);
                b=false;
            }
            if(cognome.getText().trim().length()==0){
                message="Inserire cognome ";
                System.out.println(message);
                b=false;
            }
            if(username.getText().trim().length()==0){
                message="Inserire username";
                System.out.println(message);
                b=false;
            }
            if(email.getText().trim().length()==0){
                message="Inserire un'email";
                System.out.println(message);
                b=false;
            }else if(UtilityBuisness.getInstance().checkEmail(email.getText())){
                message="Email già utilizzata";
                System.out.println(message);
                b=false;
            }else if(UtilityBuisness.getInstance().checkFormattedEmail(email.getText())){
                message="Inserire un'email valida";
                System.out.println(message);
                b=false;
            }
            if(telefono.getText().trim().length()!=10 || !UtilityBuisness.getInstance().checkInteger(telefono.getText().trim())){
                message="Inserire un numero di telefono valido";
                System.out.println(message);
                b=false;
            }
            if(password.getPassword().length==0){
                message="Inserire una password valida";
                System.out.println(message);
                b=false;
            }
            if(indirizzo.getText().trim().length()==0){
                message="Inserire un indirzzo valido";
                System.out.println(message);
                b=false;
            }
            if(citta.getText().trim().length()==0){
                message="Inserire una citta valida";
                System.out.println(message);
                b=false;
            }

            if(b) {
                //inserimento utente acquirente nel database

                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(nome.getText());
                tmp.add(cognome.getText());
                tmp.add(puntoVendita.getSelectedIndex());
                tmp.add(dataDiNascita);
                tmp.add(null);
                tmp.add(null);
                tmp.add(null);
                tmp.add(username.getText());
                tmp.add(email.getText());
                tmp.add(telefono.getText());
                tmp.add(new String(password.getPassword()));
                tmp.add(indirizzo.getText());
                tmp.add(citta.getText());

                return  UtenteBusiness.getInstance().addUtenteAcuirente(tmp);

            }
        }


            return 0;
    }

    public int modifica(){

        ModificaUtenteAcquirentePanel p = (ModificaUtenteAcquirentePanel) panel;

        JTextField nome= p.getNome();
        JTextField cognome= p.getCognome();
        JComboBox puntoVendita=p.getPuntoVendita();
        JTextField username= p.getUsername();
        JTextField email= p.getEmail();
        JTextField telefono= p.getTelefono();
        JPasswordField password=p.getPassword();
        JTextField indirizzo= p.getIndirizzo();
        JTextField citta=p.getCitta();
        JTextField giornoDataNascita= p.getGiornoDataNascita();
        JTextField meseDataNascita=p.getMeseDataNascita();
        JTextField annoDataNascita= p.getAnnoDataNascita();
        String dataDiNascita;


        boolean b= UtilityBuisness.getInstance().chekData(Calendar.getInstance().get(Calendar.YEAR), 1900, giornoDataNascita.getText(), meseDataNascita.getText(), annoDataNascita.getText());
        if (b) {
            dataDiNascita = annoDataNascita.getText() + "-" + meseDataNascita.getText() + "-" + giornoDataNascita.getText();

            String message;
            if(nome.getText().trim().length()==0){
                message="Inserire il nome";
                System.out.println(message);
                b=false;
            }
            if(cognome.getText().trim().length()==0){
                message="Inserire cognome ";
                System.out.println(message);
                b=false;
            }
            if(username.getText().trim().length()==0){
                message="Inserire username";
                System.out.println(message);
                b=false;
            }
            if(email.getText().trim().length()==0){
                message="Inserire un'email";
                System.out.println(message);
                b=false;
            }else if(UtilityBuisness.getInstance().checkFormattedEmail(email.getText())){
                message="Inserire un'email valida";
                System.out.println(message);
                b=false;
            }
            if(telefono.getText().trim().length()!=10 || !UtilityBuisness.getInstance().checkInteger(telefono.getText().trim())){
                message="Inserire un numero di telefono valido";
                System.out.println(message);
                b=false;
            }
            if(password.getPassword().length==0){
                message="Inserire una password valida";
                System.out.println(message);
                b=false;
            }
            if(indirizzo.getText().trim().length()==0){
                message="Inserire un indirzzo valido";
                System.out.println(message);
                b=false;
            }
            if(citta.getText().trim().length()==0){
                message="Inserire una citta valida";
                System.out.println(message);
                b=false;
            }

            if(b) {
                //modifica utente acquirente nel database

                ArrayList<Object> tmp = new ArrayList<>();
                tmp.add(nome.getText());
                tmp.add(cognome.getText());
                tmp.add(puntoVendita.getSelectedIndex());
                tmp.add(dataDiNascita);
                tmp.add(null);
                tmp.add(null);
                tmp.add(null);
                tmp.add(username.getText());
                tmp.add(email.getText());
                tmp.add(telefono.getText());
                tmp.add(Arrays.toString(password.getPassword()));
                tmp.add(indirizzo.getText());
                tmp.add(citta.getText());
                tmp.add(p.getIdUtente());

                return  UtenteBusiness.getInstance().updataUtenteAcquirente(tmp);

            }
        }
        return 0;
    }

    public int lasciaCommento(){



        {
            finestra.getNord().removeAll();
            finestra.getNord().setLayout(new FlowLayout());
            finestra.getNord().add(finestra.getBack());
            List<Articolo> articoloList = new ArrayList<>();
            SessionManager.getSession().put(SessionManager.CARRELLO, articoloList);
            finestra.getCentro().removeAll();
            new CommentoPanel();
            finestra.getEst().removeAll();
            finestra.repaint();
            finestra.validate();

        }


        return 0;

    }

    public int impostazioni(){
        return 0;

    }

    public void mostracatalogo()
    {
        finestra.mostraListe();

    }




}
