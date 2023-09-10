package ActionListener;

import Buisness.ArticoloBuisness;
import Buisness.PuntoVenditaBuisness;
import Buisness.SessionManager;
import ViewProveETest.CarrelloPanel;
import ViewProveETest.CatalogoPanel;
import ViewProveETest.GridBagLayoutPanel;
import ViewProveETest.MainClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CatalogoListener implements ActionListener {

    private GridBagLayoutPanel gridBagLayoutPanel;
    List<Object> articoloList;

    public CatalogoListener(GridBagLayoutPanel gridBagLayoutPanel){
        this.gridBagLayoutPanel=gridBagLayoutPanel;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(SessionManager.getSession().get(SessionManager.GUEST)!=null) {
            //caso in cui l'utente non ha fatto il login
            Object[] options = {"login",
                    "signup",
                    "annulla"};
            int n = JOptionPane.showOptionDialog(null,
                    "Per poter fare acquisti deve prima fare il login ",
                    "Attenzione",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[2]);
            System.out.println(n);
            if(n==0){
                new MainClass().getHome().mostraLogin();
            }else if(n==1){
                new MainClass().getHome().mostraRegistrazione();
            }

        }else if(e.getActionCommand().equalsIgnoreCase("+lista")){
            addLista(gridBagLayoutPanel.getCatalogoPanel().getCarrello());//prendere la lista
            }
        else if(e.getActionCommand().equalsIgnoreCase("ordina")){
            //ordian da parte dell' utente
            }else if(e.getActionCommand().equalsIgnoreCase("ordina_manager")){
            ordinaManager();
        }


        }


        private void addLista(List<Object> lista){


            JComboBox<Object> q=new JComboBox<>();
            for(int i = 1; i<= ArticoloBuisness.getInstance().getQuantita(gridBagLayoutPanel.getArticolo()); i++)
                q.addItem(i);

            int input=JOptionPane.showConfirmDialog(null, q, "Inserire quantita'", JOptionPane.YES_NO_OPTION);
            if(input==JOptionPane.YES_OPTION){
                articoloList= lista;
                articoloList.add(gridBagLayoutPanel.getArticolo());
                System.out.println("Si");
                int j=q.getSelectedIndex()+1;

                System.out.println("Quantita " + j);
                for (int x=0;x<j-1;x++) {
                    articoloList.add(gridBagLayoutPanel.getArticolo());
                }

                int y=Integer.parseInt(gridBagLayoutPanel.getBtn8().getText())-j;//valore del prodotto riferito alla quantita
                String idA= ArticoloBuisness.getInstance().geIdArticolo(gridBagLayoutPanel.getArticolo());
                String idPV= PuntoVenditaBuisness.getInstance().findBySessioneUser().getId();

                int w= PuntoVenditaBuisness.getInstance().updateQuantita(idA, idPV, y);
                if(w>0){
                    gridBagLayoutPanel.getCatalogoPanel().Update();
                    gridBagLayoutPanel.getCatalogoPanel().getCarrelloPanel().update();
                }


            }else  if(input==JOptionPane.NO_OPTION){
                System.out.println("No");
            }



        }
    private void ordinaManager() {

        String[] option ={"Si", "No"};
        int q= (gridBagLayoutPanel.getComboBox().getSelectedIndex())+1;
        int x=JOptionPane.showOptionDialog(null, "Vuoi ordinare " +q+" pezzi di " +gridBagLayoutPanel.getBtn2().getText()+"?","Conferma la scelta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0] );

        if(x==0){
            //effettua l'ordine
            String idAricolo= ArticoloBuisness.getInstance().geIdArticolo(gridBagLayoutPanel.getArticolo());
            String idPuntoVendita= PuntoVenditaBuisness.getInstance().findBySessioneManager().getId();
            q=q+Integer.parseInt(gridBagLayoutPanel.getBtn8().getText());
            int y= PuntoVenditaBuisness.getInstance().updateQuantita(idAricolo, idPuntoVendita, q);
            if(y>0){
                JOptionPane.showMessageDialog(null, "Quantità aggiornata");
            }else{
                JOptionPane.showMessageDialog(null, "Quantità non aggiornata");
            }
        }
    }

}

