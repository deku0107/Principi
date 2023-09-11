package View.Amministratore.Produttore;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.ProduttoreBuisness;

import javax.swing.*;
import java.awt.*;

public class RigaGestioneProduttorePanel extends JPanel {


    private JLabel nomeLabel;
    private JLabel sitoLabel;
    private JLabel cittaLabel;
    private JLabel nazioneLabel;
    private JTextField nome;
    private JTextField sito;
    private JTextField citta;
    private JTextField nazione;
    private JButton conferma;
    private Object produttore;

    public RigaGestioneProduttorePanel(){

        setLayout(new GridBagLayout());
        nomeLabel= new JLabel("Nome:");
        sitoLabel= new JLabel("Sito:");
        cittaLabel= new JLabel("Citta:");
        nazioneLabel= new JLabel("Nazione:");
        nome=new JTextField();
        nome.setColumns(20);
        sito= new JTextField();
        sito.setColumns(20);
        citta=new JTextField();
        citta.setColumns(20);
        nazione= new JTextField();
        nazione.setColumns(20);
        conferma= new JButton("Conferma");
        conferma.setActionCommand("inserimento_produttore");
        conferma.addActionListener(new AmministratoreListener(this));


        GridBagConstraints constraints = new GridBagConstraints();


        constraints.insets= new Insets(20,10,20,10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        add(nomeLabel, constraints);

        constraints.gridy = 1;
        add(nome, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(sitoLabel, constraints);

        constraints.gridy = 1;
        add(sito, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(cittaLabel, constraints);

        constraints.gridy = 1;
        add(citta, constraints);

        constraints.gridx = 3;
        constraints.gridy = 0;
        add(nazioneLabel, constraints);

        constraints.gridy = 1;
        add(nazione, constraints);


        constraints.gridy=2;
        add(conferma, constraints);

    }


    public void update(Object produttore){

        this.produttore=produttore;
        ProduttoreBuisness produttoreBuisness= ProduttoreBuisness.getInstance();


        nome.setText(produttoreBuisness.getNome(produttore));
        sito.setText(produttoreBuisness.getSito(produttore));
        citta.setText(produttoreBuisness.getCitta(produttore));
        nazione.setText(produttoreBuisness.getNazione(produttore));
        conferma.setActionCommand("modifica_p");

    }


    public JLabel getNomeLabel() {
        return nomeLabel;
    }

    public JLabel getSitoLabel() {
        return sitoLabel;
    }

    public JLabel getCittaLabel() {
        return cittaLabel;
    }

    public JLabel getNazioneLabel() {
        return nazioneLabel;
    }

    public JTextField getNome() {
        return nome;
    }

    public JTextField getSito() {
        return sito;
    }

    public JTextField getCitta() {
        return citta;
    }

    public JTextField getNazione() {
        return nazione;
    }

    public JButton getConferma() {
        return conferma;
    }

    public Object getProduttore() {
        return produttore;
    }
}
