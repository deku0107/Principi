package ViewProveETest;

import ActionListener.CatalogoListener;
import Buisness.ArticoloBuisness;
import Buisness.SessionManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GridBagLayoutPanel extends JPanel {

    private Object articolo;

    private JLabel btn1;
    private JLabel btn2;
    private JLabel btn3;
    private JLabel btn4;
    private JTextArea btn5;
    private JButton btn6;
    private JButton btn7;
    private JLabel btn8;
    JComboBox<Integer> comboBox;



    public void updateData(Object articolo){
        this.articolo=articolo;
        ArticoloBuisness a = ArticoloBuisness.getInstance();

        btn2.setText(a.getNome(articolo));
        btn3.setText(a.getCategoria(articolo).getNome());
        btn4.setText("Euro: " + a.getPrezzo(articolo));
        btn5.setText(a.getDescrizione(articolo));
        btn8.setText(String.valueOf(a.getQuantita(articolo)));

    }

    public GridBagLayoutPanel(){



        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;

        gbc.insets= new Insets(20,20,20,20);
        btn1= new JLabel("Immagine"); //prendo i dati dalla session
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=3;
        gbc.gridheight=4;
        gbc.fill=GridBagConstraints.BOTH;
        add(btn1, gbc);

        btn2= new JLabel("Nome");
        gbc.gridx=4;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn2, gbc);

        JLabel jLabel= new JLabel("Quantità");
        gbc.gridx=5;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(jLabel, gbc);

        btn8= new JLabel("Quantita");
        gbc.gridx=6;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn8, gbc);

        btn3= new JLabel("categoria");
        gbc.gridx=5;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn3, gbc);

        btn4= new JLabel("Prezzo");
        gbc.gridx=4;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn4, gbc);

        btn5= new JTextArea("Descrizione");
        btn5.setEditable(false);
        btn5.setSize(20,20);
        gbc.gridx=4;
        gbc.gridy=3;
        gbc.gridwidth=3;
        gbc.gridheight=2;
        add(btn5, gbc);

        ActionListener actionListener= new CatalogoListener(this);
        btn6= new JButton("+Lista");
        btn6.setActionCommand("+Lista");
        btn6.addActionListener(actionListener);
        gbc.gridx=4;
        gbc.gridy=5;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn6, gbc);

        btn7= new JButton("Ordina");
        btn7.addActionListener(actionListener);
        btn7.setActionCommand("Ordina");

        gbc.gridx=5;
        gbc.gridy=5;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn7, gbc);

        if(SessionManager.getSession().get(SessionManager.GUEST)!=null){
            btn6.setVisible(false);
            btn6.removeActionListener(actionListener);
            btn7.setVisible(false);
            btn7.removeActionListener(actionListener);

        }
        if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null){
            btn6.setVisible(false);
            btn6.removeActionListener(actionListener);
            btn7.setActionCommand("ordina_manager");

            JLabel selezionaQuantita= new JLabel("Quantità ordine");
            gbc.gridx=5;
            gbc.gridy=2;
            gbc.gridwidth=1;
            gbc.gridheight=1;
            add(selezionaQuantita, gbc);

            comboBox= new JComboBox<>();
            for(int i=0;i<100;i++)
                comboBox.addItem(i+1);

            gbc.gridx=6;
            gbc.gridy=2;
            gbc.gridwidth=1;
            gbc.gridheight=1;
            add(comboBox, gbc);

        }

       // if(SessionManager.getSession().get(SessionManager.LOGGED_ADMIN)!=null){}
    }




    public JLabel getBtn1() {
        return btn1;
    }

    public void setBtn1(JLabel btn1) {
        this.btn1 = btn1;
    }

    public JLabel getBtn2() {
        return btn2;
    }

    public void setBtn2(JLabel btn2) {
        this.btn2 = btn2;
    }

    public JLabel getBtn3() {
        return btn3;
    }

    public void setBtn3(JLabel btn3) {
        this.btn3 = btn3;
    }

    public JLabel getBtn4() {
        return btn4;
    }

    public void setBtn4(JLabel btn4) {
        this.btn4 = btn4;
    }

    public JTextArea getBtn5() {
        return btn5;
    }

    public void setBtn5(JTextArea btn5) {
        this.btn5 = btn5;
    }

    public JButton getBtn6() {
        return btn6;
    }

    public void setBtn6(JButton btn6) {
        this.btn6 = btn6;
    }

    public JButton getBtn7() {
        return btn7;
    }

    public void setBtn7(JButton btn7) {
        this.btn7 = btn7;
    }

    public Object getArticolo() {
        return articolo;
    }

    public void setArticolo(Object articolo) {
        this.articolo = articolo;
    }

    public JLabel getBtn8() {
        return btn8;
    }

    public void setBtn8(JLabel btn8) {
        this.btn8 = btn8;
    }

    public JComboBox<Integer> getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox<Integer> comboBox) {
        this.comboBox = comboBox;
    }
}