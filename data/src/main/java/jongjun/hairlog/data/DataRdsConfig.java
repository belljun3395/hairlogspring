package jongjun.hairlog.data;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = DataRdsConfig.BASE_PACKAGE)
@EnableAutoConfiguration(
		exclude = {
			DataSourceAutoConfiguration.class,
			DataSourceTransactionManagerAutoConfiguration.class,
			HibernateJpaAutoConfiguration.class,
		})
public class DataRdsConfig {

	public static final String BASE_PACKAGE = "jongjun.hairlog.data";
	public static final String SERVICE_NAME = "hairlog";
	public static final String MODULE_NAME = "data";
	public static final String BEAN_NAME_PREFIX = SERVICE_NAME + MODULE_NAME;
	public static final String PROPERTY_PREFIX = SERVICE_NAME + "." + MODULE_NAME;
}
