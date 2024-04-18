package com.wyvrn.increment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wyvrn.increment.buildings.machines.LargeMachine;
import com.wyvrn.increment.buildings.machines.Machine;
import com.wyvrn.increment.buildings.machines.SmallMachine;

class InvalidSaveException extends IllegalArgumentException {
    public InvalidSaveException() {
        super("Save data is invalid or corrupted");
    }
}

/**
 * GameState
 */
public class GameState {
    private ArrayList<Machine> machines;
    private int credits;
    private int lifetimeGenerated;

    public GameState(ArrayList<Machine> machines, int credits, int lifetimeGenerated) {
        this.machines = machines;
        this.credits = credits;
    }

    public int getLifetimeGenerated() {
        return lifetimeGenerated;
    }

    public void setLifetimeGenerated(int lifetimeGenerated) {
        this.lifetimeGenerated = lifetimeGenerated;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public void setMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }

    public void addMachine(Machine machine) {
        this.machines.add(machine);
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void tickAll() {
        int accumulator = 0;
        for (Machine machine : this.machines) {
            accumulator += machine.getOutput();
        }

        this.setCredits(this.getCredits() + accumulator);
    }

    public static GameState fromSaveFile(File saveFile) throws FileNotFoundException, InvalidSaveException {
        BufferedReader reader = new BufferedReader(new FileReader(saveFile));
        JsonElement jsonElement = JsonParser.parseReader(reader);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        int credits = jsonObject.get("credits").getAsInt();
        int lifetimeGenerated = jsonObject.get("lifetimeGenerated").getAsInt();
        JsonArray jsonMachines = jsonObject.get("machines").getAsJsonArray();
        ArrayList<Machine> machines = new ArrayList<>();

        for (JsonElement jsonMachine : jsonMachines) {
            JsonObject obj = jsonMachine.getAsJsonObject();
            String type = obj.get("type").getAsString();
            if (type.equals("small")) {
                SmallMachine machine = new SmallMachine(obj.get("cost").getAsInt(), obj.get("output").getAsInt());
                machines.add(machine);
            } else if (type.equals("large")) {
                LargeMachine machine = new LargeMachine(obj.get("cost").getAsInt(), obj.get("output").getAsInt());
                machines.add(machine);
            }
        }

        return new GameState(machines, credits, lifetimeGenerated);
    }

    public static GameState fromDefaults() {
        ArrayList<Machine> machines = new ArrayList<>();

        machines.add(new SmallMachine().createDefault());
        return new GameState(machines, 0, 0);
    }

    public static void main(String[] args) {
        // Testing
        try {
            GameState something = GameState.fromSaveFile(new File("/home/tetraphobia/.increment-saves/jacob.json"));
            System.out.println(something);
            System.out.println(something.getCredits());
            System.out.println(something.getLifetimeGenerated());
            System.out.println(something.getMachines());
        } catch (InvalidSaveException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
