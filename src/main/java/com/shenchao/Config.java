package com.shenchao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by shenchao on 2017/4/5.
 */
@Configuration
@ComponentScan(basePackages = {"com.shenchao"})
@PropertySource({"classpath:jdbc.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.shenchao.repository")
public class Config {
    @Value("${jdbc.username}")
    public String username;

    @Value("${jdbc.password}")
    public String password;

    @Value("${jdbc.url}")
    public String url ;

    @Value("${jdbc.driver}")
    public String driver;
    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setUser(username);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setJdbcUrl(url);
        try {
            comboPooledDataSource.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return comboPooledDataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setDataSource(dataSource());
        return manager;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan("com.shenchao.entity");
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.ddl-auto", "update");
        jpaProperties.put("hibernate.format_sql", true);
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }
}
