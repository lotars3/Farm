package pl.szymonsmenda.Farm.models;

public class Sheep extends Animal {

    Sheep(String name, double age, boolean isVaccinated) {
        super(name, age, isVaccinated);
    }

    public final String getTYPE() {
        return "SHEEP";
    }

    public void sound() {
        System.out.println("baa baa baa");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
