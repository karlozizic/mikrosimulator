package com.microservices.payments;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Configuration
@ComponentScan
@EntityScan("com.microservices.payments")
@EnableJpaRepositories("com.microservices.payments")
@PropertySource("classpath:db-config.properties")
public class PaymentsConfiguration {

    protected Logger logger;

    public PaymentsConfiguration() { logger = Logger.getLogger(getClass().getName()); }

    @Bean
    public DataSource dataSource() {
        logger.info("dataSource() invoked");
        DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/paymentschema.sql")
                .build();

        logger.info("dataSource = " + dataSource);

        // Sanity check
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> payments = jdbcTemplate.queryForList("SELECT * FROM PAYMENT");
        logger.info("System has " + payments.size() + " payments");

        return dataSource;
    }

}
