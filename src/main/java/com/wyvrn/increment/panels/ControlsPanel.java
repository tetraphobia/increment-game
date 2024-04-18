package com.wyvrn.increment.panels;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.wyvrn.increment.Dialog;
import com.wyvrn.increment.GameState;
import com.wyvrn.increment.buildings.machines.LargeMachine;
import com.wyvrn.increment.buildings.machines.Machine;
import com.wyvrn.increment.buildings.machines.SmallMachine;

/**
 * ControlsPanel
 */
public class ControlsPanel extends Panel {
    private MultiWindowTextGUI gui;
    private GameState state;
    // A callback for when an option is chosen. Used to update the screen.
    private Runnable onSubmit;

    public ControlsPanel(MultiWindowTextGUI gui, GameState state, Runnable onSubmit) {
        super();
        this.gui = gui;
        this.state = state;
        this.onSubmit = onSubmit;

        for (Button btn : this.generateButtons()) {
            btn.setRenderer(new Button.FlatButtonRenderer());
            this.addComponent(btn);
        }
    }

    private ArrayList<Button> generateButtons() {
        ArrayList<Button> buttons = new ArrayList<>();

        // Purchase
        buttons.add(new Button("Purchase", new Runnable() {
            @Override
            public void run() {
                ActionListDialog dialog = new ActionListDialogBuilder()
                        .setTitle("Purchase")
                        .addAction("Small Machine (100)", new Runnable() {
                            @Override
                            public void run() {
                                // TODO I'm hardcoding this in out of laziness. Sue me :)
                                if (state.getCredits() < 100)
                                    return;
                                state.setCredits(state.getCredits() - 100);
                                state.addMachine(new SmallMachine().createDefault());
                                return;
                            }
                        })
                        .addAction("Large Machine (1000)", new Runnable() {
                            @Override
                            public void run() {
                                if (state.getCredits() < 1000)
                                    return;
                                state.setCredits(state.getCredits() - 1000);
                                state.addMachine(new LargeMachine().createDefault());
                                return;
                            }
                        })
                        .build();
                dialog.showDialog(gui);
                onSubmit.run();
            }
        }));

        // Upgrade
        buttons.add(new Button("Upgrade", new Runnable() {
            @Override
            public void run() {
                ActionListDialogBuilder builder = new ActionListDialogBuilder()
                        .setTitle("Upgrade");
                ArrayList<Machine> machines = state.getMachines();

                for (Machine machine : machines) {
                    if (machine instanceof LargeMachine) {
                        LargeMachine lMachine = (LargeMachine) machine;
                        builder.addAction(
                                lMachine.toString() + " (" + lMachine.getUpgradeCost() + ")",
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        lMachine.upgrade();
                                    }
                                });
                    }

                }
                ActionListDialog dialog = builder.build();
                dialog.showDialog(gui);
                onSubmit.run();
            }
        }));

        // Save
        buttons.add(new Button("Save", new Runnable() {
            @Override
            public void run() {
                // TODO refactor this to call GameState.toString(), then save the returning JSON
                Gson gson = new Gson();
                Path saveDir = Paths.get(System.getProperty("user.home"),
                        ".increment-saves/");
                try {
                    Files.createDirectories(saveDir);
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
                    BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
                    String json = gson.toJson(state);
                    writer.write(json);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));

        // Quit
        buttons.add(new Button("Quit to Menu", new Runnable() {
            @Override
            public void run() {
                gui.removeWindow(gui.getActiveWindow());
            }
        }));

        return buttons;

    }
}
