package Buisness.Utente;

public class PasswordResult {
    public enum Result {EMAIL_NON_VALIDA, PASSWORD_AGGIORNATA,ERRORE}

    private PasswordResult.Result result;
    private String messaggio;

    public PasswordResult.Result getResult() {
        return result;
    }

    public void setResult(PasswordResult.Result result) {
        this.result = result;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
