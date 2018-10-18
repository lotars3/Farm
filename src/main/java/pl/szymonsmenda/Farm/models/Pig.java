package pl.szymonsmenda.Farm.models;

public class Pig extends Animal{

    Pig(String name, double age, boolean isVaccinated){
        super(name, age, isVaccinated);
    }

    public final String getTYPE(){
        return "PIG";
    }

    public void sound(){
        System.out.println("oink oink oink");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

