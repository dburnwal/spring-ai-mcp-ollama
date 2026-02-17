# Spring AI MCP Server

A Spring AI-based MCP (Model Context Protocol) server implementation that provides weather-related tools and utilities.

## Features

- **Weather Forecast**: Get detailed weather forecasts for specific locations using latitude/longitude coordinates
- **Weather Alerts**: Retrieve active weather alerts for US states
- **Text Utilities**: Transform text using MCP tools (e.g., convert to uppercase)
- **MCP Protocol Support**: Full support for Model Context Protocol with SSE (Server-Sent Events) transport

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Internet connection (for accessing the National Weather Service API)

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd spring-ai-mcp-ollama
```

2. Build the project:
```bash
mvn clean install
```

## Configuration

The application can be configured through `src/main/resources/application.properties`:

```properties
# Application Configuration
spring.application.name=spring-mcp-server

# MCP Server Configuration
spring.ai.mcp.server.name=my-weather-server
spring.ai.mcp.server.version=0.0.1
spring.ai.mcp.server.type=SYNC

# Transport Configuration
spring.ai.mcp.server.stdio=false
spring.ai.mcp.server.sse-message-endpoint=/mcp/message

# Change Notifications
spring.ai.mcp.server.resource-change-notification=true
spring.ai.mcp.server.tool-change-notification=true
spring.ai.mcp.server.prompt-change-notification=true

# Server Configuration
server.port=8081

# Logging Configuration
spring.main.banner-mode=off
logging.file.name=./target/starter-webflux-server.log
logging.level.org.springframework.ai=DEBUG
logging.level.com.dburnwal.springai=DEBUG
```

## Running the Server

Start the application:
```bash
mvn spring-boot:run
```

The MCP server will be available at `http://localhost:8081` with the SSE endpoint at `/mcp/message`.

## MCP Tools

The server exposes the following MCP tools:

### Weather Tools

1. **getWeatherForecastByLocation**
   - Description: Get weather forecast for a specific latitude/longitude
   - Parameters:
     - `latitude` (double): Latitude coordinate
     - `longitude` (double): Longitude coordinate
   - Returns: Detailed weather forecast including temperature, wind, and detailed forecast information

2. **getAlerts**
   - Description: Get weather alerts for a US state
   - Parameters:
     - `state` (string): Two-letter US state code (e.g., CA, NY)
   - Returns: Active weather alerts including event type, area, severity, description, and instructions

### Utility Tools

1. **toUpperCase**
   - Description: Convert text to uppercase
   - Parameters:
     - `input` (string): Text to convert to uppercase
   - Returns: Uppercase version of the input text

## Architecture

The application is structured as follows:

```
src/main/java/com/dburnwal/springai/mcp/
├── SpringMCPServer.java              # Main application entry point
├── config/
│   └── McpServerConfig.java           # MCP server and tool configuration
├── service/
│   └── WeatherService.java           # Weather service implementation
└── tools/                             # MCP tools directory
```

## Usage Examples

### Using the Weather Forecast Tool

```json
{
  "jsonrpc": "2.0",
  "method": "tools/call",
  "params": {
    "name": "getWeatherForecastByLocation",
    "arguments": {
      "latitude": 47.6062,
      "longitude": -122.3321
    }
  },
  "id": 1
}
```

### Using the Weather Alerts Tool

```json
{
  "jsonrpc": "2.0",
  "method": "tools/call",
  "params": {
    "name": "getAlerts",
    "arguments": {
      "state": "NY"
    }
  },
  "id": 2
}
```

### Using the Text Utility Tool

```json
{
  "jsonrpc": "2.0",
  "method": "tools/call",
  "params": {
    "name": "toUpperCase",
    "arguments": {
      "input": "hello world"
    }
  },
  "id": 3
}
```

## Project Dependencies

- Spring Boot 4.0.2
- Spring AI 1.1.2
- spring-ai-starter-mcp-server-webflux

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the Apache License 2.0.

## Acknowledgments

- Weather data provided by the National Weather Service API (https://api.weather.gov)
- Built with Spring AI and Spring Boot