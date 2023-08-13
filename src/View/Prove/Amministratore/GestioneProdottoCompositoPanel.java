package View.Prove.Amministratore;

import Buisness.ArticoloBuisness;
import Buisness.CategoriaBuisness;
import Buisness.ProduttoreBuisness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestioneProdottoCompositoPanel extends JPanel {

    private JTextField scaffale;
    private JTextField corsia;
    private JComboBox categoria;
    private JComboBox produttore;
    private JComboBox prodotti;
    private JComboBox q;
    private int j;
    private  JTextArea descrizione;
    private JTextArea elenco;
    private JScrollPane jScrollPane;
    private JButton conferma;
    private List<Object> articoli;
    private List<Integer> quantita;
    private String lista;

    public GestioneProdottoCompositoPanel(JTextField nome, JTextField preezzo){

        setLayout(new FlowLayout());


        articoli=new ArrayList<>();
        quantita=new ArrayList<>();
        elenco= new JTextArea();
        prodotti= ArticoloBuisness.getInstance().getProdottiBox();
        prodotti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                q=new JComboBox();
                for(int i=1;i<11;i++)
                    q.addItem(i);

                int input=JOptionPane.showConfirmDialog(null, q, "Inserire quantita'", JOptionPane.DEFAULT_OPTION);

                if(input==JOptionPane.OK_OPTION){
                    j=q.getSelectedIndex()+1;

                    articoli.add(ArticoloBuisness.getInstance().getProdotti().get(prodotti.getSelectedIndex()));
                    quantita.add(j);
                    System.out.println(articoli);
                    System.out.println(quantita);

                    lista+=ArticoloBuisness.getInstance().getProdotti().get(prodotti.getSelectedIndex()).getNome() + " X " + j +"\n";

                    elenco.setText(lista);
                    repaint();
                    validate();
                }

            }
        });



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

                int i= ArticoloBuisness.getInstance().addProdottoComposito(nome.getText(), Float.parseFloat(preezzo.getText()), CategoriaBuisness.getInstance().getCategorie().get(categoria.getSelectedIndex()), Integer.parseInt(scaffale.getText()), Integer.parseInt(corsia.getText()),ProduttoreBuisness.getInstance().getProduttori().get(produttore.getSelectedIndex()),descrizione.getText(),articoli, quantita);
                if(i<=0){
                    JOptionPane.showMessageDialog(null, "Prodotto non inserito!");
                }else{JOptionPane.showMessageDialog(null, "Prodotto inserito correttamnete");}

                removeAll();

                add(new GestioneArticoliPanel());
                repaint();
                validate();
            }
        });




        add(prodotti);
        elenco.setColumns(20);
        elenco.setRows(5);
        jScrollPane=new JScrollPane(elenco);
        add(jScrollPane);
        add(scaffale);
        add(corsia);
        add(categoria);
        add(produttore);
        add(descrizione);
        add(conferma);
    }
}
