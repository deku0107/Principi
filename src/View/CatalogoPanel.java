package View;

import View.ViewModel.RigaCatalogo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogoPanel extends JPanel {

    public CatalogoPanel(){


        setLayout(new BorderLayout());
        String[][] dati= new String[3][5];
        String[] nomiColonne= new String[] {"Colonna 1", "Colonna 2", "Colonna 3", "Colonna 4", "Colonna 5"};
        //JTable tabella= new JTable(dati, nomiColonne);

        List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();

        //prendere i dati dal dao e fare righe.add(riga) per ogni riga del dao

        CatalogoTableModel catalogoTableModel= new CatalogoTableModel(righe);


        JTable tabella = new JTable(catalogoTableModel);
        JScrollPane scrollPane= new JScrollPane(tabella);
        add(scrollPane, BorderLayout.CENTER);

    }
}
