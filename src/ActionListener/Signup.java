package ActionListener;

import Buisness.Utente.UtenteBusiness;
import Model.Utenti.UtenteAcquirente;
import ViewSceneBuilder.Login.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.time.LocalDate;

public class Signup {

    public Signup(){}

    @FXML
    private Button buttonChangePicture;
    @FXML
    private TextField nomeR;
    @FXML
    private TextField cognomeR;
    @FXML
    private TextField usernameR;
    @FXML
    private TextField emailR;
    @FXML
    private TextField telefonoR;
    @FXML
    private TextField indirizzoR;
    @FXML
    private TextField cittaR;
    @FXML
    private PasswordField passwordR;
    @FXML
    private Button buttonRegistration;
    @FXML
    private Button buttonCancelRegistration;
    @FXML
    private DatePicker nascitaR;
    @FXML
    private BorderPane panelRegistration;


    public void CancelListener(ActionEvent actionEvent) throws IOException {

        Main m= new Main();
        m.changeDimension(616,439);
        m.changeScene("login.fxml");
    }



    public void RegisterListener(ActionEvent actionEvent) throws IOException{
        boolean check=true;
        do {
            if(nomeR==null || nomeR.getText().equalsIgnoreCase("")) {
                check=false;

            }
            if(cognomeR==null || cognomeR.getText().equalsIgnoreCase("")) {
                check=false;
            }
            if(usernameR==null || usernameR.getText().equalsIgnoreCase("")) {
                check=false;
            }
            if(emailR==null || emailR.getText().equalsIgnoreCase("")) {
                check=false;
            }
            if(passwordR==null || passwordR.getText().equalsIgnoreCase("")) {
                check=false;
            }
            if(nascitaR==null) {
                check=false;
            }
        }while(check);


        LocalDate date=nascitaR.getValue();
        int day =date.getDayOfMonth();
        int month= date.getMonthValue();
        int year= date.getYear();
        String dateDB=year+"-"+month+"-"+day;

        UtenteAcquirente utenteAcquirente= new UtenteAcquirente();
        utenteAcquirente.setNome(nomeR.getText());
        utenteAcquirente.setCognome(cognomeR.getText());
        utenteAcquirente.setCitta(cittaR.getText());
        utenteAcquirente.setIndirizzo(indirizzoR.getText());
        utenteAcquirente.setTelefono(telefonoR.getText());
        utenteAcquirente.getDataDiNascita().setData(dateDB);
        utenteAcquirente.setEmail(emailR.getText());

        UtenteBusiness.getInstance().registrazione(utenteAcquirente, passwordR.getText());




    }

    }

