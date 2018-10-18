package pl.szymonsmenda.Farm.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Farmer {
    public ArrayList<Barn> farm;

    public Farmer(){
        farm = new ArrayList<>();
    }

    public void setFarm(ArrayList<Barn> farm) {
        this.farm = farm;
    }

    public void addBarn (Barn barnToAdd) {
       farm.add(barnToAdd);
    }

    public void removeBarn(int barnID){
        farm.remove(barnID);
    }

    public int countFarmerBarns(){
       return farm.size();
    }

    public void showMyBarns (){
        for (Barn b : farm){
            System.out.println(b.toString());
            }
    }

    public void saveFarm (){
        ArrayList <Barn> farm = this.farm;

        File file = new File("Farm.txt");
        String cleanOld = "";

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String rozmiarFarmy = String.valueOf(farm.size());
        try {
            Files.write(file.toPath(),(rozmiarFarmy + '\r'+'\n').getBytes(),StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Barn b : farm){
            String barnToWrite = b.barnFormatToWrite();
            try {
                Files.write(file.toPath(),(barnToWrite + '\r'+'\n').getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Barn> loadFarm (){
        File file = new File("Farm.txt");
        ArrayList<Barn> farmerFarm = new ArrayList<>();
        try {
            Scanner in = new Scanner(new FileInputStream("Farm.txt"),"UTF-8");
            int numberOflines = in.nextInt();
            String line = in.nextLine();
                for (int i = 0 ;  i < numberOflines; i++){
                farmerFarm.add(Barn.readData(in));
                }
        }catch (FileNotFoundException e1) {
                e1.printStackTrace();
        }
    return farmerFarm;
    }
}



