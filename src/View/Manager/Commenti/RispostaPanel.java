package View.Manager.Commenti;

import Buisness.Utente.CommentoBuisness;
import Buisness.Utente.ListaBuisness;
import View.Home;
import View.UtenteAcquirente.RigaCommentoPanel;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class RispostaPanel extends JPanel {

    private JPanel mainList;
    private List commenti;

    public RispostaPanel() {
        mainList = new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        commenti = CommentoBuisness.getInstance().getCommenti();

        if(commenti==null){
            JOptionPane.showMessageDialog(null, "Nessun commento da visualizzare");
            Home.getInstance().aggiornaMenuPulsanti();
        }
        else{
            Home.getInstance().getCentro().removeAll();
            Home.getInstance().getCentro().setLayout(new GridLayout(1, 1));
            int i = 1;
            for (Object commento : commenti) {
                RigaRispostaPanel gridBagLayoutPanel = new RigaRispostaPanel(commento);

                JPanel panel = new JPanel();

                panel.add(gridBagLayoutPanel);
                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.weighty = i++;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(panel, gbc, 0);
            }

            JScrollPane scrollPane = new JScrollPane(mainList);
            Home.getInstance().getCentro().add(scrollPane, BorderLayout.CENTER);

            Home.getInstance().repaint();
            Home.getInstance().validate();
        }
    }
}