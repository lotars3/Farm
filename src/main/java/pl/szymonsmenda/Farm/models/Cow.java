package pl.szymonsmenda.Farm.models;

public class Cow extends Animal {

    Cow(String name, double age, boolean isVaccinated) {
        super(name, age, isVaccinated);
    }

    public final String getTYPE() {
        return "COW";
    }

    public void sound() {
        System.out.println("moo moomooo");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
