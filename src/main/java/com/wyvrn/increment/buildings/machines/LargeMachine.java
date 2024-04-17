package com.wyvrn.increment.buildings.machines;

/**
 * LargeMachine
 */
public class LargeMachine extends Machine implements Upgradable {

    public LargeMachine(int cost, int output) {
        super(cost, output);
    }

    public LargeMachine create() {
        return new LargeMachine(1000, 50);
    }

    @Override
    public int getUpgradeCost() {
        return (int) (super.getCost() * Upgradable.upgradeCost);
    }

    @Override
    public int getUpgradeOutput() {
        return (int) (super.getOutput() * Upgradable.upgradeOutput);
    }

    @Override
    public void upgrade() {
        this.setOutput((int) (super.getOutput() * Upgradable.upgradeOutput));
    }
}
