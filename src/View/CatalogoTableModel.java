package View;

import View.ViewModel.RigaCatalogo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class CatalogoTableModel extends AbstractTableModel {

    public CatalogoTableModel(List<RigaCatalogo> righe) {
        this.righe = righe;
    }

    private List<RigaCatalogo> righe = new ArrayList<RigaCatalogo>();


    @Override
    public int getRowCount() {
        return righe.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RigaCatalogo riga= righe.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> riga.getIdProdotto();
            case 1 -> riga.getNomeProdotto();
            case 2 -> riga.getNomeProduttore();
            case 3 -> riga.getNomeCategoria();
            case 4 -> riga.getPrezzo();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int columnIndex){

        return switch (columnIndex) {
            case 0 -> "Id";
            case 1 -> "Nome";
            case 2 -> "Produttore";
            case 3 -> "Categoria";
            case 4 -> "Prezzo";
            default -> null;
        };

    }
}
