package View.Amministratore.Articoli;


import Buisness.ArticoloBuisness;
import View.Amministratore.Articoli.ArticoloComposito.InserimentoProdottoCompositoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InserimentoArticoliPanel extends JPanel {

    private JLabel nomeLabel;
    private JTextField nome;
    private JLabel tipoLabel;
    private JComboBox tipo;
    private JLabel prezzoLabel;
    private JTextField prezzo;
    private JButton conferma;

    public InserimentoArticoliPanel(){

            nomeLabel= new JLabel("Nome");
            tipoLabel= new JLabel("Tipo");prezzoLabel= new JLabel("Prezzo");
            nome = new JTextField();
            nome.setColumns(20);

            tipo = new JComboBox();
            tipo.addItem("prodotto");
            tipo.addItem("prodotto_composito");
            tipo.addItem("servizio");
            tipo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tipo.setSelectedIndex(tipo.getSelectedIndex());
                }
            });

            prezzo = new JTextField();
            prezzo.setColumns(20);

            conferma=new JButton("Conferma");
            conferma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    if (false) {//controlli sui dati inseriri
                    } else {
                        if (tipo.getSelectedIndex() == 0) {
                            // mostra schermata gestione prodotto
                            removeAll();

                            add(new GestioneProdottoPanel(nome, prezzo));
                            repaint();
                            validate();

                        } else if (tipo.getSelectedIndex() == 1) {
                            //mostra schermata prodotto composito
                            removeAll();
                            add(new InserimentoProdottoCompositoPanel(nome, prezzo));
                            repaint();
                            validate();
                        } else if (tipo.getSelectedIndex() == 2) {

                            int i=ArticoloBuisness.getInstance().addServizio(nome.getText(), Float.parseFloat(prezzo.getText()));
                            if(i<=0){
                                JOptionPane.showMessageDialog(null, "Servizio non inserito!");
                            }else{JOptionPane.showMessageDialog(null, "Servizio inserito correttamnete");}

                        }
                    }
                }
            });

            setLayout(new GridBagLayout());

            GridBagConstraints gbc= new GridBagConstraints();

            gbc.insets= new Insets(20,20,20,20);
            gbc.gridx=0;
            gbc.gridy=0;

            this.add(nomeLabel, gbc);
            gbc.gridy=1;
            this.add(nome, gbc);

            gbc.gridx=1;
            gbc.gridy=0;

            this.add(tipoLabel, gbc);
            gbc.gridy=1;
            this.add(tipo, gbc);

            gbc.gridx=2;
            gbc.gridy=0;

            this.add(prezzoLabel, gbc);
            gbc.gridy=1;
            this.add(prezzo, gbc);

            gbc.gridx=3;
            this.add(conferma, gbc);
        }



    public JTextField getNome() {
        return nome;
    }

    public void setNome(JTextField nome) {
        this.nome = nome;
    }


    public JComboBox getTipo() {
        return tipo;
    }

    public void setTipo(JComboBox tipo) {
        this.tipo = tipo;
    }

    public JTextField getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(JTextField prezzo) {
        this.prezzo = prezzo;
    }
}

