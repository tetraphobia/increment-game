# Increment
![A screenshot of the game](/doc/screenshot.png)

## Description
A Java game I wrote for a compsci project.

## Requirements
- Cool Retro Term (https://github.com/Swordfish90/cool-retro-term)
- Maven (To build from source)
- OpenJDK >= 17

## Usage
Run `./start.sh`. It should yell at you if you're missing a dependency and launch the program in a CRT wrapper.

To run without the CRT wrapper, run:
```sh
mvn clean
mvn package
java -cp target/increment-game-0.0.1.jar com.wyvrn.increment.App
```

## Contributing
dont :|
