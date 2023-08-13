package View.Table;

import View.GridBagLayoutPanel;
import View.Table.ViewModel.RigaCatalogo;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;


public class CatalogoTableModel extends AbstractTableModel {

    public CatalogoTableModel(List<RigaCatalogo> righe) {
        this.righe = righe;
    }

    private final List<RigaCatalogo> righe;


    @Override
    public int getRowCount() {
        System.out.println("Numero di righe " + righe.size());
        return righe.size();

    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RigaCatalogo riga= righe.get(rowIndex);

        if(columnIndex==0){

        return new GridBagLayoutPanel();}
        return null;
    }

    @Override
    public String getColumnName(int columnIndex){

        return "Prodotti";

    }

}
