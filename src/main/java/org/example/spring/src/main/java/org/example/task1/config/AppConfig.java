package org.example.task1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//"Container" — pure Java, no XML
@Configuration
@ComponentScan(basePackages = "task1.service")
public class AppConfig {
}
