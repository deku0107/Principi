package Buisness;

import Dao.Prodotti.ArticoloCompositoDao;
import Dao.Prodotti.ProdottoDao;
import Dao.Prodotti.ServizioDao;
import Dao.PuntoVendita.OffertaDAO;
import Dao.PuntoVendita.PuntoVenditaDao;
import Model.Categoria;
import Model.Prodotti.*;
import Model.Produttore;
import Model.PuntoVendita;
import Model.Utenti.Manager;
import Model.Utenti.UtenteAcquirente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ArticoloBuisness {


    private static ArticoloBuisness instance= new ArticoloBuisness();
    public static ArticoloBuisness getInstance(){return  instance;}


    public List<Articolo> getProdotti(){
        List<Articolo> articoloList= ProdottoDao.getInstance().findArticolo();
        List<Articolo> articoloList1=ArticoloCompositoDao.getInstance().findArticolo();
        articoloList.addAll(articoloList1);
        return articoloList;
    }

    public List<Articolo> getCatalogo(){

        UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        String idPuntoVendita= utenteAcquirente.getPuntoVendita().getId();
        System.out.println("Id punto vendita " + idPuntoVendita);

        return OffertaDAO.getInstance().getOfferta(idPuntoVendita);

    }
    public List<Articolo> getCatalogo(String id){


        return OffertaDAO.getInstance().getOfferta(id);

    }
    public List<Articolo> getCatalogoInverso(String idPuntoVendita) {
        List<Articolo>catalogo= OffertaDAO.getInstance().getOfferta(idPuntoVendita);
        List<Articolo> articoloList= getProdotti();
        List<Articolo> r = new ArrayList<>();
        String tmp;
        for(Articolo a: articoloList){
            tmp=a.getId();
            boolean check=true;
            for (Articolo b:catalogo){
                if (b.getId().equalsIgnoreCase(tmp)) {
                    check = false;
                    break;
                }
            }
            if(check)
                r.add(a);
        }
        return r;
    }

    public List<Articolo> getCatalogoManager(){

        Manager manager = (Manager) SessionManager.getSession().get(SessionManager.LOGGED_MANAGER);

        System.out.println("Id manager " + manager.getId());
        PuntoVendita puntoVendita= PuntoVenditaDao.getInstance().findByManager(manager.getId());
        System.out.println("Id punto vendita " + puntoVendita.getId());

        return OffertaDAO.getInstance().getOfferta(puntoVendita.getId());

    }

    public JComboBox getProdottiBox(){
        JComboBox comboBox= new JComboBox();
        List<Articolo> articoli= getProdotti();
        System.out.println("Dimensione categorie "+articoli.size());

        for(Articolo a: articoli){
            comboBox.addItem(a.getNome());
        }
        return comboBox;
    }

    public List<Articolo> getServizi(){
        return ServizioDao.getInstance().findArticolo();
    }

    public List<Articolo> getProdottiCompositi(){
        return ArticoloCompositoDao.getInstance().findArticolo();
    }

    public int addServizio(String nome, Float prezzo){
        Servizio servizio = new Servizio();
        servizio.setNome(nome);
        servizio.setPrezzo(prezzo);
        return ServizioDao.getInstance().addArticolo(servizio);
    }

    public int addProdotto(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione){
        Prodotto prodotto= new Prodotto();
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setCategoria(categoria);
        prodotto.setScaffale(scaffale);
        prodotto.setCorsia(corsia);
        prodotto.setProduttore(produttore);
        prodotto.setDescrizione(descrizione);
        return ProdottoDao.getInstance().addArticolo(prodotto);
    }

    public int addProdottoComposito(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione, List<Object> composizione, List<Integer> quantita ){

        ArticoloComposito articoloComposito= new ArticoloComposito();
        articoloComposito.setNome(nome);
        articoloComposito.setPrezzo(prezzo);
        articoloComposito.setCategoria(categoria);
        articoloComposito.setScaffale(scaffale);
        articoloComposito.setCorsia(corsia);
        articoloComposito.setProduttore(produttore);
        articoloComposito.setDescrizione(descrizione);
        ArrayList<Composizione> composizioneArrayList= new ArrayList<>();
        int i=0;
        for(Object c:  composizione ){
            Articolo articolo=(Articolo) c;
            Composizione comp = new Composizione();
            comp.setArticolo(articolo);
            comp.setQuantita(quantita.get(i));
            i++;
            composizioneArrayList.add(comp);
        }
        articoloComposito.setComposizione(composizioneArrayList);

        return ArticoloCompositoDao.getInstance().addArticolo(articoloComposito);

    }

    public String getNome(Object b){
        Articolo a= (Articolo) b;
        return a.getNome();

    }
    public String geIdArticolo(Object b){
        Articolo a= (Articolo) b;
        return a.getId();

    }

    public String getDescrizione(Object b){
        Articolo a= (Articolo) b;
        return a.getDescrizione();

    }

    public Float getPrezzo(Object b){
        Articolo a= (Articolo) b;
        return a.getPrezzo();

    }

    //immagini

    //Servizi

    //Prodotti
    public Categoria getCategoria(Object b){
        Prodotto p= (Prodotto) b;
        return p.getCategoria();

    }

    public Produttore getProduttore(Object b){
        Prodotto p= (Prodotto) b;
        return p.getProduttore();

    }

    public int getScaffale(Object b){
        Prodotto p = (Prodotto) b;
        return p.getScaffale();

    }

    public int getCorsia(Object b){
        Prodotto p = (Prodotto) b;
        return p.getCorsia();

    }

    public int getQuantita(Object b){
        Prodotto p = (Prodotto) b;
        return p.getQuantita();

    }

    public int setPosizione(String idProdotto, int corsia, int scaffale) {

        return ProdottoDao.getInstance().setPosizione(idProdotto, corsia, scaffale);

    }



    //Prodotti Compositi



}
