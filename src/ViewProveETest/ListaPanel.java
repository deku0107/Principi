package ViewProveETest;

import Buisness.ArticoloBuisness;
import Buisness.Bridge.PDF.DocumentoListaAcquisto;
import Buisness.Bridge.PDF.PdfBoxAPI;
import Buisness.PuntoVenditaBuisness;
import Buisness.Utente.ListaBuisness;
import ViewProveETest.Prove.UtenteAcquirente.CommentoPanel;


import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class ListaPanel extends JPanel {

    private final List<Object> articoloList;
    private JPanel mainList;
    private JButton paga;
    private JButton addLista;
    public ListaPanel(CarrelloPanel carrelloPanel) {

        articoloList= carrelloPanel.getArticoloList();

        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        int i=1;
        for (Object o: articoloList){
            GridBagLayoutPanel gridBagLayoutPanel=new GridBagLayoutPanel(null);


            gridBagLayoutPanel.updateData(o);

            gridBagLayoutPanel.getBtn6().setActionCommand("");
            gridBagLayoutPanel.getBtn6().setVisible(false);

            gridBagLayoutPanel.getBtn7().setActionCommand("");
            gridBagLayoutPanel.getBtn7().setVisible(false);
            gridBagLayoutPanel.remove(gridBagLayoutPanel.getBtn8());

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

        Home.getInstance().getCentro().add(scrollPane, BorderLayout.CENTER);



        paga= new JButton("Paga ora");
        paga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String listNome= JOptionPane.showInputDialog("Inserire il nome della lista");
                if (listNome==null|| !listNome.equals("")){

                    if (ListaBuisness.getInstance().checkNome(listNome)){
                        ListaBuisness.getInstance().add(listNome, articoloList, true);

                        JOptionPane.showMessageDialog(null, "Lista creata e pagata, controlla le email");
                        Home.getInstance().aggiornaMenuPulsanti();
                    }else{
                        JOptionPane.showMessageDialog(null, "Nome utilizzato, sceglierne un altro");
                    }
                }
            }
        });
        addLista= new JButton("Aggiungi ad una lista");
        addLista.setActionCommand("add_list");
        addLista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] option ={"Crea nuova lista ", "Aggiungi ad una lista d'acquisto esistente", "Annulla"};

                int x=JOptionPane.showOptionDialog(null, "Cosa vuoi fare?", "Scegli", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

                if(x==0){

                    String listNome= JOptionPane.showInputDialog("Inserire il nome della lista");
                    if (listNome==null|| !listNome.equals("")){

                        if (ListaBuisness.getInstance().checkNome(listNome)){
                            ListaBuisness.getInstance().add(listNome, articoloList);
                            JOptionPane.showMessageDialog(null, "Lista creata, controlla le email");
                            Home.getInstance().aggiornaMenuPulsanti();
                        }else{
                            JOptionPane.showMessageDialog(null, "Nome utilizzato, sceglierne un altro");
                        }
                    }


                }else if(x==1){
                    JComboBox<String> jComboBox= ListaBuisness.getInstance().getComboBox();
                    int y=JOptionPane.showConfirmDialog(null, jComboBox, "Scegli", JOptionPane.YES_NO_OPTION);
                    if(y==JOptionPane.YES_OPTION){
                        ListaBuisness.getInstance().updateLista(articoloList, jComboBox.getSelectedIndex());
                        JOptionPane.showMessageDialog(null, "Lista aggiornata");
                        Home.getInstance().aggiornaMenuPulsanti();
                    }

                }

            }
        });


        Home.getInstance().getNord().add(paga);
        Home.getInstance().getNord().add(addLista);

        JButton back= Home.getInstance().getBack();
        back.setActionCommand("indietro_da_carrello");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equalsIgnoreCase("indietro_da_carrello")){
                    String idPV=PuntoVenditaBuisness.getInstance().findBySessioneUser().getId();
                    if (articoloList.size()!=0){
                        String idA;
                        String tmp = ArticoloBuisness.getInstance().geIdArticolo(articoloList.get(0));
                        for (Object o : articoloList) {

                            idA = ArticoloBuisness.getInstance().geIdArticolo(o);
                            PuntoVenditaBuisness.getInstance().updateQuantita(idA, idPV);
                            if (!Objects.equals(tmp, idA)) {
                                PuntoVenditaBuisness.getInstance().removeQuantita(tmp, idPV);
                                tmp = idA;
                            }

                        }

                        PuntoVenditaBuisness.getInstance().removeQuantita(tmp, idPV);
                    }
                    back.setActionCommand("indietro");
                    Home.getInstance().aggiornaMenuPulsanti();
                }
            }
        });


        Home.getInstance().repaint();
        Home.getInstance().validate();
    }


}
