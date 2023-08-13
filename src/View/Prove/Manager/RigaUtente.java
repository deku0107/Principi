package View.Prove.Manager;

import Model.Utenti.UtenteAcquirente;

public class RigaUtente {//da inserire della view model

    private int idUtente;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private UtenteAcquirente.Stato stato;//


    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public UtenteAcquirente.Stato getStato() {
        return stato;
    }

    public String getStatoString(){
        if(stato== UtenteAcquirente.Stato.ATTIVO){
            return "attivo";
        }else if(stato== UtenteAcquirente.Stato.BLOCCATO){
            return "bloccato";
        }else{return "eliminato";}

    }
    public void setStato(UtenteAcquirente.Stato stato) {
        this.stato = stato;
    }
}
