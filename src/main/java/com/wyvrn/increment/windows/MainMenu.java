package com.wyvrn.increment.windows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.googlecode.lanterna.gui2.dialogs.FileDialog;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.wyvrn.increment.GameState;
import com.wyvrn.increment.panels.Header;

/**
 * MainMenu
 */
public class MainMenu extends GameWindow {

    public MainMenu(MultiWindowTextGUI gui) {
        super(gui);
        this.populateWindow();
    }

    private void populateWindow() {
        Panel buttonsPanel = new Panel();
        Panel headerPanel = new Panel();
        Panel mainMenuGrid = new Panel();

        GridLayout layout = new GridLayout(1);
        layout.setVerticalSpacing(2);

        headerPanel.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, true, false));
        buttonsPanel.setLayoutData(GridLayout.createLayoutData(Alignment.CENTER, Alignment.CENTER, false, false));

        // Header is added before menu buttons
        mainMenuGrid.addComponent(headerPanel);
        mainMenuGrid.addComponent(buttonsPanel);
        mainMenuGrid.setLayoutManager(layout);

        try {
            InputStream stream = MainMenu.class.getResourceAsStream("banner.txt");
            headerPanel.addComponent(Header.fromStream(stream));
        } catch (FileNotFoundException e) {
            String err = "oopsie i couldnt find banner.txt";
            System.err.println(err);
            headerPanel.addComponent(new Header(err));
        }

        for (Button btn : this.generateMenuItems()) {
            btn.setRenderer(new Button.FlatButtonRenderer());
            buttonsPanel.addComponent(btn);
        }

        setComponent(mainMenuGrid);
    }

    private ArrayList<Button> generateMenuItems() {
        ArrayList<Button> buttons = new ArrayList<>();
        MultiWindowTextGUI gui = super.getGui();

        // Load Game
        buttons.add(new Button("Load Game", new Runnable() {
            @Override
            public void run() {
                Path saveDir = Paths.get(System.getProperty("user.home"), ".increment-saves");
                try {
                    saveDir.toFile().createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                FileDialog dialog = new FileDialogBuilder()
                        .setTitle("Load Game")
                        .setDescription("Choose a save file (probably ending in .json)")
                        .setSelectedFile(saveDir.toFile())
                        .setActionLabel("Load")
                        .build();

                File input = dialog.showDialog(gui);

                try {
                    GameState loadedState = GameState.fromSaveFile(input);
                    MachinesWindow mWindow = new MachinesWindow(gui, input, loadedState);
                    gui.addWindowAndWait(mWindow);
                    
                    
                } catch (Exception e) {
                    MessageDialog errDialog = new MessageDialogBuilder()
                            .setTitle("Load Error")
                            .setText("Error occured while loading file.\n" + e.getMessage())
                            .build();

                    errDialog.showDialog(gui);
                }

            }
        }));

        // New Game
        buttons.add(new Button("New Game", new Runnable() {
            @Override
            public void run() {
                Path saveDir = Paths.get(System.getProperty("user.home") + "/.increment-saves/");
                try {
                    saveDir.toFile().createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TextInputDialog dialog = new TextInputDialogBuilder()
                        .setTitle("New Game")
                        .setDescription("Enter a name for your new game (Alphanumeric only).")
                        .setValidationPattern(Pattern.compile("^[a-zA-Z0-9-_]+$"),
                                "Must be alphanumeric, dashes, or underscores.$")
                        .build();

                String input = dialog.showDialog(gui);
                File saveFile = new File(saveDir.toString(), input + ".json");

                try {
                    saveFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
