package ViewProveETest.Prove.Amministratore.Manager;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.Utente.UtenteBusiness;

import javax.swing.*;

public class CancellazioneManagerPanel extends JPanel {

    private JComboBox manager;
    private JButton conferma;

    public CancellazioneManagerPanel(){

        manager= UtenteBusiness.getInstance().getManagerBox();
        conferma= new JButton("Elimina");
        conferma.setActionCommand("elimina_manager");
        conferma.addActionListener(new AmministratoreListener(this));

        add(manager);
        add(conferma);

    }

    public JComboBox getManager() {
        return manager;
    }

    public void setManager(JComboBox manager) {
        this.manager = manager;
    }

    public JButton getConferma() {
        return conferma;
    }

    public void setConferma(JButton conferma) {
        this.conferma = conferma;
    }
}
