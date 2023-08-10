package Buisness.Utente;

public class RegisterResult {
    public enum Result {REGISTRAZIONE_OK, NOME_UTENTE_ESISTENTE, MAIL_GIA_REGISTRATA, REGISTRAZIONE_FALLITA}

    private Result result;
    private String messaggio;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
}
