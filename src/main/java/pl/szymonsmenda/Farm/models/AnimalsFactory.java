package pl.szymonsmenda.Farm.models;

public class AnimalsFactory {
    private static AnimalsFactory INSTANCE = new AnimalsFactory();

    private AnimalsFactory(){}

    public static AnimalsFactory getINSTANCE(){
        return INSTANCE;
    }

    public static Animal getAnimal(String animalType, String name, double age, boolean isVaccinated) {
        if (animalType == null) {
            return null;
        }
        if (animalType.equals("PIG")) {
            //nie wchodzi tu a powinno//
            return new Pig(name, age, isVaccinated);
        }
        if (animalType.equals("COW"))
            return new Cow(name, age, isVaccinated);
        if (animalType.equals("SHEEP"))
            return new Sheep(name, age, isVaccinated);
        if (animalType.equals("HORSE")) {
            return new Horse(name, age, isVaccinated);
        }
        System.out.println("źle"); //zwraca nulla zamiast robic konkretny typ zwierzaka//
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
