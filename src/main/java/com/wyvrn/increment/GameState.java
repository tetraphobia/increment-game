package com.wyvrn.increment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wyvrn.increment.buildings.machines.Machine;
import com.wyvrn.increment.buildings.machines.MachineFactory;
import com.wyvrn.increment.buildings.machines.SmallMachine;

class InvalidSaveException extends IllegalArgumentException {
    public InvalidSaveException() {
        super("Save data is invalid or corrupted");
    }
}

/**
 * GameState
 *
 * @param   machines An ArrayList of Machine objects.
 * @param   credits  An int representing the starting number of credits.
 * @return  {@link GameState}
 */
public class GameState {
    private ArrayList<Machine> machines;
    private int credits;

    public GameState() {
        this.machines = new ArrayList<>();
        this.machines.add(new SmallMachine());
        this.credits = 0;
    }

    public GameState(ArrayList<Machine> machines, int credits) {
        this.machines = machines;
        this.credits = credits;
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

    /**
     * Causes each machine in the {@link GameState} instance to produce credits.
     */
    public void tickAll() {
        // This could be refactored to pass a callback with the current GameState to the
        // Machine and run it, if I wanted machines to be able to do more than just make
        // credits, but for simplicity I'm doing this.
        int accumulator = 0;

        for (Machine machine : this.machines) {
            accumulator += machine.getOutput();
        }

        this.setCredits(this.getCredits() + accumulator);
    }

    /**
     * Loads data from a json save file and returns a new GameState using those
     * values.
     *
     * @param   saveFile A File pointing to a valid json save file.
     * @return  {@link GameState}
     * @throws  FileNotFoundException
     * @throws  InvalidSaveException
     */
    public static GameState fromSaveFile(File saveFile) throws FileNotFoundException, InvalidSaveException {
        BufferedReader reader = new BufferedReader(new FileReader(saveFile));
        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

        int credits = jsonObject.get("credits").getAsInt();
        JsonArray jsonMachines = jsonObject.get("machines").getAsJsonArray();
        ArrayList<Machine> machines = new ArrayList<>();

        for (JsonElement jsonMachine : jsonMachines) {
            JsonObject obj = jsonMachine.getAsJsonObject();
            String type = obj.get("type").getAsString();
            MachineFactory factory = new MachineFactory();

            Machine machine = factory.create(type).fromJsonObject(obj);
            machines.add(machine);
        }

        return new GameState(machines, credits);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
