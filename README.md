1. Handle february
2. Handle month endings
3. Refactor
4. Handle ","
5. Command validation


# Cron Expression Parser

## Requirements:
- Write a command line application or script which parses a cron string and expands each field
  to show the times at which it will run. 
- Consider only the standard cron format with five time fields (minute, hour, day of
  month, month, and day of week) plus a command, 
- No need to handle the special time strings such as "@yearly". 
- The input will be on a single line

## Assumptions:
- Program doesn't cover month endings, i.e it prints all month endings as 31



## Key points:
- Java
- JUnit - testing,
- Gradle - build automation system


To build project, please use(alternatively gradle wrapper can be used - ```gradle wrapper```):
```
gradle build
```

To execute tests:
```
gradle test
```

Example program run:
```
java -jar build/libs/cron-experssion-parser.jar "*/15 0 1 * 1-5 /usr/bin/find" 
```

After execution of above command, the service will start at port 8080.

## Initial in-memory data:

Check files:
```
./src/main/resources/schema.sql
./src/main/resources/data.sql
```

## API
Swagger is added to the project, after program start please find it under the link:
```http://localhost:8080/api/swagger-ui.html```
