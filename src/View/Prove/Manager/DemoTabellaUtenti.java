package View.Prove.Manager;

import Dao.Categorie.CategoriaDao;
import Model.Categoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DemoTabellaUtenti {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Manger visualizza utenti");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1200,400);

               TabellaUtenti tabellaUtenti= new TabellaUtenti();




                // Add the JComboBox to the JFrame.
                frame.add(tabellaUtenti, BorderLayout.CENTER);

                // Pack the JFrame and make it visible.
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
