package com.wyvrn.increment.buildings.machines;

import java.util.ArrayList;

/**
 * MachineFactory
 */
public class MachineFactory {

    public MachineFactory() {
    }

    /**
     * @param type The type of machine ('small' | 'large'). Defaults to 'small' if
     *             entry cannot be found.
     * @return  {@link Machine}
     */
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
