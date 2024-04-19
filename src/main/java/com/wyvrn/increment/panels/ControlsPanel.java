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
    private Runnable onSubmit; // A callback for when an option is chosen.

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
                SmallMachine sMachine = new SmallMachine();
                LargeMachine lMachine = new LargeMachine();
                ActionListDialog dialog = new ActionListDialogBuilder()
                        .setTitle("Purchase")
                        .addAction("Small Machine (" + sMachine.getCost() + ")", new Runnable() {
                            @Override
                            public void run() {
                                if (state.getCredits() < sMachine.getCost())
                                    return;
                                state.setCredits(state.getCredits() - sMachine.getCost());
                                state.addMachine(sMachine);
                                return;
                            }
                        })
                        .addAction("Large Machine (" + lMachine.getCost() + ")", new Runnable() {
                            @Override
                            public void run() {
                                if (state.getCredits() < lMachine.getCost())
                                    return;
                                state.setCredits(state.getCredits() - lMachine.getCost());
                                state.addMachine(new LargeMachine());
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

                String input = "";

                input = dialog.showDialog(gui);

                if (input == null) {
                    return;
                }
                File saveFile = new File(saveDir.toString(), input + ".json");

                try {
                    saveFile.createNewFile();
                    BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
                    String json = state.toString();
                    writer.write(json);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    onSubmit.run();
                }
            }
        }));

        // Quit
        buttons.add(new Button("Quit to Menu", new Runnable() {
            @Override
            public void run() {
                gui.removeWindow(gui.getActiveWindow());
                onSubmit.run();
            }
        }));

        return buttons;

    }
}
