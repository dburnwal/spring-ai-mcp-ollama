
package com.dburnwal.springai.mcp.config;

import com.dburnwal.springai.mcp.service.WeatherService;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the MCP (Model Context Protocol) server.
 * This class sets up the MCP server with proper transport and properties.
 */
@Configuration
public class McpServerConfig {

    @Bean
    public ToolCallbackProvider weatherTools(WeatherService weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }

    public record TextInput(String input) {
    }

    @Bean
    public ToolCallback toUpperCase() {
        return FunctionToolCallback.builder("toUpperCase", (TextInput input) -> input.input().toUpperCase())
                .inputType(TextInput.class)
                .description("Put the text to upper case")
                .build();
    }
}
