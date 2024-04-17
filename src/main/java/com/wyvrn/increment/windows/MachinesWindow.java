package com.wyvrn.increment.windows;

import java.io.File;
import java.util.Arrays;

import com.googlecode.lanterna.gui2.Border;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.LayoutData;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.GridLayout.Alignment;
import com.wyvrn.increment.GameState;
import com.wyvrn.increment.panels.ControlsPanel;
import com.wyvrn.increment.panels.MachinesPanel;

/**
 * MachinesWindow
 */
public class MachinesWindow extends GameWindow {
    private GameState state;
    private File loadedFile;

    public MachinesWindow(MultiWindowTextGUI gui, File loadedFile, GameState state) {
        super(gui);
        super.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN, Window.Hint.NO_DECORATIONS));
        this.populateWindow();
    }

    private void populateWindow() {
        Panel machinesGrid = new Panel();
        Panel smallMachinesPanel = new MachinesPanel();
        Panel largeMachinesPanel = new MachinesPanel();
        Panel controlsPanel = new ControlsPanel(super.getGui(), this.state);

        GridLayout layout = new GridLayout(1);
        layout.setVerticalSpacing(1);

        LayoutData machinePanelData = GridLayout.createLayoutData(Alignment.BEGINNING, Alignment.BEGINNING, true, false);
        LayoutData controlsPanelData = GridLayout.createLayoutData(Alignment.CENTER, Alignment.END, true, true);

        smallMachinesPanel.setLayoutData(machinePanelData);
        largeMachinesPanel.setLayoutData(machinePanelData);
        controlsPanel.setLayoutData(controlsPanelData);

        machinesGrid.addComponent(smallMachinesPanel.withBorder(Borders.singleLineBevel("Small Machines")));
        machinesGrid.addComponent(largeMachinesPanel.withBorder(Borders.singleLineBevel("Large Machines")));
        machinesGrid.addComponent(controlsPanel.withBorder(Borders.singleLineBevel()));
        machinesGrid.setLayoutManager(layout);

        setComponent(machinesGrid);
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public File getLoadedFile() {
        return loadedFile;
    }

    public void setLoadedFile(File loadedFile) {
        this.loadedFile = loadedFile;
    }

}
