package com.wyvrn.increment.buildings.machines;

interface Upgradable {
    final double upgradeCost = 0.2;
    final double upgradeOutput = 1.2;

    public int getUpgradeCost();
    public int getUpgradeOutput();
    public void upgrade();
}

/**
 * Machine
 */
public abstract class Machine {
    private int cost;
    private int output;

    public Machine(int cost, int output) {
        this.cost = cost;
        this.output = output;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    
    /**
     * Factory method to create a Machine with default values.
     *
     * @return Machine
     */
    public abstract Machine create();
}
