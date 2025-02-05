package es.cesguiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        //Ejectuar las migraciones de flyway
        /*String url = PropertiesUtil.getProperty("spring.datasource.url");
        String user = PropertiesUtil.getProperty("spring.datasource.username");
        String password = PropertiesUtil.getProperty("spring.datasource.password");
        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();*/
    }
}
