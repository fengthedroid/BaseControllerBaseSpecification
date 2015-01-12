package panda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(
        basePackages = "panda",
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
public class RootContextConfiguration {

        @Bean
        public DataSource dataSource() {
                return new EmbeddedDatabaseBuilder().setName("test").setType(EmbeddedDatabaseType.H2).addScript("create.sql").build();
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
                final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
                em.setDataSource(dataSource());
                em.setPackagesToScan("panda");
                final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
                em.setJpaVendorAdapter(vendorAdapter);
                em.setJpaProperties(additionalProperties());
                return em;
        }

        final Properties additionalProperties() {
                final Properties hibernateProperties = new Properties();
                hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                hibernateProperties.setProperty("hibernate.show_sql", "true");
                hibernateProperties.setProperty("hibernate.format_sql", "true");
                return hibernateProperties;
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
                final JpaTransactionManager transactionManager = new JpaTransactionManager();
                transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
                return transactionManager;
        }
}
