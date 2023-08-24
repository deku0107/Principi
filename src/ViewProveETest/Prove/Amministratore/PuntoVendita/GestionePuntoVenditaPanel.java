package ViewProveETest.Prove.Amministratore.PuntoVendita;

import Buisness.PuntoVenditaBuisness;
import ViewProveETest.Prove.Manager.GestioneClientela.GestioneUtentiPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class GestionePuntoVenditaPanel extends JPanel {

    private final JPanel mainList;

    public GestionePuntoVenditaPanel(){
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        List puntiVendita= PuntoVenditaBuisness.getInstance().getPuntivendita();
        popola(puntiVendita);

    }

    private void popola(List<Object> puntivendita){
        int i=1;
        for(Object o: puntivendita){
            rigaPuntoVenditaPanel rigaPuntoVenditaPanel= new rigaPuntoVenditaPanel();
            rigaPuntoVenditaPanel.updateData(o);

            JPanel panel= new JPanel();

            panel.add(rigaPuntoVenditaPanel);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);
        }

        JScrollPane scrollPane= new JScrollPane(mainList);
        add(scrollPane, BorderLayout.CENTER);

    }
}
