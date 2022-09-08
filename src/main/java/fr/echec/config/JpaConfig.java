package fr.echec.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // Active les annotations @Transactional
@PropertySource("classpath:/datasource.properties")
@EnableJpaRepositories("fr.echec.repository")
public class JpaConfig {
	@Autowired
	private Environment env;
	

	
	@Bean // Permet de charger les clés dans les @Value, dans ce fichier de config
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	// Configuration des 3 beans pour JPA : DataSource, EntityManagerFactory, TransactionManager
	// Eventuellement, ajouter la translation des Exceptions
	
	// DataSource (Commons DBCP2)
	@Bean
	@Profile("!test")
	public DataSource dataSourceDbcp2() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setMaxTotal(10);
		
		return dataSource;
	}

	
	
	// EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		Properties jpaProps = new Properties();
		
		// Propriétés de JPA
		jpaProps.setProperty("hibernate.hbm2ddl.auto", "update");
		jpaProps.setProperty("hibernate.show_sql", "true");
		
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("fr.echec.classe");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(jpaProps);
		
		return emf;
	}
	
	// Gestionnaire de transactions
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	// Traducteur d'Exceptions (optionel)
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
