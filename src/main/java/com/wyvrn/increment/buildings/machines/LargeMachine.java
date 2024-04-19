package com.wyvrn.increment.buildings.machines;

import com.google.gson.JsonObject;

/**
 * LargeMachine
 */
public class LargeMachine extends Machine implements Upgradable {
    double upgradeCost;
    double upgradeOutput;
    int upgradeLevel;

    public LargeMachine(int cost, int output, double upgradeCost, double upgradeOutput, int upgradeLevel) {
        super(cost, output, "large");
        this.upgradeCost = upgradeCost;
        this.upgradeLevel = upgradeLevel;
        this.upgradeOutput = upgradeOutput;
    }

    public LargeMachine(int cost, int output) {
        super(cost, output, "large");
        this.upgradeCost = Upgradable.Defaults.UPGRADE_COST;
        this.upgradeOutput = Upgradable.Defaults.UPGRADE_OUTPUT;
        this.upgradeLevel = Upgradable.Defaults.UPGRADE_LEVEL;
    }

    public LargeMachine() {
        super(1000, 100, "large");
        this.upgradeCost = Upgradable.Defaults.UPGRADE_COST;
        this.upgradeOutput = Upgradable.Defaults.UPGRADE_OUTPUT;
        this.upgradeLevel = Upgradable.Defaults.UPGRADE_LEVEL;
    }

    @Override
    public int getUpgradeCost() {
        return (int) (super.getOutput() * this.upgradeCost);
    }

    @Override
    public int getUpgradeOutput() {
        return (int) (super.getOutput() * this.upgradeOutput);
    }

    @Override
    public int getUpgradeLevel() {
        return this.upgradeLevel;
    }

    @Override
    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    @Override
    public void upgrade() {
        this.setUpgradeLevel(this.getUpgradeLevel() + 1);
        this.setOutput((int) (super.getOutput() * this.upgradeOutput));
    }

    @Override
    public String toString() {
        return "[-" + this.getUpgradeLevel() + "-]";
    }

    @Override
    public LargeMachine fromJsonObject(JsonObject obj) {
        int cost = obj.get("cost").getAsInt();
        int output = obj.get("output").getAsInt();
        double upgradeCost = obj.get("upgradeCost").getAsDouble();
        double upgradeOutput = obj.get("upgradeOutput").getAsDouble();
        int upgradeLevel = obj.get("upgradeLevel").getAsInt();

        return new LargeMachine(cost, output, upgradeCost, upgradeOutput, upgradeLevel);
    }
}
