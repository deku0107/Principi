package ViewProveETest.Prove.Amministratore.Produttore;


import Buisness.ProduttoreBuisness;


import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class ElencoProduttoriPanel extends JPanel {

    private final JPanel mainList;


    public ElencoProduttoriPanel(){

        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        List produttori;
        produttori= ProduttoreBuisness.getInstance().getProduttori();
        popola(produttori);

    }


    private void popola(List produttori){
        int i=1;
        for (Object o:produttori){
            RigaGestioneProduttorePanel r=new RigaGestioneProduttorePanel();
            r.update(o);
            JPanel panel= new JPanel();

            panel.add(r);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);
        }

        JScrollPane scrollPane= new JScrollPane(mainList);
        add(scrollPane, BorderLayout.CENTER);

    }
}
