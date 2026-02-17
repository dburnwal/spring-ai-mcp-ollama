package com.dburnwal.springai.mcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringAiMCPServer is the main entry point for the Spring AI MCP Server application.
 * This class is annotated with @SpringBootApplication, which indicates that it is a
 * Spring Boot application that enables auto-configuration, component scanning, and
 * allows for defining additional configuration classes.
 */
@SpringBootApplication
public class SpringMCPServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringMCPServer.class, args);
    }
}
