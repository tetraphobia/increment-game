package com.wyvrn.increment.buildings.machines;

/**
 * MachineFactory
 */
public class MachineFactory {

    public MachineFactory() {
        super();
    }

    SmallMachine createSmallMachine() {
        return new SmallMachine();
    }

    SmallMachine createSmallMachine(int cost, int output) {
        return new SmallMachine(cost, output);
    }
}
