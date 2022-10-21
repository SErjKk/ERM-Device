package Controllers;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import Animation.Shake;
import Base.*;
import MySQL.DatabaseHandler;
import com.sun.webkit.graphics.WCImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import static MySQL.Const.*;
import static com.sun.webkit.graphics.WCImage.getImage;

public class SignUpController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button main_button;                //кнопка возврата на главную страницу
    @FXML
    private PasswordField password_field;      //поле с паролем
    @FXML
    private Button login_button;              //кнопка регистрации
    @FXML
    private TextField login_field;             //поле с паролем
    @FXML
    private TextField flat_field;             //поле квартира
    @FXML
    private RadioButton radioButton_male;     //кнопка: мужской пол
    @FXML
    private RadioButton radioButton_female;    //кнопка: женский пол
    @FXML
    private TextField address_field;           //поле адрес
    @FXML
    private TextField name_field;              //поле с именем
    @FXML
    private TextField lastName_field;         //поле с фамилией
    @FXML
    private ComboBox<String> location_choice;        //район
    @FXML
    private DatePicker date;               //дата рождения
    @FXML
    private MenuBar menu;                  //панель меню

    //метод регистрации нового пользователя
    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String nameText = name_field.getText();
        String lastnameText = lastName_field.getText();
        String loginText = login_field.getText();
        String passwordText = password_field.getText();
        String genderText = "";
        String locationText = location_choice.getValue();
        String addressText = address_field.getText();
        String flatText = flat_field.getText();
        LocalDate dateText = date.getValue();

        if (radioButton_male.isSelected()) {
            genderText = "Мужской";
        } else if (radioButton_female.isSelected()) {
            genderText = "Женский";
        }

        User user = new User(nameText, lastnameText, loginText, passwordText, genderText, locationText, addressText, flatText, dateText);
        dbHandler.signUpUser(user);

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
    }

    @FXML
    void initialize () {
        //выпадающий список
        ObservableList<String> districts = FXCollections.observableArrayList("Выборгский р–н", "Василеостровский р–н", "Адмиралтейский р–н",
        "Кировский р–н", "Невский р–н", "Московский р–н", "Петроградский р–н", "Приморский р–н", "Кронштадтский р–н", "Курортный р–н", "Колпинский р–н",
        "Калининский р–н", "Красногвардейский р–н", "Петродворцовый р–н", "Красносельский р–н");
        location_choice.setItems(districts);
        location_choice.setStyle("-fx-font: 19px \"Century Gothic\";");

        //календарь
        date.setStyle("-fx-font: 17px \"Century Gothic\";");

        //кнопка регистрации
        login_button.setOnAction(event -> {

            //переменные проверки полей регистрации
            boolean condition = false,  condition1 = false, condition2 = false,
                    condition3 = false, condition4 = false, condition5 = false,
                    condition6 = false, condition7 = false, condition8 = false;

            //проверка имени:
            String name = name_field.getText().trim();
            Shake name_animation = new Shake(name_field);
            if (name.isEmpty()) {
                name_animation.playAnimation();
                name_field.clear();
            }
            else if (CheckField.checkThisField(name, NUMBERS)) {
                name_animation.playAnimation();
                name_field.clear();
            }
            else if (CheckField.checkThisField(name, SYMBOLS)) {
                name_animation.playAnimation();
                name_field.clear();
            }
            else condition = true;

            //проверка фамилии:
            String lastName = lastName_field.getText().trim();
            Shake lastName_animation = new Shake(lastName_field);
            if (lastName.isEmpty()) {
                lastName_animation.playAnimation();
                lastName_field.clear();
            }
            else if (CheckField.checkThisField(lastName, NUMBERS)) {
                lastName_animation.playAnimation();
                lastName_field.clear();
            }
            else if (CheckField.checkThisField(lastName, SYMBOLS)) {
                lastName_animation.playAnimation();
                lastName_field.clear();
            }
            else condition1 = true;

            //проверка квартиры:
            String flat = flat_field.getText().trim();
            Shake flat_animation = new Shake(flat_field);
            if (flat.isEmpty()) {
                flat_animation.playAnimation();
                flat_field.clear();
            }
            else if (CheckField.checkThisField(flat, RUS_LETTERS)) {
                flat_animation.playAnimation();
                flat_field.clear();
            }
            else if (CheckField.checkThisField(flat, EN_LETTERS)) {
                flat_animation.playAnimation();
                flat_field.clear();
            }
            else if (CheckField.checkThisField(flat, SYMBOLS)) {
                flat_animation.playAnimation();
                flat_field.clear();
            }
            else condition2 = true;

            //проверка логина:
            String login = login_field.getText().trim();
            Shake login_animation = new Shake(login_field);
            if (login.isEmpty()) {
                login_animation.playAnimation();
                login_field.clear();
            }
            else condition3 = true;

            //проверка пароля:
            String password = password_field.getText().trim();
            Shake password_animation = new Shake(password_field);
            if (password.isEmpty()) {
                password_animation.playAnimation();
                password_field.clear();
            }
            else condition4 = true;

            //проверка района:
            try {
                String loc = location_choice.getValue();
                Shake loc_animation = new Shake(location_choice);

                if (loc.isEmpty()) {
                    loc_animation.playAnimation();
                }
                else condition5 = true;
            } catch (Exception e) {
                System.out.println("WARNING!");
            }

            //проверка адреса:
            String address = address_field.getText().trim();
            Shake address_animation = new Shake(address_field);
            if (address.isEmpty()) {
                address_animation.playAnimation();
                address_field.clear();
            }
            else if (CheckField.checkThisField(address, SYMBOLS)) {
                address_animation.playAnimation();
                address_field.clear();
            }
            else condition6 = true;

            //проверка пола:
            Shake female_animation = new Shake(radioButton_female);
            Shake male_animation = new Shake(radioButton_male);
            if (!radioButton_male.isSelected() && !radioButton_female.isSelected()) {
                radioButton_male.setVisible(false);
                radioButton_female.setVisible(false);
                male_animation.playAnimation();
                female_animation.playAnimation();
            }
            else condition7 = true;

            //проверка даты
            try {
                LocalDate birthday = date.getValue();
                String year = String.valueOf(birthday.getYear());
                Shake date_animation = new Shake(date);

                if (Integer.parseInt (year) > 2003) {
                    System.out.println("You're too young, it's so cute, but get out of here");
                    date_animation.playAnimation();
                }
                else condition8 = true;
            } catch (Exception e) {
                System.out.println("WARNING!");
            }

            //разрешение на регистрацию если все поля заполнены
            if (condition && condition1 && condition2 && condition3 && condition4 && condition5 && condition6 && condition7 && condition8)
                signUpNewUser();
            else
                System.out.println("Be attentive! Some fields are empty or wrong-completed");
        });

        //нажатие на ENTER
        login_button.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
                login_button.fire();
        });
        login_button.setDefaultButton(true);

        //radioButton_male + radioButton_female
        radioButton_male.setOnAction(event -> {
            if (radioButton_male.isSelected())
                radioButton_female.setSelected(false);
        });
        radioButton_female.setOnAction(event -> {
            if (radioButton_female.isSelected())
                radioButton_male.setSelected(false);
        });

        //кнопка возврата
        main_button.setOnAction(event -> {
            main_button.getScene().getWindow().hide();   //прячем текущее окно

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
            helpMenu.getItems().addAll(helpItem1);

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
            main_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    main_button.setEffect(shadow);
                }
            });
            main_button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    main_button.setEffect(null);
                }
            });

            login_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_button.setEffect(shadow);
                }
            });
            login_button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_button.setEffect(null);
                }
            });

            radioButton_male.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    radioButton_male.setEffect(shadow);
                }
            });
            radioButton_male.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    radioButton_male.setEffect(null);
                }
            });

            radioButton_female.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    radioButton_female.setEffect(shadow);
                }
            });
            radioButton_female.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    radioButton_female.setEffect(null);
                }
            });

            login_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_field.setEffect(shadow);
                }
            });
            login_field.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    login_field.setEffect(null);
                }
            });

            password_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    password_field.setEffect(shadow);
                }
            });
            password_field.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    password_field.setEffect(null);
                }
            });

            name_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    name_field.setEffect(shadow);
                }
            });
            name_field.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    name_field.setEffect(null);
                }
            });

            lastName_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    lastName_field.setEffect(shadow);
                }
            });
            lastName_field.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    lastName_field.setEffect(null);
                }
            });

            location_choice.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    location_choice.setEffect(shadow);
                }
            });
            location_choice.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    location_choice.setEffect(null);
                }
            });

            address_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    address_field.setEffect(shadow);
                }
            });
            address_field.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    address_field.setEffect(null);
                }
            });

            date.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    date.setEffect(shadow);
                }
            });
            date.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    date.setEffect(null);
                }
            });

            flat_field.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    flat_field.setEffect(shadow);
                }
            });
            flat_field.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    flat_field.setEffect(null);
                }
            });
        }
    }
}