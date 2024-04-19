package com.wyvrn.increment.buildings.machines;

import com.google.gson.JsonObject;

interface Upgradable {
    class Defaults {
        public static final double UPGRADE_COST = 0.8;
        public static final double UPGRADE_OUTPUT = 1.2;
        public static final int UPGRADE_LEVEL = 0;
    }

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

    /**
     * Parses a {@link JsonObject} and creates a Machine using the parsed values.
     * 
     * @param obj A {@link JsonObject}
     * @return {@link Machine}
     */
    public abstract Machine fromJsonObject(JsonObject obj);
}
