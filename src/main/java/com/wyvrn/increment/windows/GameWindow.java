package com.wyvrn.increment.windows;

import java.util.Arrays;

import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;

/**
 * GameWindow
 */
public abstract class GameWindow extends BasicWindow {
    private MultiWindowTextGUI gui;

    public GameWindow(MultiWindowTextGUI gui) {
        super();
        super.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
        this.gui = gui;
        gui.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
    }

    public MultiWindowTextGUI getGui() {
        return gui;
    }

    public void setGui(MultiWindowTextGUI gui) {
        this.gui = gui;
    }
}
