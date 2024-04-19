package com.wyvrn.increment.buildings.machines;

import java.util.ArrayList;

/**
 * MachineFactory
 */
public class MachineFactory {

    public MachineFactory() {
    }

    public Machine create(String type) {
        if (type.equals("small")) {
            return new SmallMachine();
        }

        if (type.equals("large")) {
            return new LargeMachine();
        }

        return new SmallMachine(0, 0);
    }
}
