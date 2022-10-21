package Controllers;
import Animation.Shake;
import Base.User;
import MySQL.DatabaseHandler;
import com.sun.webkit.graphics.WCImage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.sun.webkit.graphics.WCImage.getImage;

public class SampleController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private PasswordField password_field;     //поле с паролем
    @FXML
    private TextField login_field;         //поле с логином
    @FXML
    private Button login_button;        //кнопка регистрации
    @FXML
    private Button signIn_button;      //кнопка входа
    @FXML
    private MenuBar menu;         //панель меню

    //метод авторизации пользователей
    private void loginUser(String loginText, String passwordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(passwordText);

        ResultSet result = dbHandler.getUser(user);
        int count = 0;

        //перебор всех возможных пользователей
        try {
            while (result.next())
                count++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count >= 1) {
            System.out.println("Success!");

            signIn_button.getScene().getWindow().hide();   //прячем текущее окно

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
        } else {
            Shake loginAnimation = new Shake(login_field);
            Shake passwordAnimation = new Shake(password_field);

            loginAnimation.playAnimation();       //анимация
            passwordAnimation.playAnimation();

            login_field.clear();                   //очистка полей
            password_field.clear();
            System.out.println("This user is not found...");
        }
    }

    @FXML
    void initialize() {
        //кнопка входа
        signIn_button.setOnAction(event -> {

            //переменные проверки полей авторизации
            boolean condition = false, condition1 = false;

            //провера логина
            String login = login_field.getText().trim();
            Shake login_animation = new Shake(login_field);
            if (login.isEmpty()) {
                login_animation.playAnimation();
                login_field.clear();
            }
            else condition = true;

            //проверка пароля
            String password = password_field.getText().trim();
            Shake password_animation = new Shake(password_field);
            if (password.isEmpty()) {
                password_animation.playAnimation();
                password_field.clear();
            }
            else condition1 = true;

            //разрешение на вход если все поля заполнены
            if (condition && condition1)
                loginUser(login, password);
            else
                System.out.println("WARNING!" + "\nBe attentive! Some fields are empty or wrong-completed");
        });

        //нажатие на ENTER
        signIn_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                signIn_button.fire();
        });
        signIn_button.setDefaultButton(true);

        //кнопка регистрации
        login_button.setOnAction(event -> {
            login_button.getScene().getWindow().hide();   //прячем текущее окно

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/signUp.fxml"));   //указываем путь к новому

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registering a new ERM–user");
            stage.setResizable(false);
            stage.getIcons().add(new Image("Images/icon.png"));
            stage.show();
        });

        //меню + привязка клавиш
        {
            Menu fileMenu = new Menu("Файл");
            Menu editMenu = new Menu("Редактировать");
            Menu helpMenu = new Menu("Помощь");

            MenuItem registerItem = new MenuItem("Регистрация  ");
            registerItem.setGraphic(new ImageView("Images/reg.png"));

            MenuItem autoItem = new MenuItem("Авторизация  ");
            autoItem.setGraphic(new ImageView("Images/auto.png"));

            MenuItem exitItem = new MenuItem("Выход  ");
            exitItem.setGraphic(new ImageView("Images/ex.png"));

            fileMenu.getItems().addAll(registerItem, autoItem, exitItem);

            MenuItem copyItem = new MenuItem("Копировать");
            copyItem.setGraphic(new ImageView("Images/copy.png"));

            MenuItem pasteItem = new MenuItem("Вставить");
            pasteItem.setGraphic(new ImageView("Images/paste.jpg"));

            editMenu.getItems().addAll(copyItem, pasteItem);

            MenuItem helpItem1 = new MenuItem("* Все поля являются обязательными для заполнения");
            MenuItem helpItem2 = new MenuItem("* Для входа в аккаунт введите свои данные");
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
                    login_button.getScene().getWindow().hide();   //прячем текущее окно

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/sample.fxml"));   //указываем путь к новому

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Energy Resource Management Devise 1.1");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("Images/icon.png"));
                    stage.show();
                });

                registerItem.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
                registerItem.setOnAction(event -> {
                    login_button.getScene().getWindow().hide();   //прячем текущее окно

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/View/signUp.fxml"));   //указываем путь к новому

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Registering a new ERM–user");
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
            signIn_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    signIn_button.setEffect(shadow);
                }
            });
            signIn_button.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    signIn_button.setEffect(null);
                }
            });

            login_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_button.setEffect(shadow);
                }
            });
            login_button.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_button.setEffect(null);
                }
            });

            login_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_field.setEffect(shadow);
                }
            });
            login_field.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_field.setEffect(null);
                }
            });

            password_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    password_field.setEffect(shadow);
                }
            });
            password_field.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    password_field.setEffect(null);
                }
            });
        }
    }
}