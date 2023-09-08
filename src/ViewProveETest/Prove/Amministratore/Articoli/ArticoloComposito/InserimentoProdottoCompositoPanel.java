package ViewProveETest.Prove.Amministratore.Articoli.ArticoloComposito;

import Buisness.Immagini.OpenFileChooser;
import Buisness.ArticoloBuisness;
import Buisness.CategoriaBuisness;
import Buisness.Immagini.SalvaImmagine;
import Buisness.ProduttoreBuisness;
import ViewProveETest.Prove.Amministratore.Articoli.InserimentoArticoliPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InserimentoProdottoCompositoPanel extends JPanel {


    private JLabel scaffaleLabel;
    private JLabel corsiaLabel;
    private JLabel categoriaLabel;
    private JLabel produttoreLabel;
    private JLabel descrizioneLabel;
    private JLabel immagineLabel;
    private JLabel prodottoLabel;
    private JTextField scaffale;
    private JTextField corsia;
    private JComboBox categoria;
    private JComboBox produttore;
    private JComboBox prodotti;
    private JComboBox<Integer> q;
    private int j;
    private  JTextArea descrizione;
    private JTextArea elenco;
    private JScrollPane jScrollPane;
    private JButton conferma;
    private JButton immagineChooser;
    private List<Object> articoli;
    private List<Integer> quantita;
    private String lista;
    private File file;
    private String path;

    public InserimentoProdottoCompositoPanel(JTextField nome, JTextField preezzo){

        setLayout(new GridBagLayout());



        scaffaleLabel= new JLabel("Corsia");
        corsiaLabel= new JLabel("Corsia");
        categoriaLabel= new JLabel("Categoria");
        produttoreLabel= new JLabel("Produttore");
        descrizioneLabel= new JLabel("Descrizione");
        immagineLabel= new JLabel("Immagine");
        prodottoLabel= new JLabel("Prodotto");
        immagineChooser= new JButton("Scegli immagine");
        immagineChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFileChooser openFileChooser= new OpenFileChooser();
                file= openFileChooser.getFile();
                path=new SalvaImmagine(file, nome.getText()+file.getName().substring(file.getName().lastIndexOf("."))).getPath();
            }
        });




        articoli=new ArrayList<>();
        quantita=new ArrayList<>();
        elenco= new JTextArea();
        prodotti= ArticoloBuisness.getInstance().getProdottiBox();
        prodotti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                q=new JComboBox<>();
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

                int i= ArticoloBuisness.getInstance().addProdottoComposito(nome.getText(), Float.parseFloat(preezzo.getText()), CategoriaBuisness.getInstance().getCategorie().get(categoria.getSelectedIndex()), Integer.parseInt(scaffale.getText()), Integer.parseInt(corsia.getText()),ProduttoreBuisness.getInstance().getProduttori().get(produttore.getSelectedIndex()),descrizione.getText(),articoli, quantita, path);
                if(i<=0){
                    JOptionPane.showMessageDialog(null, "Prodotto non inserito!");
                }else{JOptionPane.showMessageDialog(null, "Prodotto inserito correttamnete");}

                removeAll();

                add(new InserimentoArticoliPanel());
                repaint();
                validate();
            }
        });



        GridBagConstraints gbc= new GridBagConstraints();
        //gbc.insets= new Insets(20,20,20,20);
        gbc.gridx=0;
        gbc.gridy=0;

        add(prodottoLabel, gbc);
        gbc.gridx=0;
        gbc.gridy=1;

        add(prodotti, gbc);



        elenco.setColumns(20);
        elenco.setRows(5);
        jScrollPane=new JScrollPane(elenco);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridheight=2;

        add(jScrollPane, gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridheight=1;
        add(categoriaLabel, gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        add(categoria, gbc);



        gbc.gridx=0;
        gbc.gridy=3;
        add(categoria, gbc);

        gbc.gridx=0;
        gbc.gridy=4;

        add(produttoreLabel, gbc);

        gbc.gridx=0;
        gbc.gridy=5;

        add(produttore, gbc);

        gbc.gridx=0;
        gbc.gridy=6;
        add(corsiaLabel, gbc);

        gbc.gridx=0;
        gbc.gridy=7;
        add(corsia, gbc);

        gbc.gridx=1;
        gbc.gridy=6;
        add(scaffaleLabel, gbc);

        gbc.gridx=1;
        gbc.gridy=7;
        add(scaffale, gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        add(immagineLabel, gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        add(immagineChooser, gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        add(descrizioneLabel, gbc);

        gbc.gridx=2;
        gbc.gridy=3;
        add(descrizione, gbc);

        gbc.gridx=2;
        gbc.gridy=7;
        add(conferma, gbc);
    }
}
