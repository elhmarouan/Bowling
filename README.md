# American Ten-Pin Bowling Game

Program that calculates the score for an American Ten-Pin Bowling game.

Built with Java 14.0.1, Gradle 7.0.2 and JUnit 5.7.0

## Run the program
To run the program, you need to execute the following command with a valid sequence of rolls entry.
For example : 
```
./gradlew run --args="9- 9- 9- 9- 9- 9- 9- 9- 9- 9-"
```

To execute the unit tests, run the following command:
```
./gradlew test
```

## Test Cases
```
"9- 9- 9- 9- 9- 9- 9- 9- 9- 9-" returns a total score of 90
```
```
"5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5" returns a total score of 150
```
```
"X X X X X X X X X X X X" returns a total score of 300
```
```
"51 32 X 9- 11 5- 33 44 1- 22" returns a total score of 65
```
```
"-- -- -- -- -- -- -- -- -- --" returns a total score of 0
```
