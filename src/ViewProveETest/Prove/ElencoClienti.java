package ViewProveETest.Prove;

import Dao.Utenti.UtenteAcquirenteDao;
import Model.Utenti.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ElencoClienti {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("JComboBox Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create the JComboBox.
                JComboBox comboBox = new JComboBox();
                List<Utente> managers= UtenteAcquirenteDao.getInstance().findAll();
                System.out.println("Dimensione categorie "+managers.size());

                for(Utente manager: managers){
                    comboBox.addItem(manager.getNome());

                }
                comboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(comboBox.getSelectedIndex());
                        //ritorna il numero dell'elemento selezionato
                        //che corrisponde al numero dell'elemnto nell'array
                    }
                });


                // Add the JComboBox to the JFrame.
                frame.add(comboBox, BorderLayout.CENTER);

                // Pack the JFrame and make it visible.
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
