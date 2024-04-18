package com.wyvrn.increment.panels;

import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.Panel;

/**
 * MachinesPanel
 */
public class MachinesPanel extends Panel {

    public MachinesPanel() {
        super();
        this.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
    }
}
