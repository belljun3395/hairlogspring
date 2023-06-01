package jongjun.hairlog.data;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(value = org.flywaydb.core.Flyway.class)
@ConditionalOnBean(value = javax.sql.DataSource.class)
@AutoConfigureAfter(value = {JpaDataSourceConfig.class})
public class FlywayConfig {
	private static final String SERVICE_NAME = "hairlog";
	private static final String FLYWAY = SERVICE_NAME + "Flyway";
	private static final String FLYWAY_PROPERTIES = SERVICE_NAME + "FlywayProperties";
	private static final String FLYWAY_MIGRATION_STRATEGY = SERVICE_NAME + "FlywayMigrationStrategy";
	private static final String FLYWAY_INITIALIZER = SERVICE_NAME + "FlywayInitializer";
	private static final String FLYWAY_CONFIGURATION = SERVICE_NAME + "FlywayConfiguration";

	@Bean(name = FLYWAY)
	public Flyway flyway(
			@Qualifier(value = FLYWAY_CONFIGURATION) ClassicConfiguration configuration) {
		return new Flyway(configuration);
	}

	@Bean(name = FLYWAY_MIGRATION_STRATEGY)
	public FlywayMigrationStrategy flywayMigrationStrategy() {
		return flyway -> {
			flyway.validate();
			flyway.migrate();
			flyway.baseline();
		};
	}

	@Bean(name = FLYWAY_INITIALIZER)
	public FlywayMigrationInitializer flywayInitializer(
			@Qualifier(value = FLYWAY) Flyway flyway,
			@Qualifier(value = FLYWAY_MIGRATION_STRATEGY)
					FlywayMigrationStrategy flywayMigrationStrategy) {
		return new FlywayMigrationInitializer(flyway, flywayMigrationStrategy);
	}

	@Bean(name = FLYWAY_PROPERTIES)
	@ConfigurationProperties(prefix = SERVICE_NAME + ".flyway")
	public FlywayProperties flywayProperties() {
		return new FlywayProperties();
	}

	@Bean(name = FLYWAY_CONFIGURATION)
	public ClassicConfiguration configuration(
			@Qualifier(value = FLYWAY_PROPERTIES) FlywayProperties flywayProperties) {
		ClassicConfiguration configuration = new ClassicConfiguration();
		configuration.configure(flywayProperties.getJdbcProperties());
		return configuration;
	}
}
