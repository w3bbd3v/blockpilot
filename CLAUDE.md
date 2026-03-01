# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Run the server
./gradlew :blockpilot-server:bootRun

# Build all modules
./gradlew build

# Run server tests
./gradlew :blockpilot-server:test

# Run a single test class
./gradlew :blockpilot-server:test --tests "com.blockpilot.server.BlockpilotServerApplicationTests"
```

## Architecture

BlockPilot is a control plane for the BLOCKCLOCK mini (a Bitcoin-focused E-Ink display). It exposes the full BLOCKCLOCK HTTP API through a UI and adds scheduling automation.

Multi-module Gradle project (Java 21, Spring Boot 4):

- **`blockpilot-server`** — Spring Boot backend. Will contain the BLOCKCLOCK API client, scheduling engine, and REST API. Currently stubbed out.
- **`blockpilot-client`** _(planned)_ — Frontend UI.

Common Gradle config (Java toolchain, JUnit platform, repositories) lives in the root `build.gradle` and applies to all subprojects. Module-specific dependencies go in each module's own `build.gradle`.

### Core domain concept

A **command** is a named, reusable sequence of one or more BLOCKCLOCK API calls fired together as a single logical action. Commands can be triggered on-demand or run on a cron/interval schedule. Third-party HTTP data sources can be fetched and formatted before being pushed to the device.

## Style

Follow Clean Code practices. Only comment complex code — no obvious or redundant comments.

### Package structure

`com.blockpilot.server` — root package for the server module.
