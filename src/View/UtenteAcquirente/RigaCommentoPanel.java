package View.UtenteAcquirente;

import Buisness.ArticoloBuisness;
import Buisness.Utente.CommentoBuisness;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RigaCommentoPanel extends JPanel {

    private final Object articolo;

    private JLabel immagine;
    private JLabel nome;
    private JButton commenta;
    private RigaCommentoPanel rigaCommentoPanel;

    public RigaCommentoPanel(Object a){

        rigaCommentoPanel= this;
        articolo=a;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.insets= new Insets(20,20,20,20);
        nome= new JLabel(ArticoloBuisness.getInstance().getNome(articolo));
        commenta= new JButton("Commenta");
        commenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewWindow(articolo, rigaCommentoPanel );
            }
        });

        immagine= new JLabel("Immagine");

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=2;
        add(immagine, gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridheight=1;
        add(nome, gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridheight=1;
        add(commenta, gbc);


    }

    public static void createNewWindow(Object articolo, RigaCommentoPanel r) {
        JFrame newFrame = new JFrame("Commenta");
        newFrame.setSize(400, 450);

        // Aggiungi un pannello per visualizzare i dati nella nuova finestra
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crea un JTextArea per la descrizione
        JTextArea descrizioneTextArea = new JTextArea(5, 20);
        descrizioneTextArea.setLineWrap(true);
        descrizioneTextArea.setWrapStyleWord(true);



        // Crea una JComboBox per il voto da 1 a 5
        Integer[] votoOptions = {1, 2, 3, 4, 5};
        JComboBox votoComboBox = new JComboBox<>(votoOptions);
        JButton closeButton = new JButton("Commenta");
        boolean check= CommentoBuisness.getInstance().isCommentatoUtente(articolo);
        if (check){
            closeButton.setText("Modifica commento");
            descrizioneTextArea.setText(CommentoBuisness.getInstance().getCommentatoUtente(articolo));
            votoComboBox.setSelectedIndex(CommentoBuisness.getInstance().getCommentatoUtenteValutazione(articolo)-1);

        }

        // Aggiungi componenti al pannello
        panel.add(new JScrollPane(descrizioneTextArea), BorderLayout.CENTER);
        panel.add(votoComboBox, BorderLayout.NORTH);
        newFrame.add(panel);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i= Arrays.stream(votoOptions).toList().get(votoComboBox.getSelectedIndex());
                int y= CommentoBuisness.getInstance().addCommento(descrizioneTextArea.getText(), i,articolo  );
                if (y>=0){
                    JOptionPane.showMessageDialog(null, "Commento inserito");
                }
            }
        });

        panel.add(closeButton, BorderLayout.SOUTH);

        newFrame.setVisible(true);
    }

    public Object getArticolo(){
        return articolo;

    }
}
