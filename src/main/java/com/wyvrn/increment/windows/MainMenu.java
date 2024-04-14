package com.wyvrn.increment.windows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.dialogs.FileDialog;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.wyvrn.increment.panels.Header;

/**
 * MainMenu
 */
public class MainMenu extends GameWindow {

    public MainMenu(MultiWindowTextGUI gui) {
        super(gui);

        // Configure layout manager
        GridLayout layout = new GridLayout(1);
        layout.setVerticalSpacing(2);

        Panel mainMenuGrid = new Panel();
        mainMenuGrid.setLayoutManager(layout);

        Panel headerPanel = new Panel();
        Panel buttonsPanel = new Panel();

        mainMenuGrid.addComponent(headerPanel);
        mainMenuGrid.addComponent(buttonsPanel);

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
            buttonsPanel.addComponent(btn);
        }

        // Alignment for components
        headerPanel.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, true, false));
        buttonsPanel.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, false, false));

        setComponent(mainMenuGrid);
    }

    private ArrayList<Button> generateMenuItems() {
        ArrayList<Button> buttons = new ArrayList<>();
        MultiWindowTextGUI gui = super.getGui();

        // Load Game
        buttons.add(new Button("Load Game", new Runnable() {
            @Override
            public void run() {
                Path saveDir = Paths.get(System.getProperty("user.home") + "/.increment-saves/");
                try {
                    Files.createDirectory(saveDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FileDialog dialog = new FileDialogBuilder()
                        .setTitle("Load Game")
                        .setDescription("Choose a save file (probably ending in .json)")
                        .setSelectedFile(saveDir.toFile())
                        .setActionLabel("Load")
                        .build();
                dialog.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));

                File input = dialog.showDialog(gui);
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
