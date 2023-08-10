package Dao.Categorie;



import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Model.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CategoriaDao implements ICategoriaDao{

    private static final CategoriaDao instance = new CategoriaDao();
    private static ResultSet rs;
    private static int rowCount;
    public static CategoriaDao getInstance() {
        return instance;
    }


    @Override
    public int addCategoria(Object categoria) {

        if (!(categoria instanceof Categoria categorie)) {
            System.out.println("L'oggetto passato non appartiene alla classe Categoria");
            return -2;
        }
        String sql;
        if(((Categoria) categoria).getCategoriaPadre()==null){
            sql="INSERT INTO `mydb`.`categoria` (`nome`, `descrizione`) VALUES ('"+((Categoria) categoria).getNome() + "', '"+((Categoria) categoria).getDescrizione()+"');";
        }else{
            String id=((Categoria) categoria).getId();
            sql = "INSERT INTO `mydb`.`categoria` (`nome`, `descrizione`, `categoria`) VALUES ('" + ((Categoria) categoria).getNome() + "', '" + ((Categoria) categoria).getDescrizione() + "', '" + id + "');";
        }

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;
        return rowCount;
    }

    @Override
    public Categoria findCategoria(String id) {

        System.out.println("ID " + id);
        if(id==null)
            return null;
        DbOperationeExecutor executor = new DbOperationeExecutor();

        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.categoria where idcategoria= '"+id+"';");
        rs = executor.executeOperation(readOp);

        try {
             rs.next();
           if (rs.getRow()==1) {
                Categoria categoria= new Categoria();
                categoria.setId(id);
                categoria.setNome(rs.getString("nome"));
                categoria.setDescrizione(rs.getString("descrizione"));
                String tmp= rs.getString("categoria");
               System.out.println("Categoria" + tmp);
                if(tmp==null){
                    categoria.setCategoriaPadre(null);
                }else {
                    categoria.setCategoriaPadre(findCategoria(tmp));
                }

                return categoria;
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
    public ArrayList<Categoria> findCategoriaByName(String id) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.categoria where idcategoria= = '"+id+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Categoria> categorie= new ArrayList<>();
            while (rs.next()){
                Categoria categoria= new Categoria();
                categoria.setNome(id);
                categoria.setId(rs.getString("idcategoria"));
                categoria.setDescrizione(rs.getString("descrizione"));
                String tmp= rs.getString("categoria");
                if(tmp==null){
                    categoria.setCategoriaPadre(null);
                }else {

                    categoria.setCategoriaPadre(findCategoria(tmp));
                }

                categorie.add(categoria);
            }
            return categorie;

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
    public int update(Object categoria) {
        if (!(categoria instanceof Categoria)) {
            System.out.println("L'oggetto passato non appartiene alla classe Categoria");
            return -2;
        }

        String sql;
        if(((Categoria) categoria).getCategoriaPadre()==null){
            String id=((Categoria) categoria).getId();
            sql = "UPDATE `mydb`.`categoria` SET `nome` = '"+((Categoria) categoria).getNome()+"', `descrizione` = '"+((Categoria) categoria).getDescrizione()+"', `categoria` = '"+id+"' WHERE (`idcategoria` = '"+((Categoria) categoria).getId()+"');";

        }else{
            sql="UPDATE `mydb`.`categoria` SET `nome` = '"+((Categoria) categoria).getNome()+"', `descrizione` = '"+((Categoria) categoria).getDescrizione()+"' WHERE (`idcategoria` = '"+((Categoria) categoria).getId()+"');";
        }

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;
        return rowCount;
    }

    @Override
    public int remove(String id) {
        String sql;
        sql="DELETE FROM `mydb`.`categoria` WHERE (`idcategoria` = '"+id+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;
        return rowCount;
    }
}
