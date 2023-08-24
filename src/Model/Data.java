package Model;

public class Data {
    private int giorno;
    private int mese;
    private int anno;

    public Data(String data){
        setData(data);

    }

    public Data(){}
    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getDataDB(){
        return this.anno + "-"+this.mese+ "-"+ this.giorno;
    }
    public void setData(String data){
        String[] dati = data.split("-");
        this.anno= Integer.parseInt(dati[0]);
        this.mese= Integer.parseInt(dati[1]);
        this.giorno= Integer.parseInt(dati[2]);


    }
}
