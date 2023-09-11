package View.Amministratore.Articoli;


import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.ArticoloBuisness;
import Buisness.CategoriaBuisness;
import Buisness.Immagini.OpenFileChooser;
import Buisness.Immagini.SalvaImmagine;
import Buisness.ProduttoreBuisness;
import View.Amministratore.Articoli.ArticoloComposito.ComposizionePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ModificaProdottoPanel extends JPanel {

    private JLabel nomeLabel;
    private JTextField nome;
    private String nomeOriginario;
    private JLabel prezzoLabel;
    private JTextField prezzo;
    private JLabel categoriaLabel;
    private JComboBox categoria;
    private JLabel descrizioneLabel;
    private JTextArea descrizione;
    private JLabel produttoreLabel;
    private JComboBox produttore;
    private JLabel immagineLabel;
    private JButton immagineChooser;
    private JTextArea composizione;
    private JLabel composizioneLabel;
    private JButton conferma;
    private File file;
    private String path;
    private Object o;//articolo
    private JButton modificaComposizione;



    public ModificaProdottoPanel(Object o){

        this.o=o;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();


        gbc.insets= new Insets(10,10,10,10);

        nomeLabel= new JLabel("Nome");
        nome= new JTextField(15);
        nome.setText(ArticoloBuisness.getInstance().getNome(o));
        prezzoLabel= new JLabel("Prezzo");
        prezzo= new JTextField(15);
        prezzo.setText(String.valueOf(ArticoloBuisness.getInstance().getPrezzo(o)));
        produttoreLabel= new JLabel("Produttore: " +ArticoloBuisness.getInstance().getProduttore(o).getNome());
        produttore= ProduttoreBuisness.getInstance().getproduttoreBox();
        categoriaLabel= new JLabel("Categoria: " + ArticoloBuisness.getInstance().getCategoria(o).getNome());
        categoria= CategoriaBuisness.getInstance().getCategorieBox();
        descrizioneLabel= new JLabel("descrizione");
        descrizione= new JTextArea();
        descrizione.setText(ArticoloBuisness.getInstance().getDescrizione(o));
        immagineLabel= new JLabel("Immagine");
        immagineChooser= new JButton("Scegli");
        immagineChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFileChooser openFileChooser= new OpenFileChooser();
                file= openFileChooser.getFile();
                path=new SalvaImmagine(file, nome.getText()+file.getName().substring(file.getName().lastIndexOf("."))).getPath();
            }
        });

        if (ArticoloBuisness.getInstance().getPath(o)== null){

            path = "src/Resources/Immagini/Logo.jpg";
        }else {
           // path=ArticoloBuisness.getInstance().getPath(o);
        }
        conferma= new JButton("Conferma");
        conferma.setActionCommand("effettua_modifica_prodotto");
        conferma.addActionListener(new AmministratoreListener(this));



        gbc.gridx=0;
        gbc.gridy=0;
        add(nomeLabel, gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        add(nome, gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        add(prezzoLabel, gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        add(prezzo, gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        add(produttoreLabel, gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        add(produttore, gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        add(categoriaLabel, gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        add(categoria, gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        add(descrizioneLabel, gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        add(descrizione, gbc);

        gbc.gridx=2;
        gbc.gridy=2;
        add(immagineLabel, gbc);

        gbc.gridx=2;
        gbc.gridy=3;
        add(immagineChooser, gbc);


        gbc.gridx=3;
        gbc.gridy=0;
        gbc.gridheight=4;

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
        ImageIcon icon= new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        JLabel label = new JLabel(icon);
        add(label, gbc);


        gbc.gridx=1;
        gbc.gridy=4;
        gbc.gridheight=1;
        add(conferma, gbc);


        if (ArticoloBuisness.getInstance().isComposito(o)) {
            modificaComposizione= new JButton("Modifica composizione");
            modificaComposizione.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finestraComposizione();
                }
            });
            gbc.gridx=2;
            gbc.gridy=4;
            add(modificaComposizione, gbc);

        }


    }


    private void finestraComposizione(){
        JFrame newFrame = new JFrame("Nuova Finestra");
        newFrame.setSize(500, 500);

        setLayout(new BorderLayout());
        // Aggiungi un pannello per visualizzare i dati nella nuova finestra

        ComposizionePanel panel = new ComposizionePanel((java.util.List) ArticoloBuisness.getInstance().getComposizione(o), newFrame);

        JScrollPane scrollPane= new JScrollPane(panel);
        newFrame.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose(); // Chiudi la nuova finestra
            }
        });

       newFrame.add(closeButton, BorderLayout.SOUTH);

       JButton add= new JButton("Aggiungi");
       add.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               JComboBox prodotti= ArticoloBuisness.getInstance().getProdottiBox();
                int input= JOptionPane.showConfirmDialog(null, prodotti, "Scegliere il prodotto", JOptionPane.DEFAULT_OPTION);

                if (input==JOptionPane.OK_OPTION){

                    Object prodotto= ArticoloBuisness.getInstance().getProdotti().get(prodotti.getSelectedIndex());

                    JComboBox <Integer>q=new JComboBox<>();
                    for(int i=1;i<11;i++)
                        q.addItem(i);
                    input= JOptionPane.showConfirmDialog(null, q, "Scegliere il prodotto", JOptionPane.DEFAULT_OPTION);
                    if(input==JOptionPane.OK_OPTION){
                        int j=q.getSelectedIndex()+1;

                        ArticoloBuisness.getInstance().setComposizione(o, prodotto, j);

                        ArrayList list= (ArrayList) ArticoloBuisness.getInstance().getFinalComposizione(o);
                        ArticoloBuisness.getInstance().addComposizione(o, list.get(list.size()-1));

                        panel.update((java.util.List) ArticoloBuisness.getInstance().getFinalComposizione(o));

                    }
                }

                   repaint();
                   validate();
           }
       });

       newFrame.add(add, BorderLayout.NORTH);

        newFrame.setVisible(true);
    }


    private void articoloComposito() {
    }

    private void servizio() {
    }

    private void prodotto(){}


    public JLabel getNomeLabel() {
        return nomeLabel;
    }

    public void setNomeLabel(JLabel nomeLabel) {
        this.nomeLabel = nomeLabel;
    }

    public JTextField getNome() {
        return nome;
    }

    public void setNome(JTextField nome) {
        this.nome = nome;
    }

    public String getNomeOriginario() {
        return nomeOriginario;
    }

    public void setNomeOriginario(String nomeOriginario) {
        this.nomeOriginario = nomeOriginario;
    }

    public JLabel getPrezzoLabel() {
        return prezzoLabel;
    }

    public void setPrezzoLabel(JLabel prezzoLabel) {
        this.prezzoLabel = prezzoLabel;
    }

    public JTextField getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(JTextField prezzo) {
        this.prezzo = prezzo;
    }

    public JLabel getCategoriaLabel() {
        return categoriaLabel;
    }

    public void setCategoriaLabel(JLabel categoriaLabel) {
        this.categoriaLabel = categoriaLabel;
    }

    public JComboBox getCategoria() {
        return categoria;
    }

    public void setCategoria(JComboBox categoria) {
        this.categoria = categoria;
    }

    public JLabel getDescrizioneLabel() {
        return descrizioneLabel;
    }

    public void setDescrizioneLabel(JLabel descrizioneLabel) {
        this.descrizioneLabel = descrizioneLabel;
    }

    public JTextArea getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(JTextArea descrizione) {
        this.descrizione = descrizione;
    }

    public JLabel getProduttoreLabel() {
        return produttoreLabel;
    }

    public void setProduttoreLabel(JLabel produttoreLabel) {
        this.produttoreLabel = produttoreLabel;
    }

    public JComboBox getProduttore() {
        return produttore;
    }

    public void setProduttore(JComboBox produttore) {
        this.produttore = produttore;
    }

    public JLabel getImmagineLabel() {
        return immagineLabel;
    }

    public void setImmagineLabel(JLabel immagineLabel) {
        this.immagineLabel = immagineLabel;
    }

    public JButton getImmagineChooser() {
        return immagineChooser;
    }

    public void setImmagineChooser(JButton immagineChooser) {
        this.immagineChooser = immagineChooser;
    }

    public JTextArea getComposizione() {
        return composizione;
    }

    public void setComposizione(JTextArea composizione) {
        this.composizione = composizione;
    }

    public JLabel getComposizioneLabel() {
        return composizioneLabel;
    }

    public void setComposizioneLabel(JLabel composizioneLabel) {
        this.composizioneLabel = composizioneLabel;
    }

    public JButton getConferma() {
        return conferma;
    }

    public void setConferma(JButton conferma) {
        this.conferma = conferma;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
