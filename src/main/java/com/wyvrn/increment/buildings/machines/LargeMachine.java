package com.wyvrn.increment.buildings.machines;

/**
 * LargeMachine
 */
public class LargeMachine extends Machine implements Upgradable {
    double upgradeCost;
    double upgradeOutput;
    int upgradeLevel;

    public LargeMachine(int cost, int output) {
        super(cost, output, "large");
        this.upgradeCost = 0.8;
        this.upgradeOutput = 1.2;
        this.upgradeLevel = 0;
    }

    public LargeMachine() {
    }

    @Override
    public int getUpgradeCost() {
        return (int) (super.getOutput() * this.upgradeCost);
    }

    public void setUpgradeCost(double upgradeCost) {
        this.upgradeCost = upgradeCost;
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
    public Machine createDefault() {
        return new LargeMachine(1000, 30);
    }

    @Override
    public String toString() {
        return "[-" + this.getUpgradeLevel() + "-]";
    }
}
