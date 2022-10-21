package Base;
import java.time.LocalDate;

public class User {
    private String name;
    private String lastname;
    private String login;
    private String password;
    private String gender;
    private String location;
    private String address;
    private String flat;
    private LocalDate date;

    //конструктор
    public User(String name, String lastname, String login, String password,
                String gender, String location, String address, String flat, LocalDate date) {
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.location = location;
        this.address = address;
        this.flat = flat;
        this.date = date;
    }

    //конструктор по умолчанию
    public User() {}

    //GET-теры и SET-теры
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlat() {
        return flat;
    }
    public void setFlat(String flat) {
        this.flat = flat;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}