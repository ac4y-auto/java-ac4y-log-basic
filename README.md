# java-ac4y-log-basic

Log domain object, JDBC persistence adapter, and service layer.

## Coordinates

- **GroupId**: `ac4y`
- **ArtifactId**: `ac4y-log-basic`
- **Version**: `1.0.0`

## Description

Core logging module with domain object (`Ac4yLog`), JDBC persistence adapter (`Ac4yLogDBAdapter`), and service layer (`Ac4yLogService`) with JNDI connection management. The `Ac4yLog` domain object tracks log entries with class, module, event, information, properties, thread ID, and timestamps.

### Key Classes

- `Ac4yLog` - Log domain object extending Ac4y (id, logClass, module, event, information, properties, threadID, timestamps)
- `Ac4yLogDBAdapter` - JDBC persistence adapter for insert and get operations
- `Ac4yLogService` - Service layer with JNDI connection management

## Dependencies

- `ac4y-class` (Ac4y base class)
- `ac4y-base4jsonandxml` (JSON/XML serialization)
- `ac4y-connection-pool` (DBConnection)

## Build

```bash
mvn clean package
```

## Origin

Extracted from `IJAc4yLogModule/Basic`.
