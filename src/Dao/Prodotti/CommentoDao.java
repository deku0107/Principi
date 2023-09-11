package Dao.Prodotti;

import Buisness.ArticoloBuisness;
import Buisness.SessionManager;
import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Dao.ListaDiAcquisto.ListaAcquistoDao;
import Model.Commento;
import Model.Prodotti.Articolo;
import Model.PuntoVendita;
import Model.Utenti.UtenteAcquirente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentoDao implements ICommentoDao{



    private static final CommentoDao i=new CommentoDao();
    public static CommentoDao getInstance(){return i;}
    private static ResultSet rs;
    private static int rowCount;
    @Override
    public ArrayList<Commento> getCommenti(UtenteAcquirente utenteAcquirente) {


        ArrayList<Articolo> articoloArrayList= ListaAcquistoDao.getInstance().getSingleArticolo(utenteAcquirente);
        ArrayList<Commento> commentoArrayList= new ArrayList<>();
        for (Articolo a:articoloArrayList){
            String sql="SELECT * FROM mydb.commento where articolo ='"+a.getId()+"';";
            DbOperationeExecutor executor = new DbOperationeExecutor();
            IDbOperation readOp = new ReadOperation(sql);
            rs = executor.executeOperation(readOp);
            try {

                while (rs.next()){

                   Commento commento= new Commento();
                   commento.setId(rs.getInt("idcommento"));
                   commento.setArticolo(a);
                   commento.setOpenText(rs.getString("testo"));
                   commento.setPunteggio( rs.getInt("valutazione"));

                   commentoArrayList.add(commento);

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

        return commentoArrayList;
    }

    public Commento getCommento(String idArticolo, String idUtente){
        String sql= "SELECT * FROM mydb.commento where articolo ='"+idArticolo+"' and utente = '"+idUtente+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {

            rs.next();
            if (rs.getRow()==1){
                Commento commento= new Commento();
                commento.setId(rs.getInt("idcommento"));
                commento.setOpenText(rs.getString("testo"));
                commento.setPunteggio( rs.getInt("valutazione"));
                return commento;
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

    public ArrayList<Commento> getCommenti(PuntoVendita puntoVendita) {



        ArrayList<Commento> commentoArrayList= new ArrayList<>();
            String sql="SELECT distinct mydb.commento.idcommento as id, mydb.commento.articolo, mydb.commento.testo, mydb.commento.valutazione FROM mydb.commento join mydb.utente_acquirente on mydb.commento.utente=mydb.utente_acquirente.id where mydb.utente_acquirente.id_punto_vendita ='"+ puntoVendita.getId()+"';";
            DbOperationeExecutor executor = new DbOperationeExecutor();
            IDbOperation readOp = new ReadOperation(sql);
            rs = executor.executeOperation(readOp);
            try {

                while (rs.next()){

                    Commento commento= new Commento();
                    commento.setId(rs.getInt("idcommento"));
                    commento.setArticolo(ArticoloBuisness.getInstance().getProdotto(rs.getString("articolo")));
                    commento.setOpenText(rs.getString("testo"));
                    commento.setPunteggio( rs.getInt("valutazione"));

                    commentoArrayList.add(commento);

                }

                return commentoArrayList;
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
    public int commento(Object commento) {
        if (commento instanceof Commento c) {
            UtenteAcquirente utenteAcquirente = (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
            String sql = "INSERT INTO `mydb`.`commento` (`utente`, `articolo`, `testo`, `valutazione`) VALUES ('" + utenteAcquirente.getId() + "', '" + c.getArticolo().getId() + "', '" + c.getOpenText() + "', '" + c.getPunteggioIntero() + "');";
            DbOperationeExecutor executor = new DbOperationeExecutor();
            IDbOperation writeOp = new WriteOperation(sql);
            rowCount = executor.updateOperation(writeOp);
            if (rowCount < 0)
                return -1;
            return rowCount;
        }
        return -2;
    }

    @Override
    public void modificaCommento(Object commento) {

    }

    @Override
    public int  eliminaCommento(Object commento) {

        if (commento instanceof Commento c) {
            String sql = "DELETE FROM `mydb`.`commento` WHERE (`idcommento` = '"+c.getId()+ "');";
            DbOperationeExecutor executor = new DbOperationeExecutor();
            IDbOperation writeOp = new WriteOperation(sql);
            rowCount = executor.updateOperation(writeOp);
            if (rowCount < 0)
                return -1;
            return rowCount;
        }
        return -2;
    }

    public int risposta(String risposta, Commento commento, String idM){

        String sql="INSERT INTO `mydb`.`risposta` (`commento`, `manager`, `testo`) VALUES ('"+commento.getId()+"', '"+idM+"', '"+risposta+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount = executor.updateOperation(writeOp);
        if (rowCount < 0)
            return -1;
        return rowCount;

    }

    public int deleteRisposta(String id){

        String sql="DELETE FROM `mydb`.`risposta` WHERE (`idrisposta` = '"+id+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount = executor.updateOperation(writeOp);
        if (rowCount < 0)
            return -1;
        return rowCount;

    }

    public String getRisposta(Commento commento){

        String sql= "SELECT * FROM mydb.risposta where commento='"+commento.getId()+"';";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {

            rs.next();
            if (rs.getRow()==1){

                return rs.getString("testo");
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

    public String getRispostaId(Commento commento){

        String sql= "SELECT * FROM mydb.risposta where commento='"+commento.getId()+"';";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {

            rs.next();
            if (rs.getRow()==1){

                return rs.getString("idrisposta");
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
}
