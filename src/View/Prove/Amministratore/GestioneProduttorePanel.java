package View.Prove.Amministratore;

import Buisness.ProduttoreBuisness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestioneProduttorePanel extends JPanel {


    private JTextField nome;
    private JTextField sito;
    private JTextField citta;
    private JTextField nazione;
    private JButton conferma;

    public GestioneProduttorePanel(){

        nome=new JTextField();
        nome.setColumns(20);
        sito= new JTextField();
        sito.setColumns(20);
        citta=new JTextField();
        citta.setColumns(20);
        nazione= new JTextField();
        nazione.setColumns(20);
        conferma= new JButton("Conferma");
        conferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nome.getText().trim().length()==0||sito.getText().trim().length()==0||citta.getText().trim().length()==0||nazione.getText().trim().length()==0){

                    JOptionPane.showMessageDialog(null, "Compila tutti i campi");
                }else{
                    int i= ProduttoreBuisness.getInstance().addProduttore(nome.getText(), sito.getText(), citta.getText(), nazione.getText());

                    if(i<0){
                        JOptionPane.showMessageDialog(null, "Produttore non inserito");
                    }else{
                        JOptionPane.showMessageDialog(null, "Produttore inserito");
                    }
                }
            }
        });

        add(nome);
        add(sito);
        add(citta);
        add(nazione);
        add(conferma);

    }



}
