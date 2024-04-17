package com.wyvrn.increment.windows;

import java.util.Arrays;

import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;

/**
 * RootWindow
 */
public abstract class RootWindow extends BasicWindow {
    private MultiWindowTextGUI gui;

    public RootWindow(MultiWindowTextGUI gui) {
        super();
        super.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
        super.setTheme(LanternaThemes.getRegisteredTheme("businessmachine"));
        this.gui = gui;
    }

    public MultiWindowTextGUI getGui() {
        return gui;
    }

    public void setGui(MultiWindowTextGUI gui) {
        this.gui = gui;
    }
}
