package Model;

public class Produttore {
    private String id;

    public String getId() {
        if(id==null)
            return "1";
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
