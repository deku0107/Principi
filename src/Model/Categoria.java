package Model;

public class Categoria {
    private String id;
    private String nome;
    private Categoria categoriaPadre;
    private String descrizione;

    public String getId() {
        if(id==null)
            return "10";
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

    public Categoria getCategoriaPadre() {
        if(categoriaPadre==null)
            return null;
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
