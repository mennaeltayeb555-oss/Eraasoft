package org.example.task3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//"Container" — pure Java, no XML
@Configuration
@ComponentScan(basePackages = "task3.service")
public class AppConfig {
}
