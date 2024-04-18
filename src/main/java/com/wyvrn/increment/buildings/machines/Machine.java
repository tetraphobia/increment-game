package com.wyvrn.increment.buildings.machines;

interface Upgradable {
    public int getUpgradeCost();

    public int getUpgradeOutput();

    public int getUpgradeLevel();

    public void setUpgradeLevel(int upgradeLevel);

    public void upgrade();
}

/**
 * Machine
 */
public abstract class Machine {
    private int cost;
    private int output;
    private String type;

    public Machine() {
        this.cost = 0;
        this.output = 0;
        this.type = "default";
    }

    public Machine(int cost, int output, String type) {
        this.cost = cost;
        this.output = output;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    // TODO refactor this into a MachineFactory class
    public abstract Machine createDefault();
}
