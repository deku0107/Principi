package ViewProveETest.Prove.Manager.GestioneClientela;

import Buisness.Bridge.Mail.InvioEmail;
import Buisness.Bridge.Mail.MailAPI;
import Buisness.Bridge.Mail.MailHelper;
import Buisness.Utente.UtenteBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class invioEmailPanel extends JPanel {
    private JTextField fromTextField;
    private JTextField toTextField;
    private final JTextField subjectTextField;
    private final JTextField bodyTextField;
    private final JButton sendButton;

    public invioEmailPanel(){
        super(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("From:"));
        inputPanel.add(fromTextField = new JTextField());
        inputPanel.add(new JLabel("To:"));
        inputPanel.add(toTextField = new JTextField());
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(subjectTextField = new JTextField());
        inputPanel.add(new JLabel("Body:"));
        inputPanel.add(bodyTextField = new JTextField());

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> email= UtenteBusiness.getInstance().getEmailClienti();
                for(String s: email) {
                    System.out.println(s);
                    InvioEmail invioEmail= new InvioEmail(new MailAPI());
                    invioEmail.invia(s, subjectTextField.getText(), bodyTextField.getText(), null);
                }
            }
        });

        add(inputPanel, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);

    }

}
