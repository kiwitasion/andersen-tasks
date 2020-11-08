package Multithreading.logisticCenter;

import Multithreading.logisticCenter.FruitBox;
import Multithreading.logisticCenter.FruitLogisticCenter;

public class Unloader implements Runnable{


    private FruitLogisticCenter center;
    private FruitBox fruitBox;
    private int count;

    public Unloader(FruitLogisticCenter center, FruitBox fruitBox, int count) {
        this.center = center;
        this.fruitBox = fruitBox;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                center.unload(fruitBox);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
