package jongjun.hairlog.app;

import jongjun.hairlog.data.DataRdsConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@Import({DataRdsConfig.class})
public class AppConfig {}
