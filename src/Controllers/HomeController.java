package Controllers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button static_button;     //статистика
    @FXML
    private CheckBox check_1;        //холодильник
    @FXML
    private CheckBox check_2;        //микроволновка
    @FXML
    private CheckBox check_3;        //стиралная машина
    @FXML
    private CheckBox check_4;        //кондиционер
    @FXML
    private CheckBox check_5;        //телевизор
    @FXML
    private CheckBox check_6;        //чайник
    @FXML
    private CheckBox check_7;        //ПК
    @FXML
    private CheckBox check_8;        //утюг
    @FXML
    private CheckBox check_9;        //плита
    @FXML
    private Button leave_button;          //выход из аккаунта
    @FXML
    private Button user_button;         //настройки профиля
    @FXML
    private Button worry_button;           //отключение всего
    @FXML
    private Slider lightSlider;         //освещние
    @FXML
    private Label infoLabel;         //процент
    @FXML
    private MenuBar menu;         //панель меню

    @FXML
    void initialize() {
        //кнопка выхода из аккаунта
        leave_button.setOnAction(event -> {
            leave_button.getScene().getWindow().hide();   //прячем текущее окно

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

        //включение или выключение приборов
        check_1.setOnAction(event -> {
            if (check_1.isSelected())
                System.out.println("Fridge is in the switched-ON state");
            else
                System.out.println("Fridge is in the switched-OFF state");
        });
        check_2.setOnAction(event -> {
            if (check_2.isSelected())
                System.out.println("Microwave is in the switched-ON state");
            else
                System.out.println("Microwave is in the switched-OFF state");
        });
        check_3.setOnAction(event -> {
            if (check_3.isSelected())
                System.out.println("Washing machine is in the switched-ON state");
            else
                System.out.println("Washing machine is in the switched-OFF state");
        });
        check_4.setOnAction(event -> {
            if (check_4.isSelected())
                System.out.println("Air-conditioning is in the switched-ON state");
            else
                System.out.println("Air-conditioning is in the switched-OFF state");
        });
        check_5.setOnAction(event -> {
            if (check_5.isSelected())
                System.out.println("TV is in the switched-ON state");
            else
                System.out.println("TV is in the switched-OFF state");
        });
        check_6.setOnAction(event -> {
            if (check_6.isSelected())
                System.out.println("Kettle is in the switched-ON state");
            else
                System.out.println("Kettle is in the switched-OFF state");
        });
        check_7.setOnAction(event -> {
            if (check_7.isSelected())
                System.out.println("Computer is in the switched-ON state");
            else
                System.out.println("Computer is in the switched-OFF state");
        });
        check_8.setOnAction(event -> {
            if (check_8.isSelected())
                System.out.println("Iron is in the switched-ON state");
            else
                System.out.println("Iron is in the switched-OFF state");
        });
        check_9.setOnAction(event -> {
            if (check_9.isSelected())
                System.out.println("Cooker is in the switched-ON state");
            else
                System.out.println("Cooker is in the switched-OFF state");
        });

        //аварийное отключение
        worry_button.setOnAction(event -> {
            System.out.println("All devices are turned OFF");
            check_1.setSelected(false);
            check_2.setSelected(false);
            check_3.setSelected(false);
            check_4.setSelected(false);
            check_5.setSelected(false);
            check_6.setSelected(false);
            check_7.setSelected(false);
            check_8.setSelected(false);
            check_9.setSelected(false);
        });

        //настройки профиля
        user_button.setOnAction(event -> {
            user_button.getScene().getWindow().hide();   //прячем текущее окно

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
            stage.setTitle("ERM–user profile settings");
            stage.setResizable(false);
            stage.getIcons().add(new Image("Images/icon.png"));
            stage.show();
        });

        //кнопка статистики
        static_button.setOnAction(event -> {
            static_button.getScene().getWindow().hide();   //прячем текущее окно

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/statistic.fxml"));   //указываем путь к новому

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ERM–user's statistic view");
            stage.setResizable(false);
            stage.getIcons().add(new Image("Images/icon.png"));
            stage.show();
        });

        //слидер
        lightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double exp = lightSlider.getValue();
                String position = String.format("%.0f", exp);
                infoLabel.setText("Свет выставлен на: " + position + "%");
            }
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

            MenuItem helpItem1 = new MenuItem("* Здеть можно поуправлять приборами");
            MenuItem helpItem2 = new MenuItem("* Свет кстати тоже регулируется");
            MenuItem helpItem3 = new MenuItem("* Как на счёт глянуть статистику?");
            MenuItem helpItem4 = new MenuItem("* Или может зайти в настройки?");
            helpMenu.getItems().addAll(helpItem1, helpItem2, helpItem3, helpItem4);

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
                    stage.setTitle("ERM-user account");
                    stage.setResizable(false);
                    stage.getIcons().add(new Image("Images/icon.png"));
                    stage.show();
                });

                setItem.setAccelerator(KeyCombination.keyCombination("Ctrl+T"));
                setItem.setOnAction(event -> {
                    user_button.getScene().getWindow().hide();   //прячем текущее окно

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
                    stage.setTitle("ERM–user profile settings");
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
            static_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    static_button.setEffect(shadow);
                }
            });
            static_button.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    static_button.setEffect(null);
                }
            });

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

            user_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    user_button.setEffect(shadow);
                }
            });
            user_button.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    user_button.setEffect(null);
                }
            });

            worry_button.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    worry_button.setEffect(shadow);
                }
            });
            worry_button.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    worry_button.setEffect(null);
                }
            });

            check_1.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_1.setEffect(shadow);
                }
            });
            check_1.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_1.setEffect(null);
                }
            });

            check_2.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_2.setEffect(shadow);
                }
            });
            check_2.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_2.setEffect(null);
                }
            });

            check_3.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_3.setEffect(shadow);
                }
            });
            check_3.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_3.setEffect(null);
                }
            });

            check_4.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_4.setEffect(shadow);
                }
            });
            check_4.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_4.setEffect(null);
                }
            });

            check_5.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_5.setEffect(shadow);
                }
            });
            check_5.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_5.setEffect(null);
                }
            });

            check_6.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_6.setEffect(shadow);
                }
            });
            check_6.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_6.setEffect(null);
                }
            });

            check_7.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_7.setEffect(shadow);
                }
            });
            check_7.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_7.setEffect(null);
                }
            });

            check_8.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_8.setEffect(shadow);
                }
            });
            check_8.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_8.setEffect(null);
                }
            });

            check_9.addEventHandler(MouseEvent.MOUSE_ENTERED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_9.setEffect(shadow);
                }
            });
            check_9.addEventHandler(MouseEvent.MOUSE_EXITED, new javafx.event.EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    check_9.setEffect(null);
                }
            });
        }
    }
}