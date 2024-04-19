package com.wyvrn.increment.buildings.machines;

import com.google.gson.JsonObject;

/**
 * SmallMachine
 */
public class SmallMachine extends Machine {
    public SmallMachine() {
        super(100, 10, "small");
    }

    public SmallMachine(int cost, int output) {
        super(cost, output, "small");
    }

    @Override
    public SmallMachine fromJsonObject(JsonObject obj) {
        int cost = obj.get("cost").getAsInt();
        int output = obj.get("output").getAsInt();
        return new SmallMachine(cost, output);
    }
}
