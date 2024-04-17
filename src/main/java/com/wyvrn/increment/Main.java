package com.wyvrn.increment;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import com.wyvrn.increment.windows.MainMenu;
import com.wyvrn.increment.windows.GameWindow;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        try {
            Audio.playMusic();
        } catch (LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.out.println("Couldn't load music file, continuing without music.");
        }

        screen.startScreen();

        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
                new EmptySpace(TextColor.ANSI.DEFAULT));

        GameWindow window = new MainMenu(gui);
        gui.addWindowAndWait(window);
    }
}
