package com.lasias.hostelbookingbackend.health;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    private final DataSource dataSource;

    public DatabaseHealthIndicator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {

            if (connection.isValid(2)) {
                return Health.up()
                        .withDetail("database", "MySQL")
                        .withDetail("connection", "available")
                        .build();
            }

            return Health.down()
                    .withDetail("database", "MySQL")
                    .withDetail("connection", "unavailable")
                    .build();

        } catch (Exception e) {
            return Health.down()
                    .withDetail("database", "MySQL")
                    .withDetail("connection", "unavailable")
                    .build();
        }
    }
}
