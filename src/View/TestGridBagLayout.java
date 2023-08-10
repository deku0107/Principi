package View;

import javax.swing.*;

public class TestGridBagLayout {
    public static void main(String[] args) {
        JFrame frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 400);
        frame.add(new GridBagLayoutPanel());
        frame.setVisible(true);
    }
}
