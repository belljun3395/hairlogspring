package jongjun.hairlog.app.config;

import static jongjun.hairlog.app.config.AppConfig.APP_BASE_PACKAGE;

import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = {APP_BASE_PACKAGE})
@Import({DataRdsConfig.class, JpaDataSourceConfig.class})
public class AppConfig {

	public static final String APP_BASE_PACKAGE = "jongjun.hairlog.app";
}
