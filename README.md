# Cardpal API Server

A Spring Boot backend for Cardpal that exposes health and Sentry test endpoints.

## Overview

This repository is a Java 21 Spring Boot application configured with Sentry monitoring and a small HTTP API surface.

## Features

- Spring Boot 4.1.0
- HTTP API endpoints for Sentry logging and error reporting
- Actuator health check
- PostgreSQL JDBC driver available at runtime
- Sentry JVM integration for exception capture and profiling

## API Endpoints

- `GET /actuator/health`
  - Built-in Spring Boot Actuator health probe.
- `GET /info-sentry`
  - Logs a Sentry info event and returns `Hello world from Sentry`.
- `GET /error-sentry`
  - Captures a test exception in Sentry and returns an empty response.

## Configuration

The application reads configuration from `src/main/resources/application.yaml`.

Key values:

- `server.port` — defaults to `8080`, can be overridden with `PORT`
- `sentry.dsn` — set via `SENTRY_DSN`
- `SENTRY_AUTH_TOKEN` — used by the Gradle Sentry plugin for source bundle uploads

## Requirements

- Java 21
- Gradle wrapper (`./gradlew`)
- Optional: Sentry DSN and auth token for Sentry integration

## Run Locally

From the project root:

\`\`\`powershell
./gradlew bootRun
\`\`\`

Or to build a JAR:

\`\`\`powershell
./gradlew clean bootJar
\`\`\`

Then run:

\`\`\`powershell
java -jar build/libs/cardpal-0.0.1-SNAPSHOT.jar
\`\`\`

## Environment Variables

- `PORT` — port override for the server
- `SENTRY_DSN` — Sentry DSN for sending events
- `SENTRY_AUTH_TOKEN` — auth token for Gradle plugin uploads (used only at build time)

## Testing

Run tests with:

\`\`\`powershell
./gradlew test
\`\`\`

## Notes

- The repository currently provides a minimal API surface focused on health checks and Sentry integration.
- Additional Cardpal-specific endpoints can be added under `src/main/java/decoteam/cardpal/controller`.

## Project Structure

- `src/main/java/decoteam/cardpal` — application entry point and controllers
- `src/main/resources/application.yaml` — Spring Boot configuration
- `build.gradle` — Gradle build configuration
