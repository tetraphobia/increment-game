package com.wyvrn.increment;

import java.io.IOException;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        String s = "Hello world!";
        TextGraphics tGraphics = screen.newTextGraphics();

        screen.startScreen();
        screen.clear();

        tGraphics.putString(10, 10, s);
        screen.refresh();

        screen.readInput();
        screen.stopScreen();
    }
}
