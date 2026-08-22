package org.example.task2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//"Container" — pure Java, no XML
@Configuration
@ComponentScan(basePackages = "task2.service")
public class AppConfig {
}
