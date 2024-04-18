package com.wyvrn.increment.buildings.machines;

/**
 * SmallMachine
 */
public class SmallMachine extends Machine {
    public SmallMachine() {
    }

    public SmallMachine(int cost, int output) {
        super(cost, output, "small");
    }

    @Override
    public Machine createDefault() {
        return new SmallMachine(100, 10);
    }
}
