package View.Manager.Commenti;

import Buisness.ArticoloBuisness;
import Buisness.Immagini.ImageDisplay;
import Buisness.Utente.CommentoBuisness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RigaRispostaPanel extends JPanel {
    private Object commento;

    private JLabel immagine;
    private JLabel nomeProdotto;
    private JLabel nomeUtente;
    private JLabel valutazione;
    private JTextArea commentoText;
    private JTextArea risposta;
    private JButton rispondi;
    private JScrollPane scrollPane;

    public RigaRispostaPanel(Object c){
        this.commento= c;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.insets= new Insets(20,20,20,20);

        nomeProdotto= new JLabel(CommentoBuisness.getInstance().getNomeArticoloCommentato(commento));
        nomeUtente=new JLabel(CommentoBuisness.getInstance().getNomeUtente(commento));
        commentoText= new JTextArea();
        commentoText.setText(CommentoBuisness.getInstance().getTesto(commento));
        commentoText.setEditable(false);

        risposta= new JTextArea();

        immagine= new JLabel();
        new ImageDisplay(immagine, ArticoloBuisness.getInstance().getImmaginePath(CommentoBuisness.getInstance().getNomeArticoloCommentato(commento)));
        valutazione= new JLabel("Valutazione " +CommentoBuisness.getInstance().getValutazione(commento));

        rispondi = new JButton("Rispondi");
        if (CommentoBuisness.getInstance().isRisposto(commento)) {
            risposta.setText(CommentoBuisness.getInstance().getRisposta(commento));
            rispondi.setText("Modifica");
        }


        rispondi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int c =CommentoBuisness.getInstance().risposta(risposta.getText(), commento);
                if(c>=0){
                    JOptionPane.showMessageDialog(null, "Risposta effettuata");
                }
            }
        });


        gbc.gridy=0;
        gbc.gridx=0;
        gbc.gridheight=2;

        add(immagine, gbc);

        gbc.gridy=0;
        gbc.gridx=1;
        gbc.gridheight=1;

        add(nomeProdotto, gbc);

        gbc.gridy=0;
        gbc.gridx=2;
        add(nomeUtente, gbc);

        gbc.gridy=1;
        gbc.gridx=2;

        scrollPane= new JScrollPane();
        scrollPane.add(commentoText);
        add(scrollPane, gbc);

        gbc.gridy=1;
        gbc.gridx=1;

        add(valutazione, gbc);

        gbc.gridy=0;
        gbc.gridx=2;
        gbc.gridheight=2;

        add(risposta, gbc);

        gbc.gridy=2;
        gbc.gridx=2;
        gbc.gridheight=1;
        add(rispondi, gbc);



    }
}
