package Model.Prodotti;

import java.util.ArrayList;

public class ArticoloComposito extends Prodotto{

    private float sconto;
    private ArrayList<Composizione> composizione;



    public float getSconto() {
        return sconto;
    }

    public void setSconto(float sconto) {
        this.sconto = sconto;
    }


    public ArrayList<Composizione> getComposizione() {
        return composizione;
    }

    public void setComposizione(ArrayList<Composizione> composizione) {
        this.composizione = composizione;
    }

    public void setComposizione(Composizione composizione) {
       if (this.composizione==null) {
            this.composizione = new ArrayList<>();
        }
        this.composizione.add(composizione);
    }

}


