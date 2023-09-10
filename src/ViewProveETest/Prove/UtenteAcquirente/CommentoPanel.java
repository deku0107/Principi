package ViewProveETest.Prove.UtenteAcquirente;


import Buisness.Utente.ListaBuisness;
import ViewProveETest.Home;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CommentoPanel extends JPanel {
    private JTextArea descrizioneTextArea;
    private JComboBox<Integer> votoComboBox;



    private  JPanel mainList;
    private List articoli;

    public CommentoPanel() {
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        articoli= ListaBuisness.getInstance().getSingleArticolo();

        Home.getInstance().getCentro().removeAll();
        Home.getInstance().getCentro().setLayout(new GridLayout(1,1));
        int i=1;
        for (Object articolo:articoli){
            RigaCommentoPanel gridBagLayoutPanel= new RigaCommentoPanel(articolo);

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

        JScrollPane scrollPane= new JScrollPane(mainList);
        Home.getInstance().getCentro().add(scrollPane, BorderLayout.CENTER);

        Home.getInstance().repaint();
        Home.getInstance().validate();


    }

    public String getDescrizione() {
        return descrizioneTextArea.getText();
    }

    public int getVoto() {
        return (int) votoComboBox.getSelectedItem();
    }



    public static void createNewWindow() {
        JFrame newFrame = new JFrame("Nuova Finestra");
        newFrame.setSize(300, 150);

        // Aggiungi un pannello per visualizzare i dati nella nuova finestra
        JPanel panel = new JPanel();
        panel.add(new CommentoPanel());
        newFrame.add(panel);

        JButton closeButton = new JButton("Chiudi");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose(); // Chiudi la nuova finestra
            }
        });

        panel.add(closeButton);

        newFrame.setVisible(true);
    }

}

/*
 // Imposta il layout del pannello
        setLayout(new BorderLayout());

        // Crea un JTextArea per la descrizione
        descrizioneTextArea = new JTextArea(5, 20);
        descrizioneTextArea.setLineWrap(true);
        descrizioneTextArea.setWrapStyleWord(true);

        // Crea una JComboBox per il voto da 1 a 5
        Integer[] votoOptions = {1, 2, 3, 4, 5};
        votoComboBox = new JComboBox<>(votoOptions);

        // Aggiungi componenti al pannello
        add(new JScrollPane(descrizioneTextArea), BorderLayout.CENTER);
        add(votoComboBox, BorderLayout.SOUTH);*/