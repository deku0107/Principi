package ViewProveETest.Prove.Amministratore.Offerta;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.ArticoloBuisness;
import Buisness.ProduttoreBuisness;


import javax.swing.*;
import java.awt.*;

public class RigaProdotto extends JPanel {

    private JLabel nomeLabel;
    private JLabel nome;
    private JLabel scaffaleLabel;
    private JTextField scaffale;
    private JLabel corsiaLabel;
    private JTextField corsia;
    private JButton remove;
    private JButton conferma;
    private Object prodotto;
    private Object puntoVendita;

    public RigaProdotto(){
        nomeLabel= new JLabel("Prodotto:");
        nome= new JLabel("nome prodotto");
        scaffaleLabel= new JLabel("Scaffale");
        scaffale= new JTextField();
        corsiaLabel= new JLabel("Corsia");
        corsia= new JTextField();
        remove= new JButton("Rimuovi");
        remove.setActionCommand("rimuovere_offerta");
        remove.addActionListener(new AmministratoreListener(this));
        conferma=new JButton("Aggiorna poiszione");
        conferma.setActionCommand("aggiorna_offerta");
        conferma.addActionListener(new AmministratoreListener(this));

        setLayout(new GridBagLayout());

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
        add(scaffaleLabel, constraints);

        constraints.gridy = 1;
        add(scaffale, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(corsiaLabel, constraints);

        constraints.gridy = 1;
        add(corsia, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(remove, constraints);

        constraints.gridx=2;
        add(conferma, constraints);

    }

    public void update(Object prodotto){
        this.prodotto=prodotto;
        System.out.println("Id prodotto in update " + ArticoloBuisness.getInstance().geIdArticolo(prodotto));

        nome.setText(ArticoloBuisness.getInstance().getNome(prodotto));
        scaffale.setText(String.valueOf(ArticoloBuisness.getInstance().getScaffale(prodotto)));
        corsia.setText(String.valueOf(ArticoloBuisness.getInstance().getCorsia(prodotto)));


    }

    public JLabel getNome() {
        return nome;
    }

    public JTextField getScaffale() {
        return scaffale;
    }

    public JTextField getCorsia() {
        return corsia;
    }

    public JButton getRemove() {
        return remove;
    }

    public Object getProdotto() {
        return prodotto;
    }

    public Object getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(Object puntoVendita) {
        this.puntoVendita = puntoVendita;
    }

    public JButton getConferma() {
        return conferma;
    }
}
