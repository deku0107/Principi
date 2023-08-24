package ViewProveETest;

import Buisness.SessionManager;
import Model.Prodotti.Articolo;


import javax.swing.*;
import java.awt.*;
import java.util.List;



public class CarrelloPanel extends JPanel {

    private static List<Articolo> articoloList;
    private static float totale;
    private static JPanel panel;
    private static JLabel sud;
    private static CarrelloPanel instance =new CarrelloPanel();

    public static CarrelloPanel getInstance(){return instance;}

    public CarrelloPanel(){


        setLayout(new BorderLayout());
        sud=new JLabel();
        panel= new JPanel(new GridLayout(0,1));
        add(new JLabel("Carrello"), BorderLayout.NORTH);
        add(sud, BorderLayout.SOUTH);
        update();

        repaint();
        validate();
    }

    public void update(){

        articoloList= (List<Articolo>) SessionManager.getSession().get(SessionManager.CARRELLO);


        panel.removeAll();
        totale=0F;


        int i=0;
        for(Articolo articolo:articoloList){

            panel.add(new JLabel(articolo.getNome()), i);
            i++;
            totale+=articolo.getPrezzo();
            System.out.println("I " + i);
        }

        System.out.println("Totale "+ totale);

        panel.setPreferredSize(new Dimension(200,400));
        JScrollPane jScrollPane= new JScrollPane(panel);

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(jScrollPane, BorderLayout.CENTER);

        System.out.println("Totale finale " + totale);
        sud.removeAll();
        sud.setText("Totale da pagare: " + totale + " Euro");

    }

    public List<Articolo> getArticoloList() {
        return articoloList;
    }

    public void setArticoloList(List<Articolo> articoloList) {
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
