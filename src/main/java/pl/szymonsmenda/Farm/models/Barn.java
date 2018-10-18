package pl.szymonsmenda.Farm.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Barn implements Serializable{
    private static int nextID = 0;
    private int id;
    private String name;
    private ArrayList<Animal> animals;

    public Barn(String name){
        this.name = name;
        this.animals = new ArrayList<>();
    }

    private void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public final void setId(){
        id = nextID;
        nextID++;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animalToAdd){
                this.animals.add(animalToAdd);
    }

    @Override
    public String toString() {
        return "Barn(" +
                "id=" + id +
                ", name=" + name +
                ", animals=" + animals +
                ')';
    }
    String barnFormatToWrite(){
        return id + ";" + name +"[."+animalsToWrite(animals);
    }
    private String animalsToWrite(ArrayList<Animal> animals){
        StringBuilder allAnimals = new StringBuilder();
        for (Animal a : animals){
            String animal = a.formatToWrite();
            allAnimals = allAnimals.append(animal);
        }
        String allAnimalsInBarnToWrite = allAnimals.toString();
        return allAnimalsInBarnToWrite;
    }

    static Barn readData(Scanner in){
        String line = in.nextLine();
        String[] parts = line.split("\\[");
        String dataNeededForBarnCreation = parts[0];
        String[] barnData = dataNeededForBarnCreation.split(";");
        int id = Integer.parseInt(barnData[0]);
        String name = barnData[1];
        Barn newBarn = new Barn(name);
        newBarn.setId();
        String dataNeededForAnimals = parts[1].substring(1);
        if (!dataNeededForAnimals.isEmpty()) {
            newBarn.setAnimals(Animal.animalsInTheBarn(dataNeededForAnimals));
        }
        return newBarn;
    }
}

