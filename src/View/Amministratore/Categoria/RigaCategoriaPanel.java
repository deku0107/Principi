package View.Amministratore.Categoria;

import ActionListener.AmministratoreListener.AmministratoreListener;
import Buisness.CategoriaBuisness;

import javax.swing.*;
import java.awt.*;

public class RigaCategoriaPanel extends JPanel {

    private JLabel nome;
    private JTextField nomeField;
    private JLabel descrizione;
    private JTextArea descrizioneField;
    private JLabel categoria;
    private JComboBox categoriaBox;
    private JButton button;
    private final int COLUMNS=10;

    private Object cat;

    public RigaCategoriaPanel(){

        setLayout(new GridBagLayout());

        nome= new JLabel("Nome:");
        nomeField= new JTextField(COLUMNS);
        descrizione= new JLabel("Descrizione:");
        JScrollPane jScrollPane= new JScrollPane();
        descrizioneField= new JTextArea();
        jScrollPane.add(descrizioneField);
        categoria= new JLabel("Categoria:");
        categoriaBox= CategoriaBuisness.getInstance().getCategorieBox();
        button= new JButton("Conferma");
        button.setActionCommand("inserimento_categoria" );
        button.addActionListener(new AmministratoreListener(this));


        GridBagConstraints constraints = new GridBagConstraints();


        constraints.insets= new Insets(20,20,20,20);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        add(nome, constraints);

        constraints.gridy = 1;
        add(nomeField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(descrizione, constraints);

        constraints.gridy = 1;
        jScrollPane.setSize(10,5);
        add(jScrollPane, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(categoria, constraints);

        constraints.gridy = 1;
        add(categoriaBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(button, constraints);


    }

    public void updateData(Object categoria){

        this.cat= categoria;
        CategoriaBuisness c= new CategoriaBuisness();

        nomeField.setText(c.getNome(cat));
        descrizioneField.setText(c.getDescrizione(cat));
        this.categoriaBox.setSelectedIndex(CategoriaBuisness.getInstance().getSelectedIndex(cat));
        button.setActionCommand("modifica_c");

    }

    public JLabel getNome() {
        return nome;
    }

    public void setNome(JLabel nome) {
        this.nome = nome;
    }

    public JTextField getNomeField() {
        return nomeField;
    }

    public void setNomeField(JTextField nomeField) {
        this.nomeField = nomeField;
    }

    public JLabel getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(JLabel descrizione) {
        this.descrizione = descrizione;
    }

    public JTextArea getDescrizioneField() {
        return descrizioneField;
    }

    public void setDescrizioneField(JTextArea descrizioneField) {
        this.descrizioneField = descrizioneField;
    }

    public JComboBox getCategoria() {
        return categoriaBox;
    }

    public void setCategoria(JComboBox categoria) {
        this.categoriaBox = categoria;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public Object getCat() {
        return cat;
    }

    public void setCat(Object cat) {
        this.cat = cat;
    }
}
