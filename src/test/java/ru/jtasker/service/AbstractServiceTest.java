package ru.jtasker.service;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.jtasker.config.AppConfigTest;

@SpringJUnitConfig(AppConfigTest.class)
@Sql(scripts = "classpath:/schema.sql")
public abstract class AbstractServiceTest {
}
