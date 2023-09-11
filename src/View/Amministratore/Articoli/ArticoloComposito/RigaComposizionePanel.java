package View.Amministratore.Articoli.ArticoloComposito;

import Buisness.ArticoloBuisness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RigaComposizionePanel extends JPanel {

    private Object composizione;
    private JLabel nome;
    private JLabel quantitaLabel;
    private JTextField quantita;
    private JButton modifica;
    private JButton elimina;

    public RigaComposizionePanel(Object o, JFrame frame){
        composizione= o;

        nome= new JLabel(ArticoloBuisness.getInstance().getComposizioneArticoloNome(o));
        quantitaLabel= new JLabel("Quantità");
        quantita= new JTextField(5);
        quantita.setText(String.valueOf(ArticoloBuisness.getInstance().getComposizioneQuantita(o)));

        modifica= new JButton("Modifica");
        modifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int q= Integer.parseInt(quantita.getText());

                    ArticoloBuisness.getInstance().setComposizione(o, ArticoloBuisness.getInstance().getComposizioneArticolo(o), q);
                    ArticoloBuisness.getInstance().updateQuantitaComposizione(o, q);

                }catch (NumberFormatException ex) {
                    // L'input non è un numero float valido
                    JOptionPane.showMessageDialog(null, "Inserire una quantita' valida");
                }
            }
        });
        elimina= new JButton("Elimina");
        elimina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArticoloBuisness.getInstance().deleteComposizione(o);
                frame.dispose();

            }
        });


        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.insets= new Insets(10,10,10,10);

        gbc.gridy=0;

        gbc.gridx=0;
        add(nome, gbc);

        gbc.gridx=1;
        add(quantitaLabel, gbc);

        gbc.gridx=2;
        add(quantita, gbc);

        gbc.gridx=3;
        add(modifica, gbc);

        gbc.gridx=4;
        add(elimina, gbc);

    }



}
