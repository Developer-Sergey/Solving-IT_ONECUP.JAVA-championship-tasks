package com.solution.allcups.reportbuilder;

import com.solution.allcups.reportbuilder.database.DatabaseData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties({DatabaseData.class})
public class ReportBuilderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportBuilderApplication.class, args);
    }
}
