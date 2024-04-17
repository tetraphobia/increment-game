package com.wyvrn.increment.buildings.machines;

/**
 * SmallMachine
 */
public class SmallMachine extends Machine {
    public SmallMachine(int cost, int output) {
        super(cost, output);
    }

    public SmallMachine create() {
        return new SmallMachine(100, 10);
    }
}
