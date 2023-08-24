package ActionListener;

import Buisness.AbstractFactory.AbstractFactoryUtente.FactoryProviderUtente;
import Buisness.Utente.LoginResult;
import Buisness.Utente.PasswordResult;
import Buisness.Utente.UtenteBusiness;
import ViewSceneBuilder.Login.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.io.IOException;

import static Buisness.Utente.LoginResult.Result.LOGIN_OK;

public class UtenteLogin {

    public UtenteLogin(){
    }

    @FXML
    private Button buttonLoginUtente;
    @FXML
    private TextField usernameTextUtente;
    @FXML
    private PasswordField passwordTextUtente;
    @FXML
    private Button buttonManagerLoginUtente;
    @FXML
    private Button buttonAdminLoginUtente;
    @FXML
    private Button buttonSignupUtente;
    @FXML
    private Button forgottenPasswordUtente;
    @FXML
    private BorderPane loginPane;


    public void userLogin(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

    }

    public void LoginListener(ActionEvent actionEvent) {
        System.out.println(loginPane.getHeight());
        System.out.println(loginPane.getWidth());
        String usr=usernameTextUtente.getText();
        String psw=passwordTextUtente.getText();
        LoginResult result=UtenteBusiness.getInstance().login(usr, psw, FactoryProviderUtente.FactoryType.CLIENTE);
        if(LOGIN_OK.equals(result.getResult())){
            //homepage utente
            System.out.println(result.getMessaggio() + " ...");
        }else{
            JOptionPane.showMessageDialog(null, result.getMessaggio(), "Errore", JOptionPane.INFORMATION_MESSAGE);

        }
    }


    public void managerListenerLogin(ActionEvent actionEvent) {
        //pagina login manager
    }

    public void adminListenerLogin(ActionEvent actionEvent) {
        //pagina login admin
    }

    public void registerListener(ActionEvent actionEvent) throws IOException {
        //pagina registrazione
        Main m= new Main();
        m.changeDimension(1073,501);
        m.changeScene("signup.fxml");


    }
}
