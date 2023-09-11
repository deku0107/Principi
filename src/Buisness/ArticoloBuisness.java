package Buisness;

import Dao.Categorie.CategoriaDao;
import Dao.Prodotti.ArticoloCompositoDao;
import Dao.Prodotti.ProdottoDao;
import Dao.Prodotti.ServizioDao;
import Dao.PuntoVendita.OffertaDAO;
import Dao.PuntoVendita.PuntoVenditaDao;
import Model.Categoria;
import Model.Immagine;
import Model.Prodotti.*;
import Model.Produttore;
import Model.PuntoVendita;
import Model.Utenti.Manager;
import Model.Utenti.UtenteAcquirente;


import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArticoloBuisness {


    private static ArticoloBuisness instance= new ArticoloBuisness();
    public static ArticoloBuisness getInstance(){return  instance;}


    public List<Articolo> getProdotti(){
        List<Articolo> articoloList= ProdottoDao.getInstance().findArticolo();
        System.out.println("Articolo 1 " + articoloList.size());
        List<Articolo> articoloList1=ArticoloCompositoDao.getInstance().findArticolo();
        System.out.println("Articolo 2 " + articoloList1.size());
        articoloList.addAll(articoloList1);
        return articoloList;
    }

    public Articolo getProdotto(String id){
        Articolo a = ProdottoDao.getInstance().findArticolo(id);
        if (a==null)
            a= ArticoloCompositoDao.getInstance().findArticolo(id);
        if(a==null)
            a= ServizioDao.getInstance().findArticolo(id);
        return a;

    }
    public List<Articolo> getProdotti(String categoria){

        Categoria c= CategoriaDao.getInstance().findCategoriaByName(categoria).get(0);
        if (c==null){
            return null;
        }
        List<Articolo> articoloList= ProdottoDao.getInstance().findArticolo(c);
        List<Articolo> articoloList1=ArticoloCompositoDao.getInstance().findArticolo(c);
        articoloList.addAll(articoloList1);
        return articoloList;
    }

    public List<Articolo> getCatalogo(){

        UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        String idPuntoVendita= utenteAcquirente.getPuntoVendita().getId();
        System.out.println("Id punto vendita " + idPuntoVendita);

        return OffertaDAO.getInstance().getOfferta(idPuntoVendita);

    }

    public List<Articolo> getCatalogoByCategoria(String categoria){

        UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);

        String idPuntoVendita= utenteAcquirente.getPuntoVendita().getId();
        System.out.println("Id punto vendita " + idPuntoVendita);

        return OffertaDAO.getInstance().getOfferta(idPuntoVendita, categoria);

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

    public int addProdotto(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione, String path){
        Prodotto prodotto= new Prodotto();
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setCategoria(categoria);
        prodotto.setScaffale(scaffale);
        prodotto.setCorsia(corsia);
        prodotto.setProduttore(produttore);
        prodotto.setDescrizione(descrizione);
        //settare l'immagine con il path
        prodotto.setImmagine(path);
        return ProdottoDao.getInstance().addArticolo(prodotto);
    }

    public int updateProdotto(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione, String path, String id){
        Prodotto prodotto= new Prodotto();
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setCategoria(categoria);
        prodotto.setScaffale(scaffale);
        prodotto.setCorsia(corsia);
        prodotto.setProduttore(produttore);
        prodotto.setDescrizione(descrizione);
        prodotto.setId(id);
        //settare l'immagine con il path
        prodotto.setImmagine(path);
        return ProdottoDao.getInstance().update(prodotto);
    }

    public int addProdottoComposito(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione, List<Object> composizione, List<Integer> quantita, String path){

        ArticoloComposito articoloComposito= new ArticoloComposito();
        articoloComposito.setNome(nome);
        articoloComposito.setPrezzo(prezzo);
        articoloComposito.setCategoria(categoria);
        articoloComposito.setScaffale(scaffale);
        articoloComposito.setCorsia(corsia);
        articoloComposito.setProduttore(produttore);
        articoloComposito.setDescrizione(descrizione);
        articoloComposito.setImmagine(path);
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

    public int updateProdottoComposito(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione, List<Object> composizione, List<Integer> quantita, String path, String id) {

        ArticoloComposito articoloComposito = new ArticoloComposito();
        articoloComposito.setNome(nome);
        articoloComposito.setPrezzo(prezzo);
        articoloComposito.setCategoria(categoria);
        articoloComposito.setScaffale(scaffale);
        articoloComposito.setCorsia(corsia);
        articoloComposito.setProduttore(produttore);
        articoloComposito.setDescrizione(descrizione);
        articoloComposito.setImmagine(path);
        articoloComposito.setId(id);
        ArrayList<Composizione> composizioneArrayList = new ArrayList<>();
        int i = 0;
        for (Object c : composizione) {
            Articolo articolo = (Articolo) c;
            Composizione comp = new Composizione();
            comp.setArticolo(articolo);
            comp.setQuantita(quantita.get(i));
            i++;
            composizioneArrayList.add(comp);
        }
        articoloComposito.setComposizione(composizioneArrayList);

        return ArticoloCompositoDao.getInstance().update(articoloComposito);

    }


    public int addComposizione(Object o, Object c){
        if (!(o instanceof ArticoloComposito && c instanceof Composizione)) {
            return -1;
        }
        return ArticoloCompositoDao.getInstance().addComposizione((Composizione) c, (ArticoloComposito) o);

    }

    public int updateComposizione(Object o, Object c){
        if (!(o instanceof ArticoloComposito && c instanceof Composizione)) {

            return -1;
        }
        return ArticoloCompositoDao.getInstance().updateComposizione((Composizione) c, (ArticoloComposito) o);

    }

    public int updateQuantitaComposizione(Object o, int i){
        if (!(o instanceof Composizione)) {

            return -1;
        }
        return ArticoloCompositoDao.getInstance().updateQuantitaComposizione((Composizione) o, i);
    }

    public int deleteComposizione(Object o ){
        if (!(o instanceof Composizione)) {

            return -1;
        }
        return ArticoloCompositoDao.getInstance().deleteComposizione((Composizione) o);
    }

        public int remove(Object o){
        if(o instanceof ArticoloComposito){
            return ArticoloCompositoDao.getInstance().remove(((ArticoloComposito) o).getId());
        }
        if(o instanceof Servizio){
            return ServizioDao.getInstance().remove(((Servizio) o).getId());
        }
        if (o instanceof Articolo){
            return ProdottoDao.getInstance().remove(((Articolo) o).getId());
        }
        return -1;
    }
    public String getNome(Object b){
        if( b instanceof  Articolo a)
        return a.getNome();


        return null;

    }

    public String getPath(Object b){
        Articolo a= (Articolo) b;
        if (a.getImmagine()== null)
            a.setImmagine(new Immagine());
        return a.getImmagine().getPath();

    }

    public String geIdArticolo(Object b){
        Articolo a= (Articolo) b;
        return a.getId();

    }

    public String getImmaginePath(Object b){
        Articolo a= (Articolo) b;
        return a.getImmagine().getPath();

    }

    public File getImmagineFile(Object b){
        Articolo a= (Articolo) b;
        return a.getImmagine().getFile();

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


    public boolean isProdotto(Object o){
        return o instanceof Prodotto;

    }

    public boolean isCServizio(Object o){
        return o instanceof Servizio;

    }

    //Prodotti Compositi


    public boolean isComposito(Object o){
        return o instanceof ArticoloComposito;

    }

    public Object getComposizione(Object o){
        if(!instance.isComposito(o))
            return null;

        return ArticoloCompositoDao.getInstance().findComposizione((ArticoloComposito) o);


    }

    public Object getFinalComposizione(Object o){
        if(!instance.isComposito(o))
            return null;

        ArticoloComposito a= (ArticoloComposito) o;
        return a.getComposizione();


    }


    public int getComposizioneQuantita(Object o){
        if(!(o instanceof  Composizione))
            return -1;

        return  ((Composizione) o).getQuantita();

    }

    public String geIdComposizione(Object o){
        if(!(o instanceof  Composizione))
            return null;

        return  ((Composizione) o).getId();

    }

    public Object getComposizioneArticolo(Object o){
        if(!(o instanceof  Composizione))
            return null;

        return  ((Composizione) o).getArticolo();
    }

    public String getComposizioneArticoloNome(Object o){
        if(!(o instanceof  Composizione))
            return null;

        return  ((Composizione) o).getArticolo().getNome();
    }

    public void setComposizione(Object o, Object a, int i){
        if (o instanceof ArticoloComposito && a instanceof Articolo){
            Composizione composizione= new Composizione();
            composizione.setArticolo((Articolo) a);
            composizione.setQuantita(i);

            ((ArticoloComposito) o).setComposizione(composizione);
        }

    }

}
