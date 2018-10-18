package pl.szymonsmenda.Farm.models;

public class Horse extends Animal{

    Horse(String name, double age, boolean isVaccinated){
        super(name, age, isVaccinated);
    }

    public final String getTYPE(){
        return  "HORSE";
    }

    public void sound(){
        System.out.println("neigh neigh neigh");
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
