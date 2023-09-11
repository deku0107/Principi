package Model.Utenti;

import Model.Data;

public class Manager extends Utente {

    private Data inizioIncarico;
    private Data fineIncarico;
    private float stipendio;

    public Data getInizioIncarico() {
        return inizioIncarico;
    }

    public void setInizioIncarico(Data inizioIncarico) {
        this.inizioIncarico = inizioIncarico;
    }

    public Data getFineIncarico() {
        return fineIncarico;
    }

    public void setFineIncarico(Data fineIncarico) {
        this.fineIncarico = fineIncarico;
    }

    public float getStipendio() {
        return stipendio;
    }

    public void setStipendio(float stipendio) {
        this.stipendio = stipendio;
    }



}

