package pl.szymonsmenda.Farm.controller;

import pl.szymonsmenda.Farm.models.Animal;
import pl.szymonsmenda.Farm.models.AnimalsFactory;
import pl.szymonsmenda.Farm.models.Barn;
import pl.szymonsmenda.Farm.models.Farmer;
import pl.szymonsmenda.Farm.view.UserView;
import java.util.Scanner;
import static java.lang.System.out;

public class Controller {
    private Scanner scanner;
    private Farmer farmer;

    public Controller() {
        scanner = new Scanner(System.in);
        farmer = new Farmer();
    }

    public void start() {
        UserView.welcomeMessage();
        createMenu();
    }

    private void createMenu() {
        int choose;
        do {
            UserView.showMenu();
            choose = this.scanner.nextInt();
            switch (choose) {
                case 1: {
                    showFarmerBarns(farmer);
                    UserView.askForTheNextTask();
                }
                break;
                case 2: {
                    handleAddNewBarn();
                    break;
                }
                case 3: {
                    if (farmer.countFarmerBarns() == 0) {
                        UserView.tryingAddAnimalWithoutHavingAnyBarn();
                        break;
                    }
                    UserView.messageAfter3rdOption();
                    UserView.showAvailableAnimalType();
                    int animalType = scanner.nextInt();
                    switch (animalType) {
                        case 1: {
                            addAnimalToBarn(createNewAnimal("PIG"));
                            break;
                        }
                        case 2: {
                            addAnimalToBarn( createNewAnimal("COW"));
                            break;
                        }
                        case 3: {
                            addAnimalToBarn( createNewAnimal("SHEEP"));
                            break;
                        }
                        case 4: {
                            addAnimalToBarn( createNewAnimal("HORSE"));
                            break;
                        }
                        case 5: {
                            System.out.println();
                            break;
                        }
                        default: {
                            UserView.instructUserToChooseCorrectly();
                            break;
                        }
                    } UserView.askForTheNextTask();
                    break;
                }
                case 4: {
                    if (farmer.countFarmerBarns() == 0) {
                        UserView.notifyThereIsNoBarn();
                        break;
                    }
                    UserView.messageAfterChoosingBarnRemoval();
                    handleRemoveBarn();
                    UserView.askForTheNextTask();
                    break;
                }
                case 5: {
                    if (farmer.countFarmerBarns() == 0) {
                        UserView.notifyThereIsNoAnimalInTheFarm();
                        UserView.askForTheNextTask();
                        break;
                    }
                    UserView.messageAfter5thOption();
                    UserView.showAvailableAnimalType();
                    int animalType = scanner.nextInt();
                    switch (animalType){
                        case 1:{
                            UserView.showFarmerBarns();
                            farmer.showMyBarns();
                            removeAnimalFromBarn(createNewAnimal("PIG"));
                            break;
                        }
                        case 2:{
                            removeAnimalFromBarn(createNewAnimal("COW"));
                            break;
                        }
                        case 3: {
                            removeAnimalFromBarn(createNewAnimal("SHEEP"));
                            break;
                        }
                        case 4:{
                            removeAnimalFromBarn(createNewAnimal("HORSE"));
                            break;
                        }
                        case 5:{
                            System.out.println();
                            break;
                        }
                        default: {
                        }
                    }
                    break;
                }
                case 6: {
                    farmer.saveFarm();
                    UserView.confirmationOfSavingTheFarm();
                    UserView.askForTheNextTask();
                    break;
                }
                case 7: {
                    farmer.setFarm(farmer.loadFarm());
                    showFarmerBarns(farmer);
                    UserView.askForTheNextTask();
                    break;
                }
            }
        } while (choose != 8);
        UserView.messageExit();
    }

    private void showFarmerBarns(Farmer farmer) {
        UserView.showFarmerBarns();
        if (farmer.countFarmerBarns() == 0) {
            UserView.messageNoBarnsYet();
        } else {
            farmer.showMyBarns();
        }
    }

    private void handleAddNewBarn() {
        showFarmerBarns(farmer);
        Barn newBarn = buildNewBarn();
        farmer.addBarn(newBarn);
        out.println(UserView.confirmationThatBarnHasBeenBuilt() + newBarn.toString());
        UserView.askForTheNextTask();
    }

    private void handleRemoveBarn(){
        UserView.choosingBarnToBeCompletelyRemoved();
        farmer.showMyBarns();
        int idOfTheBarnToBeRemoved = scanner.nextInt();
        int index = 0;
        for (Barn b : farmer.farm){
            if (b.getId()!=idOfTheBarnToBeRemoved){
                index++;
            } else break;
        }
        out.println(UserView.confirmationThatBarnHasBeenRemoved() + farmer.farm.get(index).toString());
        farmer.removeBarn(index);
    }

    private Barn buildNewBarn() {
        UserView.askForTheNameOfNewBarn();
        Scanner scanner = new Scanner(System.in);
        String barnName = scanner.nextLine();
        Barn newBarn = new Barn(barnName);
        newBarn.setId();
        return newBarn;
    }

    private Animal createNewAnimal(String animalType) {
        UserView.someQuestionsAboutNewAnimal();
        Scanner scanner = new Scanner(System.in);
        UserView.askForAnimalName();
        String animalName = scanner.nextLine();
        UserView.askForAnimalAge();
        double animalAge = scanner.nextDouble();
        UserView.askIfVaccinated();
        boolean isItVaccinated = scanner.hasNext("yes");
        return AnimalsFactory.getAnimal(animalType, animalName, animalAge,isItVaccinated);
    }

    private void addAnimalToBarn(Animal animalToAdd) {
        UserView.showFarmerBarns();
        farmer.showMyBarns();
        UserView.askForBarnNumberToAddAnimal();
        int whichBarn = this.scanner.nextInt();
        int indexNumber = 0;
        for (Barn b : farmer.farm) {
            if (b.getId() != whichBarn) {
                indexNumber++;
            } else break;
        }
        farmer.farm.get(indexNumber).addAnimal(animalToAdd);
        UserView.confirmationAddAnimal();
        System.out.println(farmer.farm.get(indexNumber).toString());
    }

    private void removeAnimalFromBarn(Animal animalForRemoval){
        UserView.showFarmerBarns();
        farmer.showMyBarns();
        UserView.askFromWhichBarnShouldAnimalBeRemoved();
        Scanner scanner = new Scanner(System.in);
        int whichBarn = scanner.nextInt();
        if (farmer.farm.get(whichBarn).getAnimals().contains(animalForRemoval)){
            farmer.farm.get(whichBarn).getAnimals().remove(animalForRemoval);
            out.println(UserView.confirmationOfAnimalRemoval() + animalForRemoval.toString());
        } else {
            UserView.notifyNoSuchAnimalInBarn();
        }
        UserView.askForTheNextTask();
       }
    }