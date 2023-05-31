package jongjun.hairlog.data;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConditionalOnClass(value = org.flywaydb.core.Flyway.class)
@ConditionalOnBean(value = javax.sql.DataSource.class)
@AutoConfigureAfter(value = {JpaDataSourceConfig.class})
public class FlywayConfig {

	@Bean
	public Flyway flyway(ClassicConfiguration configuration) {
		return new Flyway(configuration);
	}

	@Bean
	public FlywayMigrationStrategy flywayMigrationStrategy() {
		return flyway -> {
			flyway.validate();
			flyway.migrate();
			flyway.baseline();
		};
	}

	@Bean
	public FlywayMigrationInitializer flywayInitializer(
			Flyway flyway, FlywayMigrationStrategy flywayMigrationStrategy) {
		return new FlywayMigrationInitializer(flyway, flywayMigrationStrategy);
	}

	@Bean
	@ConfigurationProperties(prefix = "hairlog.flyway")
	public FlywayProperties flywayProperties() {
		return new FlywayProperties();
	}

	@Profile("data")
	@Bean
	@ConfigurationProperties(prefix = "data.flyway")
	public FlywayProperties flywayDataProperties() {
		return new FlywayProperties();
	}

	@Bean
	public ClassicConfiguration configuration(FlywayProperties flywayProperties) {
		ClassicConfiguration configuration = new ClassicConfiguration();
		configuration.configure(flywayProperties.getJdbcProperties());
		return configuration;
	}
}
