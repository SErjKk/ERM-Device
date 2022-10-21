package Controllers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button leave_button;    //кнопка назад
    @FXML
    private MenuBar menu;         //панель меню

    @FXML
    void initialize() {
        //кнопка назад
        leave_button.setOnAction(event -> {
            leave_button.getScene().getWindow().hide();   //прячем текущее окно

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/home.fxml"));   //указываем путь к новому

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ERM–user account");
            stage.setResizable(false);
            stage.getIcons().add(new Image("Images/icon.png"));
            stage.show();
        });

        //меню + привязка клавиш
        {
            Menu fileMenu = new Menu("Файл");
            Menu editMenu = new Menu("Редактировать");
            Menu helpMenu = new Menu("Помощь");

            MenuItem setItem = new MenuItem("Настройки  ");
            setItem.setGraphic(new ImageView("Images/set.png"));

            MenuItem autoItem = new MenuItem("Мой аккаунт  ");
            autoItem.setGraphic(new ImageView("Images/auto.png"));

            MenuItem exitItem = new MenuItem("Выход  ");
            exitItem.setGraphic(new ImageView("Images/ex.png"));

            fileMenu.getItems().addAll(setItem, autoItem, exitItem);

            MenuItem copyItem = new MenuItem("Копировать");
            copyItem.setGraphic(new ImageView("Images/copy.png"));

            MenuItem pasteItem = new MenuItem("Вставить");
            pasteItem.setGraphic(new ImageView("Images/paste.jpg"));

            editMenu.getItems().addAll(copyItem, pasteItem);

            MenuItem helpItem1 = new MenuItem("* Тут можно ознакомиться с данными вашего профиля");
            MenuItem helpItem2 = new MenuItem("* Классная фотка, правда?");
            helpMenu.getItems().addAll(helpItem1, helpItem2);

            menu.getMenus().clear();
            menu.setStyle("-fx-font: 13px \"Century Gothic\";");
            menu.getMenus().addAll(fileMenu, editMenu, helpMenu);

            //привязка клавиш
            {
                exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));
                exitItem.setOnAction(event -> {
                    System.exit(0);
                });

                autoItem.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
                autoItem.setOnAction(event -> {
                    leave_button.getScene().getWindow().hide();   //прячем текущее окно

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/home.fxml"));   //указываем путь к новому

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("ERM–user account");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("Images/icon.png"));
                    stage.show();
                });

                setItem.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
                setItem.setOnAction(event -> {
                    leave_button.getScene().getWindow().hide();   //прячем текущее окно

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/user.fxml"));   //указываем путь к новому

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("ERM–user's profile settings");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("Images/icon.png"));
                    stage.show();
                });

                copyItem.setAccelerator(KeyCombination.keyCombination("Ctrl+C"));
                copyItem.setOnAction(event -> {
                    System.out.println("");
                });

                pasteItem.setAccelerator(KeyCombination.keyCombination("Ctrl+V"));
                pasteItem.setOnAction(event -> {
                    System.out.println("");
                });
            }
        }

        //эффект тени на кнопки
        DropShadow shadow = new DropShadow();
        {
            leave_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    leave_button.setEffect(shadow);
                }
            });
            leave_button.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    leave_button.setEffect(null);
                }
            });
        }
    }
}