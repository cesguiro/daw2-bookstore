package es.cesguiro.integration;

import es.cesguiro.dao.jpa.repository.BookRepositoryJpa;
import es.cesguiro.repository.impl.BookRepositoryImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "es.cesguiro.dao.jpa.repository")  // Habilita la detección automática de repositorios JPA
public class AppConfig {

    // Configurar el DataSource de H2 en memoria
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1")
                .driverClassName("org.h2.Driver")
                .username("root")
                .password("root")
                .build();
    }

    // Configuración del EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("es.cesguiro.dao.jpa.entity");  // Paquete donde están las entidades
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());  // Adaptador de proveedor Hibernate
        return factoryBean;
    }

    // Configuración del PlatformTransactionManager
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

    // Bean para BookRepositoryImpl (si es necesario y sin BookRepositoryJpa manualmente)
    @Bean
    public BookRepositoryImpl bookRepositoryImpl(BookRepositoryJpa bookRepositoryJpa) {
        return new BookRepositoryImpl(bookRepositoryJpa);
    }
}