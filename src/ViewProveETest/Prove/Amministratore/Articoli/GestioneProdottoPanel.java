package ViewProveETest.Prove.Amministratore.Articoli;

import Buisness.ArticoloBuisness;
import Buisness.CategoriaBuisness;
import Buisness.Immagini.OpenFileChooser;
import Buisness.Immagini.SalvaImmagine;
import Buisness.ProduttoreBuisness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class GestioneProdottoPanel extends JPanel {

    private JLabel scaffaleLabel;
    private JLabel corsiaLabel;
    private JLabel categoriaLabel;
    private JLabel produttoreLabel;
    private JLabel descrizioneLabel;
    private JLabel immagineLabel;
    private JTextField scaffale;
    private JTextField corsia;
    private JComboBox categoria;
    private JComboBox produttore;
    private  JTextArea descrizione;
    private JButton conferma;
    private JButton immagineChooser;
    private File file;
    private String path;


    public GestioneProdottoPanel(JTextField nome, JTextField prezzo){

        setLayout(new GridBagLayout());


        immagineChooser= new JButton("Scegli immagine");
        immagineChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFileChooser openFileChooser= new OpenFileChooser();
                file= openFileChooser.getFile();
                path=new SalvaImmagine(file, nome.getText()+file.getName().substring(file.getName().lastIndexOf("."))).getPath();
            }
        });
         scaffaleLabel=new JLabel("Scaffele");
         corsiaLabel= new JLabel("Corsia");
         categoriaLabel= new JLabel("Categoria");
         produttoreLabel= new JLabel("Produttore");
         descrizioneLabel= new JLabel("Descrizione");
         immagineLabel= new JLabel("Immagine");

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

                int i= ArticoloBuisness.getInstance().addProdotto(nome.getText(), Float.parseFloat(prezzo.getText()), CategoriaBuisness.getInstance().getCategorie().get(categoria.getSelectedIndex()), Integer.parseInt(scaffale.getText()), Integer.parseInt(corsia.getText()), ProduttoreBuisness.getInstance().getProduttori().get(produttore.getSelectedIndex()),descrizione.getText(), path);
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
        gbc.insets= new Insets(20,20,20,20);
        gbc.gridx=0;
        gbc.gridy=0;


        add(scaffaleLabel, gbc);
        gbc.gridy=1;
        add(scaffale, gbc);

        gbc.gridx=1;
        gbc.gridy=0;

        add(corsiaLabel, gbc);
        gbc.gridy=1;
        add(corsia, gbc);

        gbc.gridx=2;
        gbc.gridy=0;

        add(categoriaLabel, gbc);
        gbc.gridy=1;
        add(categoria, gbc);

        gbc.gridx=0;
        gbc.gridy=2;

        add(produttoreLabel, gbc);
        gbc.gridy=3;
        add(produttore, gbc);

        gbc.gridx=1;
        gbc.gridy=2;

        add(descrizioneLabel, gbc);
        gbc.gridy=3;
        add(descrizione, gbc);


        gbc.gridx=2;
        gbc.gridy=2;

        add(immagineLabel, gbc);
        gbc.gridy=3;
        add(immagineChooser, gbc);

        gbc.gridx=6;
        add(conferma, gbc);
    }
}
