package ViewProveETest.Prove.Amministratore.Articoli;

import Buisness.ArticoloBuisness;
import Buisness.CategoriaBuisness;
import Buisness.ProduttoreBuisness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GestioneProdottoPanel extends JPanel {

    private JTextField scaffale;
    private JTextField corsia;
    private JComboBox categoria;
    private JComboBox produttore;
    private  JTextArea descrizione;
    private JButton conferma;

    public GestioneProdottoPanel(JTextField nome, JTextField prezzo){

        scaffale= new JTextField();
        scaffale.setColumns(20);

        corsia=new JTextField();
        corsia.setColumns(20);

        categoria = CategoriaBuisness.getInstance().getCategorieBox();

        produttore= ProduttoreBuisness.getInstance().getproduttoreBox();



        descrizione= new JTextArea();
        descrizione.setColumns(20);
        descrizione.setRows(5);

        conferma=new JButton("Conferma");
        conferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i= ArticoloBuisness.getInstance().addProdotto(nome.getText(), Float.parseFloat(prezzo.getText()), CategoriaBuisness.getInstance().getCategorie().get(categoria.getSelectedIndex()), Integer.parseInt(scaffale.getText()), Integer.parseInt(corsia.getText()), ProduttoreBuisness.getInstance().getProduttori().get(produttore.getSelectedIndex()),descrizione.getText());
                if(i<=0){
                    JOptionPane.showMessageDialog(null, "Prodotto non inserito!");
                }else{JOptionPane.showMessageDialog(null, "Prodotto inserito correttamnete");}



                removeAll();

                add(new InserimentoArticoliPanel());
                repaint();
                validate();
            }
        });




        add(scaffale);
        add(corsia);
        add(categoria);
        add(produttore);
        add(descrizione);
        add(conferma);
    }
}
