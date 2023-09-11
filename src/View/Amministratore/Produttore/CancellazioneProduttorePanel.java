package View.Amministratore.Produttore;

import Buisness.ProduttoreBuisness;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancellazioneProduttorePanel extends JPanel {

    private JComboBox produttori;
    private JButton elimina;

    public CancellazioneProduttorePanel(){

        produttori= ProduttoreBuisness.getInstance().getproduttoreBox();
        elimina= new JButton("Elimina");
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(ProduttoreBuisness.getInstance().getProduttori().size());
                String id= ProduttoreBuisness.getInstance().getProduttori().get(produttori.getSelectedIndex()).getId();
                int input=JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il produttore selezionato?", "Attenzione", JOptionPane.DEFAULT_OPTION);

                if(input==JOptionPane.OK_OPTION){
                input=ProduttoreBuisness.getInstance().removeProduttore(id);
                if(input<0){
                    JOptionPane.showMessageDialog(null,"Errore, produttore non eliminato");
                }else{
                    JOptionPane.showMessageDialog(null,"Produttore eliminato");
                }

                }

            }
        });

        add(produttori);
        add(elimina);
    }
}
