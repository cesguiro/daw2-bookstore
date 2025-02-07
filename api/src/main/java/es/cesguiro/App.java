package es.cesguiro;

import es.cesguiro.property.PropertyUtil;
import org.apache.catalina.core.ApplicationContext;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        String url = PropertyUtil.getProperty("flyway.datasource.url");
        String user = PropertyUtil.getProperty("flyway.datasource.username");
        String password = PropertyUtil.getProperty("flyway.datasource.password");
        System.out.println("url: " + url);
        System.out.println("user: " + user);
        System.out.println("password: " + password);
        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .load();
        flyway.migrate();

        SpringApplication.run(App.class, args);
    }
}
