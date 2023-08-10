package Buisness.Utente;

public class LoginResult {
    public enum Result {LOGIN_OK, PASSWORD_ERRATA,UTENTE_NON_ESISTE, UTENTE_BLOCCATO}

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
