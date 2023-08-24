package ViewProveETest;

import Buisness.ArticoloBuisness;
import Buisness.SessionManager;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class CatalogoPanel extends JPanel {

    private final JPanel mainList;

    public CatalogoPanel(){

        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        List articoli;
        if(SessionManager.getSession().get(SessionManager.LOGGED_USER)!=null){
            articoli= ArticoloBuisness.getInstance().getCatalogo();
            popola(articoli);
        }else if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null){
            articoli=ArticoloBuisness.getInstance().getCatalogoManager();
            popola(articoli);

        }else
        {
            articoli = ArticoloBuisness.getInstance().getProdotti();
            popola(articoli);
        }



    }

    public CatalogoPanel(List aricoli){
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());
        popola(aricoli);

    }


    private void popola(List<Object> articoli){
        int i=1;
        for (Object articolo:articoli){
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
