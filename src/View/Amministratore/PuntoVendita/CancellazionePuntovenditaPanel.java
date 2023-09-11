package View.Amministratore.PuntoVendita;

import Buisness.PuntoVenditaBuisness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancellazionePuntovenditaPanel extends JPanel {

    private JComboBox puntiVendita;
    private JButton elimina;

    public CancellazionePuntovenditaPanel(){

        puntiVendita = PuntoVenditaBuisness.getInstance().getPuntoVenditaBox();
        elimina= new JButton("Elimina");
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id= PuntoVenditaBuisness.getInstance().getPuntivendita().get(puntiVendita.getSelectedIndex()).getId();
                int input=JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il Punto vendita selezionato?", "Attenzione", JOptionPane.DEFAULT_OPTION);

                if(input==JOptionPane.OK_OPTION){

                    System.out.println("Id " + id);
                    input=PuntoVenditaBuisness.getInstance().remove(id);
                    if(input<0){
                        JOptionPane.showMessageDialog(null,"Errore, punto vendita non eliminato");
                    }else{
                        JOptionPane.showMessageDialog(null,"Punto vendita eliminato");
                    }

                }

            }
        });

        add(puntiVendita);
        add(elimina);
    }
}
