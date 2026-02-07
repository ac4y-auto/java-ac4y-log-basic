# Architektura - java-ac4y-log-basic

## Attekintes

Naplozasi modul domain objektummal, JDBC perzisztencia adapterrel es szolgaltatas reteggel. Az `Ac4yLog` objektum naplobejegyzeseket kovet nyomon osztalyokkal, modulokkal, esemenyekkel es idojelzolegekkel.

## Szerkezet

```
src/main/java/ac4y/log/
  domain/object/
    Ac4yLog.java                    - Log domain objektum (id, logClass, module, event, stb.)
  domain/persistence/
    Ac4yLogDBAdapter.java           - JDBC perzisztencia adapter (insert, get)
  service/persistence/
    Ac4yLogService.java             - Szolgaltatas reteg JNDI kapcsolatkezessel
src/test/java/ac4y/log/
  domain/object/
    Ac4yLogTest.java                - Ac4yLog egyseg tesztek (13 teszt)
  domain/persistence/
    Ac4yLogDBAdapterTest.java       - DB adapter tesztek Mockito-val (10 teszt)
```

## Domain objektum mezo

- `id` - Bejegyzes azonosito
- `logClass` - Log osztaly
- `module` - Modul nev
- `event` - Esemeny tipus
- `information` - Reszletes informacio
- `properties` - Tulajdonsagok
- `threadID` - Szal azonosito
- `uploaded` - Feltoltes idopontja
- `originalTimestamp` - Eredeti idobelyeg

## Fuggosegek

- ac4y-class (Ac4y bazis osztaly)
- ac4y-base4jsonandxml (JSON/XML szerializacio)
- ac4y-connection-pool (DBConnection)
- Teszt: JUnit 4, Mockito

## Eredet

Az `IJAc4yLogModule/Basic` modulbol kinyerve.
