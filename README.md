# Cron Expression Parser

## Requirements:
- Write a command line application or script which parses a cron string and expands each field
  to show the times at which it will run. 
- Standard cron format with five time fields (minute, hour, day of
  month, month, and day of week) plus a command.
- No need to handle the special time strings such as "@yearly". 
- The output should be formatted as a table with the field name taking the first 14 columns and
  the times as a space-separated list following it.

## Assumptions:
- Program doesn't cover month ends validation. All months have limit to 31 days.
- Program name can contain any ASCI char.
- The input will be on a single line.

## Tech stack:
- Java
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
- **deliveroo/src/test/groovy** - higher level tests covering all logic used for parsing
- **deliveroo/src/test/java** - lower level tests covering rule engine

**CronParserTest** contains tests covering all logic used for parsing, the rest of them are 
responsible for particular steps used in flow.  
