package com.wyvrn.increment;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;

/**
 * Dialog
 */
public class Dialog {
    public static void display(MultiWindowTextGUI gui, String title, String message) {
        MessageDialog dialog = new MessageDialogBuilder()
                .setTitle(title)
                .setText(message)
                .build();

        dialog.showDialog(gui);
    }

    public static void display(MultiWindowTextGUI gui, String message) {
        MessageDialog dialog = new MessageDialogBuilder()
                .setText(message)
                .build();

        dialog.showDialog(gui);
    }
}
