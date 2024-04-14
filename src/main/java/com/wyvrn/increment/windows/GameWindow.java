package com.wyvrn.increment.windows;

import java.util.Arrays;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;

/**
 * GameWindow
 */
public class GameWindow extends BasicWindow {

    public GameWindow() {
        super();
        super.setHints(Arrays.asList(Window.Hint.CENTERED, Window.Hint.FULL_SCREEN));
        super.setTheme(new SimpleTheme(TextColor.ANSI.GREEN_BRIGHT, TextColor.ANSI.DEFAULT));
    }
}
