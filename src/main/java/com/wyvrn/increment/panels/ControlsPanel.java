package com.wyvrn.increment.panels;

import java.util.ArrayList;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialog;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.wyvrn.increment.GameState;

/**
 * ControlsPanel
 */
public class ControlsPanel extends Panel {
    private MultiWindowTextGUI gui;
    private GameState state;

    public ControlsPanel(MultiWindowTextGUI gui, GameState state) {
        super();
        this.gui = gui;
        this.state = state;

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
                                return;
                            }
                        })
                        .addAction("Large Machine (1000)", new Runnable() {
                            @Override
                            public void run() {
                                return;
                            }
                        })
                        .build();
                dialog.showDialog(gui);

            }
        }));

        // Upgrade
        buttons.add(new Button("Upgrade", new Runnable() {
            @Override
            public void run() {
                ActionListDialog dialog = new ActionListDialogBuilder()
                        .setTitle("Upgrade")
                        .addAction("Large Machine", new Runnable() {
                            @Override
                            public void run() {
                                return;
                            }
                        })
                        .build();
                dialog.showDialog(gui);

            }
        }));

        // Save
        buttons.add(new Button("Save", new Runnable() {
            @Override
            public void run() {
                return;
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
