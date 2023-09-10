package Model;

import Buisness.SessionManager;
import Dao.ListaDiAcquisto.ListaAcquistoDao;
import Model.Prodotti.Articolo;
import Model.Utenti.UtenteAcquirente;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListaDiAcquisto {

    private ArrayList<Articolo> lista;
    private UtenteAcquirente utenteAcquirente;
    private String nome;
    private String id;
    private boolean stato; //true pagata, false da pagare

    public ListaDiAcquisto() {
        this.stato = false;
        lista= new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Articolo> getLista() {
        return lista;
    }


    public ArrayList<Articolo> getSingleLista(){


        ArrayList<Articolo> articoloArrayList= new ArrayList<>();
        ArrayList<Articolo> list= lista;


        for (Articolo l : list){

            if(!checkPresence(l, this))
                articoloArrayList.add(l);

        }
        return articoloArrayList;
    }

    public boolean checkPresence(Articolo a, ListaDiAcquisto listaDiAcquisto){
        boolean check= false;
        for (Articolo articolo: listaDiAcquisto.getLista()){
            if (articolo.getId().equalsIgnoreCase(a.getId())) {
                check = true;
                break;
            }
        }
        return check;
    }


    public void setLista(ArrayList<Articolo> lista) {
        this.lista = lista;
    }
    public void addLista(ArrayList<Articolo> lista) {
        this.lista.addAll(lista);
    }
    public void setLista(Articolo a) {
        this.lista.add(a);
    }

    public UtenteAcquirente getUtenteAcquirente() {
        return utenteAcquirente;
    }

    public void setUtenteAcquirente(UtenteAcquirente utenteAcquirente) {
        this.utenteAcquirente = utenteAcquirente;
    }

    public boolean isPagata(){
        return stato;

    }

    public void setPagamento(boolean b){
        this.stato=b;

    }
}
