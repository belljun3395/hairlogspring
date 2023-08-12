package jongjun.hairlog.data.config;

import static jongjun.hairlog.data.DataRdsConfig.BEAN_NAME_PREFIX;
import static jongjun.hairlog.data.DataRdsConfig.PROPERTY_PREFIX;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EntityFlywayConfig {

	// base property prefix for flyway
	public static final String BASE_PROPERTY_PREFIX = PROPERTY_PREFIX;

	// bean name for flyway configuration
	private static final String FLYWAY_BEAN_NAME_PREFIX = BEAN_NAME_PREFIX + "fly";
	private static final String FLYWAY = FLYWAY_BEAN_NAME_PREFIX;
	private static final String FLYWAY_PROPERTIES = FLYWAY_BEAN_NAME_PREFIX + "Properties";
	private static final String FLYWAY_MIGRATION_INITIALIZER =
			FLYWAY_BEAN_NAME_PREFIX + "MigrationInitializer";
	private static final String FLYWAY_VALIDATE_INITIALIZER =
			FLYWAY_BEAN_NAME_PREFIX + "ValidateInitializer";
	private static final String FLYWAY_CONFIGURATION = FLYWAY_BEAN_NAME_PREFIX + "Configuration";

	@Bean(name = FLYWAY)
	public Flyway flyway(
			@Qualifier(value = FLYWAY_CONFIGURATION)
					org.flywaydb.core.api.configuration.Configuration configuration) {
		return new Flyway(configuration);
	}

	@Profile({"!local && !test"})
	@Bean(name = FLYWAY_VALIDATE_INITIALIZER)
	public FlywayMigrationInitializer flywayValidateInitializer(
			@Qualifier(value = FLYWAY) Flyway flyway) {
		return new FlywayMigrationInitializer(flyway, Flyway::validate);
	}

	@Profile({"!local && !test"})
	@Bean(name = FLYWAY_MIGRATION_INITIALIZER)
	public FlywayMigrationInitializer flywayMigrationInitializer(
			@Qualifier(value = FLYWAY) Flyway flyway) {
		return new FlywayMigrationInitializer(flyway, Flyway::migrate);
	}

	@Bean(name = FLYWAY_PROPERTIES)
	@ConfigurationProperties(prefix = BASE_PROPERTY_PREFIX + ".flyway")
	public FlywayProperties flywayProperties() {
		return new FlywayProperties();
	}

	@Bean(name = FLYWAY_CONFIGURATION)
	public org.flywaydb.core.api.configuration.Configuration configuration(
			@Qualifier(value = FLYWAY_PROPERTIES) FlywayProperties flywayProperties,
			@Qualifier(value = EntityJpaDataSourceConfig.DATASOURCE_NAME) DataSource dataSource) {

		FluentConfiguration configuration = new FluentConfiguration();
		configuration.dataSource(dataSource);
		flywayProperties.getLocations().forEach(configuration::locations);
		return configuration;
	}
}
