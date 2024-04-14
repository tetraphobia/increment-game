package com.wyvrn.increment.windows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.wyvrn.increment.panels.Header;

/**
 * MainMenu
 */
public class MainMenu extends BasicWindow {

    public MainMenu() {
        super.setHints(Arrays.asList(Window.Hint.FULL_SCREEN));
        Panel verticalPanel = new Panel();
        verticalPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

        // Header panel
        Panel headerPanel = new Panel();
        try {
            // This has to be loaded as a Stream
            InputStream stream = ClassLoader.getSystemResourceAsStream("banner.txt");
            headerPanel.addComponent(Header.fromStream(stream));
        } catch (FileNotFoundException e) {
            System.err.println("oopsie i couldnt find `banner.txt`");
            headerPanel.addComponent(new Header("oopsie i couldnt find the banner"));
        }

        // Main menu items
        Panel mainMenuPanel = new Panel();

        verticalPanel.addComponent(headerPanel);
        verticalPanel.addComponent(mainMenuPanel.withBorder(Borders.singleLineBevel("Main Menu")));

        setComponent(verticalPanel);
    }

    private File File(String string) {
        return null;
    }
}
