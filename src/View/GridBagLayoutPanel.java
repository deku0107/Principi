package View;

import javax.swing.*;
import java.awt.*;

public class GridBagLayoutPanel extends JPanel {

    public GridBagLayoutPanel(){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;

        JButton btn1= new JButton("1");
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=3;
        gbc.gridheight=4;
        gbc.fill=GridBagConstraints.BOTH;
        add(btn1, gbc);

        JButton btn2= new JButton("2");
        gbc.gridx=4;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn2, gbc);

        JButton btn3= new JButton("3");
        gbc.gridx=5;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn3, gbc);

        JButton btn4= new JButton("4");
        gbc.gridx=4;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn4, gbc);

        JButton btn5= new JButton("5");
        gbc.gridx=4;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn5, gbc);

        JButton btn6= new JButton("6");
        gbc.gridx=4;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn6, gbc);

        JButton btn7= new JButton("7");
        gbc.gridx=5;
        gbc.gridy=4;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        add(btn7, gbc);


    }
}
