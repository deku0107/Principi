package View;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame finestra = new JFrame("Prima finestra");

        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        Container c = finestra.getContentPane();
        c.add(new Home());

        finestra.setVisible(true);


    }
}
