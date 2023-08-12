package jongjun.hairlog.data.config;

import jongjun.hairlog.data.DataRdsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {DataRdsConfig.class})
@Transactional
public abstract class BaseQueryImplTest {}
