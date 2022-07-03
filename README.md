# Cron Expression Parser

## Description:
- Codebase represents command line application or script which parses a cron string and expands each field
  to show the times at which it will run,
- Standard cron format with five time fields (minute, hour, day of
  month, month, and day of week) plus a command,
- Special time strings such as "@yearly" are not supported,
- The output is formatted as a table with the field name taking the first 14 columns and
  the times as a space-separated list following it,

## Assumptions:
- Program name can contain any ASCI char,
- The input is a single line text,

## Tech stack:
- Java 17
- JUnit/Spock - testing,
- Gradle - build automation system

## Run:
Repository contains **cron-experssion-parser.jar** program in **build/libs** folder.
It can be used or recreated using command:
```
gradle build
```

Example program run(from the repository level):
```
java -jar build/libs/cron-experssion-parser.jar "*/15 0 1 * 1-5 /usr/bin/find" 
```

Example output:
```
minute        0 15 30 45
hour          0
day of month  1
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find

```

## Tests:
To execute tests, please use:
```
gradle test
```
Or run them from the IDE. 

Tests are stored in two locations:
- **cron/src/test/groovy** - higher level tests covering all logic used for parsing
- **cron/src/test/java** - lower level tests covering rule engine

**CronParserTest** is a functional test. It contains tests covering all logic used for parsing, the rest of test classes are 
unit tests.  
