import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //окно, открывающееся первым
        Parent root = FXMLLoader.load(getClass().getResource("View/sample.fxml"));

        //оглавление окна
        primaryStage.setTitle("Energy Resource Management Devise 1.1");

        //иконка
        InputStream logo = getClass().getResourceAsStream("Images/icon.png");
        Image icon = new Image(logo);
        primaryStage.getIcons().add(icon);

        //нельзя изменять размеры окна
        primaryStage.setResizable(false);

        //размеры окна
        primaryStage.setScene(new Scene(root, 900, 527));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}