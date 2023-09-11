package View.Amministratore.Offerta;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.ArticoloBuisness;
import Buisness.PuntoVenditaBuisness;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class ElencoOfferta extends JPanel {

    private JButton button;
    private final JPanel mainList;
    private String idPuntoVendita;

    public ElencoOfferta(String idPuntoVendita){
        this.idPuntoVendita=idPuntoVendita;
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        List articoli= ArticoloBuisness.getInstance().getCatalogo(idPuntoVendita);
        popola(articoli);
    }

    public ElencoOfferta(String idPuntoVendita, int i){

        this.idPuntoVendita=idPuntoVendita;
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        List articoli= ArticoloBuisness.getInstance().getCatalogoInverso(idPuntoVendita);
        popolaInverso(articoli);
    }

    private void popolaInverso(List articoli) {

        int i = 1;
        for (Object articolo : articoli) {
            RigaProdotto rigaProdotto = new RigaProdotto();


            rigaProdotto.update(articolo);
            rigaProdotto.setPuntoVendita(PuntoVenditaBuisness.getInstance().getPuntovendita(idPuntoVendita));
            rigaProdotto.getRemove().setVisible(false);
            rigaProdotto.getRemove().removeAll();
            rigaProdotto.getRemove().setActionCommand("null");
            button= rigaProdotto.getConferma();

            button.setText("Aggiungi");
            button.setActionCommand("nuova_offerta");


            JPanel panel = new JPanel();

            panel.add(rigaProdotto);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);

        }


        JScrollPane scrollPane = new JScrollPane(mainList);
        add(scrollPane, BorderLayout.CENTER);

    }

    private void popola(List articoli) {
        int i = 1;
        GridBagConstraints gbc = new GridBagConstraints();
        for (Object articolo : articoli) {
            RigaProdotto rigaProdotto = new RigaProdotto();


            rigaProdotto.update(articolo);
            rigaProdotto.setPuntoVendita(PuntoVenditaBuisness.getInstance().getPuntovendita(idPuntoVendita));

            JPanel panel = new JPanel();

            panel.add(rigaProdotto);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);




        }

        JButton jButton= new JButton("Aggiungi prodotti all'offerta");
        jButton.setActionCommand("aggiungi_all_offerta");
        jButton.addActionListener(new AmministratoreListener(this));

        gbc.weightx = 1;
        gbc.weighty = i++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(jButton, gbc,0);


        //inserire il bottone
        JScrollPane scrollPane = new JScrollPane(mainList);
        add(scrollPane, BorderLayout.CENTER);


    }

    public String getIdPuntoVendita() {
        return idPuntoVendita;
    }
}
