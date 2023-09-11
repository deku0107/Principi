package View.Amministratore.Categoria;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.CategoriaBuisness;

import javax.swing.*;

public class CategoriaPanel extends JPanel {

    private JComboBox comboBox;
    private JButton button;

    public CategoriaPanel(){
        comboBox= CategoriaBuisness.getInstance().getCategorieBox();
        button= new JButton("Conferma");
        button.setActionCommand("modifica_categoria");
        button.addActionListener(new AmministratoreListener(this));
        add(comboBox);
        add(button);


    }

    public CategoriaPanel(int i){
        if (i==1) {
            comboBox = CategoriaBuisness.getInstance().getCategorieBox();
            button = new JButton("Conferma");
            button.setActionCommand("elimina_categoria");
            button.addActionListener(new AmministratoreListener(this));
            add(comboBox);
            add(button);
        }else {
            new CategoriaPanel();
        }


    }

    public JComboBox getComboBox() {
        return comboBox;
    }
}
