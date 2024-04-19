package com.wyvrn.increment.windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.googlecode.lanterna.gui2.AnimatedLabel;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LayoutData;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.LinearLayout.GrowPolicy;
import com.wyvrn.increment.GameState;
import com.wyvrn.increment.buildings.machines.LargeMachine;
import com.wyvrn.increment.buildings.machines.Machine;
import com.wyvrn.increment.panels.ControlsPanel;
import com.wyvrn.increment.panels.MachinesPanel;

/**
 * MachinesWindow
 */
public class MachinesWindow extends GameWindow {
    private GameState state;
    private Panel infoPanel;

    public MachinesWindow(MultiWindowTextGUI gui, GameState state) {
        super(gui);
        this.state = state;
        super.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN, Window.Hint.NO_DECORATIONS));
        this.populateWindow();
        this.initTicker();
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    private void initTicker() {
        Runnable ticker = new Runnable() {
            public void run() {
                state.tickAll();
                infoPanel.removeAllComponents();
                infoPanel.addComponent(new Label("Credits: " + state.getCredits()));
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(ticker, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * A very chonky function that sets the layout and populates the screen. I will
     * not be refactoring this.
     */
    private void populateWindow() {
        Panel machinesGrid = new Panel();
        this.infoPanel = new Panel();
        Panel smallMachinesPanel = new MachinesPanel();
        Panel largeMachinesPanel = new MachinesPanel();
        Panel controlsPanel = new ControlsPanel(super.getGui(), this.state, new Runnable() {
            public void run() {
                populateWindow();
            }
        });

        LinearLayout layout = new LinearLayout(Direction.VERTICAL);

        LayoutData infoPanelData = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill);
        LayoutData machinePanelData = LinearLayout.createLayoutData(LinearLayout.Alignment.Fill, GrowPolicy.CanGrow);
        LayoutData controlsPanelData = LinearLayout.createLayoutData(LinearLayout.Alignment.Center, GrowPolicy.CanGrow);

        infoPanel.setLayoutData(infoPanelData);
        smallMachinesPanel.setLayoutData(machinePanelData);
        largeMachinesPanel.setLayoutData(machinePanelData);
        controlsPanel.setLayoutData(controlsPanelData);

        machinesGrid.addComponent(this.infoPanel.withBorder(Borders.doubleLine()));
        machinesGrid.addComponent(smallMachinesPanel.withBorder(Borders.singleLineBevel("Small Machines")));
        machinesGrid.addComponent(largeMachinesPanel.withBorder(Borders.singleLineBevel("Large Machines")));
        machinesGrid.addComponent(controlsPanel);
        machinesGrid.setLayoutManager(layout);

        ArrayList<Machine> machines = this.state.getMachines();

        this.infoPanel.addComponent(new Label("Credits: " + this.state.getCredits()));

        // I would refactor this into a class method like it used to be, but I'm tired.
        // This is done like this to fix the upgrade level not updating.
        for (Machine machine : machines) {
            if (machine.getType() == "small") {
                AnimatedLabel aLabel = new AnimatedLabel("[+]");
                aLabel.addFrame("[-]");
                smallMachinesPanel.addComponent(aLabel);
                aLabel.startAnimation(1000);
            }

            if (machine.getType() == "large") {
                LargeMachine lMachine = (LargeMachine) machine;
                AnimatedLabel aLabel = new AnimatedLabel("[-" + lMachine.getUpgradeLevel() + "-]");
                aLabel.addFrame("[~" + lMachine.getUpgradeLevel() + "~]");
                largeMachinesPanel.addComponent(aLabel);
                aLabel.startAnimation(1000);
            }
        }

        setComponent(machinesGrid);
    }

}
