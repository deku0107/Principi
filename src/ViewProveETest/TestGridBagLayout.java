package ViewProveETest;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestGridBagLayout {
    public static void main(String[] args) {
        JFrame frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 400);




        List<JPanel> panels = new ArrayList<>();
        for (int i=0;i<5;i++){
            JPanel panel= new JPanel(new GridBagLayout());
            GridBagLayoutPanel gridBagLayoutPanel= new GridBagLayoutPanel();
            GridBagConstraints gbc= new GridBagConstraints();

            gbc.insets= new Insets(20,20,20,20);
            gbc.gridx=1;
            gbc.gridy=1;
            gbc.fill=GridBagConstraints.BOTH;
            panel.add(gridBagLayoutPanel, gbc);
            panels.add(panel);

        }
        JList list=new JList(panels.toArray());
        JScrollPane jScrollPane= new JScrollPane(list);



        frame.add(jScrollPane);
        frame.setVisible(true);
    }
}
