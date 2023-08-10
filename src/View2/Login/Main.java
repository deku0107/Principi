package View2.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg=primaryStage;
        primaryStage.setResizable(false);
        Parent root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();

    }

    public void changeScene(String fxml) throws IOException{
        Parent parent=FXMLLoader.load((Objects.requireNonNull(getClass().getResource(fxml))));
        stg.getScene().setRoot(parent);

    }

    public void changeDimension(int x, int y) throws IOException{
        stg.setWidth(x);
        stg.setHeight(y);


    }

    public static void main(String[] args) {
        launch(args);
    }

}
