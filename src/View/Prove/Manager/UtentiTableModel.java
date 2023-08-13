package View.Prove.Manager;

import Model.Utenti.UtenteAcquirente;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UtentiTableModel  extends AbstractTableModel {

    private List<RigaUtente> rigaUtente;

    public UtentiTableModel(List<RigaUtente> rigaUtente) {
        this.rigaUtente = rigaUtente;
    }

    @Override
    public int getRowCount() {
        return rigaUtente.size();
    }

    @Override
    public int getColumnCount() {

        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RigaUtente rigaUtente=this.rigaUtente.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> rigaUtente.getIdUtente();
            case 1 -> rigaUtente.getUsername();
            case 2 -> rigaUtente.getNome();
            case 3 -> rigaUtente.getCognome();
            case 4 -> rigaUtente.getEmail();
            case 5 -> rigaUtente.getTelefono();
            case 6 -> rigaUtente.getStato();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value,int rowIndex, int columnIndex) {
        RigaUtente rigaUtente=this.rigaUtente.get(rowIndex);

        switch (columnIndex) {
            case 0 -> rigaUtente.setIdUtente(Integer.parseInt(value.toString()));
            case 1 -> rigaUtente.setUsername(value.toString());
            case 2 -> rigaUtente.setNome(value.toString());
            case 3 -> rigaUtente.setCognome(value.toString());
            case 4 -> rigaUtente.setEmail(value.toString());
            case 5 -> rigaUtente.setTelefono(value.toString());
            case 6 -> rigaUtente.setStato((UtenteAcquirente.Stato)value);

        }
    }

    @Override
    public String getColumnName(int columnIndex){

         switch (columnIndex) {
             case 0: return  "Id utente";
             case 1: return  "Username";
             case 2: return  "Nome";
             case 3: return  "Cognome";
             case 4: return  "Email";
             case 5: return "Telefono";
             case 6: return  "Stato";
             default: return null;
    }
}

@Override
    public boolean isCellEditable(int rowIndex, int columnIndex){

    return columnIndex==6;
}

@Override
    public Class getColumnClass(int columnIndex){

        return Object.class;

}
}
