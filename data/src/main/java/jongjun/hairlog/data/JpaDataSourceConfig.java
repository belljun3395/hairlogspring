package jongjun.hairlog.data;

import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = JpaDataSourceConfig.BASE_PACKAGE,
		transactionManagerRef = JpaDataSourceConfig.TRANSACTION_MANAGER_NAME,
		entityManagerFactoryRef = JpaDataSourceConfig.ENTITY_MANAGER_FACTORY_NAME)
public class JpaDataSourceConfig {

	private static final String SERVICE_NAME = "hairlog";
	private static final String MODULE_NAME = "data";
	public static final String BASE_PACKAGE = "jongjun.hairlog." + MODULE_NAME;
	public static final String ENTITY_MANAGER_FACTORY_NAME =
			SERVICE_NAME + "DomainEntityManagerFactory";
	public static final String TRANSACTION_MANAGER_NAME = SERVICE_NAME + "DomainTransactionManager";
	public static final String PERSIST_UNIT = SERVICE_NAME + "Domain";

	@Bean
	@ConfigurationProperties(prefix = SERVICE_NAME + ".datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = SERVICE_NAME + ".jpa")
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = SERVICE_NAME + ".jpa.hibernate")
	public HibernateProperties hibernateProperties() {
		return new HibernateProperties();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean(name = ENTITY_MANAGER_FACTORY_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		Map<String, String> jpaPropertyMap = jpaProperties().getProperties();
		Map<String, Object> hibernatePropertyMap =
				hibernateProperties().determineHibernateProperties(jpaPropertyMap, new HibernateSettings());
		return new EntityManagerFactoryBuilder(jpaVendorAdapter(), jpaPropertyMap, null)
				.dataSource(dataSource)
				.properties(hibernatePropertyMap)
				.persistenceUnit(PERSIST_UNIT)
				.packages(BASE_PACKAGE)
				.build();
	}

	@Bean(name = TRANSACTION_MANAGER_NAME)
	public PlatformTransactionManager transactionManager(
			@Qualifier(ENTITY_MANAGER_FACTORY_NAME) EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
