package Multithreading.logisticCenter;

import Multithreading.logisticCenter.FruitBox;
import Multithreading.logisticCenter.FruitLogisticCenter;

import java.util.ArrayList;
import java.util.List;

public class Loader implements Runnable{

    private List<FruitBox> loadStore = new ArrayList<>();
    private FruitLogisticCenter center;


    public Loader(FruitLogisticCenter center) {
        this.center = center;
    }

    @Override
    public void run() {
        while(true) {
            FruitBox fruitFromCenter = null;
            try {
                fruitFromCenter = center.load();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (fruitFromCenter == null){ //Сhecking that the unloader has unloaded all fruits
                return;
            }
            loadStore.add(fruitFromCenter);
        }
    }
}
