package com.wyvrn.increment.panels;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;

/**
 * MainMenu
 */

public class MainMenu extends Panel {

    public MainMenu() {
        super();

        // Configure layout manager
        GridLayout layout = new GridLayout(1);
        layout.setVerticalSpacing(2);
        this.setLayoutManager(layout);

        Panel headerPanel = new Panel();
        Panel mainMenuPanel = new Panel();

        this.addComponent(headerPanel);
        this.addComponent(mainMenuPanel);

        // Configure header panel
        try {
            // This has to be loaded as a Stream
            InputStream stream = MainMenu.class.getResourceAsStream("banner.txt");
            headerPanel.addComponent(Header.fromStream(stream));
        } catch (FileNotFoundException e) {
            System.err.println("oopsie i couldnt find `banner.txt`");
            headerPanel.addComponent(new Header("oopsie i couldnt find the banner"));
        }

        // Populate main menu items
        for (Button btn : this.generateMenuItems()) {
            mainMenuPanel.addComponent(btn);
        }

        // Alignment for components
        headerPanel.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, true, false));
        mainMenuPanel.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, false, false));
    }

    private ArrayList<Button> generateMenuItems(){
        ArrayList<Button> buttons = new ArrayList<>();

        // Load Game
        buttons.add(new Button("Load Game", new Runnable() {
            @Override
            public void run() {
            }
        }));

        // New Game
        buttons.add(new Button("New Game", new Runnable() {
            @Override
            public void run() {
            }
        }));

        // Quit
        buttons.add(new Button("Quit", new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        }));

        return buttons;
    }
}
