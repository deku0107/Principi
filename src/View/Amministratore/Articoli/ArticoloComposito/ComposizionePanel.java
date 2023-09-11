package View.Amministratore.Articoli.ArticoloComposito;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class ComposizionePanel extends JPanel {

    private  JPanel mainList;
    private List composizione;
    private  JFrame frame;

    public ComposizionePanel(List c, JFrame frame){
        this.frame= frame;
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());
        composizione=c;
        popola();

    }

    private void popola() {

        int i=1;
        System.out.println("Dimensione composizione1 "+composizione.size());
        for (Object c:composizione){
            System.out.println("Dimensione composizione "+composizione.size());
            RigaComposizionePanel rigaComposizionePanel= new RigaComposizionePanel(c, frame);

            JPanel panel = new JPanel();

            panel.add(rigaComposizionePanel);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);
        }

        JScrollPane scrollPane= new JScrollPane(mainList);
        add(scrollPane);

        repaint();
        validate();


    }

    public void update(List comp){

        removeAll();
        mainList.removeAll();
        mainList= new JPanel(new GridBagLayout());


        int i=1;

        for (Object c:comp){
            System.out.println("Dimensione composizione "+composizione.size());
            RigaComposizionePanel rigaComposizionePanel= new RigaComposizionePanel(c, frame);

            JPanel panel = new JPanel();

            panel.add(rigaComposizionePanel);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);
        }

        JScrollPane scrollPane= new JScrollPane(mainList);
        add(scrollPane);

        repaint();
        validate();

    }
}
