package com.smartprep.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${SPRING_DATASOURCE_URL:${DATABASE_URL:jdbc:postgresql://localhost:5432/smartprep}}")
    private String dbUrl;

    @Value("${SPRING_DATASOURCE_USERNAME:root}")
    private String username;

    @Value("${SPRING_DATASOURCE_PASSWORD:root}")
    private String password;

    @Bean
    public DataSource dataSource() {
        String finalUrl = dbUrl;
        String finalUser = username;
        String finalPass = password;
        
        try {
            if (finalUrl != null && (finalUrl.startsWith("postgres://") || finalUrl.startsWith("postgresql://"))) {
                java.net.URI uri = new java.net.URI(finalUrl);
                
                if (uri.getUserInfo() != null) {
                    String[] userInfo = uri.getUserInfo().split(":");
                    finalUser = userInfo[0];
                    if (userInfo.length > 1) {
                        finalPass = userInfo[1];
                    }
                }
                
                finalUrl = "jdbc:postgresql://" + uri.getHost() + ":" + 
                           (uri.getPort() == -1 ? 5432 : uri.getPort()) + 
                           uri.getPath();
            }
        } catch (Exception e) {
            System.err.println("Failed to parse database URL: " + e.getMessage());
        }
        
        return org.springframework.boot.jdbc.DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(finalUrl)
                .username(finalUser)
                .password(finalPass)
                .build();
    }
}
