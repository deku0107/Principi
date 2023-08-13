package View;

import Dao.Prodotti.ProdottoDao;
import Model.Prodotti.Articolo;
import Model.Prodotti.Prodotto;
import View.Table.ViewModel.RigaCatalogo;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogoPanel extends JPanel {

    private JPanel mainList;
    public CatalogoPanel(){

         mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());



        List<Articolo> articoli= ProdottoDao.getInstance().findArticolo();

        int i=1;
        for (Articolo articolo:articoli){
            GridBagLayoutPanel gridBagLayoutPanel=new GridBagLayoutPanel();

            gridBagLayoutPanel.updateData(articolo);

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
        add(scrollPane, BorderLayout.CENTER);

    }
}
