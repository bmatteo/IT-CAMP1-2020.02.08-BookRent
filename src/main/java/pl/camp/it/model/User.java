package pl.camp.it.model;

import javax.persistence.*;

@Entity(name = "tuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String pass;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private UserRole role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.id)
                .append(";")
                .append(this.login)
                .append(";")
                .append(this.pass)
                .append(";")
                .append(this.role);

        return stringBuilder.toString();
    }
}
