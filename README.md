# Increment
![A screenshot of the main menu](/doc/menu.png)

## Description
A Java game I wrote for a compsci project. Generate credits forever.

## Implemented Concepts
For a quick four, look at `GameState.java`, `buildings/machines/Machine.java` and `buildings/machines/LargeMachine.java`.

### Inheritance
- [x] `SmallMachine` and `LargeMachine` both extend `Machine`.
### Abstract classes
- [x] The `Machine` class is an abstract class extended by `SmallMachine` and `LargeMachine`.
### Interfaces
- [x] The `Upgradable` interface is implemented by `LargeMachine`.
### File I/O
- [x] A JSON representation of the game's state is saved to a json file of the user's choosing.
- [x] If a save file exists, the user can choose to load it and instantiate a new `GameState` using that file.
### Exception Handling for User Input
- [x] If a user chooses a save file that does not exist, an exception will be caught and a dialog will be shown prompting them to try again.
- [x] If a user inputs invalid characters in their "save game" prompt, they will be asked to try again. (Technically handled by the library.)
### Override toString()
- [x] The `GameState.toString()` method overrides the `Object.toString()` method.
### Overloading
- [x] Both `Machine` child classes have overloaded constructors.

## Requirements
- (Optional) Cool Retro Term (https://github.com/Swordfish90/cool-retro-term)
- Maven (To build from source)
- Java 8

## Usage

### Jarfile
Download the jarfile from the Releases page, then use the `java` binary (or `javaw` if you're on Windows).
```bash
java -jar increment-game-VERSION.jar
```

### Building
```sh
mvn clean
mvn package
java -jar target/increment-game-0.0.1.jar
```

The source also comes with a shell script, which builds the package and launches it in a CRT emulator for fun.

## Contributing
dont :|
