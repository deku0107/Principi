package ActionListener;

import Buisness.ArticoloBuisness;
import Buisness.PuntoVenditaBuisness;
import Buisness.SessionManager;
import ViewProveETest.CarrelloPanel;
import ViewProveETest.GridBagLayoutPanel;
import ViewProveETest.MainClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CatalogoListener implements ActionListener {

    private GridBagLayoutPanel gridBagLayoutPanel;
    List<Object> articoloList= (List<Object>) SessionManager.getSession().get(SessionManager.CARRELLO);

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

            articoloList.add(gridBagLayoutPanel.getArticolo());
            System.out.println(articoloList.size());
            CarrelloPanel.getInstance().update();
            }else if(e.getActionCommand().equalsIgnoreCase("ordina")){
            //ordian da parte dell' utente
            }else if(e.getActionCommand().equalsIgnoreCase("ordina_manager")){
            ordinaManager();
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

