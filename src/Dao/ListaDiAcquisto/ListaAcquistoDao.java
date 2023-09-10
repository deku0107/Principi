package Dao.ListaDiAcquisto;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Dao.Prodotti.ArticoloCompositoDao;
import Dao.Prodotti.ProdottoDao;
import Dao.Prodotti.ServizioDao;
import Dao.UtilityDao;
import Model.ListaDiAcquisto;
import Model.Prodotti.Articolo;
import Model.Utenti.UtenteAcquirente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ListaAcquistoDao implements IListaAcquistoDao{

    private static final ListaAcquistoDao i= new ListaAcquistoDao();
    public static ListaAcquistoDao getInstance(){return i;}
    private static ResultSet rs;
    private static int rowCount;


    @Override
    public ArrayList<ListaDiAcquisto> getLista(UtenteAcquirente utenteAcquirente) {

        String sql="SELECT idlista_di_acquisto as id, nome, stato FROM mydb.lista_di_acquisto where utente='"+utenteAcquirente.getId()+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        String id;
        try {
            ArrayList<ListaDiAcquisto> listaDiAcquistoArrayList= new ArrayList<>();
            while (rs.next()){

                ListaDiAcquisto listaDiAcquisto= new ListaDiAcquisto();
                id=(rs.getString("id"));

                    listaDiAcquisto.setId(id);
                    listaDiAcquisto.setNome(rs.getString("nome"));
                    String stato= rs.getString("stato");
                    if (stato.equalsIgnoreCase("pagata"))
                        listaDiAcquisto.setPagamento(true);

                    listaDiAcquisto.setUtenteAcquirente(utenteAcquirente);
                    popolaLista(listaDiAcquisto);
                    listaDiAcquistoArrayList.add(listaDiAcquisto);

            }

            return listaDiAcquistoArrayList;

        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return null;
    }

    public ArrayList<ListaDiAcquisto> getListaPagata(UtenteAcquirente utenteAcquirente) {

        String sql="SELECT idlista_di_acquisto as id, nome, stato FROM mydb.lista_di_acquisto where utente='"+utenteAcquirente.getId()+"' and stato='pagata';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        String id;
        try {
            ArrayList<ListaDiAcquisto> listaDiAcquistoArrayList= new ArrayList<>();
            while (rs.next()){

                ListaDiAcquisto listaDiAcquisto= new ListaDiAcquisto();
                id=(rs.getString("id"));

                listaDiAcquisto.setId(id);
                listaDiAcquisto.setNome(rs.getString("nome"));
                String stato= rs.getString("stato");
                if (stato.equalsIgnoreCase("pagata"))
                    listaDiAcquisto.setPagamento(true);

                listaDiAcquisto.setUtenteAcquirente(utenteAcquirente);
                popolaLista(listaDiAcquisto);
                listaDiAcquistoArrayList.add(listaDiAcquisto);

            }

            return listaDiAcquistoArrayList;

        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Articolo> getSingleArticolo(UtenteAcquirente utenteAcquirente){
        String sql =  "SELECT DISTINCT mydb.contenuto_lista.articolo as id,  mydb.articolo.tipo FROM mydb.contenuto_lista join mydb.lista_di_acquisto on mydb.contenuto_lista.lista = mydb.lista_di_acquisto.idlista_di_acquisto join mydb.articolo on mydb.contenuto_lista.articolo=mydb.articolo.idarticolo where mydb.lista_di_acquisto.utente='"+utenteAcquirente.getId()+"';";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        String id;

        try {
            ArrayList<Articolo> listaDiAcquistoArrayList= new ArrayList<>();
            while (rs.next()){


                id=(rs.getString("id"));
                String tipo= rs.getString("tipo");
                if (tipo.equalsIgnoreCase("composito")){
                    listaDiAcquistoArrayList.add(ArticoloCompositoDao.getInstance().findArticolo(id));
                }
                if (tipo.equalsIgnoreCase("prodotto")){
                    listaDiAcquistoArrayList.add(ProdottoDao.getInstance().findArticolo(id));
                }
                if (tipo.equalsIgnoreCase("servizio")){
                    listaDiAcquistoArrayList.add(ServizioDao.getInstance().findArticolo(id));
                }

            }

            return listaDiAcquistoArrayList;

        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }

        return null;
    }

    private void popolaLista(ListaDiAcquisto listaDiAcquisto) {

        String sql="SELECT mydb.articolo.tipo, mydb.articolo.idarticolo as id FROM mydb.articolo JOIN mydb.contenuto_lista ON mydb.articolo.idarticolo = mydb.contenuto_lista.articolo WHERE contenuto_lista.lista='"+listaDiAcquisto.getId()+"';";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            while (rs.next()){
                String tipo= rs.getString("tipo");
                if (tipo.equalsIgnoreCase("composito")){

                    ArticoloCompositoDao.getInstance().findArticolo(rs.getString("id"));
                }
                if (tipo.equalsIgnoreCase("servizio")){

                    listaDiAcquisto.setLista(ServizioDao.getInstance().findArticolo(rs.getString("id")));
                }
                if (tipo.equalsIgnoreCase("prodotto")){
                    listaDiAcquisto.setLista(ProdottoDao.getInstance().findArticolo(rs.getString("id")));
                }
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }

    }

    @Override
    public ListaDiAcquisto getLista(UtenteAcquirente utenteAcquirente, String nome) {

        String sql="SELECT idlista_di_acquisto as id, nome, stato FROM mydb.lista_di_acquisto where utente='"+utenteAcquirente.getId()+"' and nome= '"+nome+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        String id;
        try {
            rs.next();
            if(rs.getRow()==1) {

                ListaDiAcquisto listaDiAcquisto= new ListaDiAcquisto();
                id=(rs.getString("id"));

                listaDiAcquisto.setId(id);
                listaDiAcquisto.setNome(rs.getString("nome"));
                String stato= rs.getString("stato");
                if (stato.equalsIgnoreCase("pagata"))
                    listaDiAcquisto.setPagamento(true);

                listaDiAcquisto.setUtenteAcquirente(utenteAcquirente);
                popolaLista(listaDiAcquisto);
                return listaDiAcquisto;

            }



        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return null;
    }

    @Override
    public int addLista(ListaDiAcquisto listaDiAcquisto, UtenteAcquirente utenteAcquirente) {
        String sql= "INSERT INTO `mydb`.`lista_di_acquisto` (`nome`, `utente`) VALUES ('"+listaDiAcquisto.getNome()+"', '"+utenteAcquirente.getId()+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;
        return UtilityDao.getInstance().getMax("lista_di_acquisto", "idlista_di_acquisto");
    }

    @Override
    public int removeLista(ListaDiAcquisto listaDiAcquisto) {
        String sql="DELETE FROM `mydb`.`lista_di_acquisto` WHERE (`idlista_di_acquisto` = '"+listaDiAcquisto.getId()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;
        return rowCount;
    }

    @Override
    public int updateLista(ListaDiAcquisto listaDiAcquisto) {
        int i=0;
        String sql1= "DELETE FROM `mydb`.`contenuto_lista` WHERE (`lista` = '"+listaDiAcquisto.getId()+"');";
        DbOperationeExecutor executor1 = new DbOperationeExecutor();
        IDbOperation writeOp1 = new WriteOperation(sql1);
        rowCount=executor1.updateOperation(writeOp1);
        for (Articolo a :listaDiAcquisto.getLista()){
            String sql="INSERT INTO `mydb`.`contenuto_lista` (`articolo`, `lista`) VALUES ('"+a.getId()+"', '"+listaDiAcquisto.getId()+"');";

            DbOperationeExecutor executor = new DbOperationeExecutor();
            IDbOperation writeOp = new WriteOperation(sql);
            i+=rowCount=executor.updateOperation(writeOp);

        }
        return i;
    }

    @Override
    public int setPagamento(boolean b, ListaDiAcquisto listaDiAcquisto) {
        if (b) {
            String sql = "UPDATE `mydb`.`lista_di_acquisto` SET `stato` = 'pagata' WHERE (`idlista_di_acquisto` = '" + listaDiAcquisto.getId() + "');";
            DbOperationeExecutor executor = new DbOperationeExecutor();
            IDbOperation writeOp = new WriteOperation(sql);
            rowCount=executor.updateOperation(writeOp);
            if(rowCount<0)
                return -1;
            return rowCount;
        }
        return -2;
    }

    @Override
    public ArrayList<String> getNomi(UtenteAcquirente utenteAcquirente) {
        String sql= "SELECT nome FROM mydb.lista_di_acquisto where utente='"+utenteAcquirente.getId()+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        String id;
        try {
            ArrayList<String> nomi=new ArrayList<>();
            while (rs.next()){


                id=(rs.getString("nome"));
                nomi.add(id);

            }

            return nomi;

        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return null;
    }
}
