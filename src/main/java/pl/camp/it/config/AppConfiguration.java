package pl.camp.it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.session.SessionObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {

    //@Bean
    //@SessionScope
    //public SessionObject sessionObject() {
        //return new SessionObject();
   // }

    @Bean
    public Connection connection() {
        try {
            String url = "jdbc:mysql://localhost:3306/bookRent?user=root&password=";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Błąd modczas łączenia !!");
            e.printStackTrace();
        }

        return null;
    }
}
