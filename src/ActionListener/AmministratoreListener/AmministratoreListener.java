package ActionListener.AmministratoreListener;

import Buisness.*;
import Dao.PuntoVendita.OffertaDAO;
import ViewProveETest.GridBagLayoutPanel;
import ViewProveETest.Prove.Amministratore.Articoli.InserimentoArticoliPanel;
import ViewProveETest.Prove.Amministratore.Articoli.ModificaProdottoPanel;
import ViewProveETest.Prove.Amministratore.Categoria.CategoriaPanel;
import ViewProveETest.Prove.Amministratore.Categoria.RigaCategoriaPanel;
import ViewProveETest.Prove.Amministratore.Manager.CancellazioneManagerPanel;
import ViewProveETest.Prove.Amministratore.Offerta.ElencoOfferta;
import ViewProveETest.Prove.Amministratore.Offerta.RigaProdotto;
import ViewProveETest.Prove.Amministratore.Produttore.CancellazioneProduttorePanel;
import ViewProveETest.Prove.Amministratore.Produttore.ElencoProduttoriPanel;
import ViewProveETest.Prove.Amministratore.Produttore.RigaGestioneProduttorePanel;
import ViewProveETest.Prove.Amministratore.PuntoVendita.CancellazionePuntovenditaPanel;
import ViewProveETest.Prove.Amministratore.PuntoVendita.GestionePuntoVenditaPanel;
import ViewProveETest.Prove.Amministratore.PuntoVendita.rigaPuntoVenditaPanel;
import ViewProveETest.Prove.Manager.GestioneClientela.GestioneUtentiPanel;
import Buisness.Utente.UtenteBusiness;
import ViewProveETest.Home;
import ViewProveETest.Prove.Amministratore.Manager.InserimentoManager;
import ViewProveETest.Prove.Amministratore.Manager.ModificaManager;
import ViewProveETest.Prove.Manager.GestioneClientela.rigaGestioneUtentiPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class AmministratoreListener implements ActionListener {
    private final JPanel panel;
    private final Home home= Home.getInstance();

    public AmministratoreListener(JPanel panel){
        this.panel=panel;

    }
    @Override
    public void actionPerformed(ActionEvent e) {


        if(panel instanceof InserimentoManager)
            inserimento();

        if(panel instanceof ModificaManager)
            modifica();

        if(e.getActionCommand().equalsIgnoreCase("impostazioni"))
            impostazioni();
        if(e.getActionCommand().equalsIgnoreCase("gestione_punti_vendita"))
            puntiVendita();
        if(e.getActionCommand().equalsIgnoreCase("gestione_produttori"))
            gestioneProduttori();
        if(e.getActionCommand().equalsIgnoreCase("gestione_categorie"))
            gestioneCategorie();
        if(e.getActionCommand().equalsIgnoreCase("gestione_catalogo"))
            gestioneCatalogo();
        if(e.getActionCommand().equalsIgnoreCase("manager"))
            gestioneManager();
        if(e.getActionCommand().equalsIgnoreCase("modifica_manager"))
            mostraPannelloModificamanager();
        if (e.getActionCommand().equalsIgnoreCase("elimina_manager"))
            eliminaManager();
        if (e.getActionCommand().equalsIgnoreCase("modifica_p"))
            modificaProduttore();
        if (e.getActionCommand().equalsIgnoreCase("modifica_categoria"))
            modificaCategoriaPanel();
        if (e.getActionCommand().equalsIgnoreCase("modifica_c"))
            modificaCategoria();
        if (e.getActionCommand().equalsIgnoreCase("inserimento_categoria"))
            inserimentoCategoria();
        if (e.getActionCommand().equalsIgnoreCase("inserimento_punto_vendita"))
            inserimentoPuntoVendita();
        if (e.getActionCommand().equalsIgnoreCase("modifica_punto_vendita"))
            modificaPuntoVendita();
        if (e.getActionCommand().equalsIgnoreCase("inserimento_produttore"))
            inserimentoProduttore();
        if (e.getActionCommand().equalsIgnoreCase("elimina_categoria"))
            eliminaCategoria();
        if (e.getActionCommand().equalsIgnoreCase("visualizza_offerta"))
            mostraOfferte();
        if (e.getActionCommand().equalsIgnoreCase("rimuovere_offerta"))
            rimuoviOfferte();
        if (e.getActionCommand().equalsIgnoreCase("aggiorna_offerta"))
            aggiornaOfferte();
        if (e.getActionCommand().equalsIgnoreCase("nuova_offerta"))
            nuovaOfferta();
        if (e.getActionCommand().equalsIgnoreCase("aggiungi_all_offerta"))
            aggiungiOfferta();
        if (e.getActionCommand().equalsIgnoreCase("elimina_prodotto_admin"))
            eliminaProdotto();
        if (e.getActionCommand().equalsIgnoreCase("modifica_prodotto_admin"))
            modificaProdotto();
        if (e.getActionCommand().equalsIgnoreCase("effettua_modifica_prodotto"))
            effetuaModificaProdotto();
    }

    private void effetuaModificaProdotto() {
        ModificaProdottoPanel p= (ModificaProdottoPanel) panel;

        int i= JOptionPane.showConfirmDialog(p, "Sei sicuro di voler modificare l'articolo selezionato?");
        if(i==JOptionPane.YES_OPTION){
            Object o= p.getO();

            if (ArticoloBuisness.getInstance().isProdotto(o)){
                System.out.println(p.getNome().getText());
                System.out.println(Float.parseFloat(p.getPrezzo().getText()));
                System.out.println(CategoriaBuisness.getInstance().getCategorie().get(p.getCategoria().getSelectedIndex()).getNome());
                System.out.println(ProduttoreBuisness.getInstance().getProduttori().get(p.getProduttore().getSelectedIndex()).getNome());
                System.out.println(p.getDescrizione().getText());
                System.out.println(p.getPath());

                int j=ArticoloBuisness.getInstance().updateProdotto(p.getNome().getText(),Float.parseFloat(p.getPrezzo().getText()), CategoriaBuisness.getInstance().getCategorie().get(p.getCategoria().getSelectedIndex()),0,0, ProduttoreBuisness.getInstance().getProduttori().get(p.getProduttore().getSelectedIndex()), p.getDescrizione().getText(), p.getPath(), ArticoloBuisness.getInstance().geIdArticolo(o));
                if (j>=0){
                    JOptionPane.showMessageDialog(null, "Prodotto modificato");
                }else{
                    JOptionPane.showMessageDialog(null, "Prodotto non modificato");
                }
            }

            if (ArticoloBuisness.getInstance().isComposito(o)){

                int j=ArticoloBuisness.getInstance().updateProdottoComposito(p.getNome().getText(),Float.parseFloat(p.getPrezzo().getText()), CategoriaBuisness.getInstance().getCategorie().get(p.getCategoria().getSelectedIndex()),0,0, ProduttoreBuisness.getInstance().getProduttori().get(p.getProduttore().getSelectedIndex()), p.getDescrizione().getText(), null, null, p.getPath(), ArticoloBuisness.getInstance().geIdArticolo(o));
                if (j>=0){
                    JOptionPane.showMessageDialog(null, "Prodotto modificato");
                }else{
                    JOptionPane.showMessageDialog(null, "Prodotto non modificato");
                }
            }

        }

    }

    private void modificaProdotto() {
        GridBagLayoutPanel p= (GridBagLayoutPanel) panel;
        home.getNord().removeAll();
        home.getNord().add(home.getBack());

        home.getCentro().removeAll();
        home.getCentro().add(new ModificaProdottoPanel(p.getArticolo()));
        home.repaint();
        home.validate();

    }

    private void eliminaProdotto() {
        GridBagLayoutPanel p= (GridBagLayoutPanel) panel;
        int i= JOptionPane.showConfirmDialog(p, "Sei sicuro di voler eliminare l'articolo selezionato?");
        if(i==JOptionPane.YES_OPTION){
            int j=ArticoloBuisness.getInstance().remove(p.getArticolo());
            if (j>=0){
                JOptionPane.showMessageDialog(null, "Prodotto eliminato");
            }else{
                JOptionPane.showMessageDialog(null, "Prodotto non eliminato");
            }
        }
     }

    private void aggiungiOfferta() {
        ElencoOfferta p= (ElencoOfferta) panel;
        home.getNord().removeAll();
        home.getNord().add(home.getBack());

        home.getCentro().removeAll();
        home.getCentro().add(new ElencoOfferta(p.getIdPuntoVendita(), 1));

        home.repaint();
        home.validate();
    }

    private void nuovaOfferta() {
        RigaProdotto p = (RigaProdotto) panel;
        String idArticolo=ArticoloBuisness.getInstance().geIdArticolo(p.getProdotto());
        String idPuntoVendita= PuntoVenditaBuisness.getInstance().getId(p.getPuntoVendita());

        int i= OffertaDAO.getInstance().add(idArticolo, idPuntoVendita);
        if(i<0){
            JOptionPane.showMessageDialog(null,"Errore, offerta non aggiornata");
        }else{
            JOptionPane.showMessageDialog(null,"Offerta aggiornata");
            home.getCentro().removeAll();
            home.getCentro().add(new ElencoOfferta(idPuntoVendita, 1));

            home.repaint();
            home.validate();
        }





    }

    private void aggiornaOfferte() {
        RigaProdotto p = (RigaProdotto) panel;
        int corsia= Integer.parseInt(p.getCorsia().getText());
        int scaffale= Integer.parseInt(p.getScaffale().getText());

        int i= ArticoloBuisness.getInstance().setPosizione(ArticoloBuisness.getInstance().geIdArticolo(p.getProdotto()),corsia, scaffale);

        if(i<0){
            JOptionPane.showMessageDialog(null,"Errore, offerta non aggiornata");
        }else{
            JOptionPane.showMessageDialog(null,"Offerta aggiornata");
        }
    }

    private void rimuoviOfferte() {

        RigaProdotto p = (RigaProdotto) panel;
        System.out.println("id articolo in rimuovi offerte"+ProduttoreBuisness.getInstance().getId(p.getProdotto()));
        int i= PuntoVenditaBuisness.getInstance().removeOfferta(ArticoloBuisness.getInstance().geIdArticolo(p.getProdotto()), PuntoVenditaBuisness.getInstance().getId(p.getPuntoVendita()) );

        if(i<0){
            JOptionPane.showMessageDialog(null,"Errore, offerta non eliminata");
        }else{
            JOptionPane.showMessageDialog(null,"Offerta eliminata");
        }

        home.getCentro().removeAll();
        home.getCentro().add(new GestionePuntoVenditaPanel());

        home.repaint();
        home.validate();

    }

    private void mostraOfferte() {

        rigaPuntoVenditaPanel p= (rigaPuntoVenditaPanel) panel;
        home.getNord().removeAll();
        home.getNord().add(home.getBack());

        home.getCentro().removeAll();
        home.getCentro().add(new ElencoOfferta(PuntoVenditaBuisness.getInstance().getId(p.getPuntoVendita())));

        home.repaint();
        home.validate();

    }

    private void modificaPuntoVendita() {
        rigaPuntoVenditaPanel r= (rigaPuntoVenditaPanel) panel;
        JTextField nome= r.getNomeField();
        JTextField email=r.getEmailField();
        JTextField citta= r.getCittaField();
        JTextField telefono=r.getTelefonoField();
        JTextField indirizzo= r.getIndirizzoField();
        JComboBox manager= r.getManagerBox();
        Object puntoVendita= r.getPuntoVendita();

        if(nome.getText().trim().length()==0||email.getText().trim().length()==0||citta.getText().trim().length()==0||telefono.getText().trim().length()!=10 ||indirizzo.getText().trim().length()==0){

            JOptionPane.showMessageDialog(null, "Compila tutti i campi");
        }else{


            PuntoVenditaBuisness p= PuntoVenditaBuisness.getInstance();

            p.setNome(puntoVendita, nome.getText());
            p.setEmail(puntoVendita, email.getText());
            p.setTelefono(puntoVendita, telefono.getText());
            p.setIndirizzo(puntoVendita, indirizzo.getText());
            p.setCitta(puntoVendita, citta.getText());
            p.setManager(puntoVendita,UtenteBusiness.getInstance().getManager().get(manager.getSelectedIndex()) );


            int i= PuntoVenditaBuisness.getInstance().update(puntoVendita);

            if(i<0){
                JOptionPane.showMessageDialog(null, "Punto vendita non inserito");
            }else{
                JOptionPane.showMessageDialog(null, "Punto vendita inserito");
            }
        }
    }

    private void inserimentoProduttore() {

        RigaGestioneProduttorePanel r= (RigaGestioneProduttorePanel) panel;
        JTextField nome= r.getNome();
        JTextField sito= r.getSito();
        JTextField citta=r.getCitta();
        JTextField nazione= r.getNazione();
        if(nome.getText().trim().length()==0||sito.getText().trim().length()==0||citta.getText().trim().length()==0||nazione.getText().trim().length()==0){

            JOptionPane.showMessageDialog(null, "Compila tutti i campi");
        }else{
            int i= ProduttoreBuisness.getInstance().addProduttore(nome.getText(), sito.getText(), citta.getText(), nazione.getText());

            if(i<0){
                JOptionPane.showMessageDialog(null, "Produttore non inserito");
            }else{
                JOptionPane.showMessageDialog(null, "Produttore inserito");
            }
        }
    }

    private void eliminaCategoria() {
        CategoriaPanel panel= (CategoriaPanel) this.panel;
        String id= CategoriaBuisness.getInstance().getCategorie().get(panel.getComboBox().getSelectedIndex()).getId();

        int input=JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la categoria selezionata?", "Attenzione", JOptionPane.DEFAULT_OPTION);

        if(input==JOptionPane.OK_OPTION){
            input= CategoriaBuisness.getInstance().remove(Integer.parseInt(id));
            if(input<0){
                JOptionPane.showMessageDialog(null,"Errore, categoria non eliminata");
            }else{
                JOptionPane.showMessageDialog(null,"Categoria eliminata");
            }
        }
    }

    private void modificaCategoria() {

        JComboBox comboBox= ((RigaCategoriaPanel) panel).getCategoria();
        JTextField nome=((RigaCategoriaPanel) panel).getNomeField();
        JTextArea descrizione= ((RigaCategoriaPanel) panel).getDescrizioneField();



        Object categoria= ((RigaCategoriaPanel) panel).getCat();
        CategoriaBuisness.getInstance().setNome(categoria, nome.getText());
        CategoriaBuisness.getInstance().setDescrizione(categoria, descrizione.getText());
        CategoriaBuisness.getInstance().setCategoriaPadre(categoria, CategoriaBuisness.getInstance().getCategorie().get(comboBox.getSelectedIndex()));

        int i = CategoriaBuisness.getInstance().update(categoria);

        if (i>0){
            JOptionPane.showMessageDialog(null, "Categoria modificata");
        }else{
            JOptionPane.showMessageDialog(null, "Categoria non modificata");
        }
    }

    private void inserimentoCategoria() {


        JComboBox comboBox= ((RigaCategoriaPanel) panel).getCategoria();
        JTextField nome=((RigaCategoriaPanel) panel).getNomeField();
        JTextArea descrizione= ((RigaCategoriaPanel) panel).getDescrizioneField();



        Object categoria= ((RigaCategoriaPanel) panel).getCat();
        CategoriaBuisness.getInstance().setNome(categoria, nome.getText());
        CategoriaBuisness.getInstance().setDescrizione(categoria, descrizione.getText());
        CategoriaBuisness.getInstance().setCategoriaPadre(categoria, CategoriaBuisness.getInstance().getCategorie().get(comboBox.getSelectedIndex()));
        int i = CategoriaBuisness.getInstance().add(categoria);

        if (i>0){
            JOptionPane.showMessageDialog(null, "Categoria inserita");
        }else{
            JOptionPane.showMessageDialog(null, "Categoria non inserita");
        }
    }

    private void eliminaManager() {

        CancellazioneManagerPanel panel= (CancellazioneManagerPanel) this.panel;
        String id= UtenteBusiness.getInstance().getManager().get(panel.getManager().getSelectedIndex()).getId();
        int input=JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il manager selezionato?", "Attenzione", JOptionPane.DEFAULT_OPTION);

        if(input==JOptionPane.OK_OPTION){

            System.out.println("Id " + id);
            input=UtenteBusiness.getInstance().removeManager(id);
            if(input<0){
                JOptionPane.showMessageDialog(null,"Errore, manager non eliminato");
            }else{
                JOptionPane.showMessageDialog(null,"Manager eliminato");
            }

        }
    }


    private void mostraPannelloModificamanager() {

        rigaGestioneUtentiPanel mypanel= (rigaGestioneUtentiPanel) panel;


        home.getNord().removeAll();
        home.getNord().add(home.getBack());

        home.getCentro().removeAll();
        home.getCentro().add(new ModificaManager(UtenteBusiness.getInstance().getIdUtente(mypanel.getUtente())));

        home.repaint();
        home.validate();

    }

    private void impostazioni() {
    }

    private void puntiVendita() {

        String[] option ={"Crea punto vendita", "Modifica punto vendita", "Elimina punto vendita", "Annulla"};

        int x=JOptionPane.showOptionDialog(null, "Cosa vuoi fare?", "Scegli", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        if(x==0||x==1||x==2){
            home.getNord().removeAll();
            home.getNord().add(home.getBack());

            home.getCentro().removeAll();
            if(x==0) {
                home.getCentro().add(new rigaPuntoVenditaPanel());
            }else if(x==1)
            {
                home.getCentro().add(new GestionePuntoVenditaPanel());
            }else {
                home.getCentro().add(new CancellazionePuntovenditaPanel());
            }

            home.repaint();
            home.validate();
        }


    }

    private void inserimentoPuntoVendita(){
        rigaPuntoVenditaPanel r= (rigaPuntoVenditaPanel) panel;
        JTextField nome= r.getNomeField();
        JTextField email=r.getEmailField();
        JTextField citta= r.getCittaField();
        JTextField telefono=r.getTelefonoField();
        JTextField indirizzo= r.getIndirizzoField();
        JComboBox manager= r.getManagerBox();
        Object puntoVendita= PuntoVenditaBuisness.getInstance().getNull();

        if(nome.getText().trim().length()==0||email.getText().trim().length()==0||citta.getText().trim().length()==0||telefono.getText().trim().length()!=10 ||indirizzo.getText().trim().length()==0){

            JOptionPane.showMessageDialog(null, "Compila tutti i campi");
        }else{


            PuntoVenditaBuisness p= PuntoVenditaBuisness.getInstance();

            p.setNome(puntoVendita, nome.getText());
            p.setEmail(puntoVendita, email.getText());
            p.setTelefono(puntoVendita, telefono.getText());
            p.setIndirizzo(puntoVendita, indirizzo.getText());
            p.setCitta(puntoVendita, citta.getText());
            p.setManager(puntoVendita,UtenteBusiness.getInstance().getManager().get(manager.getSelectedIndex()) );


            int i= PuntoVenditaBuisness.getInstance().addPuntoVendita(puntoVendita);

            if(i<0){
                JOptionPane.showMessageDialog(null, "Punto vendita non inserito");
            }else{
                JOptionPane.showMessageDialog(null, "Punto vendita inserito");
                JOptionPane.showMessageDialog(null, "Per modificare l'offerta del punto vendita, usare la voce modifica punto vendita->offerta.");
            }
        }

    }

    private void gestioneProduttori() {
        String[] option ={"Crea produttore", "Modifica produttore", "Elimina produttore", "Annulla"};

        int x=JOptionPane.showOptionDialog(null, "Cosa vuoi fare?", "Scegli", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        if(x==0||x==1||x==2){
            home.getNord().removeAll();
            home.getNord().add(home.getBack());

            home.getCentro().removeAll();
            if(x==0) {
                home.getCentro().add(new RigaGestioneProduttorePanel());
            }else if(x==1)
            {
                home.getCentro().add(new ElencoProduttoriPanel());
            }else{
                home.getCentro().add(new CancellazioneProduttorePanel());
            }

            home.repaint();
            home.validate();
        }
    }


    private void modificaProduttore(){
        RigaGestioneProduttorePanel r= (RigaGestioneProduttorePanel) panel;
        JTextField nome= r.getNome();
        JTextField sito= r.getSito();
        JTextField citta=r.getCitta();
        JTextField nazione= r.getNazione();
        Object produttore= r.getProduttore();
        if(nome.getText().trim().length()==0||sito.getText().trim().length()==0||citta.getText().trim().length()==0||nazione.getText().trim().length()==0){

            JOptionPane.showMessageDialog(null, "Compila tutti i campi");
        }else{
            int i= ProduttoreBuisness.getInstance().update(nome.getText(), sito.getText(), citta.getText(), nazione.getText(), ProduttoreBuisness.getInstance().getId(produttore));

            if(i<0){
                JOptionPane.showMessageDialog(null, "Produttore non modificato");
            }else{
                JOptionPane.showMessageDialog(null, "Produttore modificato");
            }
        }

    }

    private void gestioneCategorie() {

        String[] option ={"Crea categoria", "Modifica categoria", "Elimina categoria", "Annulla"};

        int x=JOptionPane.showOptionDialog(null, "Cosa vuoi fare?", "Scegli", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        if(x==0||x==1||x==2){
            home.getNord().removeAll();
            home.getNord().add(home.getBack());

            home.getCentro().removeAll();
            if(x==0) {
                home.getCentro().add(new RigaCategoriaPanel());
            }else if(x==1)
            {
              home.getCentro().add(new CategoriaPanel());
            }else {
               home.getCentro().add(new CategoriaPanel(1));
            }

            home.repaint();
            home.validate();
        }

    }

    private void modificaCategoriaPanel(){


        home.getCentro().removeAll();

        RigaCategoriaPanel rigaCategoriaPanel= new RigaCategoriaPanel();
        CategoriaPanel panel= (CategoriaPanel) this.panel;
        rigaCategoriaPanel.updateData(CategoriaBuisness.getInstance().getCategorie().get(panel.getComboBox().getSelectedIndex()));
        home.getCentro().add(rigaCategoriaPanel);

        home.repaint();
        home.validate();

    }

    private void gestioneCatalogo() {


            home.getNord().removeAll();
            home.getNord().add(home.getBack());

            home.getCentro().removeAll();

            home.getCentro().add(new InserimentoArticoliPanel());

            home.repaint();
            home.validate();
        }



    private void gestioneManager(){

        String[] option ={"Crea manager", "Modifica manager", "Elimina manager", "Annulla"};

        int x=JOptionPane.showOptionDialog(null, "Cosa vuoi fare?", "Scegli", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);

        if(x==0||x==1||x==2){
            home.getNord().removeAll();
            home.getNord().add(home.getBack());

            home.getCentro().removeAll();
            if(x==0) {
                home.getCentro().add(new InserimentoManager());
            }else if(x==1)
            {
                home.getCentro().add(new GestioneUtentiPanel());
            }else{
                home.getCentro().add(new CancellazioneManagerPanel());
            }

            home.repaint();
            home.validate();
        }






        home.repaint();
        home.validate();

    }

    private void inserimento(){
        InserimentoManager p= (InserimentoManager) panel;

        JTextField nome= p.getNome();
        JTextField cognome= p.getCognome();
        JComboBox puntoVendita=p.getPuntoVendita();
        JTextField salario=p.getSalario();
        JTextField username= p.getUsername();
        JTextField email= p.getEmail();
        JTextField telefono= p.getTelefono();
        JPasswordField password=p.getPassword();
        JTextField indirizzo= p.getIndirizzo();
        JTextField citta=p.getCitta();
        JTextField giornoDataNascita= p.getGiornoDataNascita();
        JTextField meseDataNascita=p.getMeseDataNascita();
        JTextField annoDataNascita= p.getAnnoDataNascita();
        JTextField giornoDataInizioIncarico= p.getGiornoDataInizioIncarico();
        JTextField meseDataInizioIncarico=p.getMeseDataInizioIncarico();
        JTextField annoDataInizioIncarico= p.getAnnoDataInizioIncarico();
        JTextField giornoDataFineIncarico= p.getGiornoDataFineIncarico();
        JTextField meseDataFineIncarico=p.getMeseDataFineIncarico();
        JTextField annoDataFineIncarico= p.getAnnoDataFineIncarico();
        String dataDiNascita;
        String dataDiInizioIncarico;
        String dataDiFineIncarico;


        boolean b= UtilityBuisness.getInstance().chekData(Calendar.getInstance().get(Calendar.YEAR), 1900, giornoDataNascita.getText(), meseDataNascita.getText(), annoDataNascita.getText());
        if (b){
            dataDiNascita=annoDataNascita.getText()+"-"+meseDataNascita.getText()+"-"+giornoDataNascita.getText();

            b= UtilityBuisness.getInstance().chekData(Calendar.getInstance().get(Calendar.YEAR), 1900, giornoDataInizioIncarico.getText(), meseDataInizioIncarico.getText(), annoDataInizioIncarico.getText());

            if(b){
                dataDiInizioIncarico=annoDataInizioIncarico.getText()+"-"+meseDataInizioIncarico.getText()+"-"+giornoDataInizioIncarico.getText();

                b= UtilityBuisness.getInstance().chekData(3000, 1900, giornoDataFineIncarico.getText(), meseDataFineIncarico.getText(), annoDataFineIncarico.getText());

                if(b){
                    dataDiFineIncarico=annoDataFineIncarico.getText()+"-"+meseDataFineIncarico.getText()+"-"+giornoDataFineIncarico.getText();

                    String message;
                    if(nome.getText().trim().length()==0){
                        message="Inserire il nome";
                        System.out.println(message);
                        b=false;
                    }
                    if(cognome.getText().trim().length()==0){
                        message="Inserire cognome ";
                        System.out.println(message);
                        b=false;
                    }
                    if(salario.getText().trim().length()==0 || !UtilityBuisness.getInstance().checkFloat(salario.getText())){
                        message="Inserire un salario valido";
                        System.out.println(message);
                        b=false;
                    }
                    if(username.getText().trim().length()==0){
                        message="Inserire username";
                        System.out.println(message);
                        b=false;
                    }
                    if(email.getText().trim().length()==0){
                        message="Inserire un'email";
                        System.out.println(message);
                        b=false;
                    }else if(UtilityBuisness.getInstance().checkEmail(email.getText())){
                        message="Email già utilizzata";
                        System.out.println(message);
                        b=false;
                    }else if(UtilityBuisness.getInstance().checkFormattedEmail(email.getText())){
                        message="Inserire un'email valida";
                        System.out.println(message);
                        b=false;
                    }
                    if(telefono.getText().trim().length()!=10 || !UtilityBuisness.getInstance().checkInteger(telefono.getText().trim())){
                        message="Inserire un numero di telefono valido";
                        System.out.println(message);
                        b=false;
                    }
                    if(password.getPassword().length==0){
                        message="Inserire una password valida";
                        System.out.println(message);
                        b=false;
                    }
                    if(indirizzo.getText().trim().length()==0){
                        message="Inserire un indirzzo valido";
                        System.out.println(message);
                        b=false;
                    }
                    if(citta.getText().trim().length()==0){
                        message="Inserire una citta valida";
                        System.out.println(message);
                        b=false;
                    }

                    if(b){
                        //inserimento manager nel database

                        ArrayList<Object> tmp = new ArrayList<>();
                        tmp.add(nome.getText());
                        tmp.add(cognome.getText());
                        tmp.add(puntoVendita.getSelectedIndex());
                        tmp.add(dataDiNascita);
                        tmp.add(salario.getText());
                        tmp.add(dataDiInizioIncarico);
                        tmp.add(dataDiFineIncarico);
                        tmp.add(username.getText());
                        tmp.add(email.getText());
                        tmp.add(telefono.getText());
                        tmp.add(Arrays.toString(password.getPassword()));
                        tmp.add(indirizzo.getText());
                        tmp.add(citta.getText());

                        int i = UtenteBusiness.getInstance().addManger(tmp);
                        System.out.println("Valore i " + i);

                    }
                }
            }

        }

    }

    private void modifica(){
        ModificaManager p= (ModificaManager) panel;

        JTextField nome= p.getNome();
        JTextField cognome= p.getCognome();
        JComboBox puntoVendita=p.getPuntoVendita();
        JTextField salario=p.getSalario();
        JTextField username= p.getUsername();
        JTextField email= p.getEmail();
        JTextField telefono= p.getTelefono();
        JPasswordField password=p.getPassword();
        JTextField indirizzo= p.getIndirizzo();
        JTextField citta=p.getCitta();
        JTextField giornoDataNascita= p.getGiornoDataNascita();
        JTextField meseDataNascita=p.getMeseDataNascita();
        JTextField annoDataNascita= p.getAnnoDataNascita();
        JTextField giornoDataInizioIncarico= p.getGiornoDataInizioIncarico();
        JTextField meseDataInizioIncarico=p.getMeseDataInizioIncarico();
        JTextField annoDataInizioIncarico= p.getAnnoDataInizioIncarico();
        JTextField giornoDataFineIncarico= p.getGiornoDataFineIncarico();
        JTextField meseDataFineIncarico=p.getMeseDataFineIncarico();
        JTextField annoDataFineIncarico= p.getAnnoDataFineIncarico();
        String dataDiNascita;
        String dataDiInizioIncarico;
        String dataDiFineIncarico;
        String idManager= p.getIdmanager();


        boolean b= UtilityBuisness.getInstance().chekData(Calendar.getInstance().get(Calendar.YEAR), 1900, giornoDataNascita.getText(), meseDataNascita.getText(), annoDataNascita.getText());
        if (b){
            dataDiNascita=annoDataNascita.getText()+"-"+meseDataNascita.getText()+"-"+giornoDataNascita.getText();

            b= UtilityBuisness.getInstance().chekData(Calendar.getInstance().get(Calendar.YEAR), 1900, giornoDataInizioIncarico.getText(), meseDataInizioIncarico.getText(), annoDataInizioIncarico.getText());

            if(b){
                dataDiInizioIncarico=annoDataInizioIncarico.getText()+"-"+meseDataInizioIncarico.getText()+"-"+giornoDataInizioIncarico.getText();

                b= UtilityBuisness.getInstance().chekData(3000, 1900, giornoDataFineIncarico.getText(), meseDataFineIncarico.getText(), annoDataFineIncarico.getText());

                if(b){
                    dataDiFineIncarico=annoDataFineIncarico.getText()+"-"+meseDataFineIncarico.getText()+"-"+giornoDataFineIncarico.getText();

                    String message;
                    if(nome.getText().trim().length()==0){
                        message="Inserire il nome";
                        System.out.println(message);
                        b=false;
                    }
                    if(cognome.getText().trim().length()==0){
                        message="Inserire cognome ";
                        System.out.println(message);
                        b=false;
                    }
                    if(salario.getText().trim().length()==0 || !UtilityBuisness.getInstance().checkFloat(salario.getText())){
                        message="Inserire un salario valido";
                        System.out.println(message);
                        b=false;
                    }
                    if(username.getText().trim().length()==0){
                        message="Inserire username";
                        System.out.println(message);
                        b=false;
                    }
                    if(email.getText().trim().length()==0){
                        message="Inserire un'email";
                        System.out.println(message);
                        b=false;
                    }else if(UtilityBuisness.getInstance().checkFormattedEmail(email.getText())){
                        message="Inserire un'email valida";
                        System.out.println(message);
                        b=false;
                    }
                    if(telefono.getText().trim().length()!=10 || !UtilityBuisness.getInstance().checkInteger(telefono.getText().trim())){
                        message="Inserire un numero di telefono valido";
                        System.out.println(message);
                        b=false;
                    }

                    if(indirizzo.getText().trim().length()==0){
                        message="Inserire un indirzzo valido";
                        System.out.println(message);
                        b=false;
                    }
                    if(citta.getText().trim().length()==0){
                        message="Inserire una citta valida";
                        System.out.println(message);
                        b=false;
                    }

                    if(b){
                        //modifica manager nel database

                        ArrayList<Object> tmp = new ArrayList<>();
                        tmp.add(nome.getText());
                        tmp.add(cognome.getText());
                        tmp.add(puntoVendita.getSelectedIndex());
                        tmp.add(dataDiNascita);
                        tmp.add(salario.getText());
                        tmp.add(dataDiInizioIncarico);
                        tmp.add(dataDiFineIncarico);
                        tmp.add(username.getText());
                        tmp.add(email.getText());
                        tmp.add(telefono.getText());
                        tmp.add(null);
                        tmp.add(indirizzo.getText());
                        tmp.add(citta.getText());
                        tmp.add(idManager);

                        int i = UtenteBusiness.getInstance().updateManager(tmp);
                        System.out.println("Valore i " + i);

                    }
                }
            }

        }

    }
}
