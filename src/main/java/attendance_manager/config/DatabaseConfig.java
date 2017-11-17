package attendance_manager.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "attendance_manager")
@PropertySource("classpath:db.properties")
public class DatabaseConfig {

    @Value("${spring.jpa.properties.org.hibernate.c3p0.min_size}")
    int min_size;
    @Value("${spring.jpa.properties.org.hibernate.c3p0.max_size}")
    int max_size;
    @Value("${spring.jpa.properties.org.c3p0.acquire_increment}")
    int increment;
    @Value("${datasource_driver_class}")
    String driverClass;
    @Value("${datasource_url}")
    String url;
    @Value("${datasource_username}")
    String username_c3p0;
    @Value("${datasource_password}")
    String password_c3p0;
    // endregion

    // region Hibernate properties
    @Value("${spring.jpa.properties.org.hibernate.dialect}")
    String dialect;
    @Value("${spring.jpa.properties.org.hibernate.hbm2ddl.auto}")
    String hbm2ddl_auto;
    @Value("${spring.jpa.properties.org.hibernate.implicit_naming_strategy}")
    String naming_strategy;
    @Value("${spring.jpa.properties.org.hibernate.generate_statistics}")
    boolean statistics;
    @Value("${spring.jpa.properties.org.hibernate.format_sql}")
    boolean format_sql;
    @Value("${spring.jpa.properties.org.hibernate.show_sql}")
    boolean show_sql;
    @Value("${spring.jpa.properties.org.hibernate.enable_lazy_load_no_trans}")
    boolean trans_lazy_load;

    // endregion

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverClass);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username_c3p0);
        dataSource.setPassword(password_c3p0);
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl_auto);
        properties.put("hibernate.implicit_naming_strategy", naming_strategy);
        properties.put("hibernate.generate_statistics", statistics);
        properties.put("hibernate.format_sql", format_sql);
        properties.put("hibernate.show_sql", show_sql);
        properties.put("hibernate.c3p0.min_size", min_size);
        properties.put("hibernate.c3p0.max_size", max_size);
        properties.put("hibernate.c3p0.acquire_increment", increment);
        properties.put("hibernate.enable_lazy_load_no_trans", trans_lazy_load);
        return properties;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.recruiting");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
        localContainerEntityManagerFactoryBean.afterPropertiesSet();

        return localContainerEntityManagerFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
