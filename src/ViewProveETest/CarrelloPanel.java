package ViewProveETest;

import Buisness.ArticoloBuisness;


import javax.swing.*;
import java.awt.*;
import java.util.List;



public class CarrelloPanel extends JPanel {

    private static List articoloList;
    private static float totale;
    private  JTextArea elenco;
    private String lista;
    private JPanel panel;
    private static JLabel sud;
    private  JScrollPane jScrollPane;
    private CatalogoPanel catalogoPanel;

    public CarrelloPanel(CatalogoPanel catalogoPanel){


        this.catalogoPanel= catalogoPanel;
        setLayout(new BorderLayout());
        sud=new JLabel();
        panel= new JPanel();
        add(new JLabel("Carrello"), BorderLayout.NORTH);
        add(sud, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        update();

        repaint();
        validate();
    }

    public void update(){

        articoloList = catalogoPanel.getCarrello();

        System.out.println("Update articolo list size" + articoloList.size());



        panel.removeAll();
        totale=0F;
        lista="";
        elenco= new JTextArea();
        elenco.setEditable(false);



        for(Object articolo: articoloList){

            lista+=(ArticoloBuisness.getInstance().getNome(articolo)+"\n");

            totale+=ArticoloBuisness.getInstance().getPrezzo(articolo);

        }



        elenco.setPreferredSize(new Dimension(200,400));
        elenco.setText(lista);
        elenco.setVisible(true);


        jScrollPane= new JScrollPane(elenco);


        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        panel.add(jScrollPane);

        sud.removeAll();
        sud.setText("Totale da pagare: " + totale + " Euro");

        repaint();
        validate();

    }

    public List<Object> getArticoloList() {
        return articoloList;
    }

    public void setArticoloList(List<Object> articoloList) {
        this.articoloList = articoloList;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
