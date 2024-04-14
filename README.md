# Increment
![A screenshot of the main menu](/doc/menu.png)

## Description
A Java game I wrote for a compsci project.

## Implemented Concepts

### Inheritance
- [x] `GameWindow` extends `BasicWindow` for themeing and passing a reference to the TUI.
- [ ] `Generator` extends building, `SmallGenerator` and `LargeGenerator` both extend `Generator`.
### Abstract classes
- [x] The `GameWindow` class is an abstract class extended by multiple game windows.
- [ ] The `Building` class is an abstract class extended by the buyable buildings.
### Interfaces
- [ ] The `SmallGenerator` class implements the `Upgradable` interface.
### File I/O
- [x] Loading the main menu triggers music to be played, which is read from a resource folder, converted to an `AudioInputStream`, and then played.
- [ ] Game state is loaded from a file and then saved to the same file.

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
