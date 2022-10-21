package MySQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Base.User;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    //метод подключения к БД
    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        //Java DataBase Connectivity – плагин подключения к БД
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        //используемый драйвер
        Class.forName("com.mysql.cj.jdbc.Driver");       //com.mysql.jdbc.Driver

        //переменная для подключения пользователя
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    //метод регистрации пользователя
    public void signUpUser(User user) {

        //создаём SQL-запрос для передачи данных
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_NAME + "," + Const.USERS_LASTNAME + ","
                + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + "," + Const.USERS_GENDER + "," + Const.USERS_LOCATION
                + "," + Const.USERS_ADDRESS + "," + Const.USERS_FLAT + "," + Const.USERS_DATE + ")" +
                "VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getName());
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getLogin());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getGender());
            prSt.setString(6, user.getLocation());
            prSt.setString(7, user.getAddress());
            prSt.setString(8, user.getFlat());
            prSt.setString(9, String.valueOf(user.getDate()));

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //метод возвращающий данные из БД
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        //создаём SQL-запрос для выборки данных
        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                        Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}